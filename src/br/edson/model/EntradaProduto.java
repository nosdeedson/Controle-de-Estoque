package br.edson.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EntradaProduto {
	private String dataEntrada;
	private int numeroNF;
	private int codigoProduto;
	private long identificacaopessoa;
	private boolean tipoForne; // true pessoa fisica, false pessoa juridica
	private int qtdEntrada;
	
	public EntradaProduto() {
		super();
	}
	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public int getNumeroNF() {
		return numeroNF;
	}
	public void setNumeroNF(int numeroNF) {
		this.numeroNF = numeroNF;
	}

	public boolean isTipoForne() {
		return tipoForne;
	}
	public void setTipoForne(boolean tipoForne) {
		this.tipoForne = tipoForne;
	}
	public long getIdentificacaopessoa() {
		return identificacaopessoa;
	}
	public void setIdentificacaopessoa(long identificacaopessoa) {
		this.identificacaopessoa = identificacaopessoa;
	}
	public int getQtdEntrada() {
		return qtdEntrada;
	}
	public void setQtdEntrada(int qtdEntrada) {
		this.qtdEntrada = qtdEntrada;
	}
	public int getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(int codigoProduto) {
		this.codigoProduto = codigoProduto;
	}


	
	
}// fim classe
