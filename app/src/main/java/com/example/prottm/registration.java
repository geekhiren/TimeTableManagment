package com.example.prottm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
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

public class registration extends AppCompatActivity {

        EditText fname,fid,fmobile,femail,fpass;
        Button btnSignup;
        Boolean emptyFlag;

    //cretating volley request
    RequestQueue requestQueue;

    //Storing server URL into string variable
    String HttpUrl = "http://decstro.info/amit/faculty_reg.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnSignup =(Button)findViewById(R.id.btnSignup);


        fname = (EditText) findViewById(R.id.fname);
        fid = (EditText) findViewById(R.id.fid);
        femail = (EditText) findViewById(R.id.femail);
        fmobile = (EditText) findViewById(R.id.fmobile);
        fpass = (EditText) findViewById(R.id.fpass);

        // creating volley request queue
        requestQueue = Volley.newRequestQueue(registration.this);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmpty();
                if(emptyFlag){
                    registration(v);
                }else{
                    Toast.makeText(registration.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });



        femail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!(Patterns.EMAIL_ADDRESS.matcher(femail.getText().toString()).matches())){
                    femail.setError("Invalid Email !");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        fmobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(fmobile.getText().toString().length()!=10){
                    fmobile.setError("Invalid Mobile Number !");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        fpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!(fpass.getText().toString().length()>=8)){
                    fpass.setError("8 characters required !");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    public void registration(View v){
        //Toast.makeText(signup.this,enrollment.getText().toString()+" "+sname.getText().toString()+" "+semail.getText().toString()+" "+smobile.getText().toString()+" "+spassword.getText().toString(),Toast.LENGTH_SHORT);
        // database insert start
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        // matching server response to our text
                        if (ServerResponse.equalsIgnoreCase("Success")) {
                            Toast.makeText(registration.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(registration.this,facultylogin.class);
                            startActivity(i);
                            finish();
                            //do stuff after registration

                        } else {
                            //shoving other response from server
                            Toast.makeText(registration.this, ServerResponse, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //showing error message if something goes wrong
                        Toast.makeText(registration.this,volleyError.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();
                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("fid", fid.getText().toString());
                params.put("fname", fname.getText().toString());
                params.put("femail", femail.getText().toString());
                params.put("fmobile", fmobile.getText().toString());
                params.put("password", fpass.getText().toString());

                return params;
            }
        };
        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(registration.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
        //database insert end
    }

    public void checkEmpty(){
        if(TextUtils.isEmpty(fname.getText().toString().trim())||TextUtils.isEmpty(fid.getText().toString().trim())||TextUtils.isEmpty(femail.getText().toString().trim())||TextUtils.isEmpty(fmobile.getText().toString().trim())||TextUtils.isEmpty(fpass.getText().toString().trim())){
            emptyFlag=false;
        }else {
            emptyFlag=true;
        }
    }

    public void reset(View v){

        fname.setText("");
        fid.setText("");
        femail.setText("");
        fmobile.setText("");
        fpass.setText("");

    }
}
