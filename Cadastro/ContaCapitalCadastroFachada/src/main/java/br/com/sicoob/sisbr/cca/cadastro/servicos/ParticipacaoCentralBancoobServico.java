/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.servicos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoob;
import br.com.sicoob.cca.entidades.negocio.entidades.ParticipacaoCentralBancoobPK;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ParticipacaoCentralBancoobDelegate;
import br.com.sicoob.sisbr.cca.cadastro.CadastroContaCapital;
import br.com.sicoob.sisbr.cca.cadastro.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.cadastro.vo.ParticipacaoCentralBancoobVO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.InstituicaoIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ItemListaIntegracaoDTO;

/**
 * A Classe ParticipacaoCentralBancoobServico.
 */
@RemoteService
public class ParticipacaoCentralBancoobServico extends CadastroContaCapital {
		
	/** O atributo instituicaoIntegracaoDelegate. */
	private InstituicaoIntegracaoDelegate instituicaoIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate();
	
	/** O atributo participacaoCentralBancoobDelegate. */
	private ParticipacaoCentralBancoobDelegate participacaoCentralBancoobDelegate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarParticipacaoCentralBancoobDelegate();
	
	/**
	 * Obter definicoes.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto)
			throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		List<ItemListaIntegracaoDTO> listaCentral = instituicaoIntegracaoDelegate.listarCentral();				
		List<ItemListaVO> listaVO = new ArrayList<ItemListaVO>();
		
		for(ItemListaIntegracaoDTO itemListaIntegracaoDTO:listaCentral){
			ItemListaVO item = new ItemListaVO(itemListaIntegracaoDTO.getCodListaItem(), itemListaIntegracaoDTO.getDescListaItem());
			listaVO.add(item);
		}		
		
		retorno.getDados().put("listaCentral", listaVO);
		return retorno;
	}	
	
	/**
	 * Obter dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		List<ParticipacaoCentralBancoob> lstParticipacaoCentralBancoob = null;
		List<ParticipacaoCentralBancoobVO> lstParticipacaoCentralBancoobVO = new ArrayList<ParticipacaoCentralBancoobVO>();
		
		ConsultaDto<ParticipacaoCentralBancoob> criterios = new ConsultaDto<ParticipacaoCentralBancoob>();
		ParticipacaoCentralBancoobPK filtro = new ParticipacaoCentralBancoobPK();
		ParticipacaoCentralBancoob filtroParticipacao = new ParticipacaoCentralBancoob();
		
		if (!dto.getDados().get("cmbCentral").toString().equals("")) {						
			filtro.setIdInstituicaoCentral(getIdInstituicao(Integer.valueOf(dto.getDados().get("cmbCentral").toString())));				
		}
		if (!dto.getDados().get("cmbAnoMesBase").toString().equals("")) {						
			filtro.setNumAnoMesBase(Integer.valueOf(dto.getDados().get("cmbAnoMesBase").toString()));			
		}
		filtroParticipacao.setId(filtro);
		criterios.setFiltro(filtroParticipacao);
		
		lstParticipacaoCentralBancoob = participacaoCentralBancoobDelegate.listar(criterios);		
		
		for(ParticipacaoCentralBancoob participacaoCentralBancoob:lstParticipacaoCentralBancoob){
			ParticipacaoCentralBancoobVO item = new ParticipacaoCentralBancoobVO();
			item.setDataHoraAtualizacao(participacaoCentralBancoob.getDataHoraAtualizacao());
			item.setIdInstituicaoCentral(getIdNumCoop(participacaoCentralBancoob.getId().getIdInstituicaoCentral()));
			item.setNumAnoMesBase(participacaoCentralBancoob.getId().getNumAnoMesBase());			
			item.setValorParticipacao(participacaoCentralBancoob.getValorParticipacao());
			item.setIdUsuario(participacaoCentralBancoob.getIdUsuario());
			
			lstParticipacaoCentralBancoobVO.add(item);
		}		
		
		retornoDTO.getDados().put("registros", lstParticipacaoCentralBancoobVO);

		return retornoDTO;
	}
	
	/**
	 * Gravar participacao central.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO gravarParticipacaoCentral(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		ParticipacaoCentralBancoob participacaoCentralBancoob = new ParticipacaoCentralBancoob();
		
		participacaoCentralBancoob.setId(new ParticipacaoCentralBancoobPK(getIdInstituicao(Integer.valueOf(dto.getDados().get("cmbCentral").toString())), Integer.valueOf(dto.getDados().get("cmbAnoMesBase").toString())));
		participacaoCentralBancoob.setDataHoraAtualizacao(new DateTimeDB());
		participacaoCentralBancoob.setValorParticipacao(new BigDecimal(dto.getDados().get("valorParticipacao").toString()));
		participacaoCentralBancoob.setIdUsuario(InformacoesUsuario.getInstance().getLogin());		
		
		if(dto.getDados().get("strIncluir").toString().equals("1")){
			participacaoCentralBancoobDelegate.incluirParticipacaoCentral(participacaoCentralBancoob);			
		}else{
			participacaoCentralBancoobDelegate.alterarParticipacaoCentral(participacaoCentralBancoob);			
		}		
		
		retornoDTO.getDados().put("msg", "Dados gravados com sucesso.");
		
		return retornoDTO;		
	}
	
	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @param numCoop o valor de num coop
	 * @return o valor de idInstituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Integer getIdInstituicao(Integer numCoop) throws BancoobException {
		Integer idInstituicao = null;
		try {
			idInstituicao = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate().obterIdInstituicao(numCoop);
		} catch (BancoobException e) {
			throw new BancoobException(e);
		}
		return idInstituicao;
	}		
	
	/**
	 * Recupera o valor de idNumCoop.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return o valor de idNumCoop
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private Integer getIdNumCoop(Integer idInstituicao) throws BancoobException {
		Integer numCoop = null;
		try {
			numCoop = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate().obterNumeroCooperativa(idInstituicao);
		} catch (BancoobException e) {
			throw new BancoobException(e);
		}
		return numCoop;
	}		
}
