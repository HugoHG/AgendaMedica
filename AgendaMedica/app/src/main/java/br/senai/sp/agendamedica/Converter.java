package br.senai.sp.agendamedica;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.senai.sp.model.Especialidade;
import br.senai.sp.model.Medico;

public class Converter {
    Context context;

//    public Converter(Context context){
//        this.context = context;
//    }

    public static List<Especialidade> jsonParaListaEspecialidades(String json) {
        List<Especialidade> listaEspecialidades = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject objeto = array.getJSONObject(i);
                Especialidade especialidade = new Especialidade();
                especialidade.setIdEspecialidade(Integer.parseInt(objeto.get("id").toString()));
                especialidade.setNomeEspecialidade(objeto.get("nome").toString());
                listaEspecialidades.add(especialidade);
            }
//            for(Especialidade especialidade : listaEspecialidades){
//                Toast.makeText(context, especialidade.getIdEspecialidade() + especialidade.getNomeEspecialidade(), Toast.LENGTH_SHORT).show();
//            }
//            JSONObject objeto = array.getJSONObject(1);
//            Toast.makeText(context, objeto.get("nome").toString(), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaEspecialidades;
    }

    public static List<Medico> jsonParaListaMedicos(String json) {
        List<Medico> listaMedicos = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject objeto = array.getJSONObject(i);
                Medico medico = new Medico();
                medico.setIdMedico(Integer.parseInt(objeto.get("id").toString()));
                medico.setNomeMedico(objeto.get("nome").toString());
                listaMedicos.add(medico);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listaMedicos;
    }
}
