package com.example.prottm;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class viewtt extends AppCompatActivity {

    Spinner sem1, day1,div1;
    String[] sem={"Select Semester","1","2","3","4","5","6"};
    String[] div={"Select Division","A","B","C"};
    String[] day={"Select Day","Monday","Tuesday","Wednesday","Thursday","Friday"};
    Button view,reset ;
    int posday,posdiv,possem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtt);
        sem1 = (Spinner) findViewById(R.id.sem);
        div1 = (Spinner) findViewById(R.id.div);
        day1 = (Spinner) findViewById(R.id.day);


        view = (Button)findViewById(R.id.view);
        reset = (Button)findViewById(R.id.reset);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sem);
        sem1.setAdapter(adapter);

        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,div);
        div1.setAdapter(adapter1);

        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,day);
        day1.setAdapter(adapter2);


        div1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posdiv = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        sem1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                possem = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        day1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posday = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selday = day1.getSelectedItem().toString();
                String selsem = sem1.getSelectedItem().toString();
                String seldiv = div1.getSelectedItem().toString();
             //   Toast.makeText(viewtt.this,"",Toast.LENGTH_LONG).show();

                if(posday != 0 && posdiv != 0 && possem !=0){
                    Intent i = new Intent(viewtt.this,timetableDisplay.class);
                    i.putExtra("dy",selday);
                    i.putExtra("sm",selsem);
                    i.putExtra("dv",seldiv);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(viewtt.this,"Please Select all field",Toast.LENGTH_SHORT).show();
                }


            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sem1.setSelection(0);
                day1.setSelection(0);
                div1.setSelection(0);
            }
        });
    }
}
