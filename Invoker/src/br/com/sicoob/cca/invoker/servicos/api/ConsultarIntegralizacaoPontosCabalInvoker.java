package br.com.sicoob.cca.invoker.servicos.api;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.invoker.ContaCapitalInvoker;
import br.com.sicoob.sisbr.cca.api.negocio.dto.ConsultaIntegralizacaoCapitalCabalDTO;
import br.com.sicoob.sisbr.cca.api.negocio.dto.ConsultaIntegralizacaoCapitalCabalRetornoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.IntegralizacaoCapitalServico;

/**
 * Invoker para o servico de consulta de integralizacao de pontos cabal.
 * @author Nairon.Silva
 */
public class ConsultarIntegralizacaoPontosCabalInvoker extends ContaCapitalInvoker {

	private static final Logger LOG = Logger.getLogger(ConsultarIntegralizacaoPontosCabalInvoker.class.getName());
	
	private static final String LOOKUP_NAME = "APIContaCapital/IntegralizacaoCapitalServicoRemote";
	
	private static final Integer NUM_COOPERATIVA = 3008;
	
	@Override
	protected void executar() throws BancoobException {
		final int ano = 2018;
		final int dia = 26;
		IntegralizacaoCapitalServico servico = criarServico(IntegralizacaoCapitalServico.class);
		ConsultaIntegralizacaoCapitalCabalDTO dto = new ConsultaIntegralizacaoCapitalCabalDTO();
		dto.setDataIntegralizacao(new DateTimeDB(new GregorianCalendar(ano, Calendar.APRIL, dia).getTime().getTime()));
		dto.setIdOperacaoCabal("TESTE-CABAL");
		dto.setNumCooperativa(NUM_COOPERATIVA);
		LOG.info("Consultando...");
		List<ConsultaIntegralizacaoCapitalCabalRetornoDTO> dtos = servico.consultarIntegralizacaoPontosCabal(dto);
		for (ConsultaIntegralizacaoCapitalCabalRetornoDTO dtoRet : dtos) {
			LOG.info(dtoRet.getIdOperacaoCabal() + " | " + dtoRet.getIdOperacaoContaCapital() + " | " 
					+ dtoRet.getNumCpfCnpj() + " | " + dtoRet.getNumCooperativa() + " | " + dtoRet.getDataHoraOperacaoContaCapital());
		}
	}
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		new ConsultarIntegralizacaoPontosCabalInvoker().invoke(LOOKUP_NAME);
	}

}
