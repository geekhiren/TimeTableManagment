package com.example.prottm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GEN extends AppCompatActivity {



    Spinner Day,Class,Lablec,Time;
    TextView t1;
    Button next,reset;
    int daypos,classpos,timepos,lablecpos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen);

        Day = (Spinner) findViewById(R.id.day);
        Class = (Spinner) findViewById(R.id.clas);
        Time = (Spinner)findViewById(R.id.time);
        Lablec = (Spinner) findViewById(R.id.lablec);

        next =(Button)findViewById(R.id.next);
        reset = (Button)findViewById(R.id.reset);
        Class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classpos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timepos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                daypos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Lablec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lablecpos = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String day = Day.getSelectedItem().toString();
                String Clas = Class.getSelectedItem().toString();
                String lablec = Lablec.getSelectedItem().toString();
                String tim = Time.getSelectedItem().toString();

                if(daypos != 0 && classpos != 0 && timepos !=0){
                    switch(lablecpos){
                        case 0 :
                            Intent i = new Intent(GEN.this,Lab.class);
                            i.putExtra("day",day);
                            i.putExtra("Clas",Clas);
                            i.putExtra("time",tim);
                            startActivity(i);
                            finish();
                            break;
                        case 1:
                            Intent j = new Intent(GEN.this,Lecture.class);
                            j.putExtra("day",day);
                            j.putExtra("Clas",Clas);
                            j.putExtra("time",tim);
                            startActivity(j);
                            finish();
                            break;
                    }
                }else{

                    Toast.makeText(GEN.this,"Please Select all field",Toast.LENGTH_SHORT).show();
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Day.setSelection(0);
                Class.setSelection(0);
                Lablec.setSelection(0);
                Time.setSelection(0);
            }
        });
    }
}
