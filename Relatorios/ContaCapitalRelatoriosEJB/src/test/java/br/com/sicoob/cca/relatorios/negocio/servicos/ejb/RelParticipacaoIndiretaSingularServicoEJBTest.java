package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapital;
import br.com.sicoob.cca.relatorios.negocio.dto.FiltroParticipacaoIndiretaSingularDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelParticipacaoIndiretaSingularDTO;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.RelParticipacaoIndiretaSingularDao;

/**
 * A Classe RelParticipacaoIndiretaSingularServicoEJBTest.
 */
public class RelParticipacaoIndiretaSingularServicoEJBTest {
	
	@BeforeClass
	public static void setUp() {
		InformacoesUsuario info = new InformacoesUsuario();
		info.setIdInstituicao("0");
		info.setPac("0");
		info.setLogin("Teste");
		InformacoesUsuario.INSTANCIA.set(info);
	}
	
	/**
	 * O método Emitir rel participacao indireta.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Ignore
	public void emitirRelParticipacaoIndireta() throws BancoobException{
		RelParticipacaoIndiretaSingularServicoEJB ejb = new RelParticipacaoIndiretaSingularServicoEJB();
		FiltroParticipacaoIndiretaSingularDTO dto = new FiltroParticipacaoIndiretaSingularDTO();
		
		dto.setChkArquivoExcel(false);
		
		RelParticipacaoIndiretaSingularDao mockDao = EasyMock.createMock(RelParticipacaoIndiretaSingularDao.class);
		
		@SuppressWarnings("unchecked")
		RelatorioContaCapital<RelParticipacaoIndiretaSingularDTO> mockRelatorio = EasyMock.createMock(RelatorioContaCapital.class);
		
		ejb.setRelParticipacaoIndiretaSingularDao(mockDao);
		
		List<RelParticipacaoIndiretaSingularDTO> lista = new ArrayList<RelParticipacaoIndiretaSingularDTO>();
		RelParticipacaoIndiretaSingularDTO listItem = new RelParticipacaoIndiretaSingularDTO();
		lista.add(listItem);
		
		EasyMock.expect(mockDao.listarRelParticipacaoIndireta(EasyMock.anyObject(FiltroParticipacaoIndiretaSingularDTO.class))).andReturn(lista);
		
		Object obj = new Object();
		
		@SuppressWarnings("unchecked")
		RelatorioContaCapital<RelParticipacaoIndiretaSingularDTO> mockBancoob = PowerMock.createNicePartialMock(RelatorioContaCapital.class, "gerar", "getInputStream", "getArquivo");
		EasyMock.expect(mockBancoob.gerarSincronamente()).andReturn(obj);
		
		EasyMock.replay(mockRelatorio, mockDao);
		
		dto.setNumCentral(1001);
		dto.setNumCooperativa(3008);
		dto.setMes(1);
		dto.setAno(2017);		
		
		Object relatorio = ejb.emitirRelParticipacaoIndireta(dto);
		Assert.assertNotNull(relatorio);
		EasyMock.verify(mockRelatorio, mockDao);
	}
	
	/**
	 * O método Emitir rel participacao indireta null.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Test
	public void emitirRelParticipacaoIndiretaNull() throws BancoobException{
		RelParticipacaoIndiretaSingularServicoEJB ejb = new RelParticipacaoIndiretaSingularServicoEJB();
		FiltroParticipacaoIndiretaSingularDTO dto = new FiltroParticipacaoIndiretaSingularDTO();
		
		RelParticipacaoIndiretaSingularDao mockDao = EasyMock.createMock(RelParticipacaoIndiretaSingularDao.class);
		ejb.setRelParticipacaoIndiretaSingularDao(mockDao);
		
		List<RelParticipacaoIndiretaSingularDTO> lista = new ArrayList<RelParticipacaoIndiretaSingularDTO>();
		EasyMock.expect(mockDao.listarRelParticipacaoIndireta(EasyMock.anyObject(FiltroParticipacaoIndiretaSingularDTO.class))).andReturn(lista);
		
		EasyMock.replay(mockDao);
		boolean exception = true;
		try{
			ejb.emitirRelParticipacaoIndireta(dto);
			Assert.fail("Erro");
		}catch(Exception e){
			exception = false;
			Assert.assertEquals(NegocioException.class, e.getClass());
		}
		Assert.assertFalse(exception);
		EasyMock.verify(mockDao);
	}
	
	/**
	 * O método Emitir rel participacao indireta chk true.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	@Ignore
	public void emitirRelParticipacaoIndiretaChkTrue() throws BancoobException{
		RelParticipacaoIndiretaSingularServicoEJB ejb = new RelParticipacaoIndiretaSingularServicoEJB();
		FiltroParticipacaoIndiretaSingularDTO dto = new FiltroParticipacaoIndiretaSingularDTO();
		
		dto.setChkArquivoExcel(true);
		
		RelParticipacaoIndiretaSingularDao mockDao = EasyMock.createMock(RelParticipacaoIndiretaSingularDao.class);
		
		@SuppressWarnings("unchecked")
		RelatorioContaCapital<RelParticipacaoIndiretaSingularDTO> mockRelatorio = EasyMock.createMock(RelatorioContaCapital.class);
		
		ejb.setRelParticipacaoIndiretaSingularDao(mockDao);
		
		List<RelParticipacaoIndiretaSingularDTO> lista = new ArrayList<RelParticipacaoIndiretaSingularDTO>();
		RelParticipacaoIndiretaSingularDTO listItem = new RelParticipacaoIndiretaSingularDTO();
		lista.add(listItem);
		
		EasyMock.expect(mockDao.listarRelParticipacaoIndireta(EasyMock.anyObject(FiltroParticipacaoIndiretaSingularDTO.class))).andReturn(lista);
		
		Object obj = new Object();
		
		@SuppressWarnings("unchecked")
		RelatorioContaCapital<RelParticipacaoIndiretaSingularDTO> mockBancoob = PowerMock.createNicePartialMock(RelatorioContaCapital.class, "gerar", "getInputStream", "getArquivo");
		EasyMock.expect(mockBancoob.gerarSincronamente()).andReturn(obj);
		
		EasyMock.replay(mockRelatorio, mockDao);
		Object relatorio = ejb.emitirRelParticipacaoIndireta(dto);
		Assert.assertNotNull(relatorio);
		EasyMock.verify(mockRelatorio, mockDao);
	}
}
