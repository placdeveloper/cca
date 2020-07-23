package br.com.sicoob.cca.comum.negocio.servicos.interceptors;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.comum.negocio.annotation.LogCCA;
import br.com.sicoob.cca.comum.negocio.servicos.OperacaoContaCapitalServico;
import br.com.sicoob.cca.entidades.negocio.entidades.MetodoOperacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.OperacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.ResultadoOperacaoContaCapital;
import br.com.sicoob.cca.entidades.negocio.enums.EnumResultadoOperacaoContaCapital;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Interceptador para logs do Conta Capital
 * @author Nairon.Silva
 */
public class ContaCapitalLogInterceptor {

	private static final int TAMANHO_MAXIMO_PARAMETROS = 5000;
	private static final int TAMANHO_MAXIMO_ERROS = 1000;
	
	@EJB
	private OperacaoContaCapitalServico servico;
	
	@AroundInvoke
	public Object intercept(InvocationContext contexto) throws Exception {
		LogCCA annotation = contexto.getMethod().getAnnotation(LogCCA.class);
		DateTimeDB dataHoraInicio = new DateTimeDB();
		Object retorno = null;
		try {
			retorno = contexto.proceed();
			criarLogCCA(annotation, EnumResultadoOperacaoContaCapital.SUCESSO, dataHoraInicio, null, contexto);
		} catch (NegocioException ne) {
			criarLogCCA(annotation, EnumResultadoOperacaoContaCapital.ERRO_NEGOCIO, dataHoraInicio, ne.getMessage(), contexto);
			throw ne;
		} catch (BancoobRuntimeException bre) {
			criarLogCCA(annotation, EnumResultadoOperacaoContaCapital.ERRO_SISTEMA, dataHoraInicio, bre.getMessage(), contexto);
			throw bre;
		} catch (BancoobException be) {
			criarLogCCA(annotation, EnumResultadoOperacaoContaCapital.ERRO_SISTEMA, dataHoraInicio, be.getMessage(), contexto);
			throw be;
		}
		return retorno;
	}

	private void criarLogCCA(LogCCA annotation, EnumResultadoOperacaoContaCapital resultado, DateTimeDB dataHoraInicio, String descErro, InvocationContext contexto) throws BancoobException {
		if (annotation != null) {
			StringBuilder parametros = new StringBuilder();
			Object[] parameters = contexto.getParameters();
			if (parameters != null) {
				List<Object> paramsList = Arrays.asList(parameters);
				for (Iterator<Object> iterator = paramsList.iterator(); iterator.hasNext();) {
					Object obj = iterator.next();
					parametros.append(obj.toString());
					if (iterator.hasNext()) {
						parametros.append(" | ");
					}
				}
			}
			OperacaoContaCapital log = new OperacaoContaCapital();
			log.setDataHoraInicio(dataHoraInicio);
			log.setDataHoraFim(new DateTimeDB());
			log.setMetodo(new MetodoOperacaoContaCapital(annotation.metodo().getCodigo()));
			log.setDescParametros(tratarCampoExtenso(parametros.toString(), TAMANHO_MAXIMO_PARAMETROS));
			log.setResultado(new ResultadoOperacaoContaCapital(resultado.getCodigo()));
			log.setDescErro(tratarCampoExtenso(descErro, TAMANHO_MAXIMO_ERROS));
			InformacoesUsuario info = InformacoesUsuario.getInstance();
			if (info != null && info.getLogin() != null) {
				log.setIdUsuario(info.getLogin());
				log.setIdInstituicao(info.getIdInstituicao() == null ? null : Integer.valueOf(info.getIdInstituicao()));
				log.setIdUnidadeInst(info.getPac() == null ? null : Short.valueOf(info.getPac()));
			} else {
				log.setIdUsuario(ContaCapitalConstantes.USR_EXTERNO_SISBR);
			}
			try {
				servico.incluir(log);
			} catch (BancoobException be) {
				SicoobLoggerPadrao.getInstance(getClass()).info(log+"\n"+be.getMessage());
			}
		}
	}
	
	private String tratarCampoExtenso(String valor, int tamanhoMaximo) {
		if (valor != null && valor.length() > tamanhoMaximo) {
			return valor.substring(0, tamanhoMaximo);
		}
		return valor;
	}

}
