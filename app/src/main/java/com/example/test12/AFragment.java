package com.example.test12;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


public class AFragment extends Fragment {
    MyViewModel myViewModel;
    ResAdapta resAdapta;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = requireActivity().findViewById(R.id.recycle);
        swipeRefreshLayout = requireActivity().findViewById(R.id.swipefreshLayout);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        resAdapta = new ResAdapta();
        recyclerView.setAdapter(resAdapta);
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 2));

        if (myViewModel.getPhotoListLive().getValue() == null) {
            myViewModel.fetchData();
        }

        myViewModel.getPhotoListLive().observe(getViewLifecycleOwner(), photoItems -> {
            resAdapta.submitList(photoItems);
            swipeRefreshLayout.setRefreshing(false);
        });

        swipeRefreshLayout.setOnRefreshListener(() -> myViewModel.fetchData());
    }

}