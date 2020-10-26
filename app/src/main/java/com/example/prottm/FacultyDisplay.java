package com.example.prottm;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class FacultyDisplay extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_display);


        //code for jason parse starts here
        listView = (ListView)findViewById(R.id.lvDisplayFaculty);


        String[] url = {"http://decstro.info/amit/jsonAPI/parseFaculty.php"};

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
                Toast.makeText(FacultyDisplay.this,"Loading",Toast.LENGTH_LONG);
            }

            @Override
            protected void onPostExecute(JSONObject j) {

                try{
                    JSONArray jarr = j.getJSONArray("data");
                    Toast.makeText(FacultyDisplay.this,"Available Faculties = "+jarr.length(),Toast.LENGTH_SHORT).show();

                    String id[];
                    String name[];
                    int fload[];
                    String subject[];
                    int lab[];
                    int lecture[];

                    id = new String[jarr.length()];
                    name = new String[jarr.length()];
                    fload = new int[jarr.length()];
                    subject = new String[jarr.length()];
                    lab = new int[jarr.length()];
                    lecture = new int[jarr.length()];

                    //Bundle extra = new Bundle();
                    //extra.putStringArray("facid", id);

                    for(int i=0;i<jarr.length();i++){
                        JSONObject j1 = jarr.getJSONObject(i);

                        id[i] = j1.getString("id");
                        name[i] = j1.getString("name");
                        fload[i] = j1.getInt("fload");
                        subject[i] = j1.getString("subject");
                        lab[i] = j1.getInt("lab");
                        lecture[i] =j1.getInt("lecture"); ;
                    }

                    SingleFaculty adapter = new SingleFaculty(FacultyDisplay.this,id, name,fload,subject,lab,lecture);
                    listView.setAdapter(adapter);


                }catch (Exception e){
                    Toast.makeText(FacultyDisplay.this,""+e,Toast.LENGTH_SHORT).show();
                }
            }
        }

        new JSONParse().execute(url);
        //code for json parse ends here
    }
}
