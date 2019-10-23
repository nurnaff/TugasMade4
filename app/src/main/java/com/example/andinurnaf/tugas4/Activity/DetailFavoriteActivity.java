package com.example.andinurnaf.tugas4.Activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andinurnaf.tugas4.Favorite;
import com.example.andinurnaf.tugas4.R;

//sumber pak Bambang Batch 3
public class DetailFavoriteActivity extends AppCompatActivity {

    public static String EXTRA_MOVIE = "extra_movie";
    private TextView tvObject;
    ImageView img;
    public static int RESULT_ADD = 101;
    ImageButton btnFavourite;

    private Favorite favouriteItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        tvObject = (TextView)findViewById(R.id.tv_object_received);
        img = findViewById(R.id.photo);

        Uri uri = getIntent().getData();

        if (uri != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);

            if (cursor != null) {

                if (cursor.moveToFirst()) favouriteItem = new Favorite(cursor);
                cursor.close();
            }
        }

        Glide.with(DetailFavoriteActivity.this)
                // LOAD URL DARI INTERNET
                .load(favouriteItem.getFoto())
                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                .placeholder(R.drawable.ic_launcher_background)
                //. LOAD GAMBAR SAAT TERJADI KESALAHAN MEMUAT GMBR UTAMA
                .error(R.drawable.ic_launcher_foreground)
                .into(img);
        String text = "Judul : "+ favouriteItem.getJudul()+"\nDeskripsi : "+ favouriteItem.getDeskripsi()+"\nRilis : "+ favouriteItem.getRilis();
        tvObject.setText(text);
    }

}