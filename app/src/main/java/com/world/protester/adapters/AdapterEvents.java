package com.world.protester.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.world.protester.NewsActivity;
import com.world.protester.ProtesterApplication;
import com.world.protester.R;
import com.world.protester.wraps.EventWrap;

import java.util.ArrayList;

public class AdapterEvents extends RecyclerView.Adapter<AdapterEvents.NewsViewHolder>  {

    private ArrayList<EventWrap> mDataset;

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

        void loadImage(String url){
            Picasso.get().load(url).into(ivNews);
        }
    }

    public AdapterEvents(ArrayList<EventWrap> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public AdapterEvents.NewsViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news, parent, false);
        return new NewsViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, int position) {


        final EventWrap eventWrap = mDataset.get(position);

        holder.tvNews.setText(eventWrap.getTitle());
        String imageUrl = ProtesterApplication.BASE_URL +
                eventWrap.getImage().substring(1);

        holder.loadImage(imageUrl);

        holder.rlNews.setOnClickListener(v -> {
            Intent intent = new Intent(holder.rlNews.getContext(), NewsActivity.class);
            intent.putExtra("title", eventWrap.getTitle());
            intent.putExtra("text", eventWrap.getContent());
            intent.putExtra("url",imageUrl);
            holder.rlNews.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
