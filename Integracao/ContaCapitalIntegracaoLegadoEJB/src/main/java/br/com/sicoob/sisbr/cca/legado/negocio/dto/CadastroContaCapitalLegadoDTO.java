/*
 * 
 */
package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO para gravação nas tabelas do legado de Conta Capital
 * @author Marcos.Balbi
 *
 */
public class CadastroContaCapitalLegadoDTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -3514765263367314598L;

	//Provenientes da Interface de Cadastro
	/** O atributo numMatricula. */
	private Integer numMatricula;   //Pode vir dependendo do parametro
	
	/** O atributo numCliente. */
	private Integer numCliente; 	//cliente no sql server, não representa o cliente do capes
	
	/** O atributo numContaCorrente. */
	private Long numContaCorrente; 	//pode vir nulo
	
	/** O atributo uidTrabalha. */
	private String uidTrabalha; 	//chave da tabela trabalha, pode vir nulo
	
	/** O atributo valorSubsc. */
	private BigDecimal valorSubsc; 	//valor de subscrição 
	
	/** O atributo valorInt. */
	private BigDecimal valorInt; 	//valor de integralização
	
	/** O atributo codModoLanc. */
	private Integer codModoLanc; 	//Tipo de Integralizacao, Via cx via cco e etc	
	
	/** O atributo idUsuario. */
	private String idUsuario; 		//usuario no sql server
	
	/** O atributo lstParcelamentoContaCapitalLegadoDTO. */
	private List<ParcelamentoContaCapitalLegadoDTO> lstParcelamentoContaCapitalLegadoDTO;
	
	//SCI e CAPES - //Usados nos servicos de integracao
	/** O atributo idInstituicao. */
	private Integer idInstituicao;  
	
	/** O atributo idPessoa. */
	private Integer idPessoa;	
	
	/**
	 * Recupera o valor de idPessoa.
	 *
	 * @return o valor de idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * Define o valor de idPessoa.
	 *
	 * @param idPessoa o novo valor de idPessoa
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @return o valor de idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	
	/**
	 * Define o valor de idInstituicao.
	 *
	 * @param idInstituicao o novo valor de idInstituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	
	/**
	 * Recupera o valor de numMatricula.
	 *
	 * @return o valor de numMatricula
	 */
	public Integer getNumMatricula() {
		return numMatricula;
	}
	
	/**
	 * Define o valor de numMatricula.
	 *
	 * @param numMatricula o novo valor de numMatricula
	 */
	public void setNumMatricula(Integer numMatricula) {
		this.numMatricula = numMatricula;
	}
	
	/**
	 * Recupera o valor de numCliente.
	 *
	 * @return o valor de numCliente
	 */
	public Integer getNumCliente() {
		return numCliente;
	}
	
	/**
	 * Define o valor de numCliente.
	 *
	 * @param numCliente o novo valor de numCliente
	 */
	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
	}

	/**
	 * Recupera o valor de numContaCorrente.
	 *
	 * @return o valor de numContaCorrente
	 */
	public Long getNumContaCorrente() {
		return numContaCorrente;
	}
	
	/**
	 * Define o valor de numContaCorrente.
	 *
	 * @param numContaCorrente o novo valor de numContaCorrente
	 */
	public void setNumContaCorrente(Long numContaCorrente) {
		this.numContaCorrente = numContaCorrente;
	}
	
	/**
	 * Recupera o valor de uidTrabalha.
	 *
	 * @return o valor de uidTrabalha
	 */
	public String getUidTrabalha() {
		return uidTrabalha;
	}
	
	/**
	 * Define o valor de uidTrabalha.
	 *
	 * @param uidTrabalha o novo valor de uidTrabalha
	 */
	public void setUidTrabalha(String uidTrabalha) {
		this.uidTrabalha = uidTrabalha;
	}
	
	/**
	 * Recupera o valor de valorSubsc.
	 *
	 * @return o valor de valorSubsc
	 */
	public BigDecimal getValorSubsc() {
		return valorSubsc;
	}
	
	/**
	 * Define o valor de valorSubsc.
	 *
	 * @param valorSubsc o novo valor de valorSubsc
	 */
	public void setValorSubsc(BigDecimal valorSubsc) {
		this.valorSubsc = valorSubsc;
	}
	
	/**
	 * Recupera o valor de valorInt.
	 *
	 * @return o valor de valorInt
	 */
	public BigDecimal getValorInt() {
		return valorInt;
	}
	
	/**
	 * Define o valor de valorInt.
	 *
	 * @param valorInt o novo valor de valorInt
	 */
	public void setValorInt(BigDecimal valorInt) {
		this.valorInt = valorInt;
	}
	
	/**
	 * Recupera o valor de codModoLanc.
	 *
	 * @return o valor de codModoLanc
	 */
	public Integer getCodModoLanc() {
		return codModoLanc;
	}
	
	/**
	 * Define o valor de codModoLanc.
	 *
	 * @param codModoLanc o novo valor de codModoLanc
	 */
	public void setCodModoLanc(Integer codModoLanc) {
		this.codModoLanc = codModoLanc;
	}
	
	/**
	 * Recupera o valor de idUsuario.
	 *
	 * @return o valor de idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}
	
	/**
	 * Define o valor de idUsuario.
	 *
	 * @param idUsuario o novo valor de idUsuario
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Recupera o valor de lstParcelamentoContaCapitalLegadoDTO.
	 *
	 * @return o valor de lstParcelamentoContaCapitalLegadoDTO
	 */
	public List<ParcelamentoContaCapitalLegadoDTO> getLstParcelamentoContaCapitalLegadoDTO() {
		return lstParcelamentoContaCapitalLegadoDTO;
	}

	/**
	 * Define o valor de lstParcelamentoContaCapitalLegadoDTO.
	 *
	 * @param lstParcelamentoContaCapitalLegadoDTO o novo valor de lstParcelamentoContaCapitalLegadoDTO
	 */
	public void setLstParcelamentoContaCapitalLegadoDTO(
			List<ParcelamentoContaCapitalLegadoDTO> lstParcelamentoContaCapitalLegadoDTO) {
		this.lstParcelamentoContaCapitalLegadoDTO = lstParcelamentoContaCapitalLegadoDTO;
	}




	
}
