/*
 * 
 */
package br.com.sicoob.sisbr.cca.movimentacao.conversor;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.entidades.BancoobEntidade;
import br.com.bancoob.sisbrweb.fachada.vo.ConversorVO;


// TODO: Auto-generated Javadoc
/**
 * A Classe ConversorMovimentacaoContaCapital.
 *
 * @author Antonio.Genaro
 * @param <E> o tipo generico
 * @param <V> o tipo generico
 */
public class ConversorMovimentacaoContaCapital<E extends BancoobEntidade, V extends Object> implements ConversorVO<E, V> {

	/**
	 * O método Vo para entidade.
	 *
	 * @param entidade o valor de entidade
	 * @param vo o valor de vo
	 * @throws BancoobException lança a exceção BancoobException
	 * @see br.com.bancoob.sisbrweb.fachada.vo.ConversorVO#voParaEntidade(br.com.bancoob.negocio.entidades.BancoobEntidade, java.lang.Object)
	 */
	public void voParaEntidade(E entidade, V vo) throws BancoobException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * O método Entidade para vo.
	 *
	 * @param vo o valor de vo
	 * @param entidade o valor de entidade
	 * @throws BancoobException lança a exceção BancoobException
	 * @see br.com.bancoob.sisbrweb.fachada.vo.ConversorVO#entidadeParaVO(java.lang.Object, br.com.bancoob.negocio.entidades.BancoobEntidade)
	 */
	public void entidadeParaVO(V vo, E entidade) throws BancoobException {
		// TODO Auto-generated method stub
		
	}

