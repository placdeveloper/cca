package br.com.sicoob.sisbr.cca.comum.conversor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.sicoob.cca.comum.negocio.dto.PesquisaContaCapitalDTO;
import br.com.sicoob.sisbr.cca.comum.vo.PesquisaContaCapitalVO;

public class ConversorPesquisaContaCapitalTest {

	private static final Integer UM = 1;
	
	private ConversorPesquisaContaCapital conversor = new ConversorPesquisaContaCapital();
	
	@Test
	public void voParaDtoTest() {
		PesquisaContaCapitalVO vo = new PesquisaContaCapitalVO();
		PesquisaContaCapitalDTO dto = new PesquisaContaCapitalDTO();
		vo.setCpfCnpj("cpfCnpj");
		vo.setDescSituacaoContaCapital("descSituacaoContaCapital");
		vo.setIdInstituicao(UM);
		vo.setIdPessoa(UM);
		vo.setIdPessoaLegado(UM);
		vo.setIdSituacaoCadastro(UM);
		vo.setIdSituacaoContaCapital(UM);
		vo.setNome("nome");
		vo.setNumContaCapital(UM);
		vo.setIdContaCapital(UM);
		conversor.voParaDto(dto, vo);
		assertVoDtoEquals(vo, dto);
	}
	
	@Test
	public void dtoParaVoTest() {
		PesquisaContaCapitalVO vo = new PesquisaContaCapitalVO();
		PesquisaContaCapitalDTO dto = new PesquisaContaCapitalDTO();
		dto.setCpfCnpj("cpfCnpj");
		dto.setDescSituacaoContaCapital("descSituacaoContaCapital");
		dto.setIdInstituicao(UM);
		dto.setIdPessoa(UM);
		dto.setIdPessoaLegado(UM);
		dto.setIdSituacaoCadastro(UM);
		dto.setIdSituacaoContaCapital(UM);
		dto.setNome("nome");
		dto.setNumContaCapital(UM);
		dto.setIdContaCapital(UM);
		conversor.dtoParaVo(dto, vo);
		assertVoDtoEquals(vo, dto);
	}
	
	@Test
	public void dtoParaVoListTest() {
		List<PesquisaContaCapitalDTO> dtos = new ArrayList<PesquisaContaCapitalDTO>();
		PesquisaContaCapitalDTO dto = new PesquisaContaCapitalDTO();
		dto.setCpfCnpj("cpfCnpj");
		dto.setDescSituacaoContaCapital("descSituacaoContaCapital");
		dto.setIdInstituicao(UM);
		dto.setIdPessoa(UM);
		dto.setIdPessoaLegado(UM);
		dto.setIdSituacaoContaCapital(UM);
		dto.setNome("nome");
		dto.setNumContaCapital(UM);
		dto.setIdContaCapital(UM);
		dtos.add(dto);
		List<PesquisaContaCapitalVO> vos = conversor.dtoParaVo(dtos);
		Assert.assertNotNull(vos);
		Assert.assertFalse(vos.isEmpty());
		assertVoDtoEquals(vos.get(0), dtos.get(0));
	}

	private void assertVoDtoEquals(PesquisaContaCapitalVO vo, PesquisaContaCapitalDTO dto) {
		Assert.assertEquals(vo.getCpfCnpj(), dto.getCpfCnpj());
		Assert.assertEquals(vo.getDescSituacaoContaCapital(), dto.getDescSituacaoContaCapital());
		Assert.assertEquals(vo.getIdInstituicao(), dto.getIdInstituicao());
		Assert.assertEquals(vo.getIdPessoa(), dto.getIdPessoa());
		Assert.assertEquals(vo.getIdPessoaLegado(), dto.getIdPessoaLegado());
		Assert.assertEquals(vo.getIdSituacaoCadastro(), dto.getIdSituacaoCadastro());
		Assert.assertEquals(vo.getIdSituacaoContaCapital(), dto.getIdSituacaoContaCapital());
		Assert.assertEquals(vo.getNome(), dto.getNome());
		Assert.assertEquals(vo.getNumContaCapital(), dto.getNumContaCapital());
		Assert.assertEquals(vo.getIdContaCapital(), dto.getIdContaCapital());
	}
	
}
