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

public class Lab extends AppCompatActivity {

    TextView day,Clas,time;
    Spinner faculty1,faculty2,faculty3,subject;
    Button Add,Back;
    RequestQueue requestQueue;
    int ds;
    String D,C,T;
    //Storing server URL into string variable
    String HttpMonday = "http://decstro.info/amit/monday.php";
    String HttpTueday = "http://decstro.info/amit/tuesday.php";
    String HttpWednesday = "http://decstro.info/amit/wednesday.php";
    String HttpThursday = "http://decstro.info/amit/thursday.php";
    String HttpFriday = "http://decstro.info/amit/friday.php";
    String url;
    int fac1,fac2,fac3,sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);

        // creating volley request queue
        requestQueue = Volley.newRequestQueue(Lab.this);

        day = (TextView)findViewById(R.id.day) ;
        Clas = (TextView)findViewById(R.id.Clas) ;
        time = (TextView)findViewById(R.id.time) ;
        faculty1 = (Spinner)findViewById(R.id.faculty1);
        faculty2 = (Spinner)findViewById(R.id.faculty2);
        faculty3 = (Spinner)findViewById(R.id.faculty3);
        subject = (Spinner)findViewById(R.id.subject);

        faculty1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fac1 =  position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { } });
        faculty2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(fac1 == position ||fac3 == position){
                    ((TextView)faculty2.getSelectedView()).setError("Error message");
                }else{fac2 =  position;}


            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { } });
        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sub = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        faculty3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(fac1 == position ||fac2 == position){
                    ((TextView)faculty3.getSelectedView()).setError("Error message");
                }else{fac3 =  position;}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { } });


        Add =(Button)findViewById(R.id.Add);
        Back = (Button)findViewById(R.id.Back);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        D = extras.getString("day");
        C = extras.getString("Clas");
        T = extras.getString("time");

        day.setText(D);
        Clas.setText(C);
        time.setText(T);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(D.equals("Monday")){
                   url = HttpMonday;
                }else if(D.equals("Tuesday")){
                    url = HttpTueday;
                }else if(D.equals("Wednesday")){
                    url = HttpWednesday;
                }else if(D.equals("Thursday")){
                    url = HttpThursday;
                }else if(D.equals("Friday")){
                    url = HttpFriday;
                }

                if(fac1 != 0 && fac2 != 0 && fac3 !=0 && sub !=0){

                    // database insert start
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String ServerResponse) {
                                    // matching server response to our text
                                    if (ServerResponse.equalsIgnoreCase("Success")) {
                                        Toast.makeText(Lab.this, "Add Data Successfully", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Lab.this,GEN.class);
                                        startActivity(i);
                                        //do stuff after registration

                                    } else {
                                        //shoving other response from server
                                        Toast.makeText(Lab.this, "GIVE DIFFERENT TIME SLOT", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    //showing error message if something goes wrong
                                    Toast.makeText(Lab.this,volleyError.toString(),Toast.LENGTH_SHORT).show();
                                }
                            }){
                        @Override
                        protected Map<String, String> getParams() {

                            // Creating Map String Params.
                            Map<String, String> params = new HashMap<String, String>();
                            // Adding All values to Params.
                            // The firs argument should be same sa your MySQL database table columns.
                            params.put("time",T);
                            params.put("class", C);
                            params.put("subject", subject.getSelectedItem().toString());
                            params.put("faculty1", faculty1.getSelectedItem().toString());
                            params.put("faculty2", faculty2.getSelectedItem().toString());
                            params.put("faculty3", faculty3.getSelectedItem().toString());


                            return params;
                        }
                    };

                    // Creating RequestQueue.
                    RequestQueue requestQueue = Volley.newRequestQueue(Lab.this);

                    // Adding the StringRequest object into requestQueue.
                    requestQueue.add(stringRequest);
                    //database insert end

                    //Toast.makeText(Lab.this,"Monday",Toast.LENGTH_LONG).show();

                }
                else{

                    Toast.makeText(Lab.this,"Select All Field or Check Faculty",Toast.LENGTH_LONG).show();

                }


            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Lab.this,GEN.class);
                startActivity(i);
            }
        });

    }

}
