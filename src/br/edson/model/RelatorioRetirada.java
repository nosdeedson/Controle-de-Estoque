package br.edson.model;

public class RelatorioRetirada {
	private String nomeProduto, dataRetirada,
	responsavel;
	private int quantidade;
	public RelatorioRetirada(String nomeProduto, String dataRetirada, String responsavel,int quantidade) {
		super();
		this.nomeProduto = nomeProduto;
		this.dataRetirada = dataRetirada;
		this.responsavel = responsavel;
		this.quantidade = quantidade;
	}
	public RelatorioRetirada() {
		super();
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getdataRetirada() {
		return dataRetirada;
	}
	public void setdataRetirada(String dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}// fim classe
