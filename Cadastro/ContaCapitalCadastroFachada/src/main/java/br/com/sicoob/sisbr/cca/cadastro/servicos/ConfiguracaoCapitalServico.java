/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.cadastro.negocio.delegates.AgrupadorConfiguracaoCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ConfiguracaoCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorConfiguracaoCapitalDelegate;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.AgrupadorConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.ConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.PerfilConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoValorConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumPerfilConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoValorConfiguracaoCapital;
import br.com.sicoob.sisbr.cca.cadastro.CadastroContaCapital;
import br.com.sicoob.sisbr.cca.cadastro.vo.ConfiguracaoCapitalVO;
import br.com.sicoob.sisbr.cca.cadastro.vo.ItemListaVO;

/**
 * A Classe ConfiguracaoCapitalServico.
 */
@RemoteService
public class ConfiguracaoCapitalServico extends CadastroContaCapital {
	
	/** O atributo configuracaoCapitalDelegate. */
	private ConfiguracaoCapitalDelegate configuracaoCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarConfiguracaoCapitalDelegate();
	
	/** O atributo valorConfiguracaoCapitalDelegate. */
	private ValorConfiguracaoCapitalDelegate valorConfiguracaoCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarValorConfiguracaoCapitalDelegate();
	
	/** O atributo agrupadorDelegate. */
	private AgrupadorConfiguracaoCapitalDelegate agrupadorDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarAgrupadorConfiguracaoCapitalDelegate();
	
