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
import br.edson.model.Fornecedor;

import javax.swing.JButton;
import java.awt.Color;

public class DeletarFornecedor extends JFrame {
	private JComboBox<String> jcFornecedor;
	private long codigoFornecedor;
	private List<Fornecedor> fornecedores;
	/**
	 * Create the panel.
	 */
	public DeletarFornecedor(boolean tipoPessoa) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(500, 250, 400, 200);
		setTitle("DELETAR FORNECEDOR");
		getContentPane().setLayout(null);
		
		jcFornecedor =new JComboBox<String>();
		jcFornecedor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		jcFornecedor.setBounds(0, 0, 395, 20);
		getContentPane().add(jcFornecedor);
		
		fornecedores = new ArrayList<Fornecedor>();
		fornecedores = BuscarListasObjetosBD.buscarFornecedores(fornecedores, tipoPessoa);
		
		jcFornecedor = PreencheJCombo.preencheJComboFornecedor(jcFornecedor, fornecedores);
		
		jcFornecedor.addItemListener( new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED) {
					codigoFornecedor = fornecedores.get(jcFornecedor.getSelectedIndex() -1 ).getIdentificacaoPessoa();
				}
			}
		});
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBackground(Color.RED);
		btnDeletar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnDeletar.setBounds(118, 135, 150, 25);
		getContentPane().add(btnDeletar);
		
		btnDeletar.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				int deletado = DeletarBD.deletarFornecedor(codigoFornecedor);
				if( deletado == -1)
					JOptionPane.showMessageDialog(null, "Falha na conexão com o banco.");
				else if( deletado == 0)
					JOptionPane.showMessageDialog(null, "Falha ao deletar o fornecedor!!");
				else {
					JOptionPane.showMessageDialog(null, "Fornecedor deletado!!");
					DeletarFornecedor.this.dispose();
					TipoPessoa tipo = new TipoPessoa(3);
				}
			}
		});
	}
}
