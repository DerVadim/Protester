package com.world.protester.adapters;

import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
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
import org.threeten.bp.LocalDate;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Period;
import org.threeten.bp.format.DateTimeParseException;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.format.DateTimeFormatter;

public class AdapterEvents extends RecyclerView.Adapter<AdapterEvents.NewsViewHolder>  {

    private ArrayList<EventWrap> mDataset;

    private Resources mResources;


    static class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView tvNews;
        ImageView ivNews;
        RelativeLayout rlNews;
        TextView tvDate;

        NewsViewHolder(View v) {
            super(v);

            tvNews = v.findViewById(R.id.tvNewsTitle);
            ivNews = v.findViewById(R.id.ivNewsImage);
            rlNews = v.findViewById(R.id.rlNews);
            tvDate = v.findViewById(R.id.tvDate);

        }

        void loadImage(String url){
            Picasso.get().load(url).into(ivNews);
        }
    }

    public AdapterEvents(ArrayList<EventWrap> myDataset,Resources resources) {
        mDataset = myDataset;
        this.mResources=resources;
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

        holder.tvDate.setText(this.getDate(eventWrap.getCreatedOn()));

        holder.rlNews.setOnClickListener(v -> {
            Intent intent = new Intent(holder.rlNews.getContext(), NewsActivity.class);
            intent.putExtra("title", eventWrap.getTitle());
            intent.putExtra("text", eventWrap.getContent());
            intent.putExtra("url",imageUrl);
            holder.rlNews.getContext().startActivity(intent);
        });

    }

    private String getDate(String dateString){

        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse("05-04-2020", formatter);

            LocalDate dateNow = LocalDate.now();

            int value = date.compareTo(dateNow);
            // data now == data of event show "today"
            if(value == 0)
                return this.mResources.getString(R.string.fragment_events_today);
                // data now > data of event show "ended"
            else if(value<0){
                return this.mResources.getString(R.string.fragment_events_ended);
            }else{

                Period period = Period.between(dateNow, date);
                int diff = period.getDays();

                if( diff <7) {
                    DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
                    switch (day) {
                        case MONDAY:
                            return this.mResources.getString(R.string.fragment_events_this_monday);
                        case TUESDAY:
                            return this.mResources.getString(R.string.fragment_events_this_tuesday);
                        case WEDNESDAY:
                            return this.mResources.getString(R.string.fragment_events_this_wednesday);
                        case THURSDAY:
                            return this.mResources.getString(R.string.fragment_events_this_thursday);
                        case FRIDAY:
                            return this.mResources.getString(R.string.fragment_events_this_friday);
                        case SATURDAY:
                            return this.mResources.getString(R.string.fragment_events_this_saturday);
                        case SUNDAY:
                            return this.mResources.getString(R.string.fragment_events_this_sunday);
                    }
                }
            }

            return date.toString();

        }catch (DateTimeParseException e){
            Log.d(ProtesterApplication.PROTESTER,"Error parsing date!");
            return dateString;
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
