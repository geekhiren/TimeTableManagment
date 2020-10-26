package com.example.prottm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class faculty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
    }
    public void fview(View view){
        Intent i=new Intent(this,viewtt.class);
        startActivity(i);
    }
    public void fnotifi(View view){
        Intent i=new Intent(this,viewnotifiction.class);
        startActivity(i);
    }
    public void send(View view){
        Intent i=new Intent(this,SendNotification.class);
        startActivity(i);
    }
}
