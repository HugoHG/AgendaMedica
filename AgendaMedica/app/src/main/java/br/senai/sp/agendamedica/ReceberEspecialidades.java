package br.senai.sp.agendamedica;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class ReceberEspecialidades extends AsyncTask<Void, Void, String> {

    Context context;
    private ProgressDialog alertDialog;

    public ReceberEspecialidades(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        alertDialog = ProgressDialog.show(context,"Aguarde" , "Baixando as especialidades...", true, true);
        alertDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
//        AlunoDAO dao = new AlunoDAO(context);
//        List<Aluno> lista = dao.listarAlunos();
//
//        AlunoConverter converter = new AlunoConverter();
//        String json = converter.toJson(lista);

        WebClientEspecialidades client = new WebClientEspecialidades();
        String resposta = client.post();

//        dao.close();

        return resposta;
    }

    @Override
    protected void onPostExecute(String s) {
        alertDialog.dismiss();
//        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
