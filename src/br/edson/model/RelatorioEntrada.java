package br.edson.model;

public class RelatorioEntrada {
	private String nomeProduto, dataEntrada,
	nomeFornecedor;
	private int numeroNota, quantidade;
	
	public RelatorioEntrada(String nomeProduto, String dataEntrada, String nomeFornecedor, int numeroNota,
			int quantidade) {
		super();
		this.nomeProduto = nomeProduto;
		this.dataEntrada = dataEntrada;
		this.nomeFornecedor = nomeFornecedor;
		this.numeroNota = numeroNota;
		this.quantidade = quantidade;
	}
	public RelatorioEntrada() {
		super();
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	public int getNumeroNota() {
		return numeroNota;
	}
	public void setNumeroNota(int numeroNota) {
		this.numeroNota = numeroNota;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
