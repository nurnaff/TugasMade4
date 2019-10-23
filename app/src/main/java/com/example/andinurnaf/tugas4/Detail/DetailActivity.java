package com.example.andinurnaf.tugas4.Detail;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.andinurnaf.tugas4.R;

import static com.example.andinurnaf.tugas4.Database.DatabaseContract.FavoriteColumns.CONTENT_URI;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.FavoriteColumns.DESKRIPSI;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.FavoriteColumns.FOTO;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.FavoriteColumns.JUDUL;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.FavoriteColumns.RILIS;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    public static String EXTRA_MOVIE = "extra_movie";
    private TextView tvObject;
    ImageView img;
    public static int RESULT_ADD = 101;
    ImageButton btnFavourite;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnFavourite = findViewById(R.id.btn_favourite);
        btnFavourite.setOnClickListener(this);

        tvObject = (TextView)findViewById(R.id.tv_object_received);
        img = findViewById(R.id.photo);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        Glide.with(DetailActivity.this)
                // LOAD URL DARI INTERNET
                .load(movie.getPhoto())
                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                .placeholder(R.drawable.ic_launcher_background)
                //. LOAD GAMBAR SAAT TERJADI KESALAHAN MEMUAT GMBR UTAMA
                .error(R.drawable.ic_launcher_foreground)
                .into(img);
        String text = "Judul : "+ movie.getJudul()+"\nDeskripsi : "+ movie.getDeskrisi()+"\nRilis : "+ movie.getRilis();
        tvObject.setText(text);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_favourite) {

            // Gunakan contentvalues untuk menampung data
            ContentValues values = new ContentValues();
            values.put(JUDUL, movie.getJudul());
            values.put(DESKRIPSI, movie.getDeskrisi());
            values.put(RILIS, movie.getRilis());
            values.put(FOTO, movie.getPhoto());


            getContentResolver().insert(CONTENT_URI, values);

            setResult(RESULT_ADD);
            finish();

            Toast.makeText(getApplicationContext(),"Data berhasil ditambahkan", Toast.LENGTH_LONG).show();

        }
    }
}