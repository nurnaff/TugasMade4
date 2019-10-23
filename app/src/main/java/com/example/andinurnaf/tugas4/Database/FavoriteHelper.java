package com.example.andinurnaf.tugas4.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.andinurnaf.tugas4.Favorite;

import java.sql.SQLException;
import java.util.ArrayList;

import static android.provider.MediaStore.Audio.Playlists.Members._ID;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.FavoriteColumns.DESKRIPSI;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.FavoriteColumns.FOTO;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.FavoriteColumns.JUDUL;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.FavoriteColumns.RILIS;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.FavoriteColumns.TABLE_NAME;

public class FavoriteHelper {
    private static String DATABASE_TABLE = TABLE_NAME;
    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public FavoriteHelper(Context context) {
        this.context = context;
    }

    public FavoriteHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    public ArrayList<Favorite> query() {
        ArrayList<Favorite> arrayList = new ArrayList<Favorite>();
        Cursor cursor = database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null, _ID + " DESC"
                , null);
        cursor.moveToFirst();
        Favorite favourite;
        if (cursor.getCount() > 0) {
            do {

                favourite = new Favorite();
                favourite.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                favourite.setJudul(cursor.getString(cursor.getColumnIndexOrThrow(JUDUL)));
                favourite.setDeskripsi(cursor.getString(cursor.getColumnIndexOrThrow(DESKRIPSI)));
                favourite.setRilis(cursor.getString(cursor.getColumnIndexOrThrow(RILIS)));
                favourite.setFoto(cursor.getString(cursor.getColumnIndexOrThrow(FOTO)));

                arrayList.add(favourite);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(Favorite favourite) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(JUDUL, favourite.getJudul());
        initialValues.put(DESKRIPSI, favourite.getDeskripsi());
        initialValues.put(RILIS, favourite.getRilis());
        initialValues.put(FOTO, favourite.getFoto());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }


    public int update(Favorite favourite) {
        ContentValues args = new ContentValues();
        args.put(JUDUL, favourite.getJudul());
        args.put(DESKRIPSI, favourite.getDeskripsi());
        args.put(RILIS, favourite.getRilis());
        args.put(FOTO, favourite.getFoto());
        return database.update(DATABASE_TABLE, args, _ID + "= '" + favourite.getId() + "'", null);
    }

    public int delete(int id) {
        return database.delete(TABLE_NAME, _ID + " = '" + id + "'", null);
    }

    public Cursor queryByIdProvider(String id) {
        return database.query(DATABASE_TABLE, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public Cursor queryProvider() {
        return database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null
                , _ID + " DESC");
    }


    public long insertProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int updateProvider(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, _ID + " = ?", new String[]{id});
    }

    public int deleteProvider(String id) {
        return database.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }
}