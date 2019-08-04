package br.edson.model;

public class Fornecedor {
	
	private String nome;
	private long fone;
	private String email; 
	private long identificacaoPessoa;
	public Fornecedor() {
		super();
	}
	public Fornecedor(String nome, long fone, String email, long identificacaoPessoa) {
		super();
		this.nome = nome;
		this.fone = fone;
		this.email = email;
		this.identificacaoPessoa = identificacaoPessoa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getFone() {
		return fone;
	}
	public void setFone(long fone) {
		this.fone = fone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getIdentificacaoPessoa() {
		return identificacaoPessoa;
	}
	public void setIdentificacaoPessoa(long identificacaoPessoa) {
		this.identificacaoPessoa = identificacaoPessoa;
	}
	
	
	
}
