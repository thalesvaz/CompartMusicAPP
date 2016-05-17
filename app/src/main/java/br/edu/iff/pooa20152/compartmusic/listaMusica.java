package br.edu.iff.pooa20152.compartmusic;

import android.app.ListActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Mp3Filter implements FilenameFilter{
    @Override
    public boolean accept(File dir, String filename) {
        return (filename.endsWith(".mp3"));
    }
}


public class listaMusica extends ListActivity {

    private static final String SD_PATH = new String(Environment.getExternalStorageDirectory().getPath() + "/music/");
    private List<String> musicas = new ArrayList<>();
    private MediaPlayer mp = new MediaPlayer();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_musica);
        updatePlaylist();


        Button parar = (Button) findViewById(R.id.btparar);
        parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
            }
        });

    }

    @Override
    protected void onListItemClick(ListView list, View view, int position, long id){
        try{
            mp.reset();
            mp.setDataSource(SD_PATH + musicas.get(position));
            mp.prepare();
            mp.start();
        }catch (IOException e){
            Log.v(getString(R.string.app_name), e.getMessage());
        }
    }


    private void updatePlaylist() {
        File home = new File(SD_PATH);
        if (home.listFiles(new Mp3Filter()).length>0){
            for (File file : home.listFiles(new Mp3Filter())){
                musicas.add(file.getName());
            }

            ArrayAdapter<String> listaMusica = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, musicas);
            setListAdapter(listaMusica);
        }
    }





}
