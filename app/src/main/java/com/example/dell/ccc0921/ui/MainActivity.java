package com.example.dell.ccc0921.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.ccc0921.R;
import com.example.dell.ccc0921.adapter.MyAdapter;
import com.example.dell.ccc0921.bean.news;
import com.example.dell.ccc0921.di.Icontract;
import com.example.dell.ccc0921.di.Presenterimp;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Icontract.iview {

    private Presenterimp presenterimp;
    private RecyclerView recy_view;
    private ImageView img1;
    private ImageView img2;
    private MyAdapter adapter;
    private TextView gps;
    private ImageView img_qq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recy_view = findViewById(R.id.recy_view);
        img_qq = findViewById(R.id.img_qq);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        gps = findViewById(R.id.gps);
        img_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"dmjfsdjfdjjf",Toast.LENGTH_LONG).show();
                UMShareAPI.get(MainActivity.this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, authListener);
            }
            UMAuthListener authListener = new UMAuthListener() {
                /**
                 * @desc 授权开始的回调
                 * @param platform 平台名称
                 */
                @Override
                public void onStart(SHARE_MEDIA platform) {

                }

                /**
                 * @desc 授权成功的回调
                 * @param platform 平台名称
                 * @param action 行为序号，开发者用不上
                 * @param data 用户资料返回
                 */
                @Override
                public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

                    Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
                    Picasso.with(MainActivity.this).load(data.get("iconurl")).into(img_qq);
                }

                /**
                 * @desc 授权失败的回调
                 * @param platform 平台名称
                 * @param action 行为序号，开发者用不上
                 * @param t 错误原因
                 */
                @Override
                public void onError(SHARE_MEDIA platform, int action, Throwable t) {

                    Toast.makeText(MainActivity.this, "失败：" + t.getMessage(),                                     Toast.LENGTH_LONG).show();
                }

                /**
                 * @desc 授权取消的回调
                 * @param platform 平台名称
                 * @param action 行为序号，开发者用不上
                 */
                @Override
                public void onCancel(SHARE_MEDIA platform, int action) {
                    Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
                }
            };
        });







        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, dingwei.class);
                startActivity(intent);
            }
        });
        presenterimp = new Presenterimp();
        presenterimp.attchview(this);
        presenterimp.requestinfo();
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recy_view.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                adapter.notifyDataSetChanged();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recy_view.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterimp.datachview(this);
    }

    @Override
    public void data(final String s) {
        runOnUiThread(new Runnable() {



            @Override
            public void run() {
                Gson gson = new Gson();
                news news = gson.fromJson(s, news.class);
                List<com.example.dell.ccc0921.bean.news.ResultBean.NearbyCinemaListBean> nearbyCinemaList = news.getResult().getNearbyCinemaList();
                Toast.makeText(MainActivity.this, nearbyCinemaList+"", Toast.LENGTH_SHORT).show();
                LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                recy_view.setLayoutManager(manager);
                adapter = new MyAdapter(MainActivity.this, nearbyCinemaList);
                recy_view.setAdapter(adapter);
            }
        });
    }

    //第三方
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
