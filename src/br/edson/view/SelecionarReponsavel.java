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
import br.edson.model.ResponsavelRetirada;
import javax.swing.JButton;


public class SelecionarReponsavel extends JFrame {
	private JComboBox<String> jcResponsaveis;
	private List<ResponsavelRetirada> responsaveis;

	private int codigoResponsavel;
	private String nomeResponsavel;
	
	private TelaRetiradaProduto telaRetiradaProduto;
	private TelaDevolucaoProduto telaDevolucaoProduto;
	/**
	 * Create the panel.
	 */
	public SelecionarReponsavel(boolean retiradaDevolucao) { // true quando for retirada de produto, false quando for devolução
		setTitle("Selecionar Responsável");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setBounds(500, 250, 400, 200);
		getContentPane().setLayout(null);
		
		jcResponsaveis = new JComboBox<String>();
		jcResponsaveis.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		jcResponsaveis.setBounds(0, 0, 395, 20);
		getContentPane().add(jcResponsaveis);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnSelecionar.setBounds(125, 137, 126, 23);
		getContentPane().add(btnSelecionar);
		
		responsaveis = new ArrayList<ResponsavelRetirada>();
		responsaveis = BuscarListasObjetosBD.buscarResponsaveis(responsaveis);
		
		jcResponsaveis = PreencheJCombo.preencheJComboBoResponsaveis(jcResponsaveis, responsaveis);
		
		jcResponsaveis.addItemListener( new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if( e.getStateChange() == ItemEvent.SELECTED) {
					codigoResponsavel = responsaveis.get(jcResponsaveis.getSelectedIndex()- 1).getCodigo();
					nomeResponsavel = responsaveis.get(jcResponsaveis.getSelectedIndex()-1).getNome();
				}
			}
		});
		
		btnSelecionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				if(retiradaDevolucao)
					telaRetiradaProduto = new TelaRetiradaProduto(codigoResponsavel, nomeResponsavel);
				else
					telaDevolucaoProduto = new TelaDevolucaoProduto(codigoResponsavel, nomeResponsavel);
				SelecionarReponsavel.this.dispose();				
			}
		});
		
	}
}
