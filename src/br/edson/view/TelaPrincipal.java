package br.edson.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JEditorPane;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import br.edson.helper.BuscarListasObjetosBD;
import br.edson.helper.GerarRelatorioProdutoPDF;
import br.edson.helper.TrataDados;
import br.edson.model.Produto;

import java.awt.SystemColor;

public class TelaPrincipal extends JFrame {

	private JPanel telaInicial;
	private CadastrarProduto cadastrarProduto;
	private CadastrarFornecedor cadastrarFornecedor;
	private CadastrarResponsavel cadastrarResp;
	
	private static EditarProduto editaProduto;
	private static EditarResponsavel editarResp;
	private static TipoPessoa tipoPessoa;
	
	private static SelecionarFornecedorEntrada telaSelecionarFornecedor;
	private SelecionarReponsavel selecionarResp;
	
	private static TelaEntradaProduto entrada;
	
	private DeletarFornecedor deletarFornecedor;
	private DeletarProduto deletarProduto;
	private DeletarResponsavel deletarResponsavel;
	
	private static boolean flagEntrada = false;
	
	private static TelaGerarRelatorioEntrada relatorioEntrada;
	private static TelaGerarRelatorioRetirada relatorioRetirada;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal telaInicial = new TelaPrincipal();
					telaInicial.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 600, 650);
		setResizable(false);
		telaInicial = new JPanel();
		telaInicial.setBackground(SystemColor.inactiveCaption);
		telaInicial.setBorder(new EmptyBorder(5, 5, 5, 5));
		//this.setExtendedState(MAXIMIZED_BOTH);
		setContentPane(telaInicial);
		setTitle("Almoxarifado");
		telaInicial.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(51, 153, 153)));
		panel_3.setBounds(15, 10, 550, 80);
		telaInicial.add(panel_3);
		panel_3.setLayout(null);
		
		JButton btnCadastrarFornecedor = new JButton("Fornecedor");
		btnCadastrarFornecedor.setBackground(SystemColor.menu);
		btnCadastrarFornecedor.setBounds(10, 40, 150, 25);
		panel_3.add(btnCadastrarFornecedor);
		btnCadastrarFornecedor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JButton btnCadastrarProduto = new JButton("Produto");
		btnCadastrarProduto.setBackground(SystemColor.menu);
		btnCadastrarProduto.setBounds(200, 40, 150, 25);
		panel_3.add(btnCadastrarProduto);
		
		btnCadastrarProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JButton btnCadastrarResponsavel = new JButton("Respons\u00E1vel");
		btnCadastrarResponsavel.setBackground(SystemColor.menu);
		btnCadastrarResponsavel.setBounds(390, 40, 150, 25);
		panel_3.add(btnCadastrarResponsavel);
		btnCadastrarResponsavel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JLabel lblCadastrar = new JLabel("Cadastrar");
		lblCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblCadastrar.setBounds(0, 11, 531, 16);
		panel_3.add(lblCadastrar);
				
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(51, 153, 51)));
		panel_2.setBounds(15, 110, 550, 80);
		telaInicial.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnEditarResponsavel = new JButton("Respons\u00E1vel");
		btnEditarResponsavel.setBackground(SystemColor.menu);
		btnEditarResponsavel.setBounds(390, 40, 150, 25);
		panel_2.add(btnEditarResponsavel);
		btnEditarResponsavel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		
		JButton btnEditarFornecedor = new JButton("Fornecedor");
		btnEditarFornecedor.setBackground(SystemColor.menu);
		btnEditarFornecedor.setBounds(10, 40, 150, 25);
		panel_2.add(btnEditarFornecedor);
		btnEditarFornecedor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JButton btnEditarProduto = new JButton("Produto");
		btnEditarProduto.setBackground(SystemColor.menu);
		btnEditarProduto.setBounds(200, 40, 150, 25);
		panel_2.add(btnEditarProduto);
		btnEditarProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JLabel lblEditar = new JLabel("Editar");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblEditar.setBounds(0, 11, 523, 16);
		panel_2.add(lblEditar);
			
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(51, 204, 51)));
		panel_1.setBounds(15, 310, 550, 80);
		telaInicial.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnRetiradaProduto = new JButton("Retirada");
		btnRetiradaProduto.setBackground(SystemColor.menu);
		btnRetiradaProduto.setBounds(390, 40, 150, 25);
		panel_1.add(btnRetiradaProduto);
		btnRetiradaProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JButton btnEntradaProduto = new JButton("Entrada");
		btnEntradaProduto.setBackground(SystemColor.menu);
		btnEntradaProduto.setBounds(10, 40, 150, 25);
		panel_1.add(btnEntradaProduto);
		btnEntradaProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JButton btnDevolucaoProduto = new JButton("Devolu\u00E7\u00E3o");
		btnDevolucaoProduto.setBackground(SystemColor.menu);
		btnDevolucaoProduto.setBounds(200, 40, 150, 25);
		panel_1.add(btnDevolucaoProduto);
		btnDevolucaoProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblProduto.setHorizontalAlignment(SwingConstants.CENTER);
		lblProduto.setBounds(0, 11, 550, 14);
		panel_1.add(lblProduto);
				
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(255, 51, 0)));
		panel.setBounds(15, 210, 550, 80);
		telaInicial.add(panel);
		panel.setLayout(null);
		
		JButton btnDeletarFornecedor = new JButton("Fornecedor");
		btnDeletarFornecedor.setBounds(10, 40, 150, 25);
		panel.add(btnDeletarFornecedor);
		btnDeletarFornecedor.setBackground(Color.RED);
		btnDeletarFornecedor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JButton btnDeletarProduto = new JButton("Produto");
		btnDeletarProduto.setBounds(200, 40, 150, 25);
		panel.add(btnDeletarProduto);
		btnDeletarProduto.setBackground(Color.RED);
		btnDeletarProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JButton btnDeletarResponsavel = new JButton("Respons\u00E1vel");
		btnDeletarResponsavel.setBounds(390, 40, 150, 25);
		panel.add(btnDeletarResponsavel);
		btnDeletarResponsavel.setBackground(Color.RED);
		btnDeletarResponsavel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JLabel lblDeletar = new JLabel("Deletar ");
		lblDeletar.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeletar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblDeletar.setBounds(0, 11, 550, 16);
		panel.add(lblDeletar);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		panel_4.setBounds(15, 410, 550, 175);
		telaInicial.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblGerarRelatorios = new JLabel("Gerar Relatorios");
		lblGerarRelatorios.setBounds(0, 11, 550, 16);
		lblGerarRelatorios.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerarRelatorios.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		panel_4.add(lblGerarRelatorios);
		
		JButton btnRetirada = new JButton("Retirada");
		btnRetirada.setBackground(SystemColor.menu);
		btnRetirada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnRetirada.setBounds(390, 80, 150, 25);
		panel_4.add(btnRetirada);
		
		JButton btnEntrada = new JButton("Entrada");
		btnEntrada.setBackground(SystemColor.menu);
		btnEntrada.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnEntrada.setBounds(200, 40, 150, 25);
		panel_4.add(btnEntrada);
		
		JButton btnProduto = new JButton("Produto");
		btnProduto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnProduto.setBackground(SystemColor.menu);
		btnProduto.setBounds(10, 80, 150, 25);
		panel_4.add(btnProduto);
		
		JButton btnDevolucao = new JButton("Devolu\u00E7\u00E3o");
		btnDevolucao.setBackground(SystemColor.menu);
		btnDevolucao.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnDevolucao.setBounds(10, 40, 150, 25);
		panel_4.add(btnDevolucao);
		
		JButton btnResponsavel = new JButton("Respons\u00E1vel");
		btnResponsavel.setBackground(SystemColor.menu);
		btnResponsavel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnResponsavel.setBounds(200, 80, 150, 25);
		panel_4.add(btnResponsavel);
		
		JButton btnFornecedor = new JButton("Fornecedor");
		btnFornecedor.setBackground(SystemColor.menu);
		btnFornecedor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		btnFornecedor.setBounds(390, 40, 150, 25);
		panel_4.add(btnFornecedor);
		
		JButton btnPrevisaoConsumo = new JButton("Gerar PDF Previs\u00E3o Consumo");
		btnPrevisaoConsumo.setBounds(152, 134, 259, 23);
		panel_4.add(btnPrevisaoConsumo);
		btnPrevisaoConsumo.setBackground(SystemColor.menu);
		btnPrevisaoConsumo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		btnPrevisaoConsumo.addMouseListener( new MouseAdapter() {
			 @Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				int qtdMeses = Integer.parseInt( JOptionPane.showInputDialog(null, "Digite para quantos meses é a \nprevisão de consumo?"));
				//Calendar c = Calendar.getInstance();
				//String data = new SimpleDateFormat("YYYY/MM/dd").format(c.getTime());
				TrataDados.previsaoConsumo(qtdMeses);
			}
		});

		// metodos dos botoes
		// metodos cadastrar
		btnCadastrarResponsavel.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				cadastrarResp= new CadastrarResponsavel();
			}
		});
		
		btnCadastrarProduto.addMouseListener( new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				cadastrarProduto = new CadastrarProduto();
			};
		});
		btnCadastrarFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				super.mousePressed(arg0);
				tipoPessoa = new TipoPessoa(5);
			}
		});
		//metodos editar
		btnEditarProduto.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				editaProduto = new EditarProduto();
			}
		});
		
		btnEditarFornecedor.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				tipoPessoa = new TipoPessoa(2);	// se 1 abre selecionar fornecedor, se 2 abre editar fornecedor, se 3 abre deletar fornecedor
			}
		});
		
		btnEditarResponsavel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				editarResp = new EditarResponsavel(); 
			}
		});
		// entrada 
		btnEntradaProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				tipoPessoa = new TipoPessoa(1);	// se 1 abre selecionar fornecedor, se 2 abre editar fornecedor, se 3 abre deletar fornecedor
			}
		});
		// retirada
		btnRetiradaProduto.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				selecionarResp = new SelecionarReponsavel(true); // true quando for retirada de produto
			}
		});
		btnDevolucaoProduto.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				selecionarResp = new SelecionarReponsavel(false);// false quando for devolução de produto
			}
		});
		
		// botoes deletar
		btnDeletarFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				tipoPessoa = new TipoPessoa(3);
			}
		});
		btnDeletarProduto.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				deletarProduto = new DeletarProduto();
			}
		});
		btnDeletarResponsavel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				deletarResponsavel = new DeletarResponsavel();
			}
		});
		
		// gerar relatorio
		btnProduto.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				JOptionPane.showMessageDialog(null, "Salve o PDF se quiser manter o arquivo.");
				List<Produto> listaProdutos = new ArrayList<Produto>();
				listaProdutos = BuscarListasObjetosBD.buscaProdutosBD(listaProdutos);
				GerarRelatorioProdutoPDF.gerarRelatorioProdutos(listaProdutos);
			}
		});
		btnEntrada.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				JOptionPane.showMessageDialog(null, "Salve o PDF se quiser manter o arquivo.");
				relatorioEntrada = new TelaGerarRelatorioEntrada(); 
			}
		});
		btnRetirada.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				JOptionPane.showMessageDialog(null, "Salve o PDF se quiser manter o arquivo.");
				relatorioRetirada = new TelaGerarRelatorioRetirada();
			}
		});
		btnDevolucao.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				JOptionPane.showMessageDialog(null, "Salve o PDF se quiser manter o arquivo.");
				TrataDados.gerarRelatorioDevolucao();
			}
		});
		btnFornecedor.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				JOptionPane.showMessageDialog(null, "Salve o PDF se quiser manter o arquivo.");
				tipoPessoa = new TipoPessoa(4);
			}
		});
		btnResponsavel.addMouseListener( new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);
				JOptionPane.showMessageDialog(null, "Salve o PDF se quiser manter o arquivo.");
				TrataDados.gerarListaResponsavel();
			}
		});
	}
	

	public JPanel getTelaInicial() {
		return telaInicial;
	}

	public void setTelaInicial(JPanel telaInicial) {
		this.telaInicial = telaInicial;
	}

	public CadastrarProduto getCadastrarProduto() {
		return cadastrarProduto;
	}

	public void setCadastrarProduto(CadastrarProduto cadastrarProduto) {
		this.cadastrarProduto = cadastrarProduto;
	}

	public static EditarProduto getEditaProduto() {
		return editaProduto;
	}

	public static void setEditaProduto(EditarProduto editaProduto) {
		TelaPrincipal.editaProduto = editaProduto;
	}

	public static TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public static void setTipoPessoa(TipoPessoa tipoPessoa) {
		TelaPrincipal.tipoPessoa = tipoPessoa;
	}
	
	public static EditarResponsavel getEditarResp() {
		return editarResp;
	}

	public static void setEditarResp(EditarResponsavel editarResp) {
		TelaPrincipal.editarResp = editarResp;
	}

	public static boolean isFlagEntrada() {
		return flagEntrada;
	}

	public static void setFlagEntrada(boolean flagEntrada) {
		TelaPrincipal.flagEntrada = flagEntrada;
	}

	public static TelaEntradaProduto getEntrada() {
		return entrada;
	}

	public static void setEntrada(TelaEntradaProduto entrada) {
		TelaPrincipal.entrada = entrada;
	}

	public static SelecionarFornecedorEntrada getTelaSelecionarFornecedor() {
		return telaSelecionarFornecedor;
	}

	public static void setTelaSelecionarFornecedor(SelecionarFornecedorEntrada telaSelecionarFornecedor) {
		TelaPrincipal.telaSelecionarFornecedor = telaSelecionarFornecedor;
	}

	public static TelaGerarRelatorioEntrada getRelatorioEntrada() {
		return relatorioEntrada;
	}

	public static void setRelatorioEntrada(TelaGerarRelatorioEntrada relatorioEntrada) {
		TelaPrincipal.relatorioEntrada = relatorioEntrada;
	}

	public static TelaGerarRelatorioRetirada getRelatorioRetirada() {
		return relatorioRetirada;
	}

	public static void setRelatorioRetirada(TelaGerarRelatorioRetirada relatorioRetirada) {
		TelaPrincipal.relatorioRetirada = relatorioRetirada;
	}
}// fim classe
