package br.com.sicoob.sisbr.contacapital.atendimento.fachada;

import java.awt.Image;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;

import br.com.bancoob.comum.negocio.delegates.FabricaDelegates;
import br.com.bancoob.comum.negocio.delegates.ListaDelegate;
import br.com.bancoob.comum.negocio.delegates.ParametroDelegate;
import br.com.bancoob.comum.negocio.entidades.ItemLista;
import br.com.bancoob.comum.negocio.entidades.Lista;
import br.com.bancoob.comum.negocio.entidades.Parametro;
import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.hotdeploy.ItemDeploy;
import br.com.bancoob.infraestrutura.relatorios.RepositorioRelatorios;
import br.com.bancoob.infraestrutura.relatorios.ServicoRelatorios;
import br.com.bancoob.sci.negocio.delegates.SCIFabricaDelegates;
import br.com.bancoob.sci.negocio.delegates.UnidadeInstituicaoDelegate;
import br.com.bancoob.sci.negocio.entidades.UnidadeInstituicao;
import br.com.bancoob.sisbrweb.fachada.BancoobFachada;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.contacapital.comum.util.ContaCapitalConstantes;
import br.com.sicoob.contacapital.comum.vo.ItemListaVO;
import br.com.sicoob.retaguarda.comum.RecuperadorCooperativa;
import br.com.sicoob.retaguarda.comum.RecuperadorPac;
import br.com.sicoob.retaguarda.comum.negocio.vo.PacVO;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.AtendimentoFabricaDelegates;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.sisbr.contacapital.atendimento.negocio.delegates.RelAtendimentoComumDelegate;
import br.com.sicoob.sisbr.corporativo.produto.negocio.delegates.ProdutosServicoDelegate;
import br.com.sicoob.sisbr.corporativo.produto.negocio.entidades.ProdutoLegado;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe Abstrata para as Fachadas do Atendimento Conta Capital 
 *
 */
public abstract class AtendimentoFachada extends BancoobFachada {
	protected ListaDelegate listaDelegate = 
			FabricaDelegates.getInstance().criarListaDelegate();	
	
	protected ProdutosServicoDelegate produtoDelegate  = 
			ProdutosServicoDelegate.getInstance();	
	
	protected ParametroDelegate parametroDelegate = 
			FabricaDelegates.getInstance().criarParametroDelegate();
	
	protected ContaCapitalDelegate ccaDelegate = 
			AtendimentoFabricaDelegates.getInstance().criarContaCapitalDelegate();
	
	private UnidadeInstituicaoDelegate unidInstDelegate = SCIFabricaDelegates
			.getInstance().criarUnidadeInstituicaoDelegate();
	
	private RelAtendimentoComumDelegate relAteDelegate = 
			AtendimentoFabricaDelegates.getInstance().criarRelAtendimentoComumDelegate();
	
	ServicoRelatorios servicoRelatorios = 
			RepositorioRelatorios.getInstance().getServicoRelatorios();
	
