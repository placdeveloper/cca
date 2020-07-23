package br.com.sicoob.sisbr.cca.apirest.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.cca.apirest.util.swagger.SwaggerConstantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = SwaggerConstantes.INTEGRALIZAR_VO_VALOR, description = SwaggerConstantes.INTEGRALIZAR_VO_DESCRICAO)
public class IntegralizacaoRecursoDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_NUM_CCA, example = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_NUM_CCA_EXEMPLO)	
	private Integer numeroContaCapital;
	
	@ApiModelProperty(value = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_ID_INST, example = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_ID_INST_EXEMPLO)	
	private Integer idInstituicao;
	
	@ApiModelProperty(value = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_VALOR_SUBSC, example = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_VALOR_SUBSC_EXEMPLO)	
	private BigDecimal valorSubscricao;
	
	@ApiModelProperty(value = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_VALOR_INTEG, example = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_VALOR_INTEG_EXEMPLO)	
	private BigDecimal valorIntegralizacao;
	
	@ApiModelProperty(value = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_ID_TIPOHISTORICO, example = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_ID_TIPOHISTORICO_EXEMPLO)	
	private Integer idTipoHistorico;
	
	@ApiModelProperty(value = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_COD_OP, example = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_COD_OP_EXEMPLO)	
	private String codigoOperacao;
	
	@ApiModelProperty(value = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_NUM_CCO, example = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_NUM_CCO_EXEMPLO)	
	private Long numeroContaCorrente;
	
	@ApiModelProperty(value = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_QTDE_PARC, example = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_QTDE_PARC_EXEMPLO)	
	private Integer quantidadeParcelas;
	
	@ApiModelProperty(value = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_DT_PARC, example = SwaggerConstantes.INTEGRALIZAR_VO_ATRIB_DT_PARC_EXEMPLO)	
	private String dataInicioParcelamento;

	//Atribuidos apos validação dos parametros recebidos
	@ApiModelProperty(hidden=true)
	private Integer idContaCapital;
	@ApiModelProperty(hidden=true)	
	private Integer idPessoa;
	@ApiModelProperty(hidden=true)	
	private DateTimeDB dataParcelamento;
	@ApiModelProperty(hidden=true)	
	private DateTimeDB dataAtualProduto;
	
	public Integer getNumeroContaCapital() {
		return numeroContaCapital;
	}
	public void setNumeroContaCapital(Integer numeroContaCapital) {
		this.numeroContaCapital = numeroContaCapital;
	}
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public BigDecimal getValorSubscricao() {
		return valorSubscricao;
	}
	public void setValorSubscricao(BigDecimal valorSubscricao) {
		this.valorSubscricao = valorSubscricao;
	}
	public BigDecimal getValorIntegralizacao() {
		return valorIntegralizacao;
	}
	public void setValorIntegralizacao(BigDecimal valorIntegralizacao) {
		this.valorIntegralizacao = valorIntegralizacao;
	}
	public Integer getIdTipoHistorico() {
		return idTipoHistorico;
	}
	public void setIdTipoHistorico(Integer idTipoHistorico) {
		this.idTipoHistorico = idTipoHistorico;
	}
	public String getCodigoOperacao() {
		return codigoOperacao;
	}
	public void setCodigoOperacao(String codigoOperacao) {
		this.codigoOperacao = codigoOperacao;
	}
	public Long getNumeroContaCorrente() {
		return numeroContaCorrente;
	}
	public void setNumeroContaCorrente(Long numeroContaCorrente) {
		this.numeroContaCorrente = numeroContaCorrente;
	}
	public Integer getQuantidadeParcelas() {
		return quantidadeParcelas;
	}
	public void setQuantidadeParcelas(Integer quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}
	public String getDataInicioParcelamento() {
		return dataInicioParcelamento;
	}
	public void setDataInicioParcelamento(String dataInicioParcelamento) {
		this.dataInicioParcelamento = dataInicioParcelamento;
	}
	public Integer getIdContaCapital() {
		return idContaCapital;
	}
	public void setIdContaCapital(Integer idContaCapital) {
		this.idContaCapital = idContaCapital;
	}
	public Integer getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	public DateTimeDB getDataParcelamento() {
		return dataParcelamento;
	}
	public void setDataParcelamento(DateTimeDB dataParcelamento) {
		this.dataParcelamento = dataParcelamento;
	}
	public DateTimeDB getDataAtualProduto() {
		return dataAtualProduto;
	}
	public void setDataAtualProduto(DateTimeDB dataAtualProduto) {
		this.dataAtualProduto = dataAtualProduto;
	}
	
		
}