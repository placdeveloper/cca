package br.com.sicoob.sisbr.cca.apirest.dtos;

import java.io.Serializable;

import br.com.sicoob.sisbr.cca.apirest.util.swagger.SwaggerConstantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value=SwaggerConstantes.INTEGRALIZAR_RESP_VO_VALOR, description=SwaggerConstantes.INTEGRALIZAR_RESP_VO_DESCRICAO)
public class IntegralizacaoRecursoRetornoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = SwaggerConstantes.INTEGRALIZAR_RESP_VO_ATRIB_ID_INST, example = SwaggerConstantes.INTEGRALIZAR_RESP_VO_ATRIB_ID_INST_EXEMPLO)	
	private Integer idInstituicao;
	@ApiModelProperty(value = SwaggerConstantes.INTEGRALIZAR_RESP_VO_ATRIB_DT_LOTE, example = SwaggerConstantes.INTEGRALIZAR_RESP_VO_ATRIB_DT_LOTE_EXEMPLO)	
	private String dataLancamento; 
	@ApiModelProperty(value = SwaggerConstantes.INTEGRALIZAR_RESP_VO_ATRIB_NUM_LOTE, example = SwaggerConstantes.INTEGRALIZAR_RESP_VO_ATRIB_NUM_LOTE_EXEMPLO)	
	private Integer numeroLote;
	@ApiModelProperty(value = SwaggerConstantes.INTEGRALIZAR_RESP_VO_ATRIB_NUM_LANCAMENTO, example = SwaggerConstantes.INTEGRALIZAR_RESP_VO_ATRIB_NUM_LANCAMENTO_EXEMPLO)	
	private Integer numeroLancamento;
	
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public String getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public Integer getNumeroLote() {
		return numeroLote;
	}
	public void setNumeroLote(Integer numeroLote) {
		this.numeroLote = numeroLote;
	}
	public Integer getNumeroLancamento() {
		return numeroLancamento;
	}
	public void setNumeroLancamento(Integer numeroLancamento) {
		this.numeroLancamento = numeroLancamento;
	}
	
	
}
