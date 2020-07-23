package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.PropostaSubscricaoServicoLocal;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.PropostaSubscricao;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.dto.RelFichaPropostaMatriculaDTO;
import br.com.sicoob.cca.relatorios.negocio.excecao.ContaCapitalRelatoriosException;
import br.com.sicoob.cca.relatorios.negocio.excecao.ContaCapitalRelatoriosNegocioException;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelFichaPropostaMatriculaServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelFichaPropostaMatriculaServicoRemote;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.BemPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.EnderecoPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.FonteRendaPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LocalizacaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaFisicaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaJuridicaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ReferenciaPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.RelacionamentoPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.TelefonePessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoCapesNegocioException;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.LocalizacaoIntegracaoServicoLocal;

/**
 * EJB contendo servicos relacionados a RelFichaPropostaMatricula.
 */
@Stateless
@Local(RelFichaPropostaMatriculaServicoLocal.class)
@Remote(RelFichaPropostaMatriculaServicoRemote.class) 
public class RelFichaPropostaMatriculaServicoEJB extends ContaCapitalRelatoriosServicoEJB implements RelFichaPropostaMatriculaServicoLocal, RelFichaPropostaMatriculaServicoRemote {

	@EJB
	private CapesIntegracaoServicoLocal capesInt;
	
	@EJB
	private LocalizacaoIntegracaoServicoLocal locInt;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instInt;

	@EJB
	private ContaCapitalServicoLocal ccaServico;
	
	@EJB
	private PropostaSubscricaoServicoLocal propostaServico;
	
	private static final short COD_PESSOA_FISICA = 0;
	private static final short COD_PESSOA_JURIDICA = 1;
	private static final String MSG_NAO_ESPECIFICADA = "OUTROS DECLARANTES NÃO ESPECIFICADOS NOS GRUPOS ANTERIORES";
	private static final String MSG_CURTA_NAO_ESPECIFICADA = "Não especificada";
	private static final Integer TAMANHO_MAX_LISTA_REP_LEGAL = 16;
	
	/**
	 * Método que recebe um dto de entrada. Esse DTO será usado para segurar os valores que serão
	 * atribuidos ao relatório de Ficha Proposta Matricula.
	 * 
	 * O retorno desse método é o objeto de relatório.
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object gerarRelatorioFichaPropostaMatricula(RelFichaPropostaMatriculaDTO dtoEntrada) throws BancoobException {
		try {
			RelFichaPropostaMatriculaDTO dtoRelatorio =  consultarFichaPropostaMatricula(dtoEntrada);
			List<RelFichaPropostaMatriculaDTO> lista = new ArrayList<RelFichaPropostaMatriculaDTO>();
			lista.add(dtoRelatorio);
			String nomeRelatorio = "";
			if(dtoRelatorio.getTipoPessoa() == COD_PESSOA_FISICA){
				nomeRelatorio = "CCA_Relatorio_FichaPropostaMatricula.jasper";
			}
			else if(dtoRelatorio.getTipoPessoa() == COD_PESSOA_JURIDICA){
				nomeRelatorio = "CCA_Relatorio_FichaPropostaMatriculaPJ.jasper";
			}
			Map<String, Object> parametros = getParametrosComuns();
			
			parametros.put("listaRepresentantesLegais", getRepresentantesLegais(dtoEntrada));
			
			return new RelatorioContaCapitalV2<RelFichaPropostaMatriculaDTO>(lista, nomeRelatorio, parametros);
			
		} catch (IntegracaoCapesNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalRelatoriosNegocioException(e.getMessage());	
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalRelatoriosException("MSG_RELATORIO_ERRO",e);
		}
	}	
	
	/**
	 * Método resopnsável de trazer os Representantes Legais que serão atribuidos ao relatório de PJ por parâmetro
	 * @param dtoEntrada
	 * @return
	 * @throws BancoobException
	 */
	public List<RelacionamentoPessoaIntegracaoDTO> getRepresentantesLegais(RelFichaPropostaMatriculaDTO dtoEntrada) throws BancoobException {

		List<RelacionamentoPessoaIntegracaoDTO> lstRepLegalDTO = capesInt.obterRepresentantesLegaisPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		List<RelacionamentoPessoaIntegracaoDTO> lstRetorno = new ArrayList<RelacionamentoPessoaIntegracaoDTO>();
		
		for (RelacionamentoPessoaIntegracaoDTO rel : lstRepLegalDTO) {
			if(rel.getNomeRepresentanteLegal() != null
			&& rel.getCpfRepresentanteLegal() != null
			&& rel.getTipoRepresentanteLegal() != null
			&& lstRetorno.size() < TAMANHO_MAX_LISTA_REP_LEGAL){
				lstRetorno.add(rel);
			}
		}
		
		return lstRetorno;
	}
	
