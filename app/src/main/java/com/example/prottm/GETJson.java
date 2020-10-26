package com.example.prottm;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Hyran on 4/13/2018.
 */

public class GETJson {
    InputStream is;
    JSONObject json;

    JSONObject getData(String url){
        try{
            is = new URL(url).openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line=br.readLine())!=null){
                sb.append(line);
            }
            String data = sb.toString();
            json = new JSONObject(data);

        }catch(Exception e){

        }
        return json;
        }


}
