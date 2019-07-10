package pt.ismt.gestordetarefas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {

    private BaseDeDados db;
    private WS _server;
    private ArrayList<HashMap<String, String>> _listaTarefas;
    private ListView _lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Aguarde...", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent dados = new Intent(ListActivity.this, MainActivity.class);
                startActivity(dados);
            }
        });

        //Solução 1 - Utilizando BD Local
        //db = new BaseDeDados(this); //base de dados
        //_listaTarefas = db.getListaTarefas(); //lista de dados
        //_lv = findViewById(R.id.lv_tarefas); //listView

        /*ListAdapter adapter = new SimpleAdapter(this, _listaTarefas, R.layout.listview_row,
                new String[]{"idtarefa", "data", "descricao", "nome"},
                new int[]{R.id.tv_idTarefa, R.id.tv_nomeTarefa, R.id.tv_descricaoTarefa, R.id.tv_dataTarefa});
        _lv.setAdapter(adapter);*/

        //Solução 2 - Utilizando WS
        _server = new WS();
        _server.activity = ListActivity.this;
        _server._listaTarefas = new ArrayList<>();
        _server._lv = findViewById(R.id.lv_tarefas);
        _server.execute();
    }
}