	/**
	 * Obter definicoes.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();

		if(!Integer.valueOf(dto.getDados().get("idConfiguracaoCapital").toString()).equals(ContaCapitalConstantes.NUM_ZERO)){
			ConfiguracaoCapital configuracaoCapitalConsulta = new ConfiguracaoCapital();  
			configuracaoCapitalConsulta.setId(Integer.valueOf(dto.getDados().get("idConfiguracaoCapital").toString()));			
			ConfiguracaoCapital configuracaoCapital = configuracaoCapitalDelegate.obterConfiguracaoCapital(configuracaoCapitalConsulta.getId());					
			retornoDTO.getDados().put("dadosConfiguracaoCapital", montarConfiguracaoCapital(configuracaoCapital));			
			
			ConsultaDto<ValorConfiguracaoCapital> consultaDTO = new ConsultaDto<ValorConfiguracaoCapital>();			
			ValorConfiguracaoCapital filtro = new ValorConfiguracaoCapital();
			filtro.setConfiguracaoCapital(configuracaoCapital);							
			consultaDTO.setFiltro(filtro);			
			
			List<ValorConfiguracaoCapital> lstValorConfiguracaoCapital = valorConfiguracaoCapitalDelegate.listarValorConfiguracaoCapital(consultaDTO);
			if(lstValorConfiguracaoCapital.isEmpty()){
				retornoDTO.getDados().put("possuiValorConfiguracaoCapital", ContaCapitalConstantes.NUM_ZERO);										
			}
			
		}else{			
			retornoDTO.getDados().put("idConfiguracaoCapital", configuracaoCapitalDelegate.pesquisarProximoSeqConfiguracaoCapital());						
		}
		
		List<ItemListaVO> listaVO = new ArrayList<ItemListaVO>();
		
		for(EnumTipoValorConfiguracaoCapital itemListaDTO:EnumTipoValorConfiguracaoCapital.values()){
			ItemListaVO item = new ItemListaVO(itemListaDTO.getCodigo().toString(), itemListaDTO.getDescricao());
			listaVO.add(item);
		}				
		
		retornoDTO.getDados().put("listaTipoValorConfiguracaoCapital", listaVO);		
		List<ItemListaVO> listaVO2 = new ArrayList<ItemListaVO>();
		
		for(EnumPerfilConfiguracaoCapital itemListaDTO:EnumPerfilConfiguracaoCapital.values()){
			ItemListaVO item = new ItemListaVO(itemListaDTO.getCodigo().toString(), itemListaDTO.getDescricao());
			listaVO2.add(item);
		}				
		
		retornoDTO.getDados().put("listaPerfilConfiguracaoCapital", listaVO2);		
		
		List<ItemListaVO> listaAgrupador = new ArrayList<ItemListaVO>();
		listaAgrupador.add(new ItemListaVO(null, "SELECIONE"));
		for(AgrupadorConfiguracaoCapital agrupador : agrupadorDelegate.listar()){
			listaAgrupador.add(new ItemListaVO(String.valueOf(agrupador.getId()), agrupador.getNome()));
		}
		retornoDTO.getDados().put("listaAgrupador", listaAgrupador);	

		return retornoDTO;
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
		
		ConsultaDto<ConfiguracaoCapital> consultaDTO = new ConsultaDto<ConfiguracaoCapital>();
		consultaDTO.setFiltro(criarFiltroConsulta(dto));
		List<ConfiguracaoCapital> lstConfiguracaoCapital = configuracaoCapitalDelegate.listarConfiguracaoCapital(consultaDTO);
				
		if(lstConfiguracaoCapital.isEmpty()){
			retornoDTO.getDados().put("msg", "Não existem dados cadastrados para os filtros informados.");			
		}else{
			retornoDTO.getDados().put("registros", montarListaConfiguracaoCapital(lstConfiguracaoCapital));			
		}		

		return retornoDTO;
	}	
	
	/**
	 * Montar lista configuracao capital.
	 *
	 * @param lstConfiguracaoCapital o valor de lst configuracao capital
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<ConfiguracaoCapitalVO> montarListaConfiguracaoCapital(List<ConfiguracaoCapital> lstConfiguracaoCapital) throws BancoobException {	
		List<ConfiguracaoCapitalVO> lstConfiguracaoCapitalVO = new ArrayList<ConfiguracaoCapitalVO>();
		
		for(ConfiguracaoCapital configuracaoCapital:lstConfiguracaoCapital){
			ConfiguracaoCapitalVO item = new ConfiguracaoCapitalVO();
			item.setIdConfiguracaoCapital(configuracaoCapital.getId());
			item.setDescConfiguracaoCapital(configuracaoCapital.getDescConfiguracaoCapital());
			item.setBolAtivo(configuracaoCapital.getBolAtivo());
			item.setIdTipoValorConfiguracaoCapital(Integer.valueOf(configuracaoCapital.getTipoValorConfiguracaoCapital().getId()));
			item.setDescTipoValorConfiguracaoCapital(configuracaoCapital.getTipoValorConfiguracaoCapital().getDescTipoValorConfiguracaoCapital());
			item.setIdPerfilConfiguracaoCapital(Integer.valueOf(configuracaoCapital.getPerfilConfiguracaoCapital().getId()));
			item.setDescPerfilConfiguracaoCapital(configuracaoCapital.getPerfilConfiguracaoCapital().getDescPerfilConfiguracaoCapital());
			
			if(configuracaoCapital.getAgrupadorConfiguracaoCapital() != null && configuracaoCapital.getAgrupadorConfiguracaoCapital().getId() != null) {
				item.setNomeConfiguracaoCapital("[" + configuracaoCapital.getAgrupadorConfiguracaoCapital().getNome() + "] - " + configuracaoCapital.getNomeConfiguracaoCapital());
			} else {
				item.setNomeConfiguracaoCapital(configuracaoCapital.getNomeConfiguracaoCapital());
			}
			
			lstConfiguracaoCapitalVO.add(item);
			
		}						
		return lstConfiguracaoCapitalVO;		
	}
	
	/**
	 * Criar filtro consulta.
	 *
	 * @param reqDTO o valor de req dto
	 * @return ConfiguracaoCapital
	 */
	private ConfiguracaoCapital criarFiltroConsulta(RequisicaoReqDTO reqDTO) {
		ConfiguracaoCapital filtro = new ConfiguracaoCapital();
		
		if(!reqDTO.getDados().get("valorPesquisa").toString().equals("")){
			if(Integer.valueOf(reqDTO.getDados().get("tipoPesquisa").toString()).equals(ContaCapitalConstantes.NUM_TIPO_PESQUISA_PARAMETRO_CODIGO)){						
				filtro.setId(Integer.valueOf(reqDTO.getDados().get("valorPesquisa").toString()));				
			}else if(Integer.valueOf(reqDTO.getDados().get("tipoPesquisa").toString()).equals(ContaCapitalConstantes.NUM_TIPO_PESQUISA_PARAMETRO_NOME)){						
				filtro.setNomeConfiguracaoCapital(reqDTO.getDados().get("valorPesquisa").toString());			
			}else if(Integer.valueOf(reqDTO.getDados().get("tipoPesquisa").toString()).equals(ContaCapitalConstantes.NUM_TIPO_PESQUISA_PARAMETRO_DESCRICAO)){						
				filtro.setDescConfiguracaoCapital(reqDTO.getDados().get("valorPesquisa").toString());
			}			
		}						
		return filtro;
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
		configuracaoCapitalVO.setNomeConfiguracaoCapital(configuracaoCapital.getNomeConfiguracaoCapital());
		configuracaoCapitalVO.setDescConfiguracaoCapital(configuracaoCapital.getDescConfiguracaoCapital());
		configuracaoCapitalVO.setBolAtivo(configuracaoCapital.getBolAtivo());
		configuracaoCapitalVO.setIdTipoValorConfiguracaoCapital(Integer.valueOf(configuracaoCapital.getTipoValorConfiguracaoCapital().getId()));
		configuracaoCapitalVO.setDescTipoValorConfiguracaoCapital(configuracaoCapital.getTipoValorConfiguracaoCapital().getDescTipoValorConfiguracaoCapital());
		configuracaoCapitalVO.setIdPerfilConfiguracaoCapital(Integer.valueOf(configuracaoCapital.getPerfilConfiguracaoCapital().getId()));
		configuracaoCapitalVO.setDescPerfilConfiguracaoCapital(configuracaoCapital.getPerfilConfiguracaoCapital().getDescPerfilConfiguracaoCapital());
		configuracaoCapitalVO.setBolMaiorZero(configuracaoCapital.getBolMaiorZero());
		configuracaoCapitalVO.setBolMostrarRelatorio(configuracaoCapital.getBolMostrarRelatorio());
		
		if(configuracaoCapital.getAgrupadorConfiguracaoCapital() != null) {
			configuracaoCapitalVO.setIdAgrupador(configuracaoCapital.getAgrupadorConfiguracaoCapital().getId());
		}
		
		return configuracaoCapitalVO;		
	}		
	
