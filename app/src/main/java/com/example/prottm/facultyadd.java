package com.example.prottm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class facultyadd extends AppCompatActivity {
        EditText fid,fname,fload,fsub;
        Spinner flab,flecture;
        int lab,lec;
        Button addFaculty;
        Boolean emptyFlag;
       // String[] lab={"LAB LOAD","2","4","6","8"};
       // String[] lecture={"LECTURE LOAD","1","2","3","4","5","6","7","8","9","10","11","12"};

    //cretating volley request
    RequestQueue requestQueue;

    //Storing server URL into string variable
    String HttpUrl = "http://decstro.info/amit/faculty_add.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultyadd);

        // creating volley request queue
        requestQueue = Volley.newRequestQueue(facultyadd.this);

        fid = (EditText)findViewById(R.id.fid);
        fname = (EditText)findViewById(R.id.fname);
        fload = (EditText)findViewById(R.id.fload);
        fsub = (EditText)findViewById(R.id.fsub);
        flab = (Spinner)findViewById(R.id.flab);
        flecture = (Spinner)findViewById(R.id.flecture);
        addFaculty = (Button)findViewById(R.id.btnsignup);

        flecture.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lec = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        flab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lab = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmpty();
                if(emptyFlag){
                    addFaculty(v);
                }else{
                    Toast.makeText(facultyadd.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void checkEmpty(){
        if(TextUtils.isEmpty(fname.getText().toString().trim())||TextUtils.isEmpty(fid.getText().toString().trim())|| TextUtils.isEmpty(fload.getText().toString().trim())||TextUtils.isEmpty(fsub.getText().toString().trim())){

            emptyFlag=false;
        }else {
            if (lab != 0 && lec != 0){
            emptyFlag=true;
        }
      }
    }

    public void addFaculty(View v){

        // database insert start
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        // matching server response to our text
                        if (ServerResponse.equalsIgnoreCase("Success")) {
                            Toast.makeText(facultyadd.this, "Add Data Successfully", Toast.LENGTH_SHORT).show();
                            fid.setText("");
                            fname.setText("");
                            fload.setText("");
                            fsub.setText("");
                            flab.setSelection(0);
                            flecture.setSelection(0);
                            finish();

                            //do stuff after registration

                        } else {
                            //shoving other response from server
                            Toast.makeText(facultyadd.this, ServerResponse, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //showing error message if something goes wrong
                        Toast.makeText(facultyadd.this,"Please check your connection",Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();
                // Adding All values to Params.
                // The firs argument should be same sa your MySQL database table columns.
                params.put("fid", fid.getText().toString());
                params.put("name", fname.getText().toString());
                params.put("load", fload.getText().toString());
                params.put("subject", fsub.getText().toString());
                params.put("lab", flab.getSelectedItem().toString());
                params.put("lecture", flecture.getSelectedItem().toString());

                return params;
            }
        };

        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(facultyadd.this);

        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
        //database insert end
    }
    public void btnResetad(View v){
        fid.setText("");
        fname.setText("");
        fload.setText("");
        fsub.setText("");
        flab.setSelection(0);
        flecture.setSelection(0);

    }
}
