package com.example.prottm;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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

public class facultylogin extends AppCompatActivity {

    EditText id,password;
    boolean emptyFlag = true;
   // TextView fp;

    //cretating volley request
    RequestQueue requestQueue;

    //Storing server URL into string variable
    String HttpUrl = "http://decstro.info/amit/faculty_login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultylogin);

        // creating volley request queue
        requestQueue = Volley.newRequestQueue(facultylogin.this);

        id = (EditText)findViewById(R.id.fid);
        password = (EditText) findViewById(R.id.fpass);
       // fp = (TextView) findViewById(R.id.forpass);

    }

    public void fp(View view){
        Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://decstro.info/amit/ForgotF/first.php"));
        startActivity(i);
    }
    public void signupclick(View view){
        Intent i=new Intent(this,facultyAdminContact.class);
        startActivity(i);
    }

    public  void facLogin(View v){
        checkEmpty();
        if(emptyFlag){
            LogFac(v);
        }else{
            Toast.makeText(facultylogin.this,"Please Fill All Detail",Toast.LENGTH_SHORT).show();
        }

    }

    public void checkEmpty(){
        if(TextUtils.isEmpty(id.getText().toString().trim())||TextUtils.isEmpty(password.getText().toString().trim())){
            emptyFlag=false;
        }else {
            emptyFlag=true;
        }
    }


    public void LogFac(View v){

        // database insert start
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        // matching server response to our text
                        if (ServerResponse.equalsIgnoreCase("Data Matched")) {
                            Toast.makeText(facultylogin.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                            Intent i=new Intent(facultylogin.this,faculty.class);
                            startActivity(i);
                            //do stuff after registration
                            id.setText("");
                            password.setText("");

                        } else {
                            //shoving other response from server
                            Toast.makeText(facultylogin.this, ServerResponse, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //showing error message if something goes wrong
                        Toast.makeText(facultylogin.this,"check your internet conection",Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();



                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("id", id.getText().toString());
                params.put("password", password.getText().toString());

                return params;
            }
        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(facultylogin.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

        //database insert end
    }
}