	/**
	 * Método responsável para pesquisar os valores do Capes e atribuir ao dtoEntrada
	 * que será usado para gerar o relatório
	 * 
	 * @param dtoEntrada
	 * @return
	 * @throws BancoobException
	 */
	private RelFichaPropostaMatriculaDTO consultarFichaPropostaMatricula(RelFichaPropostaMatriculaDTO dtoEntrada) throws BancoobException{
		
		RelFichaPropostaMatriculaDTO dto = new RelFichaPropostaMatriculaDTO();
		PessoaIntegracaoDTO pessoaIntegracaoDTO = capesInt.obterPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
	
		/**
		 * caso a pessoa seja uma pessoa fisica, busca os dados de pessoa fisica
		 */
		if(pessoaIntegracaoDTO.getCodTipoPessoa() == COD_PESSOA_FISICA){
			dto = atribuirDadosPessoaFisica(dtoEntrada);
		}
		
		/**
		 * caso a pessoa seja uma pessoa juridica, busca os dados de pessoa juridica
		 */
		if(pessoaIntegracaoDTO.getCodTipoPessoa() == COD_PESSOA_JURIDICA){
			dto = atribuirDadosPessoaJuridica(dtoEntrada);
		}
		return dto;
	}
	
	/**
	 * Metodo responsavel de atribuir os dados de pessoa fisica para o dto de relatorio de ficha proposta matricula
	 * @param dtoEntrada
	 * @return
	 * @throws BancoobException
	 */
	private RelFichaPropostaMatriculaDTO atribuirDadosPessoaFisica(RelFichaPropostaMatriculaDTO dtoEntrada) throws BancoobException {
		
		/**
		 * DTOs que vão trazer os valores do capes para atribuir ao relatório da pessoa
		 */
		PessoaIntegracaoDTO pessoaIntegracaoDTO = capesInt.obterPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		PessoaFisicaIntegracaoDTO pessoaFisicaIntegracaoDTO = capesInt.obterPessoaFisicaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		EnderecoPessoaIntegracaoDTO enderecoPessoaIntegracaoDTO = capesInt.obterEnderecoPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		TelefonePessoaIntegracaoDTO telefonePessoaIntegracaoDTO =  capesInt.obterTelefonePessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		List<RelacionamentoPessoaIntegracaoDTO> relacionamentoConjugePessoaIntegracaoDTO = capesInt.obterConjugePessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		List<RelacionamentoPessoaIntegracaoDTO> relacionamentoResponsavelLegalPessoaIntegracaoDTO = capesInt.obterResponsavelLegalPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		ReferenciaPessoaIntegracaoDTO referenciaPessoaIntegracaoDTO = capesInt.obterReferenciaPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		FonteRendaPessoaIntegracaoDTO fonteRendaPessoaIntegracaoDTO = capesInt.obterFonteRendaPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		BemPessoaIntegracaoDTO bemPessoaIntegracaoDTO = capesInt.obterBemPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		
		/**
		 * DTO que vai segurar todos os valores que vão para o relatório
		 */
		RelFichaPropostaMatriculaDTO dto = new RelFichaPropostaMatriculaDTO();
		
		/**
		 * Seta o tipo da pessoa
		 */
		dto.setTipoPessoa(pessoaIntegracaoDTO.getCodTipoPessoa());

		/**
		 * Dados de pessoa física
		 */
		dto.setNomeCompleto(pessoaIntegracaoDTO.getNomeCompleto());
		dto.setCpf(pessoaIntegracaoDTO.getCpfCnpj());
		dto.setDescDocumento(pessoaFisicaIntegracaoDTO.getDescDocumento());
		dto.setNumDocumento(pessoaFisicaIntegracaoDTO.getNumDocumento());
		dto.setEmissaoDocumento(pessoaFisicaIntegracaoDTO.getEmissaoDocumento());
		dto.setOrgaoDocumento(pessoaFisicaIntegracaoDTO.getOrgaoDocumento());
		dto.setUfDocumento(pessoaFisicaIntegracaoDTO.getUfDocumento());
		
		/**
		 * lógica para atribuir a filiação da pessoa
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
		 * lógica para atribuir o naturalidade (localidade) da pessoa
		 */
		if(isIdLocalidadeValido(pessoaFisicaIntegracaoDTO.getIdNaturalidade())) {
			LocalizacaoIntegracaoDTO localizacaoIntegracaoDTO = locInt.consultarLocalidade(pessoaFisicaIntegracaoDTO.getIdNaturalidade());
			dto.setNaturalidade(localizacaoIntegracaoDTO.getMunicipio() + " - " + localizacaoIntegracaoDTO.getUf());
		} else {
			dto.setNaturalidade(pessoaFisicaIntegracaoDTO.getNaturalidade());
		}
		
		dto.setNascimento(pessoaFisicaIntegracaoDTO.getNascimento());
		dto.setDescSexo(pessoaFisicaIntegracaoDTO.getDescSexo());
		
		/**
		 *lógica para atribuir "não especificada" caso a profissão da pessoa não esteja definida
		 */
		if(pessoaFisicaIntegracaoDTO.getDescProfissao() != null && pessoaFisicaIntegracaoDTO.getDescProfissao().toString().equals(MSG_NAO_ESPECIFICADA)){
			dto.setDescProfissao(MSG_CURTA_NAO_ESPECIFICADA);
		} else{
			dto.setDescProfissao(pessoaFisicaIntegracaoDTO.getDescProfissao());
		}
		
		dto.setEstadoCivil(pessoaFisicaIntegracaoDTO.getEstadoCivil());
		
		/**
		 * if para atribuir nome e cpf do conjuge
		 */
		if(relacionamentoConjugePessoaIntegracaoDTO != null && CollectionUtils.isNotEmpty(relacionamentoConjugePessoaIntegracaoDTO)) {
			dto.setNomeCompanheiro(relacionamentoConjugePessoaIntegracaoDTO.get(0).getNomeConjuge());
			dto.setCpfCompanheiro(relacionamentoConjugePessoaIntegracaoDTO.get(0).getCpfConjuge());
		}
		/**
		 * if para atribuir nome e cpf do responsável legal
		 */
		if(relacionamentoResponsavelLegalPessoaIntegracaoDTO != null && CollectionUtils.isNotEmpty(relacionamentoResponsavelLegalPessoaIntegracaoDTO)) {
			dto.setNomeResponsavelLegal(relacionamentoResponsavelLegalPessoaIntegracaoDTO.get(0).getNomeResponsavelLegal());
			dto.setCpfResponsavelLegal(relacionamentoResponsavelLegalPessoaIntegracaoDTO.get(0).getCpfResponsavelLegal());
		}
		
		dto.setRendaMensal(fonteRendaPessoaIntegracaoDTO.getRendaMensal());
		
		/**
		 * if para atribuir valor total de bens
		 */
		if(bemPessoaIntegracaoDTO.getValorTotalBens() != null){
			dto.setValorPatrimonio(bemPessoaIntegracaoDTO.getValorTotalBens());
		}
		
		/**
		 * DADOS DE ENDERECO
		 */
		
		/**
		 * Dados de Endereço Residencial
		 */
		dto.setDescEnderecoResidencial(enderecoPessoaIntegracaoDTO.getDescEnderecoResidencial());
		dto.setNumEnderecoResidencial(enderecoPessoaIntegracaoDTO.getNumEnderecoResidencial());
		dto.setComplementoEnderecoResidencial(enderecoPessoaIntegracaoDTO.getComplementoEnderecoResidencial());
		dto.setBairroEnderecoResidencial(enderecoPessoaIntegracaoDTO.getBairroEnderecoResidencial());
		dto.setCepEnderecoResidencial(enderecoPessoaIntegracaoDTO.getCepEnderecoResidencial());
		
		/**
		 * if para atribuir localidade residencial (uf e municipio)
		 */
		if(isIdLocalidadeValido(enderecoPessoaIntegracaoDTO.getIdLocResidencial())) {
			LocalizacaoIntegracaoDTO localizacaoIntegracaoResidencialDTO = locInt.consultarLocalidade(enderecoPessoaIntegracaoDTO.getIdLocResidencial());
			dto.setUfEnderecoResidencial(localizacaoIntegracaoResidencialDTO.getUf());
			dto.setMunicipioEnderecoResidencial(localizacaoIntegracaoResidencialDTO.getMunicipio());
		}
		
		/**
		 * Telefone Residencial
		 */
		dto.setTelefoneEnderecoResidencial(telefonePessoaIntegracaoDTO.getTelefoneEnderecoResidencial());
		dto.setRamalEnderecoResidencial(telefonePessoaIntegracaoDTO.getRamalEnderecoResidencial());
		
		/**
		 * Dados de Endereço Comercial
		 */
		dto.setDescEnderecoComercial(enderecoPessoaIntegracaoDTO.getDescEnderecoComercial());
		dto.setNumEnderecoComercial(enderecoPessoaIntegracaoDTO.getNumEnderecoComercial());
		dto.setComplementoEnderecoComercial(enderecoPessoaIntegracaoDTO.getComplementoEnderecoComercial());
		dto.setBairroEnderecoComercial(enderecoPessoaIntegracaoDTO.getBairroEnderecoComercial());
		dto.setCepEnderecoComercial(enderecoPessoaIntegracaoDTO.getCepEnderecoComercial());
		
		/**
		 * if para atribuir localidade comercial (uf e municipio)
		 */
		if(isIdLocalidadeValido(enderecoPessoaIntegracaoDTO.getIdLocComercial())) {
			LocalizacaoIntegracaoDTO localizacaoIntegracaoComercialDTO = locInt.consultarLocalidade(enderecoPessoaIntegracaoDTO.getIdLocComercial());
			dto.setUfEnderecoComercial(localizacaoIntegracaoComercialDTO.getUf());
			dto.setMunicipioEnderecoComercial(localizacaoIntegracaoComercialDTO.getMunicipio());
		}
		
		/**
		 * Telefone Comercial
		 */
		dto.setTelefoneEnderecoComercial(telefonePessoaIntegracaoDTO.getTelefoneEnderecoComercial());
		dto.setRamalEnderecoComercial(telefonePessoaIntegracaoDTO.getRamalEnderecoComercial());
		
		/**
		 * Referencias
		 */
		dto.setTipoReferenciaUm(referenciaPessoaIntegracaoDTO.getTipoReferenciaUm());
		dto.setDescReferenciaUm(referenciaPessoaIntegracaoDTO.getDescReferenciaUm());
		dto.setNumReferenciaUm(referenciaPessoaIntegracaoDTO.getNumReferenciaUm());
		
		dto.setTipoReferenciaDois(referenciaPessoaIntegracaoDTO.getTipoReferenciaDois());
		dto.setDescReferenciaDois(referenciaPessoaIntegracaoDTO.getDescReferenciaDois());
		dto.setNumReferenciaDois(referenciaPessoaIntegracaoDTO.getNumReferenciaDois());
		
		atribuirDadosCooperativa(dto, pessoaIntegracaoDTO);
		atribuirDadosContaCapital(dto, dtoEntrada);
		
		/**
		 * Atribui os dados da proposta
		 */
		atribuirDadosProposta(dto, dtoEntrada);
		
		return dto;
	}
	
