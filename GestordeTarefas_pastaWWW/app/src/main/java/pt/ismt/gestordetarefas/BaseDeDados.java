package pt.ismt.gestordetarefas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BaseDeDados extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "myDB";
    public static final int DATABASE_VERSION = 1;

    private static final String TABLE_TAREFAS = "tarefas"; // nome da tabela

    // Campos da tabela
    private static final String KEY_ID = "idtarefa";
    private static final String KEY_NAME = "nome";
    private static final String KEY_DESCRIPTION = "descricao";
    private static final String KEY_DATA = "data";

    /**
     * CONSTRUCTOR
     */
    public BaseDeDados(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * É INVOCADA QUANDO A BD É CRIADA PELA PRIMEIRA VEZ
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_TAREFAS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_DATA + " TEXT" + ")";

        db.execSQL(query);
    }

    /**
     * É INVOCADA QUANDO A VERSÃO DA BD É MAIOR QUE A VERSÃO ATUAL NO DISPOSITIVO
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAREFAS);
        onCreate(db);
    }

    // ADICIONA NOVA TAREFA
    long adicionaTarefa(Tarefa _task) {
        SQLiteDatabase db = this.getWritableDatabase();

        // valores a inserir na registo da tabela
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, _task.getNomeTarefa());
        values.put(KEY_DESCRIPTION, _task.getDescricaoTarefa());
        values.put(KEY_DATA, _task.getDataTarefa().toString());

        // Inserting Row
        long id = db.insert(TABLE_TAREFAS, null, values);
        db.close();

        return id;
    }

    ArrayList<HashMap<String,String>> getListaTarefas() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor dados = db.rawQuery("SELECT * FROM "+TABLE_TAREFAS, null);

        ArrayList<HashMap<String, String>> listaTarefas = new ArrayList<HashMap<String, String>>();
        if (dados.moveToFirst()){
            while(!dados.isAfterLast()){
                HashMap<String, String> tarefa = new HashMap();
                tarefa.put(KEY_ID, dados.getString(dados.getColumnIndex(KEY_ID)));
                tarefa.put(KEY_NAME, dados.getString(dados.getColumnIndex(KEY_NAME)));
                tarefa.put(KEY_DESCRIPTION, dados.getString(dados.getColumnIndex(KEY_DESCRIPTION)));
                tarefa.put(KEY_DATA, dados.getString(dados.getColumnIndex(KEY_DATA)));
                listaTarefas.add(tarefa);
                dados.moveToNext();
            }
        }
        dados.close();

        return listaTarefas;
    }
}