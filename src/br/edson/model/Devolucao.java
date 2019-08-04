package br.edson.model;

public class Devolucao {
	private String dataDevolucao;
	private int codigoProduto;
	private int codigoResponsavel;
	private int qtd;
	public Devolucao(String dataDevolucao, int codigoProduto, int codigoResponsavel, int qtd) {
		super();
		this.dataDevolucao = dataDevolucao;
		this.codigoProduto = codigoProduto;
		this.codigoResponsavel = codigoResponsavel;
		this.qtd = qtd;
	}
	public Devolucao() {
		super();
	}
	public String getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
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
