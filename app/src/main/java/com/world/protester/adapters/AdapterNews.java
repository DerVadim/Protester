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
import com.world.protester.wraps.NewsWrap;

import java.util.ArrayList;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.NewsViewHolder>  {

    private ArrayList<NewsWrap> mDataset;

    static class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView tvNews;
        ImageView ivNews;
        RelativeLayout rlNews;

        NewsViewHolder(View v) {
            super(v);

            tvNews = v.findViewById(R.id.tvNewsTitle);
            ivNews = v.findViewById(R.id.ivNewsImage);
            rlNews = v.findViewById(R.id.rlNews);
        }
    }

    public AdapterNews(ArrayList<NewsWrap> myDataset) {
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


        final NewsWrap newsWrap = mDataset.get(position);

        holder.tvNews.setText(newsWrap.getTitle());
        holder.rlNews.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(holder.rlNews.getContext(), NewsActivity.class);
                intent.putExtra("title",newsWrap.getTitle());
                intent.putExtra("text",newsWrap.getText());
                holder.rlNews.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
