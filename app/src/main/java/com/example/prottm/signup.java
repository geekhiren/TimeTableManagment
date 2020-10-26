package com.example.prottm;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {

        EditText sname,enrollment,semail,smobile,spassword;
        Button btnSignup ;
        Boolean emptyFlag;
    //cretating volley request
    RequestQueue requestQueue;

    //Storing server URL into string variable
    String HttpUrl = "http://decstro.info/amit/student_reg.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        enrollment = (EditText)findViewById(R.id.sen);
        sname = (EditText)findViewById(R.id.sname);
        semail = (EditText)findViewById(R.id.semail);
        smobile = (EditText)findViewById(R.id.smobile);
        spassword = (EditText)findViewById(R.id.spassword);

        btnSignup = (Button)findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmpty();
                if(emptyFlag){
                    signupstu(v);
                }else{
                    Toast.makeText(signup.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // creating volley request queue
        requestQueue = Volley.newRequestQueue(signup.this);


        sname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!(sname.getText().toString().matches("[a-zA-Z]*"))){
                    sname.setError("Invalid Name !");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        enrollment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(enrollment.getText().toString().length()!=12){
                    enrollment.setError("12 digit required !");

                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        semail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!(Patterns.EMAIL_ADDRESS.matcher(semail.getText().toString()).matches())){
                    semail.setError("Invalid Email !");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        smobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(smobile.getText().toString().length()!=10){
                    smobile.setError("Invalid Mobile Number !");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        spassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!(spassword.getText().toString().length()>=8)){
                    spassword.setError("8 characters required !");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    public void signupstu(View v){

        //Toast.makeText(signup.this,enrollment.getText().toString()+" "+sname.getText().toString()+" "+semail.getText().toString()+" "+smobile.getText().toString()+" "+spassword.getText().toString(),Toast.LENGTH_SHORT);

        // get data into strings
        String en = enrollment.getText().toString();
        String name = sname.getText().toString();
        String email = semail.getText().toString();
        String mobile = smobile.getText().toString();
        String password = spassword.getText().toString();


        // database insert start
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        // matching server response to our text
                        if (ServerResponse.equalsIgnoreCase("Success")) {
                            Toast.makeText(signup.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(signup.this,studentlogin.class);
                            startActivity(i);
                            finish();
                            //do stuff after registration

                        } else {
                            //shoving other response from server
                            Toast.makeText(signup.this, ServerResponse, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //showing error message if something goes wrong
                        Toast.makeText(signup.this,volleyError.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("enrolment_no", enrollment.getText().toString());
                params.put("name", sname.getText().toString());
                params.put("email", semail.getText().toString());
                params.put("contact_no", smobile.getText().toString());
                params.put("password", spassword.getText().toString());

                return params;
            }
        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(signup.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

        //database insert end
    }

    public void btnReset(View v){
        sname.setText("");
        enrollment.setText("");
        semail.setText("");
        smobile.setText("");
        spassword.setText("");
    }

    public void checkEmpty(){
        if(TextUtils.isEmpty(sname.getText().toString().trim())||TextUtils.isEmpty(enrollment.getText().toString().trim())||TextUtils.isEmpty(semail.getText().toString().trim())||TextUtils.isEmpty(smobile.getText().toString().trim())||TextUtils.isEmpty(spassword.getText().toString().trim())){
            emptyFlag=false;
        }else {
            emptyFlag=true;
        }
    }
}
