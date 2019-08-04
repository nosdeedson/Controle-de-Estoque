package br.edson.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.management.relation.Relation;
import javax.swing.JOptionPane;

import br.edson.dao.PrevisaoConsumoDao;
import br.edson.dao.RelatorioDevolucaoDao;
import br.edson.dao.RelatorioEntradaDao;
import br.edson.dao.RelatorioRetiradaDao;
import br.edson.model.Fornecedor;
import br.edson.model.PrevisaoConsumo;
import br.edson.model.RelatorioDevolucao;
import br.edson.model.RelatorioEntrada;
import br.edson.model.RelatorioRetirada;
import br.edson.model.ResponsavelRetirada;

public class TrataDados {
	private static List<RelatorioEntrada> relatorioEntrada;
	private static List<RelatorioRetirada> relatorioRetirada;

	public static String inverteDataParaBD(String dia) {
		String data = "";
		if (dia.equals(""))
			return data;
		int x = dia.length();
		char d[] = new char[x];
		for (int i = dia.length() - 4; i < dia.length(); i++) {
			d[i] = dia.charAt(i);
			data += d[i];
		}
		data += "/";
		for (int i = dia.length() - 7; i < dia.length() - 5; i++) {
			d[i] = dia.charAt(i);
			data += d[i];
		}
		data += "/";
		for (int i = 0; i < 2; i++) {
			d[i] = dia.charAt(i);
			data += d[i];
		}
		return data;
	}

	public static String inverteDataParaUsuario(String dia) {
		String data = "";
		
		if (dia.equals(""))
			return data;
		int x = dia.length();
		char d[] = new char[x];
		for (int i = dia.length() - 2; i < dia.length(); i++) {
			d[i] = dia.charAt(i);
			data += d[i];
		}
		data += "/";
		for (int i = dia.length() - 5; i < dia.length() - 3; i++) {
			d[i] = dia.charAt(i);
			data += d[i];
		}
		data += "/";
		for (int i = 0; i < 4; i++) {
			d[i] = dia.charAt(i);
			data += d[i];
		}
		return data;
	}

	private static void inverteDataListaRelatorioEntrada(List<RelatorioEntrada> listaEntrada) {
		for (int i = 0; i < listaEntrada.size(); i++) {
			listaEntrada.get(i).setDataEntrada(inverteDataParaUsuario(listaEntrada.get(i).getDataEntrada()));
		}
	}

	private static void inverteDataListaRelatorioRetirada(List<RelatorioRetirada> listaRetirada) {
		
		for (int i = 0; i < listaRetirada.size(); i++) {
			String data = listaRetirada.get(i).getdataRetirada();
			String d = "";
			char dia; 
			for(int j = 0; j < data.length(); j++) {
				dia = data.charAt(j);
				if( dia == 32)
					break;
				else
					d += dia;
			}
			listaRetirada.get(i).setdataRetirada(d);
			listaRetirada.get(i).setdataRetirada(inverteDataParaUsuario(listaRetirada.get(i).getdataRetirada()));
		}
	}
	// verifica se as datas tem valores atribuídos

	public static void preparaRelatorio(long id, int codigoProduto, String dataInicio, String dataFinal,boolean entradaRetirada) {
		// se true relatorio entrada, false relatorio retirada
		if (dataInicio.equals("") && dataFinal.equals("")) {
			verificaPessoaProduto(id, codigoProduto, entradaRetirada);
		} else if (!dataInicio.equals("") && dataFinal.equals("")) {
			Calendar c = Calendar.getInstance();
			dataFinal = new SimpleDateFormat("dd/MM/YYYY").format(c.getTime());
			verificaPessoaProduto(id, codigoProduto, dataInicio, dataFinal, entradaRetirada);
		} else if (!dataInicio.equals("") && !dataFinal.equals("")) {
			verificaPessoaProduto(id, codigoProduto, dataInicio, dataFinal, entradaRetirada);
		} else if (dataInicio.equals("") && !dataFinal.equals(""))
			JOptionPane.showMessageDialog(null, "Digite a data de inicio");

	}
	// verifica se indentificacaoPessoa e codigoProduto tem valores atribuídos

