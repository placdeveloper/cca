package br.com.sicoob.cca.replicacao.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.comum.util.JsonCapital;
import br.com.sicoob.cca.replicacao.negocio.constantes.ReplicacaoContaCapitalConstantes;
import br.com.sicoob.cca.replicacao.negocio.excecao.ContaCapitalReplicacaoException;
import br.com.sicoob.cca.replicacao.negocio.excecao.ReplicacaoContaCapitalDb2Exception;
import br.com.sicoob.cca.replicacao.negocio.excecao.ReplicacaoObjetoReplicacaoInvalidoException;
import br.com.sicoob.cca.replicacao.negocio.excecao.ReplicacaoObjetoReplicacaoNaoEncontradoException;
import br.com.sicoob.cca.replicacao.negocio.servicos.interfaces.ControleReplicacaoServicoLocal;
import br.com.sicoob.cca.replicacao.negocio.servicos.interfaces.ControleReplicacaoServicoRemote;
import br.com.sicoob.cca.replicacao.negocio.servicos.interfaces.EmailReplicacaoServicoLocal;
import br.com.sicoob.cca.replicacao.negocio.servicos.interfaces.ReplicacaoContaCapitalServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.constantes.ReplicacaoLegadoConstantes;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoConfiguracaoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaContaCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaLancamentosCCapitalLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ReplicacaoTabelaParcelamentoCCALegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoAtualizaChaveDb2LegadoException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoAtualizaListaReplicacaoException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoConsultaLegadoException;
import br.com.sicoob.sisbr.cca.legado.negocio.excecao.ReplicacaoConsultaListaReplicacaoException;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ReplicacaoContaCapitalLegadoServicoLocal;
import br.com.sicoob.tipos.DateTime;

/**
 * ControleReplicacaoServicoEJB
 */
@Stateless
@Local(ControleReplicacaoServicoLocal.class)
@Remote(ControleReplicacaoServicoRemote.class)
public class ControleReplicacaoServicoEJB extends ContaCapitalReplicacaoServicoEJB implements ControleReplicacaoServicoLocal, ControleReplicacaoServicoRemote {

	private static final String MSG_ERRO_REPLICACAO_GERAL =  "Erro inesperado na replicacacao.";
	
	@EJB
	private ReplicacaoContaCapitalLegadoServicoLocal replicacaoContaCapitalLegadoServico;

	@EJB
	private ReplicacaoContaCapitalServicoLocal replicacaoContaCapitalServico;

	@EJB
	private EmailReplicacaoServicoLocal emailReplicacaoServico;
	
	/**
	 * Cria o Objeto Json para usar na classe
	 */	
	private JsonCapital jsonCapital = new JsonCapital();
	
	/**
	 * Metodo que inicia a rotina de replicacao das tabelas do conta capital
	 * Consulta lista de replicacao
	 * Chama metodo de replicacao da tabela
	 * Chama metodo de atualizacao de status da tabela
	 * @author Marcos.Balbi
	 * @throws ContaCapitalReplicacaoException 
	 */
	public void iniciarReplicacao() throws ContaCapitalReplicacaoException {
		
		try{			
			List<ReplicacaoContaCapitalLegadoDTO> listReplicacaoContaCapitalLegado = replicacaoContaCapitalLegadoServico.consultarTabelaReplicacao();
			
			if (listReplicacaoContaCapitalLegado != null){			
				for (ReplicacaoContaCapitalLegadoDTO objReplicacao: listReplicacaoContaCapitalLegado){
					ReplicacaoContaCapitalLegadoDTO objSaida = replicarTabelas(objReplicacao);
					gravarTabelaReplicacao(objSaida);				
				}
			}
			
		}catch (ReplicacaoConsultaListaReplicacaoException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalReplicacaoException(e.getMessage(),e);
		}catch (ReplicacaoAtualizaListaReplicacaoException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalReplicacaoException(e.getMessage(),e);			
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalReplicacaoException(e.getMessage(),e);			
		}
	}
	
