package com.example.hellocat2;


import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder> {

    private List<CatModel> arrayListCat;

    public CatAdapter(List<CatModel> userModelList) {
        this.arrayListCat = userModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = arrayListCat.get(position).getTitle();
        String image = arrayListCat.get(position).getImage();
        //Double lat = arrayListCat.get(position).getLat();
        //Double lon = arrayListCat.get(position).getLon();

        Bitmap imageConverter = Utils.StringToBitMap(image);

        holder.tvTitle.setText(title);
        holder.ivCat.setImageBitmap(imageConverter);
        //holder.tvLat.setText(lat);
        //holder.tvLon.setText(lon);

    }

    @Override
    public int getItemCount() {
        return arrayListCat.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageView ivCat;
        private TextView tvLat;
        private TextView tvLon;
        public ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tvTitle);
            ivCat = v.findViewById(R.id.ivCat);
            tvLat = v.findViewById(R.id.tvLat);
            tvLon = v.findViewById(R.id.tvLon);
        }
    }
}