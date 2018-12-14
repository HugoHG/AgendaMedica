package br.senai.sp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.senai.sp.dao.EspecialidadeDAO;
import br.senai.sp.dao.MedicoDAO;
import br.senai.sp.dao.PeriodoDAO;
import br.senai.sp.model.Especialidade;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ListaEspecialidades extends JFrame {
	
	private int modoAtual = 0;
	private final int MODO_VISUALIZAR = 1;
	private final int MODO_CADASTRAR = 2;
	private final int MODO_EDITAR = 3;
	private Integer idEspecialidadeSelecionada;

	private JPanel contentPanel;
	private JTable tableTodasEspecialidades;
	JScrollPane scrollEspecialidades;
	JPanel panelEspecialidades;
	JTabbedPane tabbedPane;
	private JButton btnCadastrar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JTextField txtNomeEspecialidade;
	JPanel panelCadastrar;
	JLabel lblNomeDaEspecialidade;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ListaEspecialidades frame = new ListaEspecialidades();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ListaEspecialidades() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 414, 230);
		contentPanel.add(tabbedPane);
		
		panelEspecialidades = new JPanel();
		tabbedPane.addTab("Especialidades", null, panelEspecialidades, null);
		
		panelCadastrar = new JPanel();
		panelCadastrar.setLayout(null);
		
		lblNomeDaEspecialidade = new JLabel("Nome da especialidade");
		lblNomeDaEspecialidade.setBounds(10, 11, 109, 14);
		panelCadastrar.add(lblNomeDaEspecialidade);
		
		txtNomeEspecialidade = new JTextField();
		txtNomeEspecialidade.setBounds(10, 27, 242, 20);
		panelCadastrar.add(txtNomeEspecialidade);
		txtNomeEspecialidade.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
				Especialidade especialidade = new Especialidade();
				especialidade.setNomeEspecialidade(txtNomeEspecialidade.getText().toString());
				if (modoAtual == MODO_CADASTRAR) {
					//cadastra
					especialidadeDAO.gravar(especialidade);
					montarTabelaTodasEspecialidades();
					tabbedPane.setSelectedIndex(0);
				} else if (modoAtual == MODO_EDITAR) {
					//editar
					especialidade.setIdEspecialidade(idEspecialidadeSelecionada);
					especialidadeDAO.editarEspecialidade(especialidade);
					montarTabelaTodasEspecialidades();
					tabbedPane.setSelectedIndex(0);
				}
			}
		});
		btnSalvar.setBounds(310, 168, 89, 23);
		panelCadastrar.add(btnSalvar);
		
		montarTabelaTodasEspecialidades();
	}
	
	private void montarTabelaTodasEspecialidades() {
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		ArrayList<Especialidade> listaTodasEspecialidades = especialidadeDAO.listar();
		
		DefaultTableModel modeloTodasEspec = new DefaultTableModel();
		modeloTodasEspec.addColumn("id");
		modeloTodasEspec.addColumn("Especialidade");
		
		for (Especialidade especialidade : listaTodasEspecialidades) {
			modeloTodasEspec.addRow(new Object[] {especialidade.getIdEspecialidade(), especialidade.getNomeEspecialidade()});
		}
		
		tableTodasEspecialidades = new JTable(modeloTodasEspec);
		
		if (scrollEspecialidades != null) {
			panelEspecialidades.remove(scrollEspecialidades);
		}
		panelEspecialidades.setLayout(null);
		
		scrollEspecialidades = new JScrollPane();
		scrollEspecialidades.setBounds(10, 5, 389, 152);
		panelEspecialidades.add(scrollEspecialidades);
		scrollEspecialidades.setViewportView(tableTodasEspecialidades);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudarModo(MODO_CADASTRAR);
			}
		});
		btnCadastrar.setBounds(10, 168, 89, 23);
		panelEspecialidades.add(btnCadastrar);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudarModo(MODO_EDITAR);
			}
		});
		btnEditar.setBounds(119, 168, 89, 23);
		panelEspecialidades.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EspecialidadeDAO dao = new EspecialidadeDAO();
				dao.excluirEspecialidadeMedicoPorIdEspecialidade(idEspecialidadeSelecionada);
				dao.excluirEspecialidade(idEspecialidadeSelecionada);
				montarTabelaTodasEspecialidades();
			}
		});
		btnExcluir.setBounds(231, 168, 89, 23);
		panelEspecialidades.add(btnExcluir);
		
		tableTodasEspecialidades.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				idEspecialidadeSelecionada = Integer.valueOf(tableTodasEspecialidades.getValueAt(tableTodasEspecialidades.getSelectedRow(), 0).toString());
			}
		});
	}
	
	private void mudarModo(int modo) {
		this.modoAtual = modo;
		if(modoAtual == MODO_VISUALIZAR) {
			if (tabbedPane.getTabCount() >= 2) {
				tabbedPane.removeTabAt(1);
			}
		} else if (modoAtual == MODO_CADASTRAR) {
			if (tabbedPane.getTabCount() < 2) {
				tabbedPane.addTab("Cadastrar", null, panelCadastrar, null);
				tabbedPane.setSelectedIndex(1);
			}
		} else if(modoAtual == MODO_EDITAR) {
			if (tabbedPane.getTabCount() < 2) {
				tabbedPane.addTab("Cadastrar", null, panelCadastrar, null);
				tabbedPane.setSelectedIndex(1);
			}
			EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
			txtNomeEspecialidade.setText(especialidadeDAO.getEspecialidade(idEspecialidadeSelecionada).getNomeEspecialidade());
		}
	}
}