	private static void verificaPessoaProduto(long id, int codigoProduto, String dataInicio, String dataFinal, boolean entradaRetirada) { /// datas com valores
		// datas com valores se true relatorio entrada, false relatorio retirada id
		// refere-se a identificação fornecedor e codigo responsável
		if (id > 0 && codigoProduto > 0) {
			if (entradaRetirada) {
				relatorioEntrada = new ArrayList<RelatorioEntrada>();
				relatorioEntrada = RelatorioEntradaDao.listaEntradas(id, codigoProduto, dataInicio, dataFinal);
				if (relatorioEntrada.size() == 0)
					JOptionPane.showMessageDialog(null, " Nenhum dado a ser informado!!\n Refine sua pesquisa.");
				else {
					inverteDataListaRelatorioEntrada(relatorioEntrada);
					GerarRelatorioEntrada.relatorioEntrada(relatorioEntrada);
				}
			} else {
				relatorioRetirada = new ArrayList<RelatorioRetirada>();
				relatorioRetirada = RelatorioRetiradaDao.listaRetiradas(id, codigoProduto, dataInicio, dataFinal);
				if (relatorioRetirada.size() == 0)
					JOptionPane.showMessageDialog(null, " Nenhum dado a ser informado!!\n Refine sua pesquisa.");
				else {
					inverteDataListaRelatorioRetirada(relatorioRetirada);
					GerarRelatorioRetirada.relatorioRetirada(relatorioRetirada);
				}
			}

		} else if (id > 0 && codigoProduto == 0) { // datas com valores
			if (entradaRetirada) {
				relatorioEntrada = new ArrayList<RelatorioEntrada>();
				relatorioEntrada = RelatorioEntradaDao.listaEntradasFornecedor(id, dataInicio, dataFinal);
				if (relatorioEntrada.size() == 0)
					JOptionPane.showMessageDialog(null, " Nenhum dado a ser informado!!\n Refine sua pesquisa.");
				else {
					inverteDataListaRelatorioEntrada(relatorioEntrada);
					GerarRelatorioEntrada.relatorioEntrada(relatorioEntrada);
				}
			} else {			
				relatorioRetirada = new ArrayList<RelatorioRetirada>();
				relatorioRetirada = RelatorioRetiradaDao.listaRetiradas(id, dataInicio, dataFinal);
				if (relatorioRetirada.size() == 0) {
					JOptionPane.showMessageDialog(null, " Nenhum dado a ser informado!!\n Refine sua pesquisa.");
				} else {
					inverteDataListaRelatorioRetirada(relatorioRetirada);
					GerarRelatorioRetirada.relatorioRetirada(relatorioRetirada);
				}
			}

		} else if (id == 0 && codigoProduto > 0) { // datas com valores

			if (entradaRetirada) {
				relatorioEntrada = new ArrayList<RelatorioEntrada>();
				relatorioEntrada = RelatorioEntradaDao.listaEntradasProduto(codigoProduto, dataInicio, dataFinal);
				if (relatorioEntrada.size() == 0)
					JOptionPane.showMessageDialog(null, " Nenhum dado a ser informado!!\n Refine sua pesquisa.");
				else {
					inverteDataListaRelatorioEntrada(relatorioEntrada);
					GerarRelatorioEntrada.relatorioEntrada(relatorioEntrada);
				}
			} else {
				
				relatorioRetirada = new ArrayList<RelatorioRetirada>();
				relatorioRetirada = RelatorioRetiradaDao.listaRetiradasProduto(codigoProduto, dataInicio, dataFinal);
				if (relatorioRetirada.size() == 0) {
					JOptionPane.showMessageDialog(null, " Nenhum dado a ser informado!!\n Refine sua pesquisa.");
				} else {
					inverteDataListaRelatorioRetirada(relatorioRetirada);
					GerarRelatorioRetirada.relatorioRetirada(relatorioRetirada);
				}
			}

		} else if (id == 0 && codigoProduto == 0) { // datas com valores
			if (entradaRetirada) {
				relatorioEntrada = new ArrayList<RelatorioEntrada>();
				relatorioEntrada = RelatorioEntradaDao.listaEntradas(dataInicio, dataFinal);
				if (relatorioEntrada.size() == 0)
					JOptionPane.showMessageDialog(null, " Nenhum dado a ser informado!!\n Refine sua pesquisa.");
				else {
					inverteDataListaRelatorioEntrada(relatorioEntrada);
					GerarRelatorioEntrada.relatorioEntrada(relatorioEntrada);
				}
			} else {
				relatorioRetirada = new ArrayList<RelatorioRetirada>();
				relatorioRetirada = RelatorioRetiradaDao.listaRetiradas(dataInicio, dataFinal);
			
				if (relatorioRetirada.size() == 0) {
					JOptionPane.showMessageDialog(null, " Nenhum dado a ser informado!!\n Refine sua pesquisa.");
				} else {
					inverteDataListaRelatorioRetirada(relatorioRetirada);
					GerarRelatorioRetirada.relatorioRetirada(relatorioRetirada);
				}
			}

		}
	}

