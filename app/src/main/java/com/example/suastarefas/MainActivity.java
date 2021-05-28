package com.example.suastarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView tarefasListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        loadFromDBToMemory();
        setTarefaAdapter();
        setOnClickListener();
    }

    private void initWidgets() {
        tarefasListView = findViewById(R.id.tarefasListView);
    }

    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateTarefaListArray();
    }

    private void setTarefaAdapter() {
        TarefaAdapter tarefaAdapter = new TarefaAdapter(getApplicationContext(), Tarefa.tarefasNaoDeletadas());
        tarefasListView.setAdapter(tarefaAdapter);
    }

    private void setOnClickListener()
    {
        tarefasListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l)
            {
                Tarefa tarefaSelecionada = (Tarefa) tarefasListView.getItemAtPosition(posicao);
                Intent editTarefaIntent = new Intent(getApplicationContext(), DetalheTarefaActivity.class);
                editTarefaIntent.putExtra(Tarefa.TAREFA_EDIT_EXTRA, tarefaSelecionada.getId());
                startActivity(editTarefaIntent);
            }
        });
    }

    public void novaTarefa(View view) {
        Intent newTarefaIntent = new Intent(this, DetalheTarefaActivity.class);
        startActivity(newTarefaIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTarefaAdapter();
    }
}