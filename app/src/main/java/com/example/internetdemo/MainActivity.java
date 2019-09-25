package com.example.internetdemo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    JSONArray libros;
    List<Item> datos;
    RecyclerView recycler;
    AdaptadorLibros adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datos = new ArrayList<>();
        recycler=findViewById(R.id.reciclador);
        adaptador = new AdaptadorLibros(this,datos);
        recycler.setAdapter(adaptador);
        recycler.setLayoutManager(new LinearLayoutManager(this));



        ConnectivityManager c = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = c.getActiveNetworkInfo();
        if(info == null) {
            Toast.makeText(this, "Active su Wifi", Toast.LENGTH_LONG).show();
        }else if(!info.isConnected()) {
            Toast.makeText(this, "Error, no tiene internet", Toast.LENGTH_LONG).show();
        }

        final String BASE_URL ="https://www.googleapis.com/books/v1/volumes?";
        final String QUERY_PARAM = "q";
        final String MAX_RESULTS = "maxResults";
        final String PRINT_TYPE = "printType";
        Uri builtURI = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, "pride+prejudice")
                .appendQueryParameter(MAX_RESULTS, "10")
                .appendQueryParameter(PRINT_TYPE, "books")
                .build();

        try {
            URL requestURL = new URL(builtURI.toString());
            TaskCargarLibros task = new TaskCargarLibros(this);
            task.execute(requestURL);


        } catch (Exception e) {
            Log.e("ErrorInternet", e.toString());
            e.printStackTrace();
        }
    }
    public void LlenarLibros(JSONArray l){
        libros=l;
        String title;
        String subtitle="";
        String publishedDate;
        String pageCount;
        List<String> Autor = new LinkedList<>();
        boolean saleability;
        String buyLink;
        for (int a=0;a< l.length();a++) {
            JSONObject item = null;

            try {
                item = l.getJSONObject(a);
                Log.e ("lugar",a+"");
                title=item.getJSONObject("volumeInfo").getString("title")+"("+a+")";
                try{
                    subtitle=item.getJSONObject("volumeInfo").getString("subtitle");
                }catch (JSONException e1){
                    try {
                        subtitle = item.getJSONObject("volumeInfo").getString("description");
                    }catch (JSONException e2){
                        subtitle = "has no summary";
                    }
                }

                publishedDate=item.getJSONObject("volumeInfo").getString("publishedDate");
                Log.e ("lugar",a+"-0");
                pageCount=String.valueOf(item.getJSONObject("volumeInfo").getInt("pageCount"));
                Log.e ("lugar",a+"-a");
                JSONArray autor=item.getJSONObject("volumeInfo").getJSONArray("authors");
                Log.e ("lugar",a+"-b");
                for (int a1=0;a1< autor.length();a1++) {
                    String aut = autor.getString(a1);
                    Autor.add(aut);
                }
                Log.e ("lugar",a+"-c");
                String si =item.getJSONObject("saleInfo").getString("saleability");
                Log.e ("lugar",a+"-d");
                if(!si.equals("NOT_FOR_SALE")){
                    saleability=true;
                    buyLink=item.getJSONObject("saleInfo").getString("buyLink");
                }else{
                    saleability=false;
                    buyLink="";
                }
                Log.e ("lugar",a+"-e");
                datos.add(new Item(title,subtitle,publishedDate,pageCount,Autor,saleability,buyLink));
                adaptador.notifyDataSetChanged();
            } catch (JSONException e) {
                Log.d("arturo" , e.toString());
                e.printStackTrace();
            }



        }

    }
}
