package br.com.sicoob.sisbr.cca.apirest.servicos.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.apirest.dtos.IntegralizacaoRecursoDTO;
import br.com.sicoob.sisbr.cca.apirest.dtos.IntegralizacaoRecursoRetornoDTO;

public interface IntegralizacaoContaCapitalServicoLocal {

	/**
	 * A integraliza��o de capital consiste em uma opera��o em par(subscri��o e integraliza��o)
	 * 1 - Valor subscrito sempre � lan�ado
	 * 2 - Valor integralizado � lan�ado apenas para os hist�ricos que n�o dependem de uma segunda a��o em um outro canal (caixa, remessa folha) 
	 * 3 - Para o saldo residual (subsc>integ) s�o criadas parcelas no mesmo hist�rico da integraliza��o solicitada
	 * 4 - Para hist�ricos que n�o aceitam integraliza��o � vista (que depende de uma segunda a��o feita em um canal) � criado o parcelamento para essa integraliza��o
	 * 5 - Para hist�ricos com vinculo em cco, � feito lan�amento a d�bito na cco
	 * 6 - Aceita apenas tipos espec�ficos de hist�ricos mapaTipoIntegralizacaoLimiteIntegralizacao
	 * 7 - Aceita apenas uma integraliza��o na vida para tipos especificos mapaTipoIntegralizacaoLimiteIntegralizacao valor "1"
	 * 
	 * @author Marcos.Balbi 
	 */		
	List<IntegralizacaoRecursoRetornoDTO> integralizarCapital(IntegralizacaoRecursoDTO dto) throws BancoobException;	
}
