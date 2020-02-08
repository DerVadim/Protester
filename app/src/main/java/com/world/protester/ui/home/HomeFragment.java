package com.world.protester.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.world.protester.ProtesterApplication;
import com.world.protester.R;
import com.world.protester.adapters.AdapterNews;
import com.world.protester.tools.SharedPreferencesManager;
import com.world.protester.tools.ToastManager;
import com.world.protester.wraps.NewsWrap;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ArrayList<NewsWrap> mNews = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        NewsViewModel mNewsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        mNewsViewModel.init(SharedPreferencesManager.getCurrentCity(this.getActivity()));

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        this.mRecyclerView = root.findViewById(R.id.news_recycler_view);

        /**
         * Send request  if device has internet connection
         */
        if(ProtesterApplication.getConnectionStatus(this.getContext())){
            mNewsViewModel.getNewsRepository().observe(this, new Observer<List<NewsWrap>>() {
                @Override
                public void onChanged(@Nullable List<NewsWrap> news) {
                    mNews.addAll(news);
                }
            });
        }else{
            ToastManager.getInstance().showToastById
                    (R.string.common_internet_connection_nf,this.getContext());
        }

        setupRecyclerView();

        return root;
    }

    private void setupRecyclerView() {
        if (this.mAdapter == null) {
            this.mAdapter = new AdapterNews(mNews);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setNestedScrollingEnabled(true);
        } else {
            this.mAdapter.notifyDataSetChanged();
        }
    }


}