package br.senai.sp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.senai.sp.helper.DbHelper;
import br.senai.sp.model.Medico;

public class MedicoDAO {
	
	public ArrayList<Medico> listarMedicos() {
		Connection con = DbHelper.getConexao();
		ArrayList<Medico> listaDeMedicos = new ArrayList<>();

		String sql = "SELECT * FROM medico";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				Medico medico = new Medico();
				
				medico.setIdMedico(rs.getInt("idMedico"));
				medico.setNomeMedico(rs.getString("nomeMedico"));

				listaDeMedicos.add(medico);
			}

			DbHelper.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaDeMedicos;
	}
	
	public void gravarMedico(Medico medico) {
		Connection con = DbHelper.getConexao();
		String sql = "INSERT INTO medico (nomeMedico) values (?)";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, medico.getNomeMedico());
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro na gravação do médico.\nEntre em contato com o Administrador do Sistema",
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Médico salvo com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}
			DbHelper.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Medico getMedico(int idMedico) {
		Connection con = DbHelper.getConexao();
		Medico medico = new Medico();

		String sql = "SELECT * FROM medico where idMedico = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, idMedico);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				
				medico.setIdMedico(idMedico);
				medico.setNomeMedico(rs.getString("nomeMedico"));
			}

			DbHelper.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return medico;
	}
	
	public int getUltimoMedico() {
		Connection con = DbHelper.getConexao();
		int idMedico = 0;

		String sql = "SELECT MAX(idMedico) FROM medico";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				
				idMedico = rs.getInt("max(idMedico)");
			}

			DbHelper.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return idMedico;
	}
	
	public void editarMedico(Medico medico) {
		Connection con = DbHelper.getConexao();
		String sql = "UPDATE medico SET nomeMedico = ? WHERE idMedico = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, medico.getNomeMedico());
			stm.setInt(2, medico.getIdMedico());
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro na edição do médico.\nEntre em contato com o Administrador do Sistema",
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Médico atualizado com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}
			DbHelper.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void excluirMedico(int idMedicoSelecionado) {
		// TODO Auto-generated method stub
		Connection con = DbHelper.getConexao();
		String sql = "DELETE FROM medico WHERE idMedico = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			
			stm.setInt(1, idMedicoSelecionado);
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro na exclusão do médico.\n Entre em contato com o administrador", "Erro",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso.", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}
			DbHelper.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void adicionarEspecialidade(int idMedico, int idEspecialidade) {
		Connection con = DbHelper.getConexao();
		String sql = "INSERT INTO especialidade_medico (idEspecialidade, idMedico) values (?, ?)";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, idEspecialidade);
			stm.setInt(2, idMedico);
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro na atribuição da especialidade.\nEntre em contato com o Administrador do Sistema",
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Especialidade atribuída com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}
			DbHelper.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removerEspecialidade(int idMedico, int idEspecialidade) {
		Connection con = DbHelper.getConexao();
		String sql = "DELETE FROM especialidade_medico WHERE idEspecialidade = ? AND idMedico = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, idEspecialidade);
			stm.setInt(2, idMedico);
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro na remoção da especialidade.\nEntre em contato com o Administrador do Sistema",
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Especialidade removida com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}
			DbHelper.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
