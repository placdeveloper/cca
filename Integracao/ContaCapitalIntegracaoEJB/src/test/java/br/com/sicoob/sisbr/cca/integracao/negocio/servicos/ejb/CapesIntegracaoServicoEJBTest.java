/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.AnotacaoPessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.BemPessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.ClienteDelegate;
import br.com.sicoob.capes.api.negocio.delegates.EnderecoPessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.FonteRendaPessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.PessoaFisicaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.PessoaJuridicaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.ReferenciaPessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.RelacionamentoPessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.TelefonePessoaDelegate;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.BemPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.ClienteVO;
import br.com.sicoob.capes.api.negocio.vo.EnderecoPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.FonteRendaPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaFisicaVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaJuridicaVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.api.negocio.vo.ReferenciaPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.RelacionamentoPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.TelefonePessoaVO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.CapesIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.AnotacaoPessoaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.BemPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.EnderecoPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.FonteRendaPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaFisicaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaJuridicaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ReferenciaPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.RelacionamentoPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.TelefonePessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoCapesNegocioException;
import br.com.sicoob.sisbr.localidade.api.filtro.LocApiFabricaFiltro;
import br.com.sicoob.sisbr.localidade.api.filtro.LocApiFiltroTipoLogradouro;
import br.com.sicoob.sisbr.localidade.api.negocio.delegates.LocApiTipoLogradouroDelegate;

public class CapesIntegracaoServicoEJBTest {

	@Test
	public void testObterPessoaInstituicao() throws BancoobException {
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		PessoaVO pessoaVO = new PessoaVO(); 
		
		PessoaDelegate mockPessoaDelegate = EasyMock.createMock(PessoaDelegate.class);
		EasyMock.expect(mockPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(pessoaVO);
		
		ejb.setPessoaDelegate(mockPessoaDelegate);
		
		EasyMock.replay(mockPessoaDelegate);
		PessoaIntegracaoDTO pessoa = ejb.obterPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(pessoa);
		EasyMock.verify(mockPessoaDelegate);
	}	
	
	@Ignore
	public void testObterPessoaInstituicaoError() throws BancoobException {
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		
		boolean exception = true;
		try{
			ejb.obterPessoaInstituicao(idPessoa, idInstituicao);
			Assert.fail("Erro");
		}catch(Exception e){
			exception = false;
			Assert.assertEquals(IntegracaoCapesNegocioException.class, e.getClass());
		}
		Assert.assertFalse(exception);
	}	
	
	@Test
	public void testObterPessoaInstituicaoNull() throws BancoobException {
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		
		PessoaDelegate mockPessoaDelegate = EasyMock.createMock(PessoaDelegate.class);
		EasyMock.expect(mockPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(null);
		
		ejb.setPessoaDelegate(mockPessoaDelegate);
		
		EasyMock.replay(mockPessoaDelegate);
		PessoaIntegracaoDTO pessoa = ejb.obterPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(pessoa);
		EasyMock.verify(mockPessoaDelegate);
	}	
	
	@Test
	public void testObterPessoaFisicaInstituicao() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		PessoaFisicaVO pessoaFisicaVO = new PessoaFisicaVO(); 
		
		PessoaFisicaDelegate mockPessoaFisicaDelegate = EasyMock.createMock(PessoaFisicaDelegate.class);
		EasyMock.expect(mockPessoaFisicaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(pessoaFisicaVO);
		
		ejb.setPessoaFisicaDelegate(mockPessoaFisicaDelegate);
		
		EasyMock.replay(mockPessoaFisicaDelegate);
		PessoaFisicaIntegracaoDTO pessoa = ejb.obterPessoaFisicaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(pessoa);
		EasyMock.verify(mockPessoaFisicaDelegate);
	}
	
	@Ignore
	public void testObterPessoaFisicaInstituicaoErro() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		
		boolean exception = true;
		try{
			ejb.obterPessoaFisicaInstituicao(idPessoa, idInstituicao);
			Assert.fail("Erro");
		}catch(Exception e){
			exception = false;
			Assert.assertEquals(IntegracaoCapesNegocioException.class, e.getClass());
		}
		Assert.assertFalse(exception);
	}
	
	@Test
	public void testObterPessoaFisicaInstituicaoNull() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		
		PessoaFisicaDelegate mockPessoaFisicaDelegate = EasyMock.createMock(PessoaFisicaDelegate.class);
		EasyMock.expect(mockPessoaFisicaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(null);
		
		ejb.setPessoaFisicaDelegate(mockPessoaFisicaDelegate);
		
		EasyMock.replay(mockPessoaFisicaDelegate);
		PessoaFisicaIntegracaoDTO pessoa = ejb.obterPessoaFisicaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(pessoa);
		EasyMock.verify(mockPessoaFisicaDelegate);
	}
	
	public void testObterEnderecoPessoaInstituicao() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		EnderecoPessoaVO enderecoPessoaVO = new EnderecoPessoaVO(); 
		enderecoPessoaVO.setIdTipoLogradouro(Short.valueOf("1"));
		
		List<EnderecoPessoaVO> lstEnderecoVO = new ArrayList();
		
		EnderecoPessoaDelegate mockEnderecoPessoaDelegate = EasyMock.createMock(EnderecoPessoaDelegate.class);
		EasyMock.expect(mockEnderecoPessoaDelegate.obterEnderecoCorrespondenciaPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(enderecoPessoaVO);
		EasyMock.expect(mockEnderecoPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstEnderecoVO);
		
		LocApiFiltroTipoLogradouro filtroLog = new LocApiFabricaFiltro().createLocApiFiltroTipoLogradouro();
		filtroLog.setId(enderecoPessoaVO.getIdTipoLogradouro().intValue());
		String logradouro = "sad";
		
		LocApiTipoLogradouroDelegate mockLocApiTipoLogradouroDelegate = EasyMock.createMock(LocApiTipoLogradouroDelegate.class);
		EasyMock.expect(mockLocApiTipoLogradouroDelegate.pesquisarTiposLogradouro(filtroLog).get(0).getDescricao()).andReturn(logradouro);
		
		ejb.setEnderecoPessoaDelegate(mockEnderecoPessoaDelegate);
		ejb.setLocApiTipoLogradouroDelegate(mockLocApiTipoLogradouroDelegate);
		
		EasyMock.replay(mockEnderecoPessoaDelegate, mockLocApiTipoLogradouroDelegate);
		EnderecoPessoaIntegracaoDTO endereco = ejb.obterEnderecoPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(endereco);
		EasyMock.verify(mockEnderecoPessoaDelegate, mockLocApiTipoLogradouroDelegate);
	}
	
	@Test
	public void testObterTelefonePessoaInstituicao() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<TelefonePessoaVO> lstTelefonePessoaVO = new ArrayList();
		
		TelefonePessoaVO tel1 = new TelefonePessoaVO();
		tel1.setTelefone("123");
		tel1.setRamal("123");
		tel1.setIdTelefone(Long.valueOf("1"));
		tel1.setIdInstituicao(123);
		tel1.setDescricaoTipoTelefone("casa");
		tel1.setDdd("ddd");
		tel1.setDataHoraInicio(new Date());
		tel1.setCpfCnpj("cpfCnpj");
		tel1.setCodigoTipoTelefone(Short.valueOf("0"));
		
		TelefonePessoaVO tel2 = new TelefonePessoaVO();
		tel2.setTelefone("123");
		tel2.setRamal("123");
		tel2.setIdTelefone(Long.valueOf("1"));
		tel2.setIdInstituicao(123);
		tel2.setDescricaoTipoTelefone("casa");
		tel2.setDdd("ddd");
		tel2.setDataHoraInicio(new Date());
		tel2.setCpfCnpj("cpfCnpj");
		tel2.setCodigoTipoTelefone(Short.valueOf("1"));
		
		TelefonePessoaVO tel3 = new TelefonePessoaVO();
		
		lstTelefonePessoaVO.add(tel1);
		lstTelefonePessoaVO.add(tel2);
		lstTelefonePessoaVO.add(tel3);

		TelefonePessoaDelegate mockTelefonePessoaDelegate = EasyMock.createMock(TelefonePessoaDelegate.class);
		EasyMock.expect(mockTelefonePessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstTelefonePessoaVO);
		
		ejb.setTelefonePessoaDelegate(mockTelefonePessoaDelegate);
		
		EasyMock.replay(mockTelefonePessoaDelegate);
		TelefonePessoaIntegracaoDTO telefone = ejb.obterTelefonePessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(telefone);
		EasyMock.verify(mockTelefonePessoaDelegate);
	}
	
