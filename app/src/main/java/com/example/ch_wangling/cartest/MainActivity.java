package com.example.ch_wangling.cartest;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get_request();
            }
        });
    }

    //retrofit的基本用法
    public void get_request(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                //需要屏蔽gson分析json这个方法
//                .addConverterFactory(GsonConverterFactory.create())
                .build();

        car_interface carInterface = retrofit.create(car_interface.class);

        Call<ResponseBody> call = carInterface.getCall();

        //call.enqueue开启异步网络请求
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,@NonNull Response<ResponseBody> response) {
                try{
                    //注意空指针异常
                    if (response.body() != null){
                        //注意IO异常
                        try{
                            //实例化一个String变量，来装入获取的json数据，注意格式
                            String jsonStr = new String(response.body().bytes());
                            textView.setText(jsonStr);
                            Log.d("查看获取的数据", jsonStr);
                        }catch (IOException e){
                            e.printStackTrace();
                            Log.d("异常", e.getMessage());
                        }
                    }
                }catch (NullPointerException e){
                    Log.d("异常", e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call,@NonNull Throwable t) {
                Log.d("失败情况", t.getMessage());
            }
        });
    }
}
