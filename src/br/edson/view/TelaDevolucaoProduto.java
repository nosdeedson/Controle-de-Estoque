package br.edson.view;

import javax.swing.JFrame;

import br.edson.controller.GravarBD;
import br.edson.helper.BuscarListasObjetosBD;
import br.edson.helper.PreencheJCombo;
import br.edson.model.Devolucao;
import br.edson.model.EntradaProduto;
import br.edson.model.Produto;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class TelaDevolucaoProduto extends JFrame {
	private JComboBox<String> jcProdutos;
	private JTextField txtQTD;
	private List<Produto> produtos;// produtos no BD
	private List<Devolucao> devolucao; // o objeto retirada e devolucao tem os mesmos atributos, portanto 
	private List<Produto> listaProdutosAtualizar; // produtos a terem a quantidade atualiza
	private int codigoProduto;
	private int qtdProduto;

	/**
	 * Create the panel.
	 */
	public TelaDevolucaoProduto(int codigoResponsavel, String nomeResponsavel) {
		setFont(new Font("Arial Black", Font.PLAIN, 15));

		setTitle("Devolu\u00E7\u00E3o Produto");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(500, 250, 600, 235);
		getContentPane().setLayout(null);

		jcProdutos = new JComboBox<String>();
		jcProdutos.setMaximumRowCount(20);
		jcProdutos.setBounds(0, 0, 593, 20);
		getContentPane().add(jcProdutos);

		JLabel lblProduto = new JLabel("QTD Produto");
		lblProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblProduto.setBounds(224, 129, 250, 14);
		getContentPane().add(lblProduto);

		txtQTD = new JTextField();
		txtQTD.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		txtQTD.setHorizontalAlignment(SwingConstants.CENTER);
		txtQTD.setBounds(510, 126, 58, 20);
		getContentPane().add(txtQTD);
		txtQTD.setColumns(10);

		JButton btnProximoItem = new JButton("Proximo Item");
		btnProximoItem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnProximoItem.setBounds(434, 155, 150, 23);
		getContentPane().add(btnProximoItem);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnGravar.setBounds(47, 154, 150, 25);
		getContentPane().add(btnGravar);

		JLabel lblDigiteAQuantidade = new JLabel("Digite a quantidade de todos os produtos antes de gravar");
		lblDigiteAQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblDigiteAQuantidade.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblDigiteAQuantidade.setBounds(10, 31, 583, 20);
		getContentPane().add(lblDigiteAQuantidade);

		Calendar c = Calendar.getInstance();
		String hoje = new SimpleDateFormat("dd/MM/YYYY").format(c.getTime());

		JLabel lblDataEntrada = new JLabel("Data Entrada: " + hoje);
		lblDataEntrada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblDataEntrada.setBounds(24, 129, 190, 14);
		getContentPane().add(lblDataEntrada);

		JLabel lblResponsavelRetirada = new JLabel("Respons\u00E1vel Devolu\u00E7\u00E3o: "+ nomeResponsavel);
		lblResponsavelRetirada.setHorizontalAlignment(SwingConstants.CENTER);
		lblResponsavelRetirada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblResponsavelRetirada.setBounds(10, 95, 574, 20);
		getContentPane().add(lblResponsavelRetirada);

		produtos = new ArrayList<Produto>();// produtos no BD
		produtos = BuscarListasObjetosBD.buscaProdutosBD(produtos);

		devolucao = new ArrayList<Devolucao>(); // retiradas realizadas
		listaProdutosAtualizar = new ArrayList<Produto>(); // produtos a terem a quantidade atualiza

		jcProdutos = PreencheJCombo.preencheJCombo(jcProdutos, produtos);
		jcProdutos.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					codigoProduto = produtos.get(jcProdutos.getSelectedIndex() - 1).getCodigo();
					lblProduto.setText("QTD " + jcProdutos.getSelectedItem().toString());
				}
			}
		});

		btnProximoItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				if (txtQTD.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Digite a quantidade!!");
				else if (jcProdutos.getSelectedItem().equals("Produtos"))
					JOptionPane.showMessageDialog(null, "Escolha um produto!!");
				else {
					Devolucao dev = new Devolucao();
					dev.setCodigoProduto(codigoProduto);
					dev.setCodigoResponsavel(codigoResponsavel);
					dev.setDataDevolucao(hoje);
					dev.setQtd(Integer.parseInt(txtQTD.getText()));
					devolucao.add(dev);
					produtos.get(jcProdutos.getSelectedIndex() - 1).setQuantidade(Integer.parseInt(txtQTD.getText()));
					listaProdutosAtualizar.add(produtos.get(jcProdutos.getSelectedIndex() - 1));
					txtQTD.setText("");
					lblProduto.setText("qtd produto");
				}
			}
		});

		btnGravar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				if(!txtQTD.getText().equals("")) {
					Devolucao dev = new Devolucao();
					dev.setCodigoProduto(codigoProduto);
					dev.setCodigoResponsavel(codigoResponsavel);
					dev.setDataDevolucao(hoje);
					dev.setQtd(Integer.parseInt(txtQTD.getText()));
					devolucao.add(dev);
					produtos.get(jcProdutos.getSelectedIndex() - 1).setQuantidade(Integer.parseInt(txtQTD.getText()));
					listaProdutosAtualizar.add(produtos.get(jcProdutos.getSelectedIndex() - 1));
					txtQTD.setText("");
					lblProduto.setText("qtd produto");
				}
				int gravado = GravarBD.inserirDevolucao(devolucao, listaProdutosAtualizar);
				if (gravado == -1) {
					JOptionPane.showMessageDialog(null, "Falha na conexão com o banco de dados!!");
				} else if (gravado == 0) {
					JOptionPane.showMessageDialog(null, "Falha no registro da devolução!!");
				} else {
					JOptionPane.showMessageDialog(null, "Devolução gravada com sucesso!!");
				}
				TelaDevolucaoProduto.this.dispose();
			}
			
		});

	}
}// fim classe
