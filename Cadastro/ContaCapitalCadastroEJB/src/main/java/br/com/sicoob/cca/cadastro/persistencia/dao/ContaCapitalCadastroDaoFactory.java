/*
 * 
 */
package br.com.sicoob.cca.cadastro.persistencia.dao;

import br.com.bancoob.persistencia.dao.BancoobDaoFactory;
import br.com.sicoob.cca.cadastro.persistencia.dao.impl.AgrupadorConfiguracaoCapitalDaoImpl;
import br.com.sicoob.cca.cadastro.persistencia.dao.impl.ConfiguracaoCapitalDaoImpl;
import br.com.sicoob.cca.cadastro.persistencia.dao.impl.ContaCapitalDaoImpl;
import br.com.sicoob.cca.cadastro.persistencia.dao.impl.DocumentoCapitalDaoImpl;
import br.com.sicoob.cca.cadastro.persistencia.dao.impl.PropostaSubscricaoDaoImpl;
import br.com.sicoob.cca.cadastro.persistencia.dao.impl.SituacaoCadastroPropostaDaoImpl;
import br.com.sicoob.cca.cadastro.persistencia.dao.impl.ValorConfiguracaoCapitalDaoImpl;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.AgrupadorConfiguracaoCapitalDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ConfiguracaoCapitalDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ContaCapitalDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.DocumentoCapitalDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.PropostaSubscricaoDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.SituacaoCadastroPropostaDao;
import br.com.sicoob.cca.cadastro.persistencia.dao.interfaces.ValorConfiguracaoCapitalDao;
import br.com.sicoob.cca.entidades.negocio.entidades.AgrupadorConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.ConfiguracaoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.DocumentoCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.PropostaSubscricao;
import br.com.sicoob.cca.entidades.negocio.entidades.SituacaoCadastroProposta;
import br.com.sicoob.cca.entidades.negocio.entidades.ValorConfiguracaoCapital;

/**
 * Fabrica de objetos da camada de acesso aos dados do sistema ContaCapitalCadastro
 * 
 * @author Stefanini IT Solutions
 */
public final class ContaCapitalCadastroDaoFactory extends BancoobDaoFactory {

	/** Instancia do DAOFactory. */
	private static ContaCapitalCadastroDaoFactory factory = new ContaCapitalCadastroDaoFactory();

	/**
	 * Retorna a fabrica de DAO's a ser utilizada.
	 * 
	 * @return a fabrica de DAO's a ser utilizada.
	 */
	public static ContaCapitalCadastroDaoFactory getInstance() {
		return factory;
	}

	/**
	 * Construtor privado no-args da classe
	 */
	protected ContaCapitalCadastroDaoFactory() {
		
	}
	
	/**
	 * @return ConfiguracaoCapital
	 */
	public ConfiguracaoCapitalDao criarConfiguracaoCapitalDao() {
		return new ConfiguracaoCapitalDaoImpl(ConfiguracaoCapital.class, "OBTERCONFIGURACAOCAPITAL");
	}	
		
	/**
	 * @return ValorConfiguracaoCapital
	 */
	public ValorConfiguracaoCapitalDao criarValorConfiguracaoCapitalDao() {
		return new ValorConfiguracaoCapitalDaoImpl(ValorConfiguracaoCapital.class, "OBTERVALORCONFIGURACAOCAPITAL");
	}
	
	/**
	 * @return SituacaoCadastroPropostaDao
	 */
	public SituacaoCadastroPropostaDao criarSituacaoCadastroPropostaDao() {
		return new SituacaoCadastroPropostaDaoImpl(SituacaoCadastroProposta.class, "OBTERSITUACAOCADASTROPROPOSTA");
	}
	
	/**
	 * @return PropostaSubscricaoDao
	 */
	public PropostaSubscricaoDao criarPropostaSubscricaoDao() {
		return new PropostaSubscricaoDaoImpl(PropostaSubscricao.class, "OBTERPROPOSTASUBSCRICAO");
	}
	
	/**
	 * @return PropostaSubscricaoDao
	 */
	public ContaCapitalDao criarContaCapitalDao() {
		return new ContaCapitalDaoImpl(ContaCapital.class, "OBTERCONTACAPITAL");
	}
	
	/**
	 * @return DocumentoCapitalDao
	 */
	public DocumentoCapitalDao criarDocumentoCapitalDao() {
		return new DocumentoCapitalDaoImpl(DocumentoCapital.class, "OBTERDOCUMENTOCAPITAL");
	}
	
	/**
	 * @return AgrupadorConfiguracaoCapitalDao
	 */
	public AgrupadorConfiguracaoCapitalDao criarAgrupadorConfiguracaoCapitalDao() {
		return new AgrupadorConfiguracaoCapitalDaoImpl(AgrupadorConfiguracaoCapital.class, "OBTERAGRUPADORCAPITAL");
	}
}