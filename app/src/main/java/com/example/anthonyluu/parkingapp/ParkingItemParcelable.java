package com.example.anthonyluu.parkingapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anthonyluu on 15-01-11.
 */
public class ParkingItemParcelable implements Parcelable{
    private Integer id;
    private String address;
    private String rate;
    private Double lat;
    private Double lng;
    private int capacity;
    private Double distance;

    public static final Parcelable.Creator<ParkingItemParcelable> CREATOR = new Parcelable.Creator<ParkingItemParcelable>() {
        public ParkingItemParcelable createFromParcel(Parcel in) {
            return new ParkingItemParcelable(in);
        }

        public ParkingItemParcelable[] newArray(int size) {
            return new ParkingItemParcelable[size];
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

    public int getCapacity() {
        return capacity;
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

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public ParkingItemParcelable(int id, String address, String rate, Double lat, Double lng, int capacity, Double distance) {
        this.id = id;
        this.address = address;
        this.rate = rate;
        this.lat = lat;
        this.lng = lng;
        this.capacity = capacity;
        this.distance = distance;

    }

    public ParkingItemParcelable(Parcel in) {
        String[] data = new String[7];

        in.readStringArray(data);
        this.id = Integer.parseInt(data[0]);
        this.address = data[1];
        this.rate = data[2];
        this.lat = Double.parseDouble(data[3]);
        this.lng = Double.parseDouble(data[4]);
        this.capacity = Integer.parseInt(data[5]);
        this.distance = Double.parseDouble(data[6]);

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
                String.valueOf(this.lat),
                String.valueOf(this.lng),
                String.valueOf(this.capacity),
                String.valueOf(this.distance)
        });
    }
}
