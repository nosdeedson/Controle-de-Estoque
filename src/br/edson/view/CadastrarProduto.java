package br.edson.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.edson.controller.GravarBD;
import br.edson.dao.ConectarBD;
import br.edson.dao.ProdutoDao;
import br.edson.model.Produto;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CadastrarProduto extends JFrame {
	private JTextField txtNomeProduto;

	/**
	 * Create the panel.
	 */
	public CadastrarProduto() {
		setTitle("Tela Cadastrar");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(450, 300, 400, 150);
		getContentPane().setLayout(null);
		setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 394, 319);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNomeProduto = new JLabel("Nome Produto");
		lblNomeProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNomeProduto.setBounds(60, 30, 110, 20);
		panel.add(lblNomeProduto);
		
		txtNomeProduto = new JTextField();
		txtNomeProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		txtNomeProduto.setHorizontalAlignment(SwingConstants.CENTER);
		txtNomeProduto.setBounds(195, 30, 150, 20);
		panel.add(txtNomeProduto);
		txtNomeProduto.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnSalvar.setBounds(256, 85, 89, 23);
		panel.add(btnSalvar);
		
		btnSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				super.mousePressed(arg0);
				
				try {
					Produto mat =new Produto(txtNomeProduto.getText(), 0,0);
					int i = GravarBD.inserirProdutoBD(mat);
					if( i != 0)
						JOptionPane.showMessageDialog(null, "gravado com sucesso");
					else
						JOptionPane.showMessageDialog(null, "produto Existente");
					txtNomeProduto.setText("");
				} catch (Exception e) {
					// TODO: handle exception
					if(txtNomeProduto.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Digite um nome para o material");
						txtNomeProduto.setText("");
					}

				}
			}
		});
	}
}
