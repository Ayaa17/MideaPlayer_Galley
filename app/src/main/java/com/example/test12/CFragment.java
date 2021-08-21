package com.example.test12;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;
import java.util.Random;


public class CFragment extends Fragment {
    VideoViewModel myViewModel;
    ResAdaptaVideo resAdapta;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_c, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = requireActivity().findViewById(R.id.recycleV);
        swipeRefreshLayout = requireActivity().findViewById(R.id.swipefreshLayoutV);
        myViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        resAdapta = new ResAdaptaVideo();
        recyclerView.setAdapter(resAdapta);
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 4));
        textView = requireView().findViewById(R.id.textView5);



        if (myViewModel.getVideoListLive().getValue() == null) {
            myViewModel.fetchData();
            Log.d("TAG", "onActivityCreated: fetch");
        }
        myViewModel.getVideoListLive().observe(getViewLifecycleOwner(), videoItems -> {
            resAdapta.submitList(videoItems);
            swipeRefreshLayout.setRefreshing(false);
        });

        swipeRefreshLayout.setOnRefreshListener(() -> myViewModel.fetchData());


//        MutableLiveData<List<VideoItem>>  videoListLive = new MutableLiveData<>();
//        String url = "https://pixabay.com/api/videos/?key=22167844-d92b8b89fcc3b846e88653da4&q=yellow+flowers"+"&per_page=10";
//        //????????????????????????
//        RequestQueue queue= Volley.newRequestQueue(this.getContext());
//        //抓取HTML
//        StringRequest stringRequest = new StringRequest(
//                Request.Method.GET,
//                url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Pixabay_video pixabay_video = new Gson().fromJson(response, Pixabay_video.class);
//                        videoListLive.setValue(pixabay_video.hits);
////                        textView.setText(response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d("TAG", "onErrorResponse: "+error);
//                    }
//                });
//        queue.add(stringRequest);



    }


}