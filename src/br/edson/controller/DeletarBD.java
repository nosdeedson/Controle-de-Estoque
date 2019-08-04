package br.edson.controller;

import br.edson.dao.FornecedorDao;
import br.edson.dao.ProdutoDao;
import br.edson.dao.ResponsavelDao;
import br.edson.model.Fornecedor;

public class DeletarBD {
	public static int deletarFornecedor( long identificacaoPessoa) {
		int deletado = 0;
		deletado = FornecedorDao.deletarFornecedor(identificacaoPessoa);
		return deletado;
	}
	public static int deletarProduto( int codigoProduto) {
		int deletado = ProdutoDao.deletarProduto(codigoProduto);
		return deletado;
	}
	public static int deletarResponsavel( int codigoResponsavel) {
		int deletado = 0;
		deletado = ResponsavelDao.deletarResponsave(codigoResponsavel);
		return deletado;
	}
}
