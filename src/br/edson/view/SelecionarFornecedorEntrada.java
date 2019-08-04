package br.edson.view;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import br.edson.controller.EditarBD;
import br.edson.helper.BuscarListasObjetosBD;
import br.edson.helper.PreencheJCombo;
import br.edson.model.Fornecedor;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SelecionarFornecedorEntrada extends JFrame {
	private JComboBox<String> jcFornecedor;
	private List<Fornecedor> fornecedores;
	
	private long idFornecedorEntrada;
	private int numeroNota;
	private String nomeFornecedor;
	private JTextField txtNotaFical;
	private boolean tipoPessoa;
	/**
	 * Create the panel.
	 */
	public SelecionarFornecedorEntrada(boolean tipoPessoa) {
		this.tipoPessoa= tipoPessoa;
		setTitle("Selecionar Fornecedor");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setBounds(500, 250, 400, 200);
		getContentPane().setLayout(null);

		fornecedores = new ArrayList<Fornecedor>();
		jcFornecedor = new JComboBox<String>();
		jcFornecedor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		jcFornecedor.setBounds(0, 0, 395, 20);
		getContentPane().add(jcFornecedor);

		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnSelecionar.setBounds(96, 135, 200, 25);
		getContentPane().add(btnSelecionar);

		fornecedores = BuscarListasObjetosBD.buscarFornecedores(fornecedores, tipoPessoa);
		jcFornecedor = PreencheJCombo.preencheJComboFornecedor(jcFornecedor, fornecedores);
		
		JLabel lblDigiteNumeroNota = new JLabel("Digite Numero Nota Fiscal");
		lblDigiteNumeroNota.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblDigiteNumeroNota.setHorizontalAlignment(SwingConstants.CENTER);
		lblDigiteNumeroNota.setBounds(28, 96, 188, 14);
		getContentPane().add(lblDigiteNumeroNota);
		
		txtNotaFical = new JTextField();
		txtNotaFical.setHorizontalAlignment(SwingConstants.CENTER);
		txtNotaFical.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		txtNotaFical.setBounds(247, 94, 86, 20);
		getContentPane().add(txtNotaFical);
		txtNotaFical.setColumns(10);

		
		jcFornecedor.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED && jcFornecedor.getSelectedIndex() > 0 ) {
					idFornecedorEntrada = fornecedores.get(jcFornecedor.getSelectedIndex()-1).getIdentificacaoPessoa();	
					nomeFornecedor = fornecedores.get(jcFornecedor.getSelectedIndex()-1).getNome();
				}
			}
		});
		
		btnSelecionar.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				super.mousePressed(arg0);
				if(txtNotaFical.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Digite o numero da nota fiscal!!");
				else {
					numeroNota = Integer.parseInt(txtNotaFical.getText());
					TelaPrincipal.setEntrada(new TelaEntradaProduto(idFornecedorEntrada, numeroNota, nomeFornecedor, tipoPessoa)); // true pessoa fisica
					SelecionarFornecedorEntrada.this.dispose();
				}
			}
		});
		
	}
}// fim classe



















