package com.example.test12;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pixabay_video {
    int total;
    int totalHits;
    List<VideoItem> hits;

}
class VideoItemInfo {
    @SerializedName("tiny")
    VideoInfo videoInfo;
}
class VideoInfo implements  Parcelable{
    String url;
    int width;
    int hight;
    protected VideoInfo(Parcel in) {
        url = in.readString();
        width = in.readInt();
        hight = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(width);
        dest.writeString(url);
        dest.writeInt(hight);


    }
}

class VideoItem implements Parcelable {
    @SerializedName("id")
    int videoID;
    @SerializedName("userImageURL")
    String previewUrl;
    @SerializedName("pageURL")
    String fullUrl;
    @SerializedName("picture_id")
    String imgID;
    @SerializedName("videos")
    VideoItemInfo test;
//    @SerializedName("videos")
//    List<VideoItemSizeInfo> videoItemSizeInfo;

    protected VideoItem(Parcel in) {
        videoID = in.readInt();
        previewUrl = in.readString();
        fullUrl = in.readString();
        imgID = in.readString();


    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(videoID);
        dest.writeString(previewUrl);
        dest.writeString(fullUrl);
        dest.writeString(imgID);
//        dest.writeString(test);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VideoItem> CREATOR = new Creator<VideoItem>() {
        @Override
        public VideoItem createFromParcel(Parcel in) {
            return new VideoItem(in);
        }

        @Override
        public VideoItem[] newArray(int size) {
            return new VideoItem[size];
        }
    };
}
;