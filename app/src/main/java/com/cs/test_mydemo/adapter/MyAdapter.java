package com.cs.test_mydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cs.test_mydemo.R;
import com.cs.test_mydemo.entry.News;

import java.util.List;

/**
 * Created by chenshuai on 2016/11/4.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{
    private Context context;
    private List<News.ResultBean.DataBean> list;

    public MyAdapter(Context context, List<News.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.title.setText(list.get(position).getAuthor_name());
        holder.content.setText(list.get(position).getTitle());
        Glide.with(context)
                .load(list.get(position).getThumbnail_pic_s())
                .override(100,100)
                .placeholder(R.mipmap.noloading)
                .error(R.mipmap.nosccess)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()//淡入淡出的动画
                .centerCrop()
                .into(holder.imageView);
        /*Picasso.with(context)
                .load(list.get(position).getThumbnail_pic_s())
                .resize(100,100)
                .placeholder(R.mipmap.noloading)
                .error(R.mipmap.nosccess)
                .centerCrop()
                .into(holder.imageView);*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title;
        public TextView content;
        public MyHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.iv_img);
            title= (TextView) itemView.findViewById(R.id.tv_title);
            content= (TextView) itemView.findViewById(R.id.tv_content);

        }
    }

}
