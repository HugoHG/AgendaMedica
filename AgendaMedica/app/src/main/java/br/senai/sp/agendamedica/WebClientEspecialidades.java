package br.senai.sp.agendamedica;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class WebClientEspecialidades {
    public String post(/*String json*/){
        try {
            URL url = new URL("http://192.168.1.3/apiAgendaMedica/listarEspecialidades.php");
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
