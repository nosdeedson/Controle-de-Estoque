package br.edson.model;

public class PrevisaoConsumo {
	
	private String nomeProduto;
	private int quantidade;
	public PrevisaoConsumo(String nomeProduto, int quantidade) {
		super();
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
	}
	public PrevisaoConsumo() {
		super();
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
