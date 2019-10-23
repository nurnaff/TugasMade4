package com.example.andinurnaf.tugas4.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.andinurnaf.tugas4.Detail.CustomOnItemClickListener;
import com.example.andinurnaf.tugas4.Detail.DetailActivity;
import com.example.andinurnaf.tugas4.Detail.Movie;
import com.example.andinurnaf.tugas4.Loader.MovieItems;
import com.example.andinurnaf.tugas4.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    //Variable ArrayList dengan Parameter dari Class DataFilter (Nama, ImageID)
    private ArrayList<MovieItems> arrayList;
    private Context context;

    AppCompatActivity myActivity;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<MovieItems> items){
        arrayList = items;
        notifyDataSetChanged();
    }
    private ArrayList<MovieItems> getListMovie() {
        return arrayList;
    }

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgPhoto;
        TextView textViewJudulFilm;
        TextView textViewDeskripsi;
        TextView textViewRilis;
        Button btnDetail;

        ViewHolder(View itemView) {
            super(itemView);

            //Menginisialisasi View-View untuk kita gunakan pada RecyclerView
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            textViewJudulFilm= itemView.findViewById(R.id.tv_item_title);
            textViewDeskripsi = itemView.findViewById(R.id.tv_item_description);
            textViewRilis = itemView.findViewById(R.id.tv_item_release);
            btnDetail = itemView.findViewById(R.id.btn_detail);

        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_movie, parent, false);

        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        //Mengambil Data dari method getNama seseuai Posisi Index pada Class DataFilter
        final String photo = arrayList.get(position).getPhoto();
        final String judul = arrayList.get(position).getJudul();
        final String deskripsi = arrayList.get(position).getDeskripsi();
        final String rilis = arrayList.get(position).getRilis();
        Glide.with(context)
                .load(arrayList.get(position).getPhoto())
                .crossFade()
                .into(holder.imgPhoto);
        holder.textViewJudulFilm.setText(judul);
        holder.textViewDeskripsi.setText(deskripsi);
        holder.textViewRilis.setText(rilis);

        holder.btnDetail.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Movie movie = new Movie();
                movie.setPhoto(getListMovie().get(position).getPhoto());
                movie.setJudul(getListMovie().get(position).getJudul());
                movie.setDeskrisi(getListMovie().get(position).getDeskripsi());
                movie.setRilis(getListMovie().get(position).getRilis());

                myActivity = new AppCompatActivity();
                Intent moveWithObjectIntent = new Intent(context.getApplicationContext(), DetailActivity.class);
                moveWithObjectIntent.putExtra(DetailActivity.EXTRA_MOVIE, movie);
                context.getApplicationContext().startActivity(moveWithObjectIntent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        if (arrayList == null) return 0;
        return arrayList.size();
    }

    void setFilter(ArrayList<MovieItems> filterList){
        arrayList = new ArrayList<>();
        arrayList.addAll(filterList);
        notifyDataSetChanged();
    }

}