package br.edson.model;

public class Retirada {
	private String dataRetirada;
	private int codigoProduto;
	private int codigoResponsavel;
	private int qtd;
	public Retirada(String dataRetirada, int codigoProduto, int codigoResponsavel, int qtd) {
		super();
		this.dataRetirada = dataRetirada;
		this.codigoProduto = codigoProduto;
		this.codigoResponsavel = codigoResponsavel;
		this.qtd = qtd;
	}
	public Retirada() {
		super();
	}
	public String getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(String dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public int getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(int codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public int getCodigoResponsavel() {
		return codigoResponsavel;
	}
	public void setCodigoResponsavel(int codigoResponsavel) {
		this.codigoResponsavel = codigoResponsavel;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
	
}
