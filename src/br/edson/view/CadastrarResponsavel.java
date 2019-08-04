package br.edson.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;

import br.edson.controller.GravarBD;
import br.edson.model.ResponsavelRetirada;

import javax.swing.JTextField;
import javax.swing.JButton;

public class CadastrarResponsavel extends JFrame {
	
	private ResponsavelRetirada resp;
	private JTextField txtNome;
	
	/**
	 * Create the panel.
	 */
	public CadastrarResponsavel() {
		setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		setTitle("Cadastrar Respons\u00E1vel Retirada");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(500, 200, 400, 181);
		getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNome.setBounds(49, 41, 46, 14);
		getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setHorizontalAlignment(SwingConstants.CENTER);
		txtNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		txtNome.setBounds(105, 38, 200, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnSalvar.setBounds(270, 102, 89, 23);
		getContentPane().add(btnSalvar);
		
		btnSalvar.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				try {
					resp = new ResponsavelRetirada(txtNome.getText(), 0);
					int cadastrado = GravarBD.inserirResponsavel(resp);
					if( cadastrado == 0 )
						JOptionPane.showMessageDialog(null, "Responsável já existe!!");
					else if( cadastrado > 0)
						JOptionPane.showMessageDialog(null, "Responsável salvo com sucesso!!");
					else
						JOptionPane.showMessageDialog(null, "Falha na conexão com o banco!!");
					txtNome.setText("");
				} catch (Exception e2) {
					// TODO: handle exception
					if(txtNome.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Digite o nome do responsável!!");
					txtNome.setText("");
				}
			}
		});
	}
}
