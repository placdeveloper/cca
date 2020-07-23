package br.com.sicoob.sisbr.cca.apirest.servicos.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.apirest.dtos.IntegralizacaoRecursoDTO;
import br.com.sicoob.sisbr.cca.apirest.dtos.IntegralizacaoRecursoRetornoDTO;

public interface IntegralizacaoContaCapitalServicoLocal {

	/**
	 * A integralização de capital consiste em uma operação em par(subscrição e integralização)
	 * 1 - Valor subscrito sempre é lançado
	 * 2 - Valor integralizado é lançado apenas para os históricos que não dependem de uma segunda ação em um outro canal (caixa, remessa folha) 
	 * 3 - Para o saldo residual (subsc>integ) são criadas parcelas no mesmo histórico da integralização solicitada
	 * 4 - Para históricos que não aceitam integralização à vista (que depende de uma segunda ação feita em um canal) é criado o parcelamento para essa integralização
	 * 5 - Para históricos com vinculo em cco, é feito lançamento a débito na cco
	 * 6 - Aceita apenas tipos específicos de históricos mapaTipoIntegralizacaoLimiteIntegralizacao
	 * 7 - Aceita apenas uma integralização na vida para tipos especificos mapaTipoIntegralizacaoLimiteIntegralizacao valor "1"
	 * 
	 * @author Marcos.Balbi 
	 */		
	List<IntegralizacaoRecursoRetornoDTO> integralizarCapital(IntegralizacaoRecursoDTO dto) throws BancoobException;	
}
