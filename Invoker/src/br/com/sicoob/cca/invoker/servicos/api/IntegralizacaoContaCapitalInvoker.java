package br.com.sicoob.cca.invoker.servicos.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.invoker.ContaCapitalInvoker;
import br.com.sicoob.sisbr.cca.api.negocio.dto.EstornoRateioDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.IntegralizacaoCapitalBoletoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.IntegralizacaoCapitalCabalDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.IntegralizacaoCapitalCabalRetornoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.IntegralizacaoCapitalCartaoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.IntegralizacaoCapitalRateioDTO;
import br.com.sicoob.sisbr.cca.api.negocio.enums.EnumTipoLiquidacao;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.IntegralizacaoCapitalServico;

/**
 * Invoker para o servico de integralizacao da API
 * @author Nairon.Silva
 */
public class IntegralizacaoContaCapitalInvoker extends ContaCapitalInvoker {

	private static final Logger LOG = Logger.getLogger(IntegralizacaoContaCapitalInvoker.class.getName());
	
	private static final String LOOKUP_NAME = "APIContaCapital/IntegralizacaoCapitalServicoRemote";

	private static final int NUM_COOPERATIVA = 3008;
	private static final int ID_INSTITUICAO = 29;
	private static final int ID_CONTA_CAPITAL = 1528377;
	private static final int NUM_CONTA_CAPITAL = 50176;
	private static final int ID_CONTA_CAPITAL2 = 1528373;
	private static final int QTD_PARCELAS = 2;
	private static final BigDecimal VALOR_INTEGRALIZACAO = BigDecimal.valueOf(10);
	private static final BigDecimal VALOR_SUBSCRICAO = BigDecimal.valueOf(10);
	private static final Long NUM_CONTA_CORRENTE = 907790L;
	private static final String NUM_CPF = "45180806887";

	@Override
	protected void executar() throws BancoobException {
		IntegralizacaoCapitalServico servico = criarServico(IntegralizacaoCapitalServico.class);
		LOG.info("Iniciando chamadas da API");
		integralizarBoleto(servico);
		integralizarCartao(servico);
		integralizarCabal(servico);
		integralizarRateio(servico);
		LOG.info("Fim");
	}
	
	private void integralizarRateio(IntegralizacaoCapitalServico servico) throws BancoobException {
		LOG.info("> Rateio");
		EstornoRateioDTO estornoDto = new EstornoRateioDTO();
		estornoDto.setIdUsuario("RATUSR-E");
		
		List<IntegralizacaoCapitalRateioDTO> dtos = new ArrayList<IntegralizacaoCapitalRateioDTO>();
		
		IntegralizacaoCapitalRateioDTO dto = new IntegralizacaoCapitalRateioDTO();
		dto.setDataCalculo(new Date());
		dto.setIdContaCapital(ID_CONTA_CAPITAL);
		dto.setIdInstituicao(ID_INSTITUICAO);
		dto.setIdUsuario("RATUSR");
		dto.setValorLancamento(BigDecimal.valueOf(ContaCapitalConstantes.NUMERO_CEM));
		dtos.add(dto);
		
		LOG.info("Integralizando 1");
		estornoDto.addChaveRateio(servico.integralizarRateio(dtos));
		
		dtos.clear();
		dto = new IntegralizacaoCapitalRateioDTO();
		dto.setDataCalculo(new Date());
		dto.setIdContaCapital(ID_CONTA_CAPITAL2);
		dto.setIdInstituicao(ID_INSTITUICAO);
		dto.setIdUsuario("RATUSR");
		dto.setValorLancamento(VALOR_INTEGRALIZACAO);
		dtos.add(dto);
		
		LOG.info("Integralizando 2");
		estornoDto.addChaveRateio(servico.integralizarRateio(dtos));
		
		LOG.info("Chaves rateio: "+estornoDto.getChavesRateio());
		
		LOG.info("Estornando");
		servico.estornarRateio(estornoDto);
	}

	private void integralizarCabal(IntegralizacaoCapitalServico servico) throws BancoobException {
		LOG.info("> Cabal");
		IntegralizacaoCapitalCabalDTO dto = new IntegralizacaoCapitalCabalDTO();
		dto.setIdOperacaoCabal("CABAL-"+NUM_CONTA_CAPITAL);
		dto.setNumCooperativa(NUM_COOPERATIVA);
		dto.setNumCpfCnpj(NUM_CPF);
		dto.setValorIntegralizacao(VALOR_INTEGRALIZACAO);
		IntegralizacaoCapitalCabalRetornoDTO ret = servico.integralizarPontosCabal(dto);
		LOG.info("Ret: "+ret);
	}

	private void integralizarCartao(IntegralizacaoCapitalServico servico) throws BancoobException {
		LOG.info("> Cartao");
		IntegralizacaoCapitalCartaoDTO dto = new IntegralizacaoCapitalCartaoDTO();
		dto.setIdInstituicao(ID_INSTITUICAO);
		dto.setIdOperacao("TESTE-CARTAO");
		dto.setNumContaCapital(NUM_CONTA_CAPITAL);
		dto.setValorIntegralizacao(VALOR_INTEGRALIZACAO);
		dto.setValorSubscricao(VALOR_SUBSCRICAO);
		servico.integralizarCartaoCredito(dto);
	}

	private void integralizarBoleto(IntegralizacaoCapitalServico servico) throws BancoobException {
		LOG.info("> Boleto");
		IntegralizacaoCapitalBoletoDTO dto = new IntegralizacaoCapitalBoletoDTO();
		dto.setDataInicioParcelamento(new DateTimeDB(LocalDate.now().plusMonths(1).toDate().getTime()));
		dto.setNumContaCorrente(NUM_CONTA_CORRENTE);
		dto.setIdInstituicao(ID_INSTITUICAO);
		dto.setIdOperacao("TESTE-PARCELAS-"+NUM_CONTA_CAPITAL);
		dto.setNumContaCapital(NUM_CONTA_CAPITAL);
		dto.setQtdeParcelas(QTD_PARCELAS);
		dto.setTipoLiquidacao(EnumTipoLiquidacao.COD_BOLETO_LIQ_CCO);
		dto.setValorIntegralizacao(VALOR_INTEGRALIZACAO);
		dto.setValorSubscricao(VALOR_SUBSCRICAO);
		String ret = servico.integralizarBoletoBancario(dto);
		LOG.info("Ret: "+ret);
	}

	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		new IntegralizacaoContaCapitalInvoker().invoke(LOOKUP_NAME);
	}
	
}
