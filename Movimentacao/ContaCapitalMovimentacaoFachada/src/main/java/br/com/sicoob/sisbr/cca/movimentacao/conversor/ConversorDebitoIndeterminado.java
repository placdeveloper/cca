package br.com.sicoob.sisbr.cca.movimentacao.conversor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorConfiguracaoCapitalDelegate;
import br.com.sicoob.cca.cadastro.negocio.delegates.ValorCotaDelegate;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.DebitoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoValorDebito;
import br.com.sicoob.cca.movimentacao.negocio.dto.ConsultaDebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.DebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.movimentacao.negocio.dto.QuadroGeralAssociadoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.CapesIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.FonteRendaPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.ConsultaDebitoIndeterminadoRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.DebitoIndeterminadoRenVO;
import br.com.sicoob.sisbr.cca.movimentacao.vo.QuadroGeralAssociadoVO;

/**
 * @author marco.nascimento
 */
public class ConversorDebitoIndeterminado implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** O atributo capesIntDelegate. */
	private CapesIntegracaoDelegate capesIntDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarCapesIntegracaoDelegate();
	
	/** O atributo valorCotaDelegate. */
	private ValorCotaDelegate valorCotaDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarValorCotaDelegate();
	
	private ValorConfiguracaoCapitalDelegate valorConfDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarValorConfiguracaoCapitalDelegate();

	/**
	 * Converte DTO para VO para apresentacao na view
	 * @param lstDTO
	 * @return List VO Quadro Geral Associados
	 */
	public List<QuadroGeralAssociadoVO> converterQuadroGeralAssociado(List<QuadroGeralAssociadoDTO> lstDTO) {
		List<QuadroGeralAssociadoVO> lstRetorno = new ArrayList<QuadroGeralAssociadoVO>();
		QuadroGeralAssociadoVO vo;
		
		Integer somaQtdAssociadosComDebito = 0;
		Integer somaQtdAssociadosSemDebito = 0;
		Integer somaAssociados = 0;
		
		for(QuadroGeralAssociadoDTO dto : lstDTO) {
			vo = new QuadroGeralAssociadoVO();
			vo.setTipoPessoa(dto.getTipoPessoa());
			vo.setQtdPessoasComDebito(dto.getQtdPessoasComDebito());
			vo.setQtdPessoasSemDebito(dto.getQtdPessoasSemDebito());
			vo.setTotalAssociados(dto.getTotalAssociados());
			
			vo.setDia(dto.getDia());
			vo.setQtdPorDiaFixo(dto.getQtdPorDiaFixo());
			vo.setValorTotalPorDiaFixo(dto.getValorTotalPorDiaFixo());
			
			vo.setIntervaloDias(dto.getIntervaloDias());
			vo.setQtdIntervalo(dto.getQtdIntervalo());
			vo.setValorTotalIntervalo(dto.getValorTotalIntervalo());
			
			vo.setDescFormaCalculo(dto.getDescFormaCalculo());
			vo.setQtdDebitos(dto.getQtdDebitos());
			
			lstRetorno.add(vo);
			
			somaQtdAssociadosComDebito += dto.getQtdPessoasComDebito();
			somaQtdAssociadosSemDebito += dto.getQtdPessoasSemDebito();
			somaAssociados += dto.getTotalAssociados();
		}
		
		vo = new QuadroGeralAssociadoVO();
		vo.setTipoPessoa("TOTAL");
		vo.setQtdPessoasComDebito(somaQtdAssociadosComDebito);
		vo.setQtdPessoasSemDebito(somaQtdAssociadosSemDebito);
		vo.setTotalAssociados(somaAssociados);
		
		lstRetorno.add(vo);
		
		return lstRetorno;
	}
	
	/**
	 * Converte lista de DTO para VO
	 * @param lstDTO
	 * @return
	 */
	public List<QuadroGeralAssociadoVO> converterDTOparaVO(List<QuadroGeralAssociadoDTO> lstDTO) {
		List<QuadroGeralAssociadoVO> lstRetorno = new ArrayList<QuadroGeralAssociadoVO>();
		QuadroGeralAssociadoVO vo;
		
		for(QuadroGeralAssociadoDTO dto : lstDTO) {
			vo = new QuadroGeralAssociadoVO();
			vo.setTipoPessoa(dto.getTipoPessoa());
			vo.setQtdPessoasComDebito(dto.getQtdPessoasComDebito());
			vo.setQtdPessoasSemDebito(dto.getQtdPessoasSemDebito());
			vo.setTotalAssociados(dto.getTotalAssociados());
			
			vo.setDia(dto.getDia());
			vo.setQtdPorDiaFixo(dto.getQtdPorDiaFixo());
			vo.setValorTotalPorDiaFixo(dto.getValorTotalPorDiaFixo());
			
			vo.setIntervaloDias(dto.getIntervaloDias());
			vo.setQtdIntervalo(dto.getQtdIntervalo());
			vo.setValorTotalIntervalo(dto.getValorTotalIntervalo());
			
			vo.setFormaCalculo(dto.getFormaCalculo());
			vo.setDescFormaCalculo(dto.getDescFormaCalculo());
			vo.setQtdDebitos(dto.getQtdDebitos());
			
			lstRetorno.add(vo);
		}
		
		return lstRetorno;
	}
	
	/**
	 * Converte List<ConsultaDebitoIndeterminadoRenDTO> para List<ConsultaDebitoIndeterminadoRenVO>
	 * @param lstDTO
	 * @return
	 */
	public List<ConsultaDebitoIndeterminadoRenVO> converterListDTOparaListVO(List<ConsultaDebitoIndeterminadoRenDTO> lstDTO) {
		List<ConsultaDebitoIndeterminadoRenVO> lstRetorno = new ArrayList<ConsultaDebitoIndeterminadoRenVO>();
		ConsultaDebitoIndeterminadoRenVO vo;
		
		for(ConsultaDebitoIndeterminadoRenDTO dto : lstDTO) {
			vo = new ConsultaDebitoIndeterminadoRenVO();
			vo.setIdDebitoContaCapital(dto.getIdDebitoContaCapital());
			vo.setIdContaCapital(dto.getIdContaCapital());
			vo.setNumContaCapital(dto.getNumContaCapital());
			vo.setNumContaCorrente(dto.getNumContaCorrente());
			vo.setDiaDebito(dto.getDiaDebito());
			vo.setNome(dto.getNome());
			vo.setTipoPessoa(dto.getTipoPessoa());
			vo.setFormaDebito(dto.getFormaDebito());
			vo.setValor(dto.getValor());
			vo.setDataPeriodoDeb(dto.getDataPeriodoDeb());
			vo.setCpfCnpj(dto.getCpfCnpj());
			vo.setIdTipoValorDebito(dto.getIdTipoValorDebito());
			vo.setIdTipoIntegralizacao(dto.getIdTipoInteg());
			vo.setSelecionado(null);
			vo.setNomeEmpresa(dto.getNomeEmpresa());
			vo.setNumMatriculaFunc(dto.getNumMatriculaFunc());
			lstRetorno.add(vo);
		}
		
		return lstRetorno;
	}
	
	/**
	 * ConsultaDebitoIndeterminadoVO para ConsultaDebitoIndeterminadoDTO
	 * @param vo
	 * @return
	 */
	public ConsultaDebitoIndeterminadoRenDTO converterVOparaDTO(ConsultaDebitoIndeterminadoRenVO vo) {
		ConsultaDebitoIndeterminadoRenDTO dto = new ConsultaDebitoIndeterminadoRenDTO();
		dto.setIdDebitoContaCapital(vo.getIdDebitoContaCapital());
		dto.setNumContaCapital(vo.getNumContaCapital());
		dto.setDiaDebito(vo.getDiaDebito());
		dto.setNome(vo.getNome());
		dto.setTipoPessoa(vo.getTipoPessoa());
		dto.setIdTipoPessoa(vo.getIdTipoPessoa());
		dto.setFormaDebito(vo.getFormaDebito());
		dto.setIdFormaDebito(vo.getIdFormaDebito());
		dto.setValor(vo.getValor());
		dto.setDataPeriodoDeb(vo.getDataPeriodoDeb());
		dto.setCpfCnpj(vo.getCpfCnpj());
		dto.setSelecionado(vo.getSelecionado());
		return dto;
	}
	
	/**
	 * List<ConsultaDebitoIndeterminadoRenVO> para List<ConsultaDebitoIndeterminadoRenDTO> 
	 * @param vo
	 * @return
	 */
	public List<ConsultaDebitoIndeterminadoRenDTO> converterListVOparaListDTO(List<ConsultaDebitoIndeterminadoRenVO> lstVO) {
		List<ConsultaDebitoIndeterminadoRenDTO> lst = new ArrayList<ConsultaDebitoIndeterminadoRenDTO>();
		for(ConsultaDebitoIndeterminadoRenVO vo : lstVO) {
			lst.add(converterVOparaDTO(vo));
		}
		return lst;
	}
	
	/**
	 * DebitoIndeterminadoRenVO para DebitoIndeterminadoRenDTO 
	 * @param vo
	 * @return
	 */
	public DebitoIndeterminadoRenDTO converterVOparaDTO(DebitoIndeterminadoRenVO vo) {
		DebitoIndeterminadoRenDTO dto = new DebitoIndeterminadoRenDTO();
		dto.setIdDebitoContaCapital(vo.getIdDebitoContaCapital());
		dto.setTipoInclusao(vo.getTipoInclusao());
		dto.setIdContaCapital(vo.getIdContaCapital());
		dto.setIdInstituicao(vo.getIdInstituicao());
		dto.setNumContaCapital(vo.getNumContaCapital());
		dto.setIdPessoaLegado(vo.getIdPessoaLegado());
		dto.setIdPessoa(vo.getIdPessoa());
		dto.setTipoInteg(vo.getTipoInteg());
		dto.setNumContaCorrente(vo.getNumContaCorrente());
		dto.setFormaDebito(vo.getFormaDebito());
		dto.setQtdCotas(vo.getQtdCotas());
		dto.setPercentual(vo.getPercentual());
		dto.setVlrDebito(vo.getVlrDebito());
		dto.setPeriodoDebito(vo.getPeriodoDebito());
		dto.setDataInicialDeb(vo.getDataInicialDeb());
		dto.setNumPeriodo(vo.getNumPeriodo());
		dto.setIdsContaCapital(vo.getIdsContaCapital());
		dto.setIdsNumMatricula(vo.getIdsNumMatricula());
		dto.setIdsDebitoContaCapital(vo.getIdsDebitoContaCapital());
		dto.setContasCorrente(vo.getContasCorrente());
		return dto;
	}
	
	/**
	 * DebitoContaCapital para DebitoIndeterminadoRenVO 
	 * @param debCCA
	 * @return DebitoIndeterminadoRenVO
	 * @throws BancoobException 
	 */
	public DebitoIndeterminadoRenVO converterEntidadeparaVO(DebitoContaCapital debCCA) throws BancoobException {
		DebitoIndeterminadoRenVO vo = new DebitoIndeterminadoRenVO();
		vo.setIdDebitoContaCapital(debCCA.getId());
		vo.setIdContaCapital(debCCA.getContaCapital().getId());
		vo.setIdInstituicao(debCCA.getIdInstituicao());
		vo.setNumContaCapital(debCCA.getContaCapital().getNumContaCapital());
		vo.setIdPessoa(debCCA.getContaCapital().getIdPessoa());
		vo.setTipoInteg(debCCA.getTipoIntegralizacao().getId().intValue());
		vo.setFormaDebito(debCCA.getTipoValorDebito().getId().intValue());
		vo.setNumContaCorrente(debCCA.getNumContaCorrente());
		vo.setPeriodoDebito(debCCA.getTipoPeriodoDebito().getId().intValue());
		vo.setDataInicialDeb(debCCA.getDataVencimentoDebito());
		vo.setNumPeriodo(debCCA.getNumPeriodo().intValue());
		
		PessoaIntegracaoDTO pessoaVO = capesIntDelegate.obterPessoaInstituicao(debCCA.getContaCapital().getIdPessoa(), debCCA.getContaCapital().getIdInstituicao());
		vo.setIdPessoaLegado(pessoaVO.getIdPessoaLegado());
		vo.setNome(pessoaVO.getNomeCompleto());
		vo.setCodTipoPessoa(pessoaVO.getCodTipoPessoa().intValue());
		
		if(EnumTipoValorDebito.COD_TIPO_DEB_VALOR.getCodigo().equals(debCCA.getTipoValorDebito().getId().intValue())) {
			vo.setVlrDebito(debCCA.getValorDebito());
		}
		
		if(EnumTipoValorDebito.COD_TIPO_DEB_QTD_COTAS.getCodigo().equals(debCCA.getTipoValorDebito().getId().intValue())) {
			vo.setQtdCotas(debCCA.getValorDebito());
			BigDecimal vlrCota = valorCotaDelegate.obterValorCota(debCCA.getIdInstituicao());
			BigDecimal valorDebito = vlrCota.multiply(debCCA.getValorDebito());
			vo.setVlrDebito(valorDebito);
		}
		
		if(EnumTipoValorDebito.COD_TIPO_DEB_PERC_SALARIO_BASE.getCodigo().equals(debCCA.getTipoValorDebito().getId().intValue())) {
			vo.setPercentual(debCCA.getValorDebito());
			ValorConfiguracaoCapital valorConf = valorConfDelegate.obterValorConfiguracao(ContaCapitalConstantes.PAR_VALOR_SALARIO_BASE, debCCA.getIdInstituicao());
			BigDecimal valor = new BigDecimal(valorConf.getValorConfiguracao());
			valor = valor.multiply(debCCA.getValorDebito()).divide(new BigDecimal(ContaCapitalConstantes.NUMERO_CEM), 2, RoundingMode.HALF_UP);
			vo.setVlrDebito(valor);
		}
		
		if(EnumTipoValorDebito.COD_TIPO_DEB_PERC_SALARIO_RENDA.getCodigo().equals(debCCA.getTipoValorDebito().getId().intValue())) {
			vo.setPercentual(debCCA.getValorDebito());
			FonteRendaPessoaIntegracaoDTO frDTO = capesIntDelegate.obterFonteRendaPessoaInstituicao(debCCA.getContaCapital().getIdPessoa(), debCCA.getIdInstituicao());
			if(frDTO != null && frDTO.getRendaMensal() != null) {
				BigDecimal valor = frDTO.getRendaMensal().multiply(debCCA.getValorDebito()).divide(new BigDecimal(ContaCapitalConstantes.NUMERO_CEM), 2, RoundingMode.HALF_UP);
				vo.setVlrDebito(valor);
			}
		}
		
		return vo;
	}
}
