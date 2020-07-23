package br.com.sicoob.cca.relatorios.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.cca.relatorios.negocio.dto.FechRelPosicaoDiariaCarteiraDTO;
import br.com.sicoob.cca.relatorios.negocio.excecao.ContaCapitalRelatoriosException;
import br.com.sicoob.cca.relatorios.persistencia.dao.ContaCapitalRelatoriosDao;
import br.com.sicoob.cca.relatorios.persistencia.dao.interfaces.FechRelPosicaoDiariaCarteiraDao;

/**
* @author Ricardo.Barcante
*/
public class FechRelPosicaoDiariaCarteiraDaoImpl extends ContaCapitalRelatoriosDao implements FechRelPosicaoDiariaCarteiraDao {

	public FechRelPosicaoDiariaCarteiraDTO pesquisarPosicaoDiariaCarteira(
			FechRelPosicaoDiariaCarteiraDTO filtro) throws BancoobException{
		Comando comando = null;
		Connection conexao = null;
		ResultSet rs = null;
		
		FechRelPosicaoDiariaCarteiraDTO posicaoDiariaCarteiraDTO = null;
		try {		
			
			comando = getComando("FECHPOSICAODIARIACARTEIRA");
			comando.adicionarVariavel("filtro", filtro);
			comando.configurar();
			conexao = estabelecerConexao();
			rs = comando.executarConsulta(conexao);
			
			while(rs.next()) {
				posicaoDiariaCarteiraDTO =  new FechRelPosicaoDiariaCarteiraDTO();
				posicaoDiariaCarteiraDTO.setCarteira("001 - Conta Capital");
				
				posicaoDiariaCarteiraDTO.setCreditosIntegralizado(rs.getDouble("VALORINTEGRALIZADOCRED"));
				posicaoDiariaCarteiraDTO.setCreditosSubscrito(rs.getDouble("VALORSUBSCRITOCRED"));
				posicaoDiariaCarteiraDTO.setCreditosPagar(rs.getDouble("VALORAPAGARCRED"));
				posicaoDiariaCarteiraDTO.setCreditosRealizar(rs.getDouble("VALORREALIZARCRED"));
				
				posicaoDiariaCarteiraDTO.setSaldoAnteriorIntegralizado(rs.getDouble("VALORINTEGRALIZADOANT"));
				posicaoDiariaCarteiraDTO.setSaldoAnteriorSubscrito(rs.getDouble("VALORSUBSCRITOANT"));
				posicaoDiariaCarteiraDTO.setSaldoAnteriorPagar(rs.getDouble("VALORAPAGARANT"));
				posicaoDiariaCarteiraDTO.setSaldoAnteriorRealizar(rs.getDouble("VALORREALIZARANT"));
				
				posicaoDiariaCarteiraDTO.setSaldoAtualIntegralizado(rs.getDouble("VALORINTEGRALIZADO"));
				posicaoDiariaCarteiraDTO.setSaldoAtualSubscrito(rs.getDouble("VALORSUBSCRITO"));
				posicaoDiariaCarteiraDTO.setSaldoAtualPagar(rs.getDouble("VALORAPAGAR"));
				posicaoDiariaCarteiraDTO.setSaldoAtualRealizar(rs.getDouble("VALORREALIZAR"));
				
				posicaoDiariaCarteiraDTO.setDebitosIntegralizado(rs.getDouble("VALORINTEGRALIZADODEB"));
				posicaoDiariaCarteiraDTO.setDebitosSubscrito(rs.getDouble("VALORSUBSCRITODEB"));
				posicaoDiariaCarteiraDTO.setDebitosPagar(rs.getDouble("VALORAPAGARDEB"));
				posicaoDiariaCarteiraDTO.setDebitosRealizar(rs.getDouble("VALORREALIZARDEB"));
			}			
			
		} catch (Exception e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalRelatoriosException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return posicaoDiariaCarteiraDTO;
	}

}