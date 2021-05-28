package com.example.suastarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class DetalheTarefaActivity extends AppCompatActivity {

    private EditText tituloEditarTexto, descricaoEditarTexto;
    private Button botaoDeletar;
    private Tarefa tarefaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_tarefa);
        initWidgets();
        checkForEditTarefa();
    }

    private void initWidgets() {
        tituloEditarTexto = findViewById(R.id.tituloEditarTexto);
        descricaoEditarTexto = findViewById(R.id.descricaoEditarTexto);
        botaoDeletar = findViewById(R.id.deletarTarefa);
    }

    private void checkForEditTarefa() {
        Intent previousIntent = getIntent();

        int passedTarefaID = previousIntent.getIntExtra(Tarefa.TAREFA_EDIT_EXTRA, -1);
        tarefaSelecionada = Tarefa.getTarefaForID(passedTarefaID);

        if (tarefaSelecionada != null) {
            tituloEditarTexto.setText(tarefaSelecionada.getTitulo());
            descricaoEditarTexto.setText(tarefaSelecionada.getDescricao());
        } else {
            botaoDeletar.setVisibility(View.INVISIBLE);
        }
    }

    public void salvarTarefa(View view) {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String titulo = String.valueOf(tituloEditarTexto.getText());
        String descricao = String.valueOf(descricaoEditarTexto.getText());

        if (tarefaSelecionada == null) {
            int id = Tarefa.tarefaArrayList.size();
            Tarefa novaTarefa = new Tarefa(id, titulo, descricao);
            Tarefa.tarefaArrayList.add(novaTarefa);
            sqLiteManager.addTarefaToDatabase(novaTarefa);
        } else {
            tarefaSelecionada.setTitulo(titulo);
            tarefaSelecionada.setDescricao(descricao);
            sqLiteManager.updateTarefaInDB(tarefaSelecionada);
        }
        finish();
    }

    public void deletarTarefa(View view) {
        tarefaSelecionada.setDeletado(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateTarefaInDB(tarefaSelecionada);
        finish();
    }
}