package pt.ismt.gestordetarefas;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class WS extends AsyncTask<Void, Void, Void> {
    public ListActivity activity;
    private ProgressDialog pDialog;
    public ListView _lv;
    public ArrayList<HashMap<String, String>> _listaTarefas;

    @Override
    protected void onPreExecute(){
        super.onPreExecute();

        //mostra uma barra de carregamento
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Aguarde...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpURLConnection conexao;
        InputStream is;
        String jsonStr;

        try {
            //URL _endpoint = new URL("http://localhost/ws/public/api/tarefa");
            URL _endpoint = new URL("http://10.0.2.2/ws/public/api/tarefa");
            conexao = (HttpURLConnection) _endpoint.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.connect();

            int _httpCode = conexao.getResponseCode();
            if (_httpCode != HttpURLConnection.HTTP_BAD_REQUEST) {
                is = conexao.getInputStream();
            } else {
                is = conexao.getErrorStream();
            }
            jsonStr = inputStreamToString(is);

            int count;
            if (jsonStr != null) {
                try {
                    JSONArray prs = new JSONArray(jsonStr);

                    for (int i = 0; i < prs.length(); i++)
                    {
                        JSONObject obj = prs.getJSONObject(i);
                        String id = obj.getString("id");
                        String nome = obj.getString("nome");
                        String descricao = obj.getString("descricao");
                        String data = obj.getString("data");

                        //lista de propriedades
                        HashMap<String, String> tarefa = new HashMap();
                        tarefa.put("idtarefa", String.valueOf(id));
                        tarefa.put("nome", String.valueOf(nome));
                        tarefa.put("descricao", String.valueOf(descricao));
                        tarefa.put("data", data);
                        _listaTarefas.add(tarefa);
                    }
                } catch (final JSONException e) {
                    Log.d("Erro", "doInBackground: " + e.getMessage());
                }
            }
            is.close();
            conexao.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String inputStreamToString(InputStream is){
        StringBuffer buffer = new StringBuffer();
        try{
            BufferedReader br;
            String linha;

            br = new BufferedReader(new InputStreamReader(is));
            while((linha = br.readLine())!=null){
                buffer.append(linha);
            }

            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return buffer.toString();
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();

        ListAdapter adapter = new SimpleAdapter(activity, _listaTarefas, R.layout.listview_row,
                new String[]{"idtarefa", "nome", "descricao", "data"},
                new int[]{R.id.tv_idTarefa, R.id.tv_nomeTarefa, R.id.tv_descricaoTarefa, R.id.tv_dataTarefa});
        _lv.setAdapter(adapter);
    }
}