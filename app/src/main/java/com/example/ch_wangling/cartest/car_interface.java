package com.example.ch_wangling.cartest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

/**
 * Created by CH_WangLing on 2018/1/29.
 */

public interface car_interface {

    @Streaming
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%30world")
    Call<ResponseBody> getCall();
}