	private boolean isIdLocalidadeValido(Integer idLocalidade) {
		return idLocalidade != null && idLocalidade.intValue() > 0;
	}
	
	/**
	 * Metodo responsavel de atribuir os dados de pessoa juridica para o dto de relatorio de ficha proposta matricula
	 * @param dtoEntrada
	 * @return
	 * @throws BancoobException
	 */
	private RelFichaPropostaMatriculaDTO atribuirDadosPessoaJuridica(RelFichaPropostaMatriculaDTO dtoEntrada) throws BancoobException {
		
		/**
		 * DTOs que vão trazer os valores do capes para atribuir ao relatório da pessoa
		 */
		PessoaIntegracaoDTO pessoaIntegracaoDTO = capesInt.obterPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		PessoaIntegracaoDTO pessoaIntegracaoPJDTO = capesInt.obterPessoaJuridicaFormaConstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		EnderecoPessoaIntegracaoDTO enderecoPessoaIntegracaoDTO = capesInt.obterEnderecoPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		TelefonePessoaIntegracaoDTO telefonePessoaIntegracaoDTO =  capesInt.obterTelefonePessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		FonteRendaPessoaIntegracaoDTO fonteRendaPessoaIntegracaoDTO = capesInt.obterFonteRendaPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		PessoaJuridicaIntegracaoDTO pessoaJuridicaIntegracaoDTO =  capesInt.obterPessoaJuridicaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		ReferenciaPessoaIntegracaoDTO referenciaPessoaIntegracaoDTO = capesInt.obterReferenciaPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		BemPessoaIntegracaoDTO bemPessoaIntegracaoDTO = capesInt.obterBemPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		
		/**
		 * DTO que vai segurar todos os valores que vão para o relatório
		 */
		RelFichaPropostaMatriculaDTO dto = new RelFichaPropostaMatriculaDTO();
		
		/**
		 * Seta o tipo da pessoa
		 */
		dto.setTipoPessoa(pessoaIntegracaoDTO.getCodTipoPessoa());
		
		/**
		 * Dados de pessoa jurídica
		 */
		dto.setRazaoSocialEmpresa(pessoaJuridicaIntegracaoDTO.getRazaoSocialEmpresa());
		dto.setCnpjEmpresa(pessoaJuridicaIntegracaoDTO.getCnpjEmpresa());
		dto.setNomeFantasia(pessoaIntegracaoDTO.getNomeApelido());
		dto.setInscricaoEstadual(pessoaJuridicaIntegracaoDTO.getInscricaoEstadual());
		dto.setCodCNAE(pessoaIntegracaoDTO.getCodCNAE());
		dto.setNaturezaJuridica(pessoaIntegracaoPJDTO.getNaturezaJuridica());
		dto.setNumRegistroOrgaoCompetente(pessoaIntegracaoPJDTO.getNumRegistroOrgaoCompetente());
		dto.setDataRegistroOrgaoCompetente(pessoaIntegracaoPJDTO.getDataRegistroOrgaoCompetente());
		dto.setCapitalSocial(pessoaJuridicaIntegracaoDTO.getCapitalSocial());
		
		/**
		 * Dados endereco comercial
		 */
		dto.setDescEnderecoComercial(enderecoPessoaIntegracaoDTO.getDescEnderecoComercial());
		dto.setNumEnderecoComercial(enderecoPessoaIntegracaoDTO.getNumEnderecoComercial());
		dto.setComplementoEnderecoComercial(enderecoPessoaIntegracaoDTO.getComplementoEnderecoComercial());
		dto.setBairroEnderecoComercial(enderecoPessoaIntegracaoDTO.getBairroEnderecoComercial());
		dto.setCepEnderecoComercial(enderecoPessoaIntegracaoDTO.getCepEnderecoComercial());
		
		/**
		 * if para atribuir localidade comercial (uf e municipio)
		 */
		if(isIdLocalidadeValido(enderecoPessoaIntegracaoDTO.getIdLocComercial())) {
			LocalizacaoIntegracaoDTO localizacaoIntegracaoComercialDTO = locInt.consultarLocalidade(enderecoPessoaIntegracaoDTO.getIdLocComercial());
			dto.setUfEnderecoComercial(localizacaoIntegracaoComercialDTO.getUf());
			dto.setMunicipioEnderecoComercial(localizacaoIntegracaoComercialDTO.getMunicipio());
		}
		
		/**
		 * Telefone comercial
		 */
		dto.setTelefoneEnderecoComercial(telefonePessoaIntegracaoDTO.getTelefoneEnderecoComercial());
		dto.setRamalEnderecoComercial(telefonePessoaIntegracaoDTO.getRamalEnderecoComercial());
		
		dto.setFaturamentoMensal(fonteRendaPessoaIntegracaoDTO.getRendaMensal());
		
		/**
		 * if para atribuir valor total de bens
		 */
		if(bemPessoaIntegracaoDTO.getValorTotalBens() != null){
			dto.setValorPatrimonio(bemPessoaIntegracaoDTO.getValorTotalBens());
		}
		
		/**
		 * Dados da referencia
		 */
		dto.setTipoReferenciaUm(referenciaPessoaIntegracaoDTO.getTipoReferenciaUm());
		dto.setDescReferenciaUm(referenciaPessoaIntegracaoDTO.getDescReferenciaUm());
		dto.setNumReferenciaUm(referenciaPessoaIntegracaoDTO.getNumReferenciaUm());
		
		dto.setTipoReferenciaDois(referenciaPessoaIntegracaoDTO.getTipoReferenciaDois());
		dto.setDescReferenciaDois(referenciaPessoaIntegracaoDTO.getDescReferenciaDois());
		dto.setNumReferenciaDois(referenciaPessoaIntegracaoDTO.getNumReferenciaDois());
		
		atribuirDadosCooperativa(dto, pessoaIntegracaoDTO);
		atribuirDadosContaCapital(dto, dtoEntrada);
		
		/**
		 * Atribui os dados da proposta
		 */
		atribuirDadosProposta(dto, dtoEntrada);
		
		return dto;
	}
	
