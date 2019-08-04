package br.edson.view;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;

import br.edson.controller.EditarBD;
import br.edson.helper.BuscarListasObjetosBD;
import br.edson.helper.PreencheJCombo;
import br.edson.model.Produto;

import javax.swing.JTextField;
import javax.swing.JButton;

public class EditarProduto extends JFrame {
	private JTextField txtNome;
	private JTextField txtQuantidade;
	private JComboBox<String> jCombolistaProdutos;
	private List<Produto> listaProdutosBD;
	private int codigoEditar;
	/**
	 * Create the panel.
	 */
	public EditarProduto() {
		
		setTitle("Editar Produto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(500, 250, 400, 400);
		getContentPane().setLayout(null);
		setVisible(true);
		
		listaProdutosBD = new ArrayList<Produto>();
		listaProdutosBD = BuscarListasObjetosBD.buscaProdutosBD(listaProdutosBD);
		
		jCombolistaProdutos = new JComboBox<String>();
		jCombolistaProdutos.setBounds(0, 0, 395, 20);
		getContentPane().add(jCombolistaProdutos);
		jCombolistaProdutos = PreencheJCombo.preencheJCombo(jCombolistaProdutos, listaProdutosBD);
	
		JLabel lblEditeEsteMaterial = new JLabel("Edite este material");
		lblEditeEsteMaterial.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditeEsteMaterial.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblEditeEsteMaterial.setBounds(161, 221, 180, 14);
		getContentPane().add(lblEditeEsteMaterial);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNome.setBounds(44, 248, 46, 14);
		getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setHorizontalAlignment(SwingConstants.CENTER);
		txtNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		txtNome.setBounds(160, 246, 200, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("quantidade");
		lblQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantidade.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblQuantidade.setBounds(23, 295, 103, 14);
		getContentPane().add(lblQuantidade);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuantidade.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		txtQuantidade.setBounds(160, 292, 200, 20);
		getContentPane().add(txtQuantidade);
		txtQuantidade.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnSalvar.setBounds(271, 337, 89, 23);
		getContentPane().add(btnSalvar);
		
		// metodos
		jCombolistaProdutos.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED	) {
					txtNome.setText(listaProdutosBD.get(jCombolistaProdutos.getSelectedIndex() - 1).getNome());
					txtQuantidade.setText( Integer.toString(listaProdutosBD.get(jCombolistaProdutos.getSelectedIndex()-1).getQuantidade()) );
					codigoEditar = jCombolistaProdutos.getSelectedIndex() - 1;
				}
			}
		});
		
		btnSalvar.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				super.mousePressed(arg0);
				Produto mat = new Produto();
				mat= listaProdutosBD.get(codigoEditar);
				try {
					mat.setNome(txtNome.getText());
					mat.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
					if(EditarBD.EditarProduto(mat) != 0) {
						JOptionPane.showMessageDialog(null, "Item editado!!!");						
						TelaPrincipal.setEditaProduto(new EditarProduto());
						TelaPrincipal.getEditaProduto().dispose();
					}
					else
						JOptionPane.showMessageDialog(null, "Falha ao editar o item");
				} catch (Exception e) {
					// TODO: handle exception
					if(txtNome.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Digite o nome do material!!");
					if(txtQuantidade.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Digite a quantidade!!");
				}
				
			}
		});
	}
}
