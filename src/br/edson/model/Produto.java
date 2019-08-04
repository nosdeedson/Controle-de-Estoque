package br.edson.model;

public class Produto {
	private String nome;
	private int quantidade;
	private int codigo;
	
	public Produto(String nome, int quantidade, int codigo) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
		this.codigo = codigo;
	}
	
	public Produto() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) { 
		this.quantidade = quantidade;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
}
