/*
 * 
 */
package br.com.sicoob.sisbr.cca.comum.servicos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.sisbr.cca.comum.ContaCapitalComumServico;
import br.com.sicoob.sisbr.cca.comum.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.InstituicaoIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.CentralCooperativaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;

/**
 * A Classe NivelInstituicaoServico.
 */
@RemoteService
public class NivelInstituicaoServico extends ContaCapitalComumServico {

	/** O atributo delegateIntegracao. */
	private InstituicaoIntegracaoDelegate delegateIntegracao = ContaCapitalIntegracaoFabricaDelegates.getInstance()
			.criarInstituicaoIntegracaoDelegate();

	/** O atributo idInstituicao. */
	private Integer idInstituicao = Integer.valueOf(0);

	private final Integer ID_BANCOOB = Integer.valueOf(1);
	/**
	 * Obter definicoes.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		
		 if(dto.getDados().get("numCoop").toString().equals(ID_BANCOOB.toString())) { 
			 idInstituicao = ID_BANCOOB;
		 }else {		
			 idInstituicao = delegateIntegracao
				.obterIdInstituicao(Integer.valueOf(dto.getDados().get("numCoop").toString()));
		 }

		Integer tipoGrauCoopetativa = delegateIntegracao.obterTipoGrauCooperativa(idInstituicao).getCodTipoGrauCoop();
		retorno.getDados().put("tipoGrauCoopetativa", tipoGrauCoopetativa);
		
		if (tipoGrauCoopetativa.equals(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_BANCOOB)) {
			retorno.getDados().put("tipoGrauCoopetativa",
					ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_CONFEDERACAO);

			List<ItemListaIntegracaoDTO> listaCentral = delegateIntegracao.listarCentralEConfederacao();
			
			retorno.getDados().put("listaCentral", montarListaCombo(listaCentral));
			
		}else if (tipoGrauCoopetativa.equals(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_CONFEDERACAO)) {

			retorno.getDados().put("tipoGrauCoopetativa",
					ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_CONFEDERACAO);

			List<ItemListaIntegracaoDTO> listaCentral = delegateIntegracao.listarCentralInstituicao();

			listaCentral.add(new ItemListaIntegracaoDTO(idInstituicao.toString(), "0300 - SICOOB CONFEDERAÇÃO"));

			retorno.getDados().put("listaCentral", montarListaCombo(listaCentral));
		} else if (tipoGrauCoopetativa.equals(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_CENTRAL)) {
			CentralCooperativaDTO centralCooperativaDTO = delegateIntegracao.consultarCentralCooperativa(idInstituicao);
			retorno.getDados().put("listaCentral", montarCentralSingularCombo(centralCooperativaDTO,
					ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_CENTRAL));

			List<ItemListaIntegracaoDTO> listaSingular = new ArrayList<ItemListaIntegracaoDTO>();
			if (dto.getDados().get("bolInstCentral") != null
					&& ((Boolean) dto.getDados().get("bolInstCentral")).equals(Boolean.TRUE)) {
				CentralCooperativaDTO dtoCentral = delegateIntegracao.consultarCentralCooperativa(idInstituicao);
				listaSingular.add(new ItemListaIntegracaoDTO(dtoCentral.getIdInstituicaoCentral().toString(),
						dtoCentral.getNumCentral().toString() + " - " + dtoCentral.getDescCentral()));
			}
			listaSingular.addAll(delegateIntegracao.listarSingularesInstituicao(idInstituicao));

			retorno.getDados().put("listaSingular", montarListaCombo(listaSingular));
		} else if (tipoGrauCoopetativa.equals(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_SINGULAR)) {
			CentralCooperativaDTO centralCooperativaDTO = delegateIntegracao.consultarCentralCooperativa(idInstituicao);
			retorno.getDados().put("listaCentral", montarCentralSingularCombo(centralCooperativaDTO,
					ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_CENTRAL));
			retorno.getDados().put("listaSingular", montarCentralSingularCombo(centralCooperativaDTO,
					ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_SINGULAR));
		}
		return retorno;
	}

	/**
	 * Listar singulares central.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO listarSingularesCentral(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		idInstituicao = Integer.valueOf(dto.getDados().get("idInstituicao").toString());

		Integer numCoop = delegateIntegracao.obterNumeroCooperativa(idInstituicao);
		if (numCoop != null && numCoop.equals(ContaCapitalConstantes.NUMERO_CONFEDERACAO)) {
			List<ItemListaVO> lstConf = new ArrayList<ItemListaVO>(0);
			lstConf.add(new ItemListaVO(idInstituicao.toString(), "0300 - SICOOB CONFEDERAÇÃO"));
			retorno.getDados().put("listaSingular", lstConf);
			return retorno;
		}

		List<ItemListaIntegracaoDTO> listaSingular = delegateIntegracao.listarSingularesInstituicao(idInstituicao);

		List<ItemListaVO> lst = new ArrayList<ItemListaVO>();
		if (dto.getDados().get("bolInstCentral") != null
				&& ((Boolean) dto.getDados().get("bolInstCentral")).equals(Boolean.TRUE)) {
			CentralCooperativaDTO dtoCentral = delegateIntegracao.consultarCentralCooperativa(idInstituicao);
			lst.add(new ItemListaVO(dtoCentral.getIdInstituicaoCentral().toString(),
					dtoCentral.getNumCentral().toString() + " - " + dtoCentral.getDescCentral()));
		}
		lst.addAll(montarListaCombo(listaSingular));

		retorno.getDados().put("listaSingular", lst);

		return retorno;
	}

	/**
	 * Montar lista combo.
	 *
	 * @param listaDTO o valor de lista dto
	 * @param object 
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<ItemListaVO> montarListaCombo(List<ItemListaIntegracaoDTO> listaDTO) throws BancoobException {
		List<ItemListaVO> listaVO = new ArrayList<ItemListaVO>();
		for (ItemListaIntegracaoDTO itemListaIntegracaoDTO : listaDTO) {
			ItemListaVO item = new ItemListaVO(itemListaIntegracaoDTO.getCodListaItem(),
					itemListaIntegracaoDTO.getDescListaItem());
			listaVO.add(item);
		}

		Collections.sort(listaVO);
		return listaVO;		
	}

	/**
	 * Montar central singular combo.
	 *
	 * @param centralCooperativaDTO o valor de central cooperativa dto
	 * @param tipoGrauCoopetativa   o valor de tipo grau coopetativa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<ItemListaVO> montarCentralSingularCombo(CentralCooperativaDTO centralCooperativaDTO,
			Integer tipoGrauCoopetativa) throws BancoobException {
		List<ItemListaVO> listaVO = new ArrayList<ItemListaVO>();
		ItemListaVO item = null;
		if (tipoGrauCoopetativa == 1) {
			item = new ItemListaVO(centralCooperativaDTO.getIdInstituicaoCentral().toString(),
					centralCooperativaDTO.getNumCentral().toString() + " - " + centralCooperativaDTO.getDescCentral());
		} else {
			item = new ItemListaVO(centralCooperativaDTO.getIdInstituicaoCooperativa().toString(),
					centralCooperativaDTO.getNumCooperativa().toString() + " - "
							+ centralCooperativaDTO.getDescCooperativa());
		}
		listaVO.add(item);
		Collections.sort(listaVO);
		return listaVO;
	}
}
