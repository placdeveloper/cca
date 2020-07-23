package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.PropostaSubscricaoServicoLocal;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.PropostaSubscricao;
import br.com.sicoob.cca.entidades.negocio.entidades.TipoIntegralizacao;
import br.com.sicoob.cca.relatorios.negocio.dto.RelFichaPropostaMatriculaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.BemPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.EnderecoPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.FonteRendaPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.LocalizacaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaFisicaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaJuridicaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ReferenciaPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.RelacionamentoPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.TelefonePessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.TipoGrauCooperativaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.LocalizacaoIntegracaoServicoLocal;

/**
 * A Classe RelFichaPropostaMatriculaServicoEJBTest.
 */
public class RelFichaPropostaMatriculaServicoEJBTest extends Mockito {

	@Mock
	private CapesIntegracaoServicoLocal capesInt;
	
	@Mock
	private LocalizacaoIntegracaoServicoLocal locInt;
	
	@Mock
	private InstituicaoIntegracaoServicoLocal instInt;

	@Mock
	private ContaCapitalServicoLocal ccaServico;
	
	@Mock
	private PropostaSubscricaoServicoLocal propostaServico;
	
	@InjectMocks
	private RelFichaPropostaMatriculaServicoEJB ejb = new RelFichaPropostaMatriculaServicoEJB();

	@Before
	public void inicializaMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@BeforeClass
	public static void setUp() {
		InformacoesUsuario info = new InformacoesUsuario();
		info.setIdInstituicao("0");
		info.setPac("0");
		info.setLogin("Teste");
		InformacoesUsuario.INSTANCIA.set(info);
	}
	
