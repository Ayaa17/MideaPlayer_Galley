package com.example.test12;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import io.supercharge.shimmerlayout.ShimmerLayout;


public class BFragment extends Fragment {
    ShimmerLayout shimmerLayout;
    ImageView imageView;
    TextView textView,textView2;
    Button button;
//    NavHostFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textView = requireView().findViewById(R.id.textView3);
        textView2 = requireView().findViewById(R.id.textView4);
        shimmerLayout = requireView().findViewById(R.id.photo_shimmer);
        imageView = requireView().findViewById(R.id.photo_view);
        button = requireView().findViewById(R.id.button);

        shimmerLayout.setShimmerColor(0x55ffffff);
        shimmerLayout.setShimmerAngle(0);
        shimmerLayout.startShimmerAnimation();

        Bundle bundle = getArguments();
        assert bundle != null;
        PhotoItem photoItem = bundle.getParcelable("PHOTO");
        Log.d("TAG", "onActivityCreated: "+photoItem.photoId);
//        textView.setText(photoItem.photoId);
        textView.setText("id :"+photoItem.photoId);
        textView2.setText("url :"+photoItem.fullUrl);
        Glide.with(requireContext())
                .load(photoItem.fullUrl)
                .placeholder(R.drawable.ic_baseline_image_24)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        shimmerLayout.stopShimmerAnimation();
                        return false;
                    }
                })
                .into(imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                NavHostFragment.findNavController(BFragment.this).navigate(R.id.action_BFragment_to_AFragment);
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_BFragment_to_AFragment, bundle);
                return ;
//                finishOnTaskLaunch
            }
        });
    }



}