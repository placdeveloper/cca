package br.com.sicoob.sisbr.cca.replicacao.servicos;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.fachada.BancoobFachada;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.cca.comum.util.JsonCapital;
import br.com.sicoob.sisbr.cca.api.negocio.delegates.APIContaCapitalDelegate;
import br.com.sicoob.sisbr.cca.api.negocio.delegates.APIContaCapitalFabricaDelegates;
import br.com.sicoob.sisbr.cca.api.negocio.delegates.CadastroContaCapitalDelegate;
import br.com.sicoob.sisbr.cca.api.negocio.delegates.ContaCapitalDelegate;
import br.com.sicoob.sisbr.cca.api.negocio.delegates.DebIndeterminadoContaCapitalDelegate;
import br.com.sicoob.sisbr.cca.api.negocio.delegates.IntegralizacaoCapitalDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.CapesIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCorrenteIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;

/**
 * Fachada para testar a API
 * @author Nairon.Silva
 */
@RemoteService
public class TestadorAPIFachada extends BancoobFachada {

	/**
	 * Obtem as classes delegates
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO req)throws BancoobException{
		RetornoDTO dto = new RetornoDTO();
		List<Map<String, Object>> classes = new ArrayList<Map<String,Object>>();
		Set<Class<?>> classesAPI = APIHelper.getClasses();
		for (Class<?> clazz : classesAPI) {
			classes.add(criarMapClasse(clazz));
		}
		dto.getDados().put("classes", classes);
		return dto;
	}
	
	private Map<String, Object> criarMapClasse(Class<?> clazz) {
		Map<String, Object> mapClasse = new LinkedHashMap<String, Object>();
		List<Map<String, Object>> metodos = new ArrayList<Map<String,Object>>();
		mapClasse.put("classe", clazz.getSimpleName());
		Method[] metodosClasse = clazz.getDeclaredMethods();
		for (Method metodo : metodosClasse) {
			if (metodo.getModifiers() == Modifier.PUBLIC) {
				Map<String, Object> mapMetodo = new HashMap<String, Object>();
				mapMetodo.put("metodo", metodo.getName());
				Class<?>[] tiposParametros = metodo.getParameterTypes();
				List<String> parametros = new ArrayList<String>();
				for (Class<?> classeParametro : tiposParametros) {
					parametros.add(classeParametro.getSimpleName());
				}
				mapMetodo.put("parametros", parametros);
				metodos.add(mapMetodo);
			}
		}
		mapClasse.put("metodos", metodos);
		return mapClasse;
	}
	
	/**
	 * Executa um metodo da API
	 * @param req
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO executar(RequisicaoReqDTO req) throws BancoobException {
		RetornoDTO dto = new RetornoDTO();
		Gson gson = new Gson();
		JsonCapital jsonCapital = new JsonCapital();
		String classe = req.getDados().get("classe").toString();
		String metodo = req.getDados().get("metodo").toString();
		String parametros = req.getDados().get("parametros").toString();
		String json = req.getDados().get("valor").toString();
		List<String> valores = gson.fromJson(json, new TypeToken<List<String>>(){}.getType());
		
		Class<?> classeAPI = APIHelper.getClassePorNome(classe);
		try {
			Method metodoAPI = APIHelper.recuperarMetodo(classeAPI, metodo, parametros);
			Object resultado = APIHelper.chamarMetodo(classeAPI, metodoAPI, valores);
			dto.getDados().put("resultado", jsonCapital.obterJSon(resultado));
		} catch (SecurityException e) {
			enviarExcecao(e);
		} catch (NoSuchMethodException e) {
			enviarExcecao(e);
		} catch (IllegalArgumentException e) {
			enviarExcecao(e);
		} catch (IllegalAccessException e) {
			enviarExcecao(e);
		} catch (InvocationTargetException e) {
			enviarExcecao(e);
		}
		
		return dto;
	}
	
	private void enviarExcecao(Exception e) throws BancoobException {
		StringBuilder sb = new StringBuilder();
		sb.append("Erro: ").append(e.getClass().getSimpleName());
		sb.append("\nMessage: ").append(e.getMessage());
		sb.append("\nCause: ").append(e.getCause() == null ? "" : e.getCause().getMessage());
		throw new BancoobException(sb.toString());
	}
	
	public RetornoDTO executarIntegracao(RequisicaoReqDTO req) throws BancoobException {
		final int CCO_LISTAR = 1;
		final int CAPES_OBTER_CPF_INST = 2;
		final int CAPES_OBTER_PESSOA_INST = 3;
		RetornoDTO dto = new RetornoDTO();
		JsonCapital jsonCapital = new JsonCapital();
		Object resultado = null;
		String[] valor = req.getDados().get("valor").toString().split(",");
		int operacao = Integer.parseInt(valor[0]);
		
		switch (operacao) {
		case CCO_LISTAR:
			ContaCorrenteIntegracaoDelegate ccoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarContaCorrenteIntegracaoDelegate();
			ContaCorrenteIntegracaoDTO ccoDTO = new ContaCorrenteIntegracaoDTO();
			ccoDTO.setIdInstituicao(Integer.valueOf(valor[1]));
			ccoDTO.setIdPessoa(Integer.valueOf(valor[2]));
			resultado = ccoDelegate.consultarContaCorrenteAtivaPorNumeroCliente(ccoDTO);
			break;
		case CAPES_OBTER_CPF_INST:
		case CAPES_OBTER_PESSOA_INST:
			CapesIntegracaoDelegate capesDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarCapesIntegracaoDelegate();
			if (operacao == CAPES_OBTER_CPF_INST) {
				resultado = capesDelegate.obterPorCpfCnpjInstituicao(valor[1], Integer.valueOf(valor[2]));
			} else {
				resultado = capesDelegate.obterPessoaInstituicao(Integer.valueOf(valor[1]), Integer.valueOf(valor[2]));
			}
			break;
		default:
			break;
		}
		
		dto.getDados().put("resultado", jsonCapital.obterJSon(resultado));
		
		return dto;
	}

	private static class APIHelper {
		
		private static Map<Class<?>, APIContaCapitalDelegate<?>> classes = new LinkedHashMap<Class<?>, APIContaCapitalDelegate<?>>();
		private static List<Class<?>> parametros = new ArrayList<Class<?>>();
		
		static {
			APIContaCapitalFabricaDelegates fabrica = APIContaCapitalFabricaDelegates.getInstance();
			classes.put(ContaCapitalDelegate.class, fabrica.criarContaCapitalDelegate());
			classes.put(CadastroContaCapitalDelegate.class, fabrica.criarCadastroContaCapitalDelegate());
			classes.put(DebIndeterminadoContaCapitalDelegate.class, fabrica.criarDebIndeterminadoContaCapitalDelegate());
			classes.put(IntegralizacaoCapitalDelegate.class, fabrica.criarIntegralizacaoCapitalDelegate());
			
			parametros.add(String.class);
			parametros.add(Integer.class);
		}
		
		/**
		 * Retorna as classes delegates
		 * @return
		 */
		public static Set<Class<?>> getClasses() {
			return classes.keySet();
		}
		
