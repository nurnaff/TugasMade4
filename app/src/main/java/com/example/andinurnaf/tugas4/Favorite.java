package com.example.andinurnaf.tugas4;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.andinurnaf.tugas4.Database.DatabaseContract;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.getColumnInt;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.getColumnString;

public class Favorite implements Parcelable {
    private int id;
    private String judul;
    private String deskripsi;
    private String rilis;
    private String foto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.judul);
        dest.writeString(this.deskripsi);
        dest.writeString(this.rilis);
        dest.writeString(this.foto);
    }

    public Favorite() {

    }

    public Favorite(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.judul = getColumnString(cursor, DatabaseContract.FavoriteColumns.JUDUL);
        this.deskripsi = getColumnString(cursor, DatabaseContract.FavoriteColumns.DESKRIPSI);
        this.rilis = getColumnString(cursor, DatabaseContract.FavoriteColumns.RILIS);
        this.foto = getColumnString(cursor, DatabaseContract.FavoriteColumns.FOTO);
    }

    protected Favorite(Parcel in) {
        this.id = in.readInt();
        this.judul = in.readString();
        this.deskripsi = in.readString();
        this.rilis = in.readString();
        this.foto = in.readString();
    }

    public static final Parcelable.Creator<Favorite> CREATOR = new Parcelable.Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel source) {
            return new Favorite(source);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };
}