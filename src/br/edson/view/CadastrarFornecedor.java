package br.edson.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.Key;

import javax.swing.SwingConstants;

import br.edson.controller.GravarBD;
import br.edson.helper.TrataDados;
import br.edson.model.Fornecedor;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class CadastrarFornecedor extends JFrame {
	private JTextField txtNome;
	private JTextField txtFone;
	private JTextField txtEmail;
	private JTextField txt_CNPJ_CPF;
	private JLabel lblCPF_CNPJ;

	private Fornecedor fornecedor;
	private boolean pessoa;
	// int cont =1;

	/**
	 * Create the panel.
	 */
	public CadastrarFornecedor( boolean tipoPessoa) {
		setTitle("Cadastrar Fornecedor");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setBounds(450, 250, 400, 294);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNome.setBounds(75, 49, 46, 14);
		getContentPane().add(lblNome);
		
		JLabel lblFone = new JLabel("Fone");
		lblFone.setHorizontalAlignment(SwingConstants.CENTER);
		lblFone.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblFone.setBounds(75, 98, 46, 14);
		getContentPane().add(lblFone);
		
		if( tipoPessoa)
			lblCPF_CNPJ= new JLabel("CPF");
		else
			lblCPF_CNPJ = new JLabel("CNPJ");
		lblCPF_CNPJ.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCPF_CNPJ.setBounds(85, 182, 67, 14);
		getContentPane().add(lblCPF_CNPJ);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(75, 141, 46, 14);
		getContentPane().add(lblEmail);
		
		txtNome = new JTextField();
		txtNome.setHorizontalAlignment(SwingConstants.CENTER);
		txtNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		txtNome.setBounds(143, 46, 200, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
	
		txtFone = new JTextField("");
		txtFone.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		txtFone.setHorizontalAlignment(SwingConstants.CENTER);
		txtFone.setBounds(143, 95, 200, 20);
		getContentPane().add(txtFone);
		txtFone.setColumns(10);
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
		txtEmail.setBounds(143, 138, 200, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		if( tipoPessoa)
			txt_CNPJ_CPF = new JTextField("");
		else
			txt_CNPJ_CPF = new JTextField("");
		txt_CNPJ_CPF.setHorizontalAlignment(SwingConstants.CENTER);
		txt_CNPJ_CPF.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		txt_CNPJ_CPF.setBounds(143, 179, 200, 20);
		getContentPane().add(txt_CNPJ_CPF);
		txt_CNPJ_CPF.setColumns(10);
		txt_CNPJ_CPF.addKeyListener( new KeyAdapter() {
			int cont = 0;
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if( tipoPessoa) {
					if(e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
						
						if( cont == 3) {
							txt_CNPJ_CPF.setText( txt_CNPJ_CPF.getText() + ".");
							cont++;
						}
						else if( cont == 7) {
							txt_CNPJ_CPF.setText( txt_CNPJ_CPF.getText() + ".");
							cont++;
						}
						if( cont == 11) {
							txt_CNPJ_CPF.setText( txt_CNPJ_CPF.getText() + "-");
							cont++;
						}
						cont++;
					}
					else
						cont--;
					
				}
				else {
					if(e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
						if( cont == 2) {
							txt_CNPJ_CPF.setText( txt_CNPJ_CPF.getText() + ".");
							cont++;
						}
						if( cont == 6) {
							txt_CNPJ_CPF.setText( txt_CNPJ_CPF.getText() + ".");
							cont++;
						}
						if( cont == 10) {
							txt_CNPJ_CPF.setText( txt_CNPJ_CPF.getText() + "/");
							cont++;
						}
						if( cont == 15) {
							txt_CNPJ_CPF.setText( txt_CNPJ_CPF.getText() + "-");				
							cont++;
						}
						cont++;
					}else
						cont--;
				}
			}
		});
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnSalvar.setBounds(266, 220, 89, 23);
		getContentPane().add(btnSalvar);
		
		
		btnSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				super.mousePressed(arg0);
				if( tipoPessoa) { // true pessoa fisica, false pessoa juridica
					
					try {

						fornecedor = new Fornecedor(txtNome.getText(), Long.parseLong(TrataDados.palavraParaNumero(txtFone.getText())), 
								txtEmail.getText(), Long.parseLong( TrataDados.palavraParaNumero( txt_CNPJ_CPF.getText())) );	
						int retorno = GravarBD.inserirFornecedor(fornecedor, tipoPessoa);
						if(retorno == -1) // zero fornecedor existente, valor maior que 0 fornecedor cadastrado, -1 falha no banco
							JOptionPane.showMessageDialog(null, "Falha ao acessar o banco!!");
						else if( retorno == 0)
							JOptionPane.showMessageDialog(null, "Fornecedor existente!!");
						else if( retorno == -2 ) {
							JOptionPane.showMessageDialog(null, "CPF Inválido!!");
						}
						else
							JOptionPane.showMessageDialog(null, "Fornecedor salvo com sucesso!!");
						txtNome.setText("");
						txtFone.setText("");
						txtEmail.setText("");
						txt_CNPJ_CPF.setText("");
					
					} catch (Exception e) {
						// TODO: handle exception
						if (txt_CNPJ_CPF.getText().equals("") || txtNome.getText().equals("") || txtFone.getText().equals("") 
								|| txtEmail.getText().equals("") ) {
							JOptionPane.showMessageDialog(null, "Preencha todos os Campos!!");
							txtNome.setText("");
							txtFone.setText("");
							txtEmail.setText("");
							txt_CNPJ_CPF.setText("");
						}
					}
				}
				else if(tipoPessoa == false ) {
					try {
						
						fornecedor = new Fornecedor(txtNome.getText(), Long.parseLong( TrataDados.palavraParaNumero( txtFone.getText() ) ), 
								txtEmail.getText(), Long.parseLong( TrataDados.palavraParaNumero( txt_CNPJ_CPF.getText())));
						
						int retorno = GravarBD.inserirFornecedor(fornecedor, tipoPessoa);
						if(retorno == -1) // zero fornecedor existente, valor maior que 0 fornecedor cadastrado, -1 falha no banco
							JOptionPane.showMessageDialog(null, "Falha ao acessar o banco!!");
						else if( retorno == 0)
							JOptionPane.showMessageDialog(null, "Fornecedor existente!!");
						else if( retorno == -2) {
							JOptionPane.showMessageDialog(null, "CNPJ inválido!!");
						}
						else
							JOptionPane.showMessageDialog(null, "Fornecedor salvo com sucesso!!");
						txtNome.setText("");
						txtFone.setText("");
						txtEmail.setText("");
						txt_CNPJ_CPF.setText("");
						//cont= 0;
					} catch (Exception e) {
						// TODO: handle exception
						if (txt_CNPJ_CPF.getText().equals("") || txtNome.getText().equals("") || txtFone.getText().equals("") 
								|| txtEmail.getText().equals("") ) {
							JOptionPane.showMessageDialog(null, "Preencha todos os Campos!!");
							txtNome.setText("");
							txtFone.setText("");
							txtEmail.setText("");
							txt_CNPJ_CPF.setText("");
							//cont = 0;
						}
					}
				}

			}
		});
	}
}
