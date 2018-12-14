package br.senai.sp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import br.senai.sp.dao.EspecialidadeDAO;
import br.senai.sp.dao.MedicoDAO;
import br.senai.sp.dao.PeriodoDAO;
import br.senai.sp.helper.JanelaMedicoHelper;
import br.senai.sp.model.Especialidade;
import br.senai.sp.model.Medico;
import br.senai.sp.model.Periodo;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class ListaMedicos extends JFrame {

	private int modoAtual = 0;
	private final int MODO_VISUALIZAR = 1;
	private final int MODO_CADASTRAR = 2;
	private final int MODO_EDITAR = 3;
	
	private JPanel contentPane;
	private JTable tabelaMedicos;
	JTabbedPane tabbedPane;
	JPanel panelListaMedicos;
	JPanel detalhesMedicos;
	JScrollPane scrollListaMedicos;
	JPanel panelEspecialidades;
	private JTextField txtNomeMedico;
	private JTable tableEspecialidades;
	private JFormattedTextField txtDomingoInicio;
	private JFormattedTextField txtDomingoFim;
	private JFormattedTextField txtSextaInicio;
	private JFormattedTextField txtSegundaInicio;
	private JFormattedTextField txtTercaInicio;
	private JFormattedTextField txtQuartaInicio;
	private JFormattedTextField txtQuintaInicio;
	private JFormattedTextField txtSabadoInicio;
	private JFormattedTextField txtSextaFim;
	private JFormattedTextField txtSegundaFim;
	private JFormattedTextField txtTercaFim;
	private JFormattedTextField txtQuartaFim;
	private JFormattedTextField txtQuintaFim;
	private JFormattedTextField txtSabadoFim;
	JLabel lblNome;
	private int idMedicoSelecionado;
	private JButton btnCadastrarMdico;
	private JButton btnSalvar;
	JCheckBox chckbxDomingo;
	JCheckBox chckbxSextafeira;
	private JCheckBox chckbxSegundafeira;
	JCheckBox chckbxTercafeira;
	JCheckBox chckbxQuartafeira;
	JCheckBox chckbxQuintafeira;
	JCheckBox chckbxSabado;
	JScrollPane scrollEspecialidades;
	JLabel lblModo;
	JLabel lblEspecialidades;
	private JButton btnAdicionarEsp;
	private JButton btnExcluirEsp;
	JTable tableTodasEspecialidades;
	JScrollPane scrollTodasEspecialidades;
	private JButton btnOk;
	private JButton btnCancelarEsp;
	int idEspecialidadeSelecionada;//id da especialidade selecionada na tabela que mostra todas as especialidades
	int idEspecilidadeMedicoSelecionada;//id da especialidade seleciondada na tabela de especialidades de cada médico
	
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ListaMedicos frame = new ListaMedicos();
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
	public ListaMedicos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 595, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 559, 315);
		contentPane.add(tabbedPane);
		
		panelListaMedicos = new JPanel();
		tabbedPane.addTab("Lista de Médicos", null, panelListaMedicos, null);
		panelListaMedicos.setLayout(null);
		
		btnCadastrarMdico = new JButton("Cadastrar M\u00E9dico");
		btnCadastrarMdico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
				tabbedPane.setSelectedIndex(1);
				mudarModo(MODO_CADASTRAR);
			}
		});
		btnCadastrarMdico.setBounds(10, 233, 117, 23);
		panelListaMedicos.add(btnCadastrarMdico);
		
		detalhesMedicos = new JPanel();
		tabbedPane.addTab("Detalhes", null, detalhesMedicos, null);
		detalhesMedicos.setLayout(null);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 11, 46, 14);
		detalhesMedicos.add(lblNome);
		
		txtNomeMedico = new JTextField();
		txtNomeMedico.setEnabled(false);
		txtNomeMedico.setBounds(10, 24, 210, 20);
		detalhesMedicos.add(txtNomeMedico);
		txtNomeMedico.setColumns(10);
		
		lblEspecialidades = new JLabel("Especialidades");
		lblEspecialidades.setBounds(10, 41, 96, 25);
		detalhesMedicos.add(lblEspecialidades);
		
		JLabel lblHorariosDeAtendimento = new JLabel("Hor\u00E1rios de atendimento");
		lblHorariosDeAtendimento.setBounds(289, 11, 169, 14);
		detalhesMedicos.add(lblHorariosDeAtendimento);
		
		chckbxDomingo = new JCheckBox("Domingo");
		chckbxDomingo.setBounds(289, 42, 97, 23);
		detalhesMedicos.add(chckbxDomingo);
		chckbxDomingo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (chckbxDomingo.isSelected()) {
					txtDomingoInicio.setEnabled(true);
					txtDomingoFim.setEnabled(true);
				} else {
					txtDomingoInicio.setEnabled(false);
					txtDomingoFim.setEnabled(false);
				}
			}
		});
		
		chckbxSegundafeira = new JCheckBox("Segunda-feira");
		chckbxSegundafeira.setBounds(289, 62, 97, 23);
		detalhesMedicos.add(chckbxSegundafeira);
		chckbxSegundafeira.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(chckbxSegundafeira.isSelected()) {
					txtSegundaInicio.setEnabled(true);
					txtSegundaFim.setEnabled(true);
				} else {
					txtSegundaInicio.setEnabled(false);
					txtSegundaFim.setEnabled(false);
				}
			}
		});
		
		chckbxTercafeira = new JCheckBox("Ter\u00E7a-feira");
		chckbxTercafeira.setBounds(289, 82, 97, 23);
		detalhesMedicos.add(chckbxTercafeira);
		chckbxTercafeira.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(chckbxTercafeira.isSelected()) {
					txtTercaInicio.setEnabled(true);
					txtTercaFim.setEnabled(true);
				} else {
					txtTercaInicio.setEnabled(false);
					txtTercaFim.setEnabled(false);
				}
			}
		});
		
		chckbxQuartafeira = new JCheckBox("Quarta-feira");
		chckbxQuartafeira.setBounds(289, 102, 97, 23);
		detalhesMedicos.add(chckbxQuartafeira);
		chckbxQuartafeira.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(chckbxQuartafeira.isSelected()) {
					txtQuartaInicio.setEnabled(true);
					txtQuartaFim.setEnabled(true);
				} else {
					txtQuartaInicio.setEnabled(false);
					txtQuartaFim.setEnabled(false);
				}
			}
		});
		
		chckbxQuintafeira = new JCheckBox("Quinta-feira");
		chckbxQuintafeira.setBounds(289, 122, 97, 23);
		detalhesMedicos.add(chckbxQuintafeira);
		chckbxQuintafeira.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(chckbxQuintafeira.isSelected()) {
					txtQuintaInicio.setEnabled(true);
					txtQuintaFim.setEnabled(true);
				} else {
					txtQuintaInicio.setEnabled(false);
					txtQuintaFim.setEnabled(false);
				}
			}
		});
		
		chckbxSextafeira = new JCheckBox("Sexta-feira");
		chckbxSextafeira.setBounds(289, 144, 97, 23);
		detalhesMedicos.add(chckbxSextafeira);
		chckbxSextafeira.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(chckbxSextafeira.isSelected()) {
					txtSextaInicio.setEnabled(true);
					txtSextaFim.setEnabled(true);
				} else {
					txtSextaInicio.setEnabled(false);
					txtSextaFim.setEnabled(false);
				}
			}
		});
		
		chckbxSabado = new JCheckBox("S\u00E1bado");
		chckbxSabado.setBounds(289, 166, 97, 23);
		detalhesMedicos.add(chckbxSabado);
		chckbxSabado.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(chckbxSabado.isSelected()) {
					txtSabadoInicio.setEnabled(true);
					txtSabadoFim.setEnabled(true);
				} else {
					txtSabadoInicio.setEnabled(false);
					txtSabadoFim.setEnabled(false);
				}
			}
		});
		
		try {
			txtDomingoInicio = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtDomingoInicio.setEnabled(false);
		txtDomingoInicio.setBounds(423, 42, 35, 23);
		detalhesMedicos.add(txtDomingoInicio);
		txtDomingoInicio.setColumns(10);
		
		try {
			txtDomingoFim = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtDomingoFim.setEnabled(false);
		txtDomingoFim.setColumns(10);
		txtDomingoFim.setBounds(480, 43, 35, 23);
		detalhesMedicos.add(txtDomingoFim);
		
		try {
			txtSegundaInicio = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtSegundaInicio.setEnabled(false);
		txtSegundaInicio.setColumns(10);
		txtSegundaInicio.setBounds(423, 63, 35, 23);
		detalhesMedicos.add(txtSegundaInicio);
		
		try {
			txtTercaInicio = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtTercaInicio.setEnabled(false);
		txtTercaInicio.setColumns(10);
		txtTercaInicio.setBounds(423, 82, 35, 23);
		detalhesMedicos.add(txtTercaInicio);
		
		try {
			txtQuartaInicio = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtQuartaInicio.setEnabled(false);
		txtQuartaInicio.setColumns(10);
		txtQuartaInicio.setBounds(423, 103, 35, 23);
		detalhesMedicos.add(txtQuartaInicio);
		
		try {
			txtQuintaInicio = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtQuintaInicio.setEnabled(false);
		txtQuintaInicio.setColumns(10);
		txtQuintaInicio.setBounds(423, 123, 35, 23);
		detalhesMedicos.add(txtQuintaInicio);
		
		try {
			txtSextaInicio = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtSextaInicio.setEnabled(false);
		txtSextaInicio.setColumns(10);
		txtSextaInicio.setBounds(423, 145, 35, 23);
		detalhesMedicos.add(txtSextaInicio);
		
		try {
			txtSabadoInicio = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtSabadoInicio.setEnabled(false);
		txtSabadoInicio.setColumns(10);
		txtSabadoInicio.setBounds(423, 167, 35, 23);
		detalhesMedicos.add(txtSabadoInicio);
		
		try {
			txtSegundaFim = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtSegundaFim.setEnabled(false);
		txtSegundaFim.setColumns(10);
		txtSegundaFim.setBounds(480, 63, 35, 23);
		detalhesMedicos.add(txtSegundaFim);
		
		try {
			txtTercaFim = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtTercaFim.setEnabled(false);
		txtTercaFim.setColumns(10);
		txtTercaFim.setBounds(480, 83, 35, 23);
		detalhesMedicos.add(txtTercaFim);
		
		try {
			txtQuartaFim = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtQuartaFim.setEnabled(false);
		txtQuartaFim.setColumns(10);
		txtQuartaFim.setBounds(480, 103, 35, 23);
		detalhesMedicos.add(txtQuartaFim);
		
		try {
			txtQuintaFim = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtQuintaFim.setEnabled(false);
		txtQuintaFim.setColumns(10);
		txtQuintaFim.setBounds(480, 123, 35, 23);
		detalhesMedicos.add(txtQuintaFim);
		
		try {
			txtSextaFim = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtSextaFim.setEnabled(false);
		txtSextaFim.setColumns(10);
		txtSextaFim.setBounds(480, 145, 35, 23);
		detalhesMedicos.add(txtSextaFim);
		
		try {
			txtSabadoFim = new JFormattedTextField(new MaskFormatter("##:##"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		txtSabadoFim.setEnabled(false);
		txtSabadoFim.setColumns(10);
		txtSabadoFim.setBounds(480, 167, 35, 23);
		detalhesMedicos.add(txtSabadoFim);
		
		JLabel labelTraco = new JLabel("-");
		labelTraco.setBounds(464, 46, 27, 14);
		detalhesMedicos.add(labelTraco);
		
		JLabel label = new JLabel("-");
		label.setBounds(464, 66, 27, 14);
		detalhesMedicos.add(label);
		
		JLabel label_1 = new JLabel("-");
		label_1.setBounds(464, 86, 27, 14);
		detalhesMedicos.add(label_1);
		
		JLabel label_2 = new JLabel("-");
		label_2.setBounds(464, 106, 27, 14);
		detalhesMedicos.add(label_2);
		
		JLabel label_3 = new JLabel("-");
		label_3.setBounds(464, 126, 27, 14);
		detalhesMedicos.add(label_3);
		
		JLabel label_4 = new JLabel("-");
		label_4.setBounds(464, 148, 27, 14);
		detalhesMedicos.add(label_4);
		
		JLabel label_5 = new JLabel("-");
		label_5.setBounds(464, 170, 27, 14);
		detalhesMedicos.add(label_5);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (modoAtual == MODO_CADASTRAR) {
					salvarMedico();
				} else if (modoAtual == MODO_EDITAR) {
					editarMedico();
				}
			}
		});
		btnSalvar.setBounds(423, 253, 89, 23);
		detalhesMedicos.add(btnSalvar);
		
		lblModo = new JLabel("");
		lblModo.setBounds(10, 257, 46, 14);
		detalhesMedicos.add(lblModo);
		
		panelEspecialidades = new JPanel();
		panelEspecialidades.setLayout(null);
		
		//tabbedPane.removeTabAt(tabbedPane.getTabCount()-1);
		
		montarTabela();
//		montarTabelaTodasEspecialidades();
	}
	
	private void montarTabela() {
		MedicoDAO medicoDao = new MedicoDAO();
		ArrayList<Medico> listaMedicosCadastrados = medicoDao.listarMedicos();
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("id");
		modelo.addColumn("Nome");
		
		for (Medico medico : listaMedicosCadastrados) {
			modelo.addRow(new Object[] {medico.getIdMedico(), medico.getNomeMedico()});
		}
		
		tabelaMedicos = new JTable(modelo);
		
		if (scrollListaMedicos != null) {
			panelListaMedicos.remove(scrollListaMedicos);
		}
		
		scrollListaMedicos = new JScrollPane();
		scrollListaMedicos.setBounds(0, 11, 554, 196);
		scrollListaMedicos.setViewportView(tabelaMedicos);
		panelListaMedicos.add(scrollListaMedicos);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudarModo(MODO_EDITAR);
			}
		});
		btnEditar.setBounds(157, 233, 89, 23);
		panelListaMedicos.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PeriodoDAO periodoDAOExcluir = new PeriodoDAO();
				periodoDAOExcluir.excluirPeriodo(idMedicoSelecionado);
				EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
				especialidadeDAO.excluirEspecialidadeMedico(idMedicoSelecionado);
				MedicoDAO medicoDAOExcluir = new MedicoDAO();
				medicoDAOExcluir.excluirMedico(idMedicoSelecionado);
				montarTabela();
			}
		});
		btnExcluir.setBounds(282, 233, 89, 23);
		panelListaMedicos.add(btnExcluir);
		
		tabelaMedicos.addMouseListener(new MouseListener() {
			
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
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				idMedicoSelecionado = Integer.parseInt(tabelaMedicos.getValueAt(tabelaMedicos.getSelectedRow(), 0).toString());
				preencherCampos(idMedicoSelecionado);
				mudarModo(MODO_VISUALIZAR);
			}
		});
	}
	
	private void preencherCampos(int idMedico) {
		//Pegando o médico selecionado
		MedicoDAO medicoDao = new MedicoDAO();
		Medico medicoSelecionado = medicoDao.getMedico(idMedico);
		//Colocando o nome do médico no campo certo
		txtNomeMedico.setText(medicoSelecionado.getNomeMedico());
		//Preenchendo os dias
		preencherDias();
		//Montando a tabela de especialidades
		montarTabelaEspecialidades();
	}
	
	private void preencherDias() {
		PeriodoDAO periodoDAO = new PeriodoDAO();
		ArrayList<Integer> listaDias = periodoDAO.listarDias(idMedicoSelecionado);
		for(Integer dia : listaDias) {
			switch (dia) {
			case 0:
				chckbxDomingo.setSelected(true);
				txtDomingoInicio.setText(periodoDAO.getPeriodo(idMedicoSelecionado, 0).getHoraInicio().toString());
				txtDomingoFim.setText(periodoDAO.getPeriodo(idMedicoSelecionado, 0).getHoraFim().toString());
				break;
			case 1:
				chckbxSegundafeira.setSelected(true);
				txtSegundaInicio.setText(periodoDAO.getPeriodo(idMedicoSelecionado, 1).getHoraInicio().toString());
				txtSegundaFim.setText(periodoDAO.getPeriodo(idMedicoSelecionado, 1).getHoraFim().toString());
				break;
			case 2:
				chckbxTercafeira.setSelected(true);
				txtTercaInicio.setText(periodoDAO.getPeriodo(idMedicoSelecionado, 2).getHoraInicio().toString());
				txtTercaFim.setText(periodoDAO.getPeriodo(idMedicoSelecionado, 2).getHoraFim().toString());
				break;
			case 3:
				chckbxQuartafeira.setSelected(true);
				txtQuartaInicio.setText(periodoDAO.getPeriodo(idMedicoSelecionado, 3).getHoraInicio().toString());
				txtQuartaFim.setText(periodoDAO.getPeriodo(idMedicoSelecionado, 3).getHoraFim().toString());
				break;
			case 4:
				chckbxQuintafeira.setSelected(true);
				txtQuintaInicio.setText(periodoDAO.getPeriodo(idMedicoSelecionado, 4).getHoraInicio().toString());
				txtQuintaFim.setText(periodoDAO.getPeriodo(idMedicoSelecionado, 4).getHoraFim().toString());
				break;
			case 5:
				chckbxSextafeira.setSelected(true);
				txtSextaInicio.setText(periodoDAO.getPeriodo(idMedicoSelecionado, 5).getHoraInicio().toString());
				txtSextaFim.setText(periodoDAO.getPeriodo(idMedicoSelecionado, 5).getHoraFim().toString());
				break;
			case 6:
				chckbxSabado.setSelected(true);
				txtSabadoInicio.setText(periodoDAO.getPeriodo(idMedicoSelecionado, 6).getHoraInicio().toString());
				txtSabadoFim.setText(periodoDAO.getPeriodo(idMedicoSelecionado, 6).getHoraFim().toString());
				break;
			}
		}
	}
	
	private void limparCampos() {
		//montarTabelaEspecialidades();
		txtDomingoInicio.setText("");
		txtDomingoFim.setText("");
		txtSextaInicio.setText("");
		txtSegundaInicio.setText("");
		txtTercaInicio.setText("");
		txtQuartaInicio.setText("");
		txtQuintaInicio.setText("");
		txtSabadoInicio.setText("");
		txtSextaFim.setText("");
		txtSegundaFim.setText("");
		txtTercaFim.setText("");
		txtQuartaFim.setText("");
		txtQuintaFim.setText("");
		txtSabadoFim.setText("");
		
		chckbxDomingo.setSelected(false);
		chckbxSextafeira.setSelected(false);
		chckbxSegundafeira.setSelected(false);
		chckbxTercafeira.setSelected(false);
		chckbxQuartafeira.setSelected(false);
		chckbxQuintafeira.setSelected(false);
		chckbxSabado.setSelected(false);
		
		txtNomeMedico.setText("");
		
		//tableEspecialidades.removeAll();
		//tableEspecialidades.setVisible(false);
//		System.out.println("teste");
		
		//tableEspecialidades.setModel(JanelaMedicoHelper.modeloVazioTabelaEspecialidades());
	}
	
	private void salvarMedico() {
		if (!txtNomeMedico.getText().toString().isEmpty()) {
			//Montando o objeto médico
			Medico medico = new Medico();
			medico.setNomeMedico(txtNomeMedico.getText().toString());
			//Salvando o médico
			MedicoDAO medicoDao = new MedicoDAO();
			medicoDao.gravarMedico(medico);
			montarTabela();
		} else {
			JOptionPane.showMessageDialog(null,
					"Ocorreu um erro na gravação do médico.\nO nome do médico não pode estar vazio",
					"Erro", JOptionPane.ERROR_MESSAGE);
		}
		salvarHorarios();
	}
	
	private void editarMedico() {
		if (!txtNomeMedico.getText().toString().isEmpty()) {
			//Montando o objeto médico
			Medico medico = new Medico();
			medico.setIdMedico(idMedicoSelecionado);
			medico.setNomeMedico(txtNomeMedico.getText().toString());
			//Salvando o médico
			MedicoDAO medicoDao = new MedicoDAO();
			medicoDao.editarMedico(medico);
		} else {
			JOptionPane.showMessageDialog(null,
					"Ocorreu um erro na ediçao do médico.\nO nome do médico não pode estar vazio",
					"Erro", JOptionPane.ERROR_MESSAGE);
		}
		editarHorarios();
		montarTabela();
	}
	
	public void montarTabelaEspecialidades() {
		btnAdicionarEsp = new JButton("Adicionar");
		btnAdicionarEsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				panelEspecialidades.setVisible(true);
				if (tabbedPane.getTabCount() < 3) {
					tabbedPane.addTab("Especialidades", null, panelEspecialidades, null);
				}
				tabbedPane.setSelectedIndex(2);
				montarTabelaTodasEspecialidades();
			}
		});
		btnAdicionarEsp.setBounds(10, 205, 89, 23);
		detalhesMedicos.add(btnAdicionarEsp);
		
		btnExcluirEsp = new JButton("Excluir");
		btnExcluirEsp.setBounds(131, 205, 89, 23);
		btnExcluirEsp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (idEspecilidadeMedicoSelecionada != 0) {
					MedicoDAO medicoDAO = new MedicoDAO();
					medicoDAO.removerEspecialidade(idMedicoSelecionado, idEspecilidadeMedicoSelecionada);
					montarTabelaEspecialidades();
				} else {
					JOptionPane.showMessageDialog(null,
							"Não há nenhuma especialidade selecionada", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		detalhesMedicos.add(btnExcluirEsp);
		
		EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
		ArrayList<Especialidade> listaEspecialidades = especialidadeDAO.listar(idMedicoSelecionado);
		
		DefaultTableModel modeloEspec = new DefaultTableModel();
		modeloEspec.addColumn("id");
		modeloEspec.addColumn("Especialidade");
		
		for (Especialidade especialidade : listaEspecialidades) {
			modeloEspec.addRow(new Object[] {especialidade.getIdEspecialidade(), especialidade.getNomeEspecialidade()});
		}
		
		tableEspecialidades = new JTable(modeloEspec);
		
		tableEspecialidades.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				idEspecilidadeMedicoSelecionada = Integer.valueOf(tableEspecialidades.getValueAt(tableEspecialidades.getSelectedRow(), 0).toString());
			}
		});
		
		if (scrollEspecialidades != null) {
			detalhesMedicos.remove(scrollEspecialidades);
		}
		
		scrollEspecialidades = new JScrollPane();
		scrollEspecialidades.setBounds(10, 65, 210, 131);
		detalhesMedicos.add(scrollEspecialidades);
		scrollEspecialidades.setViewportView(tableEspecialidades);
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
		
		if (scrollTodasEspecialidades != null) {
			panelEspecialidades.remove(scrollTodasEspecialidades);
		}
		
		scrollTodasEspecialidades = new JScrollPane();
		scrollTodasEspecialidades.setBounds(0, 0, 544, 233);
		panelEspecialidades.add(scrollTodasEspecialidades);
		scrollTodasEspecialidades.setViewportView(tableTodasEspecialidades);
		
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
		
		btnOk = new JButton("Ok");
		btnOk.setBounds(10, 253, 89, 23);
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MedicoDAO medicoDAO = new MedicoDAO();
				medicoDAO.adicionarEspecialidade(idMedicoSelecionado, idEspecialidadeSelecionada);
				montarTabelaEspecialidades();
				tabbedPane.removeTabAt(2);
				tabbedPane.setSelectedIndex(1);
			}
		});
		panelEspecialidades.add(btnOk);
		
		btnCancelarEsp = new JButton("Cancelar");
		btnCancelarEsp.setBounds(124, 253, 89, 23);
		btnCancelarEsp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				tabbedPane.removeTabAt(2);
				tabbedPane.setSelectedIndex(1);
			}
		});
		panelEspecialidades.add(btnCancelarEsp);
	}
	
	private void salvarHorarios() {
		MedicoDAO medicoDAO = new MedicoDAO();
		int idMedicoSalvar = 0;
		if (modoAtual == MODO_CADASTRAR) {
			idMedicoSalvar = medicoDAO.getUltimoMedico();
		} else if (modoAtual == MODO_EDITAR) {
			idMedicoSalvar = idMedicoSelecionado;
		}
		
		ArrayList<JCheckBox> listaCheckBox = new ArrayList<>();
		listaCheckBox.add(chckbxDomingo);
		listaCheckBox.add(chckbxSegundafeira);
		listaCheckBox.add(chckbxTercafeira);
		listaCheckBox.add(chckbxQuartafeira);
		listaCheckBox.add(chckbxQuintafeira);
		listaCheckBox.add(chckbxSextafeira);
		listaCheckBox.add(chckbxSabado);
		
		int dia = 0;
		for(JCheckBox checkBox : listaCheckBox) {
			if (checkBox.isSelected()) {
				Periodo periodo = new Periodo();
				PeriodoDAO periodoDao = new PeriodoDAO();
				
				periodo.setIdMedico(idMedicoSalvar);
				periodo.setDiaSemana(dia);
				
				ArrayList<JTextField> listaCampos = getTextField(dia);
				Time horaInicio = JanelaMedicoHelper.getTime(listaCampos.get(0).getText().toString());
				periodo.setHoraInicio(horaInicio);
				Time horaFim = JanelaMedicoHelper.getTime(listaCampos.get(1).getText().toString());
				periodo.setHoraFim(horaFim);
				
				periodoDao.gravarPeriodo(periodo);
			}
			dia++;
		}
	}
	
	private void editarHorarios() {
		PeriodoDAO periodoDao = new PeriodoDAO();
		periodoDao.excluirPeriodo(idMedicoSelecionado);
		salvarHorarios();
	}
	
	private ArrayList<JTextField> getTextField (int dia) {
		ArrayList<JTextField> listaArrays = new ArrayList<>();
		switch (dia) {
		case 0:
			listaArrays.add(txtDomingoInicio);
			listaArrays.add(txtDomingoFim);
			break;
		case 1:
			listaArrays.add(txtSegundaInicio);
			listaArrays.add(txtSegundaFim);
			break;
		case 2:
			listaArrays.add(txtTercaInicio);
			listaArrays.add(txtTercaFim);
			break;
		case 3:
			listaArrays.add(txtQuartaInicio);
			listaArrays.add(txtQuartaFim);
			break;
		case 4:
			listaArrays.add(txtQuintaInicio);
			listaArrays.add(txtQuintaFim);
			break;
		case 5:
			listaArrays.add(txtSextaInicio);
			listaArrays.add(txtSextaFim);
			break;
		case 6:
			listaArrays.add(txtSabadoInicio);
			listaArrays.add(txtSabadoFim);
			break;
		}
		return listaArrays;
	}
	
	private void habilitarCampos (int dia) {
		switch (dia) {
		case 0:
			txtDomingoInicio.setEnabled(true);
			txtDomingoFim.setEnabled(true);
			break;
		case 1:
			txtSegundaInicio.setEnabled(true);
			txtSegundaFim.setEnabled(true);
			break;
		case 2:
			txtTercaInicio.setEnabled(true);
			txtTercaFim.setEnabled(true);
			break;
		case 3:
			txtQuartaInicio.setEnabled(true);
			txtQuartaFim.setEnabled(true);
			break;
		case 4:
			txtQuintaInicio.setEnabled(true);
			txtQuintaFim.setEnabled(true);
			break;
		case 5:
			txtSextaInicio.setEnabled(true);
			txtSextaFim.setEnabled(true);
			break;
		case 6:
			txtSabadoInicio.setEnabled(true);
			txtSabadoFim.setEnabled(true);
			break;
		}
	}
	
	private void desabilitarCampos() {
		txtDomingoInicio.setEnabled(false);
		txtDomingoFim.setEnabled(false);
		txtSegundaInicio.setEnabled(false);
		txtSegundaFim.setEnabled(false);
		txtTercaInicio.setEnabled(false);
		txtTercaFim.setEnabled(false);
		txtQuartaInicio.setEnabled(false);
		txtQuartaFim.setEnabled(false);
		txtQuintaInicio.setEnabled(false);
		txtQuintaFim.setEnabled(false);
		txtSextaInicio.setEnabled(false);
		txtSextaFim.setEnabled(false);
		txtSabadoInicio.setEnabled(false);
		txtSabadoFim.setEnabled(false);
	}
	
	private void mudarModo(int modo) {
		this.modoAtual = modo;
		if(modoAtual == MODO_VISUALIZAR) {
			//desabilitar os campos
			lblEspecialidades.setVisible(true);
			lblModo.setText("Visualizar");
			btnSalvar.setVisible(false);
			btnAdicionarEsp.setVisible(false);
			btnExcluirEsp.setVisible(false);
		} else if (modoAtual == MODO_CADASTRAR) {
			btnSalvar.setVisible(true);
			lblModo.setText("Cadastro");
			txtNomeMedico.setEnabled(true);
			PeriodoDAO periodoDAO = new PeriodoDAO();
			ArrayList<Integer> listaDias = periodoDAO .listarDias(idMedicoSelecionado);
			for (Integer dia : listaDias) {
				habilitarCampos(dia);
			}
			limparCampos();
			if (scrollEspecialidades != null) {
				detalhesMedicos.remove(scrollEspecialidades);
			}
			lblEspecialidades.setVisible(false);
			desabilitarCampos();
			btnAdicionarEsp.setVisible(false);
			btnExcluirEsp.setVisible(false);
		} else if(modoAtual == MODO_EDITAR) {
			lblEspecialidades.setVisible(true);
			preencherCampos(idMedicoSelecionado);
			btnSalvar.setVisible(true);
			lblModo.setText("Edição");
			txtNomeMedico.setEnabled(true);
			PeriodoDAO periodoDAO = new PeriodoDAO();
			ArrayList<Integer> listaDias = periodoDAO .listarDias(idMedicoSelecionado);
			for (Integer dia : listaDias) {
				habilitarCampos(dia);
			}
			tabbedPane.setSelectedIndex(1);

			btnAdicionarEsp.setVisible(true);
			btnExcluirEsp.setVisible(true);
		}
	}
}
