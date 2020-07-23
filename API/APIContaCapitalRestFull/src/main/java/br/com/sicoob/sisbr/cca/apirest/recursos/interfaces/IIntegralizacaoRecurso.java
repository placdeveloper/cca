package br.com.sicoob.sisbr.cca.apirest.recursos.interfaces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.sicoob.sisbr.cca.apirest.dtos.IntegralizacaoRecursoDTO;
import br.com.sicoob.sisbr.cca.apirest.util.swagger.CCAMensagensErro;
import br.com.sicoob.sisbr.cca.apirest.util.swagger.RespostaIntegralizacaoRecurso;
import br.com.sicoob.sisbr.cca.apirest.util.swagger.SwaggerConstantes;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface IIntegralizacaoRecurso {

	
	@ApiOperation(
				value = SwaggerConstantes.INTEGRALIZACOES_INTEGRALIZAR_DESCRICAO,
				produces = MediaType.APPLICATION_JSON, 
				notes = SwaggerConstantes.INTEGRALIZACOES_INTEGRALIZAR_NOTA)
	@ApiResponses({
			@ApiResponse(code = 201, message = SwaggerConstantes.OPERACAO_CODE_201, response = RespostaIntegralizacaoRecurso.class),
			@ApiResponse(code = 400, message = SwaggerConstantes.OPERACAO_CODE_400, response = CCAMensagensErro.class),
			@ApiResponse(code = 500, message = SwaggerConstantes.OPERACAO_CODE_500, response = CCAMensagensErro.class) })	
	 Response integralizarCapital(@ApiParam(name=SwaggerConstantes.INTEGRALIZAR_VO_DESCRICAO , value = SwaggerConstantes.INTEGRALIZAR_VO_VALOR, required = true) IntegralizacaoRecursoDTO dto);
	
	
}
