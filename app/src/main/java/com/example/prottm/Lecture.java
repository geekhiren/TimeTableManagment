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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Lecture extends AppCompatActivity {

    TextView day,Clas,Time;
    Spinner faculty1,subject;
    RequestQueue requestQueue;
    Button Add,Back;

    int ds;
    String D,C,T;
    //Storing server URL into string variable
    String HttpMonday = "http://decstro.info/amit/monday.php";
    String HttpTueday = "http://decstro.info/amit/tuesday.php";
    String HttpWednesday = "http://decstro.info/amit/wednesday.php";
    String HttpThursday = "http://decstro.info/amit/thursday.php";
    String HttpFriday = "http://decstro.info/amit/friday.php";
    String url;

    int sub,fac1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);
        // creating volley request queue
        requestQueue = Volley.newRequestQueue(Lecture.this);

        day = (TextView)findViewById(R.id.day) ;
        Clas = (TextView)findViewById(R.id.Clas) ;
        Time = (TextView)findViewById(R.id.time) ;
        faculty1 = (Spinner)findViewById(R.id.faculty1);
        subject =(Spinner) findViewById(R.id.subject);
        Add =(Button)findViewById(R.id.Add);
        Back = (Button)findViewById(R.id.Back);


        faculty1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fac1 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sub = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Intent i = getIntent();

        Bundle extras = i.getExtras();
         D = extras.getString("day");
         C = extras.getString("Clas");
         T = extras.getString("time");

        day.setText(D);
        Clas.setText(C);
        Time.setText(T);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(C.equals("Monday")){
                    url = HttpMonday;
                }else if(C.equals("Tuesday")){
                    url = HttpTueday;
                }else if(C.equals("Wednesday")){
                    url = HttpWednesday;
                }else if(C.equals("Thursday")){
                    url = HttpThursday;
                }else if(C.equals("Friday")){
                    url = HttpFriday;
                }
                if(sub != 0 && fac1 != 0 ){

                    // database insert start
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String ServerResponse) {
                                    // matching server response to our text
                                    if (ServerResponse.equalsIgnoreCase("Success")) {
                                        Toast.makeText(Lecture.this, "Add Data Successfully", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Lecture.this,GEN.class);
                                        startActivity(i);
                                        //do stuff after registration

                                    } else {
                                        //shoving other response from server
                                        Toast.makeText(Lecture.this, "GIVE DIFFERENT TIME SLOT", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    //showing error message if something goes wrong
                                    Toast.makeText(Lecture.this,volleyError.toString(),Toast.LENGTH_SHORT).show();
                                }
                            }){
                        @Override
                        protected Map<String, String> getParams() {

                            // Creating Map String Params.
                            Map<String, String> params = new HashMap<String, String>();
                            // Adding All values to Params.
                            // The firs argument should be same sa your MySQL database table columns.
                            params.put("time",T);
                            params.put("class", D);
                            params.put("subject", subject.getSelectedItem().toString());
                            params.put("faculty1", faculty1.getSelectedItem().toString());

                            return params;
                        }
                    };

                    // Creating RequestQueue.
                    RequestQueue requestQueue = Volley.newRequestQueue(Lecture.this);

                    // Adding the StringRequest object into requestQueue.
                    requestQueue.add(stringRequest);
                    //database insert end

                    //Toast.makeText(Lab.this,"Monday",Toast.LENGTH_LONG).show();
                }
                else { Toast.makeText(Lecture.this,"Please Select all field",Toast.LENGTH_SHORT).show();}


            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Lecture.this,GEN.class);
                startActivity(i);
            }
        });
    }
}
