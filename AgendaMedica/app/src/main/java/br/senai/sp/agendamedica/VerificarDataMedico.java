package br.senai.sp.agendamedica;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class VerificarDataMedico extends AsyncTask<Void, Void, String> {

    Context context;
    String horario;
    private ProgressDialog alertDialog;
    int idMedico;
    int diaSemana;

    public VerificarDataMedico(Context context, String horario, int idMedico, int diaSemana){
        this.context = context;
        this.horario = horario;
        this.idMedico = idMedico;
        this.diaSemana = diaSemana;
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

        WebClientDataMedico webClientDataMedico = new WebClientDataMedico();
        String resposta = webClientDataMedico.post(horario, idMedico, diaSemana);

//        dao.close();

        return resposta;
    }

    @Override
    protected void onPostExecute(String s) {
        alertDialog.dismiss();
//        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