	/**
	 * Gerencia a tabela que vai ser replicada
	 * Trata todas as excecoes da replicacao
	 * @param objReplicacao
	 */
	private ReplicacaoContaCapitalLegadoDTO replicarTabelas(ReplicacaoContaCapitalLegadoDTO objReplicacao){
		
		String mensagem = "";
		ReplicacaoContaCapitalLegadoDTO objSaida = montarObjetoReplicacaoSucesso(objReplicacao);
		
		try{

			if (!validarObjetoReplicacao(objReplicacao)){
				throw new ReplicacaoObjetoReplicacaoInvalidoException("MSG_ERRO_OBJ_REPLICACAO");
			}
			
			if (objReplicacao.getIdTabelaReplicadaCCA().equals(ReplicacaoContaCapitalConstantes.IDTIPOTABELAREPLICACAO_CONTACAPITAL)){
				objSaida.setDescChaveReplicacaoDB2(replicarTabelaContaCapital(objReplicacao));
			}else if (objReplicacao.getIdTabelaReplicadaCCA().equals(ReplicacaoContaCapitalConstantes.IDTIPOTABELAREPLICACAO_PARCELAMENTOCCA)){
				objSaida.setDescChaveReplicacaoDB2(replicarTabelaParcelamentoCCA(objReplicacao));								
			}else if (objReplicacao.getIdTabelaReplicadaCCA().equals(ReplicacaoContaCapitalConstantes.IDTIPOTABELAREPLICACAO_LANCAMENTOSCCAPITAL)){
				objSaida.setDescChaveReplicacaoDB2(replicarTabelaLancamentosCCapital(objReplicacao));
			}
			
		}catch (ReplicacaoConsultaLegadoException e) {
			this.getLogger().erro(e, e.getMessage());
			objSaida = montarObjetoReplicacaoErro(objReplicacao, e.getMessage());
		}catch (ReplicacaoObjetoReplicacaoInvalidoException e) {
			mensagem = e.getMessage() + " - IdReplicacao: " + objReplicacao.getIdReplicacaoCCA().toString();
			this.getLogger().erro(e, mensagem);
			objSaida = montarObjetoReplicacaoInvalido(objReplicacao,mensagem);
		}catch (ReplicacaoObjetoReplicacaoNaoEncontradoException e) {
			mensagem = e.getMessage() + " - IdReplicacao: " + objReplicacao.getIdReplicacaoCCA().toString();
			this.getLogger().erro(e, mensagem);
			objSaida = montarObjetoReplicacaoNaoEncontrado(objReplicacao,mensagem);
		}catch (ReplicacaoContaCapitalDb2Exception e) {
			this.getLogger().erro(e, e.getMessage());						
			objSaida = montarObjetoReplicacaoErro(objReplicacao,e.getMessage());
		}catch (ReplicacaoAtualizaChaveDb2LegadoException e){
			this.getLogger().erro(e, e.getMessage());						
			objSaida = montarObjetoReplicacaoErro(objReplicacao,e.getMessage());			
		}catch (BancoobException e) {
			mensagem = MSG_ERRO_REPLICACAO_GERAL + " - " + e.getMessage();			
			this.getLogger().erro(e, mensagem);						
			objSaida = montarObjetoReplicacaoErro(objReplicacao,mensagem);	
		}
		
		return 	objSaida;		
	}
	
