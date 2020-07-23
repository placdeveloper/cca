/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.AnotacaoPessoaDTO;

/**
 * DTO RelAprovacaoQuadroPendenciaDTO
 */
public class RelAprovacaoQuadroPendenciaDTO extends BancoobDto {

	private static final long serialVersionUID = -2253275109270721569L;
	
	private Integer idContaCapital;
	private Integer idPessoa;
	private Integer idInstituicao;
	
	private String descCooperativa;
	private String nome;
	private String cpfCnpj;
	private Integer numContaCapital;
	private DateTimeDB data;
	
	private BigDecimal integralizacaoVista;
	private BigDecimal valorTotalParcela;
	private BigDecimal capitalSubscrever;
	private BigDecimal valorParcelas;
	private Integer qtdParcelasMensais;
	private Integer diaDebito;
	private String formaDebito;
	
	private List<AnotacaoPessoaDTO> listaBaixadas;
	private List<AnotacaoPessoaDTO> listaVigentes;
	
	
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
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public String getDescCooperativa() {
		return descCooperativa;
	}
	public void setDescCooperativa(String descCooperativa) {
		this.descCooperativa = descCooperativa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	public Integer getNumContaCapital() {
		return numContaCapital;
	}
	public void setNumContaCapital(Integer numContaCapital) {
		this.numContaCapital = numContaCapital;
	}
	public DateTimeDB getData() {
		return data;
	}
	public void setData(DateTimeDB data) {
		this.data = data;
	}
	public BigDecimal getIntegralizacaoVista() {
		return integralizacaoVista;
	}
	public void setIntegralizacaoVista(BigDecimal integralizacaoVista) {
		this.integralizacaoVista = integralizacaoVista;
	}
	public BigDecimal getValorTotalParcela() {
		return valorTotalParcela;
	}
	public void setValorTotalParcela(BigDecimal valorTotalParcela) {
		this.valorTotalParcela = valorTotalParcela;
	}
	public BigDecimal getCapitalSubscrever() {
		return capitalSubscrever;
	}
	public void setCapitalSubscrever(BigDecimal capitalSubscrever) {
		this.capitalSubscrever = capitalSubscrever;
	}
	public BigDecimal getValorParcelas() {
		return valorParcelas;
	}
	public void setValorParcelas(BigDecimal valorParcelas) {
		this.valorParcelas = valorParcelas;
	}
	public Integer getQtdParcelasMensais() {
		return qtdParcelasMensais;
	}
	public void setQtdParcelasMensais(Integer qtdParcelasMensais) {
		this.qtdParcelasMensais = qtdParcelasMensais;
	}
	public Integer getDiaDebito() {
		return diaDebito;
	}
	public void setDiaDebito(Integer diaDebito) {
		this.diaDebito = diaDebito;
	}
	public String getFormaDebito() {
		return formaDebito;
	}
	public void setFormaDebito(String formaDebito) {
		this.formaDebito = formaDebito;
	}
	public List<AnotacaoPessoaDTO> getListaBaixadas() {
		return listaBaixadas;
	}
	public void setListaBaixadas(List<AnotacaoPessoaDTO> listaBaixadas) {
		this.listaBaixadas = listaBaixadas;
	}
	public List<AnotacaoPessoaDTO> getListaVigentes() {
		return listaVigentes;
	}
	public void setListaVigentes(List<AnotacaoPessoaDTO> listaVigentes) {
		this.listaVigentes = listaVigentes;
	}
}
