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
import br.edson.model.Produto;

import javax.swing.JButton;
import java.awt.Color;

public class DeletarProduto extends JFrame {
	private JComboBox<String> jcProduto;
	private int codigoProduto;
	private List<Produto> listaProdutos;
	/**
	 * Create the panel.
	 */
	public DeletarProduto() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(500, 250, 400, 200);
		setTitle("DELETAR PRODUTO");
		getContentPane().setLayout(null);
		
		jcProduto =new JComboBox<String>();
		jcProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		jcProduto.setBounds(0, 0, 395, 20);
		getContentPane().add(jcProduto);
		
		listaProdutos = new ArrayList<Produto>();
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.setBackground(Color.RED);
		btnDeletar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnDeletar.setBounds(136, 100, 150, 25);
		getContentPane().add(btnDeletar);
		
		listaProdutos = BuscarListasObjetosBD.buscaProdutosBD(listaProdutos);
		
		jcProduto = PreencheJCombo.preencheJCombo(jcProduto, listaProdutos);
		
		jcProduto.addItemListener( new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED) {
					codigoProduto = listaProdutos.get(jcProduto.getSelectedIndex() - 1).getCodigo();
				}
			}
		});
		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				int deletado = DeletarBD.deletarProduto(codigoProduto);
				if(deletado == -1)
					JOptionPane.showMessageDialog(null, "Falha na conexão com o banco de dados!!");
				else if( deletado == 0)
					JOptionPane.showMessageDialog(null, "Falha ao deletar o produto!!");
				else {
					JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!!");
					DeletarProduto.this.dispose();
					DeletarProduto del = new DeletarProduto();
				}
			}
		});

	}
}