	/**
	 * Replica dos dados da conta capital do legado para o db2
	 * 1 - Valida se o objeto informado e consistente 
	 * 2 - Realiza Insert, Update ou Delete na base do db2
	 * @param objReplicacao
	 * @throws BancoobException
	 */
	private Integer replicarTabelaContaCapital(ReplicacaoContaCapitalLegadoDTO objReplicacao) throws BancoobException{
		
		Integer retorno = null;
		ReplicacaoTabelaContaCapitalLegadoDTO dtoContaCapital = jsonCapital.converterJSon(objReplicacao.getDescChaveReplicacaoSQL(), ReplicacaoTabelaContaCapitalLegadoDTO.class);
		dtoContaCapital.setNumCooperativa(objReplicacao.getNumCooperativa());
		dtoContaCapital.setIdInstituicao(objReplicacao.getIdInstituicao());
		
		if (objReplicacao.getCodAcao().equals(ReplicacaoContaCapitalConstantes.TIPO_ACAO_INSERT)){
			retorno = replicacaoContaCapitalServico.incluirContaCapital(dtoContaCapital);
		}else if (objReplicacao.getCodAcao().equals(ReplicacaoContaCapitalConstantes.TIPO_ACAO_UPDATE)){
			replicacaoContaCapitalServico.alterarContaCapital(dtoContaCapital);
		}else if (objReplicacao.getCodAcao().equals(ReplicacaoContaCapitalConstantes.TIPO_ACAO_DELETE)){
			replicacaoContaCapitalServico.excluirContaCapital(dtoContaCapital);
		}
		
		replicacaoContaCapitalServico.replicarDebitoIndeterminado(dtoContaCapital, objReplicacao.getCodAcao());
		
		return retorno;
	}
	
	/**
	 * Replica dos dados da tabela de lancamentos do legado para o db2
	 * 1 - Valida se o objeto informado e consistente 
	 * 2 - Realiza Insert, Update ou Delete na base do db2
	 * @param objReplicacao
	 * @throws BancoobException
	 */	
	private Long replicarTabelaParcelamentoCCA(ReplicacaoContaCapitalLegadoDTO objReplicacao) throws BancoobException{
		
		ReplicacaoTabelaParcelamentoCCALegadoDTO dtoParcelamentoCCA = jsonCapital.converterJSon(objReplicacao.getDescChaveReplicacaoSQL(), ReplicacaoTabelaParcelamentoCCALegadoDTO.class);
		dtoParcelamentoCCA.setNumCooperativa(objReplicacao.getNumCooperativa());
		dtoParcelamentoCCA.setIdInstituicao(objReplicacao.getIdInstituicao());			
		
		if (objReplicacao.getCodAcao().equals(ReplicacaoContaCapitalConstantes.TIPO_ACAO_INSERT)){
			return replicacaoContaCapitalServico.incluirParcelamentoContaCapital(dtoParcelamentoCCA);
		}else if (objReplicacao.getCodAcao().equals(ReplicacaoContaCapitalConstantes.TIPO_ACAO_UPDATE)){
			replicacaoContaCapitalServico.alterarParcelamentoContaCapital(dtoParcelamentoCCA);
		}else if (objReplicacao.getCodAcao().equals(ReplicacaoContaCapitalConstantes.TIPO_ACAO_DELETE)){
			replicacaoContaCapitalServico.excluirParcelamentoContaCapital(dtoParcelamentoCCA);
		}
		
		return null;
	}
	
	/**
	 * Replica dos dados da tabela de lancamentos do legado para o db2
	 * 1 - Valida se o objeto informado e consistente 
	 * 2 - Realiza Insert, Update ou Delete na base do db2
	 * @param objReplicacao
	 * @throws BancoobException
	 */
	private Long replicarTabelaLancamentosCCapital(ReplicacaoContaCapitalLegadoDTO objReplicacao) throws BancoobException{
		
		ReplicacaoTabelaLancamentosCCapitalLegadoDTO dtoLancamentosCCapital = jsonCapital.converterJSon(objReplicacao.getDescChaveReplicacaoSQL(), ReplicacaoTabelaLancamentosCCapitalLegadoDTO.class);
		dtoLancamentosCCapital.setNumCooperativa(objReplicacao.getNumCooperativa());
		dtoLancamentosCCapital.setIdInstituicao(objReplicacao.getIdInstituicao());	
		
		if (objReplicacao.getCodAcao().equals(ReplicacaoContaCapitalConstantes.TIPO_ACAO_INSERT)){
			return replicacaoContaCapitalServico.incluirLancamentoContaCapital(dtoLancamentosCCapital);
		}else if (objReplicacao.getCodAcao().equals(ReplicacaoContaCapitalConstantes.TIPO_ACAO_UPDATE)){
			replicacaoContaCapitalServico.alterarLancamentoContaCapital(dtoLancamentosCCapital);
		}else if (objReplicacao.getCodAcao().equals(ReplicacaoContaCapitalConstantes.TIPO_ACAO_DELETE)){
			replicacaoContaCapitalServico.excluirLancamentoContaCapital(dtoLancamentosCCapital);
		}
		
		return null;		
	}	
	
