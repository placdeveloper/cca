package br.com.sicoob.sisbr.cca.api.negocio.servicos.ejb;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoContaCapital;
import br.com.sicoob.sisbr.cca.api.negocio.dto.ContaCapitalDebIndeterminadoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.excecao.DebIndeterminadoContaCapitalNegocioException;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.interfaces.DebIndeterminadoContaCapitalServicoLocal;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.interfaces.DebIndeterminadoContaCapitalServicoRemote;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.ContaCorrenteIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.entidades.ContaCapitalLegado;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ContaCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.persistencia.ContaCapitalIntegracaoLegadoDataSource;

/**
 * Responsavel por controlar debito indeterminado de conta capital
 * @author Marco.Nascimento
 */
@Stateless
@Local(DebIndeterminadoContaCapitalServicoLocal.class)
@Remote(DebIndeterminadoContaCapitalServicoRemote.class)
public class DebIndeterminadoContaCapitalServicoEJB extends APIContaCapitalServicoEJB implements DebIndeterminadoContaCapitalServicoLocal, DebIndeterminadoContaCapitalServicoRemote {

	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@EJB
	private CapesIntegracaoServicoLocal capesIntegracaoServico;
	
	@EJB
	private ContaCorrenteIntegracaoServicoLocal ccoServico;
	
	@EJB
	private ContaCapitalLegadoServicoLocal ccaLegadoServico;
	
	@EJB
	private ProdutoLegadoServicoLocal prodLegadoServico;

	/**
	 * @see br.com.sicoob.sisbr.cca.api.negocio.servicos.DebIndeterminadoContaCapitalServico#incluirDebitoIndeterminado(br.com.sicoob.sisbr.cca.api.negocio.dto.ContaCapitalDebIndeterminadoDTO)
	 */
	public Boolean incluirDebitoIndeterminado(ContaCapitalDebIndeterminadoDTO dto) throws BancoobException {
		final Integer um = 1;
		try {
			getLogger().info("cca.incluirDebitoIndeterminado");
			getLogger().info(dto.toString());
			validarEntradaDebIndeterminado(dto);
			
			ContaCapitalIntegracaoLegadoDataSource.definirNumeroCooperativa(instituicaoIntegracaoServico.obterNumeroCooperativa(dto.getIdInstituicao()));
			
			validarInclusaoDebIndeterminado(dto);
			
			ContaCapitalLegado ccaLegado = ccaLegadoServico.obter(dto.getNumMatricula());
			ccaLegado.setBolDebIndeterminado(Boolean.TRUE);
			ccaLegado.setValorDeb(dto.getValorDebito());
			ccaLegado.setCodFormaDeb(ContaCapitalConstantes.COD_FORMA_DEB_VIA_CONTA);
			Date dataAtualProduto = prodLegadoServico.obterDataAtualProduto(ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, dto.getIdInstituicao());
			ccaLegado.setDataVencimentoDeb(new DateTimeDB(alterarDataPeloDiaDebito(dataAtualProduto, dto.getDiaDebitoMensal().intValue())));
			ccaLegado.setPeriodoProxDeb(um);
			ccaLegado.setTipoPeriodoDeb(ContaCapitalConstantes.COD_TIPO_PERIODO_DEB_MENSAL);
			ccaLegado.setNumContaCorrente(dto.getNumContaCorrente());
			getLogger().info("cca.incluirDebitoIndeterminado | ccaLegado:" + ccaLegado.toString());
			ccaLegadoServico.alterar(ccaLegado);
			
			return true;
		} catch (BancoobException be) {
			getLogger().erro(be, be.getMessage());
			lancarAPIContaCapitalNegocioException(be.getMessage());
		} catch (EJBException e) {
			getLogger().erro(e, e.getMessage());
			lancarAPIContaCapitalNegocioException(e.getMessage());
		}
		return false;
	}
	
