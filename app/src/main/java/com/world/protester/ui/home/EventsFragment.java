package com.world.protester.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.world.protester.ProtesterApplication;
import com.world.protester.R;
import com.world.protester.adapters.AdapterEvents;
import com.world.protester.tools.SharedPreferencesManager;
import com.world.protester.wraps.EventWrap;

import java.util.ArrayList;
import java.util.Objects;

public class EventsFragment extends Fragment {

    private ArrayList<EventWrap> mEvents = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private TextView mTvEventError;
    private SwipeRefreshLayout sflEvents;
    private EventsViewModel mEventsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        this.mEventsViewModel = ViewModelProviders.of(this).get(EventsViewModel.class);
        this.mEventsViewModel.getEvents(SharedPreferencesManager.getCurrentCity(this.getActivity()));

        View root = inflater.inflate(R.layout.fragment_events, container, false);
        this.mRecyclerView = root.findViewById(R.id.news_recycler_view);
        //this.mTvEventError=root.findViewById(R.id.tvEventError);
        this.sflEvents = root.findViewById(R.id.sflEvents);


        /**
         * Send request  if device has internet connection
         */
        if(ProtesterApplication.getConnectionStatus(Objects.requireNonNull(this.getContext()))){
            mEventsViewModel.getNewsRepository().observe(this, news -> {

                if(news==null || news.isEmpty()) {
                    showErrorMessege(getString(R.string.common_error_get_data));
                }else {
                    mEvents.addAll(news);
                    this.mAdapter.notifyDataSetChanged();
                }

            });
        }else{
            showErrorMessege(getString(R.string.common_internet_connection_nf));
        }

        mEventsViewModel.getIsLoading().observe(this,Flag-> {

            Log.d("LOADING","Change observe"+String.valueOf(Flag));
            if(Flag){
                this.sflEvents.setRefreshing(true);
            }else{
                this.sflEvents.setRefreshing(false);
            }

        });

        setupRecyclerView();




        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d("LOADING",String.valueOf(mEventsViewModel.getIsLoading().getValue()));

        this.sflEvents.setRefreshing(mEventsViewModel.getIsLoading().getValue());
    }

    private void showErrorMessege(String text){
//        this.mTvEventError.setText(text);
//        this.mTvEventError.setVisibility(View.VISIBLE);
    }

    private void setupRecyclerView() {
        this.mAdapter = new AdapterEvents(mEvents);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(true);

        this.sflEvents.setOnRefreshListener(() -> {
            Log.d("LOADING","OnRefreshListener");
            mEventsViewModel.getEvents(SharedPreferencesManager.getCurrentCity(getActivity()));
    });

    }


}