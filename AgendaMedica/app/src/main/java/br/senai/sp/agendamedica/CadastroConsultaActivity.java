package br.senai.sp.agendamedica;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import br.senai.sp.model.Medico;

public class CadastroConsultaActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    EditText layoutNome;
    EditText layoutRg;
    Button botaoData;
    Button botaoHorario;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    int idMedico;
    String nome;
    int rg;
    String dataConsulta;
    String horario;
    int diaSemana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_consulta);

        Intent intent = getIntent();
        Medico medico = (Medico) intent.getSerializableExtra("medico");
        idMedico = medico.getIdMedico();

        layoutNome = findViewById(R.id.campo_consulta_nome);
        layoutRg = findViewById(R.id.campo_consulta_rg);
        botaoData = findViewById(R.id.botao_consulta_data);
        botaoHorario = findViewById(R.id.botao_consulta_horario);

        botaoData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        CadastroConsultaActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String data = year + "-" + month + "-" + day;
                botaoData.setText(data);
                dataConsulta = data;

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
                switch (diaSemana){
                    case 1:
                        diaSemana = 4;
                        break;
                    case 2:
                        diaSemana = 5;
                        break;
                    case 3:
                        diaSemana = 6;
                        break;
                    case 4:
                        diaSemana = 0;
                        break;
                    case 5:
                        diaSemana = 1;
                        break;
                    case 6:
                        diaSemana = 2;
                        break;
                    case 7:
                        diaSemana = 3;
                        break;
                }
            }

        };

        botaoHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro_consulta, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_consulta_salvar:
                nome = layoutNome.getText().toString();
                rg = Integer.parseInt(layoutRg.getText().toString());
                try {
                    String resultadoVerificarMedico = new VerificarDataMedico(CadastroConsultaActivity.this, horario, idMedico, diaSemana).execute().get();
                    if (resultadoVerificarMedico.equals("sucesso")){
                        String resultadoVerificarConsulta = new VerificarDataConsulta(CadastroConsultaActivity.this, idMedico, dataConsulta, horario).execute().get();
                        if (resultadoVerificarConsulta.equals("sucesso")){
                            String resultado = new EnviarConsulta(CadastroConsultaActivity.this, nome, rg, idMedico, dataConsulta, horario).execute().get();
                            if(resultado.equals("sucesso")){
                                Toast.makeText(CadastroConsultaActivity.this, "Consulta agendada com sucesso", Toast.LENGTH_SHORT).show();
                            } else{
                                Toast.makeText(CadastroConsultaActivity.this, "Ocorreu um erro no agendamento da consulta", Toast.LENGTH_SHORT).show();
                            }
                        } else{
                            Toast.makeText(CadastroConsultaActivity.this, "Já há uma consulta agendada para esse horário", Toast.LENGTH_SHORT).show();
                        }
                    } else{
                        Toast.makeText(CadastroConsultaActivity.this, "O médico não está disponível nesse horário", Toast.LENGTH_SHORT).show();
                    }
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(CadastroConsultaActivity.this, "Hora: "+hourOfDay+", minutos: "+minute, Toast.LENGTH_SHORT).show();
        horario = hourOfDay+":"+minute+":"+"00";
        botaoHorario.setText(horario);
    }
}
