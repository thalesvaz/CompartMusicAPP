package br.edu.iff.pooa20152.compartmusic.helper;

/**
 * Created by thales on 12/04/16.
 */

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.iff.pooa20152.compartmusic.RestFullHelper;

import static org.junit.Assert.assertEquals;

public class RestFullUnitTest {

    RestFullHelper http;
    JSONObject json;
    String id;
    //String durl = "http://doml-pooa20152.herokuapp.com/empregadors";
    String durl = "http://compartmusic.herokuapp.com/musicas";

    @Before
    public void setUp() throws Exception {
        http = new RestFullHelper();
        json = http.doPost(durl + ".json", getParams());
        id = Integer.toString(json.getInt("id")).trim();

    }

    @After
    public void tearDown() throws Exception {


    }


    @Test
    public void dogets() throws Exception {

        json = http.doGet(durl+".json");

        //assertEquals(1, json);
        http.doDelete(durl+"/"+id+".json");
    }

    @Test
    public void doget() throws Exception {

        json = http.doGet(durl + "/" + id + ".json");

        assertEquals("coldplay", json.getString("artista"));
        http.doDelete(durl+"/"+id+".json");
    }


    @Test
    public void doDelete() throws Exception {

        json = http.doDelete(durl+"/"+id+".json");
        assertEquals(null, json);
    }

    @Test
    public void doPost() throws Exception{

        assertEquals("fix you", json.getString("nome"));
        http.doDelete(durl+"/"+id+".json");

    }
    @Test
    public void doPut() throws Exception{

        JSONObject oPut = new JSONObject();
        oPut.put("nome","fix you"+id);
        oPut.put("tamanho","9898"+id);
        oPut.put("genero","pop"+id);
        oPut.put("artista","noplay"+id);
        oPut.put("album","X&X"+id);

        json = http.doPut(durl+"/"+id+".json",oPut);

        assertEquals("fix you"+id, json.getString("nome"));
        http.doDelete(durl+"/"+id+".json");

    }


    private JSONObject getParams() {
        JSONObject params = new JSONObject();
        try {
            params.put("nome", "fix you");
            params.put("tamanho", "6500");
            params.put("genero", "alternative");
            params.put("artista", "coldplay");
            params.put("album", "X&Y");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return params;
    }

}