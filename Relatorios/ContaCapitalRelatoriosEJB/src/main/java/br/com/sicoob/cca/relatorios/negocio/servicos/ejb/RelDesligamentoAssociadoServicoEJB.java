/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.cca.cadastro.negocio.dto.DadosDesligamentoDTO;
import br.com.sicoob.cca.cadastro.negocio.dto.FiltroContaCapitalDTO;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.dto.DadosDesligamentoEncontroContasDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.LancamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.dto.RelDesligamentoAssociadoDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelDesligamentoEncontroContasListaDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelDesligamentoAssociadoServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelDesligamentoAssociadoServicoRemote;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.EnderecoPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LocalizacaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaFisicaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaJuridicaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.TelefonePessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.LocalizacaoIntegracaoServicoLocal;

/**
 * Servico RelDesligamentoAssociadoServicoEJB
 */
@Stateless
@Local(RelDesligamentoAssociadoServicoLocal.class)
@Remote(RelDesligamentoAssociadoServicoRemote.class) 
public class RelDesligamentoAssociadoServicoEJB extends ContaCapitalRelatoriosServicoEJB implements RelDesligamentoAssociadoServicoLocal, RelDesligamentoAssociadoServicoRemote{
	
	public static final  String MSG_PROFISSAO_NAO_ESPECIFICADA = "OUTROS DECLARANTES NÃO ESPECIFICADOS NOS GRUPOS ANTERIORES";
	public static final  String MSG_NAO_ESPECIFICADA = "Não especificada";

	@EJB
	private CapesIntegracaoServicoLocal capesInt;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@EJB
	private LocalizacaoIntegracaoServicoLocal locInt;
	
	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;
	
	@EJB
	private LancamentoContaCapitalServicoLocal lancamentoContaCapitalServico;
	
	/**
	 * Metodo responsavel de gerar o relatorio de desligamento associado
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object gerarRelatorioDesligamentoAssociado(RelDesligamentoAssociadoDTO dtoEntrada) throws BancoobException {
		
		RelDesligamentoAssociadoDTO dtoRelatorio = buscarDadosRelatorio(dtoEntrada);
		List<RelDesligamentoAssociadoDTO> lista = new ArrayList<RelDesligamentoAssociadoDTO>(0);
		lista.add(dtoRelatorio);
		
		String nomeRelatorio = "";
		if(dtoRelatorio.getTipoPessoa() == (short) 0){
			nomeRelatorio = "CCA_Relatorio_DesligamentoAssociado.jasper";
		}
		if(dtoRelatorio.getTipoPessoa() == (short) 1){
			nomeRelatorio = "CCA_Relatorio_DesligamentoAssociadoPJ.jasper";
		}
		
		Map<String, Object> parametros = getParametrosComuns();	
		parametros.put("COD_RELATORIO", CodigoRelatorio.COD_REL_APROVACAO_QUADRO_PENDENCIA);
		
		InstituicaoIntegracaoDTO dtoInstituicaoIntegracaoDelegate = instituicaoIntegracaoServico.obterInstituicaoIntegracao(dtoEntrada.getIdInstituicao());
		parametros.put("DESC_SIGLA_COOP", dtoInstituicaoIntegracaoDelegate.getNumero() + " - " + dtoInstituicaoIntegracaoDelegate.getDescInstituicao());
		
		parametros.put("valorIntegralizado", lancamentoContaCapitalServico.calcularValorIntegralizado(dtoEntrada.getIdContaCapital()));
		
		return new RelatorioContaCapitalV2<RelDesligamentoAssociadoDTO>(lista, nomeRelatorio, parametros);
	}
	
	/**
	 * Metodo responsavel de gerar o relatorio de desligamento associado com encontro de contas
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object gerarRelatorioDesligamentoEncontroContas(Integer idContaCapital) throws BancoobException {
		ContaCapital cca = contaCapitalServico.obter(idContaCapital); 
		
		if(cca.getDescDadosDesligamento() == null) {
			throw new NegocioException("MSG_RELATORIO_ENCONTRO_CONTAS");
		}
		
		DadosDesligamentoEncontroContasDTO dadosDTO = new Gson().fromJson(cca.getDescDadosDesligamento(), DadosDesligamentoEncontroContasDTO.class);
		List<DadosDesligamentoEncontroContasDTO> lista = new ArrayList<DadosDesligamentoEncontroContasDTO>();
		lista.add(dadosDTO);
		
		String nomeRelatorio = "";
		if(dadosDTO.getTipoPessoa() == (short) 0){
			nomeRelatorio = "CCA_Relatorio_DesligamentoEncontroContas.jasper";
		} else if(dadosDTO.getTipoPessoa() == (short) 1){
			nomeRelatorio = "CCA_Relatorio_DesligamentoEncontroContasPJ.jasper";
		}
		
		Map<String, Object> parametros = getParametrosComuns();	
		InstituicaoIntegracaoDTO dtoInstituicaoIntegracaoDelegate = instituicaoIntegracaoServico.obterInstituicaoIntegracao(cca.getIdInstituicao());
		parametros.put("DESC_SIGLA_COOP", dtoInstituicaoIntegracaoDelegate.getNumero() + " - " + dtoInstituicaoIntegracaoDelegate.getDescInstituicao());
		return new RelatorioContaCapitalV2<DadosDesligamentoEncontroContasDTO>(lista, nomeRelatorio, parametros);
	}
	
	/**
	 * Metodo responsavel para buscar os dados que seram atribuidos ao relatorio
	 * @param dtoEntrada
	 * @return
	 * @throws BancoobException
	 */
	private RelDesligamentoAssociadoDTO buscarDadosRelatorio(RelDesligamentoAssociadoDTO dtoEntrada) throws BancoobException {
		
		RelDesligamentoAssociadoDTO dto = new RelDesligamentoAssociadoDTO();
		PessoaIntegracaoDTO pessoaIntegracaoDTO = capesInt.obterPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
	
		if(pessoaIntegracaoDTO.getCodTipoPessoa() != null){
			
			/**
			 * caso a pessoa seja uma pessoa fisica, busca os dados de pessoa fisica
			 */
			if(pessoaIntegracaoDTO.getCodTipoPessoa().shortValue() == (short) 0){
				dto = atribuirDadosPessoaFisica(dtoEntrada);
			}
			
			/**
			 * caso a pessoa seja uma pessoa juridica, busca os dados de pessoa juridica
			 */
			if(pessoaIntegracaoDTO.getCodTipoPessoa().shortValue() == (short) 1){
				dto = atribuirDadosPessoaJuridica(dtoEntrada);
			}
			
		}
		return dto;
		
	}

