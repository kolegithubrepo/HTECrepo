package com.example.ivan.htec;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ListObject> arrayList;
    ListView listView;

    final Context mContext = this;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_layout);

        String url = "https://raw.githubusercontent.com/danieloskarsson/mobile-coding-exercise/master/items.json";
        arrayList = new ArrayList<>();
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.Loading));
        pDialog.show();

        listView = (ListView) findViewById(R.id.list_view_main);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3)
            {
                handleOnItemClickEvent(position);
            }
        });




        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pDialog.dismiss();
                        try{
                            JSONArray json = new JSONArray(response);
                            arrayList = new ArrayList<>();
                            for(int i=0;i<json.length();i++){
                                JSONObject obj = json.getJSONObject(i);
                                ListObject listObject = new ListObject();
                                listObject.setImage((String) obj.get(ListObject.IMAGE_C));
                                listObject.setTitle((String) obj.get(ListObject.TITLE_C));
                                listObject.setDescription((String) obj.get(ListObject.DESCRIPTION_C));

                                arrayList.add(listObject);
                            }

                            listView.setAdapter(new Adapter_HTEC(getBaseContext(),arrayList));

                        }catch (JSONException e) {
                            Toast.makeText(mContext, R.string.Error_RequestMessage, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        Toast.makeText(mContext, R.string.Error_RequestMessage, Toast.LENGTH_LONG).show();
                    }
                });


        AppController.getInstance(this).addToRequestQueue(stringRequest);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void handleOnItemClickEvent(int position){
        Intent nextScreen = new Intent(getApplicationContext(), ListItemActivity.class);
        ListObject obj = arrayList.get(position);
        nextScreen.putExtra(ListObject.TITLE_C,obj.getTitle());
        nextScreen.putExtra(ListObject.DESCRIPTION_C, obj.getDescription());
        nextScreen.putExtra(ListObject.IMAGE_C, obj.getImage());


        startActivity(nextScreen);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.ivan.htec/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.ivan.htec/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
