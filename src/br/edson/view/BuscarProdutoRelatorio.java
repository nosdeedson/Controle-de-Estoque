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

import br.edson.helper.BuscarListasObjetosBD;
import br.edson.helper.PreencheJCombo;
import br.edson.model.Fornecedor;
import br.edson.model.Produto;

import javax.swing.JButton;

public class BuscarProdutoRelatorio extends JFrame {
	private JComboBox<String> JCProduto;
	private List<Produto> listaProdutos;
	private int codigo;
	/**
	 * Create the panel.
	 */
	public BuscarProdutoRelatorio( boolean retiradaEntrada) { // true janela Entrada, false janela retirada
		setTitle("Buscar Fornecedor");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setBounds(500, 250, 400, 146);
		getContentPane().setLayout(null);
		
		JCProduto = new JComboBox<String>();
		JCProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		JCProduto.setBounds(0, 0, 395, 20);
		getContentPane().add(JCProduto);
		
		listaProdutos = new ArrayList<Produto>();
		listaProdutos = BuscarListasObjetosBD.buscaProdutosBD(listaProdutos);
		
		JCProduto = PreencheJCombo.preencheJCombo(JCProduto, listaProdutos);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnSelecionar.setBounds(125, 83, 89, 23);
		getContentPane().add(btnSelecionar);
		JCProduto.addItemListener( new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED)
					codigo = listaProdutos.get(JCProduto.getSelectedIndex()-1).getCodigo();
			}
		});
		btnSelecionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				super.mousePressed(arg0);
				if( retiradaEntrada) {
					TelaGerarRelatorioEntrada.setCodigoProduto(codigo);
					BuscarProdutoRelatorio.this.dispose();
				}
				else {
					TelaGerarRelatorioRetirada.setCodigoProduto(codigo);
					BuscarProdutoRelatorio.this.dispose();
				}
			}
		});
		
	}

}
