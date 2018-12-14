package br.senai.sp.agendamedica;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class ReceberMedicos extends AsyncTask<Void, Void, String> {

    Context context;
    int idEspecialidade;
    private ProgressDialog alertDialog;

    public ReceberMedicos(Context context, int idEspecialidade){
        this.context = context;
        this.idEspecialidade = idEspecialidade;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        alertDialog = ProgressDialog.show(context,"Aguarde" , "Baixando os médicos...", true, true);
        alertDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
//        AlunoDAO dao = new AlunoDAO(context);
//        List<Aluno> lista = dao.listarAlunos();
//
//        AlunoConverter converter = new AlunoConverter();
//        String json = converter.toJson(lista);

        WebClientMedicos client = new WebClientMedicos();
        String resposta = client.post(idEspecialidade);

//        dao.close();

        return resposta;
    }

    @Override
    protected void onPostExecute(String s) {
        alertDialog.dismiss();
//        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
