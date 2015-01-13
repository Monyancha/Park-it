package com.example.anthonyluu.parkingapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Comparator;

/**
 * Created by anthonyluu on 15-01-13.
 */
public class ParkingJSONObjComparator implements Comparator<JSONObject> {
    @Override
    public int compare(JSONObject lhs, JSONObject rhs) {
        int ret = -1;
        try {
            if(lhs.getDouble("distance") > rhs.getDouble("distance") ) {
                ret = 1;
            }

            if(lhs.getDouble("distance") == rhs.getDouble("distance")) {
                ret = 0;
            }

            if(lhs.getDouble("distance") < rhs.getDouble("distance")) {
                ret = -1;
            }
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
