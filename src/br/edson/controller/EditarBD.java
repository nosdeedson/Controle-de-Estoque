package br.edson.controller;

import br.edson.dao.FornecedorDao;
import br.edson.dao.ProdutoDao;
import br.edson.dao.ResponsavelDao;
import br.edson.model.Fornecedor;
import br.edson.model.Produto;
import br.edson.model.ResponsavelRetirada;

public class EditarBD {
	
	public static int  EditarProduto( Produto prod) {
		int editado = 0;
		editado = ProdutoDao.editarProduto(prod);
		return editado;
	}
	
	public static int editarFornecedor( Fornecedor  ff) {
		int editado = 0;
		editado = FornecedorDao.editarFornecedor(ff);
		if( editado > 0) {
			return editado;
		}
		else if(editado == -1) {
			return editado;
		}
		return editado;
	}
	

	
	public static int editarResponsavel( ResponsavelRetirada resp) {
		
		int editado = 0;
		editado = ResponsavelDao.editarResponsavel(resp);
		return editado;
	}
}
