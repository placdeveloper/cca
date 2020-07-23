package br.com.sicoob.sisbr.cca.movimentacao.servicos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.cca.cadastro.negocio.delegates.CadastroContaCapitalRenDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorCotaDelegate;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumSituacaoContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.DesligarContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalImpedimentosNegocioException;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoException;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ContaCapitalIntegracaoLegadoFabricaDelegates;
import br.com.sicoob.sisbr.cca.legado.negocio.delegates.ProdutoLegadoDelegate;
import br.com.sicoob.sisbr.cca.movimentacao.MovimentacaoContaCapital;
import br.com.sicoob.sisbr.cca.movimentacao.vo.CadastroContaCapitalRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.DesligarContaCapitalRenVO;

/**
 * A Classe DesligarContaCapitalServico.
 *
 * @author marco.nascimento
 */
@RemoteService
public class DesligarContaCapitalServico extends MovimentacaoContaCapital {
	
	/** O atributo prodLegadoDelegate. */
	private ProdutoLegadoDelegate prodLegadoDelegate = ContaCapitalIntegracaoLegadoFabricaDelegates.getInstance().criarProdutoLegadoDelegate();	
	
	/** O atributo desligarContaCapitalDelegate. */
	private DesligarContaCapitalDelegate desligarContaCapitalDelegate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarDesligarContaCapitalDelegate();
	
	/** O atributo contaCapitalDelegate. */
	private ContaCapitalDelegate contaCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate();
	
	/** O atributo valorCotaDelegate. */
	private ValorCotaDelegate valorCotaDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarValorCotaDelegate();
	
	/** O atributo cadastroDelegate. */
	private CadastroContaCapitalRenDelegate cadastroDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarCadastroContaCapitalRenDelegate();
	
