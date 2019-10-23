package com.example.andinurnaf.tugas4.Activity;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.andinurnaf.tugas4.Adapter.RecyclerViewAdapter;
import com.example.andinurnaf.tugas4.Loader.MovieItems;
import com.example.andinurnaf.tugas4.Loader.MyAsynTaskLoader;
import com.example.andinurnaf.tugas4.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<MovieItems>>{
    RecyclerView rvMovie;
    private ArrayList<MovieItems> list;
    RecyclerViewAdapter adapter;
    EditText editJudul;
    Button buttonCari;
    static String judul = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editJudul = findViewById(R.id.edit_judul);
        buttonCari = findViewById(R.id.btn_cari);
        buttonCari.setOnClickListener(myListener);

        rvMovie = findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);

        rvMovie.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this);
        rvMovie.setAdapter(adapter);

        //getLoaderManager().restartLoader(0 , null , this);
    }
    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            judul = editJudul.getText().toString();

            if (TextUtils.isEmpty(judul))return;

            Bundle bundle = new Bundle();
            bundle.putString("https://api.themoviedb.org/3/search/movie?api_key=6c063cdb5fc9520684745b5408008e68&language=en-US&query="+judul,judul);
            getSupportLoaderManager().restartLoader(0,bundle,SearchActivity.this);
        }
    };


    @Override
    public Loader<ArrayList<MovieItems>> onCreateLoader(int id, Bundle args) {
        judul = editJudul.getText().toString();
        return new MyAsynTaskLoader(this,"https://api.themoviedb.org/3/search/movie?api_key=6c063cdb5fc9520684745b5408008e68&language=en-US&query=" + judul);
    }

    public void onLoadFinished(Loader<ArrayList<MovieItems>> loader, ArrayList<MovieItems> data) {
        Log.d("size", "onLoadFinished: " + data.size());
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<MovieItems>> loader) {
        adapter.setData(null);
    }
}