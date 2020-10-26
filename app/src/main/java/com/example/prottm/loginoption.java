package com.example.prottm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class loginoption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginoption);
    }
    public void studentlogin(View view){
        Intent i=new Intent(this,studentlogin.class);
        startActivity(i);
    }
    public void facultylogin(View view){
        Intent i=new Intent(this,facultylogin.class);
        startActivity(i);
    }
    public void adminlogin(View view){
        Intent i=new Intent(this,adminlogin.class);
        startActivity(i);
    }
}
