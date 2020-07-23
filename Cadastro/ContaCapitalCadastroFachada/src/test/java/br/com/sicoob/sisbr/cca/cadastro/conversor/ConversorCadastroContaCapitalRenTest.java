package br.com.sicoob.sisbr.cca.cadastro.conversor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.cadastro.negocio.delegates.PropostaSubscricaoDelegate;
import br.com.sicoob.cca.cadastro.negocio.dto.CadastroContaCapitalRenDTO;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.DocumentoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.PropostaSubscricao;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoIntegralizacao;
import br.com.sicoob.sisbr.cca.cadastro.vo.CadastroContaCapitalRenVO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.CapesIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.InstituicaoIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.CentralCooperativaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;

public class ConversorCadastroContaCapitalRenTest extends Mockito {

	private static final Integer UM = 1;
	
	@InjectMocks
	private ConversorCadastroContaCapitalRen conversor;
	
	@Mock
	private CapesIntegracaoDelegate capesDelegate;
	
	@Mock
	private InstituicaoIntegracaoDelegate instituicaoDelegate;	
	
	@Mock
	private PropostaSubscricaoDelegate psDelegate;
	
	@Before
	public void setUpClass() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void voParaDtoTest() throws BancoobException {
		CadastroContaCapitalRenDTO dto = new CadastroContaCapitalRenDTO();
		CadastroContaCapitalRenVO vo = new CadastroContaCapitalRenVO();
		vo.setIdContaCapital(UM);
		vo.setIdInstituicao(UM);
		vo.setIdPessoa(UM);
		vo.setNomePessoa("nomePessoa");
		vo.setNomeCompleto("nomeCompleto");
		vo.setCpfCnpj("cpfCnpj");
		vo.setIdPessoaLegado(UM);
		vo.setNumContaCapital(UM);
		vo.setNumContaCapitalGerada(UM);
		vo.setVlrSubs(BigDecimal.ONE);
		vo.setVlrInteg(BigDecimal.ONE);
		vo.setQtdParcelas(UM);
		vo.setVlrParcelas(BigDecimal.ONE);
		vo.setDiaDebito(UM);
		vo.setTipoInteg(UM);
		vo.setNumCco(UM.longValue());
		vo.setIdSituacaoCadastro(UM);
		vo.setDescSituacaoAprovacaoCapital("descSituacaoAprovacaoCapital");
		vo.setDescSituacaoContaCapital("descSituacaoContaCapital");
		vo.setMatriculaEscolhida(Boolean.TRUE);
		vo.setIdAtividade(UM);
		vo.setObservacao("observacao");
		vo.setIdSituacaoContaCapital(UM);
		conversor.voParaDto(vo, dto);
		assertVoDtoEquals(dto, vo);
	}
	
	@Test
	public void dtoParaVoTest() throws BancoobException {
		CadastroContaCapitalRenDTO dto = new CadastroContaCapitalRenDTO();
		CadastroContaCapitalRenVO vo = new CadastroContaCapitalRenVO();
		dto.setIdContaCapital(UM);
		dto.setIdInstituicao(UM);
		dto.setIdPessoa(UM);
		dto.setNomePessoa("nomePessoa");
		dto.setNomeCompleto("nomeCompleto");
		dto.setCpfCnpj("cpfCnpj");
		dto.setIdPessoaLegado(UM);
		dto.setNumContaCapital(UM);
		dto.setNumContaCapitalGerada(UM);
		dto.setVlrSubs(BigDecimal.ONE);
		dto.setVlrInteg(BigDecimal.ONE);
		dto.setQtdParcelas(UM);
		dto.setVlrParcelas(BigDecimal.ONE);
		dto.setDiaDebito(UM);
		dto.setTipoInteg(UM);
		dto.setNumCco(UM.longValue());
		dto.setIdSituacaoCadastro(UM);
		dto.setDescSituacaoAprovacaoCapital("descSituacaoAprovacaoCapital");
		dto.setDescSituacaoContaCapital("descSituacaoContaCapital");
		dto.setMatriculaEscolhida(Boolean.TRUE);
		dto.setIdAtividade(UM);
		dto.setObservacao("observacao");
		dto.setIdSituacaoContaCapital(UM);
		conversor.dtoParaVo(vo, dto);
		assertVoDtoEquals(dto, vo);
	}
	
	@Test
	public void lstDtoParaVoTest() throws BancoobException {
		List<CadastroContaCapitalRenDTO> lstDTO = new ArrayList<CadastroContaCapitalRenDTO>();
		CadastroContaCapitalRenDTO dto = new CadastroContaCapitalRenDTO();
		dto.setIdContaCapital(UM);
		dto.setIdInstituicao(UM);
		dto.setIdPessoa(UM);
		dto.setNomePessoa("nomePessoa");
		dto.setNomeCompleto("nomeCompleto");
		dto.setCpfCnpj("cpfCnpj");
		dto.setIdPessoaLegado(UM);
		dto.setNumContaCapital(UM);
		dto.setNumContaCapitalGerada(UM);
		dto.setVlrSubs(BigDecimal.ONE);
		dto.setVlrInteg(BigDecimal.ONE);
		dto.setQtdParcelas(UM);
		dto.setVlrParcelas(BigDecimal.ONE);
		dto.setDiaDebito(UM);
		dto.setTipoInteg(UM);
		dto.setNumCco(UM.longValue());
		dto.setIdSituacaoCadastro(UM);
		dto.setDescSituacaoAprovacaoCapital("descSituacaoAprovacaoCapital");
		dto.setDescSituacaoContaCapital("descSituacaoContaCapital");
		dto.setMatriculaEscolhida(Boolean.TRUE);
		dto.setIdAtividade(UM);
		dto.setObservacao("observacao");
		dto.setIdSituacaoContaCapital(UM);
		lstDTO.add(dto);
		List<CadastroContaCapitalRenVO> lstVO = conversor.lstDtoParaVo(lstDTO);
		Assert.assertNotNull(lstVO);
		Assert.assertFalse(lstVO.isEmpty());
		assertVoDtoEquals(lstDTO.get(0), lstVO.get(0));
	}