	/// gera lista entrada sem data de referencia
	private static void verificaPessoaProduto(long id, int codigoProduto, boolean entradaRetirada) {
		if (id == 0 && codigoProduto == 0) {/// seleciona todas as entradas
			if (entradaRetirada) {
				relatorioEntrada = new ArrayList<RelatorioEntrada>();
				relatorioEntrada = RelatorioEntradaDao.listaEntradas();
				if (relatorioEntrada.size() == 0)
					JOptionPane.showMessageDialog(null, " Nenhum dado a ser informado!!\n Refine sua pesquisa.");
				else {
					inverteDataListaRelatorioEntrada(relatorioEntrada);
					GerarRelatorioEntrada.relatorioEntrada(relatorioEntrada);
				}
			} else {
				relatorioRetirada = new ArrayList<RelatorioRetirada>();
				relatorioRetirada = RelatorioRetiradaDao.listaRetiradas();
				if (relatorioRetirada.size() == 0) {
					JOptionPane.showMessageDialog(null, " Nenhum dado a ser informado!!\n Refine sua pesquisa.");
				} else {
					inverteDataListaRelatorioRetirada(relatorioRetirada);
					GerarRelatorioRetirada.relatorioRetirada(relatorioRetirada);
				}
			}
		} else if (id == 0 && codigoProduto > 0) {// seleciona entrada de acordo com um produto
			if (entradaRetirada) {
				relatorioEntrada = new ArrayList<RelatorioEntrada>();
				relatorioEntrada = RelatorioEntradaDao.listaEntradasProdutos(codigoProduto);
				if (relatorioEntrada.size() == 0)
					JOptionPane.showMessageDialog(null, " Nenhum dado a ser informado!!\n Refine sua pesquisa.");
				else {
					inverteDataListaRelatorioEntrada(relatorioEntrada);
					GerarRelatorioEntrada.relatorioEntrada(relatorioEntrada);
				}
			} else {
				relatorioRetirada = new ArrayList<RelatorioRetirada>();
				relatorioRetirada = RelatorioRetiradaDao.listaRetiradasProdutos(codigoProduto);
				if (relatorioRetirada.size() == 0) {
					JOptionPane.showMessageDialog(null, " Nenhum dado a ser informado!!\n Refine sua pesquisa.");
				} else {
					inverteDataListaRelatorioRetirada(relatorioRetirada);
					GerarRelatorioRetirada.relatorioRetirada(relatorioRetirada);
				}
			}
		} else if (id > 0 && codigoProduto == 0) {

			if (entradaRetirada) {
				relatorioEntrada = new ArrayList<RelatorioEntrada>();
				relatorioEntrada = RelatorioEntradaDao.listaEntradasFornecedor(id);
				if (relatorioEntrada.size() == 0)
					JOptionPane.showMessageDialog(null, " Nenhum dado a ser informado!!\n Refine sua pesquisa.");
				else {
					inverteDataListaRelatorioEntrada(relatorioEntrada);
					GerarRelatorioEntrada.relatorioEntrada(relatorioEntrada);
				}
			} else {
				relatorioRetirada = new ArrayList<RelatorioRetirada>();
				relatorioRetirada = RelatorioRetiradaDao.listaRetiradasResponsavel(id);
				if (relatorioRetirada.size() == 0) {
					JOptionPane.showMessageDialog(null, " Nenhum dado a ser informado!!\n Refine sua pesquisa.");
				} else {
					inverteDataListaRelatorioRetirada(relatorioRetirada);
					GerarRelatorioRetirada.relatorioRetirada(relatorioRetirada);
				}
			}
		} else {
			
			if (entradaRetirada) {
				relatorioEntrada = new ArrayList<RelatorioEntrada>();
				relatorioEntrada = RelatorioEntradaDao.listaEntradas(id, codigoProduto);
				if (relatorioEntrada.size() == 0)
					JOptionPane.showMessageDialog(null, " Nenhum dado a ser informado!!\n Refine sua pesquisa.");
				else {
					
					inverteDataListaRelatorioEntrada(relatorioEntrada);
					GerarRelatorioEntrada.relatorioEntrada(relatorioEntrada);
				}
			}
			else {
				
				relatorioRetirada = new ArrayList<RelatorioRetirada>();
				relatorioRetirada = RelatorioRetiradaDao.listaRetiradas(id, codigoProduto);
				if (relatorioRetirada.size() == 0) {
					JOptionPane.showMessageDialog(null, " Nenhum dado a ser informado!!\n Refine sua pesquisa.");
				} else {
					
					inverteDataListaRelatorioRetirada(relatorioRetirada);
					GerarRelatorioRetirada.relatorioRetirada(relatorioRetirada);
				}
			}
		}
	}
	public static void gerarRelatorioDevolucao() {
		List<RelatorioDevolucao> dev = new ArrayList<RelatorioDevolucao>();
		dev = RelatorioDevolucaoDao.gerarRelatorioDevolucao();
		if( dev.size() != 0) {
			GerarRelatorioDevolucao.gerarRelatorioDevolucao(dev);
		}
		else
			JOptionPane.showMessageDialog(null, "Nenhum devlução realizada!!");
	}
	
