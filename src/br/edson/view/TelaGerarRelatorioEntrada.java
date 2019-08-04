package br.edson.view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.edson.helper.TrataDados;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JRadioButton;

public class TelaGerarRelatorioEntrada extends JFrame {
	private JTextField txtDataInicio;
	private JTextField txtDataFinal;
	private ButtonGroup grupoPessoa;
	
	private static long identificacaoPessoa = 0;
	private static int codigoProduto =0;
	
	private String dataInicio = "", dataFinal= "";

	/**
	 * Create the panel.
	 */
	public TelaGerarRelatorioEntrada() {
		setTitle("Relatório Entrada");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setBounds(500, 250, 400, 213);
		getContentPane().setLayout(null);
		
		JButton btnBuscarFornecedor = new JButton("Buscar Fornecedor");
		btnBuscarFornecedor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnBuscarFornecedor.setBounds(214, 11, 152, 23);
		getContentPane().add(btnBuscarFornecedor);
		
		JButton btnBuscarProdutos = new JButton("Buscar Produtos");
		btnBuscarProdutos.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnBuscarProdutos.setBounds(10, 11, 152, 23);
		getContentPane().add(btnBuscarProdutos);
		
		JLabel lblDataIncio = new JLabel("Data In\u00EDcio");
		lblDataIncio.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblDataIncio.setBounds(46, 56, 78, 14);
		getContentPane().add(lblDataIncio);
		
		JLabel lblDataFinal = new JLabel("Data Final");
		lblDataFinal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblDataFinal.setBounds(46, 81, 78, 14);
		getContentPane().add(lblDataFinal);
		
		txtDataInicio = new JTextField();
		txtDataInicio.setHorizontalAlignment(SwingConstants.CENTER);
		txtDataInicio.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtDataInicio.setBounds(134, 54, 86, 20);
		getContentPane().add(txtDataInicio);
		txtDataInicio.setColumns(10);
		txtDataInicio.addKeyListener(new KeyAdapter() {
			int cont = 0;
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if( cont == 2)
					txtDataInicio.setText(txtDataInicio.getText()+"/");
				/// trabalhando corrigir quando usar o backspace e na tela relatorio retirada
				if( cont == 4)
					txtDataInicio.setText(txtDataInicio.getText()+"/");
				cont++;
			}
		});
	
		
		txtDataFinal = new JTextField();
		txtDataFinal.setHorizontalAlignment(SwingConstants.CENTER);
		txtDataFinal.setFont(new Font("Times New Roman", Font.BOLD, 13));
		txtDataFinal.setBounds(134, 79, 86, 20);
		getContentPane().add(txtDataFinal);
		txtDataFinal.setColumns(10);
		txtDataFinal.addKeyListener(new KeyAdapter() {
			int cont = 0;
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if( cont == 2)
					txtDataFinal.setText(txtDataFinal.getText()+"/");
				/// trabalhando corrigir quando usar o backspace
				if( cont == 4)
					txtDataFinal.setText(txtDataFinal.getText()+"/");
				cont++;
			}
		});

		
		JLabel lblAperteGerarRelatrio = new JLabel("Use os campos acima para refinar sua pesquisa.");
		lblAperteGerarRelatrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblAperteGerarRelatrio.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblAperteGerarRelatrio.setBounds(0, 106, 394, 23);
		getContentPane().add(lblAperteGerarRelatrio);
		
		JButton btnGerarRelatrio = new JButton("");
		btnGerarRelatrio.setBackground(Color.CYAN);
		btnGerarRelatrio.setIcon(new ImageIcon(TelaGerarRelatorioEntrada.class.getResource("/br/edson/imagens/icon-pdf.png")));
		btnGerarRelatrio.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnGerarRelatrio.setBounds(336, 140, 30, 35);
		getContentPane().add(btnGerarRelatrio);
		
		JRadioButton rdbtnPessoaFsica = new JRadioButton("Pessoa F\u00EDsica");
		rdbtnPessoaFsica.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnPessoaFsica.setBounds(239, 78, 139, 23);
		getContentPane().add(rdbtnPessoaFsica);
		
		JRadioButton rdbtnPessoaJuridica = new JRadioButton("Pessoa Juridica");
		rdbtnPessoaJuridica.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnPessoaJuridica.setBounds(239, 53, 149, 23);
		getContentPane().add(rdbtnPessoaJuridica);
		grupoPessoa = new ButtonGroup();
		grupoPessoa.add(rdbtnPessoaFsica);
		grupoPessoa.add(rdbtnPessoaJuridica);
		

		btnBuscarFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				if( rdbtnPessoaFsica.isSelected() == false && rdbtnPessoaJuridica.isSelected() == false)
					JOptionPane.showMessageDialog(null, "Selecione o tipo de pessoa");
				else if(rdbtnPessoaFsica.isSelected() == true) {
					BuscarFornecedorRelatorio f = new BuscarFornecedorRelatorio(true);
				}
				else {
					BuscarFornecedorRelatorio f = new BuscarFornecedorRelatorio(false);
				}
			}
		});
		btnBuscarProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				BuscarProdutoRelatorio prod = new BuscarProdutoRelatorio(true);
			}
		});
		btnGerarRelatrio.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				dataInicio = TrataDados.inverteDataParaBD(txtDataInicio.getText().toString());
				dataFinal = TrataDados.inverteDataParaBD(txtDataFinal.getText());
				TrataDados.preparaRelatorio(identificacaoPessoa, codigoProduto, dataInicio, dataFinal, true);
				TelaPrincipal.getRelatorioEntrada().dispose();
			}
		});
	}

	public static long getIdentificacaoPessoa() {
		return identificacaoPessoa;
	}

	public static void setIdentificacaoPessoa(long identificacaoPessoa) {
		TelaGerarRelatorioEntrada.identificacaoPessoa = identificacaoPessoa;
	}

	public static int getCodigoProduto() {
		return codigoProduto;
	}

	public static void setCodigoProduto(int codigoProduto) {
		TelaGerarRelatorioEntrada.codigoProduto = codigoProduto;
	}
}
