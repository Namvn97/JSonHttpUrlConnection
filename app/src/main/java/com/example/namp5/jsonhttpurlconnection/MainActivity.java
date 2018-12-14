package com.example.namp5.jsonhttpurlconnection;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;
    private RecyclerView mRecyclerView;
    public static String MESSAGE = "Loading...";
    public static String URL = "https://api.github.com/users/google/repos";
    public static String METHOD_REQUEST_API = "GET";
    private static final String RESPONSE_Language = "language";
    private static final String RESPONSE_NAME = "name";
    private static final String RESPONSE_AVATAR_URL = "avatar_url";
    private static final String RESPONSE_OWNER = "owner";
    private static final String RESPONSE_ID = "id";
    private List<Users> mUsers ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        new FetchData().execute();
    }

    private class FetchData extends AsyncTask<Void, String, List<Users>> {
        @Override
        protected List<Users> doInBackground(Void... voids) {
            String response = "";
            try {
                URL url = new URL(URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod(METHOD_REQUEST_API);
                InputStream in = new BufferedInputStream(conn.getInputStream());
                response = convertStreamToString(in);
                JSONArray jsonArray = new JSONArray(response.toString());
                mUsers = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsObject = (JSONObject) jsonArray.get(i);
                    String language = jsObject.getString(RESPONSE_Language);
                    String name = jsObject.getString(RESPONSE_NAME);
                    JSONObject ownerObject = jsObject.getJSONObject(RESPONSE_OWNER);
                    String avatarOwner = ownerObject.getString(RESPONSE_AVATAR_URL);
                    String idOwner = ownerObject.getString(RESPONSE_ID);
                    Owner owner = new Owner(avatarOwner, idOwner);
                    owner.setmAvatar(avatarOwner);
                    owner.setmId(idOwner);
                    Users user = new Users(language, name);
                    user.setmOwner(owner);
                    mUsers.add(user);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mUsers;
        }

        private String convertStreamToString(InputStream is) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setMessage(MESSAGE);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(List<Users> users) {
            super.onPostExecute(users);
            if (mProgressDialog.isShowing())
                mProgressDialog.dismiss();
            UserAdapter userAdapter = new UserAdapter(users);
            mRecyclerView.setAdapter(userAdapter);
        }
    }
}