	protected DateTime obterDataAtualProduto() throws BancoobException{
		
		ProdutoLegado produto = produtoDelegate.obter(
				ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
		
		if(produto != null && produto.getDataAtualProd() != null){
			return new DateTime(produto.getDataAtualProd().getTime());
		}else{
			throw new BancoobException("Erro ao obter data atual produto");
		}
		
	}
	
	protected DateTime obterDataAntProduto() throws BancoobException{
		
		ProdutoLegado produto = produtoDelegate.obter(
				ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
		
		if(produto != null && produto.getDataAntProd() != null){
			return new DateTime(produto.getDataAntProd().getTime());
		}else{
			throw new BancoobException("Erro ao obter data atual produto");
		}
		
	}
	
	protected DateTime obterDataAtualMovimento() throws BancoobException {
		ProdutoLegado produto = produtoDelegate.obter(
				ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL);
		if (produto != null) {
			return new DateTime(produto.getDataAtualMovimento().getTime());
		} else {
			throw new BancoobException("Erro ao obter data atual movimento");
		}
	}	
	
	protected String obterCooperativa() {
		return ContextoHttp.getInstance().recuperarCooperativa();
	}
	
	protected Integer obterNumPai(Integer numCooperativa) throws BancoobException{
		return RecuperadorPac.getInstance().recuperarNumPai(numCooperativa);
	}
	
	protected Integer obterNumPai() throws BancoobException{
		return obterNumPai(Integer.valueOf(obterCooperativa()));
	}
	
	protected String obterParametro(Integer idParametro) throws BancoobException{
		Parametro parametro = parametroDelegate.recuperar(idParametro);
		
		if(parametro != null && parametro.getValor() != null 
				&& parametro.getValor().getValor() != null){
			return parametro.getValor().getValor();
		}else{
			throw new BancoobException("Erro ao obter parâmetro");
		}
	}
	
	protected List<ItemListaVO> obterLista(Integer idLista) throws BancoobException{
		Lista lista = listaDelegate.recuperar(idLista);		
		List<ItemLista> itens = lista.getItens();
		
		List<ItemListaVO> retorno = new ArrayList<ItemListaVO>();
		ItemListaVO ilvo = null;
		
		Iterator<ItemLista> it = itens.iterator();
		while (it.hasNext()) {
			ItemLista itemLista = (ItemLista) it.next();
			ilvo = new ItemListaVO();
			
			ilvo.setCodigoItem(itemLista.getCodigoItem());
			ilvo.setDescricao(itemLista.getDescricao());
			
			retorno.add(ilvo);
		}
		
		return retorno;
	}
	
	protected String obterCooperativaFmt() throws BancoobException {
		DecimalFormat df = new DecimalFormat("0000");
		return df.format(Integer.valueOf(obterCooperativa()));
	}
	
	protected String obterSiglaCooperativa() throws BancoobException {
		return RecuperadorCooperativa.getInstance().obterSiglaCooperativa(
				obterCooperativa().toString());
	}
	
	protected List<PacVO> recuperarListaPACs()
			throws BancoobException {
		String cooperativa = obterCooperativa();
		if (cooperativa == null || "".equals(cooperativa)) {
			throw new BancoobException("Número da coooperativa vazio");
		}

		List<PacVO> list = new ArrayList<PacVO>();
		PacVO vo = null;

		List<UnidadeInstituicao> unidades = unidInstDelegate
				.listarUnidadeInstPorNumero(cooperativa);

		for (UnidadeInstituicao unidadeInstituicao : unidades) {
			vo = new PacVO();
			vo.setIdUnidade(unidadeInstituicao.getUnidadeInstituicaoPK()
					.getIdUnidadeInst());
			vo.setNomeUnidade(unidadeInstituicao.getNomeUnidade());

			list.add(vo);
		}
		return list;
	}
	
	/**
	 * obtem o valor minimo e máximo da Matricula
	 * 
	 * @return [0] = minima, [1] = maxima
	 * 
	 */
	protected String[] obterMatricMinimaMaxima() throws BancoobException{
		return ccaDelegate.obterMatricMinimaMaxima();		
	}
	
	protected Integer recuperarPac() {
		return Integer.parseInt(ContextoHttp.getInstance().recuperarPac());
	}
	
	protected String obterNomePac(Integer numPac) throws NumberFormatException, BancoobException{
		return RecuperadorPac.getInstance().recuperarNomePAC(Integer.parseInt(obterCooperativa()), numPac);
	}
	
	protected List<PacVO> obterListaPACs() throws BancoobException{
		return RecuperadorPac.getInstance().recuperarListaPACs(obterCooperativa());
	}
	
	/**
	 * @param lista
	 */
	public static void montaListaPacVO(List<PacVO> lista) {
		for (PacVO pacVo : lista) {
			pacVo.setNomeUnidade(montaNomePac(pacVo));
		}
	}

	private static String montaNomePac(PacVO pacVo) {
		return pacVo.getIdUnidade() + " - " + pacVo.getNomeUnidade();
	}
	
	/**
	 * obtem o valor minimo e máximo da Empresa
	 * 
	 * @return [0] = minima, [1] = maxima
	 * 
	 */
	protected String[] obterEmpresaMinimaMaxima() throws BancoobException{
		return relAteDelegate.obterEmpresaMinimaMaxima();
	}
	
	protected URL recuperarUrlRelatorio(String relatorio){
		ItemDeploy itemDeploy = servicoRelatorios.recuperarRelatorio(relatorio);
		return itemDeploy.getUrl();
	}
	
	protected List<DateTime> converterListaDataParaDateTime(List<Date> datas) {
		List<DateTime> lista = null;
		if(datas != null){
			lista = new ArrayList<DateTime>();
			for (Date date : datas) {
				lista.add(new DateTime(date.getTime()));
			}
		}
		return lista;
	}
	protected Image recuperarImagemRelatorio(String imagemPacote) throws BancoobException{
		URL urlImagem = Thread.currentThread().getContextClassLoader().getResource(imagemPacote);
		ImageIcon imagem = new ImageIcon(urlImagem);
		return imagem.getImage();
	}		
	
	protected Integer obterCooperativaTipo(Integer numCooperativa) throws BancoobException {
		return RecuperadorCooperativa.getInstance().obterCooperativaTipo(numCooperativa);
	}
	
}
