package com.example.prottm;

import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    View v1, v2, v3,v4,v5;
    int i;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = new DBHelper(this);
        Cursor cur = db.getAllData();
        int i1=0;
        while (i1<cur.getCount()){
            Log.e("tid", cur.getString(0));
            Log.e("tname", cur.getString(1));
            Log.e("tcontact", cur.getString(2));

            cur.moveToNext();
            i1++;
        }

        cur = db.getData(1);
        Log.e("tid", cur.getString(0));
        Log.e("tname", cur.getString(1));
        Log.e("tcontact", cur.getString(2));

        db.insertData("abcd", 12345);
        db.insertData("pqrst", 9865);

        db.updateData(3456,1);
        db.deleteData(2);

        cur = db.getAllData();
        i1=0;
        while (i1<cur.getCount()){
            Log.e("tid", cur.getString(0));
            Log.e("tname", cur.getString(1));
            Log.e("tcontact", cur.getString(2));

            cur.moveToNext();
            i1++;
        }

        v1 = (View)findViewById(R.id.v1);
        v2 = (View)findViewById(R.id.v2);
        v3 = (View)findViewById(R.id.v3);
        v4 = (View)findViewById(R.id.v4);
        v5 = (View)findViewById(R.id.v5);

        v1.setVisibility(View.INVISIBLE);
        v2.setVisibility(View.INVISIBLE);
        v3.setVisibility(View.INVISIBLE);
        v4.setVisibility(View.INVISIBLE);
        v5.setVisibility(View.INVISIBLE);
        i=0;
        CountDownTimer ct = new CountDownTimer(3300,300) {
            @Override
            public void onTick(long millisUntilFinished) {
                switch (i){
                    case 0:
                        v2.setVisibility(View.INVISIBLE);
                        v3.setVisibility(View.INVISIBLE);
                        v4.setVisibility(View.INVISIBLE);
                        v5.setVisibility(View.INVISIBLE);

                        v1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        v2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        v3.setVisibility(View.VISIBLE);

                        break;
                    case 3:
                        v4.setVisibility(View.VISIBLE);

                        break;
                    case 4:
                        v5.setVisibility(View.VISIBLE);
                        break;


                }
                i=(i+1)%6;
            }

            @Override
            public void onFinish() {

                Intent in = new Intent(MainActivity.this, loginoption.class);
                startActivity(in);
                finish();
            }
        };
        ct.start();
    }
}
