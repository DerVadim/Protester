package com.world.protester.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.world.protester.NewsActivity;
import com.world.protester.R;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.NewsViewHolder>  {

    private String[] mDataset;

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNews;
        public ImageView ivNews;
        public RelativeLayout rlNews;

        public NewsViewHolder(View v) {
            super(v);

            tvNews = v.findViewById(R.id.tvNewsTitle);
            ivNews = v.findViewById(R.id.ivNewsImage);
            rlNews = v.findViewById(R.id.rlNews);
        }
    }

    public AdapterNews(String[] myDataset) {
        mDataset = myDataset;
    }

    @Override
    public AdapterNews.NewsViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news, parent, false);
        return new NewsViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, int position) {


        final String title = mDataset[position];

        holder.tvNews.setText(title);
        holder.rlNews.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(holder.rlNews.getContext(), NewsActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("text",title);
                holder.rlNews.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

}
