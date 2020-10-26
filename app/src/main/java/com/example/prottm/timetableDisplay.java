package com.example.prottm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class timetableDisplay extends AppCompatActivity {

    ListView listView;
    TextView day,div,sem;
    String D,S,DIV;
    String[] url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_display);


        day = (TextView)findViewById(R.id.day);
        div = (TextView)findViewById(R.id.div);
        sem = (TextView)findViewById(R.id.sem);

        final Intent i = getIntent();
        Bundle extras = i.getExtras();
        D = extras.getString("dy");
        S = extras.getString("sm");
        DIV = extras.getString("dv");

        day.setText(D);
        sem.setText(S);
        div.setText(DIV);

        if(D.equals("Monday")){
             url = new String[]{"http://decstro.info/amit/jsonAPI/parseMonday.php"};
        }else if(D.equals("Tuesday")) {
             url = new String[]{"http://decstro.info/amit/jsonAPI/parseTuesday.php"};
        }else if(D.equals("Wednesday")) {
             url = new String[]{"http://decstro.info/amit/jsonAPI/parseWednesday.php"};
        }else if(D.equals("Thursday")) {
             url = new String[]{"http://decstro.info/amit/jsonAPI/parseThursday.php"};
        }else if(D.equals("Friday")) {
             url = new String[]{"http://decstro.info/amit/jsonAPI/parseFriday.php"};
        }

        //code for jason parse starts here
        listView = (ListView)findViewById(R.id.lvViewTimetable);

        //String[] url = {"http://decstro.info/amit/jsonAPI/parseMonday.php"};

        class myclass extends AsyncTask<String,Integer,JSONObject> {

            @Override
            protected void onPreExecute() {super.onPreExecute();}

            @Override
            protected void onPostExecute(JSONObject jsonObject) {super.onPostExecute(jsonObject);}

            @Override
            protected JSONObject doInBackground(String... strings) {return null;}
        }

        class JSONParse  extends AsyncTask<String,Integer,JSONObject> {

            JSONObject jobj;

            @Override
            protected JSONObject doInBackground(String... strings) {
                GETJson json = new GETJson();
                jobj = json.getData(strings[0]);
                return jobj;
            }

            @Override
            protected void onPreExecute() {
                Toast.makeText(timetableDisplay.this,"Loading",Toast.LENGTH_LONG);
            }

            @Override
            protected void onPostExecute(JSONObject j) {

                try{
                    JSONArray jarr = j.getJSONArray("data");
                    Toast.makeText(timetableDisplay.this,"Today no of "+jarr.length()+"  Lab And Lecture ",Toast.LENGTH_SHORT).show();

                    String Class[];
                    String subject[];
                    String faculty1[];
                    String faculty2[];
                    String faculty3[];
                    String time[] = null;

                    Double t = 9.30;
                    String aa = null;

                    time = new String[jarr.length()];
                    Class = new String[jarr.length()];
                    subject = new String[jarr.length()];
                    faculty1 = new String[jarr.length()];
                    faculty2 = new String[jarr.length()];
                    faculty3 = new String[jarr.length()];

                    //Bundle extra = new Bundle();
                    //extra.putStringArray("facid", id);

                    for(int i=0;i<jarr.length();i++){
                        JSONObject j1 = jarr.getJSONObject(i);

                        time[i] = j1.getString("time");
                        Class[i] = j1.getString("Class");
                        subject[i] = j1.getString("subject");
                        faculty1[i] = j1.getString("faculty1");
                        faculty2[i] = j1.getString("faculty2");
                        faculty3[i] = j1.getString("faculty3");

                    }
                    //Toast.makeText(timetableDisplay.this,""+g,Toast.LENGTH_LONG).show();

                    singleTimeTable adapter = new singleTimeTable(timetableDisplay.this,time,subject,faculty1,faculty2,faculty3);
                    listView.setAdapter(adapter);

                }catch (Exception e){
                    Toast.makeText(timetableDisplay.this,""+e,Toast.LENGTH_SHORT).show();
                }
            }
        }
        new JSONParse().execute(url);
        //code for json parse ends here
    }
}
