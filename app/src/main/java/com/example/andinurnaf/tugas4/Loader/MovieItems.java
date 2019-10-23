package com.example.andinurnaf.tugas4.Loader;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class MovieItems implements Parcelable {
    private String photo;
    private String judul;
    private String deskripsi;
    private String rilis;
    public MovieItems(JSONObject object){
        try {
            String photo = "http://image.tmdb.org/t/p/w92/"+object.getString("poster_path");
            String judul = object.getString("title");
            String deskripsi = object.getString("overview");
            String rilis = object.getString("release_date");
            //SimpleDateFormat format = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            //String rilis2 = format.format(rilis.substring(5,7)+"/"+rilis.substring(8,10)+"/"+rilis.substring(0,4));
            this.photo = photo;
            this.judul = judul;
            this.deskripsi = deskripsi;
            this.rilis = rilis;

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getJudul() {
        return judul;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getDeskripsi() {
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    public String getRilis() {
        return rilis;
    }
    public void setRilis(String rilis) {
        this.rilis = rilis;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photo);
        dest.writeString(this.judul);
        dest.writeString(this.deskripsi);
        dest.writeString(this.rilis);
    }

    protected MovieItems(Parcel in) {
        this.photo = in.readString();
        this.judul = in.readString();
        this.deskripsi = in.readString();
        this.rilis = in.readString();
    }

    public static final Parcelable.Creator<MovieItems> CREATOR = new Parcelable.Creator<MovieItems>() {
        @Override
        public MovieItems createFromParcel(Parcel source) {
            return new MovieItems(source);
        }

        @Override
        public MovieItems[] newArray(int size) {
            return new MovieItems[size];
        }
    };
}