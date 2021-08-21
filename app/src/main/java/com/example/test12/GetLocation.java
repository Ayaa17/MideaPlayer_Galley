package com.example.test12;

import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class GetLocation {
    String location;
    int responseCode ;
    DefaultHttpClient httpclient;

    public void GetLocation(String url) {
        // TODO Auto-generated method stub
        Log.d("TAG", "GetLocation_1: ");
        httpclient = new DefaultHttpClient();
        location = null;
        responseCode = 0;
        try {
            final HttpGet request = new HttpGet(url);
            HttpParams params = new BasicHttpParams();
//            HttpResponse response=;
            // 默认不让重定向，这样就能拿到Location头了
            params.setParameter("http.protocol.handle-redirects", false);
            request.setParams(params);
            HttpResponse response = httpclient.execute(request);
            responseCode = response.getStatusLine().getStatusCode();
            Header[] headers = response.getAllHeaders();
            Log.d("TAG", "GetLocation_1: "+responseCode);
            if(responseCode==302){
                Header locationHeader = response.getFirstHeader("Location");
                Log.d("12132", "GetLocation_1: 21231564");
                if (locationHeader != null) {
                    location = locationHeader.getValue();
                    Log.d("TAG", "run123: "+location);
//                    return location;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//    return location;
    }
}