	/**
	 * Metodo responsavel para atribuir os dados de pessoa fisica ao dto de relatorio
	 * @param dtoEntrada
	 * @return
	 * @throws BancoobException
	 */
	private RelDesligamentoAssociadoDTO atribuirDadosPessoaFisica(RelDesligamentoAssociadoDTO dtoEntrada) throws BancoobException {
		
		PessoaIntegracaoDTO pessoaIntegracaoDTO = capesInt.obterPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		PessoaFisicaIntegracaoDTO pessoaFisicaIntegracaoDTO = capesInt.obterPessoaFisicaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		EnderecoPessoaIntegracaoDTO enderecoPessoaIntegracaoDTO = capesInt.obterEnderecoPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		TelefonePessoaIntegracaoDTO telefonePessoaIntegracaoDTO =  capesInt.obterTelefonePessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		
		/**
		 * DTO que vai segurar os dados para enviar ao relatorio
		 */
		RelDesligamentoAssociadoDTO dto = new RelDesligamentoAssociadoDTO();
		
		/**
		 * Seta o tipo da pessoa
		 */
		dto.setTipoPessoa(pessoaIntegracaoDTO.getCodTipoPessoa());
		
		/**
		 * Seta o numero de conta capital da pessoa
		 */
		ContaCapital contaCapital = contaCapitalServico.obter(dtoEntrada.getIdContaCapital());
		dto.setNumContaCapital(contaCapital.getNumContaCapital());
		
		/**
		 * Seta os valores da pessoa
		 */
		dto.setNomeCompleto(pessoaIntegracaoDTO.getNomeCompleto());
		dto.setCpfCnpj(pessoaIntegracaoDTO.getCpfCnpj());
		dto.setNumDocumento(pessoaFisicaIntegracaoDTO.getNumDocumento());
		dto.setEmissaoDocumento(pessoaFisicaIntegracaoDTO.getEmissaoDocumento());
		dto.setOrgaoDocumento(pessoaFisicaIntegracaoDTO.getOrgaoDocumento());
		dto.setUfDocumento(pessoaFisicaIntegracaoDTO.getUfDocumento());
		
		/**
		 * lógica para atribuir a filiação da pessoa fisica
		 */
		if(StringUtils.isNotBlank(pessoaFisicaIntegracaoDTO.getNomePai()) && StringUtils.isNotBlank(pessoaFisicaIntegracaoDTO.getNomeMae())){
			dto.setFiliacao((String) pessoaFisicaIntegracaoDTO.getNomePai() + " e " +  pessoaFisicaIntegracaoDTO.getNomeMae());	
		} else if(StringUtils.isNotBlank(pessoaFisicaIntegracaoDTO.getNomePai())) {
			dto.setFiliacao((String) pessoaFisicaIntegracaoDTO.getNomePai());
		} else if(StringUtils.isNotBlank(pessoaFisicaIntegracaoDTO.getNomeMae())) {
			dto.setFiliacao((String) pessoaFisicaIntegracaoDTO.getNomeMae());
		}
		
		dto.setNacionalidade(pessoaFisicaIntegracaoDTO.getNacionalidade());
		
		/**
		 * lógica para atribuir o localidade da pessoa fisica
		 */
		if(pessoaFisicaIntegracaoDTO.getIdNaturalidade() != null && pessoaFisicaIntegracaoDTO.getIdNaturalidade().intValue() > 0) {
			LocalizacaoIntegracaoDTO localizacaoIntegracaoDTO = locInt.consultarLocalidade(pessoaFisicaIntegracaoDTO.getIdNaturalidade());
			dto.setNaturalidade(localizacaoIntegracaoDTO.getMunicipio() + " - " + localizacaoIntegracaoDTO.getUf());
		} else {
			dto.setNaturalidade(pessoaFisicaIntegracaoDTO.getNaturalidade());
		}
		
		dto.setNascimento(pessoaFisicaIntegracaoDTO.getNascimento());
		dto.setDescSexo(pessoaFisicaIntegracaoDTO.getDescSexo());
		dto.setDescProfissao(pessoaFisicaIntegracaoDTO.getDescProfissao());
		
		/**
		 *lógica para atribuir "não especificada" caso a profissão da pessoa fisica não esteja definida
		 */
		if(dto.getDescProfissao() != null && dto.getDescProfissao().toString().equals(MSG_PROFISSAO_NAO_ESPECIFICADA)){
			dto.setDescProfissao(MSG_NAO_ESPECIFICADA);
		}
		dto.setEstadoCivil(pessoaFisicaIntegracaoDTO.getEstadoCivil());
		
		/**
		 * Dados de Endereço da pessoa fisica
		 */
		dto.setDescEnderecoResidencial(enderecoPessoaIntegracaoDTO.getDescEnderecoResidencial());
		dto.setNumResidencial(enderecoPessoaIntegracaoDTO.getNumEnderecoResidencial());
		dto.setComplementoResidencial(enderecoPessoaIntegracaoDTO.getComplementoEnderecoResidencial());
		dto.setBairroResidencial(enderecoPessoaIntegracaoDTO.getBairroEnderecoResidencial());
		dto.setCepResidencial(enderecoPessoaIntegracaoDTO.getCepEnderecoResidencial());
		
		/**
		 * if para atribuir localidade residencial (uf e municipio) da pessoa fisica
		 */
		if(enderecoPessoaIntegracaoDTO.getIdLocResidencial() != null) {
			LocalizacaoIntegracaoDTO localizacaoIntegracaoResidencialDTO = locInt.consultarLocalidade(enderecoPessoaIntegracaoDTO.getIdLocResidencial());
			dto.setUfResidencial(localizacaoIntegracaoResidencialDTO.getUf());
			dto.setMunicipioResidencial(localizacaoIntegracaoResidencialDTO.getMunicipio());
		}
		
		dto.setTelefoneResidencial(telefonePessoaIntegracaoDTO.getTelefoneEnderecoResidencial());
		
		return dto;
	}

