package com.world.protester.ui.city;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.world.protester.R;
import com.world.protester.adapters.AdaptersCitys;
import com.world.protester.tools.SharedPreferencesManager;

public class CityFragment extends Fragment {

    private CityViewModel toolsViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] citys = {"Москва", "Санкт-Петербург"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(CityViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        recyclerView = root.findViewById(R.id.rvCity);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

        String currentCity = SharedPreferencesManager.getCurrentCity(this.getContext());

        mAdapter = new AdaptersCitys(citys,currentCity,this.getContext());
        recyclerView.setAdapter(mAdapter);

        /*final TextView textView = root.findViewById(R.id.text_tools);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}