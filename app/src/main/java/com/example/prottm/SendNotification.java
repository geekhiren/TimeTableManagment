package com.example.prottm;

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

public class SendNotification extends AppCompatActivity {

    EditText edtNotification;
    Button btnSend,btnClear;
    boolean isEmpty;

    //cretating volley request
    RequestQueue requestQueue;

    //Storing server URL into string variable
    String HttpUrl = "http://decstro.info/amit/set_notification.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);

        edtNotification = (EditText)findViewById(R.id.edtNotification);
        btnSend = (Button)findViewById(R.id.btnSendNotification);
        btnClear = (Button)findViewById(R.id.btnClearNotification);

        // creating volley request queue
        requestQueue = Volley.newRequestQueue(SendNotification.this);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmpty();
                if(isEmpty){
                    setNotification();
                }else{
                    Toast.makeText(SendNotification.this,"Please fill out all fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtNotification.setText("");
            }
        });

    }

    public void checkEmpty() {
        if(TextUtils.isEmpty(edtNotification.getText().toString())){
            isEmpty = false;
        }else{
            isEmpty = true;
        }
    }

    public void setNotification() {

        // database insert start
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        // matching server response to our text
                        if (ServerResponse.equalsIgnoreCase("Added")) {
                            Toast.makeText(SendNotification.this, "Sent Successfully", Toast.LENGTH_SHORT).show();
                            edtNotification.setText("");
                            //do stuff after registration

                        } else {
                            //shoving other response from server
                            Toast.makeText(SendNotification.this, ServerResponse, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //showing error message if something goes wrong
                        Toast.makeText(SendNotification.this,"check your internet connection",Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("note", edtNotification.getText().toString());

                return params;
            }
        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(SendNotification.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);

        //database insert end

    }
}
