package br.edson.view;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Customizer;
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

public class EditarFornecedor extends JFrame {
	private JTextField txtNome;
	private JTextField txtFone;
	private JTextField txtEmail;
	private JComboBox<String> jcFornecedor;

	private List<Fornecedor> fornecedores;

	/**
	 * Create the panel.
	 */
	public EditarFornecedor(boolean tipoPessoa) {
		
		setTitle("Editar Fornecedor");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setBounds(500, 250, 400, 350);
		getContentPane().setLayout(null);

		fornecedores = new ArrayList<Fornecedor>();
		jcFornecedor = new JComboBox<String>();
		jcFornecedor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		jcFornecedor.setBounds(0, 0, 395, 20);
		getContentPane().add(jcFornecedor);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setBounds(83, 196, 46, 14);
		getContentPane().add(lblNome);

		JLabel lblFone = new JLabel("Fone");
		lblFone.setHorizontalAlignment(SwingConstants.CENTER);
		lblFone.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblFone.setBounds(83, 227, 46, 14);
		getContentPane().add(lblFone);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(83, 258, 46, 14);
		getContentPane().add(lblEmail);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.setHorizontalAlignment(SwingConstants.LEADING);
		btnGravar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnGravar.setBounds(283, 287, 89, 23);
		getContentPane().add(btnGravar);

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		txtNome.setHorizontalAlignment(SwingConstants.CENTER);
		txtNome.setBounds(154, 194, 200, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		txtFone = new JTextField();
		txtFone.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		txtFone.setHorizontalAlignment(SwingConstants.CENTER);
		txtFone.setBounds(154, 225, 200, 20);
		getContentPane().add(txtFone);
		txtFone.setColumns(5);
		
		txtFone.addKeyListener( new KeyAdapter() {
			int cont =1;
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyTyped(e);
				if( e.getKeyCode() != KeyEvent.VK_BACK_SPACE ) {
					cont++;
					if( cont == 2) {
						txtFone.setText("("+txtFone.getText());
					}
					else if( cont == 4) {
						txtFone.setText( txtFone.getText() + ")");
						cont++;
					}
					else if( cont == 9) {
						txtFone.setText( txtFone.getText() + "-");
						cont++;
					}
				}else
					cont--;
			}
		});
		
		txtEmail = new JTextField();
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		txtEmail.setBounds(154, 256, 200, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

		fornecedores = BuscarListasObjetosBD.buscarFornecedores(fornecedores, tipoPessoa);
		jcFornecedor = PreencheJCombo.preencheJComboFornecedor(jcFornecedor, fornecedores);

		jcFornecedor.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED && jcFornecedor.getSelectedIndex() > 0 ) {
					txtNome.setText(fornecedores.get(jcFornecedor.getSelectedIndex() - 1).getNome());
					txtFone.setText(Long.toString(fornecedores.get(jcFornecedor.getSelectedIndex() - 1).getFone()));
					txtEmail.setText(fornecedores.get(jcFornecedor.getSelectedIndex() - 1).getEmail());
				}
			}
		});
		
		btnGravar.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				super.mousePressed(arg0);
				Fornecedor ff = new Fornecedor();
				ff.setNome(txtNome.getText());
				ff.setFone(Long.parseLong(txtFone.getText()));
				ff.setEmail(txtEmail.getText());
				ff.setIdentificacaoPessoa(fornecedores.get(jcFornecedor.getSelectedIndex()  -1).getIdentificacaoPessoa());
				int editado =EditarBD.editarFornecedor(ff);
				if(editado == -1) {
					JOptionPane.showMessageDialog(null, "Falha ao acessar o banco de dados!!");
				}
				else if( editado == 0)
					JOptionPane.showMessageDialog(null, "Falha ao editar!!");
				else {
					JOptionPane.showMessageDialog(null, "Fornecedor editado com sucesso");
					EditarFornecedor.this.dispose();
					EditarFornecedor f = new EditarFornecedor(tipoPessoa);
					//TelaPrincipal.setTelaEditarFornecedor(new EditarFornecedor(tipoPessoa));
				}
		
			}
		});
	}
}// fim classe



















