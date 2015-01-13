package com.example.anthonyluu.parkingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthonyluu on 15-01-13.
 */
public class ParkingListArrayAdapter extends ArrayAdapter<JSONObject> {
    private List<JSONObject> items;
    public ParkingListArrayAdapter(Context context, ArrayList<JSONObject> items) {
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
        try {
            double distanceMeters = items.get(position).getDouble("distance");
            double distanceKM = distanceMeters/1000;
            distanceKM = (double) Math.round(distanceKM * 10)/10;
            tvDistance.setText(String.valueOf(distanceKM)+ " km");

            // if the rate is false, don't display
            if(items.get(position).get("rate") != false) {
                tvCost.setText(items.get(position).getString("rate"));
            }
            tvIntersection.setText(items.get(position).getString("address"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        // Return the completed view to render on screen
        return convertView;
    }
}