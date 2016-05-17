package br.edu.iff.pooa20152.compartmusic;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class compartNuvem extends AppCompatActivity {

    private EditText etcodigo, etnomemusica, ettamanhomusica, etgeneromusica, etartistamusica, etalbummusica;
    private Button btsalvarmusica, btdeletarmusica, btconsultarmusica;
    RestFullHelper http = new RestFullHelper();
    JSONObject json, musica;
    String durl = "https://compartmusic.herokuapp.com";
    private JSONObject usuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compart_nuvem);

        /*Bundle args = getIntent().getExtras();
        String user = args.getString("usuario");
        try {

            usuario = new JSONObject(user);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        etcodigo = (EditText) findViewById(R.id.etcodigo);
        etnomemusica = (EditText) findViewById(R.id.etnomemusica);
        ettamanhomusica = (EditText) findViewById(R.id.ettamanhomusica);
        etgeneromusica = (EditText) findViewById(R.id.etgeneromusica);
        etartistamusica = (EditText) findViewById(R.id.etartistamusica);
        etalbummusica = (EditText) findViewById(R.id.etalbummusica);
        btsalvarmusica = (Button) findViewById(R.id.btsalvarmusica);
        btdeletarmusica = (Button) findViewById(R.id.btdeletarmusica);
        btconsultarmusica = (Button) findViewById(R.id.btconsultarmusica);

        btsalvarmusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalvarMusica();
            }
        });
        btdeletarmusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeletarMusica();
            }
        });
        btconsultarmusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultarMusica();
            }
        });
    }

    public class musicTask extends AsyncTask<String, String, JSONObject> {

        String url = null;
        String method = null;
        JSONObject parametros = null;

        ProgressDialog dialog;

        public musicTask(String url, String method, JSONObject parametros) {
            this.url = url;
            this.method = method;
            this.parametros = parametros;

        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(compartNuvem.this);
            dialog.show();
        }

        @Override
        protected void onPostExecute(JSONObject musica) {
            dialog.dismiss();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            RestFullHelper http = new RestFullHelper();

            if("DELETE".equals(this.method)) {
                http.doDelete(url);
                return null;
            }
            else{
                if ("POST".equals(this.method)) {
                    return http.getJSON(url, method, parametros);
                }
                else{
                    if ("PUT".equals(this.method)){
                        return null;
                    }
                    else{
                        if("GET".equals(this.method)){
                            musica = http.getJSON(url, method, parametros);
                            Log.e("musica", musica.toString());
                            return musica;
                        }
                        else{
                            return null;
                        }
                    }
                }
            }
        }
    }

    private JSONObject getParams() {
        JSONObject params = new JSONObject();
        try {
            params.put("idmusic", etcodigo.getText().toString());
            params.put("nome", etnomemusica.getText().toString());
            params.put("tamanho", ettamanhomusica.getText().toString());
            params.put("genero", etgeneromusica.getText().toString());
            params.put("artista", etartistamusica.getText().toString());
            params.put("album", etalbummusica.getText().toString());
            //params.put("usuario_id", usuario.getString("id"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }

    private void SalvarMusica(){
        JSONObject params = getParams();

        musicTask mt = new musicTask(durl+"/musicas.json", RestFullHelper.POST, params);
        mt.execute();

        LimparMusica();
    }

    private void LimparMusica(){

        etcodigo.setText("");
        etnomemusica.setText("");
        ettamanhomusica.setText("");
        etgeneromusica.setText("");
        etartistamusica.setText("");
        etalbummusica.setText("");

    }

    private void DeletarMusica() {
        JSONObject params = null;

        musicTask mt = new musicTask(
                durl + "/musicas/"
                        + etcodigo.getText().toString() + ".json",
                RestFullHelper.DELETAR, params);

        mt.execute();
        LimparMusica();
    }

    private void ConsultarMusica(){

        musicTask consultar = new musicTask(durl+"/musicas/"+etcodigo.getText().toString()+".json", RestFullHelper.GET, null);
        consultar.execute();


        try {
            etnomemusica.setText(musica.getString("nome"));
            ettamanhomusica.setText(musica.getString("tamanho"));
            etgeneromusica.setText(musica.getString("genero"));
            etartistamusica.setText(musica.getString("artista"));
            etalbummusica.setText(musica.getString("album"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}