		/**
		 * Retorna a classe pela String
		 * @param nome
		 * @return
		 */
		public static Class<?> getClassePorNome(String nome) {
			for (Class<?> clazz : classes.keySet()) {
				if (clazz.getSimpleName().equals(nome)) {
					return clazz;
				}
			}
			return null;
		}
		
		/**
		 * Retorna o parametro pela String
		 * @param nome
		 * @return
		 */
		public static Class<?> getParametroPorNome(String nome) {
			for (Class<?> clazz : parametros) {
				if (clazz.getSimpleName().equals(nome)) {
					return clazz;
				}
			}
			return null;
		}
		
		/**
		 * Recupera o metodo do delegate
		 * @param clazz
		 * @param metodo
		 * @param parametros
		 * @return
		 * @throws NoSuchMethodException
		 */
		public static Method recuperarMetodo(Class<?> clazz, String metodo, String parametros) throws NoSuchMethodException {
			String[] paramArray = parametros.split(",");
			Class<?>[] classParamArray = new Class<?>[paramArray.length];
			for (int i = 0; i < paramArray.length; i++) {
				Class<?> classeParametro = getParametroPorNome(paramArray[i]);
				classParamArray[i] = classeParametro;
			}
			return clazz.getMethod(metodo, classParamArray);
		}
		
		/**
		 * Faz o tratamento do parametro, realizando as devidas conversoes
		 * @param clazz
		 * @param valor
		 * @return
		 */
		public static Object tratarValorParametro(Class<?> clazz, String valor) {
			Object valorTratado = valor;
			if (Integer.class.equals(clazz)) {
				valorTratado = Integer.valueOf(valor);
			}
			return valorTratado;
		}
		
		/**
		 * Realiza o invoke do metodo da API
		 * @param classeAPI
		 * @param metodoAPI
		 * @param valores
		 * @return
		 * @throws IllegalArgumentException
		 * @throws IllegalAccessException
		 * @throws InvocationTargetException
		 */
		public static Object chamarMetodo(Class<?> classeAPI, Method metodoAPI, List<String> valores) throws IllegalAccessException, InvocationTargetException {
			APIContaCapitalDelegate<?> delegate = classes.get(classeAPI);
			Class<?>[] tiposParametros = metodoAPI.getParameterTypes();
			Object[] valoresAPI = new Object[valores.size()];
			for (int i = 0; i < valores.size(); i++) {
				valoresAPI[i] = tratarValorParametro(tiposParametros[i], valores.get(i));
			}
			return metodoAPI.invoke(delegate, valoresAPI);
		}
		
	}
	
}
