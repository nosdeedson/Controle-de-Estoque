package br.edson.model;

public class RelatorioDevolucao {
	private String nomeResponsavel, nomeProduto;
	private int quantidade;
	public RelatorioDevolucao(String nomeResponsavel, String nomeProduto, int quantidade) {
		super();
		this.nomeResponsavel = nomeResponsavel;
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
	}
	public RelatorioDevolucao() {
		super();
	}
	public String getNomeResponsavel() {
		return nomeResponsavel;
	}
	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
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
