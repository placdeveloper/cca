package br.com.sicoob.sisbr.cca.apirest.recursos;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.cca.apirest.dtos.IntegralizacaoRecursoDTO;
import br.com.sicoob.sisbr.cca.apirest.recursos.interfaces.IIntegralizacaoRecurso;
import br.com.sicoob.sisbr.cca.apirest.servicos.interfaces.IntegralizacaoContaCapitalServicoLocal;
import br.com.sicoob.sisbr.cca.apirest.util.swagger.SwaggerConstantes;
import io.swagger.annotations.Api;

/**
 * Recurso para integralização de capital
 * @author Marcos.Balbi
 *
 */
@Api(tags = SwaggerConstantes.TAG_INTEGRALIZACOES)
@Path("/integralizacoes")
@RequestScoped
public class IntegralizacaoRecurso extends ContaCapitalBaseRecurso implements IIntegralizacaoRecurso{

	ISicoobLogger logger = SicoobLoggerPadrao.getInstance(IntegralizacaoRecurso.class);
	
	@EJB
	private IntegralizacaoContaCapitalServicoLocal integralizacaoContaCapitalServico;
	
	@POST
	@Path("/integralizar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	@Override
	public Response integralizarCapital(IntegralizacaoRecursoDTO dto) {

		try{
			return criarResultado(integralizacaoContaCapitalServico.integralizarCapital(dto),Status.CREATED);			
		}catch (NegocioException e) {
			logger.info("INTEG REST - ERRO NEGOCIAL: "+e.getMessage());
			return  criarMensagemNegocio(e.getMessage(),Status.BAD_REQUEST);
		}catch (BancoobException e) {
			logger.erro(e, "INTEG REST - ERRO: "+e.getMessage());
			return  criarMensagemNegocio(e.getMessage(),Status.BAD_REQUEST);			
		}catch (Exception e) {
			logger.erro(e, "INTEG REST - ERRO: "+e.getMessage());			
			return  criarMensagemErro(e.getMessage(),Status.INTERNAL_SERVER_ERROR);			
		}
	
	}
	
}