	private Long alterarDataPeloDiaDebito(Date dataAtualProduto, int diaDebitoMensal) {
		getLogger().info("cca.alterarDataPeloDiaDebito");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataAtualProduto);
		int ultimoDiaDoMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int diaDebito = diaDebitoMensal;
		if (diaDebitoMensal > ultimoDiaDoMes) {
			diaDebito = ultimoDiaDoMes;
		} 
		calendar.set(Calendar.DAY_OF_MONTH, diaDebito);
		return calendar.getTime().getTime();
	}

	/**
	 * Verifica se a instituicao é valida e se a pessoa em questao faz parte dela
	 * @param idInstituicao
	 * @param idPessoa
	 * @throws BancoobException 
	 * @throws  
	 */
	private void validarInclusaoDebIndeterminado(ContaCapitalDebIndeterminadoDTO dto) throws BancoobException {
		InstituicaoIntegracaoDTO iiDTO = instituicaoIntegracaoServico.obterInstituicaoIntegracao(dto.getIdInstituicao());
		if(iiDTO == null || iiDTO.getIdInstituicao() == null) {
			throw new DebIndeterminadoContaCapitalNegocioException("MSG_INST_NAO_ENC");
		}
		
		ContaCapitalLegado ccaLegado = ccaLegadoServico.obter(dto.getNumMatricula());
		if(ccaLegado == null || ccaLegado.getNumMatricula() == null) {
			throw new DebIndeterminadoContaCapitalNegocioException("MSG_DEB_IND_CCA_NAO_ENC");
		}
		
		if(!ccaLegado.getCodSituacao().equals(EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ATIVO.getCodigo())) {
			throw new DebIndeterminadoContaCapitalNegocioException("MSG_DEB_IND_CCA_INATIVA");
		}
		
		if(ccaLegado.getBolDebIndeterminado() != null && ccaLegado.getBolDebIndeterminado()) {
			throw new DebIndeterminadoContaCapitalNegocioException("MSG_DEB_IND_JA_POSSUI_DEBITO");
		}
		
		validarContaCorrentePessoa(ccaLegado.getNumCliente(), dto.getIdInstituicao(), dto.getNumContaCorrente());
	}
	
	/**
	 * Valida se a conta corrente percence a pessoa em questao e se esta bloqueada/encerrada
	 * @param idPessoaLegado
	 * @param idInstituicao
	 * @param numContaCorrente
	 * @throws BancoobException
	 */
	private void validarContaCorrentePessoa(Integer idPessoaLegado, Integer idInstituicao, Long numContaCorrente) throws BancoobException {
		ContaCorrenteIntegracaoDTO ccoDto = new ContaCorrenteIntegracaoDTO();
		ccoDto.setIdPessoa(capesIntegracaoServico.obterIdPessoaPorIdPessoaLegado(idPessoaLegado, idInstituicao));		
		ccoDto.setIdInstituicao(idInstituicao);
		ccoDto.setNumContaCorrente(numContaCorrente);
		
		if(ccoServico.verificarContaCorrenteBloqueadaEncerrada(ccoDto)) {
			throw new DebIndeterminadoContaCapitalNegocioException("MSG_CCO_BLOQ_ENC");
		}
	}
	
	/**
	 * Valida dados de entrada para inclusao de debito de indeterminado
	 * @param dto
	 * @throws BancoobException
	 */
	private void validarEntradaDebIndeterminado(ContaCapitalDebIndeterminadoDTO dto) throws BancoobException {
		final int limiteDiaDebitoMensal = 31;
		
		if(dto == null) {
			throw new DebIndeterminadoContaCapitalNegocioException("MSG_OBR_DEB_IND_DTO");
		}
		
		if(dto.getIdInstituicao() == null || dto.getIdInstituicao() <= 0) {
			throw new DebIndeterminadoContaCapitalNegocioException("MSG_OBR_DEB_IND", "Instituição");
		}
		
		if(dto.getDiaDebitoMensal() == null) {
			throw new DebIndeterminadoContaCapitalNegocioException("MSG_OBR_DEB_IND", "Dia do débito mensal");
		}
		
		if(dto.getDiaDebitoMensal() > limiteDiaDebitoMensal || dto.getDiaDebitoMensal() < 1) {
			throw new DebIndeterminadoContaCapitalNegocioException("MSG_DEB_IND_DIA_INVALIDO");
		}
		
		if(dto.getNumContaCorrente() == null || dto.getNumContaCorrente() <= 0) {
			throw new DebIndeterminadoContaCapitalNegocioException("MSG_OBR_DEB_IND", "Conta Corrente");
		}
		
		if(dto.getNumMatricula() == null || dto.getNumMatricula() <= 0) {
			throw new DebIndeterminadoContaCapitalNegocioException("MSG_OBR_DEB_IND", "Número da Conta Capital");
		}
		
		if(dto.getValorDebito() == null) {
			throw new DebIndeterminadoContaCapitalNegocioException("MSG_OBR_DEB_IND", "Valor do débito");
		}
	}
	
}