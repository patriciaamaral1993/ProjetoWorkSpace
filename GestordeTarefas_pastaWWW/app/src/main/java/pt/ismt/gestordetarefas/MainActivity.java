package pt.ismt.gestordetarefas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    BaseDeDados db;
    EditText etNome, etDescricao, etData;
    Button btnInserirTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new BaseDeDados(this);
        etNome = findViewById(R.id.et_nome);
        etDescricao = findViewById(R.id.et_descricao);
        etData = findViewById(R.id.et_data);
        etData.setText(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));

        btnInserirTarefa = findViewById(R.id.btnInserirTarefa);
        btnInserirTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdicionarTarefa(etNome.getText().toString(), etDescricao.getText().toString(), etData.toString());
            }
        });
    }

    public void AdicionarTarefa(String _nome, String _descricao, String _data) {
        long idCriado = db.adicionaTarefa(new Tarefa("0", _nome, _descricao, _data));

        if (idCriado != 0){
            Toast toast = Toast.makeText(getApplicationContext(), "Tarefa "+ idCriado +" inserido com sucesso na BD!", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
