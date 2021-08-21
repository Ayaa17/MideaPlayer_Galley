package com.example.test12;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;



import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;


public class VActivity extends AppCompatActivity {

    TextView textView ;
    String TAG= "mmm";

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_vactivity);
        textView= findViewById(R.id.textViewV2);

        textView.setText("0000");


        navController = Navigation.findNavController(this, R.id.fragmentContainerViewV);
        NavigationUI.setupActionBarWithNavController(this, navController);

    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }


//    public void back() {
//        Toast.makeText(VActivity.this, "left count:"+getSupportFragmentManager().getBackStackEntryCount(), Toast.LENGTH_SHORT).show();
//        if(getSupportFragmentManager().getBackStackEntryCount() <= 0)//这里是取出我们返回栈存在Fragment的个数
//            finish();
//        else
//            getSupportFragmentManager().popBackStack();
//        //取出我们返回栈保存的Fragment,这里会从栈顶开始弹栈
//
//    }


}