	/**
	 * Monta o ReplicacaoContaCapitalLegadoDTO com os atributos de quando não encontrado registro para replicacao no legado
	 * @param objReplicacao
	 * @param objRetorno
	 * @return
	 */
	private ReplicacaoContaCapitalLegadoDTO montarObjetoReplicacaoNaoEncontrado(ReplicacaoContaCapitalLegadoDTO objReplicacao,String mensagem){
		objReplicacao.setDataHoraReplicacao(new DateTime(new Date().getTime()));
		objReplicacao.setDescMensagemReplicacao(mensagem);
		objReplicacao.setIdSituacaoReplicacaoCCA(ReplicacaoContaCapitalConstantes.IDSITUACAOREPLICACAO_OBJETO_NAO_ENC_PARA_REPLICACAO);		
		return objReplicacao;
	}

	/**
	 * Monta o ReplicacaoContaCapitalLegadoDTO com os atributos de quando os campos necessarios para replicacao nao foram informados
	 * @param objReplicacao
	 * @return
	 */	
	private ReplicacaoContaCapitalLegadoDTO montarObjetoReplicacaoInvalido(ReplicacaoContaCapitalLegadoDTO objReplicacao,String mensagem){	

		try {
			String[] destinatariosEmail = consultarItemConfiguracao(replicacaoContaCapitalLegadoServico.consultarListaConfiguracaoReplicacao(),ReplicacaoLegadoConstantes.PARAM_LISTA_EMAIL_DEST).getDescConfiguracaoReplicacao().split(",");			
			objReplicacao.setDataHoraReplicacao(new DateTime(new Date().getTime()));
			objReplicacao.setDescMensagemReplicacao(mensagem);
			objReplicacao.setIdSituacaoReplicacaoCCA(ReplicacaoContaCapitalConstantes.IDSITUACAOREPLICACAO_OBJETO_INVALIDO_PARA_REPLICACAO);
			
			emailReplicacaoServico.enviar(destinatariosEmail,ReplicacaoContaCapitalConstantes.MSG_ASSUNTO_ERRO, mensagem);
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
		}
		
		return objReplicacao;
	}
	
	/**
	 * Monta o ReplicacaoContaCapitalLegadoDTO com os atributos de quando a replicacao foi um sucesso
	 * @param objReplicacao
	 */
	private ReplicacaoContaCapitalLegadoDTO montarObjetoReplicacaoSucesso(ReplicacaoContaCapitalLegadoDTO objReplicacao){
		objReplicacao.setDataHoraReplicacao(new DateTime(new Date().getTime()));
		objReplicacao.setDescMensagemReplicacao("");
		objReplicacao.setIdSituacaoReplicacaoCCA(ReplicacaoContaCapitalConstantes.IDSITUACAOREPLICACAO_REPLICACAO_COM_SUCESSO);
		return objReplicacao;
	}
	
