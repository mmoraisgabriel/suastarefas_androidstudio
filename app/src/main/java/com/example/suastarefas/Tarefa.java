package com.example.suastarefas;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class Tarefa {

    public static ArrayList<Tarefa> tarefaArrayList = new ArrayList<>();
    public static String TAREFA_EDIT_EXTRA = "tarefaEdit";

    private int id;
    private String titulo;
    private String descricao;
    private Date deletado;

    public Tarefa(int id, String titulo, String descricao, Date deletado) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.deletado = deletado;
    }

    public Tarefa(int id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        deletado = null;
    }

    public static Tarefa getTarefaForID(int passedTarefaID) {
        for (Tarefa tarefa : tarefaArrayList){
            if (tarefa.getId() == passedTarefaID) {
                return tarefa;
            }
        }
        return null;
    }

    public static ArrayList<Tarefa> tarefasNaoDeletadas(){
        ArrayList<Tarefa> naoDeletada = new ArrayList<>();
        for (Tarefa tarefa : tarefaArrayList){
            if (tarefa.getDeletado() == null) {
                naoDeletada.add(tarefa);
            }
        }

        return naoDeletada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDeletado() {
        return deletado;
    }

    public void setDeletado(Date deletado) {
        this.deletado = deletado;
    }
}
