package com.example.hp.anew;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context c;
    ArrayList<caart> clist;

    public MyAdapter(Context c, ArrayList<caart> clist) {
        this.c = c;
        this.clist = clist;
    }

    @Override
    public int getCount() {
        return clist.size();
    }

    @Override
    public Object getItem(int position) {
        return clist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView=LayoutInflater.from(c).inflate(R.layout.info,parent,false);
        }
        TextView item=(TextView) convertView.findViewById(R.id.item);
        TextView cost=(TextView) convertView.findViewById(R.id.cost);
        TextView qty=(TextView) convertView.findViewById(R.id.qty);

        final caart list=(caart) this.getItem(position);

        item.setText(list.getName());
        cost.setText(String.valueOf(list.getPrice()));
        qty.setText(String.valueOf(list.getQty()));

        return convertView;
    }
}
