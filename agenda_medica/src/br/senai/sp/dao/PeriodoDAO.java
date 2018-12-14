package br.senai.sp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sp.helper.DbHelper;
import br.senai.sp.model.Especialidade;
import br.senai.sp.model.Medico;
import br.senai.sp.model.Periodo;

public class PeriodoDAO {
	public void gravarPeriodo(Periodo periodo) {
		Connection con = DbHelper.getConexao();
		String sql = "INSERT INTO periodo (idMedico, diaSemana, horaInicio, horaFim) values (?,?,?,?)";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, periodo.getIdMedico());
			stm.setInt(2, periodo.getDiaSemana());
			stm.setTime(3, periodo.getHoraInicio());
			stm.setTime(4, periodo.getHoraFim());
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro na gravação dos períodos.\nEntre em contato com o Administrador do Sistema",
						"Erro", JOptionPane.ERROR_MESSAGE);
			}
			DbHelper.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> listarDias(int idMedico){
		Connection con = DbHelper.getConexao();
		ArrayList<Integer> dias = new ArrayList<>();
		
		String sql = "SELECT * FROM periodo WHERE idMedico = ?";
		
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, idMedico);
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				dias.add(rs.getInt("diaSemana"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbHelper.fecharConexao();
		return dias;
	}
	
	public Periodo getPeriodo(int idMedico, int diaSemana) {
		Connection con = DbHelper.getConexao();
		Periodo periodo = new Periodo();
		
		String sql = "SELECT * FROM periodo WHERE idMedico = ? AND diaSemana = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, idMedico);
			stm.setInt(2, diaSemana);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				periodo.setIdMedico(idMedico);
				periodo.setDiaSemana(diaSemana);
				periodo.setHoraInicio(rs.getTime("horaInicio"));
				periodo.setHoraFim(rs.getTime("horaFim"));
			}

			DbHelper.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return periodo;
	}
	
	public void excluirPeriodo(int idMedico) {
		// TODO Auto-generated method stub
		Connection con = DbHelper.getConexao();
		String sql = "DELETE FROM periodo WHERE idMedico = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			
			stm.setInt(1, idMedico);
			System.out.println(stm);
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro na exclusão dos períodos.\n Entre em contato com o administrador", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
			DbHelper.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
