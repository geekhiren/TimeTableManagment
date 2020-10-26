package com.example.prottm;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class singleTimeTable extends BaseAdapter {

    Context c;
    String time[];
    String subject[];
    String faculty1[];
    String faculty2[];
    String faculty3[];

    singleTimeTable(Context c,String time[],String subject[],String faculty1[],String faculty2[],String faculty3[]){
        this.c = c;
        this.time = time;
        this.subject = subject;
        this.faculty1 = faculty1;
        this.faculty2 = faculty2;
        this.faculty3 = faculty3;
    }

    @Override
    public int getCount() {
        return time.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);

        view  = inflater.inflate(R.layout.activity_single_time_table,viewGroup,false);

        TextView tvDisTime = (TextView) view.findViewById(R.id.tvDisTime);
        TextView tvDisSubject = (TextView) view.findViewById(R.id.tvDisSubject);
        TextView tvDisFaculty1 = (TextView) view.findViewById(R.id.tvDisFaculty1);
        TextView tvDisFaculty2 = (TextView) view.findViewById(R.id.tvDisFaculty2);
        TextView tvDisFaculty3 = (TextView) view.findViewById(R.id.tvDisFaculty3);


        tvDisTime.setText("Time :"+time[position]);
        tvDisSubject.setText("SUBJECT : "+subject[position]);
        tvDisFaculty1.setText("FACULTY : "+faculty1[position]);
        tvDisFaculty2.setText(" "+faculty2[position]);
        tvDisFaculty3.setText(" "+faculty3[position]);

        return view;
    }
}



