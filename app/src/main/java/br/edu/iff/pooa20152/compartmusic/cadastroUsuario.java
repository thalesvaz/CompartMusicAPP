package br.edu.iff.pooa20152.compartmusic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class cadastroUsuario extends AppCompatActivity {

    private Button btcancelar, btsalvaruser;
    private EditText etiduser, etusername, etsenha, etemail, etendereco;
    RestFullHelper http = new RestFullHelper();
    JSONObject json;
    String durl = "https://compartmusic.herokuapp.com";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        btcancelar = (Button) findViewById(R.id.btcancelar);
        btsalvaruser = (Button) findViewById(R.id.btsalvaruser);
        etiduser =(EditText) findViewById(R.id.etiduser);
        etusername = (EditText) findViewById(R.id.etusername);
        etsenha = (EditText) findViewById(R.id.etsenha);
        etemail = (EditText) findViewById(R.id.etemail);
        etendereco = (EditText) findViewById(R.id.etendereco);

        btcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telavoltar = new Intent(cadastroUsuario.this, MainActivity.class);
                startActivity(telavoltar);
            }
        });

        btsalvaruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalvarUsuario();
                Intent telasalvar = new Intent(cadastroUsuario.this, MainActivity.class);
                startActivity(telasalvar);
            }
        });


    }

    public class usuarioTask extends AsyncTask<String, String, JSONObject> {

        String url = null;
        String method = null;
        JSONObject parametros = null;

        ProgressDialog dialog;

        public usuarioTask(String url, String method, JSONObject parametros) {
            this.url = url;
            this.method = method;
            this.parametros = parametros;

        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(cadastroUsuario.this);
            dialog.show();
        }

        @Override
        protected void onPostExecute(JSONObject musica) {
            dialog.dismiss();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            RestFullHelper http = new RestFullHelper();

            if ("POST".equals(this.method)) {
                return http.getJSON(url, method, parametros);
            }
            else{
                 return null;
            }
        }
    }

    private JSONObject getParams() {
        JSONObject params = new JSONObject();
        try {
            params.put("iduser", etiduser.getText().toString());
            params.put("username", etusername.getText().toString());
            params.put("senha", etsenha.getText().toString());
            params.put("email", etemail.getText().toString());
            params.put("endereco", etendereco.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }

    private void SalvarUsuario(){
        JSONObject params = getParams();

        usuarioTask ut = new usuarioTask(durl+"/usuarios.json", RestFullHelper.POST, params);
        ut.execute();

        //LimparMusica();
    }

}
