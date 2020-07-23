package br.com.sicoob.cca.invoker.servicos.api;

import java.math.BigDecimal;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.invoker.ContaCapitalInvoker;
import br.com.sicoob.sisbr.cca.api.negocio.dto.ContaCapitalDebIndeterminadoDTO;
import br.com.sicoob.sisbr.cca.api.negocio.servicos.DebIndeterminadoContaCapitalServico;

/**
 * DebIndeterminadoContaCapitalInvoker
 */
public class DebIndeterminadoContaCapitalInvoker extends ContaCapitalInvoker {

	private static final String LOOKUP_NAME = "APIContaCapital/DebIndeterminadoContaCapitalServicoRemote";
	
	private static final Integer DIA_DEBITO = 22;
	private static final Integer ID_INSTITUICAO = 29;
	private static final Long NUM_CONTA_CORRENTE = 19L;
	private static final Integer NUM_MATRICULA = 1;
	private static final BigDecimal VALOR_DEBITO = BigDecimal.valueOf(20);
	
	@Override
	protected void executar() throws BancoobException {
		DebIndeterminadoContaCapitalServico servico = criarServico(DebIndeterminadoContaCapitalServico.class);
		
		ContaCapitalDebIndeterminadoDTO dto = new ContaCapitalDebIndeterminadoDTO();
		dto.setDiaDebitoMensal(DIA_DEBITO.shortValue());
		dto.setIdInstituicao(ID_INSTITUICAO);
		dto.setNumContaCorrente(NUM_CONTA_CORRENTE);
		dto.setNumMatricula(NUM_MATRICULA);
		dto.setValorDebito(VALOR_DEBITO);
		
		servico.incluirDebitoIndeterminado(dto);
	}
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		new DebIndeterminadoContaCapitalInvoker().invoke(LOOKUP_NAME);
	}
	
}
