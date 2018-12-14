import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.senai.sp.dao.PeriodoDAO;
import br.senai.sp.model.Periodo;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DateFormat formatador = new SimpleDateFormat("HH:mm:ss");
		Date dateHoraInicio = null;
		Date dateHoraFim = null;
		try {
			dateHoraInicio = formatador.parse("17:30:00");
			dateHoraFim = formatador.parse("22:45:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Time horaInicio = new Time(dateHoraInicio.getTime());
		Time horaFim = new Time(dateHoraFim.getTime());
		
		System.out.println(horaInicio.toString());
	}

}
