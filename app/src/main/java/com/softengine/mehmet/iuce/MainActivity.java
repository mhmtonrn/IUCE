package com.softengine.mehmet.iuce;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.softengine.mehmet.iuce.CustomAdapter.linkk;

public class MainActivity extends AppCompatActivity {
    public static boolean checked;
    private String finalURL = "http://ce.istanbul.edu.tr/Rss.aspx";
    List<Model> list;
    CustomAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Haberler");

        startService(new Intent(this,MyServices.class));

        HandleXML obj;
        obj = new HandleXML(finalURL);
        obj.fetchXML();
        list = new ArrayList<Model>();
        ListView listView = (ListView) findViewById(R.id.list);
        String t, d, l;
        t = obj.getTitle();
        d = obj.getDescription();
        l = obj.getLink();


        while (obj.parsingComplete) {
            int control=1;
            String nt = obj.getTitle();
            String nd = obj.getDescription();
            String nl = obj.getLink();
            if (!(t.equals(nt))  && !(t.equals(nd)) && !(l.equals(nl))) {
                list.add(new Model(Html.fromHtml(nt),Html.fromHtml(nd),Html.fromHtml(nl)));
                t = nt;
                l = nl;
                d = nl;

            }
        }

        adapter=new CustomAdapter(this,list);
        listView.setAdapter(adapter);

        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor ilk = preferences.edit();
        SharedPreferences preferences1= PreferenceManager.getDefaultSharedPreferences(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Document doc;
        try {
            // need url
            doc = Jsoup.connect("http://ce.istanbul.edu.tr/Haberler.aspx").get();
            Elements links = doc.select("div[class=pencere-icerik]");

            ilk.putString("link",links.text());
            ilk.commit();

            String myString = preferences.getString("link","text");
            String myString1 = preferences1.getString("linkControl","0");

            if (myString1=="0"){
                SharedPreferences.Editor ikinci = preferences.edit();
                ikinci.putString("linkControl",myString);
                ikinci.commit();
            }
            if (myString==myString1){
                checked=false;
            }else{
                SharedPreferences.Editor ikinci = preferences.edit();
                ikinci.putString("linkControl",myString);
                ikinci.commit();
                checked =true;
            }

        } catch (IOException ex) {
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.web){
            linkk=("http://ce.istanbul.edu.tr/m/");
            startActivity(new Intent(getApplicationContext(),HaberlerWeb.class));

        }
        return super.onOptionsItemSelected(item);
    }


}
