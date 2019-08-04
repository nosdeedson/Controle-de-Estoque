package br.edson.view;

import javax.swing.JFrame;

import br.edson.controller.GravarBD;
import br.edson.helper.BuscarListasObjetosBD;
import br.edson.helper.PreencheJCombo;
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

public class TelaEntradaProduto extends JFrame {
	private JComboBox<String> jcProdutos;
	private JTextField txtQTD;

	private long codigoFornecedorEntrada;
	private int numeroNota;
	private List<Produto> listaProdutosJCombo;
	private List<Produto> listaEntradaProd;
	private final List<EntradaProduto> listaEntrada;
	private boolean tipoPessoa; // true pessoa fisica, false pessoa juridica
	private int codigoProduto;
	private int qtdProduto;

	/**
	 * Create the panel.
	 */
	public TelaEntradaProduto(long fornecedor, int numeroNota, String nomeFornecedor, boolean tipoPessoa) {

		this.codigoFornecedorEntrada = fornecedor;
		this.numeroNota = numeroNota;
		this.tipoPessoa = tipoPessoa;
		setTitle("Entrada Produto");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(500, 250, 600, 235);
		getContentPane().setLayout(null);

		JLabel lblFornecedor = new JLabel("Fornecedor: " + nomeFornecedor);
		lblFornecedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblFornecedor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblFornecedor.setBounds(10, 78, 574, 14);
		getContentPane().add(lblFornecedor);

		jcProdutos = new JComboBox<String>();
		jcProdutos.setMaximumRowCount(100);
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

		listaProdutosJCombo = new ArrayList<Produto>();
		listaProdutosJCombo = BuscarListasObjetosBD.buscaProdutosBD(listaProdutosJCombo); // lista de materiais que
																							// preenche
																							// o jcombo
		jcProdutos = PreencheJCombo.preencheJCombo(jcProdutos, listaProdutosJCombo);

		JLabel lblDigiteAQuantidade = new JLabel("Digite a quantidade de todos os produtos antes de gravar");
		lblDigiteAQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblDigiteAQuantidade.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblDigiteAQuantidade.setBounds(10, 31, 583, 14);
		getContentPane().add(lblDigiteAQuantidade);

		Calendar c = Calendar.getInstance();
		String hoje = new SimpleDateFormat("dd/MM/YYYY").format(c.getTime());

		JLabel lblDataEntrada = new JLabel("Data Entrada: " + hoje);
		lblDataEntrada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblDataEntrada.setBounds(24, 129, 190, 14);
		getContentPane().add(lblDataEntrada);

		listaEntradaProd = new ArrayList<Produto>(); // lista de materiais que fazem parte da entrada
		listaEntrada = new ArrayList<EntradaProduto>();// entrada a ser gravando no banco

		jcProdutos.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					codigoProduto = listaProdutosJCombo.get(jcProdutos.getSelectedIndex() - 1).getCodigo();
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
					JOptionPane.showMessageDialog(null, "Digite a quantidade.");
				else if (jcProdutos.getSelectedItem().toString().equals("Produtos")) {
					JOptionPane.showMessageDialog(null, "Escolha um produto.");
				} else {
					Calendar c = Calendar.getInstance();
					EntradaProduto ent = new EntradaProduto();
					ent.setDataEntrada(new SimpleDateFormat("YYYY/MM/dd").format(c.getTime()));
					ent.setCodigoProduto(codigoProduto);
					ent.setNumeroNF(numeroNota);
					ent.setIdentificacaopessoa(codigoFornecedorEntrada);
					ent.setTipoForne(tipoPessoa);
					ent.setQtdEntrada(Integer.parseInt(txtQTD.getText()));
					listaEntrada.add(ent);
					listaProdutosJCombo.get(jcProdutos.getSelectedIndex() - 1)
							.setQuantidade(Integer.parseInt(txtQTD.getText()));// atualiza a quantidade dos produtos a
																				// serem atualizados no banco.
																				// true incrementa quantidade, false decrementa
					listaEntradaProd.add(listaProdutosJCombo.get(jcProdutos.getSelectedIndex() - 1));
					txtQTD.setText("");
				}
			}
		});

		btnGravar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				if (!txtQTD.getText().equals("")) {
					Calendar c = Calendar.getInstance();
					EntradaProduto ent = new EntradaProduto();
					ent.setDataEntrada(new SimpleDateFormat("YYYY/MM/dd").format(c.getTime()));
					ent.setCodigoProduto(codigoProduto);
					ent.setNumeroNF(numeroNota);
					ent.setIdentificacaopessoa(codigoFornecedorEntrada);
					ent.setTipoForne(tipoPessoa);
					ent.setQtdEntrada(Integer.parseInt(txtQTD.getText()));
					listaEntrada.add(ent);
					listaProdutosJCombo.get(jcProdutos.getSelectedIndex() - 1)
							.setQuantidade(Integer.parseInt(txtQTD.getText()));
					listaEntradaProd.add(listaProdutosJCombo.get(jcProdutos.getSelectedIndex() - 1));
					txtQTD.setText("");
					JOptionPane.showConfirmDialog(null, "Aperte o botão Gravar!!");
				}
				int gravado = GravarBD.inserirEntrada(listaEntrada, listaEntradaProd);
				if (gravado == -1) {
					JOptionPane.showMessageDialog(null, "Falha na conexão com o banco de dados!!");
				} else if (gravado == 0) {
					JOptionPane.showMessageDialog(null, "Falha n registro da ENTRADA!!");
				} else {
					JOptionPane.showMessageDialog(null, "Entrada gravada com sucesso!!");
				}
				TelaEntradaProduto.this.dispose();
				// falta fazer o metodo para gravar no banco, lembre-se de atualizar a
				// quantidade do material
			}
		});

	}
}// fim classe
