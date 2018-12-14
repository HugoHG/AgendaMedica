package br.senai.sp.agendamedica;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class VerificarDataConsulta extends AsyncTask<Void, Void, String> {

    Context context;
    String horario;
    private ProgressDialog alertDialog;
    int idMedico;
    String data;

    public VerificarDataConsulta(Context context, int idMedico, String data, String horario){
        this.context = context;
        this.idMedico = idMedico;
        this.data = data;
        this.horario = horario;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        alertDialog = ProgressDialog.show(context,"Aguarde" , "Verificando o horário do médico...", true, true);
        alertDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
//        AlunoDAO dao = new AlunoDAO(context);
//        List<Aluno> lista = dao.listarAlunos();
//
//        AlunoConverter converter = new AlunoConverter();
//        String json = converter.toJson(lista);

        WebClientDataConsulta webClientDataConsulta = new WebClientDataConsulta();
        String resposta = webClientDataConsulta.post(idMedico, data, horario);

//        dao.close();

        return resposta;
    }

    @Override
    protected void onPostExecute(String s) {
        alertDialog.dismiss();
//        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
