package com.example.andinurnaf.tugas4.Activity;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.andinurnaf.tugas4.Adapter.RecyclerViewAdapter;
import com.example.andinurnaf.tugas4.Loader.MovieItems;
import com.example.andinurnaf.tugas4.Loader.MyAsynTaskLoader;
import com.example.andinurnaf.tugas4.R;

import java.util.ArrayList;

public class UpcomingActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>>{
    RecyclerView rvMovie;
    private ArrayList<MovieItems> list;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);
        rvMovie = findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);

        rvMovie.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this);
        rvMovie.setAdapter(adapter);

        getSupportLoaderManager().restartLoader(0 , null , this);
    }


    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {
        return new MyAsynTaskLoader(this,"https://api.themoviedb.org/3/movie/upcoming?api_key=6c063cdb5fc9520684745b5408008e68&language=en-US");
    }

    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        Log.d("size", "onLoadFinished: " + data.size());
        adapter.setData(data);
    }

    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }

}