package lv.teteris.toms.cardinfo;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lv.teteris.toms.cardinfo.REST.GetUserList;
import lv.teteris.toms.cardinfo.models.User;

/**
 * Created by tt007 on 05.07.2017.
 */

public class CardInfoApi extends AsyncTask<Integer, Integer, String> {

    public final static String ACTION_GET    = "GET";
    public final static String ACTION_POST   = "POST";
    public final static String ACTION_DELETE = "DELETE";

    // Settings for request
    private int connectionTimeout = 50000;
    private int readTimeout = 50000;

    // API settings
    String baseUri;
    String email;
    String password;
    String actionUri = "";
    String action = CardInfoApi.ACTION_GET;

    /**
     * Set base uri with object creation or default is set.
     *
     * @param String baseUri
     */
    public CardInfoApi(String baseUri) {
        this.baseUri = baseUri;
        if (baseUri == null || baseUri.length() < 5) {
            this.baseUri = "http://10.0.2.2:8000/api/";
        }
    }

    public CardInfoApi() {
        this.baseUri = "http://10.0.2.2:8000/api/";
    }

    /**
     * Sets login data for API.
     *
     * @param String email
     * @param String password
     * @return CardInfoApi
     */
    public CardInfoApi setLogin(String email, String password) {
        // Should validate
        this.email = email;
        this.password = password;
        return this;
    }

    /**
     * Authorizes API - can be used to check if login details is ok.
     *
     * @return boolean
     * @throws
     */
    public void authorize() {
        if (this.email == null || this.password == null) {
            throw new Exception("No login details provided!");
        }

        this.actionUri = "/login/";
        this.action = CardInfoApi.ACTION_POST;
        this.execute();
    }

    /**
     * Gets info about one user.
     * TODO: Needs update
     *
     * @param integer id
     * @return
     */
    public void getUserInfo (int id) {
        this.actionUri = "/user/" + id;
        this.action = CardInfoApi.ACTION_GET;
        this.execute();
    }

    /**
     * Gets list of users.
     *
     * To get result at first need to override onPostExecute() method.
     *
     * @return
     */
    public void getUsers() {
        this.actionUri = "/user/";
        ArrayList<User> users = new ArrayList<User>();
        this.action = CardInfoApi.ACTION_GET;
        this.execute();
    }

    public void addUser(User user) {
        this.actionUri = "/user/add/";
        this.action = CardInfoApi.ACTION_POST;
        this.execute();
    }

    /**
     * Here I do the job when with other methods all variables and values has been set.
     * So other functions can also call this one but should check is all needed is provided.
     *
     * @param params
     * @return
     */
    @Override
    protected String doInBackground(Integer... params) {
        String line = "";
        String content = "";
        try {
            System.out.println("> Reading data");
            // Set url
            URL url = new URL(this.baseUri + this.actionUri);
            // Create HttpUrlCOnnection
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            // Set request type
            connect.setRequestMethod(this.action);
            // Add default headers
            connect.setRequestProperty("Accept", "*/*");
            connect.setConnectTimeout(this.connectionTimeout);
            connect.setReadTimeout(this.readTimeout);
            // TODO: Add custom headers

            // Connect
            connect.connect();
            System.out.println("> Status code: " + connect.getResponseCode());
            System.out.println("> Getting input lines");
            // TODO: Check status code
            // Read response
            BufferedReader br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            while ((line = br.readLine()) != null) {
                content += line + "\n";
                System.out.println("> " + line);
            }
            System.out.println("> Getting error lines");
        } catch (MalformedURLException e) {
            // Malformed url exception catching
            System.out.println("> Malformed url: " + e.getMessage());
        } catch (IOException e) {
            // IOException catching
            System.out.println("> IOException got: " + e.toString());
            content +=  "error";
        }
        return content;
    }

    public ArrayList<User> toUserArray(String jsonString) {
        ArrayList<User> users = new ArrayList<User>();
        if (jsonString != null) {
            try {
                JSONTokener tokener = new JSONTokener(jsonString);
                JSONArray array = (JSONArray) tokener.nextValue();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject userJson = (JSONObject) array.getJSONObject(i);
                    User user = new User(
                            userJson.getInt("id"),
                            userJson.getString("email"),
                            userJson.getString("name")
                    );

                    users.add(user);
                }
            } catch (JSONException ex) {
                System.out.println("> Error converting string to json");
            }
        } else {
            System.out.println("> No user string provided");
        }
        return users;
    }
}