	/**
	 * Obter definicoes da tela desligamento .
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		String dataAtualProdutoFormatada = DataUtil.converterDateToString(prodLegadoDelegate.obterDataAtualProdutoCCALogado(), "dd/MM/yyyy");
		retornoDTO.getDados().put("dataAtualProduto", dataAtualProdutoFormatada);
		
		return retornoDTO;
	}	
	
	/**
	 * Consulta usada na plataforma de atendimento.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO consultar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		Integer idPessoa = (Integer) reqDTO.getDados().get("idPessoa");
		Integer idInstituicao = (Integer) reqDTO.getDados().get("idInstituicao");
		
		if(idPessoa != null && idInstituicao != null) {
			
			CadastroContaCapitalRenDTO dto = new CadastroContaCapitalRenDTO();
			dto.setIdPessoa(idPessoa);
			dto.setIdInstituicao(idInstituicao);
			dto.setIdSituacaoContaCapital(EnumSituacaoContaCapital.COD_SITUACAO_COOPERADO_ATIVO.getCodigo());
			
			List<CadastroContaCapitalRenDTO> resultadoDTO = cadastroDelegate.pesquisar(dto);
			if(resultadoDTO != null && !resultadoDTO.isEmpty()) {
				retornoDTO.getDados().put("registros", lstDtoParaVo(resultadoDTO));
			}
		}
		
		return retornoDTO;
	}
	
	/**
	 * Obter informacoes sobre a conta capital selecionada .
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterInformacoes(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		try {
			Integer idContaCapital = (Integer) reqDTO.getDados().get("idContaCapital");
			
			if(idContaCapital != null) {
				ContaCapital cca = contaCapitalDelegate.obter(idContaCapital);
				verificarContaCapitalNaoEncontrada(cca);
				
				DesligarContaCapitalRenVO vo = new DesligarContaCapitalRenVO();
				vo.setIdContaCapital(cca.getId());
				vo.setIdInstituicao(cca.getIdInstituicao());
				vo.setIdPessoa(cca.getIdPessoa());
				vo.setNumContaCapital(cca.getNumContaCapital());
				vo.setVlrBloq(calcularValorBloqueado(idContaCapital));
				vo.setVlrSubs(calcularValorSubscrito(idContaCapital));
				vo.setVlrInteg(calcularValorIntegralizado(idContaCapital));
				vo.setVlrDevol(calcularValorDevolucao(idContaCapital));
				vo.setVlrAInteg(vo.getVlrSubs().subtract(vo.getVlrInteg()));
				
				BigDecimal valorCota = valorCotaDelegate.obterValorCota(vo.getIdInstituicao());
				if (valorCota.compareTo(BigDecimal.ZERO) == 0) {
					valorCota = BigDecimal.valueOf(1);
				} 
				vo.setQtdCotas(vo.getVlrInteg().divide(valorCota, RoundingMode.DOWN).intValue());
				
				retornoDTO.getDados().put("vo", vo);
			}
			
		} catch (BancoobException e) {
			SicoobLoggerPadrao.getInstance(getClass()).erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_011");			
		}
		
		return retornoDTO;
	}
	
	/**
	 * Desligar.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO desligar(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		
		DesligarContaCapitalRenVO vo = (DesligarContaCapitalRenVO) reqDTO.getDados().get("vo");
		
		try {
			
			desligarContaCapitalDelegate.desligarContaCapital(vo.getIdContaCapital(), vo.getTipoOperacao(), vo.getDataDesligamento());
			
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			retornoDTO.getDados().put("erroNegocial", e.getMessage());			
			retornoDTO.getDados().put("msg", e.getMessage());
			
		} catch (ContaCapitalImpedimentosNegocioException e) {
			retornoDTO.getDados().put("erroNegocial", e.getMessage());			
			retornoDTO.getDados().put("msg", e.getMessage());
			retornoDTO.getDados().put("impedimentos", true);
			
		} catch (ContaCapitalMovimentacaoException e) {
			SicoobLoggerPadrao.getInstance(getClass()).erro(e, e.getMessage());
			throw new BancoobException(e);
		}
		
		return retornoDTO;
	}
	
	/**
	 * O método Dto para vo.
	 *
	 * @param vo o valor de vo
	 * @param dto o valor de dto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void dtoParaVo(CadastroContaCapitalRenVO vo, CadastroContaCapitalRenDTO dto) throws BancoobException {
		vo.setIdContaCapital(dto.getIdContaCapital());
		vo.setIdInstituicao(dto.getIdInstituicao());
		vo.setIdPessoa(dto.getIdPessoa());
		vo.setNomePessoa(dto.getNomePessoa());
		vo.setNomeCompleto(dto.getNomeCompleto());
		vo.setCpfCnpj(dto.getCpfCnpj());
		vo.setIdPessoaLegado(dto.getIdPessoaLegado());
		vo.setNumContaCapital(dto.getNumContaCapital());
		vo.setNumContaCapitalGerada(dto.getNumContaCapitalGerada());
		vo.setVlrSubs(dto.getVlrSubs());
		vo.setVlrInteg(dto.getVlrInteg());
		vo.setQtdParcelas(dto.getQtdParcelas());
		vo.setVlrParcelas(dto.getVlrParcelas());
		vo.setDiaDebito(dto.getDiaDebito());
		vo.setTipoInteg(dto.getTipoInteg());
		vo.setNumCco(dto.getNumCco());
		vo.setIdSituacaoCadastro(dto.getIdSituacaoCadastro());
		vo.setDescSituacaoAprovacaoCapital(dto.getDescSituacaoAprovacaoCapital());
		vo.setDescSituacaoContaCapital(dto.getDescSituacaoContaCapital());
		vo.setMatriculaEscolhida(dto.getMatriculaEscolhida());
		vo.setDataHoraAtualizacao(dto.getDataHoraAtualizacao());
		vo.setIdAtividade(dto.getIdAtividade());
		vo.setObservacao(dto.getObservacao());
		vo.setIdSituacaoContaCapital(dto.getIdSituacaoContaCapital());
		
		Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));
	    c.set(Calendar.MINUTE, c.getActualMinimum(Calendar.MINUTE));
	    c.set(Calendar.SECOND, c.getActualMinimum(Calendar.SECOND));
	    c.set(Calendar.MILLISECOND, c.getActualMinimum(Calendar.MILLISECOND));
	    
		if(dto.getDataMatricula() != null && dto.getDataMatricula().compareTo(c.getTime()) == 0) {
			vo.setPermissaoExcluir(true);
		} else {
			vo.setPermissaoExcluir(false);
		}
	}
	
	/**
	 * Lst dto para vo.
	 *
	 * @param lstDTO o valor de lst dto
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private List<CadastroContaCapitalRenVO> lstDtoParaVo(List<CadastroContaCapitalRenDTO> lstDTO) throws BancoobException {
		List<CadastroContaCapitalRenVO> lst = new ArrayList<CadastroContaCapitalRenVO>();
		CadastroContaCapitalRenVO vo = null;
		
		for(CadastroContaCapitalRenDTO dto : lstDTO) {
			vo = new CadastroContaCapitalRenVO();
			dtoParaVo(vo, dto);
			lst.add(vo);
		}
		
		return lst;
	}

}