	/**
	 * Metodo responsavel de atribuir os dados da proposta para o dto de relatorio de ficha proposta matricula
	 * @param dto
	 * @param dtoEntrada
	 * @return
	 * @throws BancoobException
	 */
	private RelFichaPropostaMatriculaDTO atribuirDadosProposta(RelFichaPropostaMatriculaDTO dto, RelFichaPropostaMatriculaDTO dtoEntrada) throws BancoobException{
		
		PropostaSubscricao proposta = propostaServico.obter(dtoEntrada.getIdContaCapital());
		
		if(proposta != null) {
			
			dto.setCapitalSubscrever(proposta.getValorSubsProposta());
			dto.setIntegralizacaoVista(proposta.getValorIntegProposta());
			dto.setValorTotalParcela(new BigDecimal(proposta.getQtdParcelaProposta()).multiply(proposta.getValorParcelaProposta()));
			dto.setQtdParcelasMensais(proposta.getQtdParcelaProposta());
			dto.setValorParcelas(proposta.getValorParcelaProposta());
			dto.setDiaDebito(proposta.getDiaDebitoProposta());
			dto.setFormaDebito(proposta.getTipoIntegralizacao().getDescricao());
		}
		
		return dto;
	}

	private void atribuirDadosContaCapital(RelFichaPropostaMatriculaDTO dto, RelFichaPropostaMatriculaDTO dtoEntrada) throws BancoobException {
		ContaCapital cca = ccaServico.obter(dtoEntrada.getIdContaCapital());
		if (cca == null) {
			throw new ContaCapitalRelatoriosNegocioException("Conta Capital não encontrada.");
		}
		dto.setNumContaCapital(cca.getNumContaCapital());
	}

	private void atribuirDadosCooperativa(RelFichaPropostaMatriculaDTO dto, PessoaIntegracaoDTO pessoaIntegracaoDTO) throws BancoobException {
		String idUnidadeInt = pessoaIntegracaoDTO.getIdUnidadeInst() == null ? null : pessoaIntegracaoDTO.getIdUnidadeInst().toString();
		InstituicaoIntegracaoDTO instituicaoIntegracaoDTO = instInt.obterInformacoesInstituicaoSCI(
				pessoaIntegracaoDTO.getIdInstituicao(), idUnidadeInt);
		dto.setDescCidadeUf(instituicaoIntegracaoDTO.getNomeCidadeUf());
		dto.setNumCooperativa(instituicaoIntegracaoDTO.getNumero());
		dto.setRazaoSocial(instituicaoIntegracaoDTO.getDescInstituicao());
		dto.setSigla(instituicaoIntegracaoDTO.getSiglaInstituicao());
		dto.setCnpj(instituicaoIntegracaoDTO.getNumCNPJ());
		dto.setPostoDeAtendimento(idUnidadeInt);
	}
	
}