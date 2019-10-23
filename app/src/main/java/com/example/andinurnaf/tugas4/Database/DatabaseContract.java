package com.example.andinurnaf.tugas4.Database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DatabaseContract {

    // Authority yang digunakan
    public static final String AUTHORITY = "com.example.andinurnaf.tugas4";
    public static final String SCHEME = "content";

    //
    private DatabaseContract(){}

    public static final class FavoriteColumns implements BaseColumns {
        // Note table name
        public static String TABLE_NAME = "favourite";

        public static String JUDUL = "judul";
        public static String DESKRIPSI = "deskripsi";
        public static String RILIS = "rilis";
        public static String FOTO = "foto";

        // Base content yang digunakan untuk akses content provider
        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();

    }

    /*
    Digunakan untuk mempermudah akses data di dalam cursor dengan parameter nama column
    */
    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

}