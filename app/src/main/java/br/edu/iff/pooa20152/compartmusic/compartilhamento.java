package br.edu.iff.pooa20152.compartmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import static android.support.v4.app.ActivityCompat.startActivity;

public class compartilhamento extends AppCompatActivity {

    private LinearLayout ln1, ln2;
    private JSONObject usuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compartilhamento);

        /*Bundle args = getIntent().getExtras();
        String user = args.getString("usuario");
        try {
            usuario = new JSONObject(user);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        ln1 = (LinearLayout) findViewById(R.id.linearnuvem);
        ln2 = (LinearLayout) findViewById(R.id.linearuser);

        ln1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaln1 = new Intent(compartilhamento.this, compartNuvem.class);
                /*Bundle usuario = new Bundle();
                usuario.putString("usuario", usuario.toString());
                telaln1.putExtras(usuario);*/
                startActivity(telaln1);
            }
        });

        ln2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaln2 = new Intent(compartilhamento.this, compartUser.class);
                startActivity(telaln2);
            }
        });


        /*try {
            usuario = new JSONObject(user);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }



}
