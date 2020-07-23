package br.com.sicoob.cca.relatorios.negocio.servicos.ejb.v2;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.dto.FiltroParticipacaoIndiretaSingularDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelParticipacaoIndiretaSingularDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelParticipacaoIndiretaSingularServicoLocal;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.InstituicaoIntegracaoDelegate;

/**
 * Relatorio ParticipacaoIndiretaSingular
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelV2ParticipacaoIndiretaSingularServicoEJB implements IProcessamentoRelatorioServico {

	private InstituicaoIntegracaoDelegate delegateIntegracao = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate();
	
	@EJB
	private RelParticipacaoIndiretaSingularServicoLocal servico;
	
	private static final String NUM_CENTRAL = "numCentral";
	private static final String NUM_COOPERATIVA = "numCooperativa";
	private static final String MES = "mes";
	private static final String ANO = "ano";
	
	/**
	 * @see br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico#gerarRelatorio(br.com.sicoob.relatorio.api.dto.ParametroDTO, br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		FiltroParticipacaoIndiretaSingularDTO filtro = montarFiltroParticipacaoIndiretaSingularDTO(dto);
		if (!validarCentralporSingular(filtro.getIdInstituicaoCentral(), filtro.getIdInstituicaoSingular())){
			return null;
		}
		
		servico.configurarParametrosComuns(rDto);
		RelatorioContaCapitalV2<RelParticipacaoIndiretaSingularDTO> rel = 
				(RelatorioContaCapitalV2<RelParticipacaoIndiretaSingularDTO>) servico.emitirRelParticipacaoIndireta(filtro);
		return rel.gerar(rDto);
	}
	
	private FiltroParticipacaoIndiretaSingularDTO montarFiltroParticipacaoIndiretaSingularDTO(ParametroDTO dto) throws BancoobException{
		FiltroParticipacaoIndiretaSingularDTO filtro = new FiltroParticipacaoIndiretaSingularDTO();
		
		if(dto.getDados().get(MES) != null) {
			filtro.setMes(Integer.valueOf(dto.getDados().get(MES).toString()));
		}
		
		if(dto.getDados().get(ANO) != null) {
			filtro.setAno(Integer.valueOf(dto.getDados().get(ANO).toString()));
		}
		
		if(!"".equals(dto.getDados().get(NUM_CENTRAL))) {
			filtro.setNumCentral(Integer.valueOf(dto.getDados().get(NUM_CENTRAL).toString()));
			Integer idInstCentral = delegateIntegracao.obterIdInstituicao(Integer.valueOf(dto.getDados().get(NUM_CENTRAL).toString()));
			filtro.setIdInstituicaoCentral(idInstCentral);
		}
		
		if(!"".equals(dto.getDados().get(NUM_COOPERATIVA))) {
			filtro.setNumCooperativa(Integer.valueOf(dto.getDados().get(NUM_COOPERATIVA).toString()));
			Integer idInstCooperativa = delegateIntegracao.obterIdInstituicao(Integer.valueOf(dto.getDados().get(NUM_COOPERATIVA).toString()));
			filtro.setIdInstituicaoSingular(idInstCooperativa);
		}
		
		if(!"0".equals(dto.getDados().get(MES))) {
			String ano = dto.getDados().get(ANO).toString();
			String mes = StringUtils.leftPad(dto.getDados().get(MES).toString(), 2, '0'); 
			filtro.setAnoMesBase(Integer.valueOf(ano + mes));
		}
		
		filtro.setChkArquivoExcel((Boolean) dto.getDados().get("agruparPorCentral"));
		
		return filtro;
	}
	
	private Boolean validarCentralporSingular(Integer idInstituicaoCentral, Integer idInstituicaoSingular) throws BancoobException {		
		if (idInstituicaoCentral != null && idInstituicaoSingular != null){
			Integer centralDaSingular = delegateIntegracao.consultarIdInstitucaoCentralporSingular(idInstituicaoSingular);		
			return centralDaSingular.equals(idInstituicaoCentral);
		}
		return true;
	}

}
