package br.senai.sp.agendamedica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.senai.sp.model.Especialidade;
import br.senai.sp.model.Medico;

public class ListaMedicosActivity extends AppCompatActivity {
    int idEspecialidade;
    ListView layoutListaMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicos);
        layoutListaMedicos = (ListView) findViewById(R.id.lista_medicos_lista);

        Especialidade especialidade = (Especialidade) getIntent().getSerializableExtra("especialidade");
        idEspecialidade = especialidade.getIdEspecialidade();

        popularListaMedicos();

        layoutListaMedicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Medico medico = (Medico) layoutListaMedicos.getItemAtPosition(position);
                Intent intent = new Intent(ListaMedicosActivity.this, CadastroConsultaActivity.class);
                intent.putExtra("medico", medico);
                startActivity(intent);
            }
        });
    }

    public void popularListaMedicos(){
        try {
            String resultado = new ReceberMedicos(this, idEspecialidade).execute().get();
            List<Medico> listaMedicos = Converter.jsonParaListaMedicos(resultado);
            ArrayAdapter<Medico> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaMedicos);
            layoutListaMedicos.setAdapter(adapter);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
