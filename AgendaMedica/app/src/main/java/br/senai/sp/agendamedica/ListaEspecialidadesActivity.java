package br.senai.sp.agendamedica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.senai.sp.model.Especialidade;

public class ListaEspecialidadesActivity extends AppCompatActivity {
    ListView layoutListaEspecialidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_especialidades);
        layoutListaEspecialidades = (ListView) findViewById(R.id.lista_especialidades_lista);
        popularListaEspecialidades();
        layoutListaEspecialidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Especialidade especialidade = (Especialidade) layoutListaEspecialidades.getItemAtPosition(position);
                Intent irParaListaMedicos = new Intent(ListaEspecialidadesActivity.this, ListaMedicosActivity.class);
                irParaListaMedicos.putExtra("especialidade", especialidade);
                startActivity(irParaListaMedicos);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_especialidades, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_especialidades_atualizar:
                popularListaEspecialidades();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void popularListaEspecialidades() {
        try {
            String resultado = new ReceberEspecialidades(this).execute().get();
            List<Especialidade> listaEspecialidades = Converter.jsonParaListaEspecialidades(resultado);
            ArrayAdapter<Especialidade> adapter = new ArrayAdapter<Especialidade>(ListaEspecialidadesActivity.this,
                    android.R.layout.simple_list_item_1, listaEspecialidades);
            layoutListaEspecialidades.setAdapter(adapter);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