	/**
	 * Metodo responsavel para atribuir os dados de pessoa juridica ao dto de relatorio
	 * @param dtoEntrada
	 * @return
	 * @throws BancoobException
	 */
	private RelDesligamentoAssociadoDTO atribuirDadosPessoaJuridica(RelDesligamentoAssociadoDTO dtoEntrada) throws BancoobException {
		
		/**
		 * DTOs que seguram os valores da pessoa no capes para atribuir ao dto do relatorio
		 */
		PessoaIntegracaoDTO pessoaIntegracaoDTO = capesInt.obterPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		PessoaIntegracaoDTO pessoaIntegracaoPJDTO = capesInt.obterPessoaJuridicaFormaConstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		PessoaJuridicaIntegracaoDTO pessoaJuridicaIntegracaoDTO =  capesInt.obterPessoaJuridicaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		EnderecoPessoaIntegracaoDTO enderecoPessoaIntegracaoDTO = capesInt.obterEnderecoPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		TelefonePessoaIntegracaoDTO telefonePessoaIntegracaoDTO =  capesInt.obterTelefonePessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		
		/**
		 * DTO que vai segurar os dados para enviar ao relatorio
		 */
		RelDesligamentoAssociadoDTO dto = new RelDesligamentoAssociadoDTO();
		
		/**
		 * Seta o tipo da pessoa
		 */
		dto.setTipoPessoa(pessoaIntegracaoDTO.getCodTipoPessoa());
		
		/**
		 * Seta o numero de conta capital da pessoa
		 */
		ContaCapital contaCapital = contaCapitalServico.obter(dtoEntrada.getIdContaCapital());
		dto.setNumContaCapital(contaCapital.getNumContaCapital());
		
		/**
		 * Seta os valores da pessoa juridica
		 */
		dto.setRazaoSocialEmpresa(pessoaJuridicaIntegracaoDTO.getRazaoSocialEmpresa());
		dto.setDataConstituicao(pessoaIntegracaoPJDTO.getDataConstituicao());
		dto.setCpfCnpj(pessoaJuridicaIntegracaoDTO.getCnpjEmpresa());
		dto.setNumInscricaoEstadual(pessoaJuridicaIntegracaoDTO.getInscricaoEstadual());
		
		if(enderecoPessoaIntegracaoDTO.getIdLocResidencial() != null) {
			LocalizacaoIntegracaoDTO localizacaoIntegracaoResidencialDTO = locInt.consultarLocalidade(enderecoPessoaIntegracaoDTO.getIdLocResidencial());
			dto.setUfInscricaoEstadual(localizacaoIntegracaoResidencialDTO.getUf());
		}
		
		/**
		 * Dados de Endereco da pessoa juridica
		 */
		dto.setDescEnderecoComercial(enderecoPessoaIntegracaoDTO.getDescEnderecoComercial());
		dto.setNumComercial(enderecoPessoaIntegracaoDTO.getNumEnderecoComercial());
		dto.setComplementoComercial(enderecoPessoaIntegracaoDTO.getComplementoEnderecoComercial());
		dto.setBairroComercial(enderecoPessoaIntegracaoDTO.getBairroEnderecoComercial());
		dto.setCepComercial(enderecoPessoaIntegracaoDTO.getCepEnderecoComercial());
		
		/**
		 * if para atribuir localidade comercial (uf e municipio)
		 */
		if(enderecoPessoaIntegracaoDTO.getIdLocComercial() != null) {
			LocalizacaoIntegracaoDTO localizacaoIntegracaoComercialDTO = locInt.consultarLocalidade(enderecoPessoaIntegracaoDTO.getIdLocComercial());
			dto.setUfComercial(localizacaoIntegracaoComercialDTO.getUf());
			dto.setMunicipioComercial(localizacaoIntegracaoComercialDTO.getMunicipio());
		}
		
		/**
		 * Seta o telefone da pessoa juridica
		 */
		dto.setTelefoneComercial(telefonePessoaIntegracaoDTO.getTelefoneEnderecoComercial());
		
		return dto;
	}
	
