package com.example.prottm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Hyran on 4/13/2018.
 */

public class SingleNotification extends BaseAdapter {

    Context c;
    String note[];

    public SingleNotification(Context c, String[] note) {
        this.c = c;
        this.note = note;
    }

    @Override
    public int getCount() {
        return note.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
        view  = inflater.inflate(R.layout.single_notification,viewGroup,false);

        TextView textView = (TextView) view.findViewById(R.id.tvDisNotification);
        textView.setText(note[position]);

        return view;
    }
}
