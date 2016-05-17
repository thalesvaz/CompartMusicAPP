package br.edu.iff.pooa20152.compartmusic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {

    private EditText etLogin;
    private EditText etSenha;
    private Button btenterlogin;
    private Button btcadastrarUser;
    private JSONArray JA;
    RestFullHelper http = new RestFullHelper();
    JSONObject json;
    String durl = "https://compartmusic.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLogin = (EditText) findViewById(R.id.etemailLogin);
        etSenha = (EditText) findViewById(R.id.etsenhaLogin);
        btenterlogin = (Button) findViewById(R.id.btenterlogin);
        btcadastrarUser = (Button) findViewById(R.id.btcadastrarUser);

        btcadastrarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telacad = new Intent(MainActivity.this, cadastroUsuario.class);
                startActivity(telacad);
            }
        });

        btenterlogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    usuarioTask login = new usuarioTask(durl +"/usuarios.json", RestFullHelper.GET, null);
                    login.execute();
                }
        });
    }

    public class usuarioTask extends AsyncTask<String, String, JSONObject> {

        String url = null;
        String method = null;
        JSONObject parametros = null;

        ProgressDialog dialog;

        @Override
        protected JSONObject doInBackground(String... params) {
            RestFullHelper http = new RestFullHelper();

            if ("GET".equals(this.method)) {
                try {
                    JA = http.getJSONList(url, method, parametros);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
            else{
                return null;
            }
        }

        public usuarioTask(String url, String method, JSONObject parametros) {
            this.url = url;
            this.method = method;
            this.parametros = parametros;

        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MainActivity.this);
            dialog.show();
        }

        @Override
        protected void onPostExecute(JSONObject login_u) {
            int i;
            for (i = 0; i < JA.length(); i++) {
                try {
                    if (etLogin.getText().toString().equals(JA.getJSONObject(i).getString("username"))) {
                        if ((etSenha.getText().toString().equals(JA.getJSONObject(i).getString("senha")))) {
                            Intent tela = new Intent(MainActivity.this, menu_principal.class);
                            /*Bundle usuario = new Bundle();
                            usuario.putString("usuario", JA.getJSONObject(i).toString());
                            tela.putExtras(usuario);*/
                            startActivity(tela);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Login e/ou Senha incorretos", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Login e/ou Senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
            dialog.dismiss();
        }
    }

}
