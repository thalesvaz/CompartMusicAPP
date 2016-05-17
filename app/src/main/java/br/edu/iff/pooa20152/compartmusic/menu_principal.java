package br.edu.iff.pooa20152.compartmusic;

import android.content.Intent;
import android.graphics.Typeface;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class menu_principal extends AppCompatActivity {

    private ImageButton imgIcon1, imgIcon2, imgIcon3, imgIcon4;
    private JSONObject usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        /*Bundle args = getIntent().getExtras();
        String user = args.getString("usuario");
        try {
            usuario = new JSONObject(user);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/


        imgIcon1 = (ImageButton) findViewById(R.id.imgIcon1);
        imgIcon2 = (ImageButton) findViewById(R.id.imgIcon2);
        imgIcon3 = (ImageButton) findViewById(R.id.imgIcon3);
        imgIcon4 = (ImageButton) findViewById(R.id.imgIcon4);

        // INTENT PARA LIGAR CAMERA
        //imgIcon2.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //        startActivity(camera);
        //    }
        //});

        imgIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaM = new Intent(menu_principal.this, listaMusica.class);
                startActivity(telaM);
            }
        });

        imgIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaComp = new Intent(menu_principal.this, compartilhamento.class);
                /*Bundle usuario = new Bundle();
                usuario.putString("usuario", usuario.toString());
                telaComp.putExtras(usuario);*/
                startActivity(telaComp);
            }
        });

        imgIcon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaCont = new Intent(menu_principal.this, Contatos.class);
                startActivity(telaCont);
            }
        });

        imgIcon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaMens = new Intent(menu_principal.this, mensagens.class);
                startActivity(telaMens);
            }
        });
    }

}
