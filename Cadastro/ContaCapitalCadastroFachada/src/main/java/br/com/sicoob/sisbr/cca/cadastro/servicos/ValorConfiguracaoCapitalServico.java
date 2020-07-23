/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.cca.cadastro.negocio.delegates.ConfiguracaoCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorConfiguracaoCapitalDelegate;
import br.com.sicoob.cca.entidades.negocio.entidades.ConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;
import br.com.sicoob.sisbr.cca.cadastro.CadastroContaCapital;
import br.com.sicoob.sisbr.cca.cadastro.vo.ConfiguracaoCapitalVO;
import br.com.sicoob.sisbr.cca.cadastro.vo.ValorConfiguracaoCapitalVO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.InstituicaoIntegracaoDelegate;

/**
 * A Classe ValorConfiguracaoCapitalServico.
 */
@RemoteService
public class ValorConfiguracaoCapitalServico extends CadastroContaCapital {
	
	/** O atributo configuracaoCapitalDelegate. */
	private ConfiguracaoCapitalDelegate configuracaoCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarConfiguracaoCapitalDelegate();
	
	/** O atributo valorConfiguracaoCapitalDelegate. */
	private ValorConfiguracaoCapitalDelegate valorConfiguracaoCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarValorConfiguracaoCapitalDelegate();
	
	/** O atributo instituicaoIntegracaoDelegate. */
	private InstituicaoIntegracaoDelegate instituicaoIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate();
	
	
	
