package br.edson.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

import br.edson.helper.TrataDados;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class TipoPessoa extends JFrame {

	private ButtonGroup grupoBotoesPessoa;
	private ButtonGroup grupoBotoesJanela;
	private EditarFornecedor editarFornecedor;
	private SelecionarFornecedorEntrada selecionarFornecedor;
	private DeletarFornecedor deletarFornecedor;

	/**
	 * Create the panel.
	 */
	public TipoPessoa(int tipoJanela) {
		setTitle("Selecione o tipo de pessoa");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setBounds(500, 250, 234, 155);
		getContentPane().setLayout(null);

		JRadioButton rdbtnPessoaFisica = new JRadioButton("Pessoa F\u00EDsica");
		rdbtnPessoaFisica.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnPessoaFisica.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		rdbtnPessoaFisica.setBounds(44, 32, 140, 23);
		getContentPane().add(rdbtnPessoaFisica);

		JRadioButton rdbtnPessoaJuridica = new JRadioButton("Pessoa Jur\u00EDdica");
		rdbtnPessoaJuridica.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnPessoaJuridica.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		rdbtnPessoaJuridica.setBounds(44, 58, 158, 23);
		getContentPane().add(rdbtnPessoaJuridica);

		grupoBotoesPessoa = new ButtonGroup();
		grupoBotoesPessoa.add(rdbtnPessoaJuridica);
		grupoBotoesPessoa.add(rdbtnPessoaFisica);

		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnSelecionar.setBounds(44, 88, 123, 23);
		getContentPane().add(btnSelecionar);

		grupoBotoesJanela = new ButtonGroup();
		JLabel lblSelecionePessoa = new JLabel("Selecione Fisica/Juridica");
		lblSelecionePessoa.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecionePessoa.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblSelecionePessoa.setBounds(26, 11, 174, 14);
		getContentPane().add(lblSelecionePessoa);

		btnSelecionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				if (rdbtnPessoaFisica.isSelected() == false && rdbtnPessoaJuridica.isSelected() == false)
					JOptionPane.showMessageDialog(null, "Escoha um tipo de pessoa!!");
				else {
					if (tipoJanela == 1) {
						// se 1 abre selecionar fornecedor, se 2 abre editar fornecedor, se 3 abre
						// deletar fornecedor,
						// se 4 abre tela para gerar pdf dos fornecedores, se 5 abre janela cadastrar
						// fornecedor

						if (rdbtnPessoaFisica.isSelected()) {
							selecionarFornecedor = new SelecionarFornecedorEntrada(true);
						} else {
							selecionarFornecedor = new SelecionarFornecedorEntrada(false);
						}

					} else if (tipoJanela == 2) {
						if (rdbtnPessoaFisica.isSelected()) {
							EditarFornecedor ed = new EditarFornecedor(true);
						} else {
							EditarFornecedor ed = new EditarFornecedor(false);
						}
					} else if (tipoJanela == 3) {
						if (rdbtnPessoaFisica.isSelected()) {
							deletarFornecedor = new DeletarFornecedor(true);
						} else {
							deletarFornecedor = new DeletarFornecedor(false);
						}
					} else if (tipoJanela == 4) {
						if (rdbtnPessoaFisica.isSelected()) {
							TrataDados.gerarListaPdfFornecedor(true);// true gera lista em pdf de pessoa fisica
						} else {
							TrataDados.gerarListaPdfFornecedor(false);// false gera lista em pdf de pessoa juridica
						}
					} else if (tipoJanela == 5) {
						if (rdbtnPessoaFisica.isSelected()) {
							CadastrarFornecedor f = new CadastrarFornecedor(true);// true pessoa fisica
						} else {
							CadastrarFornecedor f = new CadastrarFornecedor(false);// false pessoa juridica
						}
					}
					TipoPessoa.this.dispose();
				}
			}
		});

	}
}
