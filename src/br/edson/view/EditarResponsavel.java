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
import br.edson.model.ResponsavelRetirada;

import javax.swing.JTextField;
import javax.swing.JButton;

public class EditarResponsavel extends JFrame {
	private JTextField txtNome;
	private JComboBox<String> jcResponsavel;
	private List<ResponsavelRetirada>  listaResp;

	/**
	 * Create the panel.
	 */
	public EditarResponsavel() {
		setTitle("Editar Responsável");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(500, 250, 400, 181);
		getContentPane().setLayout(null);
				
		jcResponsavel = new JComboBox<String>();
		jcResponsavel.setBounds(0, 0, 395, 20);
		getContentPane().add(jcResponsavel);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNome.setBounds(40, 96, 46, 14);
		getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setHorizontalAlignment(SwingConstants.CENTER);
		txtNome.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		txtNome.setBounds(117, 93, 200, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnGravar.setBounds(295, 124, 89, 23);
		getContentPane().add(btnGravar);
		
		listaResp = new ArrayList<ResponsavelRetirada>();
		listaResp = BuscarListasObjetosBD.buscarResponsaveis(listaResp);
		
		jcResponsavel = PreencheJCombo.preencheJComboBoResponsaveis(jcResponsavel, listaResp);
		
		jcResponsavel.addItemListener( new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED) {
					txtNome.setText(jcResponsavel.getSelectedItem().toString());
				}
			}
		});
		
		btnGravar.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				
				int codigo = jcResponsavel.getSelectedIndex() -1;
				
				ResponsavelRetirada r = new ResponsavelRetirada();
				r = listaResp.get(codigo);
				r.setNome(txtNome.getText());
				int editado = EditarBD.editarResponsavel(r);
				if(editado < 0)
					JOptionPane.showMessageDialog(null, "Falha na conexão com o banco de dados!!");
				else if(editado == 0)
					JOptionPane.showMessageDialog(null, "Falha ao editar o responsável!!");
				else {
					JOptionPane.showMessageDialog(null, "Responsável Editado!!");
					TelaPrincipal.getEditarResp().dispose();
					TelaPrincipal.setEditarResp( new EditarResponsavel());		
				}
				
			}
		});
	}
}// fim classe























