package br.com.sicoob.sisbr.cca.legado.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

public class ProdutoDTO extends BancoobDto {	

	private static final long serialVersionUID = -878905159443614275L;
	
	private Integer IDProduto;
	private Date DataAntProd;
	private Date DataProxProd;
	private Date DataAtualProd;
	
	public Integer getIDProduto() {
		return IDProduto;
	}
	public void setIDProduto(Integer iDProduto) {
		IDProduto = iDProduto;
	}
	public Date getDataAntProd() {
		return DataAntProd;
	}
	public void setDataAntProd(Date dataAntProd) {
		DataAntProd = dataAntProd;
	}
	public Date getDataProxProd() {
		return DataProxProd;
	}
	public void setDataProxProd(Date dataProxProd) {
		DataProxProd = dataProxProd;
	}
	public Date getDataAtualProd() {
		return DataAtualProd;
	}
	public void setDataAtualProd(Date dataAtualProd) {
		DataAtualProd = dataAtualProd;
	}
	
	
}
