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

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.PropostaSubscricaoServicoLocal;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.PropostaSubscricao;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapital;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.dto.RelAprovacaoQuadroPendenciaDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelAprovacaoQuadroPendenciaServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelAprovacaoQuadroPendenciaServicoRemote;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.AnotacaoPessoaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;


/**
 * Classe responsável pela construção do relatório de quadro pendencia. 
 * Seus métodos são relativos a procura atribuição dos valores que vão para o relatório.
 */
@Stateless
@Local(RelAprovacaoQuadroPendenciaServicoLocal.class)
@Remote(RelAprovacaoQuadroPendenciaServicoRemote.class) 
public class RelAprovacaoQuadroPendenciaServicoEJB extends ContaCapitalRelatoriosServicoEJB implements RelAprovacaoQuadroPendenciaServicoLocal, RelAprovacaoQuadroPendenciaServicoRemote {
	
	@EJB
	private CapesIntegracaoServicoLocal capesInt;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@EJB
	private ContaCapitalServicoLocal ccaServico;
	
	@EJB
	private PropostaSubscricaoServicoLocal propostaSubscricaoServico;
	
	/**
	 * @see br.com.sicoob.cca.relatorios.negocio.servicos.RelAprovacaoQuadroPendenciaServico#gerarRelatorioAprovacaoQuadroPendencia(br.com.sicoob.cca.relatorios.negocio.dto.RelAprovacaoQuadroPendenciaDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object gerarRelatorioAprovacaoQuadroPendencia(RelAprovacaoQuadroPendenciaDTO dtoEntrada) throws BancoobException {
		RelAprovacaoQuadroPendenciaDTO dtoRelatorio = buscarDadosRelatorio(dtoEntrada);
		List<RelAprovacaoQuadroPendenciaDTO> lista = new ArrayList<RelAprovacaoQuadroPendenciaDTO>(0);
		lista.add(dtoRelatorio);
		
		String nomeRelatorio = "CCA_Relatorio_AprovacaoQuadroPendencia.jasper";
		Map<String, Object> parametros = getParametrosComuns();
		parametros.put("COD_RELATORIO", CodigoRelatorio.COD_REL_APROVACAO_QUADRO_PENDENCIA);
		
		InstituicaoIntegracaoDTO dtoInstituicaoIntegracaoDelegate = instituicaoIntegracaoServico.obterInstituicaoIntegracao(dtoEntrada.getIdInstituicao());
		
		parametros.put("DESC_SIGLA_COOP", dtoInstituicaoIntegracaoDelegate.getNumero() + " - " + dtoInstituicaoIntegracaoDelegate.getDescInstituicao());
		
		RelatorioContaCapital<RelAprovacaoQuadroPendenciaDTO> relatorio = new RelatorioContaCapital<RelAprovacaoQuadroPendenciaDTO>(lista, nomeRelatorio, parametros);
		return relatorio.gerarSincronamente();
	}
	
	private RelAprovacaoQuadroPendenciaDTO buscarDadosRelatorio(RelAprovacaoQuadroPendenciaDTO dto) throws BancoobException {
		
		ContaCapital cca = ccaServico.obter(dto.getIdContaCapital());
		PropostaSubscricao dadosProposta = propostaSubscricaoServico.obter(dto.getIdContaCapital());
		PessoaIntegracaoDTO pessoaIntegracaoDTO = capesInt.obterPessoaInstituicao(dto.getIdPessoa(), dto.getIdInstituicao());
		
		if(dadosProposta != null ){
			dto.setData(dadosProposta.getDataHoraAtualizacao());
			dto.setNumContaCapital(cca.getNumContaCapital());
			
			dto.setCapitalSubscrever(dadosProposta.getValorSubsProposta());
			dto.setIntegralizacaoVista(dadosProposta.getValorIntegProposta());
			dto.setQtdParcelasMensais(dadosProposta.getQtdParcelaProposta());
			dto.setValorParcelas(dadosProposta.getValorParcelaProposta());
			dto.setValorTotalParcela(new BigDecimal(dadosProposta.getQtdParcelaProposta()).multiply(dadosProposta.getValorParcelaProposta()));
			dto.setDiaDebito(dadosProposta.getDiaDebitoProposta());
			dto.setFormaDebito(dadosProposta.getTipoIntegralizacao().getDescricao());
			
			dto.setNome(pessoaIntegracaoDTO.getNomePessoa());
			dto.setCpfCnpj(pessoaIntegracaoDTO.getCpfCnpj());
			
			List<AnotacaoPessoaDTO> aVigentes = capesInt.obterAnotacoesVigentes(cca.getIdPessoa(), cca.getIdInstituicao());
			List<AnotacaoPessoaDTO> aBaixadas = capesInt.obterAnotacoesBaixadas(cca.getIdPessoa(), cca.getIdInstituicao());
			
			dto.setListaBaixadas(aBaixadas);
			dto.setListaVigentes(aVigentes);
		}
		
		return dto;
	}
	
}