	@Test
	public void testObterTelefonePessoaInstituicao2() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<TelefonePessoaVO> lstTelefonePessoaVO = new ArrayList();
		
		TelefonePessoaVO tel1 = new TelefonePessoaVO();
		tel1.setTelefone("123");
		tel1.setRamal("123");
		tel1.setIdTelefone(Long.valueOf("1"));
		tel1.setIdInstituicao(123);
		tel1.setDescricaoTipoTelefone("casa");
		tel1.setDdd(null);
		tel1.setDataHoraInicio(new Date());
		tel1.setCpfCnpj("cpfCnpj");
		tel1.setCodigoTipoTelefone(Short.valueOf("0"));
		
		TelefonePessoaVO tel2 = new TelefonePessoaVO();
		tel2.setTelefone(null);
		tel2.setRamal("123");
		tel2.setIdTelefone(Long.valueOf("1"));
		tel2.setIdInstituicao(123);
		tel2.setDescricaoTipoTelefone("casa");
		tel2.setDdd("ddd");
		tel2.setDataHoraInicio(new Date());
		tel2.setCpfCnpj("cpfCnpj");
		tel2.setCodigoTipoTelefone(Short.valueOf("1"));
		
		TelefonePessoaVO tel3 = new TelefonePessoaVO();
		
		lstTelefonePessoaVO.add(tel1);
		lstTelefonePessoaVO.add(tel2);
		lstTelefonePessoaVO.add(tel3);

		TelefonePessoaDelegate mockTelefonePessoaDelegate = EasyMock.createMock(TelefonePessoaDelegate.class);
		EasyMock.expect(mockTelefonePessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstTelefonePessoaVO);
		
		ejb.setTelefonePessoaDelegate(mockTelefonePessoaDelegate);
		
