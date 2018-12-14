package br.senai.sp.agendamedica;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebClientMedicos {
    public String post(int idEspecialidade){
        try {
            String strUrl = "http://192.168.1.3/apiAgendaMedica/listarMedicos.php?idEspecialidade="+idEspecialidade;
            URL url = new URL(strUrl);
            Log.d("stringdaurl", strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

//            connection.setRequestProperty("Accept", "application/json");
//            connection.setRequestProperty("Content-type", "application/json");

            connection.setDoInput(true);
            connection.setDoOutput(true);

            //PrintStream saida = new PrintStream(connection.getOutputStream());
//            saida.println(json);
            //String stringSaida = saida;

            connection.connect();

            String resposta = new Scanner(connection.getInputStream()).next();

            return resposta;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
