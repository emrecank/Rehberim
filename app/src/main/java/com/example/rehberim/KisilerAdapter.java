package com.example.rehberim;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class KisilerAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<Kisiler> KisilerList;


    public KisilerAdapter(Activity activity, ArrayList<Kisiler> KisilerList) {

        this.mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.KisilerList = KisilerList;
    }

    @Override
    public int getCount() {
        return KisilerList.size();
    }

    @Override
    public Object getItem(int position) {
        return KisilerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = mInflater.inflate(R.layout.kisi, null);
        TextView kisiad = (TextView) convertView.findViewById(R.id.textView1);
        TextView kisino = (TextView) convertView.findViewById(R.id.textView2);
        ImageView kisiresim = (ImageView) convertView.findViewById(R.id.imageView);
        Kisiler kisi = KisilerList.get(position);
        kisiad.setText(kisi.get_isim());
        kisino.setText(kisi.get_numara());
        if (KisilerList.get(position).getResim() != null)
            kisiresim.setImageBitmap(KisilerList.get(position).getResim());
        else {
            kisiresim.setImageResource(R.drawable.contacticon);
        }
        convertView.setTag(KisilerList.get(position).get_isim());
        return convertView;
    }
}
