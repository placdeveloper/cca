package br.com.sicoob.sisbr.cca.movimentacao.servicos;

import java.math.BigDecimal;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorCotaDelegate;
import br.com.sicoob.cca.cadastro.negocio.excecao.ContaCapitalCadastroNegocioException;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.TransferenciaContaCapitalDelegate;
import br.com.sicoob.cca.movimentacao.negocio.dto.TransferenciaRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoException;
import br.com.sicoob.cca.movimentacao.negocio.excecao.ContaCapitalMovimentacaoNegocioException;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.cca.movimentacao.MovimentacaoContaCapital;

/**
 * A Classe TransferenciaContaCapitalServico.
 *
 * @author Antonio.Genaro
 */
@RemoteService
public class TransferenciaContaCapitalServico extends MovimentacaoContaCapital {

	/** O atributo contaCapitalDelegate. */
	private ContaCapitalDelegate contaCapitalDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarContaCapitalDelegate();	
	
	/** O atributo valorCotaDelegate. */
	private ValorCotaDelegate valorCotaDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarValorCotaDelegate();	
	
	/** O atributo transferenciaContaCapitalDelegate. */
	private TransferenciaContaCapitalDelegate transferenciaContaCapitalDelegate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarTransferenciaContaCapitalDelegate();
	
	/** A constante MSG_DADOS_GRAVADOS. */
	private static final String MSG_DADOS_GRAVADOS = "Dados gravados com sucesso.";
	
	/**
	 * Obter dados transferencia.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDadosTransferencia(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();	
		
		Integer idContaCapital = Integer.valueOf(reqDTO.getDados().get("idContaCapital").toString());
		
		ContaCapital contaCapital = contaCapitalDelegate.obter(idContaCapital);		
		BigDecimal valorMinimoSubscricao = valorCotaDelegate.obterValorMinimoSubscricao(contaCapital.getIdInstituicao(), contaCapital.getIdPessoa());
		
		retornoDTO.getDados().put("vlrIntegralizado", calcularValorIntegralizado(idContaCapital));	
		retornoDTO.getDados().put("vlrMinimoExigido", valorMinimoSubscricao);			
		retornoDTO.getDados().put("idSituacaoContaCapital", contaCapital.getSituacaoContaCapital().getId());			
		retornoDTO.getDados().put("vlrBloqueado", calcularValorBloqueado(idContaCapital));
			
		return retornoDTO;
	}		
	
	/**
	 * Incluir.
	 *
	 * @param reqDTO o valor de req dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO incluir(RequisicaoReqDTO reqDTO) throws BancoobException {
		RetornoDTO retornoDTO = new RetornoDTO();
		String msg = "msg";
		
		try {			
			TransferenciaRenDTO transferenciaRenDTO = montarTransferenciaDTO(reqDTO);						
			transferenciaContaCapitalDelegate.incluir(transferenciaRenDTO);						
			
		} catch (ContaCapitalMovimentacaoNegocioException e) {
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(),e);
		}catch (ContaCapitalCadastroNegocioException e) {
			throw new ContaCapitalMovimentacaoNegocioException(e.getMessage(),e);			
		} catch (ContaCapitalMovimentacaoException e) {
			throw new ContaCapitalMovimentacaoException(e.getMessage(),e);			
		}catch (BancoobException e) {
			SicoobLoggerPadrao.getInstance(getClass()).erro(e, e.getMessage());
			throw new ContaCapitalMovimentacaoException("MSG_001", e);
		}		
				
		retornoDTO.getDados().put(msg, MSG_DADOS_GRAVADOS);
		
		return retornoDTO;
	}		
	

	/**
	 * Montar transferencia dto.
	 *
	 * @param reqDTO o valor de req dto
	 * @return TransferenciaRenDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private TransferenciaRenDTO montarTransferenciaDTO(RequisicaoReqDTO reqDTO) throws BancoobException {				
		TransferenciaRenDTO dto = new TransferenciaRenDTO();		
		
		dto.setIdContaCapitalDebito(Integer.valueOf(reqDTO.getDados().get("idContaCapitalDebito").toString()));
		dto.setIdInstituicaoDebito(Integer.valueOf(reqDTO.getDados().get("idInstituicaoDebito").toString()));
		dto.setNumContaCapitalDebito(Integer.valueOf(reqDTO.getDados().get("numContaCapitalDebito").toString()));
		
		dto.setIdContaCapitalCredito(Integer.valueOf(reqDTO.getDados().get("idContaCapitalCredito").toString()));
		dto.setIdInstituicaoCredito(Integer.valueOf(reqDTO.getDados().get("idInstituicaoCredito").toString()));
		dto.setNumContaCapitalCredito(Integer.valueOf(reqDTO.getDados().get("numContaCapitalCredito").toString()));
		
		dto.setVlrTransferir(new BigDecimal(reqDTO.getDados().get("vlrTransferir").toString()));
		
		return dto;		
	}

}
