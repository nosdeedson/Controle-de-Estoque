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
import javax.swing.JButton;

public class BuscarFornecedorRelatorio extends JFrame {
	private JComboBox<String> JCFornecedor;
	private List<Fornecedor> listaFornecedores;
	private long codigo;
	/**
	 * Create the panel.
	 */
	public BuscarFornecedorRelatorio( boolean tipoPessoa) {
		setTitle("Buscar Fornecedor");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setBounds(500, 250, 400, 146);
		getContentPane().setLayout(null);
		
		JCFornecedor = new JComboBox<String>();
		JCFornecedor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		JCFornecedor.setBounds(0, 0, 395, 20);
		getContentPane().add(JCFornecedor);
		
		listaFornecedores = new ArrayList<Fornecedor>();
		listaFornecedores = BuscarListasObjetosBD.buscarFornecedores(listaFornecedores, tipoPessoa);
		
		JCFornecedor = PreencheJCombo.preencheJComboFornecedor(JCFornecedor, listaFornecedores);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnSelecionar.setBounds(125, 83, 89, 23);
		getContentPane().add(btnSelecionar);
		JCFornecedor.addItemListener( new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED)
					codigo = listaFornecedores.get(JCFornecedor.getSelectedIndex() -1).getIdentificacaoPessoa();
			}
		});
		btnSelecionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				super.mousePressed(arg0);
				TelaGerarRelatorioEntrada.setIdentificacaoPessoa(codigo);
				BuscarFornecedorRelatorio.this.dispose();
			}
		});
		
	}

}
