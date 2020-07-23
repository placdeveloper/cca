package br.com.sicoob.sisbr.contacapital.atendimento.servicos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.movimentacao.negocio.delegates.BloqueioContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;
import br.com.sicoob.contacapital.comum.util.ContaCapitalConstantes;
import br.com.sicoob.retaguarda.comum.util.RetaguardaComumUtil;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ParcelamentoCCALegadoDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.fachada.AtendimentoFachada;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.CancelarLancDiaDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.excecao.AtendimentoCadastroException;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.CancelarLancamentosDiaVO;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.vo.DadosCancelarLancamentosDiaVO;

@RemoteService
public class CancelarLancDiaServico extends AtendimentoFachada {

	private CancelarLancDiaDelegate cancelarDelegate = AtendimentoFabricaDelegates.getInstance().criarCancelarLancDiaDelegate();
	private ContaCapitalDelegate ccaDelegate = AtendimentoFabricaDelegates.getInstance().criarContaCapitalDelegate();
	
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		return null;
	}

	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
			RetornoDTO retornoDTO = new RetornoDTO();
			Integer numMatricula = (Integer) dto.getDados().get("numMatricula");
			CancelarLancamentosDiaVO vo = new CancelarLancamentosDiaVO();
			vo.setNumMatricula(numMatricula);
			List<DadosCancelarLancamentosDiaVO> dados = cancelarDelegate
					.obterDadosCancelarLancamentosDiaVO(vo);

			retornoDTO.getDados().put("registros", dados);// dadosCompletos);
			return retornoDTO;
	}
	
	public RetornoDTO estornarLancamentos(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		if (dto.getDados().containsKey("cancelLanc")) {
			DadosCancelarLancamentosDiaVO clDia = (DadosCancelarLancamentosDiaVO) dto
					.getDados().get("cancelLanc");
			CancelarLancamentosDiaVO cvo = new CancelarLancamentosDiaVO();
			cvo.setNumMatricula(clDia.getNumMatricula().intValue());
			List<DadosCancelarLancamentosDiaVO> orsMatric = cancelarDelegate
					.obterDadosCancelarLancamentosDiaVO(cvo);
			if (orsMatric != null && !orsMatric.isEmpty()) {
				List<DadosCancelarLancamentosDiaVO> listaData = new ArrayList<DadosCancelarLancamentosDiaVO>();
				for (DadosCancelarLancamentosDiaVO dcl : orsMatric) {
					if (dcl.getDataHoraInclusao().compareTo(clDia.getDataHoraInclusao()) > 0) {
						listaData.add(dcl);
					}
				}
				if (listaData.isEmpty()) {
					for (DadosCancelarLancamentosDiaVO dcl2 : orsMatric) {
						if ((dcl2.getDataHoraInclusao().compareTo(clDia.getDataHoraInclusao()) == 0)
								&& (dcl2.getNumSeqLanc() > clDia.getNumSeqLanc())) {
							listaData.add(dcl2);
						}
					}
				}
				if (listaData != null && !listaData.isEmpty()) {
					retorno.getDados().put("msgErro", true);
					retorno.getDados().put("erro", "Antes de efetuar esse estorno, deve-se estornar o evento \""
													+ listaData.get(listaData.size() - 1).getEvento()
													+ "\" Seq. = "
													+ listaData.get(listaData.size() - 1).getNumSeqLanc()
													+ ", do Cliente Selecionado.");
					return retorno;
				}
			}
			if (clDia.getNumLoteLanc().intValue() == ContaCapitalConstantes.COD_LOTE_CCA_TRANSFERENCIA) {
				Integer lNumMatriculaAux = Integer.parseInt(RetaguardaComumUtil.somenteNumero(clDia.getDescNumDocumento()));
				CancelarLancamentosDiaVO voAux = new CancelarLancamentosDiaVO();
				voAux.setNumMatricula(lNumMatriculaAux);
				List<DadosCancelarLancamentosDiaVO> tdsEventos = cancelarDelegate.obterDadosCancelarLancamentosDiaVO(voAux);
				if (tdsEventos != null && !tdsEventos.isEmpty()) {
					List<DadosCancelarLancamentosDiaVO> tdsFiltrados = new ArrayList<DadosCancelarLancamentosDiaVO>();
					for (DadosCancelarLancamentosDiaVO filtro : tdsEventos) {
						if (filtro.getDataHoraInclusao().compareTo(clDia.getDataHoraInclusao()) > 0) {
							tdsFiltrados.add(filtro);
						}
					}
					if (tdsFiltrados != null && !tdsFiltrados.isEmpty()) {
						DadosCancelarLancamentosDiaVO dclAux = tdsFiltrados.get(tdsFiltrados.size() - 1);
						Integer iTipoHistAux = null;
						if (dclAux.getIdTipoHistoricoLanc().intValue() == ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSFERENCIA_INTEG) {
							iTipoHistAux = ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSF_BAIXA_INTEG;
						} else if (dclAux.getIdTipoHistoricoLanc().intValue() == ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSF_BAIXA_INTEG) {
							iTipoHistAux = ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSFERENCIA_INTEG;
						} else
							iTipoHistAux = -1;
						if ((clDia.getIdTipoHistoricoLanc().intValue() != iTipoHistAux)
								&& (clDia.getValorLanc().compareTo(dclAux.getValorLanc()) != 0)
								&& (clDia.getNumMatricula() != Long.parseLong(RetaguardaComumUtil.somenteNumero(clDia.getDescNumDocumento())))) {
							String msg = (clDia.getNumMatricula() == dclAux.getNumMatricula()) ? "selecionado.": dclAux.getNumMatricula() + " - " + dclAux.getDescNomePessoa();
							retorno.getDados().put("msgErro", true);
							retorno.getDados().put("erro","Antes de efetuar esse estorno, deve-se estornar o evento \""
														+ dclAux.getEvento() + "\" Seq. = "
														+ dclAux.getNumSeqLanc()
														+ ", do Cliente " + msg);
							return retorno;
						}
					}
				}
			}
			// A destinação do rateio não pode ser estornado
			else if (clDia.getNumLoteLanc().intValue() == ContaCapitalConstantes.COD_LOTE_CCA_DEST_RATEIO) {
				retorno.getDados().put("msgErro", true);
				retorno.getDados().put("erro","O evento \"" + clDia.getEvento() + "\", não pode ser estornado.");
				return retorno;
			}
			// Eventos Via Caixa - o estorno deve ser feito pelo Caixa
			else if (clDia.getNumLoteLanc().intValue() == ContaCapitalConstantes.COD_LOTE_CCA_PARCELAS) {
				retorno.getDados().put("msgErro", true);
				retorno.getDados().put("erro", "Este estorno deve ser feito pelo sistema do caixa.");
				return retorno;
			}
			// Eventos Capital consignado - o estorno não analisado
			else if (clDia.getNumLoteLanc().intValue() == ContaCapitalConstantes.COD_LOTE_CCA_CAPITAL_CONSIGNADO) {
				retorno.getDados().put("msgErro", true);
				retorno.getDados()
						.put("erro", "O evento \"" + clDia.getEvento()
										+ "\", não pode ser estornado. Gerado pelo processamento do Arquivo de Retorno");
				return retorno;
			} else {
				retorno.getDados().put("msgErro", false);
			}
		}
		return retorno;
	}
	
	public RetornoDTO confirmaEstorno(RequisicaoReqDTO dto) throws BancoobException{
		try{
			RetornoDTO retorno = new RetornoDTO();
			Integer numMatricula = (Integer) dto.getDados().get("numMatricula");
			CancelarLancamentosDiaVO vo = new CancelarLancamentosDiaVO();
			vo.setNumMatricula(numMatricula);		
			if (dto.getDados().containsKey("cancelLanc")) {
				DadosCancelarLancamentosDiaVO cl = (DadosCancelarLancamentosDiaVO) dto.getDados().get("cancelLanc");
				if(cl.getEvento().equals("Inclusão de Cooperado")){
					try {
						ccaDelegate.excluirCooperado(cl.getNumMatricula());//OK
					} catch (AtendimentoCadastroException e) {
						if ("msg.erro.excluirCoop	erado".equals(e.getMessage())) {
							throw new AtendimentoCadastroException("O evento Inclusão de Cooperado só pode ser desfeito na mesma data de matrícula da conta capital.");
						}
						throw e;
					}
				}
				else if(cl.getEvento().equals("Reativação")){
					ccaDelegate.estornarSubscReativ(ContaCapitalConstantes.COD_OPERACAO_REATIVACAO, cl); //OK
				}
				else if(cl.getEvento().equals("Subscrição")){
					ccaDelegate.estornarSubscReativ(ContaCapitalConstantes.COD_OPERACAO_NOVA_SUBSCRICAO, cl); //OK
				}
				else if(cl.getEvento().equals("Desligamento")){
					ccaDelegate.estornarDesligDevolucao(ContaCapitalConstantes.COD_OPERACAO_DESLIGAMENTO, cl);//OK
					// apagar pra matricula tal as parcelas em aberto de devolucao via caixa
					ParcelamentoCCALegadoDelegate parcRenDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarParcelamentoCCALegadoDelegate();
					parcRenDelegate.excluirParcelasDevolucaoAbertoViaCaixa(cl.getNumMatricula().intValue());
				}
				else if(cl.getEvento().equals("Baixa da Parcela de Subscrição")){
					ccaDelegate.estornarBaixaInteg(cl); //OK
				}
				else if(cl.getEvento().equals("Devolução de Capital (Cooperado Ativo)")){
					ccaDelegate.estornarDesligDevolucao(ContaCapitalConstantes.COD_OPERACAO_DEVOLUCAO_ATIVO, cl);//OK
				}
				else if(cl.getEvento().equals("Baixa da Parcela de Devolução Via Banco")){
					ccaDelegate.estornarBaixaChqAdm(cl);
				}
				else if(cl.getEvento().equals("Baixa (Cancela) da Parcela de Devolução")){
					ccaDelegate.estornarBaixaDev(cl);
				}
				else if(cl.getEvento().equals("Devolução de Capital (Cooperado Inativo)")){
					ccaDelegate.estornarDesligDevolucao(ContaCapitalConstantes.COD_OPERACAO_DEVOLUCAO_INATIVO, cl);//OK
				}
				else if(cl.getEvento().equals("Integralização Via Banco/Chq. Adm.")){
					ccaDelegate.estornarIntegViaBanco(cl);//OK
				}
				else if(cl.getEvento().equals("Desligamento (Saldo zero)")){
					ccaDelegate.estornarDesligSaldoZero(cl.getNumMatricula());//OK
				}
				else if(cl.getEvento().equals("Implantação de Saldos (Migração)")){
					ccaDelegate.estornarViaMigracao(cl.getNumMatricula(), cl.getValorLanc());
				}
				else if(cl.getEvento().equals("Importação Via Folha")){
					ccaDelegate.estornarImportFolhaReserva(true, cl);
				}
				else if(cl.getEvento().equals("Integralização Via Folha")){
					ccaDelegate.estornarIntegViaFolha(cl);
				}
				else if(cl.getEvento().equals("Importação Via Reserva")){
					ccaDelegate.estornarImportFolhaReserva(false, cl);
				}
				else if(cl.getEvento().equals("Importação Via Conta Corrente")){
					ccaDelegate.estornarEvento(cl);//OK
				}
				else if(cl.getEvento().equals("Importação Via Banco")){
					ccaDelegate.estornarEvento(cl);//OK
				}
				else if(cl.getEvento().equals("Importação Via Rateio")){
					ccaDelegate.estornarEvento(cl);//OK
				}
				else if(cl.getEvento().equals("Estorno de Capital (Cooperado Ativo)")){
					ccaDelegate.estornarEvento(cl);//OK
				}
				else if(cl.getEvento().equals("Estorno de Capital (Cooperado Inativo)")){
					ccaDelegate.estornarEvento(cl);//OK
				}
				else if(cl.getEvento().equals("Inclusão/Reativação (Saldo zero)")){
					try {
						ccaDelegate.estornarInclusaoReativSaldoZero(cl.getNumMatricula());
					} catch (AtendimentoCadastroException e) {
						if (e.getMessage().contains("O cooperado já se encontra desligado")) {
							throw new AtendimentoCadastroException("O Evento Inclusão/Reativação (Saldo zero) não pode ser estornado. O cooperado já se encontra ativo.");
						}
						throw e;
					}
				}
				else {
					ccaDelegate.estornarTrasfCotas(cl);//OK

					SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
					String hoje = sdf.format(new Date());					
					Integer IdBloqueio = 0;
					
					BloqueioContaCapitalDelegate bloqueioContaCapitalDelegate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarBloqueioContaCapitalDelegate();
					
					BloqueioContaCapitalDTO dtoBloqueio = new BloqueioContaCapitalDTO();
					
					if(cl.getIdTipoHistoricoLanc().intValue() == ContaCapitalConstantes.COD_HISTORICO_CCA_TRANSFERENCIA_INTEG){
						dtoBloqueio.setNumContaCapital(cl.getNumMatricula().intValue());
					} else { 
						dtoBloqueio.setNumContaCapital(Integer.parseInt(RetaguardaComumUtil.somenteNumero(cl.getDescNumDocumento())));
					}					
					
					dtoBloqueio.setIdInstituicao(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
					
					List<BloqueioContaCapitalDTO> listaBloqueios = bloqueioContaCapitalDelegate.consultarBloqueios(dtoBloqueio);
					
					for (BloqueioContaCapitalDTO bloqueioContaCapitalDTO : listaBloqueios) {											
						if(bloqueioContaCapitalDTO.getValorBloqueado().compareTo(cl.getValorLanc()) == 0 
								&& sdf.format(bloqueioContaCapitalDTO.getDataBloqueio()).equals(hoje)	
								) {
							IdBloqueio = bloqueioContaCapitalDTO.getIdBloqueio();
							break;
						}						
					}	
					
					if(IdBloqueio > 0) {						
						bloqueioContaCapitalDelegate.excluirBloqueioCapital(IdBloqueio);
					}
				}
			}
			
			List<DadosCancelarLancamentosDiaVO> dados = cancelarDelegate
					.obterDadosCancelarLancamentosDiaVO(vo);
			retorno.getDados().put("registros", dados);
			return retorno;

		} catch (AtendimentoCadastroException e) {
			throw e; 
		} 
	}
}
