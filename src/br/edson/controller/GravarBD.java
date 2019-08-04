package br.edson.controller;

import java.util.List;

import br.edson.dao.DevolucaoDao;
import br.edson.dao.EntradaDao;
import br.edson.dao.FornecedorDao;
import br.edson.dao.ProdutoDao;
import br.edson.dao.ResponsavelDao;
import br.edson.dao.RetiradaDao;
import br.edson.model.Devolucao;
import br.edson.model.EntradaProduto;
import br.edson.model.Fornecedor;

import br.edson.model.Produto;
import br.edson.model.ResponsavelRetirada;
import br.edson.model.Retirada;

public class GravarBD {
	
	public static int inserirDevolucao( List<Devolucao> devolucao, List<Produto> listaProdutos) {
		int inserido = 0;
		inserido = DevolucaoDao.inserirDevolucao(devolucao, listaProdutos);
		return inserido;
	}
	
	public static int inserirEntrada( List<EntradaProduto> listaEntradaProdutos, List<Produto> listaProdutos) {
		int ok =0; 
		ok = EntradaDao.inserirEntrada(listaEntradaProdutos, listaProdutos);
		return ok;
	}
	
	public static int inserirFornecedor( Fornecedor f, boolean tipoPessoa) {
		boolean flag = validaIdentificacaoPessoa(f.getIdentificacaoPessoa(), tipoPessoa); //true pessoa fisica
		int cadastrado = 0; // zero fornecedor existente, valor maior que 0 fornecedor cadastrado, -1 falha no banco
		if(flag)
			cadastrado = FornecedorDao.cadastrarFornecedor(f);
		else
			cadastrado = -2;
		return cadastrado ;
		
	}

	public static int inserirProdutoBD( Produto prod) {
		int inserido= ProdutoDao.inserirProduto(prod);
		return inserido;
	}
	public static int inserirResponsavel( ResponsavelRetirada resp) {
		int cadastrado = ResponsavelDao.inserirResponsavel(resp);
		return cadastrado;
	}
	
	public static int inserirRetirada( List<Retirada> retiradas, List<Produto> listaProdutos) {
		int inserido = 0;
		inserido = RetiradaDao.inserirRetirada(retiradas, listaProdutos);
		return inserido;
	}
	
	// tratamento de dados
	public static boolean validaIdentificacaoPessoa( Long id, boolean tipoPessoa) {
		boolean flag = false;
		if(tipoPessoa) {
			int soma=0, soma1=0;
			
			int mult = 10, mult1 = 11;
			String num = Long.toString(id);
			int digito1= 0, digito2 = 0;
			if( num.length() < 11)
				num = "0"+num;
			
			String n[] = new String[11];
			for( int i = 0; i < num.length(); i++) {
				n[i] = Character.toString(num.charAt(i));
				int x = Integer.parseInt(n[i]);
				if( i < 9)
					soma += x * mult;
				if( i <= 9 )
					soma1 += x * mult1;
				mult--;
				mult1--;
				if( i == 9) {
					digito1 = x;
				}
				if(i == 10)
					digito2 = x;
			}
			soma = soma % 11;
			soma = 11 - soma;
			soma1 = soma1 % 11;
			soma1 = 11 - soma1;
			if( (soma > 9 || soma == digito1) && (soma1 > 9 || soma1 == digito2) ) {
				flag = true;
			}
		}
		else {

			int soma = 0, soma1 = 0;
			int mult = 2, mult1 = 2;
			int digito1 = 0, digito2 = 0;
			String cnpj = Long.toString(id);
			if(cnpj.length() < 14)
				cnpj = "0"+cnpj;

			String a = Character.toString(cnpj.charAt(12));
			String b = Character.toString(cnpj.charAt(13));
			digito1 = Integer.parseInt(a);
			digito2 = Integer.parseInt(b);

			int x = 0;
			String d[] = new String[13];

			for( int i = 12; i >= 0; i--) {
				d[x] = Character.toString(cnpj.charAt(i));
				int  y = Integer.parseInt(d[x]);
				
				if( i < 12) {
					soma += mult * y;
					if( mult < 9)
						mult++;
					else
						mult = 2;				
				}
				
				soma1 += mult1 * y;
				if(mult1 < 9)
					mult1++;
				else
					mult1=2;
				x++;
			}

			soma = soma % 11;
			soma1 = soma1 % 11;
			if( soma < 2)
				soma = 0;
			else
				soma = 11-soma;
			if(soma1 < 2 )
				soma1 = 0;
			else
				soma1 = 11-soma1;
			
			if( soma == digito1 && soma1 == digito2 )
				flag = true;
			
		}
	
		return flag;
	}
	//tirar
}// fim classe

































