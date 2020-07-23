/*
 * 
 */
package br.com.sicoob.cca.comum.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.conexao.CorporativoDataSource;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.cca.comum.negocio.dto.PesquisaContaCapitalDTO;
import br.com.sicoob.cca.comum.persistencia.dao.ContaCapitalComumDao;
import br.com.sicoob.cca.comum.persistencia.dao.interfaces.PesquisaContaCapitalDao;

/**
 * @author Marco.Nascimento
 */
public class PesquisaContaCapitalDaoImpl extends ContaCapitalComumDao implements PesquisaContaCapitalDao {

	private static final Integer LIMITE_MIN_NOME = 3;
	private static final Integer SEM_FILTRO_SITUACAO_CADASTRO = 99;
	
	/**
	 * {@link ContaCapitalComumDao#pesquisarPorContaCapital(PesquisaContaCapitalDTO)}
	 */
	public List<PesquisaContaCapitalDTO> pesquisar(PesquisaContaCapitalDTO filtro) throws BancoobException {
		Comando comando = null;
		Connection conexao = null;
		PesquisaContaCapitalDTO dto = null;
		List<PesquisaContaCapitalDTO> lstRetorno = new ArrayList<PesquisaContaCapitalDTO>(0); 
		
		try {
			comando = getComando("PESQUISA_CONTACAPITAL");
			
			comando.adicionarVariavel("idInstituicao", filtro.getIdInstituicao());
			
			if(filtro.getNumContaCapital() != null && filtro.getNumContaCapital().intValue() > 0) {
				comando.adicionarVariavel("numContaCapital", filtro.getNumContaCapital());
			}
			
			if(filtro.getNome() != null && filtro.getNome().length() >= LIMITE_MIN_NOME) {
				comando.adicionarVariavel("nome", filtro.getNome());
			}
			
			if(filtro.getCpfCnpj() != null && filtro.getCpfCnpj().length() > 0) {
				comando.adicionarVariavel("cpfCnpj", filtro.getCpfCnpj());
			}
			
			if(filtro.getIdSituacaoContaCapital() != null) {
				
				if(filtro.getIdSituacaoContaCapital().intValue() != SEM_FILTRO_SITUACAO_CADASTRO) {
					
					comando.adicionarVariavel("idSituacaoContaCapital", filtro.getIdSituacaoContaCapital());
				}
				
			} else {
				comando.adicionarVariavel("idSituacaoContaCapital", 1); //Conta Capital Ativa
			}
			
			if(filtro.getIdSituacaoCadastro() != null) {
				
				if(filtro.getIdSituacaoCadastro().intValue() != SEM_FILTRO_SITUACAO_CADASTRO) {
					comando.adicionarVariavel("idSituacaoCadastro", filtro.getIdSituacaoCadastro());
				}
				
			} else {
				comando.adicionarVariavel("idSituacaoCadastro", 2); //Cadastro aprovado
			}
			
			comando.configurar();
			conexao = this.estabelecerConexao();
			
			ResultSet rs = comando.executarConsulta(conexao);
			while(rs.next()) {
				dto = new PesquisaContaCapitalDTO();
				dto.setCpfCnpj(rs.getString("NUMCPFCNPJ"));
				dto.setIdInstituicao(rs.getInt("IDINSTITUICAO"));
				dto.setIdPessoa(rs.getInt("IDPESSOA"));
				dto.setCodTipoPessoa(rs.getInt("CODTIPOPESSOA"));
				dto.setIdPessoaLegado(rs.getInt("IDPESSOALEGADO"));
				dto.setNome(rs.getString("NOMECOMPLETO"));
				dto.setDescSituacaoContaCapital(rs.getString("DESCSITUACAOCONTACAPITAL"));
				dto.setNumContaCapital(rs.getInt("NUMCONTACAPITAL"));
				dto.setIdContaCapital(rs.getInt("IDCONTACAPITAL"));
				
				lstRetorno.add(dto);
			}
			
		} catch (PersistenciaException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} catch (SQLException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new BancoobException(e);
		} finally {
			fecharComando(comando);
			fecharConexao(conexao);
		}
		
		return lstRetorno;
	}
	
	/**
	 * @see br.com.bancoob.persistencia.dao.BancoobDao#estabelecerConexao()
	 */
	@Override
	protected Connection estabelecerConexao() {
		CorporativoDataSource dataSource = null;
		try {
			dataSource = new CorporativoDataSource("jdbc/BancoobCCADS", System.getProperties());
			return dataSource.getConnection();
		} catch (SQLException e) {
			getLogger().erro(e, e.getMessage());
		}
		return null;
	}
}
