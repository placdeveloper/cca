package br.com.sicoob.cca.replicacao.negocio.batimento;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.sisbr.cca.legado.negocio.dto.RegistroBatimentoContaCapitalDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RegistroBatimentoLancamentoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RegistroBatimentoParcelamentoDTO;

/**
 * Adapter para os registros de batimento.
 * @author Nairon.Silva
 */
public final class RegistroBatimentoAdapter {
	
	private RegistroBatimentoAdapter() {
		// nada
	}
	
	/**
	 * Transforma os DTOs em Registros ContaCapital
	 * @param dtos
	 * @param idInstituicao
	 * @return
	 */
	public static List<RegistroBatimentoContaCapital> adaptarRegistroBatimentoContaCapital(List<RegistroBatimentoContaCapitalDTO> dtos, 
			Integer idInstituicao) {
		List<RegistroBatimentoContaCapital> registros = new ArrayList<RegistroBatimentoContaCapital>();
		for (RegistroBatimentoContaCapitalDTO dto : dtos) {
			RegistroBatimentoContaCapital registro = new RegistroBatimentoContaCapital();
			registro.setTabela(dto.getTabela());
			registro.setIdInstituicao(idInstituicao);
			registro.setTotal(dto.getTotal());
			registro.setValorSaldoAtuDevolver(dto.getValorSaldoAtuDevolver());
			registro.setValorSaldoAtuInteg(dto.getValorSaldoAtuInteg());
			registro.setValorSaldoAtuSubsc(dto.getValorSaldoAtuSubsc());
			registro.setValorSaldoBloq(dto.getValorSaldoBloq());
			registros.add(registro);
		}
		return registros;
	}
	
	/**
	 * Transforma os DTOs em Registros Lancamento
	 * @param dtos
	 * @param idInstituicao
	 * @return
	 */
	public static List<RegistroBatimentoLancamento> adaptarRegistroBatimentoLancamento(List<RegistroBatimentoLancamentoDTO> dtos, 
			Integer idInstituicao) {
		List<RegistroBatimentoLancamento> registros = new ArrayList<RegistroBatimentoLancamento>();
		for (RegistroBatimentoLancamentoDTO dto : dtos) {
			RegistroBatimentoLancamento registro = new RegistroBatimentoLancamento();
			registro.setTabela(dto.getTabela());
			registro.setIdInstituicao(idInstituicao);
			registro.setTotal(dto.getTotal());
			registro.setBolAtualizado(dto.getBolAtualizado());
			registro.setValorLanc(dto.getValorLanc());
			registros.add(registro);
		}
		return registros;
	}
	
	/**
	 * Transforma os DTOs em Registros Parcelamento
	 * @param dtos
	 * @param idInstituicao
	 * @return
	 */
	public static List<RegistroBatimentoParcelamento> adaptarRegistroBatimentoParcelamento(List<RegistroBatimentoParcelamentoDTO> dtos, 
			Integer idInstituicao) {
		List<RegistroBatimentoParcelamento> registros = new ArrayList<RegistroBatimentoParcelamento>();
		for (RegistroBatimentoParcelamentoDTO dto : dtos) {
			RegistroBatimentoParcelamento registro = new RegistroBatimentoParcelamento();
			registro.setTabela(dto.getTabela());
			registro.setIdInstituicao(idInstituicao);
			registro.setTotal(dto.getTotal());
			registro.setTipoParcelamento(dto.getTipoParcelamento());
			registro.setSituacao(dto.getSituacao());
			registro.setValor(dto.getValor());
			registros.add(registro);
		}
		return registros;
	}

}
