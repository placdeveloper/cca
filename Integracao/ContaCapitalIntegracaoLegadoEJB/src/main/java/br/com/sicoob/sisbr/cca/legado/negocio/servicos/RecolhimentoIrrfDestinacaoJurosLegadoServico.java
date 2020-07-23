package br.com.sicoob.sisbr.cca.legado.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RecolhimentoIrrfDestinacaoJurosLegadoDTO;

public interface RecolhimentoIrrfDestinacaoJurosLegadoServico extends ContaCapitalIntegracaoLegadoServico {
	 List<RecolhimentoIrrfDestinacaoJurosLegadoDTO> gerarRelatorioRecolhimentoIrrfDestinacaoJuros(RecolhimentoIrrfDestinacaoJurosLegadoDTO dtoEntrada) throws BancoobException;
}