	/*
	 //Exemplo Conta Corrente
	 public void entidadeParaVO(V vo, E entidade) throws BancoobException {

		ContaCorrente contaCorrente = (ContaCorrente) entidade;
		ContaCorrenteVO valueObj = (ContaCorrenteVO) vo;
		
		valueObj.setBolCobrancaCPMF(contaCorrente.getBolCobrancaCPMF());
		valueObj.setBolEnvioAutomTED(contaCorrente.getBolEnvioAutomTED());
		valueObj.setBolInfCaptacaoExtrato(contaCorrente.getBolInfCaptacaoExtrato());
		valueObj.setBolLimCredTalao(contaCorrente.getBolLimCredTalao());
		valueObj.setBolRestrTalao(contaCorrente.getBolRestrTalao());
		valueObj.setBolTelTalao(contaCorrente.getBolTelTalao());
		valueObj.setBolUtilSaldoAplicacao(contaCorrente.getBolUtilSaldoAplicacao());
		
		valueObj.setCodCategoriaCCO(contaCorrente.getCodCategoriaCCO());
		valueObj.setCodCritCentralizacao(contaCorrente.getCodCritCentralizacao());
		valueObj.setCodDiaDebPctTarifa(contaCorrente.getCodDiaDebPctTarifa());
		valueObj.setCodPeriodicidadeExtrato(contaCorrente.getCodPeriodicidadeExtrato());
		valueObj.setCodSituacaoCCO(contaCorrente.getCodSituacaoCCO());
		
		valueObj.setDataAberturaFormatada(DataUtil.converterDateToString(contaCorrente.getDataHoraAbertura(), "dd/MM/yyyy"));
		
		if(contaCorrente.getDataHoraAbertura() != null){
			valueObj.setDataHoraAbertura(new DateTime(contaCorrente.getDataHoraAbertura().getTime()));
			valueObj.setDataHoraCriacao(new DateTime(contaCorrente.getDataHoraAbertura().getTime()));
		}
		if(contaCorrente.getDataUltimaAtualizacao() != null){
			valueObj.setDataUltimaAtualizacao(new DateTime(contaCorrente.getDataUltimaAtualizacao().getTime()));
		}
		if(contaCorrente.getDataUltimoMov() != null){
			valueObj.setDataUltimoMovimento(new DateTime(contaCorrente.getDataUltimoMov().getTime()));
		}
		
		String descricaoCategoria = "";
		switch (contaCorrente.getCodCategoriaCCO()) {
		case 1:
			descricaoCategoria = EnumCategoria.INDIVIDUAL.getDescCategoria();
			break;
		case 2:
			descricaoCategoria = EnumCategoria.SOLIDARIA.getDescCategoria();
			break;
		case 3:
			descricaoCategoria = EnumCategoria.NAO_SOLIDARIA.getDescCategoria();
			break;
		default:
			break;
		}
		valueObj.setDescricaoCategoria(descricaoCategoria);
		
		valueObj.setDescricaoModalidade(contaCorrente.getModalidadeTipoConta().getModalidade().getDescModalidade());
		
		String descricaoSituacaoConta = "";
		switch (contaCorrente.getCodSituacaoCCO()) {
		case 1:
			descricaoSituacaoConta = EnumSituacaoConta.ATIVA.getDescSituacao();
			break;
		case 2:
			descricaoSituacaoConta = EnumSituacaoConta.INATIVA.getDescSituacao();
			break;
		case 3:
			descricaoSituacaoConta = EnumSituacaoConta.BLOQUEADA.getDescSituacao();
			break;
		case 4:
			descricaoSituacaoConta = EnumSituacaoConta.ENCERRADA.getDescSituacao();
			break;
		default:
			break;
		}
		valueObj.setDescricaoSituacaoConta(descricaoSituacaoConta);
		valueObj.setDescricaoTipoConta(contaCorrente.getModalidadeTipoConta().getTipoConta().getDescTipoConta());
		if (contaCorrente.getModalidadeTipoConta().getTipoConta().getCodTipoPessoa() != null) {
			valueObj.setCodTipoPessoaTipoConta(contaCorrente.getModalidadeTipoConta().getTipoConta().getCodTipoPessoa());
		}
		
		valueObj.setIdContaCorrente(contaCorrente.getIdContaCorrente());
		if (contaCorrente.getGrupoConta() != null) {
			valueObj.setIdGrupoConta(contaCorrente.getGrupoConta().getIdGrupoConta());
		}
		valueObj.setIdInstituicao(contaCorrente.getIdInstituicao());
		valueObj.setIdModalidade(contaCorrente.getModalidadeTipoConta().getModalidade().getIdModalidade());
		valueObj.setIdPacoteTarifa(contaCorrente.getIdPacoteTarifa());
		valueObj.setIdTipoContaCorrente(contaCorrente.getModalidadeTipoConta().getTipoConta().getIdTipoContaCorrente());
		if (contaCorrente.getTipoMovCPMF() != null) {
			valueObj.setIdTipoMovCPMF(contaCorrente.getTipoMovCPMF().getIdTipoMovCpmf());
		}
		valueObj.setNumContaCorrente(contaCorrente.getNumContaCorrente());
		valueObj.setNumDVContaCorrente(contaCorrente.getNumDVContaCorrente());
		
		StringBuilder conta = new StringBuilder();
		conta.append(contaCorrente.getNumContaCorrente()).append(contaCorrente.getNumDVContaCorrente());
		valueObj.setNumeroContaFormatada(conta.toString());
		
		valueObj.setQtdMinAssinaturas(contaCorrente.getQtdMinAssinaturas());
		
		if (contaCorrente.getControleContaCorrente() != null) {
			valueObj.setValorJurosAD(contaCorrente.getControleContaCorrente().getValorJurosAD());
			valueObj.setValorJurosLimCred(contaCorrente.getControleContaCorrente().getValorJurosLimCred());
			valueObj.setValorJurosLimCredAcimaCap(contaCorrente.getControleContaCorrente().getValorJurosLimCredAcimaCap());
			valueObj.setValorJurosLimCredAteCap(contaCorrente.getControleContaCorrente().getValorJurosLimCredAteCap());
			valueObj.setValorRendaAproprLanc(contaCorrente.getControleContaCorrente().getValorRendaApropr());
			valueObj.setValorSaldoAntAD(contaCorrente.getControleContaCorrente().getValorSaldoAntAD());
			valueObj.setValorSaldoAntLimCred(contaCorrente.getControleContaCorrente().getValorSaldoAntLimCred());
			valueObj.setValorSaldoBloqAnt(contaCorrente.getControleContaCorrente().getValorSaldoBloqAnt());
			valueObj.setValorSaldoBloqAtual(contaCorrente.getControleContaCorrente().getValorSaldoBloqAtual());
			valueObj.setValorSaldoDevAcum(contaCorrente.getControleContaCorrente().getValorSaldoDevAcum());
			valueObj.setValorSaldoDiaAnt(contaCorrente.getControleContaCorrente().getValorSaldoDiaAnt());
			valueObj.setValorSaldoDispAnt(contaCorrente.getControleContaCorrente().getValorSaldoDispAnt());
			valueObj.setValorSaldoDispAtual(contaCorrente.getControleContaCorrente().getValorSaldoDispAtual());
			valueObj.setValorSaldoMedPosAcum(contaCorrente.getControleContaCorrente().getValorSaldoMedPosAcum());
			valueObj.setValorSaldoMedRealAcum(contaCorrente.getControleContaCorrente().getValorSaldoMedRealAcum());
			valueObj.setValorUtilAD(contaCorrente.getControleContaCorrente().getValorUtilAD());
			valueObj.setValorUtilLimCred(contaCorrente.getControleContaCorrente().getValorUtilLimCred());
		}
		
		valueObj.setNomeUsuarioResponsavel(contaCorrente.getIdUsuario());
		valueObj.setNomeUsuarioAlteracao(contaCorrente.getIdUsuarioRespAtualizacao());
		
		if(contaCorrente.getIdFontePagadora() != null){
			valueObj.setIdFontePagadora(contaCorrente.getIdFontePagadora());
		}
		
		if (contaCorrente.getAgendamentoEncerramento() != null && contaCorrente.getAgendamentoEncerramento().getCodSituacaoAgendamento() != null) {
			if(contaCorrente.getAgendamentoEncerramento().getCodSituacaoAgendamento().equals(EnumSituacaoAgendamentoEncerramento.AGENDADO.getCodSituacao())){
				valueObj.setBolPossuiAgendamento(true);
			}
		} else {
			valueObj.setBolPossuiAgendamento(false);
		}
		
		if (contaCorrente.getModalidadeTipoConta() != null && contaCorrente.getModalidadeTipoConta().getIdModalidadeTipoConta() != null) {
			valueObj.setIdAssociacaoModalidadeTipoConta(contaCorrente.getModalidadeTipoConta().getIdModalidadeTipoConta());
		}
	}

	public void voParaEntidade(E entidade, V vo) throws BancoobException {
		ContaCorrente contaCorrente = (ContaCorrente) entidade;
		ContaCorrenteVO valueObj = (ContaCorrenteVO) vo;
		List<ParticipanteConta> participantes = new ArrayList<ParticipanteConta>();
		List<ParticipanteConta> participantesBackup = new ArrayList<ParticipanteConta>();
		List<CombAssinatura> combinacoes 	   = new ArrayList<CombAssinatura>();
		List<CombAssinatura> combinacoesBackup = new ArrayList<CombAssinatura>();
		
		if(valueObj.getParticipanteContaVOs() != null){
			for (ParticipanteContaVO participanteContaVO : valueObj.getParticipanteContaVOs()) {
				ParticipanteConta participanteConta = new ParticipanteConta();
				
				if (participanteContaVO.getIdParticipanteConta() != null) {
					participanteConta.setIdParticipanteConta(participanteContaVO.getIdParticipanteConta());
				}
				
				if (participanteContaVO.getBolAtivo() != null) {
					participanteConta.setBolAtivo(participanteContaVO.getBolAtivo());
				}
				
				if (participanteContaVO.getNomeEmbossamento() != null) {
					participanteConta.setNomeEmbossamento(participanteContaVO.getNomeEmbossamento());
				}
				
				participanteConta.setContaCorrente(contaCorrente);
				participanteConta.setIdPessoa(participanteContaVO.getIdPessoa());
				participanteConta.setNumOrdemTitularidade(participanteContaVO.getNumOrdemTitularidade());
				participanteConta.setCodTipoPessoa(participanteContaVO.getCodTipoPessoa().shortValue());
				
				participantes.add(participanteConta);
			}
		}
		
		if(valueObj.getParticipanteContaBackupVOs() != null){
			for (ParticipanteContaVO participanteContaVO : valueObj.getParticipanteContaBackupVOs()) {
				ParticipanteConta participanteConta = new ParticipanteConta();
				
				participanteConta.setIdParticipanteConta(participanteContaVO.getIdParticipanteConta());
				participanteConta.setIdPessoa(participanteContaVO.getIdPessoa());
				participanteConta.setContaCorrente(contaCorrente);
				participanteConta.setNumOrdemTitularidade(participanteContaVO.getNumOrdemTitularidade());
				if (participanteContaVO.getBolAtivo() != null) {
					participanteConta.setBolAtivo(participanteContaVO.getBolAtivo());
				}
				if (participanteContaVO.getNomeEmbossamento() != null) {
					participanteConta.setNomeEmbossamento(participanteContaVO.getNomeEmbossamento());
				}
				participantesBackup.add(participanteConta);
			}
		}
		
		if(valueObj.getCombinacoesContaVOs() != null){
			for (CombAssinaturaVO combAssinaturaVO : valueObj.getCombinacoesContaVOs()) {
				for (CombAssinaturaVO combAssinaturaVOFilha : combAssinaturaVO.getChildren()) {
					CombAssinatura combinacao = new CombAssinatura();
					
					combinacao.setIdPessoa(combAssinaturaVOFilha.getIdPessoa());
					combinacao.setNumCombinacao(combAssinaturaVO.getNumCombinacao());
					combinacao.setContaCorrente(contaCorrente);
					
					if (combAssinaturaVOFilha.getIdCombAssinatura() != null) {
						combinacao.setIdCombAssinatura(combAssinaturaVOFilha.getIdCombAssinatura());
					}
					
					combinacoes.add(combinacao);
				}
			}
		}
		
		if (valueObj.getCombinacoesContaBackupVOs() != null) {
			for (CombAssinaturaVO combAssinaturaVOBackup : valueObj.getCombinacoesContaBackupVOs()) {
				if (combAssinaturaVOBackup.getChildren() != null) {
					for (CombAssinaturaVO combAssinaturaVOBackupFilha : combAssinaturaVOBackup.getChildren()) {
						CombAssinatura combinacao = new CombAssinatura();
						
						combinacao.setIdPessoa(combAssinaturaVOBackupFilha.getIdPessoa());
						combinacao.setNumCombinacao(combAssinaturaVOBackup.getNumCombinacao());
						combinacao.setContaCorrente(contaCorrente);
						
						if (combAssinaturaVOBackupFilha.getIdCombAssinatura() != null) {
							combinacao.setIdCombAssinatura(combAssinaturaVOBackupFilha.getIdCombAssinatura());
						}
					
						combinacoesBackup.add(combinacao);
					}
				}
			}
		}
		
		contaCorrente.setListaParticipanteConta(participantes);
		contaCorrente.setListaParticipanteContaBackup(participantesBackup);
		contaCorrente.setListaCombinacoes(combinacoes);
		contaCorrente.setListaCombinacoesBackup(combinacoesBackup);
		
		InformacoesUsuario informacoesUsuario = InformacoesUsuario.getInstance();
		if(valueObj.getIdContaCorrente() == null){
			contaCorrente.setIdUsuario(informacoesUsuario.getLogin());
		} else {
			contaCorrente.setIdContaCorrente(valueObj.getIdContaCorrente());
			contaCorrente.setIdUsuario(valueObj.getNomeUsuarioResponsavel());
			contaCorrente.setIdUsuarioRespAtualizacao(informacoesUsuario.getLogin());
			contaCorrente.setDataHoraAbertura(DateUtil.dateToDateTimeDB(valueObj.getDataHoraAbertura()));
		}
		
		if(valueObj.getBolCobrancaCPMF() == null){
			contaCorrente.setBolCobrancaCPMF(0);
		}
		
		if(valueObj.getBolEnvioAutomTED() != null){
			contaCorrente.setBolEnvioAutomTED(valueObj.getBolEnvioAutomTED());
		}
		
		contaCorrente.setBolInfCaptacaoExtrato(valueObj.getBolInfCaptacaoExtrato());
		contaCorrente.setBolLimCredTalao(valueObj.getBolLimCredTalao());
		contaCorrente.setBolRestrTalao(valueObj.getBolRestrTalao());
		contaCorrente.setBolTelTalao(valueObj.getBolTelTalao());
		
		if(valueObj.getBolUtilSaldoAplicacao() == null){
			contaCorrente.setBolUtilSaldoAplicacao(0);
		} else {
			contaCorrente.setBolUtilSaldoAplicacao(valueObj.getBolUtilSaldoAplicacao());
		}
		
		contaCorrente.setCodCategoriaCCO(valueObj.getCodCategoriaCCO());
		
		if(valueObj.getCodCritCentralizacao() == null || valueObj.getCodCritCentralizacao() == 0){
			contaCorrente.setCodCritCentralizacao(null);
		} else{
			contaCorrente.setCodCritCentralizacao(valueObj.getCodCritCentralizacao());
		}
		
		if(valueObj.getCodDiaDebPctTarifa() == null || valueObj.getCodDiaDebPctTarifa() == 0){
			contaCorrente.setCodDiaDebPctTarifa(null);
		} else{
			contaCorrente.setCodDiaDebPctTarifa(valueObj.getCodDiaDebPctTarifa());
		}
				
		if(valueObj.getCodPeriodicidadeExtrato() == null || valueObj.getCodPeriodicidadeExtrato() == 0){
			contaCorrente.setCodPeriodicidadeExtrato(null);
		} else{
			contaCorrente.setCodPeriodicidadeExtrato(valueObj.getCodPeriodicidadeExtrato());
		}
		
		contaCorrente.setCodSituacaoCCO(valueObj.getCodSituacaoCCO());
		
		contaCorrente.setDataUltimoMov(DateUtil.dateToDateTimeDB(valueObj.getDataUltimoMovimento()));
		
		if (valueObj.getIdGrupoConta() != null && !valueObj.getIdGrupoConta().equals(0L)) {
			contaCorrente.setGrupoConta(new GrupoConta());
			contaCorrente.getGrupoConta().setIdGrupoConta(valueObj.getIdGrupoConta());
		}
		
		contaCorrente.setIdInstituicao(valueObj.getIdInstituicao());
		
		contaCorrente.setModalidadeTipoConta(new AssociacaoModalidadeTipoConta());
		contaCorrente.getModalidadeTipoConta().setModalidade(new Modalidade());
		contaCorrente.getModalidadeTipoConta().getModalidade().setIdModalidade(valueObj.getIdModalidade());
		
		if (valueObj.getIdAssociacaoModalidadeTipoConta() != null && !valueObj.getIdAssociacaoModalidadeTipoConta().equals(NumberUtils.LONG_ZERO)) {
			contaCorrente.getModalidadeTipoConta().setIdModalidadeTipoConta(valueObj.getIdAssociacaoModalidadeTipoConta());
		}
		
		if(valueObj.getIdPacoteTarifa() == null || valueObj.getIdPacoteTarifa() == 0){
			contaCorrente.setIdPacoteTarifa(null);
		} else {
			contaCorrente.setIdPacoteTarifa(valueObj.getIdPacoteTarifa());
		}
		
		contaCorrente.getModalidadeTipoConta().setTipoConta(new TipoConta());
		
		if(valueObj.getIdTipoContaCorrente() != null){
			contaCorrente.getModalidadeTipoConta().getTipoConta().setIdTipoContaCorrente(valueObj.getIdTipoContaCorrente());
			if(valueObj.getCodTipoPessoaTipoConta() != null){
				contaCorrente.getModalidadeTipoConta().getTipoConta().setCodTipoPessoa(valueObj.getCodTipoPessoaTipoConta());
			}
		}
		
		contaCorrente.setNumContaCorrente(valueObj.getNumContaCorrente());
		contaCorrente.setNumDVContaCorrente(valueObj.getNumDVContaCorrente());
		
		contaCorrente.setQtdMinAssinaturas(valueObj.getQtdMinAssinaturas());
		
		contaCorrente.setControleContaCorrente(new ControleContaCorrente());
		contaCorrente.getControleContaCorrente().setIdContaCorrente(contaCorrente.getIdContaCorrente());
		contaCorrente.getControleContaCorrente().setValorJurosAD(valueObj.getValorJurosAD());
		contaCorrente.getControleContaCorrente().setValorJurosLimCred(valueObj.getValorJurosLimCred());
		contaCorrente.getControleContaCorrente().setValorJurosLimCredAcimaCap(valueObj.getValorJurosLimCredAcimaCap());
		contaCorrente.getControleContaCorrente().setValorJurosLimCredAteCap(valueObj.getValorJurosLimCredAteCap());
		contaCorrente.getControleContaCorrente().setValorRendaApropr(valueObj.getValorRendaAproprLanc());
		contaCorrente.getControleContaCorrente().setValorSaldoAntAD(valueObj.getValorSaldoAntAD());
		contaCorrente.getControleContaCorrente().setValorSaldoAntLimCred(valueObj.getValorSaldoAntLimCred());
		contaCorrente.getControleContaCorrente().setValorSaldoBloqAnt(valueObj.getValorSaldoBloqAnt());
		contaCorrente.getControleContaCorrente().setValorSaldoBloqAtual(valueObj.getValorSaldoBloqAtual());
		contaCorrente.getControleContaCorrente().setValorSaldoBloqJudicialAnt(valueObj.getValorSaldoBloqJudicialAnt());
		contaCorrente.getControleContaCorrente().setValorSaldoBloqJudicialAtual(valueObj.getValorSaldoBloqJudicialAtual());
		contaCorrente.getControleContaCorrente().setValorSaldoDevAcum(valueObj.getValorSaldoDevAcum());
		contaCorrente.getControleContaCorrente().setValorSaldoDiaAnt(valueObj.getValorSaldoDiaAnt());
		contaCorrente.getControleContaCorrente().setValorSaldoDispAnt(valueObj.getValorSaldoDispAnt());
		contaCorrente.getControleContaCorrente().setValorSaldoDispAtual(valueObj.getValorSaldoDispAtual());
		contaCorrente.getControleContaCorrente().setValorSaldoMedPosAcum(valueObj.getValorSaldoMedPosAcum());
		contaCorrente.getControleContaCorrente().setValorSaldoMedRealAcum(valueObj.getValorSaldoMedRealAcum());
		contaCorrente.getControleContaCorrente().setValorUtilAD(valueObj.getValorUtilAD());
		contaCorrente.getControleContaCorrente().setValorUtilLimCred(valueObj.getValorUtilLimCred());
		
		//Listagem de Cheques
		contaCorrente.setListaEstoqueCheque(new ArrayList<EstoqueCheque>());
		if(valueObj.getListaEstoqueCheque() != null){
			for(EstoqueChequeVO estoqueChequeVO: valueObj.getListaEstoqueCheque()){
				EstoqueCheque estoqueCheque = new EstoqueCheque();
				if(!estoqueChequeVO.getIdEstoqueCheque().equals(Long.valueOf(0))){
					estoqueCheque.setIdEstoqueCheque(estoqueChequeVO.getIdEstoqueCheque());
				}
				estoqueCheque.setContaCorrente(contaCorrente);
				estoqueCheque.setTipoCheque(new TipoCheque());
				estoqueCheque.getTipoCheque().setIdTipoCheque(estoqueChequeVO.getIdTipoCheque());
				estoqueCheque.setQtdCooperativa(estoqueChequeVO.getQtdCooperativa());
				estoqueCheque.setQtdDomicilio(estoqueChequeVO.getQtdDomicilio());
				contaCorrente.getListaEstoqueCheque().add(estoqueCheque);
			}
		}
		
		if(valueObj.getIdFontePagadora() == null || valueObj.getIdFontePagadora().equals(Long.valueOf(0))){
			contaCorrente.setIdFontePagadora(null);
		} else {
			contaCorrente.setIdFontePagadora(valueObj.getIdFontePagadora());
		}
		
		if(valueObj.getContaFavorecidaVO() != null){
			ConversorContaFavorecida<ContaFavorecida, ContaFavorecidaVO> conversorContaFavorecida 
												= new ConversorContaFavorecida<ContaFavorecida, ContaFavorecidaVO>();
			ContaFavorecida contaFavorecida 	= new ContaFavorecida();
			ContaFavorecidaVO contaFavorecidaVO = valueObj.getContaFavorecidaVO();
			
			if (contaCorrente.getIdContaCorrente() != null && !contaCorrente.getIdContaCorrente().equals(NumberUtils.LONG_ZERO)) {
				contaFavorecidaVO.setIdContaCorrente(contaCorrente.getIdContaCorrente());
			}
			
			conversorContaFavorecida.voParaEntidade(contaFavorecida, contaFavorecidaVO);
			
			contaCorrente.setContaFavorecida(contaFavorecida);
		}
	}
		 */
}
