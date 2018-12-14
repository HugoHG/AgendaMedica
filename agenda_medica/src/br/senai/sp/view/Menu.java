package br.senai.sp.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnMedicos = new JButton("Medicos");
		btnMedicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaMedicos listaMedicos = new ListaMedicos();
				listaMedicos.setVisible(true);
			}
		});
		btnMedicos.setBounds(38, 123, 89, 23);
		contentPane.add(btnMedicos);
		
		JButton btnEspecialidades = new JButton("Especialidades");
		btnEspecialidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListaEspecialidades listaEspecialidades = new ListaEspecialidades();
				listaEspecialidades.setVisible(true);
			}
		});
		btnEspecialidades.setBounds(298, 123, 101, 23);
		contentPane.add(btnEspecialidades);
	}
}
