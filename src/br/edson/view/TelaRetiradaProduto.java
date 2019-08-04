package br.edson.view;

import javax.swing.JFrame;

import br.edson.controller.GravarBD;
import br.edson.helper.BuscarListasObjetosBD;
import br.edson.helper.GerarRelatorioProdutoPDF;
import br.edson.helper.PreencheJCombo;
import br.edson.model.EntradaProduto;
import br.edson.model.Produto;
import br.edson.model.Retirada;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.UIManager;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.FontUIResource;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;

public class TelaRetiradaProduto extends JFrame {
	private JComboBox<String> jcProdutos;
	private JTextField txtQTD;
	private List<Produto> produtos;// produtos no BD
	private List<Retirada> retiradas; // retiradas realizadas
	private List<Produto> listaProdutosAtualizar; // produtos a terem a quantidade atualiza
	private List<Produto> gerarPdfRetirada;
	private int codigoProduto;
	private int qtdProduto;

	/**
	 * Create the panel.
	 */
	public TelaRetiradaProduto(int codigoResponsavel, String nomeResponsavel) {

		setTitle("Retirada Produto");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(500, 250, 600, 239);
		getContentPane().setLayout(null);

		jcProdutos = new JComboBox<String>();
		jcProdutos.setMaximumRowCount(30);
		jcProdutos.setBounds(0, 0, 593, 20);
		getContentPane().add(jcProdutos);

		JLabel lblProduto = new JLabel("QTD Produto");
		lblProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblProduto.setBounds(227, 129, 250, 14);
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
		btnGravar.setBounds(237, 154, 150, 25);
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
		lblDataEntrada.setBounds(23, 129, 190, 14);
		getContentPane().add(lblDataEntrada);

		JLabel lblResponsavelRetirada = new JLabel("Respons\u00E1vel Retirada: " + nomeResponsavel);
		lblResponsavelRetirada.setHorizontalAlignment(SwingConstants.CENTER);
		lblResponsavelRetirada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblResponsavelRetirada.setBounds(0, 62, 574, 20);
		getContentPane().add(lblResponsavelRetirada);

		produtos = new ArrayList<Produto>();// produtos no BD
		produtos = BuscarListasObjetosBD.buscaProdutosBD(produtos);

		retiradas = new ArrayList<Retirada>(); // retiradas realizadas
		listaProdutosAtualizar = new ArrayList<Produto>(); // produtos a terem a quantidade atualiza
		gerarPdfRetirada = new ArrayList<Produto>();
		jcProdutos = PreencheJCombo.preencheJCombo(jcProdutos, produtos);

		JButton btnGerarPdf = new JButton("");
		btnGerarPdf.setBackground(Color.CYAN);
		btnGerarPdf.setIcon(new ImageIcon(TelaRetiradaProduto.class.getResource("/br/edson/imagens/icon-pdf.png")));
		btnGerarPdf.setBounds(85, 155, 35, 33);
		getContentPane().add(btnGerarPdf);

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
				int quant = Integer.parseInt(txtQTD.getText());
				if (quant <= produtos.get(jcProdutos.getSelectedIndex() - 1).getQuantidade()) {
					if (txtQTD.getText().equals("")) {
						UIManager.put("OptionPane.messageFont",
								new FontUIResource(new Font("Times New Roman", Font.BOLD, 18))); 
						JOptionPane.showMessageDialog(null, "Digite a quantidade!!\n ", " Nova fonte.",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (jcProdutos.getSelectedItem().equals("Produtos"))
						JOptionPane.showMessageDialog(null, "Escolha um produto!!");
					else {
						Retirada ret = new Retirada();
						ret.setCodigoProduto(codigoProduto);
						ret.setCodigoResponsavel(codigoResponsavel);
						ret.setDataRetirada(hoje);
						ret.setQtd(quant);
						retiradas.add(ret);
						produtos.get(jcProdutos.getSelectedIndex() - 1)
								.setQuantidade(Integer.parseInt(txtQTD.getText()));
						listaProdutosAtualizar.add(produtos.get(jcProdutos.getSelectedIndex() - 1));
						txtQTD.setText("");
						lblProduto.setText("qtd produto");
					}
				} else {
					UIManager.put("OptionPane.messageFont",
							new FontUIResource(new Font("Times New Roman", Font.BOLD, 18))); 
					JOptionPane.showMessageDialog(null, "Quantidade a retirar é maior que o estoque !!\n "
							+ "Digite novamente. Estoque: " +produtos.get(jcProdutos.getSelectedIndex() - 1).getQuantidade(),
							" Nova fonte.",JOptionPane.INFORMATION_MESSAGE);
					txtQTD.setText("");
				}
			}
		});

		btnGravar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				int quant = Integer.parseInt(txtQTD.getText());
				if (quant <= produtos.get(jcProdutos.getSelectedIndex() - 1).getQuantidade()) {
					if (!txtQTD.getText().equals("")) {
						Retirada ret = new Retirada();
						ret.setCodigoProduto(codigoProduto);
						ret.setCodigoResponsavel(codigoResponsavel);
						ret.setDataRetirada(hoje);
						ret.setQtd(quant);
						retiradas.add(ret);
						produtos.get(jcProdutos.getSelectedIndex() - 1)
								.setQuantidade(Integer.parseInt(txtQTD.getText()));
						listaProdutosAtualizar.add(produtos.get(jcProdutos.getSelectedIndex() - 1));
						txtQTD.setText("");
						lblProduto.setText("qtd produto");
					}
					int gravado = GravarBD.inserirRetirada(retiradas, listaProdutosAtualizar);
					if (gravado == -1) {
						JOptionPane.showMessageDialog(null, "Falha na conexão com o banco de dados!!");
					} else if (gravado == 0) {
						JOptionPane.showMessageDialog(null, "Falha n registro da Retirada!!");
					} else {
						JOptionPane.showMessageDialog(null, "Retirada gravada com sucesso!!");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Quantidade a reitrar maior que o estoque!!\n" + "Digite novamennte."
									+ " Estoque: " + produtos.get(jcProdutos.getSelectedIndex() - 1).getQuantidade());
					txtQTD.setText("");
				}
			}
		});
		btnGerarPdf.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				super.mousePressed(arg0);
				GerarRelatorioProdutoPDF.gerarRelatorioProdutos(listaProdutosAtualizar, nomeResponsavel);
				// mudar o metodo set de produto para receber um valor, mudar a dao para
				// decrementar ou incrementar a quantidade
			}
		});

	}
}// fim classe
