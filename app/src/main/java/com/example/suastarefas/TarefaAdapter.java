package com.example.suastarefas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TarefaAdapter extends ArrayAdapter<Tarefa> {

    public TarefaAdapter(Context context, List<Tarefa> tarefas){
        super(context, 0, tarefas);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Tarefa tarefa = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tarefa_celula, parent, false);

            TextView titulo = convertView.findViewById(R.id.celulaTitulo);
            TextView descricao = convertView.findViewById(R.id.celulaDescricao);

            titulo.setText(tarefa.getTitulo());
            descricao.setText(tarefa.getDescricao());
        }
        return convertView;
    }
}
