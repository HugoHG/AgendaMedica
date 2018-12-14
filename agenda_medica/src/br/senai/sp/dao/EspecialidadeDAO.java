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

public class EspecialidadeDAO {
	
	public ArrayList<Especialidade> listar(){
		Connection con = DbHelper.getConexao();
		ArrayList<Especialidade> especialidades = new ArrayList<>();
		
		String sql = "SELECT * FROM especialidade";
		
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				Especialidade especialidade = new Especialidade();
				
				especialidade.setIdEspecialidade(rs.getInt("idEspecialidade"));
				especialidade.setNomeEspecialidade(rs.getString("nomeEspecialidade"));
				
				especialidades.add(especialidade);
			}
			
			DbHelper.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return especialidades;
	}
	
	public ArrayList<Especialidade> listar(int idMedico){
		Connection con = DbHelper.getConexao();
		ArrayList<Integer> listaIds = listarIds(idMedico);
		ArrayList<Especialidade> especialidades = new ArrayList<>();
		
		for (Integer idEspecialidade : listaIds) {
			String sql = "SELECT * FROM especialidade WHERE idEspecialidade = ?";
			
			try {
				PreparedStatement stm = con.prepareStatement(sql);
				stm.setInt(1, idEspecialidade);
				ResultSet rs = stm.executeQuery();
				
				while (rs.next()){
					Especialidade especialidade = new Especialidade();
					
					especialidade.setIdEspecialidade(idEspecialidade);
					especialidade.setNomeEspecialidade(rs.getString("nomeEspecialidade"));
					
					especialidades.add(especialidade);
				}
				
				DbHelper.fecharConexao();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return especialidades;
	}
	
	private ArrayList<Integer> listarIds(int idMedico){
		Connection con = DbHelper.getConexao();
		ArrayList<Integer> idsEspecialidades = new ArrayList<>();
		
		String sql = "SELECT * FROM especialidade_medico WHERE idMedico = ?";
		
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, idMedico);
			ResultSet rs = stm.executeQuery();
			
			while (rs.next()){
				Especialidade especialidade = new Especialidade();
				
				especialidade.setIdEspecialidade(rs.getInt("idEspecialidade"));
				
				idsEspecialidades.add(especialidade.getIdEspecialidade());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return idsEspecialidades;
	}
	
	public void gravar(Especialidade especialidade){
		Connection con = DbHelper.getConexao();
		String sql = "INSERT INTO especialidade (nomeEspecialidade) values (?)";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, especialidade.getNomeEspecialidade());
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro na gravação da especialidade.\nEntre em contato com o Administrador do Sistema",
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Especialidade gravada com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}
			DbHelper.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void excluirEspecialidadeMedico(int idMedico) {
		// TODO Auto-generated method stub
		Connection con = DbHelper.getConexao();
		String sql = "DELETE FROM especialidade_medico WHERE idMedico = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			
			stm.setInt(1, idMedico);
			System.out.println(stm);
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro na exclusão dos períodos.\n Entre em contato com o administrador", "Erro",
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
	
	public void excluirEspecialidadeMedicoPorIdEspecialidade(int idEspecialidade) {
		// TODO Auto-generated method stub
		Connection con = DbHelper.getConexao();
		String sql = "DELETE FROM especialidade_medico WHERE idEspecialidade = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			
			stm.setInt(1, idEspecialidade);
			System.out.println(stm);
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro na exclusão dos períodos da especialidade.\n Entre em contato com o administrador", "Erro",
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
	
	public void excluirEspecialidade(int idEspecialidade) {
		// TODO Auto-generated method stub
		Connection con = DbHelper.getConexao();
		String sql = "DELETE FROM especialidade WHERE idEspecialidade = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			
			stm.setInt(1, idEspecialidade);
			System.out.println(stm);
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro na exclusão dos períodos.\n Entre em contato com o administrador", "Erro",
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
	
	public void editarEspecialidade(Especialidade especialidade) {
		Connection con = DbHelper.getConexao();
		String sql = "UPDATE especialidade SET nomeEspecialidade = ? WHERE idEspecialidade = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, especialidade.getNomeEspecialidade());
			stm.setInt(2, especialidade.getIdEspecialidade());
			if (stm.execute()) {
				JOptionPane.showMessageDialog(null,
						"Ocorreu um erro na edição da especialidade.\nEntre em contato com o Administrador do Sistema",
						"Erro", JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Especialidade atualizada com sucesso", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
			}
			DbHelper.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Especialidade getEspecialidade(int idEspecialidade) {
		Connection con = DbHelper.getConexao();
		Especialidade especialidade = new Especialidade();

		String sql = "SELECT * FROM especialidade WHERE idEspecialidade = ?";

		try {
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, idEspecialidade);
			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				
				especialidade.setIdEspecialidade(rs.getInt("idEspecialidade"));
				especialidade.setNomeEspecialidade(rs.getString("nomeEspecialidade"));
			}

			DbHelper.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return especialidade;
	}
}
