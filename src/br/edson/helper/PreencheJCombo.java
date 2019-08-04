package br.edson.helper;

import java.util.List;

import javax.swing.JComboBox;

import br.edson.model.Fornecedor;
import br.edson.model.Produto;
import br.edson.model.ResponsavelRetirada;

public class PreencheJCombo {
	public static JComboBox<String> preencheJCombo( JComboBox<String> jCombo, List<Produto> prod){
		for( int i =0; i <= prod.size(); i++) {
			if( i == 0)
				jCombo.addItem("Produtos");
			else
				jCombo.addItem(prod.get(i - 1).getNome().toString());
		}
		return jCombo;
	}
	
	public static JComboBox<String> preencheJComboFornecedor( JComboBox<String> jCombo, List<Fornecedor> f){
		for( int i =0; i <= f.size(); i++) {
			if( i == 0)
				jCombo.addItem("Fornecedores");
			else
				jCombo.addItem(f.get(i - 1).getNome().toString());
		}
		return jCombo;
	}

	public static JComboBox<String> preencheJComboBoResponsaveis( JComboBox<String> jcombo, List<ResponsavelRetirada> resp){
		for( int i = 0; i <= resp.size(); i++) {
			if( i == 0 )
				jcombo.addItem("Responsável");
			else
				jcombo.addItem(resp.get(i -1).getNome().toString());
		}
		return jcombo;
	}
}
