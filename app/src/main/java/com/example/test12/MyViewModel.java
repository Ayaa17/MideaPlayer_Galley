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

public class MyViewModel extends AndroidViewModel {
    public MutableLiveData<List<PhotoItem>> photoListLive;

    String[] keyWords = new String[]{"cat", "dog", "car", "beauty", "phone", "computer", "flower", "animal"};

    public MyViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<PhotoItem>> getPhotoListLive() {
        if (photoListLive == null) {
            photoListLive = new MutableLiveData<>();
        }

        return photoListLive;
    }

    private String getUrl() {
        return "https://pixabay.com/api/?key=19035293-602b39ce6d5e59a1fea3a51b3&q="
                + keyWords[new Random().nextInt(keyWords.length)]
                + "&per_page=100";
    }

    void fetchData() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                getUrl(),
                response -> {
                    Pixabay pixabay = new Gson().fromJson(response, Pixabay.class);
                    photoListLive.setValue(pixabay.hits);
                },
                error -> Log.d("TAG", error.toString())
        );
        VolleySingleton.getINSTANCE(getApplication()).getRequestQueue().add(stringRequest);
    }

}

