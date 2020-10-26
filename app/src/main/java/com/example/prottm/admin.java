package com.example.prottm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin extends AppCompatActivity {

    Button anotofi,agenerate,viewTimeTable,anotifiView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        anotofi = (Button) findViewById(R.id.anotifi);
        agenerate=(Button) findViewById(R.id.agenerate);
        viewTimeTable = (Button)findViewById(R.id.viewTimeTable);
        anotifiView =(Button)findViewById(R.id.anotifiView);

        viewTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin.this,viewtt.class);
                startActivity(i);
            }
        });

        anotifiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(admin.this,viewnotifiction.class);
                startActivity(i);
            }
        });


        anotofi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(admin.this, SendNotification.class);
                startActivity(i);
            }
        });

        agenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(admin.this,GEN.class);
                startActivity(j);
            }
        });

    }



    public void mtt(View view) {
        Intent i = new Intent(this, FacultyDisplay.class);
        startActivity(i);
    }

    public void regi(View view) {
        Intent i = new Intent(this, registration.class);
        startActivity(i);
    }

    public void addfaculty(View view) {
        Intent i = new Intent(this, facultyadd.class);
        startActivity(i);
    }
}
