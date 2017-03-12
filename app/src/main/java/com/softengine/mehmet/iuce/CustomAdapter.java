package com.softengine.mehmet.iuce;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by mehmet on 29.10.2016.
 */

public class CustomAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Model> mHandleXMLs;
    Activity acc;
    public static String linkk;

    public CustomAdapter(Activity activity, List<Model> handleXML) {
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mHandleXMLs = handleXML;
        acc=activity;
    }

    @Override
    public int getCount() {
        return mHandleXMLs.size();
    }

    @Override
    public Model getItem(int position) {
        return mHandleXMLs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.row, null);
        TextView mTitle = (TextView) satirView.findViewById(R.id.row_title);
        TextView mDesc = (TextView) satirView.findViewById(R.id.row_desc);
        Button mLink = (Button) satirView.findViewById(R.id.row_link);


            final Model handleXML = mHandleXMLs.get(position);

            mTitle.setText(handleXML.getTitle());
            mDesc.setText((handleXML.getDesc()));
            mLink.setText("Habere Git");

            mLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intnet = new Intent(acc.getApplicationContext(), HaberlerWeb.class);

                    linkk = String.valueOf(handleXML.getLink());
                    acc.startActivity(intnet);
                }
            });


        return satirView;
    }
}
