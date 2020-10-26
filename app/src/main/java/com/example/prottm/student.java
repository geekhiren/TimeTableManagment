package com.example.prottm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class student extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
    }
    public void viewtt(View view){
        Intent i=new Intent(this,viewtt.class);
        startActivity(i);
    }
    public void viewnotifi(View view){
        Intent i=new Intent(this,viewnotifiction.class);
        startActivity(i);
    }

}
