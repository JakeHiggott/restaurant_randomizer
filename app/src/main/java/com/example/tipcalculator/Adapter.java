package com.example.tipcalculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private final ArrayList<Integer> mID;
    private final ArrayList<String> mImageNames;
    private final ArrayList<String> mImages;
    private final ArrayList<Double> mLat;
    private final ArrayList<Double> mLong;

    private final Context context;

    public Adapter(Context context, ArrayList<Integer> mID, ArrayList<String> mImageNames, ArrayList<String> mImages, ArrayList<Double> mLat, ArrayList<Double> mLong) {
        this.mID = mID;
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mLat = mLat;
        this.mLong = mLong;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Log.d(null, "onBindViewHolder");
        Glide.with(context)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        holder.imageName.setText(mImageNames.get(position));
        if(position % 2 == 0) {
            holder.itemView.setBackgroundColor(
                    ContextCompat.getColor(context, R.color.lightTeal)
            );
        }
        holder.parentLayout.setOnClickListener(view -> {
            Log.d(null, "Clicked on "+ mImageNames.get(position));
            Globals g = Globals.getInstance();
            g.FavID = mID.get(position);
            g.FavName = mImageNames.get(position);
            g.FavUrl = mImages.get(position);
            g.FavLong = mLong.get(position);
            g.FavLat = mLat.get(position);
            Context context = view.getContext();
            final Intent intent = new Intent(context, restaurantpage.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView imageName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.imageName);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }

}
