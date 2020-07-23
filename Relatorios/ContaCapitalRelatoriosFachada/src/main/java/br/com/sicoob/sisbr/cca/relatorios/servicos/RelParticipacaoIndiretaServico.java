/*
 * 
 */
package br.com.sicoob.sisbr.cca.relatorios.servicos;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.cca.relatorios.negocio.delegates.ContaCapitalRelatoriosFabricaDelegates;
import br.com.sicoob.cca.relatorios.negocio.delegates.RelParticipacaoIndiretaSingularDelegate;
import br.com.sicoob.cca.relatorios.negocio.dto.FiltroParticipacaoIndiretaSingularDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.InstituicaoIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.CentralCooperativaDTO;
import br.com.sicoob.sisbr.cca.relatorios.RelatoriosContaCapital;

/**
 * Classe responsavel por emitir relatorio de participação indireta
 * 
 * @author Sron.Cruz
 */
@RemoteService
public class RelParticipacaoIndiretaServico extends RelatoriosContaCapital {
	
	private InstituicaoIntegracaoDelegate delegateIntegracao = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate();

	private RelParticipacaoIndiretaSingularDelegate relatorioParticipacaoIndiretaDelegate = ContaCapitalRelatoriosFabricaDelegates.getInstance().criarRelParticipacaoIndiretaSingularDelegate();
	
	private static final String NUM_CENTRAL = "numCentral";
	private static final String NUM_COOPERATIVA = "numCooperativa";
	private static final String DESC_CENTRAL = "descCentral";
	private static final String DESC_COOPERATIVA = "descCooperativa";
	private static final String MES = "mes";
	private static final String ANO = "ano";

	/**
	 * obterDefinicoes
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		//Tratamento dos componentes de Central e Cooperativa
		CentralCooperativaDTO dtoCoop = obterCentralCooperativa();
		retorno.getDados().put(NUM_CENTRAL, dtoCoop.getNumCentral());
		retorno.getDados().put(NUM_COOPERATIVA, dtoCoop.getNumCooperativa());
		retorno.getDados().put(DESC_CENTRAL, dtoCoop.getDescCentral());
		retorno.getDados().put(DESC_COOPERATIVA, dtoCoop.getDescCooperativa());

		return retorno;
	}
	
	// Recebe Mapa do Flex
	/**
	 * Emite relatorio
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		FiltroParticipacaoIndiretaSingularDTO filtro = montarFiltroParticipacaoIndiretaSingularDTO(dto);
		if (!validarCentralporSingular(filtro.getIdInstituicaoCentral(),filtro.getIdInstituicaoSingular())){
			return retorno;
		}
		
		ContextoHttp.getInstance().adicionarContexto(
				"RelParticipacaoIndireta", 
				relatorioParticipacaoIndiretaDelegate.emitirRelParticipacaoIndireta(montarFiltroParticipacaoIndiretaSingularDTO(dto))
		);	
		
		return retorno;
	}
	
	/**
	 * Transforma o DTO recebido do flex no filtro do EJB do relatorio
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	private FiltroParticipacaoIndiretaSingularDTO montarFiltroParticipacaoIndiretaSingularDTO(RequisicaoReqDTO dto) throws BancoobException{
		FiltroParticipacaoIndiretaSingularDTO filtro = new FiltroParticipacaoIndiretaSingularDTO();
		
		if(dto.getDados().get(MES) != null) {
			filtro.setMes(Integer.valueOf(dto.getDados().get(MES).toString()));
		}
		
		if(dto.getDados().get(ANO) != null) {
			filtro.setAno(Integer.valueOf(dto.getDados().get(ANO).toString()));
		}
		
		if(dto.getDados().get(NUM_CENTRAL) != null && !dto.getDados().get(NUM_CENTRAL).equals("")) {
			filtro.setNumCentral(Integer.valueOf(dto.getDados().get(NUM_CENTRAL).toString()));
			Integer idInstCentral = delegateIntegracao.obterIdInstituicao(Integer.valueOf(dto.getDados().get(NUM_CENTRAL).toString()));
			filtro.setIdInstituicaoCentral(idInstCentral);
		}
		
		if(dto.getDados().get(NUM_COOPERATIVA) != null && !dto.getDados().get(NUM_COOPERATIVA).equals("")) {
			filtro.setNumCooperativa(Integer.valueOf(dto.getDados().get(NUM_COOPERATIVA).toString()));
			Integer idInstCooperativa = delegateIntegracao.obterIdInstituicao(Integer.valueOf(dto.getDados().get(NUM_COOPERATIVA).toString()));
			filtro.setIdInstituicaoSingular(idInstCooperativa);
		}
		
		if(dto.getDados().get(MES) != null && !dto.getDados().get(MES).equals("0")) {
			String ano = dto.getDados().get(ANO).toString();
			String mes = StringUtils.leftPad(dto.getDados().get(MES).toString(), 2, '0'); 
			filtro.setAnoMesBase(Integer.valueOf(ano + mes));
		}
		
		filtro.setChkArquivoExcel((Boolean) dto.getDados().get("agruparPorCentral"));
		
		return filtro;
	}
	/**
	 * Verifica se a singular recebida e realmente a filha da central recebida
	 * Trata problemas de passagens de informações descasadas
	 * @param idInstituicaoCentral
	 * @param idInstituicaoSingular
	 * @return
	 */
	private Boolean validarCentralporSingular(Integer idInstituicaoCentral,Integer idInstituicaoSingular) throws BancoobException{		

		boolean centralValida = true;
		Integer centralDaSingular;
		
		if (idInstituicaoCentral != null && idInstituicaoSingular != null){
			centralDaSingular = delegateIntegracao.consultarIdInstitucaoCentralporSingular(idInstituicaoSingular);		
		
			if(!centralDaSingular.equals(idInstituicaoCentral)){
				centralValida = false;
			}
		}
		return centralValida;
	}
	
}


