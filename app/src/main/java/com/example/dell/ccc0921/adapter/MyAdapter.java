package com.example.dell.ccc0921.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.ccc0921.R;
import com.example.dell.ccc0921.bean.news;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.holder>{
    private Context context;
    private List<news.ResultBean.NearbyCinemaListBean> list;
    public MyAdapter(Context context, List<news.ResultBean.NearbyCinemaListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, null);
        holder holder = new holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.address.setText(list.get(position).getAddress());
        holder.km.setText(list.get(position).getDistance()+"km");
        String[] split = list.get(position).getLogo().split("\\|");
        Glide.with(context).load(split[0]).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //创建视图
    class holder extends RecyclerView.ViewHolder{

        private final TextView address;
        private final ImageView img;
        private final TextView km;

        public holder(View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.address);
            img = itemView.findViewById(R.id.img);
            km = itemView.findViewById(R.id.km);
        }
    }
}
