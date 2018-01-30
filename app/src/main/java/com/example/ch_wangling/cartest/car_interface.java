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
    //Call方法的里面是ResponseBody，是由okhttp提供的，获取的是返回数据的本身
    //新建一个getCall()方法
    Call<ResponseBody> getCall();
}
