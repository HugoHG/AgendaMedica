package br.senai.sp.agendamedica;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class EnviarConsulta extends AsyncTask<Void, Void, String> {

    Context context;
    String nome;
    int rg;
    int idMedico;
    String data;
    String horario;
    private ProgressDialog alertDialog;

    public EnviarConsulta(Context context, String nome, int rg, int idMedico, String data, String horario){
        this.context = context;
        this.nome = nome;
        this.rg = rg;
        this.idMedico = idMedico;
        this.data = data;
        this.horario = horario;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        alertDialog = ProgressDialog.show(context,"Aguarde" , "Enviando a consulta...", true, true);
        alertDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
//        AlunoDAO dao = new AlunoDAO(context);
//        List<Aluno> lista = dao.listarAlunos();
//
//        AlunoConverter converter = new AlunoConverter();
//        String json = converter.toJson(lista);

        WebClientConsulta client = new WebClientConsulta();
        String resposta = client.post(nome, rg, idMedico, data, horario);

//        dao.close();

        return resposta;
    }

    @Override
    protected void onPostExecute(String s) {
        alertDialog.dismiss();
//        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