		EasyMock.replay(mockTelefonePessoaDelegate);
		TelefonePessoaIntegracaoDTO telefone = ejb.obterTelefonePessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(telefone);
		EasyMock.verify(mockTelefonePessoaDelegate);
	}
	
	@Test
	public void testObterTelefonePessoaInstituicao3() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<TelefonePessoaVO> lstTelefonePessoaVO = new ArrayList();
		
		TelefonePessoaVO tel1 = new TelefonePessoaVO();
		tel1.setTelefone(null);
		tel1.setRamal("123");
		tel1.setIdTelefone(Long.valueOf("1"));
		tel1.setIdInstituicao(123);
		tel1.setDescricaoTipoTelefone("casa");
		tel1.setDdd("123");
		tel1.setDataHoraInicio(new Date());
		tel1.setCpfCnpj("cpfCnpj");
		tel1.setCodigoTipoTelefone(Short.valueOf("0"));
		
		TelefonePessoaVO tel2 = new TelefonePessoaVO();
		tel2.setTelefone(null);
		tel2.setRamal("123");
		tel2.setIdTelefone(Long.valueOf("1"));
		tel2.setIdInstituicao(123);
		tel2.setDescricaoTipoTelefone("casa");
		tel2.setDdd(null);
		tel2.setDataHoraInicio(new Date());
		tel2.setCpfCnpj("cpfCnpj");
		tel2.setCodigoTipoTelefone(Short.valueOf("1"));
		
		TelefonePessoaVO tel3 = new TelefonePessoaVO();
		
		lstTelefonePessoaVO.add(tel1);
		lstTelefonePessoaVO.add(tel2);
		lstTelefonePessoaVO.add(tel3);

		TelefonePessoaDelegate mockTelefonePessoaDelegate = EasyMock.createMock(TelefonePessoaDelegate.class);
		EasyMock.expect(mockTelefonePessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstTelefonePessoaVO);
		
		ejb.setTelefonePessoaDelegate(mockTelefonePessoaDelegate);
		
		EasyMock.replay(mockTelefonePessoaDelegate);
		TelefonePessoaIntegracaoDTO telefone = ejb.obterTelefonePessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(telefone);
		EasyMock.verify(mockTelefonePessoaDelegate);
	}
	
	@Test
	public void testObterTelefonePessoaInstituicaoEmpty() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<TelefonePessoaVO> lstTelefonePessoaVO = new ArrayList();

		TelefonePessoaDelegate mockTelefonePessoaDelegate = EasyMock.createMock(TelefonePessoaDelegate.class);
		EasyMock.expect(mockTelefonePessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstTelefonePessoaVO);
		
		ejb.setTelefonePessoaDelegate(mockTelefonePessoaDelegate);
		
		EasyMock.replay(mockTelefonePessoaDelegate);
		TelefonePessoaIntegracaoDTO telefone = ejb.obterTelefonePessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(telefone);
		EasyMock.verify(mockTelefonePessoaDelegate);
	}
	
	@Test
	public void testObterFonteRendaPessoaInstituicao() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<FonteRendaPessoaVO> lstfonteRendaVO = new ArrayList();
		FonteRendaPessoaVO fonteRendaPessoaVO = new FonteRendaPessoaVO();
		lstfonteRendaVO.add(fonteRendaPessoaVO);
		
		FonteRendaPessoaDelegate mockFonteRendaPessoaDelegate = EasyMock.createMock(FonteRendaPessoaDelegate.class);
		EasyMock.expect(mockFonteRendaPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstfonteRendaVO);
		
		ejb.setFonteRendaPessoaDelegate(mockFonteRendaPessoaDelegate);
		
		EasyMock.replay(mockFonteRendaPessoaDelegate);
		FonteRendaPessoaIntegracaoDTO fonteRendaPessoaIntegracaoDTO = ejb.obterFonteRendaPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(fonteRendaPessoaIntegracaoDTO);
		EasyMock.verify(mockFonteRendaPessoaDelegate);
	}
	
	@Test
	public void testObterFonteRendaPessoaInstituicaoEmpty() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<FonteRendaPessoaVO> lstfonteRendaVO = new ArrayList();
		
		FonteRendaPessoaDelegate mockFonteRendaPessoaDelegate = EasyMock.createMock(FonteRendaPessoaDelegate.class);
		EasyMock.expect(mockFonteRendaPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstfonteRendaVO);
		
		ejb.setFonteRendaPessoaDelegate(mockFonteRendaPessoaDelegate);
		
		EasyMock.replay(mockFonteRendaPessoaDelegate);
		FonteRendaPessoaIntegracaoDTO fonteRendaPessoaIntegracaoDTO = ejb.obterFonteRendaPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(fonteRendaPessoaIntegracaoDTO);
		EasyMock.verify(mockFonteRendaPessoaDelegate);
	}
	
	@Test
	public void testObterBemPessoaInstituicao() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<BemPessoaVO> lstBemPessoaVO = new ArrayList();
		BemPessoaVO bemPessoaVO = new BemPessoaVO();
		
		bemPessoaVO.setValorAtualMercado(BigDecimal.TEN);
		bemPessoaVO.setPercentual(BigDecimal.TEN);
		
		lstBemPessoaVO.add(bemPessoaVO);
		
		BemPessoaDelegate mockBemPessoaDelegate = EasyMock.createMock(BemPessoaDelegate.class);
		EasyMock.expect(mockBemPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstBemPessoaVO);
		
		ejb.setBemPessoaDelegate(mockBemPessoaDelegate);
		
		EasyMock.replay(mockBemPessoaDelegate);
		BemPessoaIntegracaoDTO bemPessoaIntegracaoDTO = ejb.obterBemPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(bemPessoaIntegracaoDTO);
		EasyMock.verify(mockBemPessoaDelegate);
	}
	
	@Test
	public void testObterBemPessoaInstituicao2() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<BemPessoaVO> lstBemPessoaVO = new ArrayList();
		BemPessoaVO bemPessoaVO = new BemPessoaVO();
		
		bemPessoaVO.setValorAtualMercado(null);
		bemPessoaVO.setPercentual(BigDecimal.TEN);
		
		lstBemPessoaVO.add(bemPessoaVO);
		
		BemPessoaDelegate mockBemPessoaDelegate = EasyMock.createMock(BemPessoaDelegate.class);
		EasyMock.expect(mockBemPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstBemPessoaVO);
		
		ejb.setBemPessoaDelegate(mockBemPessoaDelegate);
		
		EasyMock.replay(mockBemPessoaDelegate);
		BemPessoaIntegracaoDTO bemPessoaIntegracaoDTO = ejb.obterBemPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(bemPessoaIntegracaoDTO);
		EasyMock.verify(mockBemPessoaDelegate);
	}
	
	@Test
	public void testObterBemPessoaInstituicao3() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<BemPessoaVO> lstBemPessoaVO = new ArrayList();
		BemPessoaVO bemPessoaVO = new BemPessoaVO();
		BemPessoaVO bemPessoaVO2 = new BemPessoaVO();
		
		bemPessoaVO.setValorAtualMercado(BigDecimal.TEN);
		bemPessoaVO.setPercentual(null);
		
		lstBemPessoaVO.add(bemPessoaVO);
		lstBemPessoaVO.add(bemPessoaVO2);
		
		BemPessoaDelegate mockBemPessoaDelegate = EasyMock.createMock(BemPessoaDelegate.class);
		EasyMock.expect(mockBemPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstBemPessoaVO);
		
		ejb.setBemPessoaDelegate(mockBemPessoaDelegate);
		
		EasyMock.replay(mockBemPessoaDelegate);
		BemPessoaIntegracaoDTO bemPessoaIntegracaoDTO = ejb.obterBemPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(bemPessoaIntegracaoDTO);
		EasyMock.verify(mockBemPessoaDelegate);
	}
	
	@Test
	public void testObterBemPessoaInstituicaoEmpty() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<BemPessoaVO> lstBemPessoaVO = new ArrayList();
		
		BemPessoaDelegate mockBemPessoaDelegate = EasyMock.createMock(BemPessoaDelegate.class);
		EasyMock.expect(mockBemPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstBemPessoaVO);
		
		ejb.setBemPessoaDelegate(mockBemPessoaDelegate);
		
		EasyMock.replay(mockBemPessoaDelegate);
		BemPessoaIntegracaoDTO bemPessoaIntegracaoDTO = ejb.obterBemPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(bemPessoaIntegracaoDTO);
		EasyMock.verify(mockBemPessoaDelegate);
	}
	
	@Test
	public void testObterReferenciaPessoaInstituicao() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		
		List<ReferenciaPessoaVO> lstReferenciaPessoaVO = new ArrayList();
		List<TelefonePessoaVO> lstTelefonePessoaVO = new ArrayList();
		
		ReferenciaPessoaVO referenciaPessoaVO1 = new ReferenciaPessoaVO();
		ReferenciaPessoaVO referenciaPessoaVO2 = new ReferenciaPessoaVO();
		ReferenciaPessoaVO referenciaPessoaVO3 = new ReferenciaPessoaVO();
		ReferenciaPessoaVO referenciaPessoaVO4 = new ReferenciaPessoaVO();
		ReferenciaPessoaVO referenciaPessoaVO5 = new ReferenciaPessoaVO();
		
		referenciaPessoaVO1.setDdd(Short.valueOf("61"));
		referenciaPessoaVO1.setTelefone("3215436");
		
		referenciaPessoaVO2.setCodigoTipoReferencia(Short.valueOf("2"));
		
		referenciaPessoaVO3.setDdd(Short.valueOf("61"));
		referenciaPessoaVO3.setTelefone("3215436");
		
		referenciaPessoaVO4.setCodigoTipoReferencia(Short.valueOf("1"));
		
		lstReferenciaPessoaVO.add(referenciaPessoaVO1);
		lstReferenciaPessoaVO.add(referenciaPessoaVO2);
		lstReferenciaPessoaVO.add(referenciaPessoaVO3);
		lstReferenciaPessoaVO.add(referenciaPessoaVO4);
		lstReferenciaPessoaVO.add(referenciaPessoaVO5);
		
		ReferenciaPessoaDelegate mockReferenciaPessoaDelegate = EasyMock.createMock(ReferenciaPessoaDelegate.class);
		EasyMock.expect(mockReferenciaPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstReferenciaPessoaVO);
		
		TelefonePessoaDelegate mockTelefonePessoaDelegate = EasyMock.createMock(TelefonePessoaDelegate.class);
		EasyMock.expect(mockTelefonePessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstTelefonePessoaVO).anyTimes();
		
		ejb.setReferenciaPessoaDelegate(mockReferenciaPessoaDelegate);
		ejb.setTelefonePessoaDelegate(mockTelefonePessoaDelegate);
		
		EasyMock.replay(mockReferenciaPessoaDelegate, mockTelefonePessoaDelegate);
		ReferenciaPessoaIntegracaoDTO referenciaPessoa = ejb.obterReferenciaPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(referenciaPessoa);
		EasyMock.verify(mockReferenciaPessoaDelegate, mockTelefonePessoaDelegate);
	}
	
	@Test
	public void testObterReferenciaPessoaInstituicao2() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		
		List<ReferenciaPessoaVO> lstReferenciaPessoaVO = new ArrayList();
		List<TelefonePessoaVO> lstTelefonePessoaVO = new ArrayList();
		
		ReferenciaPessoaVO referenciaPessoaVO1 = new ReferenciaPessoaVO();
		ReferenciaPessoaVO referenciaPessoaVO2 = new ReferenciaPessoaVO();
		ReferenciaPessoaVO referenciaPessoaVO3 = new ReferenciaPessoaVO();
		ReferenciaPessoaVO referenciaPessoaVO4 = new ReferenciaPessoaVO();
		ReferenciaPessoaVO referenciaPessoaVO5 = new ReferenciaPessoaVO();
		
		referenciaPessoaVO1.setDdd(Short.valueOf("61"));
		
		referenciaPessoaVO2.setCodigoTipoReferencia(Short.valueOf("2"));
		
		referenciaPessoaVO3.setDdd(Short.valueOf("61"));
		
		referenciaPessoaVO4.setCodigoTipoReferencia(Short.valueOf("4"));
		
		lstReferenciaPessoaVO.add(referenciaPessoaVO1);
		lstReferenciaPessoaVO.add(referenciaPessoaVO2);
		lstReferenciaPessoaVO.add(referenciaPessoaVO3);
		lstReferenciaPessoaVO.add(referenciaPessoaVO4);
		lstReferenciaPessoaVO.add(referenciaPessoaVO5);
		
		ReferenciaPessoaDelegate mockReferenciaPessoaDelegate = EasyMock.createMock(ReferenciaPessoaDelegate.class);
		EasyMock.expect(mockReferenciaPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstReferenciaPessoaVO);
		
		TelefonePessoaDelegate mockTelefonePessoaDelegate = EasyMock.createMock(TelefonePessoaDelegate.class);
		EasyMock.expect(mockTelefonePessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstTelefonePessoaVO).anyTimes();
		
		ejb.setReferenciaPessoaDelegate(mockReferenciaPessoaDelegate);
		ejb.setTelefonePessoaDelegate(mockTelefonePessoaDelegate);
		
		EasyMock.replay(mockReferenciaPessoaDelegate, mockTelefonePessoaDelegate);
		ReferenciaPessoaIntegracaoDTO referenciaPessoa = ejb.obterReferenciaPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(referenciaPessoa);
		EasyMock.verify(mockReferenciaPessoaDelegate, mockTelefonePessoaDelegate);
	}
	
	@Test
	public void testObterReferenciaPessoaInstituicao3() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		
		List<ReferenciaPessoaVO> lstReferenciaPessoaVO = new ArrayList();
		List<TelefonePessoaVO> lstTelefonePessoaVO = new ArrayList();
		
		ReferenciaPessoaVO referenciaPessoaVO1 = new ReferenciaPessoaVO();
		ReferenciaPessoaVO referenciaPessoaVO2 = new ReferenciaPessoaVO();
		ReferenciaPessoaVO referenciaPessoaVO3 = new ReferenciaPessoaVO();
		ReferenciaPessoaVO referenciaPessoaVO4 = new ReferenciaPessoaVO();
		ReferenciaPessoaVO referenciaPessoaVO5 = new ReferenciaPessoaVO();
		
		referenciaPessoaVO1.setDdd(Short.valueOf("61"));
		referenciaPessoaVO1.setTelefone("3215436");
		
		referenciaPessoaVO2.setCodigoTipoReferencia(Short.valueOf("5"));
		
		referenciaPessoaVO3.setDdd(Short.valueOf("61"));
		referenciaPessoaVO3.setTelefone("3215436");
		
		referenciaPessoaVO4.setCodigoTipoReferencia(Short.valueOf("3"));
		
		lstReferenciaPessoaVO.add(referenciaPessoaVO1);
		lstReferenciaPessoaVO.add(referenciaPessoaVO2);
		lstReferenciaPessoaVO.add(referenciaPessoaVO3);
		lstReferenciaPessoaVO.add(referenciaPessoaVO4);
		lstReferenciaPessoaVO.add(referenciaPessoaVO5);
		
		ReferenciaPessoaDelegate mockReferenciaPessoaDelegate = EasyMock.createMock(ReferenciaPessoaDelegate.class);
		EasyMock.expect(mockReferenciaPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstReferenciaPessoaVO);
		
		TelefonePessoaDelegate mockTelefonePessoaDelegate = EasyMock.createMock(TelefonePessoaDelegate.class);
		EasyMock.expect(mockTelefonePessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(lstTelefonePessoaVO).anyTimes();
		
		ejb.setReferenciaPessoaDelegate(mockReferenciaPessoaDelegate);
		ejb.setTelefonePessoaDelegate(mockTelefonePessoaDelegate);
		
		EasyMock.replay(mockReferenciaPessoaDelegate, mockTelefonePessoaDelegate);
		ReferenciaPessoaIntegracaoDTO referenciaPessoa = ejb.obterReferenciaPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(referenciaPessoa);
		EasyMock.verify(mockReferenciaPessoaDelegate, mockTelefonePessoaDelegate);
	}
	
	@Test
	public void testObterReferenciaPessoaInstituicaoEmpty() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<ReferenciaPessoaVO> referenciaPessoaVO = new ArrayList();
		
		ReferenciaPessoaDelegate mockReferenciaPessoaDelegate = EasyMock.createMock(ReferenciaPessoaDelegate.class);
		EasyMock.expect(mockReferenciaPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(referenciaPessoaVO);
		
		ejb.setReferenciaPessoaDelegate(mockReferenciaPessoaDelegate);
		
		EasyMock.replay(mockReferenciaPessoaDelegate);
		ReferenciaPessoaIntegracaoDTO referenciaPessoa = ejb.obterReferenciaPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(referenciaPessoa);
		EasyMock.verify(mockReferenciaPessoaDelegate);
	}
	
	@Test
	public void testObterPessoaJuridicaInstituicao() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		PessoaJuridicaVO pessoaJuridicaVO = new PessoaJuridicaVO();
		
		PessoaJuridicaDelegate mockPessoaJuridicaDelegate = EasyMock.createMock(PessoaJuridicaDelegate.class);
		EasyMock.expect(mockPessoaJuridicaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(pessoaJuridicaVO);
		
		ejb.setPessoaJuridicaDelegate(mockPessoaJuridicaDelegate);
		
		EasyMock.replay(mockPessoaJuridicaDelegate);
		PessoaJuridicaIntegracaoDTO pessoaJuridica = ejb.obterPessoaJuridicaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(pessoaJuridica);
		EasyMock.verify(mockPessoaJuridicaDelegate);
	}
	
	@Test
	public void testObterPessoaJuridicaInstituicaoEmpty() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		
		PessoaJuridicaDelegate mockPessoaJuridicaDelegate = EasyMock.createMock(PessoaJuridicaDelegate.class);
		EasyMock.expect(mockPessoaJuridicaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(null);
		
		ejb.setPessoaJuridicaDelegate(mockPessoaJuridicaDelegate);
		
		EasyMock.replay(mockPessoaJuridicaDelegate);
		PessoaJuridicaIntegracaoDTO pessoaJuridica = ejb.obterPessoaJuridicaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(pessoaJuridica);
		EasyMock.verify(mockPessoaJuridicaDelegate);
	}

	@Test
	public void testObterConjugePessoaInstituicao() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<RelacionamentoPessoaVO> relConjPessoaVOLst = new ArrayList();
		
		RelacionamentoPessoaVO relacionamentoPessoaVO = new RelacionamentoPessoaVO();
		relConjPessoaVOLst.add(relacionamentoPessoaVO);
		
		RelacionamentoPessoaDelegate mockRelacionamentoPessoaDelegate = EasyMock.createMock(RelacionamentoPessoaDelegate.class);
		EasyMock.expect(mockRelacionamentoPessoaDelegate.obterConjugesPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(relConjPessoaVOLst);
		
		PessoaVO pessoaVO = new PessoaVO(); 
		
		PessoaDelegate mockPessoaDelegate = EasyMock.createMock(PessoaDelegate.class);
		EasyMock.expect(mockPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(pessoaVO).anyTimes();
		
		ejb.setRelacionamentoPessoaDelegate(mockRelacionamentoPessoaDelegate);
		ejb.setPessoaDelegate(mockPessoaDelegate);
		
		EasyMock.replay(mockRelacionamentoPessoaDelegate, mockPessoaDelegate);
		List<RelacionamentoPessoaIntegracaoDTO> listRelacionamentos = ejb.obterConjugePessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(listRelacionamentos);
		EasyMock.verify(mockRelacionamentoPessoaDelegate, mockPessoaDelegate);
	}
	
	@Test
	public void testObterConjugePessoaInstituicaoEmpty() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<RelacionamentoPessoaVO> relConjPessoaVOLst = new ArrayList();
		
		RelacionamentoPessoaDelegate mockRelacionamentoPessoaDelegate = EasyMock.createMock(RelacionamentoPessoaDelegate.class);
		EasyMock.expect(mockRelacionamentoPessoaDelegate.obterConjugesPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(relConjPessoaVOLst);
		
		ejb.setRelacionamentoPessoaDelegate(mockRelacionamentoPessoaDelegate);
		
		EasyMock.replay(mockRelacionamentoPessoaDelegate);
		List<RelacionamentoPessoaIntegracaoDTO> listRelacionamentos = ejb.obterConjugePessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(listRelacionamentos);
		EasyMock.verify(mockRelacionamentoPessoaDelegate);
	}
	
	@Test
	public void testObterRepresentantesLegaisPessoaInstituicao() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<RelacionamentoPessoaVO> relReprPessoaVOLst = new ArrayList(); 
		
		RelacionamentoPessoaVO relacionamentoPessoaVO1 = new RelacionamentoPessoaVO();
		relacionamentoPessoaVO1.setCodigoTipoRelacionamento(Short.valueOf("1"));
		
		RelacionamentoPessoaVO relacionamentoPessoaVO2 = new RelacionamentoPessoaVO();
		relacionamentoPessoaVO2.setCodigoTipoRelacionamento(Short.valueOf("5"));
		
		RelacionamentoPessoaVO relacionamentoPessoaVO3 = new RelacionamentoPessoaVO();
		relacionamentoPessoaVO3.setCodigoTipoRelacionamento(Short.valueOf("6"));
		
		RelacionamentoPessoaVO relacionamentoPessoaVO4 = new RelacionamentoPessoaVO();
		relacionamentoPessoaVO4.setCodigoTipoRelacionamento(Short.valueOf("9"));
		
		RelacionamentoPessoaVO relacionamentoPessoaVO5 = new RelacionamentoPessoaVO();
		relacionamentoPessoaVO5.setCodigoTipoRelacionamento(Short.valueOf("3"));
		
		relReprPessoaVOLst.add(relacionamentoPessoaVO1);
		relReprPessoaVOLst.add(relacionamentoPessoaVO2);
		relReprPessoaVOLst.add(relacionamentoPessoaVO3);
		relReprPessoaVOLst.add(relacionamentoPessoaVO4);
		relReprPessoaVOLst.add(relacionamentoPessoaVO5);
		
		RelacionamentoPessoaDelegate mockRelacionamentoPessoaDelegate = EasyMock.createMock(RelacionamentoPessoaDelegate.class);
		EasyMock.expect(mockRelacionamentoPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(relReprPessoaVOLst);
		
		PessoaVO pessoaVO = new PessoaVO(); 
		
		PessoaDelegate mockPessoaDelegate = EasyMock.createMock(PessoaDelegate.class);
		EasyMock.expect(mockPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(pessoaVO).anyTimes();
		
		ejb.setRelacionamentoPessoaDelegate(mockRelacionamentoPessoaDelegate);
		ejb.setPessoaDelegate(mockPessoaDelegate);
		
		EasyMock.replay(mockRelacionamentoPessoaDelegate, mockPessoaDelegate);
		List<RelacionamentoPessoaIntegracaoDTO> listaRelacionamentos = ejb.obterRepresentantesLegaisPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(listaRelacionamentos);
		EasyMock.verify(mockRelacionamentoPessoaDelegate, mockPessoaDelegate);
	}
	
	@Test
	public void testObterRepresentantesLegaisPessoaInstituicaoEmpty() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<RelacionamentoPessoaVO> relReprPessoaVOLst = new ArrayList(); 
		
		RelacionamentoPessoaDelegate mockRelacionamentoPessoaDelegate = EasyMock.createMock(RelacionamentoPessoaDelegate.class);
		EasyMock.expect(mockRelacionamentoPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(relReprPessoaVOLst);
		
		ejb.setRelacionamentoPessoaDelegate(mockRelacionamentoPessoaDelegate);
		
		EasyMock.replay(mockRelacionamentoPessoaDelegate);
		List<RelacionamentoPessoaIntegracaoDTO> listaRelacionamentos = ejb.obterRepresentantesLegaisPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(listaRelacionamentos);
		EasyMock.verify(mockRelacionamentoPessoaDelegate);
	}
	
	@Test
	public void testObterRepresentantesLegaisPessoaInstituicaoNull() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<RelacionamentoPessoaVO> relReprPessoaVOLst = new ArrayList(); 
		RelacionamentoPessoaVO relacionamentoPessoaVO = new RelacionamentoPessoaVO();
		
		relReprPessoaVOLst.add(relacionamentoPessoaVO);
		
		RelacionamentoPessoaDelegate mockRelacionamentoPessoaDelegate = EasyMock.createMock(RelacionamentoPessoaDelegate.class);
		EasyMock.expect(mockRelacionamentoPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(relReprPessoaVOLst);
		
		PessoaVO pessoaVO = new PessoaVO(); 
		
		PessoaDelegate mockPessoaDelegate = EasyMock.createMock(PessoaDelegate.class);
		EasyMock.expect(mockPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(pessoaVO).anyTimes();
		
		ejb.setRelacionamentoPessoaDelegate(mockRelacionamentoPessoaDelegate);
		ejb.setPessoaDelegate(mockPessoaDelegate);
		
		EasyMock.replay(mockRelacionamentoPessoaDelegate, mockPessoaDelegate);
		List<RelacionamentoPessoaIntegracaoDTO> listaRelacionamentos = ejb.obterRepresentantesLegaisPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(listaRelacionamentos);
		EasyMock.verify(mockRelacionamentoPessoaDelegate, mockPessoaDelegate);
	}

	@Test
	public void testObterResponsavelLegalPessoaInstituicao() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<RelacionamentoPessoaVO> relReprPessoaVOLst = new ArrayList();
		
		RelacionamentoPessoaVO relacionamentoPessoaVO = new RelacionamentoPessoaVO();
		
		relReprPessoaVOLst.add(relacionamentoPessoaVO);
		
		RelacionamentoPessoaDelegate mockRelacionamentoPessoaDelegate = EasyMock.createMock(RelacionamentoPessoaDelegate.class);
		EasyMock.expect(mockRelacionamentoPessoaDelegate.obterPorPessoaInstituicaoTipo(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyShort())).andReturn(relReprPessoaVOLst);
		
		PessoaVO pessoaVO = new PessoaVO(); 
		
		PessoaDelegate mockPessoaDelegate = EasyMock.createMock(PessoaDelegate.class);
		EasyMock.expect(mockPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(pessoaVO).anyTimes();
		
		ejb.setRelacionamentoPessoaDelegate(mockRelacionamentoPessoaDelegate);
		ejb.setPessoaDelegate(mockPessoaDelegate);
		
		EasyMock.replay(mockRelacionamentoPessoaDelegate, mockPessoaDelegate);
		List<RelacionamentoPessoaIntegracaoDTO> listaRelacionamento = ejb.obterResponsavelLegalPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(listaRelacionamento);
		EasyMock.verify(mockRelacionamentoPessoaDelegate, mockPessoaDelegate);
	}
	
	@Test
	public void testObterResponsavelLegalPessoaInstituicaoEmpty() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<RelacionamentoPessoaVO> relReprPessoaVOLst = new ArrayList();
		
		RelacionamentoPessoaDelegate mockRelacionamentoPessoaDelegate = EasyMock.createMock(RelacionamentoPessoaDelegate.class);
		EasyMock.expect(mockRelacionamentoPessoaDelegate.obterPorPessoaInstituicaoTipo(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyShort())).andReturn(relReprPessoaVOLst);
		
		ejb.setRelacionamentoPessoaDelegate(mockRelacionamentoPessoaDelegate);
		
		EasyMock.replay(mockRelacionamentoPessoaDelegate);
		List<RelacionamentoPessoaIntegracaoDTO> listaRelacionamento = ejb.obterResponsavelLegalPessoaInstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(listaRelacionamento);
		EasyMock.verify(mockRelacionamentoPessoaDelegate);
	}

	@Test
	public void testObterPessoaJuridicaFormaConstituicao() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		
		PessoaJuridicaVO pessoaJuridicaVO = new PessoaJuridicaVO();
		ClienteVO clienteVO = new ClienteVO();
		
		PessoaJuridicaDelegate mockPessoaJuridicaDelegate = EasyMock.createMock(PessoaJuridicaDelegate.class);
		EasyMock.expect(mockPessoaJuridicaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(pessoaJuridicaVO);
		
		ClienteDelegate mockClienteDelegate = EasyMock.createMock(ClienteDelegate.class);
		EasyMock.expect(mockClienteDelegate.obterPorIdPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(clienteVO);
		
		ejb.setPessoaJuridicaDelegate(mockPessoaJuridicaDelegate);
		ejb.setClienteDelegate(mockClienteDelegate);
		
		EasyMock.replay(mockPessoaJuridicaDelegate, mockClienteDelegate);
		PessoaIntegracaoDTO pessoa = ejb.obterPessoaJuridicaFormaConstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(pessoa);
		EasyMock.verify(mockPessoaJuridicaDelegate, mockClienteDelegate);
	}
	
	@Test
	public void testObterPessoaJuridicaFormaConstituicaoNull() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		
		PessoaJuridicaDelegate mockPessoaJuridicaDelegate = EasyMock.createMock(PessoaJuridicaDelegate.class);
		EasyMock.expect(mockPessoaJuridicaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(null);
		
		ClienteDelegate mockClienteDelegate = EasyMock.createMock(ClienteDelegate.class);
		EasyMock.expect(mockClienteDelegate.obterPorIdPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(null);
		
		ejb.setPessoaJuridicaDelegate(mockPessoaJuridicaDelegate);
		ejb.setClienteDelegate(mockClienteDelegate);
		
		EasyMock.replay(mockPessoaJuridicaDelegate, mockClienteDelegate);
		PessoaIntegracaoDTO pessoa = ejb.obterPessoaJuridicaFormaConstituicao(idPessoa, idInstituicao);
		Assert.assertNotNull(pessoa);
		EasyMock.verify(mockPessoaJuridicaDelegate, mockClienteDelegate);
	}
	
	@Test
	public void testIsPessoaJuridica() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		PessoaIntegracaoDTO pessoa = new PessoaIntegracaoDTO(); 
		
		CapesIntegracaoDelegate mockCapesIntegracaoDelegate = EasyMock.createMock(CapesIntegracaoDelegate.class);
		EasyMock.expect(mockCapesIntegracaoDelegate.obterPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(pessoa);
		
		ejb.setCapesIntegracaoDelegate(mockCapesIntegracaoDelegate);
		
		EasyMock.replay(mockCapesIntegracaoDelegate);
		Boolean bol = ejb.isPessoaJuridica(idPessoa, idInstituicao);
		Assert.assertNotNull(bol);
		EasyMock.verify(mockCapesIntegracaoDelegate);
	}
	
	@Test
	public void testisClienteCadastrado() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		ClienteVO cli = new ClienteVO();
		cli.setIdPessoa(1);
		cli.setCpfCnpj("123321321");
		
		ClienteDelegate mockClienteDelegate = EasyMock.createMock(ClienteDelegate.class);
		EasyMock.expect(mockClienteDelegate.obterPorIdPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(cli);
		
		ejb.setClienteDelegate(mockClienteDelegate);
		
		EasyMock.replay(mockClienteDelegate);
		Boolean bol = ejb.isClienteCadastrado(idPessoa, idInstituicao);
		Assert.assertNotNull(bol);
		EasyMock.verify(mockClienteDelegate);
	}
	
	@Test
	public void testisClienteCadastradoNull() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		ClienteVO cli = new ClienteVO();
		
		ClienteDelegate mockClienteDelegate = EasyMock.createMock(ClienteDelegate.class);
		EasyMock.expect(mockClienteDelegate.obterPorIdPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt())).andReturn(cli);
		
		ejb.setClienteDelegate(mockClienteDelegate);
		
		EasyMock.replay(mockClienteDelegate);
		Boolean bol = ejb.isClienteCadastrado(idPessoa, idInstituicao);
		Assert.assertNotNull(bol);
		EasyMock.verify(mockClienteDelegate);
	}

	@Test
	public void testObterAnotacoesBaixadas() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<AnotacaoPessoaVO> lstAnotacaoVO = new ArrayList();
		
		AnotacaoPessoaVO anotacaoPessoaVO = new AnotacaoPessoaVO();
		lstAnotacaoVO.add(anotacaoPessoaVO);
		
		AnotacaoPessoaDelegate mockAnotacaoPessoaDelegate = EasyMock.createMock(AnotacaoPessoaDelegate.class);
		EasyMock.expect(mockAnotacaoPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyBoolean())).andReturn(lstAnotacaoVO);
		
		ejb.setAnotacaoPessoaDelegate(mockAnotacaoPessoaDelegate);
		
		EasyMock.replay(mockAnotacaoPessoaDelegate);
		List<AnotacaoPessoaDTO> lstAnotacoes = ejb.obterAnotacoesBaixadas(idPessoa, idInstituicao);
		Assert.assertNotNull(lstAnotacoes);
		EasyMock.verify(mockAnotacaoPessoaDelegate);
	}
	
	@Test
	public void testObterAnotacoesBaixadasNull() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<AnotacaoPessoaVO> lstAnotacaoVO = new ArrayList();
		
		AnotacaoPessoaVO anotacaoPessoaVO = new AnotacaoPessoaVO();
		
		AnotacaoPessoaDelegate mockAnotacaoPessoaDelegate = EasyMock.createMock(AnotacaoPessoaDelegate.class);
		EasyMock.expect(mockAnotacaoPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyBoolean())).andReturn(lstAnotacaoVO);
		
		ejb.setAnotacaoPessoaDelegate(mockAnotacaoPessoaDelegate);
		
		EasyMock.replay(mockAnotacaoPessoaDelegate);
		List<AnotacaoPessoaDTO> lstAnotacoes = ejb.obterAnotacoesBaixadas(idPessoa, idInstituicao);
		Assert.assertNotNull(lstAnotacoes);
		EasyMock.verify(mockAnotacaoPessoaDelegate);
	}

	@Test
	public void testObterAnotacoesVigentes() throws BancoobException{
		Integer idPessoa = 1;
		Integer idInstituicao = 2;
		
		CapesIntegracaoServicoEJB ejb = new CapesIntegracaoServicoEJB();
		List<AnotacaoPessoaVO> lstAnotacaoVO = new ArrayList();
		
		AnotacaoPessoaVO anotacaoPessoaVO = new AnotacaoPessoaVO();
		lstAnotacaoVO.add(anotacaoPessoaVO);
		
		AnotacaoPessoaDelegate mockAnotacaoPessoaDelegate = EasyMock.createMock(AnotacaoPessoaDelegate.class);
		EasyMock.expect(mockAnotacaoPessoaDelegate.obterPorPessoaInstituicao(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyBoolean())).andReturn(lstAnotacaoVO);
		
		ejb.setAnotacaoPessoaDelegate(mockAnotacaoPessoaDelegate);
		
		EasyMock.replay(mockAnotacaoPessoaDelegate);
		List<AnotacaoPessoaDTO> lstAnotacoes = ejb.obterAnotacoesVigentes(idPessoa, idInstituicao);
		Assert.assertNotNull(lstAnotacoes);
		EasyMock.verify(mockAnotacaoPessoaDelegate);
	}

}

