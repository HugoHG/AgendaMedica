package br.senai.sp.model;

import java.sql.Time;
import java.util.Date;

public class Consulta {
	private int idConsulta;
	private String nomePaciente;
	private int rgPaciente;
	private int idMedico;
	private Date data;
	private Time horario;
	
	public int getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public int getRgPaciente() {
		return rgPaciente;
	}
	public void setRgPaciente(int rgPaciente) {
		this.rgPaciente = rgPaciente;
	}
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getHorario() {
		return horario;
	}
	public void setHorario(Time horario) {
		this.horario = horario;
	}
}
