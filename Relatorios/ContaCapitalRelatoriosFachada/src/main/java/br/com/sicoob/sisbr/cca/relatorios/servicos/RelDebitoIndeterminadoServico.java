/*
 * 
 */
package br.com.sicoob.sisbr.cca.relatorios.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.cca.relatorios.negocio.delegates.ContaCapitalRelatoriosFabricaDelegates;
import br.com.sicoob.cca.relatorios.negocio.delegates.RelDebitoIndeterminadoDelegate;
import br.com.sicoob.cca.relatorios.negocio.dto.RelDebitoIndeterminadoRenDTO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ConsultaDebitoIndeterminadoRenVO;
import br.com.sicoob.sisbr.cca.relatorios.RelatoriosContaCapital;

/**
 * @author marco.nascimento
 */
@RemoteService
public class RelDebitoIndeterminadoServico extends RelatoriosContaCapital {

	/** O atributo debIndDelegate */
	private RelDebitoIndeterminadoDelegate debIndDelegate = ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelDebitoIndeterminadoDelegate();
	
	/**
	 * Emite relatorio de debito indeterminado
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO emitirRelatorioDebitoIndeterminado(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		List<RelDebitoIndeterminadoRenDTO> lstDTO = converterParaRelDebitoIndeterminadoDTO((List<ConsultaDebitoIndeterminadoRenVO>) reqDTO.getDados().get("lstRelDebitoIndeterminado"));
		
		String filtro = reqDTO.getDados().get("filtro").toString();
		
		if(lstDTO != null && !lstDTO.isEmpty()) {
			ContextoHttp.getInstance().adicionarContexto("RelDebitoIndeterminado", debIndDelegate.gerarRelatorioDebitoIndeterminado(lstDTO, filtro));	
		}
		
		return retorno;
	}
	
	/**
	 * Converte valor da tela para RelDebitoIndeterminadoRenDTO
	 * @param lstEntrada
	 * @return
	 * @throws BancoobException
	 */
	private List<RelDebitoIndeterminadoRenDTO> converterParaRelDebitoIndeterminadoDTO(List<ConsultaDebitoIndeterminadoRenVO> lstEntrada) throws BancoobException {
		
		List<RelDebitoIndeterminadoRenDTO> lstSaida = new ArrayList<RelDebitoIndeterminadoRenDTO>();
		RelDebitoIndeterminadoRenDTO saida = null;
		for(ConsultaDebitoIndeterminadoRenVO entrada : lstEntrada) {
			saida = new RelDebitoIndeterminadoRenDTO();
			saida.setCpfCnpj(entrada.getCpfCnpj());
			saida.setDataPeriodoDeb(entrada.getDataPeriodoDeb());
			saida.setFormaDebito(entrada.getFormaDebito());
			saida.setTipoPessoa(entrada.getTipoPessoa());
			saida.setNome(entrada.getNome());
			saida.setNumContaCapital(entrada.getNumContaCapital());
			saida.setValor(entrada.getValor());
			
			lstSaida.add(saida);
		}
		return lstSaida;
	}
	
}