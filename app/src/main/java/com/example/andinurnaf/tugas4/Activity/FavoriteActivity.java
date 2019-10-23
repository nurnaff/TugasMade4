package com.example.andinurnaf.tugas4.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.andinurnaf.tugas4.Adapter.FavoriteAdapter;
import com.example.andinurnaf.tugas4.Database.DatabaseContract;
import com.example.andinurnaf.tugas4.R;

import static com.example.andinurnaf.tugas4.Database.DatabaseContract.FavoriteColumns.CONTENT_URI;

public class FavoriteActivity extends AppCompatActivity implements
        android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>,
        AdapterView.OnItemClickListener{
    private FavoriteAdapter favouriteAdapter;
    ListView lvNotes;

    private final int LOAD_NOTES_ID = 110;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        getSupportActionBar().setTitle("Movie Favorit");

        lvNotes = findViewById(R.id.lv_notes);
        favouriteAdapter = new FavoriteAdapter(this, null, true);
        lvNotes.setAdapter(favouriteAdapter);
        lvNotes.setOnItemClickListener(this);

        getSupportLoaderManager().initLoader(LOAD_NOTES_ID, null, this);


    }
    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(LOAD_NOTES_ID, null, this);
    }


    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return new CursorLoader(this, CONTENT_URI, null, null, null, null);
    }


    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        favouriteAdapter.swapCursor(data);
    }


    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {
        favouriteAdapter.swapCursor(null);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }





    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Cursor cursor = (Cursor) favouriteAdapter.getItem(i);

        int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteColumns._ID));
        Intent intent = new Intent(FavoriteActivity.this, DetailFavoriteActivity.class);
        intent.setData(Uri.parse(CONTENT_URI + "/" + id));
        startActivity(intent);
    }



}