	/**
	 * Monta o ReplicacaoContaCapitalLegadoDTO com os atributos de quando a replicacao nao ocorreu por erro
	 * @param objReplicacao
	 */
	private ReplicacaoContaCapitalLegadoDTO montarObjetoReplicacaoErro(ReplicacaoContaCapitalLegadoDTO objReplicacao,String mensagemErro) {

		try{
			String[] destinatariosEmail = consultarItemConfiguracao(replicacaoContaCapitalLegadoServico.consultarListaConfiguracaoReplicacao(),ReplicacaoLegadoConstantes.PARAM_LISTA_EMAIL_DEST).getDescConfiguracaoReplicacao().split(",");			
			objReplicacao.setDataHoraReplicacao(new DateTime(new Date().getTime()));
			objReplicacao.setIdSituacaoReplicacaoCCA(ReplicacaoContaCapitalConstantes.IDSITUACAOREPLICACAO_ERRO_NA_REPLICACAO);		
			
			if (mensagemErro != null){
				if (mensagemErro.length() > ReplicacaoContaCapitalConstantes.TAM_MAX_MENSAGEM_ERRO.intValue()){
					objReplicacao.setDescMensagemReplicacao(mensagemErro.substring(ReplicacaoContaCapitalConstantes.TAM_MIN_MENSAGEM_ERRO.intValue(), ReplicacaoContaCapitalConstantes.TAM_MAX_MENSAGEM_ERRO.intValue()));
				}else{
					objReplicacao.setDescMensagemReplicacao(mensagemErro);
				}
			}
			
			emailReplicacaoServico.enviar(destinatariosEmail,ReplicacaoContaCapitalConstantes.MSG_ASSUNTO_ERRO, mensagemErro);			
		}catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());		
		}
		
		return objReplicacao;	
		
	}
	
	/**
	 * Realiza a gravacao na tabela de replicacao chamando o metodo do Integracao Legado
	 * Usada para atualizar o status da replicação
	 * Qualquer falha na gravacao da tabela de replicacao, deve interromper a replicacao imediatamente (replicacao nao pode ficar sem o controle)
	 * @param objReplicacao
	 */	
	private void gravarTabelaReplicacao(ReplicacaoContaCapitalLegadoDTO objReplicacao) throws BancoobException{
		replicacaoContaCapitalLegadoServico.alterarTabelaReplicacao(objReplicacao);
	}
	
	/**
	 * Valida o preenchimento correto do objeto de replicacao
	 * @author Marcos.Balbi
	 * @param obj
	 * @return
	 */
	private Boolean validarObjetoReplicacao(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException{
		
		if (!validarObjetoReplicacaoNulo(obj)){
			return false;
		}

		if (!validarObjetoReplicacaoCodAcao(obj)){
			return false;
		}
		
		if (!validarChaveReplicacaoSql(obj)){
			return false;
		}
		
		if (!validarObjetoJaReplicado(obj)){
			return false;
		}
		
		return true;
	}
	
	/**
	 * Verifica o preenchimentos dos campos obrigatorios para replicacao
	 * @param obj
	 * @return
	 */
	private Boolean validarObjetoReplicacaoNulo(ReplicacaoContaCapitalLegadoDTO obj) throws BancoobException{
		
		if (obj.getIdReplicacaoCCA() == null || obj.getIdSituacaoReplicacaoCCA() == null || obj.getIdTabelaReplicadaCCA() == null){
			return false;
		}
		
		if (obj.getNumCooperativa() == null || obj.getIdInstituicao() == null){	
			return false;
		}
		
		if (obj.getDescChaveReplicacaoSQL() == null || obj.getDescChaveReplicacaoSQL().equals("") ){
			return false;
		}			
		
		return true;
	}

	/**
	 * Valida o código de ação
	 * @param obj
	 * @return
	 */
	private Boolean validarObjetoReplicacaoCodAcao(ReplicacaoContaCapitalLegadoDTO obj)throws BancoobException{

		if (obj.getCodAcao() == null || obj.getCodAcao().equals("") ){
			return false;
		}		
		
		if (obj.getCodAcao().length()!=ReplicacaoContaCapitalConstantes.TAM_MIN_COD_ACAO){
			return false;
		}
		
		if (!obj.getCodAcao().matches("^(I|U|D)$")){
			return false;
		}
		
		return true;
	}	
	
	
	/**
	 * Valida a chave de replicacao sql, no que tange a quantidade de campos necessarios para o seu tipo
	 * @param obj
	 * @return
	 */
	private Boolean validarChaveReplicacaoSql(ReplicacaoContaCapitalLegadoDTO obj)throws BancoobException{
		Boolean saida = true;
				
		if(obj.getIdTabelaReplicadaCCA().equals(ReplicacaoContaCapitalConstantes.IDTIPOTABELAREPLICACAO_CONTACAPITAL)) 
		{
			saida = validarChaveReplicacaoContaCapital(jsonCapital.converterJSon(obj.getDescChaveReplicacaoSQL(), ReplicacaoTabelaContaCapitalLegadoDTO.class));			
		}else if(obj.getIdTabelaReplicadaCCA().equals(ReplicacaoContaCapitalConstantes.IDTIPOTABELAREPLICACAO_PARCELAMENTOCCA)){
			saida = validarChaveReplicacaoParcelamentoContaCapital(jsonCapital.converterJSon(obj.getDescChaveReplicacaoSQL(), ReplicacaoTabelaParcelamentoCCALegadoDTO.class));				
		}else if(obj.getIdTabelaReplicadaCCA().equals(ReplicacaoContaCapitalConstantes.IDTIPOTABELAREPLICACAO_LANCAMENTOSCCAPITAL)){
			saida = validarChaveReplicacaoLancamentosCCapital(jsonCapital.converterJSon(obj.getDescChaveReplicacaoSQL(), ReplicacaoTabelaLancamentosCCapitalLegadoDTO.class));				
		}

		return saida;
	}	

	/**
	 * Valida o objeto conta capital para replicacao(NumMatricula)
	 * @param dto
	 * @return
	 */
	private Boolean validarChaveReplicacaoContaCapital(ReplicacaoTabelaContaCapitalLegadoDTO objContaCapital)throws BancoobException{
		return (objContaCapital.getNumMatricula() == null? false: true);
	}

	/**
	 * Valida o objeto parcelamentocca para replicacao(NumMatricula, NumParcelamento, NumParcela, CodTipoParcelamento)
	 * @param dto
	 * @return
	 */
	private Boolean validarChaveReplicacaoParcelamentoContaCapital(ReplicacaoTabelaParcelamentoCCALegadoDTO objParcelamentoCCA)throws BancoobException{
		if (objParcelamentoCCA.getNumMatricula() == null || objParcelamentoCCA.getNumParcelamento() == null || objParcelamentoCCA.getNumParcela() == null || objParcelamentoCCA.getCodTipoParcelamento() == null){
			return false;
		}		
		return true;
	}	
	
	/**
	 * Valida o objeto lancamentosccapital para replicacao(DataLote, NumLoteLanc, NumSeqLanc)
	 * @param dto
	 * @return
	 */
	private Boolean validarChaveReplicacaoLancamentosCCapital(ReplicacaoTabelaLancamentosCCapitalLegadoDTO objLancamentosCCapital)throws BancoobException{

		if (objLancamentosCCapital.getDataLote() == null || objLancamentosCCapital.getNumLoteLanc() == null || objLancamentosCCapital.getNumSeqLanc() == null){
			return false;
		}		
		return true;
	}	
		
	
	/**
	 * valida se a replicacao ja ocorreu para aquela linha
	 * somente nos casos de insert
	 * @param dtoContaCapital
	 * @return
	 */
	private Boolean validarObjetoJaReplicado(ReplicacaoContaCapitalLegadoDTO obj)throws BancoobException{
		if (obj.getCodAcao().equals(ReplicacaoContaCapitalConstantes.TIPO_ACAO_INSERT) && (obj.getDescChaveReplicacaoDB2() != null && !obj.getDescChaveReplicacaoDB2().equals(""))){
			return false;
		}
		return true;
	}
		
	/**
	 * Consulta o item de configuração desejado na lista 
	 */
	private ReplicacaoConfiguracaoLegadoDTO consultarItemConfiguracao(List<ReplicacaoConfiguracaoLegadoDTO> listConfig, Integer parametro) throws BancoobException{
		ReplicacaoConfiguracaoLegadoDTO dto = null;
		for (ReplicacaoConfiguracaoLegadoDTO item :listConfig){
			if (item.getIdConfiguracaoReplicacaoCCA().equals(parametro)){
				dto = item;
				break;
			}
		}
		return dto;
	}

}
