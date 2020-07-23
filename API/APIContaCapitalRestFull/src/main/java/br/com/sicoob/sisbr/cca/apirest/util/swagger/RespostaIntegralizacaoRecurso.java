package br.com.sicoob.sisbr.cca.apirest.util.swagger;

import br.com.sicoob.sisbr.cca.apirest.dtos.IntegralizacaoRecursoRetornoDTO;
import io.swagger.annotations.ApiModel;

/**
 * Classe para representar o retorno dos recursos SOMENTE para a geracao do
 * swagger
 * 
 * @author Marcos.Balbi
 *
 */
@ApiModel(value=SwaggerConstantes.RESPOSTA_INTEGRALIZACOES_INTEGRALIZAR_VALOR, description=SwaggerConstantes.RESPOSTA_INTEGRALIZACOES_INTEGRALIZAR_DESCRICAO)
public class RespostaIntegralizacaoRecurso extends CCASicoobResultado<IntegralizacaoRecursoRetornoDTO>{}