	/**
	 * @see br.com.sicoob.cca.relatorios.negocio.servicos.RelDesligamentoAssociadoServico#gerarRelatorioDesligamentoEncontroContasLista(br.com.sicoob.cca.relatorios.negocio.dto.RelDesligamentoEncontroContasListaDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object gerarRelatorioDesligamentoEncontroContasLista(RelDesligamentoEncontroContasListaDTO dto) throws BancoobException {
		FiltroContaCapitalDTO filtro = new FiltroContaCapitalDTO.Builder()
				.setIdInstituicao(dto.getIdInstituicao())
				.setNumContaCapitalInicial(dto.getNumContaCapitalInicial())
				.setNumContaCapitalFinal(dto.getNumContaCapitalFinal())
				.build();
		List<DadosDesligamentoDTO> lstDadosDesligamento = contaCapitalServico.obterDadosDesligamento(filtro);
		
		if(lstDadosDesligamento == null || lstDadosDesligamento.size() == 0) {
			throw new NegocioException("MSG_NOVA_RELATORIO_SEM_REGISTROS");
		}
		
		Gson gson = new Gson();
		for (DadosDesligamentoDTO dadosDesligamento : lstDadosDesligamento) {
			DadosDesligamentoEncontroContasDTO dadosEncontroContas = gson.fromJson(dadosDesligamento.getDescDadosDesligamento(), DadosDesligamentoEncontroContasDTO.class);
			dadosEncontroContas.copiarParaRelatorio(dadosDesligamento);
		}
		Map<String, Object> parametros = getParametrosComuns();
		parametros.put("filtroNumContaCapitalInicial", dto.getNumContaCapitalInicial());
		parametros.put("filtroNumContaCapitalFinal", dto.getNumContaCapitalFinal());
		parametros.put("COD_RELATORIO", CodigoRelatorio.COD_REL_DESLIGAMENTO_ENCONTRO_CONTAS_LISTA);
		InstituicaoIntegracaoDTO dtoInstituicaoIntegracaoDelegate = instituicaoIntegracaoServico.obterInstituicaoIntegracao(dto.getIdInstituicao());
		parametros.put("DESC_SIGLA_COOP", dtoInstituicaoIntegracaoDelegate.getNumero() + " - " + dtoInstituicaoIntegracaoDelegate.getDescInstituicao());
		return new RelatorioContaCapitalV2<DadosDesligamentoDTO>(lstDadosDesligamento, 
				"CCA_Relatorio_DesligamentoEncontroContasLista.jasper", parametros);
	}
	
}
