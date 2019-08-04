package br.edson.helper;

import java.util.List;

import br.edson.dao.FornecedorDao;
import br.edson.dao.ProdutoDao;
import br.edson.dao.ResponsavelDao;
import br.edson.model.Fornecedor;
import br.edson.model.Produto;
import br.edson.model.ResponsavelRetirada;

public class BuscarListasObjetosBD {
	
	public static List<Produto> buscaProdutosBD(List<Produto> prod){
		prod= ProdutoDao.listaProdutos();
		return prod;
	}
	
	public static List<Fornecedor> buscarFornecedores(List<Fornecedor> f, boolean tipoPessoa ){
		f = FornecedorDao.listaNomesFornecedores(f, tipoPessoa);// true busca pessoa fisica false pessoa juridica
		return f;
	}
	
	public static List<ResponsavelRetirada> buscarResponsaveis( List<ResponsavelRetirada> resp){
		resp = ResponsavelDao.listaResponsaveis();

		return resp;
	}
	
}
