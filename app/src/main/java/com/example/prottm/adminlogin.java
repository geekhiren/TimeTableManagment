package com.example.prottm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class adminlogin extends AppCompatActivity {

    EditText username, password;
    boolean emptyFlag = true;

    //cretating volley request
    RequestQueue requestQueue;

    //Storing server URL into string variable
    String HttpUrl = "http://decstro.info/amit/admin_login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        // creating volley request queue
        requestQueue = Volley.newRequestQueue(adminlogin.this);


        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
    }

    public void loginAdmin(View v){

        checkEmpty();
        if(emptyFlag){
            adminLogin(v);
        }else{
            Toast.makeText(adminlogin.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
        }

    }

    public void checkEmpty(){
        if(TextUtils.isEmpty(username.getText().toString().trim())||TextUtils.isEmpty(password.getText().toString().trim())){
            emptyFlag=false;
        }else {
            emptyFlag=true;
        }
    }


    public void adminLogin(View v){


        // database insert start
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        // matching server response to our text
                        if (ServerResponse.equalsIgnoreCase("Data Matched")) {
                            Toast.makeText(adminlogin.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                            Intent i=new Intent(adminlogin.this,admin.class);
                            startActivity(i);
                            //do stuff after registration
                            username.setText("");
                            password.setText("");
                            finish();

                        } else {
                            //shoving other response from server
                            Toast.makeText(adminlogin.this, ServerResponse, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //showing error message if something goes wrong
                        Toast.makeText(adminlogin.this,"check your internet conection",Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();



                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("username", username.getText().toString());
                params.put("password", password.getText().toString());

                return params;
            }
        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(adminlogin.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

        //database insert end
    }


        public void fp(View view){
            Intent i=new Intent(this,forget.class);
            startActivity(i);
        }
        public void signupclick(View view){
            Intent i=new Intent(this,signup.class);
            startActivity(i);

        }
}
