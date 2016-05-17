package br.edu.iff.pooa20152.compartmusic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Contatos extends AppCompatActivity {

    private ListView lvc;
    private String[] contatos;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos);

        lvc = (ListView) findViewById(R.id.lvlistacontatos);

        contatos = new String[]{"Contatos 1", "Contatos 2", "Contatos 3", "Contatos 4", "Contatos 5"};

        ArrayAdapter itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contatos);
        lvc.setAdapter(itemsAdapter);

    }


}
