/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.enums.EnumTipoIntegralizacao;
import br.com.sicoob.sisbr.cca.cadastro.vo.ItemListaVO;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.InstituicaoIntegracaoDelegate;

/**
 * @author Marco.Nascimento
 */
public class CadastroContaCapitalUtil {
	
	/** A constante TAMANHO_MAX_LISTA. */
	private static final Integer TAMANHO_MAX_LISTA = 9;

	/** O atributo instituicaoDelegate. */
	private InstituicaoIntegracaoDelegate instituicaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarInstituicaoIntegracaoDelegate();
	
	/**
	 * Verifica se o usuario logado pertence a uma instituicao singular
	 * @return usuario singular
	 * @throws BancoobException
	 */
	public boolean isUsuarioLogadoInstSingular() throws BancoobException {
		Integer tipoGrauCoopetativa = instituicaoDelegate.obterTipoGrauCooperativa(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao())).getCodTipoGrauCoop();
		return tipoGrauCoopetativa.equals(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_SINGULAR);
	}
	
	/**
	 * Verifica se o usuario logado pertence a uma instituicao central
	 * @return usuario central
	 * @throws BancoobException
	 */
	public boolean isUsuarioLogadoInstCentral() throws BancoobException {
		Integer tipoGrauCoopetativa = instituicaoDelegate.obterTipoGrauCooperativa(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao())).getCodTipoGrauCoop();
		return tipoGrauCoopetativa.equals(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_CENTRAL);
	}
	
	/**
	 * Verifica se o usuario logado pertence a conferederacao
	 * @return usuario confederacao
	 * @throws BancoobException
	 */
	public boolean isUsuarioLogadoConf() throws BancoobException {
		Integer tipoGrauCoopetativa = instituicaoDelegate.obterTipoGrauCooperativa(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao())).getCodTipoGrauCoop();
		return tipoGrauCoopetativa.equals(ContaCapitalConstantes.COD_TIPO_GRAU_INSTITUICAO_CONFEDERACAO);
	}
	
	/**
	 * Monta combo de acordo com o tipo de operacao de subscrição (novo cooperado, nova subscricao e reativar cooperado)
	 * @see ContaCapitalConstantes#COD_INCLUIR_COOPERADO
	 * @see ContaCapitalConstantes#COD_NOVA_SUBSCRICAO
	 * @see ContaCapitalConstantes#COD_REATIVAR_COOPERADO
	 * @see ContaCapitalConstantes#COD_PROPOSTA_CADASTRO
	 * @param tipoOpInteg
	 * @return tipo de integralizacao
	 * @throws BancoobException
	 */
	public List<ItemListaVO> criarComboTipoIntegralizacao(Integer tipoOpInteg) throws BancoobException {
		
		List<ItemListaVO> lista = new ArrayList<ItemListaVO>(TAMANHO_MAX_LISTA);
		
		if(tipoOpInteg.equals(ContaCapitalConstantes.COD_PROPOSTA_CADASTRO)) {
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CAIXA.getDescricao()));
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_CONTA.getDescricao()));
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_BANCO_CHADMIN.getDescricao()));
			lista.add(new ItemListaVO(EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_FOLHA.getCodigo().toString(), EnumTipoIntegralizacao.COD_MODO_LANCAMENTO_VIA_FOLHA.getDescricao()));
		}
		
		return lista;
	}
}
