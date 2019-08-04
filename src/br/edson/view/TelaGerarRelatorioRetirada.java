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

public class TelaGerarRelatorioRetirada extends JFrame {
	private JTextField txtDataInicio;
	private JTextField txtDataFinal;

	
	private static int responsavel = 0;
	private static int codigoProduto =0;
	
	private String dataInicio = "", dataFinal= "";

	/**
	 * Create the panel.
	 */
	public TelaGerarRelatorioRetirada() {
		setTitle("Relatório Retirada");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setBounds(500, 250, 400, 213);
		getContentPane().setLayout(null);
		
		JButton btnBuscarResponsavel = new JButton("Buscar Respons\u00E1vel");
		btnBuscarResponsavel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnBuscarResponsavel.setBounds(192, 11, 192, 23);
		getContentPane().add(btnBuscarResponsavel);
		
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
				/// trabalhando corrigir quando usar o backspace
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
		btnGerarRelatrio.setIcon(new ImageIcon(TelaGerarRelatorioRetirada.class.getResource("/br/edson/imagens/icon-pdf.png")));
		btnGerarRelatrio.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnGerarRelatrio.setBounds(336, 140, 30, 35);
		getContentPane().add(btnGerarRelatrio);
			

		btnBuscarResponsavel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				BuscarResponsavel resp= new BuscarResponsavel();
			}
		});
		btnBuscarProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				BuscarProdutoRelatorio prod = new BuscarProdutoRelatorio(false);
			}
		});
		btnGerarRelatrio.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				dataInicio = TrataDados.inverteDataParaBD(txtDataInicio.getText().toString());
				dataFinal = TrataDados.inverteDataParaBD(txtDataFinal.getText());
				TrataDados.preparaRelatorio(responsavel, codigoProduto, dataInicio, dataFinal, false);
				TelaGerarRelatorioRetirada.this.dispose();
			}
		});
	}

	public static int getResponsavel() {
		return responsavel;
	}

	public static void setResponsavel(int responsavel) {
		TelaGerarRelatorioRetirada.responsavel = responsavel;
	}

	public static int getCodigoProduto() {
		return codigoProduto;
	}

	public static void setCodigoProduto(int codigoProduto) {
		TelaGerarRelatorioRetirada.codigoProduto = codigoProduto;
	}
}
