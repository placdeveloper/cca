/*
 * 
 */
package br.com.sicoob.cca.cadastro.negocio.servicos.ejb;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.excecao.CadastroContaCapitalNegocioException;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.AprovacaoContaCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.AprovacaoContaCapitalServicoRemote;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoCadastroProposta;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.GftIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.GftIntegracaoServicoLocal;

/**
 *	Responsavel por dar andamento no fluxo de cadastro de conta capital
 *
 *  @author Marco.Nascimento
 */
@Stateless
@Local (AprovacaoContaCapitalServicoLocal.class)
@Remote(AprovacaoContaCapitalServicoRemote.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AprovacaoContaCapitalServicoEJB extends ContaCapitalCadastroServicoEJB implements AprovacaoContaCapitalServicoLocal, AprovacaoContaCapitalServicoRemote {
	
	/** O atributo context. */
	@Resource
	private SessionContext context;
	
	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;
	
	@EJB
	private GftIntegracaoServicoLocal gftIntegracaoServico;
	
	/**
	 * @see br.com.sicoob.cca.cadastro.negocio.servicos.AprovacaoContaCapitalServico#aprovar(br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	public void aprovar(ContaCapital contaCapital, Integer idAtividade, Integer idOcorrenciaAtividade, Integer idProcedimento,
			String nomeProcedimento) throws BancoobException {
		try
		{
			validar(contaCapital);

			GftIntegracaoDTO gftIntegracaoDTO = new GftIntegracaoDTO();
			gftIntegracaoDTO.setIdRegistroControlado(contaCapital.getId());
			gftIntegracaoDTO.setIdInstituicaoProcesso(contaCapital.getIdInstituicao());
			gftIntegracaoDTO.setExecutarAtividadeAprovacao(Boolean.TRUE);
			gftIntegracaoDTO.setIdAtividade(idAtividade);
			gftIntegracaoDTO.setIdOcorrenciaAtividade(idOcorrenciaAtividade);
			gftIntegracaoDTO.setIdProcedimentoControle(idProcedimento);
			gftIntegracaoDTO.setNomeProcedimento(nomeProcedimento);
			gftIntegracaoDTO.setJustificativa(contaCapital.getDescObsAprovacao());

			gftIntegracaoServico.executarAtividadeAprovacao(gftIntegracaoDTO);

			if(gftIntegracaoServico.isProcessoCompleto(idAtividade, contaCapital.getId())) {
				SituacaoCadastroProposta sit = new SituacaoCadastroProposta();
				sit.setId(EnumSituacaoCadastroProposta.findByGFT(nomeProcedimento).getCodigo());
				contaCapital.setSituacaoCadastroProposta(sit);
			}

			contaCapitalServico.alterar(contaCapital);
		}
		catch(EJBException e)
		{
			context.setRollbackOnly();
			tratarRuntimeExceptionEJB(e);
		}
		catch (BancoobException e)
		{
			context.setRollbackOnly();
			getLogger().erro(e, e.getMessage());
			throw new CadastroContaCapitalNegocioException(e.getMessage());
		}
	}
	
	private void tratarRuntimeExceptionEJB(EJBException e) throws CadastroContaCapitalNegocioException {

		String[] excecoes = e.getMessage() != null ? e.getMessage().split(":") : new String[] {""};
		getLogger().erro(e, e.getMessage());
		throw new CadastroContaCapitalNegocioException(excecoes[excecoes.length - 1]);
	}
	
	/**
	 * Valida condições para aprovação do cadastro
	 * @param contaCapital
	 * @param idAtividade
	 * @param idSituacaoCadastro
	 * @throws BancoobException
	 */
	private void validar(ContaCapital contaCapital) throws BancoobException {
		if(!contaCapital.getSituacaoCadastroProposta().getId().equals(EnumSituacaoCadastroProposta.COD_AGUARDANDO_APROVACAO.getCodigo())) {
			throw new CadastroContaCapitalNegocioException("MSG_029");
		}
	}
}