package com.example.dell.ccc0921.di;

import com.example.dell.ccc0921.utils.HttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Moudleimp implements Icontract.imoudle{
    private String path="http://172.17.8.100/movieApi/cinema/v1/findRecommendCinemas?longitude=116.30551391385724&latitude=40.04571807462411&page=1&count=10";

    @Override
    public void requestdata(final callisten callisten) {
        HttpUtils.getstance().getdta(path, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                callisten.requestmsg(s);
            }
        });
    }
}