	/**
	 * Gravar configuracao capital.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO gravarConfiguracaoCapital(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		ConfiguracaoCapital configuracaoCapital = new ConfiguracaoCapital();		
		configuracaoCapital.setId(Integer.valueOf(dto.getDados().get("idConfiguracaoCapital").toString()));
		configuracaoCapital.setNomeConfiguracaoCapital(dto.getDados().get("nomeConfiguracaoCapital").toString());
		configuracaoCapital.setDescConfiguracaoCapital(dto.getDados().get("descConfiguracaoCapital").toString());
		if(Integer.valueOf(dto.getDados().get("cmbSituacao").toString()).equals(ContaCapitalConstantes.ST_BOL_ATIVO)){
			configuracaoCapital.setBolAtivo(true);			
		}else{
			configuracaoCapital.setBolAtivo(false);						
		}
		
		PerfilConfiguracaoCapital perfilConfiguracaoCapital = new PerfilConfiguracaoCapital();
		perfilConfiguracaoCapital.setId(Short.valueOf(dto.getDados().get("cmbPerfilConfiguracaoCapital").toString()));		
		configuracaoCapital.setPerfilConfiguracaoCapital(perfilConfiguracaoCapital);
		
		TipoValorConfiguracaoCapital tipoValorConfiguracaoCapital = new TipoValorConfiguracaoCapital();
		tipoValorConfiguracaoCapital.setId(Short.valueOf(dto.getDados().get("cmbTipoValorConfiguracaoCapital").toString()));		
		configuracaoCapital.setTipoValorConfiguracaoCapital(tipoValorConfiguracaoCapital);
		
		if(dto.getDados().get("cmbAgrupador") != null) {
			AgrupadorConfiguracaoCapital agrupador = new AgrupadorConfiguracaoCapital();
			agrupador.setId(Integer.valueOf(dto.getDados().get("cmbAgrupador").toString()));
			configuracaoCapital.setAgrupadorConfiguracaoCapital(agrupador);
		} else {
			configuracaoCapital.setAgrupadorConfiguracaoCapital(null);
		}
		
		
		configuracaoCapital.setBolMaiorZero(Boolean.valueOf(dto.getDados().get("bolMaiorZero").toString()));
		configuracaoCapital.setBolMostrarRelatorio(Boolean.valueOf(dto.getDados().get("bolMostrarRelatorio").toString()));

		if(Integer.valueOf(dto.getDados().get("strIncluir").toString()).equals(ContaCapitalConstantes.ST_INCLUIR)){
			configuracaoCapitalDelegate.incluirConfiguracaoCapital(configuracaoCapital);		
		}else{
			configuracaoCapitalDelegate.alterarConfiguracaoCapital(configuracaoCapital);			
		}		
		
		retornoDTO.getDados().put("msg", "Dados gravados com sucesso.");		
		return retornoDTO;		
	}	
}
