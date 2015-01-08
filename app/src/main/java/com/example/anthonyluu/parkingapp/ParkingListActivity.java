package com.example.anthonyluu.parkingapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ParkingListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_list);

        ArrayList vals = new ArrayList<String>();
        vals.add("5km");
        vals.add("10km");
        vals.add("100km");

        ParkingListArrayAdapter parkingListArrayAdapter = new ParkingListArrayAdapter(this, vals);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(parkingListArrayAdapter);

//        TextView textView = (TextView) findViewById(R.id.label);
//        textView.setText("THIS IS A TEXTVIEW TEST");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parking_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ParkingListArrayAdapter extends ArrayAdapter<String> {
        private List<String> items;
        public ParkingListArrayAdapter(Context context, ArrayList<String> items) {
            super(context, 0, items);
            this.items = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.parking_list_item, parent, false);
            }
            // Lookup view for data population
            TextView tvDistance = (TextView) convertView.findViewById(R.id.tvDistance);
            TextView tvCost = (TextView) convertView.findViewById(R.id.tvCost);
            TextView tvIntersection = (TextView) convertView.findViewById(R.id.tvIntersection);
            // Populate the data into the template view using the data object
            tvDistance.setText(items.get(position));
            tvCost.setText("$5");
            tvIntersection.setText("some intersection");
            String url = "http://www1.toronto.ca/City_Of_Toronto/Information_&_Technology/Open_Data/Data_Sets/Assets/Files/greenPParking.json";
            new GetParkingDataTask().execute(url);

            // Return the completed view to render on screen
            return convertView;
        }
    }

    private class GetParkingDataTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {


            String responseString = getHttpRequest(urls[0]);
            Log.i("ResponseString", responseString);
            return responseString;
        }

        // Should use this to add a spinner of some sort
//        protected void onProgressUpdate(Integer... progress) {
//            setProgressPercent(progress[0]);
//        }

        protected void onPostExecute(Long result) {
        }

        private String getHttpRequest(String url) {
            StringBuilder sBuilder = new StringBuilder();
            HttpClient client = new DefaultHttpClient();

            HttpGet getRequest = new HttpGet(url);
            HttpResponse response;
            try {
                response = client.execute(getRequest);
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                if(statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    // Starting to create InputStream to read data and append to String Builder
                    InputStream is = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String line;
                    // checking to see if line is empty
                    while((line = reader.readLine()) != null) {
                        sBuilder.append(line);
                    }
                }
                else {
                    Log.e("HttpGetError", "failed to GET information from " + url);
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sBuilder.toString();
        }
    }




}