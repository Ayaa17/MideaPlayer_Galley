package com.example.test12;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.impl.client.DefaultHttpClient;
import io.supercharge.shimmerlayout.ShimmerLayout;



public class DFragment extends Fragment {
    ShimmerLayout shimmerLayout;
    ImageView imageView;
    TextView textView,textView2;
    Button button;

    private PlayViewModel playerViewModel;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
//    private SeekBar seekBar;
//    private ImageView btn_play, btn_pause,btn_next,btn_pre;
//    private TextView currentTimeText,durationTimeText;
    private Runnable runnable;
    private Handler handler= new Handler();
    public String loc;
    String llur="https://vod-progressive.akamaized.net/exp=1624608050~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F788%2F13%2F328940142%2F1290021507.mp4~hmac=47c2dd447737cc508101d581d11c3aed67bcb5bc35a49b55fb714e903783fa45/vimeo-prod-skyfire-std-us/01/788/13/328940142/1290021507.mp4?filename=Ranunculus+-+22634.mp4";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_d, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textView = requireView().findViewById(R.id.textViewV3);
        textView2 = requireView().findViewById(R.id.textViewV4);
        shimmerLayout = requireView().findViewById(R.id.photo_shimmer);
        imageView = requireView().findViewById(R.id.photo_viewV);
        button = requireView().findViewById(R.id.buttonV);

        shimmerLayout.setShimmerColor(0x55ffffff);
        shimmerLayout.setShimmerAngle(0);
        shimmerLayout.startShimmerAnimation();

        Bundle bundle = getArguments();
        assert bundle != null;
        VideoItem videoItem = bundle.getParcelable("VIDEO");
//        Log.d("TAG", "onActivityCreated: "+videoItem.videoID);
        textView.setText("id :"+videoItem.videoID);
        textView2.setText("url :"+videoItem.fullUrl);
        Log.d("123456", "onActivityCreated: "+videoItem.test.videoInfo.url);
        String url = videoItem.test.videoInfo.url;
//        String url="https://www.jd.com";
        Log.d("URL", "onActivityCreated: "+url);
//        Glide.with(requireContext())
//                .load(videoItem.fullUrl)
//                .placeholder(R.drawable.ic_baseline_image_24)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        shimmerLayout.stopShimmerAnimation();
//                        return false;
//                    }
//                })
//                .into(imageView);


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                DefaultHttpClient httpclient = new DefaultHttpClient();
//                String location = null;
//                int responseCode = 0;
//                try {
//                    final HttpGet request = new HttpGet(url);
//                    HttpParams params = new BasicHttpParams();
//                    // 默认不让重定向，这样就能拿到Location头了
//                    params.setParameter("http.protocol.handle-redirects", false);
//                    request.setParams(params);
//                    HttpResponse response = httpclient.execute(request);
//                    responseCode = response.getStatusLine().getStatusCode();
//                    Header[] headers = response.getAllHeaders();
//
//                    if(responseCode==302){
//                        Header locationHeader = response.getFirstHeader("Location");
//                        if (locationHeader != null) {
//                            location = locationHeader.getValue();
//                            Log.d("TAG222323", "run: "+location);
//                            loc =location;
//                            Log.d("2262", "run: "+loc);
//                            Log.d("2262", "run: "+url);
////                            playerViewModel.Init(location);
//
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();


//                            playerViewModel.Init(location);
        sendHTTP(url);
//        try{
//            // delay 1 second
////            Thread.sleep(500);
//
//        } catch(InterruptedException e){
//            e.printStackTrace();
//
//        }
        Log.d("TAG1231231312313", "onActivityCreated: "+loc);




        surfaceView=requireView().findViewById(R.id.surfaceView);
        playerViewModel = new PlayViewModel();

        surfaceHolder=surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                while (loc==null){
                    try{
                        // delay 1 second
                        Thread.sleep(100);
                        Log.d("sleep", "surfaceCreated:delay ");

                    } catch(InterruptedException e){
                        e.printStackTrace();

                    }
                }
                playerViewModel.Init(loc);
                playerViewModel.mediaPlay.setDisplay(holder);
                playerViewModel.mediaPlay.setScreenOnWhilePlaying(true);

//        runnable=new Runnable() {
//            @Override
//            public void run() {
//                seekBar.setProgress(playerViewModel.mediaPlay.getCurrentPosition());
//                handler.postDelayed(this, 0);
//
//            }
//        };
//        int duration =playerViewModel.mediaPlay.getDuration();
//        Log.d("123", "surfaceCreated: "+duration);
//        String sDuration = converFormat(duration);
//        Log.d("123", "surfaceCreated: "+sDuration);
//        durationTimeText.setText(sDuration);
//
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if(fromUser){
//                    //when drag the seek bar
//                    //set progress on the bar
//                    playerViewModel.mediaPlay.seekTo(progress);
//
//                }
//                //Set current position on text view
//                currentTimeText.setText(converFormat(playerViewModel.mediaPlay.getCurrentPosition()));
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
                Log.d("TAG", "surfaceChanged: ");
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                Log.d("TAG", "surfaceDestroyed: ");
                playerViewModel.mediaPlay.stop();
                playerViewModel.mediaPlay.release();
                playerViewModel =null;

            }
        });




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                NavHostFragment.findNavController(BFragment.this).navigate(R.id.action_BFragment_to_AFragment);
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_DFragment_to_CFragment, bundle);







                return ;
//                finishOnTaskLaunch
            }
        });
    }
    private void sendHTTP(String url){

      new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                DefaultHttpClient httpclient = new DefaultHttpClient();
                String location = null;
                int responseCode = 0;
                try {
                    final HttpGet request = new HttpGet(url);
                    HttpParams params = new BasicHttpParams();
                    // 默认不让重定向，这样就能拿到Location头了
                    params.setParameter("http.protocol.handle-redirects", false);
                    request.setParams(params);
                    HttpResponse response = httpclient.execute(request);
                    responseCode = response.getStatusLine().getStatusCode();
                    Header[] headers = response.getAllHeaders();

                    if(responseCode==302){
                        Header locationHeader = response.getFirstHeader("Location");
                        if (locationHeader != null) {
                            location = locationHeader.getValue();
                            Log.d("TAG222323", "run: "+location);
                            loc =location;
                            Log.d("2262", "run: "+loc);
                            Log.d("2262", "run: "+url);
//                            playerViewModel.Init(location);

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("dfrag", "onDestroy: ");
    }
}


