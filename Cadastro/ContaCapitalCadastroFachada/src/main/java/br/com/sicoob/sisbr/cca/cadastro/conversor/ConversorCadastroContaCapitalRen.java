/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.conversor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.cca.cadastro.negocio.delegates.ContaCapitalCadastroFabricaDelegates;
import br.com.sicoob.cca.cadastro.negocio.delegates.PropostaSubscricaoDelegate;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.DocumentoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.PropostaSubscricao;
import br.com.sicoob.sisbr.cca.cadastro.vo.CadastroContaCapitalRenVO;
import br.com.sicoob.sisbr.cca.cadastro.vo.DocumentoCapitalVO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.CapesIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.InstituicaoIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.CentralCooperativaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;

/**
 * @author Marco.Nascimento
 */
public class ConversorCadastroContaCapitalRen implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4457481350482335017L;
	
	/** O atributo capesDelegate. */
	private CapesIntegracaoDelegate capesDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarCapesIntegracaoDelegate();
	
	/** O atributo instituicaoDelegate. */
	private InstituicaoIntegracaoDelegate instituicaoDelegate  = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate();	
	
	/** O atributo psDelegate. */
	private PropostaSubscricaoDelegate psDelegate = ContaCapitalCadastroFabricaDelegates.getInstance().criarPropostaSubscricaoDelegate();

	/**
	 * @param vo
	 * @param dto
	 * @throws BancoobException
	 */
	public void voParaDto(CadastroContaCapitalRenVO vo, CadastroContaCapitalRenDTO dto) throws BancoobException {
		dto.setIdContaCapital(vo.getIdContaCapital());
		dto.setIdInstituicao(vo.getIdInstituicao());
		dto.setIdPessoa(vo.getIdPessoa());
		dto.setNomePessoa(vo.getNomePessoa());
		dto.setNomeCompleto(vo.getNomeCompleto());
		dto.setCpfCnpj(vo.getCpfCnpj());
		dto.setIdPessoaLegado(vo.getIdPessoaLegado());
		dto.setNumContaCapital(vo.getNumContaCapital());
		dto.setNumContaCapitalGerada(vo.getNumContaCapitalGerada());
		dto.setVlrSubs(vo.getVlrSubs());
		dto.setVlrInteg(vo.getVlrInteg());
		dto.setQtdParcelas(vo.getQtdParcelas());
		dto.setVlrParcelas(vo.getVlrParcelas());
		dto.setDiaDebito(vo.getDiaDebito());
		dto.setTipoInteg(vo.getTipoInteg());
		dto.setNumCco(vo.getNumCco());
		dto.setIdSituacaoCadastro(vo.getIdSituacaoCadastro());
		dto.setDescSituacaoAprovacaoCapital(vo.getDescSituacaoAprovacaoCapital());
		dto.setDescSituacaoContaCapital(vo.getDescSituacaoContaCapital());
		dto.setMatriculaEscolhida(vo.getMatriculaEscolhida());
		dto.setIdAtividade(vo.getIdAtividade());
		dto.setObservacao(vo.getObservacao());
		dto.setIdSituacaoContaCapital(vo.getIdSituacaoContaCapital());
	}
	
	/**
	 * Carrega dados para VO. Atencao para registros migrados que nao tem proposta.
	 * @param ent
	 * @param vo
	 * @throws BancoobException
	 */
	public void entidadeParaVo(ContaCapital cca, CadastroContaCapitalRenVO vo) throws BancoobException {
		
		PropostaSubscricao ps = psDelegate.obter(vo.getIdContaCapital());
		if(ps != null && ps.getId() != null) {
			carregarDadosProposta(ps, vo);
		}
		
		if(cca != null) {
			carregarDadosContaCapital(cca, vo);
			carregarDadosPessoa(cca, vo);
			carregarDadosInstituicao(cca, vo);
			carregarDocumentos(cca, vo);
		}
	}
	
	private void carregarDadosProposta(PropostaSubscricao ent, CadastroContaCapitalRenVO vo) throws BancoobException {
		vo.setTipoInteg(ent.getTipoIntegralizacao().getId().intValue());
		vo.setVlrSubs(ent.getValorSubsProposta());
		vo.setVlrInteg(ent.getValorIntegProposta());
		vo.setQtdParcelas(ent.getQtdParcelaProposta());
		vo.setVlrParcelas(ent.getValorParcelaProposta());
		vo.setDiaDebito(ent.getDiaDebitoProposta());
		vo.setNumCco(ent.getNumContaCorrente());
	}
	
	private void carregarDadosContaCapital(ContaCapital cca, CadastroContaCapitalRenVO vo) throws BancoobException {
		vo.setIdContaCapital(cca.getId());
		vo.setIdInstituicao(cca.getIdInstituicao());
		vo.setIdPessoa(cca.getIdPessoa());
		vo.setNumContaCapital(cca.getNumContaCapital());
		vo.setObservacao(cca.getDescObsAprovacao());
		vo.setIdSituacaoCadastro(cca.getSituacaoCadastroProposta().getId());
		vo.setDescSituacaoAprovacaoCapital(cca.getSituacaoCadastroProposta().getDescricao());
		vo.setIdSituacaoContaCapital(cca.getSituacaoContaCapital().getId().intValue());
		vo.setDescSituacaoContaCapital(cca.getSituacaoContaCapital().getDescricao());
		vo.setMatriculaEscolhida(cca.getMatriculaEscolhida());
		String dataFormatada = DataUtil.converterDateToString(cca.getDataMatricula(), "dd/MM/yyyy");
		vo.setDataInclusao(dataFormatada);
	}
	
	private void carregarDadosPessoa(ContaCapital cca, CadastroContaCapitalRenVO vo) throws BancoobException {
		PessoaIntegracaoDTO pessoaDTO = capesDelegate.obterPessoaInstituicao(cca.getIdPessoa(), cca.getIdInstituicao());
		vo.setNomePessoa(pessoaDTO.getNomePessoa());
		vo.setNomeCompleto(pessoaDTO.getNomeCompleto());
		vo.setCpfCnpj(pessoaDTO.getCpfCnpj());
		vo.setIdPessoaLegado(pessoaDTO.getIdPessoaLegado());
	}
	
	private void carregarDadosInstituicao(ContaCapital cca, CadastroContaCapitalRenVO vo) throws BancoobException {
		CentralCooperativaDTO centralCoopDTO = instituicaoDelegate.consultarCentralCooperativa(cca.getIdInstituicao());
		vo.setDescCentral(centralCoopDTO.getNumCentral() + " - " + centralCoopDTO.getDescCentral());
		if(centralCoopDTO.getNumCooperativa() != null) {
			vo.setDescSingular(centralCoopDTO.getNumCooperativa() + " - " + centralCoopDTO.getDescCooperativa());
		} else {
			vo.setDescSingular(centralCoopDTO.getNumCentral() + " - " + centralCoopDTO.getDescCentral());
		}
	}
	
	private void carregarDocumentos(ContaCapital cca, CadastroContaCapitalRenVO vo) throws BancoobException {
		if(CollectionUtils.isNotEmpty(cca.getDocumentos())) {
			List<DocumentoCapitalVO> lstDoc = new ArrayList<DocumentoCapitalVO>(0);
			
			for(DocumentoCapital doc : cca.getDocumentos()) {
				lstDoc.add(documentoEntidadeParaVO(doc));
			}
			
			vo.setDocumentos(lstDoc);
		}
	}
	
	/**
	 * Documento entidade para vo.
	 *
	 * @param doc o valor de doc
	 * @return DocumentoCapitalVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public DocumentoCapitalVO documentoEntidadeParaVO(DocumentoCapital doc) throws BancoobException {
		DocumentoCapitalVO docVO = new DocumentoCapitalVO();
		docVO.setDataHoraAtualizacao(DataUtil.converterDateToString(doc.getDataHoraAtualizacao()));
		docVO.setIdContaCapital(doc.getContaCapital().getId());
		docVO.setIdDocumento(doc.getId());
		docVO.setIdUsuario(doc.getIdUsuario());
		docVO.setNome(doc.getNome());
		return docVO;
	}
	
	/**
	 * @param vo
	 * @param dto
	 * @throws BancoobException
	 */
	public void dtoParaVo(CadastroContaCapitalRenVO vo, CadastroContaCapitalRenDTO dto) throws BancoobException {
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
		vo.setIdOcorrenciaAtividade(dto.getIdOcorrenciaAtividade());
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
	 * @param vo
	 * @param dto
	 * @throws BancoobException
	 */
	public List<CadastroContaCapitalRenVO> lstDtoParaVo(List<CadastroContaCapitalRenDTO> lstDTO) throws BancoobException {
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