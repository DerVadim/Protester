package com.world.protester.ui.city;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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
        this.toolsViewModel =
                ViewModelProviders.of(this).get(CityViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        this.recyclerView = root.findViewById(R.id.rvCity);
        this.recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this.getContext());
        this.recyclerView.setLayoutManager(layoutManager);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        String currentCity = SharedPreferencesManager.getCurrentCity(this.getContext());
        if(currentCity!=null){
            navController.navigate(R.id.nav_home);
        }else{
            SharedPreferencesManager.setCurrentCity(this.getContext(),citys[0]);
            currentCity=citys[0];
        }

        this.mAdapter = new AdaptersCitys(citys,currentCity,this.getContext());
        this.recyclerView.setAdapter(this.mAdapter);
    }
}