package com.example.prottm;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

public class studentlogin extends AppCompatActivity {

    EditText enrolment_no,password;
    Button btnSignUp,btnLogin;
    boolean emptyFlag=true;

    //cretating volley request
    RequestQueue requestQueue;

    //Storing server URL into string variable
    String HttpUrl = "http://decstro.info/amit/student_login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);

        // creating volley request queue
        requestQueue = Volley.newRequestQueue(studentlogin.this);

        enrolment_no = (EditText)findViewById(R.id.stdenteno);
        password = (EditText) findViewById(R.id.stupass);
        btnLogin =(Button)findViewById(R.id.btnStulogin);
        btnSignUp =(Button)findViewById(R.id.btnStuSignup);

        // button login click check all detail fill yes / no
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmpty();
                if(emptyFlag){
                    loginstudent();
                }else{
                    Toast.makeText(studentlogin.this,"Please Fill All Detail",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void checkEmpty(){
        if(TextUtils.isEmpty(enrolment_no.getText().toString().trim())||TextUtils.isEmpty(password.getText().toString().trim())){
            emptyFlag=false;
        }else {
            emptyFlag=true;
        }
    }

    public void loginstudent(){
        // database insert start
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        // matching server response to our text
                        if (ServerResponse.equalsIgnoreCase("Data Matched")) {
                            Toast.makeText(studentlogin.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                            Intent i=new Intent(studentlogin.this,student.class);
                            startActivity(i);
                            enrolment_no.setText("");
                            password.setText("");
                            //do stuff after registration


                        } else {
                            //shoving other response from server
                            Toast.makeText(studentlogin.this, ServerResponse, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //showing error message if something goes wrong
                        Toast.makeText(studentlogin.this,"check your internet conection",Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();



                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("enrolment_no", enrolment_no.getText().toString());
                params.put("password", password.getText().toString());

                return params;
            }
        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(studentlogin.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

        //database insert end
    }
    public void fp(View view){
        Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://decstro.info/amit/ForgotS/first.php"));
        startActivity(i);
    }
    public void signupclick(View view){
        Intent i=new Intent(this,signup.class);
        startActivity(i);
    }

}