package br.edson.model;

public class ResponsavelRetirada {
	private String nome;
	private int codigo;
	public ResponsavelRetirada(String nome, int codigo) {
		super();
		this.nome = nome;
		this.codigo = codigo;
	}
	public ResponsavelRetirada() {
		super();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	
}
