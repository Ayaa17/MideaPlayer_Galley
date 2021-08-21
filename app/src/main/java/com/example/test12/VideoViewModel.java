package com.example.test12;


import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.List;
import java.util.Random;

public class VideoViewModel extends AndroidViewModel {
    public MutableLiveData<List<VideoItem>> videoListLive;

    String[] keyWords = new String[]{"cat", "dog", "car", "beauty", "phone", "computer", "flower", "animal"};

    public VideoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<VideoItem>> getVideoListLive() {
        if (videoListLive == null) {
            videoListLive = new MutableLiveData<>();
        }

        return videoListLive;
    }

    private String getUrl() {
//        https://pixabay.com/api/?key=22167844-d92b8b89fcc3b846e88653da4&q=yellow+flowers&image_type=photo
        return "https://pixabay.com/api/?key=22167844-d92b8b89fcc3b846e88653da4&q="
                + keyWords[new Random().nextInt(keyWords.length)]
                + "&per_page=10";
    }

    private String getVideoUrl(){
        return "https://pixabay.com/api/videos/?key=22167844-d92b8b89fcc3b846e88653da4&q="
                + keyWords[new Random().nextInt(keyWords.length)]
                + "&per_page=16";

//        return "https://pixabay.com/api/videos/?key=22167844-d92b8b89fcc3b846e88653da4&q=yellow+flowers"+"&per_page=10";
    }


    void fetchData() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                getVideoUrl(),
                response -> {
                    Pixabay_video pixabay_video = new Gson().fromJson(response, Pixabay_video.class);
                    videoListLive.setValue(pixabay_video.hits);
                },
                error -> Log.d("TAG", error.toString())
        );
        VolleySingleton.getINSTANCE(getApplication()).getRequestQueue().add(stringRequest);
    }

}