	/**
	 * Obter definicoes.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();

		ConfiguracaoCapital configuracaoCapitalConsulta = new ConfiguracaoCapital();  
		configuracaoCapitalConsulta.setId(Integer.valueOf(dto.getDados().get("idConfiguracaoCapital").toString()));			
		ConfiguracaoCapital configuracaoCapital = configuracaoCapitalDelegate.obterConfiguracaoCapital(configuracaoCapitalConsulta.getId());					
		retornoDTO.getDados().put("dadosConfiguracaoCapital", montarConfiguracaoCapital(configuracaoCapital));			
		
		Integer idInstituicao = instituicaoIntegracaoDelegate.obterIdInstituicao(Integer.valueOf(dto.getDados().get("numCoop").toString()));	
		Integer tipoGrauCoopetativa = instituicaoIntegracaoDelegate.obterTipoGrauCooperativa(idInstituicao).getCodTipoGrauCoop();			
		retornoDTO.getDados().put("tipoGrauCoopetativa", tipoGrauCoopetativa);					
		ValorConfiguracaoCapitalVO valorConfiguracaoCapitalVO = new ValorConfiguracaoCapitalVO();				
		valorConfiguracaoCapitalVO.setXmlString(valorConfiguracaoCapitalDelegate.consultaListaInstituicaoParametro(Integer.valueOf(dto.getDados().get("idConfiguracaoCapital").toString()), tipoGrauCoopetativa, Integer.valueOf(dto.getDados().get("numCoop").toString())).asXML());		
		retornoDTO.getDados().put("listaValorConfiguracaoCapital", valorConfiguracaoCapitalVO);
		
		return retornoDTO;
	}	
	
	/**
	 * Montar configuracao capital.
	 *
	 * @param configuracaoCapital o valor de configuracao capital
	 * @return ConfiguracaoCapitalVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private ConfiguracaoCapitalVO montarConfiguracaoCapital(ConfiguracaoCapital configuracaoCapital) throws BancoobException {				
		ConfiguracaoCapitalVO configuracaoCapitalVO = new ConfiguracaoCapitalVO();		
		configuracaoCapitalVO.setIdConfiguracaoCapital(configuracaoCapital.getId());
		
		if(configuracaoCapital.getAgrupadorConfiguracaoCapital() != null && configuracaoCapital.getAgrupadorConfiguracaoCapital().getId() != null) {
			String nomeComAgrupador = "[" + configuracaoCapital.getAgrupadorConfiguracaoCapital().getNome() + "]"  + " - " +  configuracaoCapital.getNomeConfiguracaoCapital();
			configuracaoCapitalVO.setNomeConfiguracaoCapital(nomeComAgrupador);
		} else {
			configuracaoCapitalVO.setNomeConfiguracaoCapital(configuracaoCapital.getNomeConfiguracaoCapital());
		}
		
		configuracaoCapitalVO.setDescConfiguracaoCapital(configuracaoCapital.getDescConfiguracaoCapital());
		configuracaoCapitalVO.setBolAtivo(configuracaoCapital.getBolAtivo());
		configuracaoCapitalVO.setIdTipoValorConfiguracaoCapital(Integer.valueOf(configuracaoCapital.getTipoValorConfiguracaoCapital().getId()));
		configuracaoCapitalVO.setDescTipoValorConfiguracaoCapital(configuracaoCapital.getTipoValorConfiguracaoCapital().getDescTipoValorConfiguracaoCapital());
		configuracaoCapitalVO.setIdPerfilConfiguracaoCapital(Integer.valueOf(configuracaoCapital.getPerfilConfiguracaoCapital().getId()));
		configuracaoCapitalVO.setDescPerfilConfiguracaoCapital(configuracaoCapital.getPerfilConfiguracaoCapital().getDescPerfilConfiguracaoCapital());
		configuracaoCapitalVO.setBolMaiorZero(configuracaoCapital.getBolMaiorZero());
		configuracaoCapitalVO.setBolMostrarRelatorio(configuracaoCapital.getBolMostrarRelatorio());
		return configuracaoCapitalVO;		
	}		

	/**
	 * Gravar lista valor configuracao capital.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO gravarListaValorConfiguracaoCapital(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		@SuppressWarnings("unchecked")
		List<ValorConfiguracaoCapitalVO> listaDados = (List<ValorConfiguracaoCapitalVO>) dto.getDados().get("listaValorConfiguracaoCapital");			
		
		valorConfiguracaoCapitalDelegate.gravarListaValorConfiguracaoCapital(montarListaValorConfiguracaoCapital(listaDados));
		retornoDTO.getDados().put("msg", "Dados gravados com sucesso.");		
		return retornoDTO;		
	}	
	
	/**
	 * Montar lista valor configuracao capital.
	 *
	 * @param lstValorConfiguracaoCapitalVO o valor de lst valor configuracao capital vo
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<ValorConfiguracaoCapital> montarListaValorConfiguracaoCapital(List<ValorConfiguracaoCapitalVO> lstValorConfiguracaoCapitalVO) throws BancoobException {				
		List<ValorConfiguracaoCapital> lstValorConfiguracaoCapital = new ArrayList<ValorConfiguracaoCapital>();		
		
		for(ValorConfiguracaoCapitalVO valorConfiguracaoCapitalVO:lstValorConfiguracaoCapitalVO){
			ValorConfiguracaoCapital item = new ValorConfiguracaoCapital();		
			item.setId(valorConfiguracaoCapitalVO.getIdValorConfiguracaoCapital());
			ConfiguracaoCapital configuracaoCapital = new ConfiguracaoCapital();
			configuracaoCapital.setId(valorConfiguracaoCapitalVO.getIdConfiguracaoCapital());			
			item.setConfiguracaoCapital(configuracaoCapital);			
			item.setIdInstituicao(valorConfiguracaoCapitalVO.getIdInstituicao());
			item.setValorConfiguracao(valorConfiguracaoCapitalVO.getValorConfiguracao());
			item.setIdUsuario(valorConfiguracaoCapitalVO.getIdUsuario());
			item.setDataHoraAtualizacao(new DateTimeDB(DataUtil.obterDataAtual().getTime()));
			lstValorConfiguracaoCapital.add(item);			
		}
			
		return lstValorConfiguracaoCapital;		
	}			
}
