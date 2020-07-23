package br.com.sicoob.sisbr.cca.comum.servicos;

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
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.sicoob.cca.comum.negocio.delegates.PesquisaContaCapitalDelegate;
import br.com.sicoob.cca.comum.negocio.dto.PesquisaContaCapitalDTO;
import br.com.sicoob.sisbr.cca.comum.vo.PesquisaContaCapitalVO;

public class PesquisaContaCapitalServicoTest extends Mockito {

	@InjectMocks
	private PesquisaContaCapitalServico servico;
	
	@Mock
	private PesquisaContaCapitalDelegate pesquisaDelegate;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		InformacoesUsuario info = new InformacoesUsuario();
		info.setIdInstituicao("1");
		InformacoesUsuario.INSTANCIA.set(info);
	}
	
	@Test
	public void pesquisarTest() throws BancoobException {
		RequisicaoReqDTO dto = new RequisicaoReqDTO();
		dto.getDados().put("pesquisaContaCapitalVO", new PesquisaContaCapitalVO());
		
		List<PesquisaContaCapitalDTO> list = new ArrayList<PesquisaContaCapitalDTO>();
		list.add(new PesquisaContaCapitalDTO());
		when(pesquisaDelegate.pesquisar(any(PesquisaContaCapitalDTO.class))).thenReturn(list);
		
		RetornoDTO retorno = servico.pesquisar(dto);
		Assert.assertTrue(retorno.getDados().containsKey("numRegistroPagina"));
		Assert.assertTrue(retorno.getDados().containsKey("numTotalRegistro"));
		Assert.assertTrue(retorno.getDados().containsKey("numTotalPaginas"));
		Assert.assertTrue(retorno.getDados().containsKey("lstDadosRetorno"));
	}
	
}
