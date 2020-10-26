package com.example.prottm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Hyran on 4/13/2018.
 */

public class SingleFaculty extends BaseAdapter {

    Context c;
    String ids[];
    String name[];
    int fload[];
    String subject[];
    int lab[];
    int lecture[];

    SingleFaculty(Context c,String id[],String name[],int fload[],String subject[],int lab[],int lecture[]){
        this.c = c;
        this.ids = id;
        this.name = name;
        this.fload = fload;
        this.subject = subject;
        this.lab = lab;
        this.lecture = lecture;
    }

    @Override
    public int getCount() {
        return ids.length;
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

        view  = inflater.inflate(R.layout.single_faculty,viewGroup,false);

        TextView tvDisfid = (TextView) view.findViewById(R.id.tvDisfid);
        TextView tvDisfname = (TextView) view.findViewById(R.id.tvDisfname);
        TextView tvDisload = (TextView) view.findViewById(R.id.tvDisload);
        TextView tvDisfsub = (TextView) view.findViewById(R.id.tvDisfsub);
        TextView tvDisflab = (TextView) view.findViewById(R.id.tvDisflab);
        TextView tvDisflect = (TextView) view.findViewById(R.id.tvDisflect);

        tvDisfid.setText("ID : "+ids[position]);
        tvDisfname.setText("NAME : "+name[position]);
        tvDisload.setText("LOAD : "+fload[position]);
        tvDisfsub.setText("SUBJECT : "+subject[position]);
        tvDisflab.setText("LAB : "+lab[position]);
        tvDisflect.setText("LECTURE : "+lecture[position]);

        return view;
    }
}
