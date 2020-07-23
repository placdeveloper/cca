/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.cadastro.negocio.delegates.ConfiguracaoCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.dto.CondicaoEstatutariaDTO;
import br.com.sicoob.sisbr.cca.cadastro.CadastroContaCapital;
import br.com.sicoob.sisbr.cca.cadastro.conversor.ConversorCondicaoEstatutaria;
import br.com.sicoob.sisbr.cca.cadastro.vo.CondicaoEstatutariaVO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.InstituicaoIntegracaoDelegate;

/**
 * @author marco.nascimento
 */
@RemoteService
public class CondEstatutariaServico extends CadastroContaCapital {
	
	private ConfiguracaoCapitalDelegate confDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarConfiguracaoCapitalDelegate();
	private InstituicaoIntegracaoDelegate iiDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate();
	private ConversorCondicaoEstatutaria conversor = new ConversorCondicaoEstatutaria();
	
	/**
	 * Apresentacao inicial da tela
	 * @param reqDTO
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Integer idInstituicao = iiDelegate.obterIdInstituicao(Integer.valueOf(InformacoesUsuario.getInstance().getCooperativa()));
		
		List<CondicaoEstatutariaDTO> lstCondicao = confDelegate.consultarConfiguracaoEstatutaria(idInstituicao);
		
		if(lstCondicao != null && !lstCondicao.isEmpty()) {
			
			SortedMap<String, List<CondicaoEstatutariaVO>> map = new TreeMap<String, List<CondicaoEstatutariaVO>>();
			for(CondicaoEstatutariaDTO dto : lstCondicao) {
				
				if(map.containsKey(dto.getNomeAgrupadorConfiguracaoCapital())) {
					map.get(dto.getNomeAgrupadorConfiguracaoCapital()).add(conversor.dtoParaVo(dto));
				} else {
					List<CondicaoEstatutariaVO> lstMap = new ArrayList<CondicaoEstatutariaVO>(0);
					lstMap.add(conversor.dtoParaVo(dto));
					map.put(dto.getNomeAgrupadorConfiguracaoCapital(), lstMap);
				}
			}
			
			retornoDTO.getDados().put("registros", map);
		}
		
		return retornoDTO;
	}
}