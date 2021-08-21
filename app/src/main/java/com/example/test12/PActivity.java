package com.example.test12;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class PActivity extends AppCompatActivity {

    TextView textView ;
    String TAG= "mmm";
    String url = "https://pixabay.com/api/?key=22167844-d92b8b89fcc3b846e88653da4&q=yellow+flowers&per_page=3";
    String imageurl2="https://pixabay.com/get/g37438b409f2acefc7f13f7bbc6da70eff8f80b5879efcde2be4a03cd4e6bdad9cefef9e2e8738670d5e20d96ef28ac11ec23b942baab2d7065d95e608a98bd9a_1280.jpg";

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pactivity);
        textView= findViewById(R.id.textView2);

        textView.setText("0000");


        navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }

}