	public static void gerarListaPdfFornecedor(boolean tipoPessoa) { // tipoPessoa false pessoa juridica, true pessoa fisica 
		
		if(tipoPessoa) {
			List<Fornecedor> listaPessoaFisica = new ArrayList<Fornecedor>();
			BuscarListasObjetosBD.buscarFornecedores(listaPessoaFisica, true);// true pessoa fisica
			GerarListaPDFFornecedores.gerarListaPDFFornecedores(listaPessoaFisica, true);// true pessoa física, false pessoa juridica
		}
		else {
			List<Fornecedor> listaPessoaJuridica = new ArrayList<Fornecedor>();
			BuscarListasObjetosBD.buscarFornecedores(listaPessoaJuridica, false); // false pessoa juridica
			GerarListaPDFFornecedores.gerarListaPDFFornecedores(listaPessoaJuridica, false);
		}
	}
	
	public static void gerarListaResponsavel() {
		List<ResponsavelRetirada> responsaveis = new ArrayList<ResponsavelRetirada>();
		responsaveis  = BuscarListasObjetosBD.buscarResponsaveis(responsaveis);
		GerarListaPDFResponsaveis.gerarListaPDFResponsaveis(responsaveis);
	}
	
	public static void previsaoConsumo( int qtdMeses) {
		List<PrevisaoConsumo> previsaoConsumo = new ArrayList<PrevisaoConsumo>();
		previsaoConsumo = PrevisaoConsumoDao.previsaoConsumo(qtdMeses);
		GerarPDFPrevisaoConsumo.gerarPDFPrecisaoConsumo(previsaoConsumo, qtdMeses);
	}
	
	
	public static String palavraParaNumero( String fone) {
		char c;
		String num = "";
		for (int i = 0; i < fone.length(); i++) {
			c = fone.charAt(i);
			
			if(Character.isDigit(c)) {
				num += c;
			}
				
		}
		return num;
	}
}// fim classe



