	/**
	 * O método Test gerar relatorio ficha proposta matricula.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Ignore
	public void testGerarRelatorioFichaPropostaMatricula() throws Exception{
		
		RelFichaPropostaMatriculaDTO dto = new RelFichaPropostaMatriculaDTO();
		
		PessoaIntegracaoDTO pessoaIntegracaoDTO = new PessoaIntegracaoDTO();
		PessoaIntegracaoDTO pessoaIntegracaoDTOPJ = new PessoaIntegracaoDTO();
		PessoaFisicaIntegracaoDTO pessoaFisicaIntegracaoDTO = new PessoaFisicaIntegracaoDTO();
		EnderecoPessoaIntegracaoDTO enderecoPessoaIntegracaoDTO = new EnderecoPessoaIntegracaoDTO();
		TelefonePessoaIntegracaoDTO telefonePessoaIntegracaoDTO = new TelefonePessoaIntegracaoDTO();
		FonteRendaPessoaIntegracaoDTO fonteRendaPessoaIntegracaoDTO = new FonteRendaPessoaIntegracaoDTO();
		PessoaJuridicaIntegracaoDTO pessoaJuridicaIntegracaoDTO = new PessoaJuridicaIntegracaoDTO();
		List<RelacionamentoPessoaIntegracaoDTO> relacionamentoConjugePessoaIntegracaoDTO = new ArrayList<RelacionamentoPessoaIntegracaoDTO>();
		List<RelacionamentoPessoaIntegracaoDTO> relacionamentoRepresentanteLegalPessoaIntegracaoDTO = new ArrayList<RelacionamentoPessoaIntegracaoDTO>();
		List<RelacionamentoPessoaIntegracaoDTO> relacionamentoResponsavelLegalPessoaIntegracaoDTO = new ArrayList<RelacionamentoPessoaIntegracaoDTO>();
		ReferenciaPessoaIntegracaoDTO referenciaPessoaIntegracaoDTO = new ReferenciaPessoaIntegracaoDTO();
		BemPessoaIntegracaoDTO bemPessoaIntegracaoDTO = new BemPessoaIntegracaoDTO();
		
		RelacionamentoPessoaIntegracaoDTO relacionamentoPessoaIntegracaoDTO = new RelacionamentoPessoaIntegracaoDTO();
		relacionamentoPessoaIntegracaoDTO.setTipoRepresentanteLegal("OUTROS DECLARANTES NÃO ESPECIFICADOS NOS GRUPOS ANTERIORES");
		
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		
		Mockito.when(capesInt.obterPessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(pessoaIntegracaoDTO);
		Mockito.when(capesInt.obterPessoaJuridicaFormaConstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(pessoaIntegracaoDTOPJ);
		Mockito.when(capesInt.obterPessoaFisicaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(pessoaFisicaIntegracaoDTO);
		Mockito.when(capesInt.obterEnderecoPessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(enderecoPessoaIntegracaoDTO);
		Mockito.when(capesInt.obterTelefonePessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(telefonePessoaIntegracaoDTO);
		Mockito.when(capesInt.obterFonteRendaPessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(fonteRendaPessoaIntegracaoDTO);
		Mockito.when(capesInt.obterPessoaJuridicaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(pessoaJuridicaIntegracaoDTO);
		Mockito.when(capesInt.obterConjugePessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(relacionamentoConjugePessoaIntegracaoDTO);
		Mockito.when(capesInt.obterRepresentantesLegaisPessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(relacionamentoRepresentanteLegalPessoaIntegracaoDTO);
		Mockito.when(capesInt.obterResponsavelLegalPessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(relacionamentoResponsavelLegalPessoaIntegracaoDTO);
		Mockito.when(capesInt.obterReferenciaPessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(referenciaPessoaIntegracaoDTO);
		Mockito.when(capesInt.obterBemPessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(bemPessoaIntegracaoDTO);
		
		pessoaIntegracaoDTO.setCodTipoPessoa(Short.valueOf("0"));
		
		PropostaSubscricao proposta = new PropostaSubscricao();
		Mockito.when(propostaServico.obter(Mockito.anyInt())).thenReturn(proposta);
		
		InstituicaoIntegracaoDTO instituicaoIntegracaoDTO = new InstituicaoIntegracaoDTO();
		Mockito.when(instInt.obterInstituicaoIntegracao(Mockito.anyInt())).thenReturn(instituicaoIntegracaoDTO);
		
		TipoGrauCooperativaDTO tipoGrauCooperativaDTO = new TipoGrauCooperativaDTO();
		tipoGrauCooperativaDTO.setCodTipoGrauCoop(Integer.valueOf("1"));
		
		Mockito.when(instInt.obterInformacoesInstituicaoSCI(Mockito.anyInt(), Mockito.any(String.class))).thenReturn(instituicaoIntegracaoDTO);
		
		proposta.setQtdParcelaProposta(1);
		proposta.setValorParcelaProposta(new BigDecimal(1));
		
		TipoIntegralizacao tipoIntegralizacao = new TipoIntegralizacao();
		tipoIntegralizacao.setDescricao("desc");
		proposta.setTipoIntegralizacao(tipoIntegralizacao);
		
		ContaCapital cca = new ContaCapital();
		cca.setNumContaCapital(1000);
		cca.setIdPessoa(33050);
		cca.setIdInstituicao(378);
		Mockito.when(ccaServico.obter(Mockito.anyInt())).thenReturn(cca);
		
		dto.setIdContaCapital(cca.getId());
		dto.setNumContaCapital(cca.getNumContaCapital());
		dto.setIdPessoa(pessoaIntegracaoDTO.getIdPessoa());		
		dto.setIdInstituicao(instituicaoIntegracaoDTO.getIdInstituicao());
		Object relatorio = ejb.gerarRelatorioFichaPropostaMatricula(dto);
		Assert.assertNotNull(relatorio);
	}
	
	/**
	 * O método Test gerar relatorio ficha proposta matricula pj.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Ignore
	public void testGerarRelatorioFichaPropostaMatriculaPJ() throws Exception{
		
		RelFichaPropostaMatriculaDTO dto = new RelFichaPropostaMatriculaDTO();
		
		PessoaIntegracaoDTO pessoaIntegracaoDTO = new PessoaIntegracaoDTO();
		PessoaIntegracaoDTO pessoaIntegracaoDTOPJ = new PessoaIntegracaoDTO();
		PessoaFisicaIntegracaoDTO pessoaFisicaIntegracaoDTO = new PessoaFisicaIntegracaoDTO();
		EnderecoPessoaIntegracaoDTO enderecoPessoaIntegracaoDTO = new EnderecoPessoaIntegracaoDTO();
		TelefonePessoaIntegracaoDTO telefonePessoaIntegracaoDTO = new TelefonePessoaIntegracaoDTO();
		FonteRendaPessoaIntegracaoDTO fonteRendaPessoaIntegracaoDTO = new FonteRendaPessoaIntegracaoDTO();
		PessoaJuridicaIntegracaoDTO pessoaJuridicaIntegracaoDTO = new PessoaJuridicaIntegracaoDTO();
		List<RelacionamentoPessoaIntegracaoDTO> relacionamentoConjugePessoaIntegracaoDTO = new ArrayList<RelacionamentoPessoaIntegracaoDTO>();
		List<RelacionamentoPessoaIntegracaoDTO> relacionamentoRepresentanteLegalPessoaIntegracaoDTO = new ArrayList<RelacionamentoPessoaIntegracaoDTO>();
		List<RelacionamentoPessoaIntegracaoDTO> relacionamentoResponsavelLegalPessoaIntegracaoDTO = new ArrayList<RelacionamentoPessoaIntegracaoDTO>();
		ReferenciaPessoaIntegracaoDTO referenciaPessoaIntegracaoDTO = new ReferenciaPessoaIntegracaoDTO();
		BemPessoaIntegracaoDTO bemPessoaIntegracaoDTO = new BemPessoaIntegracaoDTO();
		
		RelacionamentoPessoaIntegracaoDTO relacionamentoPessoaIntegracaoDTO = new RelacionamentoPessoaIntegracaoDTO();
		relacionamentoPessoaIntegracaoDTO.setTipoRepresentanteLegal("OUTROS");
		
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		relacionamentoRepresentanteLegalPessoaIntegracaoDTO.add(relacionamentoPessoaIntegracaoDTO);
		
		pessoaFisicaIntegracaoDTO.setNomePai("nome pai");
		pessoaFisicaIntegracaoDTO.setNomeMae("nome mãe");
		pessoaFisicaIntegracaoDTO.setDescProfissao("OUTROS DECLARANTES NÃO ESPECIFICADOS NOS GRUPOS ANTERIORES");
		
		RelacionamentoPessoaIntegracaoDTO conjuge = new RelacionamentoPessoaIntegracaoDTO();
		conjuge.setNomeConjuge("nome conjuge");
		conjuge.setCpfConjuge("cpf conjuge");
		relacionamentoConjugePessoaIntegracaoDTO.add(conjuge);
		
		RelacionamentoPessoaIntegracaoDTO responsavelLegal = new RelacionamentoPessoaIntegracaoDTO();
		responsavelLegal.setNomeResponsavelLegal("nome responsável legal");
		responsavelLegal.setCpfResponsavelLegal("cpf responsável legal");
		relacionamentoResponsavelLegalPessoaIntegracaoDTO.add(responsavelLegal);
		
		BigDecimal valorTotalBens = new BigDecimal(10);
		bemPessoaIntegracaoDTO.setValorTotalBens(valorTotalBens);
		
		pessoaFisicaIntegracaoDTO.setIdNaturalidade(1);
		enderecoPessoaIntegracaoDTO.setIdLocResidencial(1);
		enderecoPessoaIntegracaoDTO.setIdLocComercial(1);
		
		LocalizacaoIntegracaoDTO localizacaoIntegracaoDTO = new LocalizacaoIntegracaoDTO();
		localizacaoIntegracaoDTO.setMunicipio("municipio");
		localizacaoIntegracaoDTO.setUf("uf");
		Mockito.when(locInt.consultarLocalidade(Mockito.anyInt())).thenReturn(localizacaoIntegracaoDTO);
		
		Mockito.when(capesInt.obterPessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(pessoaIntegracaoDTO);
		Mockito.when(capesInt.obterPessoaJuridicaFormaConstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(pessoaIntegracaoDTOPJ);
		Mockito.when(capesInt.obterPessoaFisicaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(pessoaFisicaIntegracaoDTO);
		Mockito.when(capesInt.obterEnderecoPessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(enderecoPessoaIntegracaoDTO);
		Mockito.when(capesInt.obterTelefonePessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(telefonePessoaIntegracaoDTO);
		Mockito.when(capesInt.obterFonteRendaPessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(fonteRendaPessoaIntegracaoDTO);
		Mockito.when(capesInt.obterPessoaJuridicaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(pessoaJuridicaIntegracaoDTO);
		Mockito.when(capesInt.obterConjugePessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(relacionamentoConjugePessoaIntegracaoDTO);
		Mockito.when(capesInt.obterRepresentantesLegaisPessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(relacionamentoRepresentanteLegalPessoaIntegracaoDTO);
		Mockito.when(capesInt.obterResponsavelLegalPessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(relacionamentoResponsavelLegalPessoaIntegracaoDTO);
		Mockito.when(capesInt.obterReferenciaPessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(referenciaPessoaIntegracaoDTO);
		Mockito.when(capesInt.obterBemPessoaInstituicao(Mockito.anyInt(), Mockito.anyInt())).thenReturn(bemPessoaIntegracaoDTO);
		
		pessoaIntegracaoDTO.setCodTipoPessoa(Short.valueOf("1"));
		
		PropostaSubscricao proposta = new PropostaSubscricao();
		Mockito.when(propostaServico.obter(Mockito.anyInt())).thenReturn(proposta);
		
		InstituicaoIntegracaoDTO instituicaoIntegracaoDTO = new InstituicaoIntegracaoDTO();
		Mockito.when(instInt.obterInstituicaoIntegracao(Mockito.anyInt())).thenReturn(instituicaoIntegracaoDTO);
		
		TipoGrauCooperativaDTO tipoGrauCooperativaDTO = new TipoGrauCooperativaDTO();
		tipoGrauCooperativaDTO.setCodTipoGrauCoop(Integer.valueOf("1"));
		
		Mockito.when(instInt.obterInformacoesInstituicaoSCI(Mockito.anyInt(), Mockito.any(String.class))).thenReturn(instituicaoIntegracaoDTO);
		
		proposta.setQtdParcelaProposta(1);
		proposta.setValorParcelaProposta(new BigDecimal(1));
		
		TipoIntegralizacao tipoIntegralizacao = new TipoIntegralizacao();
		tipoIntegralizacao.setDescricao("desc");
		proposta.setTipoIntegralizacao(tipoIntegralizacao);
		
		ContaCapital cca = new ContaCapital();
		cca.setNumContaCapital(1000);
		cca.setIdPessoa(33050);
		cca.setIdInstituicao(378);
		Mockito.when(ccaServico.obter(Mockito.anyInt())).thenReturn(cca);
		
		dto.setIdContaCapital(cca.getId());
		dto.setNumContaCapital(cca.getNumContaCapital());
		dto.setIdPessoa(pessoaIntegracaoDTO.getIdPessoa());
		dto.setIdInstituicao(instituicaoIntegracaoDTO.getIdInstituicao());
		
		
		Object relatorio = ejb.gerarRelatorioFichaPropostaMatricula(dto);
		Assert.assertNotNull(relatorio);
	}
	
}