package br.com.sicoob.cca.movimentacao.conversor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;
import br.com.sicoob.sisbr.cca.movimentacao.conversor.ConversorBloqueioContaCapital;
import br.com.sicoob.sisbr.cca.movimentacao.vo.BloqueioContaCapitalVO;

public class ConversorBloqueioContaCapitalTest{

	private ConversorBloqueioContaCapital conversor = new ConversorBloqueioContaCapital();
	private Random random = new Random();
	
	@Test
	public void converterDTOParaVO() throws BancoobException {
		BloqueioContaCapitalDTO dto = criarDTO();
		BloqueioContaCapitalVO vo = conversor.converterDTOParaVO(dto);
		assertEquals(dto, vo);
	}

	private BloqueioContaCapitalDTO criarDTO() {
		BloqueioContaCapitalDTO dto = new BloqueioContaCapitalDTO();
		dto.setAtivo(Boolean.TRUE);
		dto.setCodSituacaoBloqueio(random.nextInt());
		dto.setCodTipoBloqueio(random.nextInt());
		dto.setCpfCnpj(""+random.nextInt());
		dto.setDataBloqueio(new DateTimeDB());
		dto.setDataDesbloqueio(new DateTimeDB());
		dto.setDataProtocolo(new DateTimeDB());
		dto.setIdBloqueio(random.nextInt());
		dto.setIdContaCapital(random.nextInt());
		dto.setIdInstituicao(random.nextInt());
		dto.setIdTipoBloqueio(random.nextInt());
		dto.setNomePessoa(""+random.nextInt());
		dto.setNomeTipoBloqueio(""+random.nextInt());
		dto.setNumContaCapital(random.nextInt());
		dto.setNumProcesso(""+random.nextInt());
		dto.setNumProtocolo(""+random.nextInt());
		dto.setValorBloqueado(BigDecimal.ONE);
		dto.setValorDesbloqueio(BigDecimal.ZERO);
		return dto;
	}
	
	@Test
	public void converterVOParaDTO() throws BancoobException {
		BloqueioContaCapitalVO vo = criarVO();
		BloqueioContaCapitalDTO dto = conversor.converterVOParaDTO(vo);
		assertEquals(dto, vo);
	}

	private BloqueioContaCapitalVO criarVO() {
		BloqueioContaCapitalVO vo = new BloqueioContaCapitalVO();
		vo.setAtivo(Boolean.TRUE);
		vo.setCodSituacaoBloqueio(random.nextInt());
		vo.setCodTipoBloqueio(random.nextInt());
		vo.setCpfCnpj(""+random.nextInt());
		vo.setDataBloqueio(new DateTimeDB());
		vo.setDataDesbloqueio(new DateTimeDB());
		vo.setDataProtocolo(new DateTimeDB());
		vo.setIdBloqueio(random.nextInt());
		vo.setIdContaCapital(random.nextInt());
		vo.setIdInstituicao(random.nextInt());
		vo.setIdTipoBloqueio(random.nextInt());
		vo.setNomePessoa(""+random.nextInt());
		vo.setNomeTipoBloqueio(""+random.nextInt());
		vo.setNumContaCapital(random.nextInt());
		vo.setNumProcesso(""+random.nextInt());
		vo.setNumProtocolo(""+random.nextInt());
		vo.setValorBloqueado(BigDecimal.ONE);
		vo.setValorDesbloqueio(BigDecimal.ZERO);
		return vo;
	}
	
	private void assertEquals(BloqueioContaCapitalDTO dto, BloqueioContaCapitalVO vo) {
		Assert.assertEquals(dto.getAtivo(), vo.getAtivo());
		Assert.assertEquals(dto.getCodSituacaoBloqueio(), vo.getCodSituacaoBloqueio());
		Assert.assertEquals(dto.getCodTipoBloqueio(), vo.getCodTipoBloqueio());
		Assert.assertEquals(dto.getCpfCnpj(), vo.getCpfCnpj());
		Assert.assertEquals(dto.getDataBloqueio(), vo.getDataBloqueio());
		Assert.assertEquals(dto.getDataDesbloqueio(), vo.getDataDesbloqueio());
		Assert.assertEquals(dto.getDataProtocolo(), vo.getDataProtocolo());
		Assert.assertEquals(dto.getIdBloqueio(), vo.getIdBloqueio());
		Assert.assertEquals(dto.getIdContaCapital(), vo.getIdContaCapital());
		Assert.assertEquals(dto.getIdInstituicao(), vo.getIdInstituicao());
		Assert.assertEquals(dto.getIdTipoBloqueio(), vo.getIdTipoBloqueio());
		Assert.assertEquals(dto.getNomePessoa(), vo.getNomePessoa());
		Assert.assertEquals(dto.getNomeTipoBloqueio(), vo.getNomeTipoBloqueio());
		Assert.assertEquals(dto.getNumContaCapital(), vo.getNumContaCapital());
		Assert.assertEquals(dto.getNumProcesso(), vo.getNumProcesso());
		Assert.assertEquals(dto.getNumProtocolo(), vo.getNumProtocolo());
		Assert.assertEquals(dto.getValorBloqueado(), vo.getValorBloqueado());
		Assert.assertEquals(dto.getValorDesbloqueio(), vo.getValorDesbloqueio());
	}
	
	@Test
	public void converterListaDTOParaVO() throws BancoobException {
		List<BloqueioContaCapitalDTO> dtos = new ArrayList<BloqueioContaCapitalDTO>();
		dtos.add(criarDTO());
		dtos.add(criarDTO());
		List<BloqueioContaCapitalVO> vos = conversor.converterListaDTOParaVO(dtos);
		assertListaEquals(dtos, vos);
	}
	
	@Test
	public void converterListaVOParaDTO() throws BancoobException {
		List<BloqueioContaCapitalVO> vos = new ArrayList<BloqueioContaCapitalVO>();
		vos.add(criarVO());
		vos.add(criarVO());
		List<BloqueioContaCapitalDTO> dtos = conversor.converterListaVOParaDTO(vos);
		assertListaEquals(dtos, vos);
	}

	private void assertListaEquals(List<BloqueioContaCapitalDTO> dtos, List<BloqueioContaCapitalVO> vos) {
		Assert.assertEquals(dtos.size(), vos.size());
		for (int i = 0; i < dtos.size(); i++) {
			assertEquals(dtos.get(i), vos.get(i));
		}
	}
	
}
