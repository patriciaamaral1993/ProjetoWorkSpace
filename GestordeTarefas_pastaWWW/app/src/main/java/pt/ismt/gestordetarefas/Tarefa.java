package pt.ismt.gestordetarefas;

import java.sql.Date;

public class Tarefa {

    //propriedades
    String _idTarefa;
    String _nomeTarefa, _descricaoTarefa;
    String _dataTarefa;

    //construtor
    public Tarefa(String id, String nome, String descricao, String data) {
        _idTarefa = id;
        _nomeTarefa = nome;
        _descricaoTarefa = descricao;
        _dataTarefa = data;
    }

    //getters e setters
    public String getIdTarefa() { return _idTarefa; }
    public void setIdTarefa(String id) { _idTarefa = id; }

    public String getNomeTarefa() { return _nomeTarefa; }
    public void setNomeTarefa(String nome) { _nomeTarefa = nome; }

    public String getDescricaoTarefa() { return _descricaoTarefa; }
    public void setDescricaoTarefa(String descricao) { _descricaoTarefa = descricao;}

    public String getDataTarefa() { return _dataTarefa; }
    public void setDataTarefa(String data) { _dataTarefa = data; }
}