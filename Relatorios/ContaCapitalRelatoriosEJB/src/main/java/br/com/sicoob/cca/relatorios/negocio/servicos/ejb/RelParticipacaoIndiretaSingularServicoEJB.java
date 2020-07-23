package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.dto.FiltroParticipacaoIndiretaSingularDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelParticipacaoIndiretaSingularDTO;
import br.com.sicoob.cca.relatorios.negocio.excecao.ContaCapitalRelatoriosNegocioException;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelParticipacaoIndiretaSingularServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelParticipacaoIndiretaSingularServicoRemote;
import br.com.sicoob.cca.relatorios.persistencia.dao.ContaCapitalRelatoriosDaoFactory;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelParticipacaoIndiretaSingularDao;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;

/**
 * Responsavel por gerar relatorios de participação indireta
 * @author Sron.Cruz
 */
@Stateless
@Local(RelParticipacaoIndiretaSingularServicoLocal.class)
@Remote(RelParticipacaoIndiretaSingularServicoRemote.class)
public class RelParticipacaoIndiretaSingularServicoEJB extends ContaCapitalRelatoriosServicoEJB implements
		RelParticipacaoIndiretaSingularServicoLocal,
		RelParticipacaoIndiretaSingularServicoRemote {

	@EJB
	private InstituicaoIntegracaoServicoLocal integracaoServico;
	
	@Dao (entityManager = "em", fabrica = ContaCapitalRelatoriosDaoFactory.class)
	private RelParticipacaoIndiretaSingularDao relParticipacaoIndiretaSingularDao;
	
	/**
	 * @see br.com.sicoob.cca.relatorios.negocio.servicos.RelParticipacaoIndiretaSingularServico#emitirRelParticipacaoIndireta(br.com.sicoob.cca.relatorios.negocio.dto.FiltroParticipacaoIndiretaSingularDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object emitirRelParticipacaoIndireta(FiltroParticipacaoIndiretaSingularDTO dto)throws BancoobException {
		try {
			List<RelParticipacaoIndiretaSingularDTO> lista = obterListaRelParticipacaoIndiretaSingular(dto);

			String nomeRelatorio = "CCA_Relatorio_ParticipacaoIndireta.jasper";
			
			if(dto.getChkArquivoExcel()){
				nomeRelatorio = "CCA_Relatorio_ParticipacaoIndireta_semAgrupamento.jasper";
			}
			
			int idCentral = integracaoServico.obterNumeroCooperativa(dto.getNumCentral());
			int idSingular = integracaoServico.obterNumeroCooperativa(dto.getNumCooperativa());
			
			Map<String, Object> parametros = getParametrosComuns();
			parametros.put("NUM_CENTRAL", idCentral);
			parametros.put("NUM_SINGULAR", idSingular);
			parametros.put("MES", dto.getMes());
			parametros.put("ANO", dto.getAno());
			parametros.put("COD_RELATORIO", CodigoRelatorio.COD_PARTICIPACAO_INDIRETA);
			
			return new RelatorioContaCapitalV2<RelParticipacaoIndiretaSingularDTO>(lista, nomeRelatorio, parametros);
			
		} catch (ContaCapitalRelatoriosNegocioException e) {
			throw new NegocioException(e.getMessage());	
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException("MSG_RELATORIO_ERRO",e);
		}
	}
	
	private List<RelParticipacaoIndiretaSingularDTO> obterListaRelParticipacaoIndiretaSingular(FiltroParticipacaoIndiretaSingularDTO dto) throws BancoobException{
		List<RelParticipacaoIndiretaSingularDTO> lista = relParticipacaoIndiretaSingularDao.listarRelParticipacaoIndireta(dto);
		if(lista.isEmpty()){
			throw new ContaCapitalRelatoriosNegocioException("MSG_RELATORIO_SEM_REGISTROS");			
		}
		return lista;
	}

	public void setRelParticipacaoIndiretaSingularDao(
			RelParticipacaoIndiretaSingularDao relParticipacaoIndiretaSingularDao) {
		this.relParticipacaoIndiretaSingularDao = relParticipacaoIndiretaSingularDao;
	}

}