	private void assertVoDtoEquals(CadastroContaCapitalRenDTO dto, CadastroContaCapitalRenVO vo) {
		Assert.assertEquals(dto.getIdContaCapital(), vo.getIdContaCapital());
		Assert.assertEquals(dto.getIdInstituicao(), vo.getIdInstituicao());
		Assert.assertEquals(dto.getIdPessoa(), vo.getIdPessoa());
		Assert.assertEquals(dto.getNomePessoa(), vo.getNomePessoa());
		Assert.assertEquals(dto.getNomeCompleto(), vo.getNomeCompleto());
		Assert.assertEquals(dto.getCpfCnpj(), vo.getCpfCnpj());
		Assert.assertEquals(dto.getIdPessoaLegado(), vo.getIdPessoaLegado());
		Assert.assertEquals(dto.getNumContaCapital(), vo.getNumContaCapital());
		Assert.assertEquals(dto.getNumContaCapitalGerada(), vo.getNumContaCapitalGerada());
		Assert.assertEquals(dto.getVlrSubs(), vo.getVlrSubs());
		Assert.assertEquals(dto.getVlrInteg(), vo.getVlrInteg());
		Assert.assertEquals(dto.getQtdParcelas(), vo.getQtdParcelas());
		Assert.assertEquals(dto.getVlrParcelas(), vo.getVlrParcelas());
		Assert.assertEquals(dto.getDiaDebito(), vo.getDiaDebito());
		Assert.assertEquals(dto.getTipoInteg(), vo.getTipoInteg());
		Assert.assertEquals(dto.getNumCco(), vo.getNumCco());
		Assert.assertEquals(dto.getIdSituacaoCadastro(), vo.getIdSituacaoCadastro());
		Assert.assertEquals(dto.getDescSituacaoAprovacaoCapital(), vo.getDescSituacaoAprovacaoCapital());
		Assert.assertEquals(dto.getDescSituacaoContaCapital(), vo.getDescSituacaoContaCapital());
		Assert.assertEquals(dto.getMatriculaEscolhida(), vo.getMatriculaEscolhida());
		Assert.assertEquals(dto.getIdAtividade(), vo.getIdAtividade());
		Assert.assertEquals(dto.getObservacao(), vo.getObservacao());
		Assert.assertEquals(dto.getIdSituacaoContaCapital(), vo.getIdSituacaoContaCapital());
	}
	
	@Test
	public void documentoEntidadeParaVOTest() throws BancoobException {
		DocumentoCapital doc = new DocumentoCapital();
		doc.setDataHoraAtualizacao(new DateTimeDB());
		doc.setContaCapital(new ContaCapital());
		Assert.assertNotNull(conversor.documentoEntidadeParaVO(doc));
	}
	
	@Test
	public void entidadeParaVoTest() throws BancoobException {
		when(capesDelegate.obterPessoaInstituicao(anyInt(), anyInt())).thenReturn(new PessoaIntegracaoDTO());
		
		when(instituicaoDelegate.consultarCentralCooperativa(anyInt())).thenReturn(new CentralCooperativaDTO());
		
		PropostaSubscricao proposta = new PropostaSubscricao();
		proposta.setId(UM);
		TipoIntegralizacao tipoIntegralizacao = new TipoIntegralizacao();
		tipoIntegralizacao.setId(UM.shortValue());
		proposta.setTipoIntegralizacao(tipoIntegralizacao);
		when(psDelegate.obter(anyInt())).thenReturn(proposta);
		
		ContaCapital cca = new ContaCapital();
		SituacaoCadastroProposta situacaoCadastroProposta = new SituacaoCadastroProposta();
		situacaoCadastroProposta.setId(UM);
		cca.setSituacaoCadastroProposta(situacaoCadastroProposta);
		SituacaoContaCapital situacaoContaCapital = new SituacaoContaCapital();
		situacaoContaCapital.setId(UM.shortValue());
		cca.setSituacaoContaCapital(situacaoContaCapital);
		cca.setDataMatricula(new DateTimeDB());
		DocumentoCapital doc = new DocumentoCapital();
		doc.setDataHoraAtualizacao(new DateTimeDB());
		doc.setContaCapital(new ContaCapital());
		List<DocumentoCapital> documentos = new ArrayList<DocumentoCapital>();
		documentos.add(doc);
		cca.setDocumentos(documentos);
		conversor.entidadeParaVo(cca, new CadastroContaCapitalRenVO());
	}
	
}
