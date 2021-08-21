package com.example.test12;



import android.app.Activity;
import android.app.Application;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.io.File;
import java.io.IOException;





public class PlayViewModel extends ViewModel   {

    public   MediaPlayer mediaPlay ;
    private  MutableLiveData _progressBarVisibilty= new MutableLiveData(View.VISIBLE);
    LiveData progressBarVisibilty= _progressBarVisibilty;
    private MutableLiveData _videoResolution = new MutableLiveData(new Pair(0,0));
    LiveData<Pair<Integer,Integer>> videoResolution =_videoResolution;
//    private String path3="https://vod-progressive.akamaized.net/exp=1624535498~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F788%2F13%2F328940142%2F1290021507.mp4~hmac=5bef0a1be2c6b135c68b28a38cdbf282c02b87c6442bbb7f2a63c89ae572dfa7/vimeo-prod-skyfire-std-us/01/788/13/328940142/1290021507.mp4?filename=Ranunculus+-+22634.mp4";
//    private String path4="https://player.vimeo.com/external/328940142.hd.mp4?s=1ea57040d1487a6c9d9ca9ca65763c8972e66bd4&profile_id=172";
//    private String path5="https://vod-progressive.akamaized.net/exp=1624598543~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F788%2F13%2F328940142%2F1290021507.mp4~hmac=208927d1999017a1afa9c66a2261c9b6a03ff757e524ecb19282a734dd29950b/vimeo-prod-skyfire-std-us/01/788/13/328940142/1290021507.mp4?filename=Ranunculus+-+22634.mp4";
    private Runnable runnable;
//    String videoPathLocal = ("android.resource://" + "com.example.test05" + "/" + R.raw.video2);
//    private static final Uri vedio1 = Uri.fromFile(new File("android.resource://" + "com.example.test05" + "/" + R.raw.video2));
//    private static final Uri vedio5 =Uri.parse("https://www.free-stock-music.com/music/mixaund-team-spirit.mp3");





    public void Init(String Urlpath) {
        try {

            Loadvideo(Urlpath);
//            mediaPlay.prepare();
            int i=mediaPlay.getDuration();
            Log.d("22364", "Loadvideo:。 "+i);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        mediaPlay.start();
    }

    public void Loadvideo(String Urlpath) throws IOException {



        _progressBarVisibilty.setValue(View.VISIBLE);

        mediaPlay = new MediaPlayer();
        mediaPlay.setDataSource(Urlpath);
//        mediaPlay.prepare();
        mediaPlay.prepareAsync();



        mediaPlay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                _progressBarVisibilty.setValue(View.INVISIBLE);
                mp.setLooping(true);

                mp.start();
            }
        });

        mediaPlay.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                _videoResolution.setValue(new Pair(width, height));
            }
        });
//        mediaPlay.prepareAsync();
    }
    private void OnCleared(){
        super.onCleared();
        mediaPlay.release();
    }


}
