package br.edson.view;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.edson.controller.DeletarBD;
import br.edson.helper.BuscarListasObjetosBD;
import br.edson.helper.PreencheJCombo;
import br.edson.model.ResponsavelRetirada;

import javax.swing.JButton;
import java.awt.Color;

public class DeletarResponsavel extends JFrame {
	private JComboBox<String> jcResponsavel;
	private int codigoResponsavel;
	private List< ResponsavelRetirada> responsaveis;
	/**
	 * Create the panel.
	 */
	public DeletarResponsavel() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(500, 250, 400, 200);
		setTitle("DELETAR RESPONSAVEL");
		getContentPane().setLayout(null);
		
		jcResponsavel =new JComboBox<String>();
		jcResponsavel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		jcResponsavel.setBounds(0, 0, 395, 20);
		getContentPane().add(jcResponsavel);
		
		responsaveis = new ArrayList<ResponsavelRetirada>();
		responsaveis = BuscarListasObjetosBD.buscarResponsaveis(responsaveis);
		
		jcResponsavel = PreencheJCombo.preencheJComboBoResponsaveis(jcResponsavel, responsaveis);
		
		jcResponsavel.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED)
					codigoResponsavel = responsaveis.get(jcResponsavel.getSelectedIndex() -1 ).getCodigo();
			}
		});
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBackground(Color.RED);
		btnDeletar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnDeletar.setBounds(138, 137, 150, 23);
		getContentPane().add(btnDeletar);
		
		btnDeletar.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				int deletado = 0;
				deletado = DeletarBD.deletarResponsavel(codigoResponsavel);
				if(deletado == -1)
					JOptionPane.showMessageDialog(null, "Falha na conexão com o banco!!");
				else if( deletado == 0)
					JOptionPane.showMessageDialog(null, "Falha ao deletar o responsavel!!");
				else {
					JOptionPane.showMessageDialog(null, "Responsavel deletado!!");
					DeletarResponsavel.this.dispose();
					DeletarResponsavel del = new DeletarResponsavel();
				}
					
			}
		});
		
		
	}
}
