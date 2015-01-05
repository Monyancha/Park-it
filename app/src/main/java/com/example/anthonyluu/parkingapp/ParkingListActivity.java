package com.example.anthonyluu.parkingapp;

import android.app.Activity;
import android.content.Context;
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

            // Return the completed view to render on screen
            return convertView;
        }
    }
}