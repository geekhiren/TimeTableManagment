package com.example.prottm;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class viewnotifiction extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewnotifiction);

        //code for jason parse starts here
        listView = (ListView)findViewById(R.id.lvViewNotification);


        String[] url = {"http://decstro.info/amit/jsonAPI/parseNotification.php"};

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
                Toast.makeText(viewnotifiction.this,"Loading",Toast.LENGTH_LONG);
            }

            @Override
            protected void onPostExecute(JSONObject j) {

                try{
                    JSONArray jarr = j.getJSONArray("data");
                    Toast.makeText(viewnotifiction.this,"no of  "+jarr.length()+"  Available Notification ",Toast.LENGTH_SHORT).show();
                    String note[];

                    note = new String[jarr.length()];

                    for(int i=0;i<jarr.length();i++){
                        JSONObject j1 = jarr.getJSONObject(i);

                        note[i] = j1.getString("note");

                    }

                    SingleNotification adapter = new SingleNotification(viewnotifiction.this,note);
                    listView.setAdapter(adapter);


                }catch (Exception e){
                    Toast.makeText(viewnotifiction.this,""+e,Toast.LENGTH_SHORT).show();
                }
            }
        }

        new JSONParse().execute(url);
        //code for json parse ends here

    }
}
