package com.example.andinurnaf.tugas4.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andinurnaf.tugas4.R;

import static com.example.andinurnaf.tugas4.Database.DatabaseContract.FavoriteColumns.DESKRIPSI;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.FavoriteColumns.FOTO;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.FavoriteColumns.JUDUL;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.FavoriteColumns.RILIS;
import static com.example.andinurnaf.tugas4.Database.DatabaseContract.getColumnString;

public class FavoriteAdapter extends CursorAdapter {


    public FavoriteAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_cardview_movie2, viewGroup, false);
    }


    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor != null){
            TextView tvTitle = (TextView)view.findViewById(R.id.tv_item_title);
            TextView tvDescription = (TextView)view.findViewById(R.id.tv_item_description);
            TextView tvRilis = (TextView)view.findViewById(R.id.tv_item_release);
            ImageView imgPhoto = view.findViewById(R.id.img_item_photo);

            tvTitle.setText(getColumnString(cursor,JUDUL));
            tvDescription.setText(getColumnString(cursor,DESKRIPSI));
            tvRilis.setText(getColumnString(cursor,RILIS));
            Glide.with(context)
                    .load(getColumnString(cursor,FOTO))
                    .crossFade()
                    .into(imgPhoto);


        }
    }
}