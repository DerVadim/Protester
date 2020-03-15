package com.world.protester.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.world.protester.ProtesterApplication;
import com.world.protester.R;
import com.world.protester.adapters.AdapterEvents;
import com.world.protester.tools.SharedPreferencesManager;
import com.world.protester.tools.ToastManager;
import com.world.protester.wraps.EventWrap;

import java.util.ArrayList;
import java.util.Objects;

public class EventsFragment extends Fragment {

    private ArrayList<EventWrap> mEvents = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        EventsViewModel mEventsViewModel = ViewModelProviders.of(this).get(EventsViewModel.class);
        mEventsViewModel.init(SharedPreferencesManager.getCurrentCity(this.getActivity()));

        View root = inflater.inflate(R.layout.fragment_events, container, false);
        this.mRecyclerView = root.findViewById(R.id.news_recycler_view);

        /**
         * Send request  if device has internet connection
         */
        if(ProtesterApplication.getConnectionStatus(Objects.requireNonNull(this.getContext()))){
            mEventsViewModel.getNewsRepository().observe(this, news -> {
                mEvents.addAll(news);
                this.mAdapter.notifyDataSetChanged();
            });
        }else{
            ToastManager.getInstance().showToastById
                    (R.string.common_internet_connection_nf,this.getContext());
        }

        setupRecyclerView();

        return root;
    }

    private void setupRecyclerView() {
            this.mAdapter = new AdapterEvents(mEvents);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setNestedScrollingEnabled(true);
    }


}