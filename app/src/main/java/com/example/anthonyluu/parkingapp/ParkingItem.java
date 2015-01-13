package com.example.anthonyluu.parkingapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by anthonyluu on 15-01-11.
 */
public class ParkingItem implements Parcelable{
    private Integer id;
    private String address;
    private String rate;
    private Double lat;
    private Double lng;
    private Double distance;

    public static final Parcelable.Creator<ParkingItem> CREATOR = new Parcelable.Creator<ParkingItem>() {
        public ParkingItem createFromParcel(Parcel in) {
            return new ParkingItem(in);
        }

        public ParkingItem[] newArray(int size) {
            return new ParkingItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getRate() {
        return rate;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public Double getDistance() {
        return distance;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public ParkingItem(int id, String address, String rate, Double lat, Double lng, Double distance) {
        this.id = id;
        this.address = address;
        this.rate = rate;
        this.lat = lat;
        this.lng = lng;
        this.distance = distance;

    }

    public ParkingItem(Parcel in) {
        String[] data = new String[6];

        in.readStringArray(data);
        this.id = Integer.parseInt(data[0]);
        this.address = data[1];
        this.rate = data[2];
        this.lat = Double.parseDouble(data[3]);
        this.lng = Double.parseDouble(data[4]);
        this.distance = Double.parseDouble(data[5]);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                String.valueOf(this.id),
                this.address,
                this.rate,
                this.lat.toString(),
                this.lng.toString(),
                this.distance.toString()
        });
    }
}
