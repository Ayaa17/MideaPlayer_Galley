package com.example.test12;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import io.supercharge.shimmerlayout.ShimmerLayout;

public class ResAdaptaVideo extends ListAdapter<VideoItem, VideoViewHolder> {

     String prefixUrl ="https://i.vimeocdn.com/video/";
     String previewSize = "_512x512.jpg";
    protected ResAdaptaVideo() {
        super(new DiffUtil.ItemCallback<VideoItem>() {
            @Override
            public boolean areItemsTheSame(@NonNull VideoItem oldItem, @NonNull VideoItem newItem) {
                return oldItem.videoID == newItem.videoID;
            }

            @Override
            public boolean areContentsTheSame(@NonNull VideoItem oldItem, @NonNull VideoItem newItem) {
                return oldItem.fullUrl.equals(newItem.fullUrl);
            }
        });

    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VideoViewHolder holder = new VideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_cell, parent, false));
        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable("VIDEO", getItem(holder.getAdapterPosition()));
            NavController navController = Navigation.findNavController(holder.itemView);
            navController.navigate(R.id.action_CFragment_to_DFragment, bundle);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.shimmerLayout.setShimmerColor(0x55ffffff);
        holder.shimmerLayout.setShimmerAngle(0);
        holder.shimmerLayout.startShimmerAnimation();
        String previewImg=prefixUrl+getItem(position).imgID+previewSize;
        Glide.with(holder.itemView)
                .load(previewImg)
                .placeholder(R.drawable.ic_baseline_image_24)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.shimmerLayout.stopShimmerAnimation();
                        return false;
                    }
                })
                .into(holder.imageView);
    }

}

class VideoViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    ShimmerLayout shimmerLayout;
    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.img_view);
        shimmerLayout = itemView.findViewById(R.id.shimmer_layout);
    }
}