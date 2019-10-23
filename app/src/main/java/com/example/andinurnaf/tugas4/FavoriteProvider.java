package com.example.andinurnaf.tugas4;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.example.andinurnaf.tugas4.Database.FavoriteHelper;

import java.sql.SQLException;

import static android.provider.ContactsContract.SyncState.CONTENT_URI;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.AUTHORITY;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.FavoriteColumns.TABLE_NAME;

public class FavoriteProvider extends ContentProvider {

    private static final int FAVOURITE = 1;
    private static final int FAVOURITE_ID = 2;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        // content://com.dicoding.mynotesapp/note
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME, FAVOURITE);

        // content://com.dicoding.mynotesapp/note/id
        sUriMatcher.addURI(AUTHORITY,
                TABLE_NAME + "/#",
                FAVOURITE_ID);
    }

    private FavoriteHelper favouriteHelper;

    @Override
    public boolean onCreate() {
        favouriteHelper = new FavoriteHelper(getContext());
        try {
            favouriteHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    /*
    Method query digunakan ketika ingin menjalankan query Select
    Return cursor
     */
    @Override
    public Cursor query(@NonNull Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case FAVOURITE:
                cursor = favouriteHelper.queryProvider();
                break;
            case FAVOURITE_ID:
                cursor = favouriteHelper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }

        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }

        return cursor;
    }


    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {

        long added;

        switch (sUriMatcher.match(uri)) {
            case FAVOURITE:
                added = favouriteHelper.insertProvider(contentValues);
                break;
            default:
                added = 0;
                break;
        }

        if (added > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return Uri.parse(CONTENT_URI + "/" + added);
    }


    @Override
    public int update(@NonNull Uri uri, ContentValues contentValues, String s, String[] strings) {
        int updated;
        switch (sUriMatcher.match(uri)) {
            case FAVOURITE_ID:
                updated = favouriteHelper.updateProvider(uri.getLastPathSegment(), contentValues);
                break;
            default:
                updated = 0;
                break;
        }

        if (updated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updated;
    }

    @Override
    public int delete(@NonNull Uri uri, String s, String[] strings) {
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case FAVOURITE_ID:
                deleted = favouriteHelper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }

        if (deleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return deleted;
    }

}