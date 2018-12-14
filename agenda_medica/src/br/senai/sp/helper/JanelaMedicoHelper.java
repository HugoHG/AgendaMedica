package br.senai.sp.helper;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

public class JanelaMedicoHelper {
	public static DefaultTableModel modeloVazioTabelaEspecialidades() {
		DefaultTableModel modeloEspec = new DefaultTableModel();
		modeloEspec.addColumn("id");
		modeloEspec.addColumn("Especialidade");
		modeloEspec.addRow(new Object[] {"", ""});
		
		return modeloEspec;
	}
	
	public static Time getTime(String horaString) {
		String[] arrayHora = horaString.split(":");
		String stringHora = arrayHora[0];
		String stringMinutos = arrayHora[1];
		int intHora = Integer.parseInt(stringHora)-3;
		String horaCompleta = String.valueOf(intHora)+":"+stringMinutos+":00";
		
		DateFormat formatador = new SimpleDateFormat("HH:mm:ss");
		
		Date date = null;
		try {
			date = formatador.parse(horaCompleta);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Time(date.getTime());
	}
}
