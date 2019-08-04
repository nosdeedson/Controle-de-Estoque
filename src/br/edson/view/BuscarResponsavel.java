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
import br.edson.model.ResponsavelRetirada;

import javax.swing.JButton;

public class BuscarResponsavel extends JFrame {
	private JComboBox<String> JCResponsavel;
	private List<ResponsavelRetirada> listaResponsaveis;
	private int responsavel;
	/**
	 * Create the panel.
	 */
	public BuscarResponsavel() {
		setTitle("Buscar Fornecedor");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setBounds(500, 250, 400, 146);
		getContentPane().setLayout(null);
		
		JCResponsavel = new JComboBox<String>();
		JCResponsavel.setMaximumRowCount(30);
		JCResponsavel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		JCResponsavel.setBounds(0, 0, 395, 20);
		getContentPane().add(		JCResponsavel);
		
		listaResponsaveis = new ArrayList<ResponsavelRetirada>();
		listaResponsaveis = BuscarListasObjetosBD.buscarResponsaveis(listaResponsaveis);
		
		JCResponsavel = PreencheJCombo.preencheJComboBoResponsaveis(JCResponsavel, listaResponsaveis);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnSelecionar.setBounds(125, 83, 89, 23);
		getContentPane().add(btnSelecionar);
		JCResponsavel.addItemListener( new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED)
					responsavel = listaResponsaveis.get(JCResponsavel.getSelectedIndex() -1).getCodigo();
			}
		});
		btnSelecionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				super.mousePressed(arg0);
				TelaGerarRelatorioRetirada.setResponsavel(responsavel);
				BuscarResponsavel.this.dispose();
			}
		});
		
	}

}
