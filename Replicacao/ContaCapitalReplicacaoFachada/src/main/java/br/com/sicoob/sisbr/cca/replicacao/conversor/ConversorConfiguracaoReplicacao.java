package br.com.sicoob.sisbr.cca.replicacao.conversor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.replicacao.negocio.dto.BatimentoSaldoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ConfiguracaoConciliacaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.ExpurgoReplicacaoDTO;
import br.com.sicoob.sisbr.cca.replicacao.vo.BatimentoSaldoVO;
import br.com.sicoob.sisbr.cca.replicacao.vo.ConfiguracaoConciliacaoVO;
import br.com.sicoob.sisbr.cca.replicacao.vo.ExpurgoReplicacaoVO;

/**
 * Conversor usada na fachada de configuracao de replicacao.
 * @author Nairon.Silva
 */
public class ConversorConfiguracaoReplicacao {

	/**
	 * Transforma ConfiguracaoConciliacaoDTO em ConfiguracaoConciliacaoVO.
	 * @param map
	 * @param key
	 */
	@SuppressWarnings("unchecked")
	public void transformarDTOConciliacaoParaVO(Map<String, Object> map, String key) {
		List<ConfiguracaoConciliacaoDTO> dtos = (List<ConfiguracaoConciliacaoDTO>) map.get(key);
		List<ConfiguracaoConciliacaoVO> vos = new ArrayList<ConfiguracaoConciliacaoVO>();
		for (ConfiguracaoConciliacaoDTO dto : dtos) {
			ConfiguracaoConciliacaoVO vo = new ConfiguracaoConciliacaoVO();
			vo.setCentral(dto.getCentral());
			vo.setCooperativa(dto.getCooperativa());
			vo.setDataHoraInclusao(dto.getDataHoraInclusao() == null ? null : new DateTimeDB(dto.getDataHoraInclusao().getTime()));
			vo.setDataLote(dto.getDataLote() == null ? null : new DateTimeDB(dto.getDataLote().getTime()));
			vo.setIdHistorico(dto.getIdHistorico());
			vo.setNumLoteLanc(dto.getNumLoteLanc());
			vo.setNumMatricula(dto.getNumMatricula());
			vo.setNumSeqLanc(dto.getNumSeqLanc());
			vo.setNumCliente(dto.getNumCliente());
			vo.setDataMatricula(dto.getDataMatricula());
			vo.setScript(dto.getScript());
			vo.setValorDiferenca(dto.getValorDiferenca());
			vo.setValorLanc(dto.getValorLanc());
			vo.setValorLancIntegDoDia(dto.getValorLancIntegDoDia());
			vo.setValorLancSubscDoDia(dto.getValorLancSubscDoDia());
			vo.setValorParcEmAberto(dto.getValorParcEmAberto());
			vo.setValorSaldoAtuInteg(dto.getValorSaldoAtuInteg());
			vo.setValorSaldoAtuSubsc(dto.getValorSaldoAtuSubsc());
			vo.setValorSaldoAtuDevol(dto.getValorSaldoAtuDevol());
			vo.setValorSaldoBloqInt(dto.getValorSaldoBloqInt());
			vo.setValorCapaLote(dto.getValorCapaLote());
			vo.setQtdCapaLote(dto.getQtdCapaLote());
			vo.setValorLancamentos(dto.getValorLancamentos());
			vo.setQtdLancamentos(dto.getQtdLancamentos());
			vo.setCodGrupoHist(dto.getCodGrupoHist());
			vo.setAuxiliar1(dto.getAuxiliar1());
			vo.setAuxiliar2(dto.getAuxiliar2());
			vo.setAuxiliar3(dto.getAuxiliar3());
			vo.setAuxiliar4(dto.getAuxiliar4());
			vo.setAuxiliar5(dto.getAuxiliar5());
			vos.add(vo);
		}
		map.put(key, vos);
	}
	
	/**
	 * Transforma lista de ExpurgoReplicacaoDTO em lista de ExpurgoReplicacaoVO.
	 * @param dtos
	 * @return
	 */
	public List<ExpurgoReplicacaoVO> transformarDTOExpurgoParaVO(List<ExpurgoReplicacaoDTO> dtos) {
		List<ExpurgoReplicacaoVO> vos = new ArrayList<ExpurgoReplicacaoVO>();
		for (ExpurgoReplicacaoDTO dto : dtos) {
			ExpurgoReplicacaoVO vo = new ExpurgoReplicacaoVO();
			vo.setNumCooperativa(dto.getNumCooperativa());
			vo.setIdInstituicao(dto.getIdInstituicao());
			vo.setQuantidade(dto.getQuantidade());
			vo.setNomeCooperativa(dto.getNomeCooperativa());
			vos.add(vo);
		}
		return vos;
	}
	
	/**
	 * Transforma lista de BatimentoSaldoDTO para VO
	 * @param dtos
	 * @return
	 */
	public List<BatimentoSaldoVO> transformarBatimentoSaldoDTOparaVO(List<BatimentoSaldoDTO> dtos) {
		List<BatimentoSaldoVO> vos = new ArrayList<BatimentoSaldoVO>();
		for (BatimentoSaldoDTO dto : dtos) {
			BatimentoSaldoVO vo = new BatimentoSaldoVO();
			vo.setNumMatricula(dto.getNumMatricula());
			vo.setValorSaldoBloq(dto.getValorSaldoBloq());
			vo.setValorSaldoBloqLegado(dto.getValorSaldoBloqLegado());
			vo.setValorSaldoDevol(dto.getValorSaldoDevol());
			vo.setValorSaldoDevolLegado(dto.getValorSaldoDevolLegado());
			vo.setValorSaldoInteg(dto.getValorSaldoInteg());
			vo.setValorSaldoIntegLegado(dto.getValorSaldoIntegLegado());
			vo.setValorSaldoSubsc(dto.getValorSaldoSubsc());
			vo.setValorSaldoSubscLegado(dto.getValorSaldoSubscLegado());
			vos.add(vo);
		}
		return vos;
	}
	
}
