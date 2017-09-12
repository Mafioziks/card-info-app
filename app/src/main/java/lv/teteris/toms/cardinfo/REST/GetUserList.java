package lv.teteris.toms.cardinfo.REST;

import android.os.AsyncTask;
import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tt007 on 11.07.2017.
 */

public class GetUserList extends AsyncTask <Integer, Integer, String>{

//    GetUserList(Integer i, String result) {
//        super(i, new Integer(1), result);
//    }

    @Override
    protected String doInBackground(Integer... ids) {
        System.out.println("> Start background process");
        String content = "";
        String line = "";
        try {
            System.out.println("> Reading data");
            URL url = new URL("http://10.0.2.2:8000/api/user/view/" + ids[0]);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET");
            connect.setRequestProperty("Accept", "*/*");
            connect.setConnectTimeout(5000);
            connect.setReadTimeout(5000);
            connect.connect();
            System.out.println("> Status code: " + connect.getResponseCode());
            System.out.println("> Getting input lines");
            BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            while ((line = br.readLine()) != null) {
                content += line + "\n";
                System.out.println("> " + line);
            }
            System.out.println("> Getting error lines");
        } catch (MalformedURLException e) {
            System.out.println("> Malformed url: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("> IOException got: " + e.toString());
            content +=  "error";
        }

        System.out.println("> End background process");

        try {
            JSONObject user = new JSONObject(content);
        } catch (JSONException e) {
            System.out.println("> JSONException: " + e.getMessage());
        }

        return content;
    }
}
