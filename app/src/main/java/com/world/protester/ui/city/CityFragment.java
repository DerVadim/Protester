package com.world.protester.ui.city;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.world.protester.ProtesterApplication;
import com.world.protester.R;
import com.world.protester.adapters.AdaptersCitys;
import com.world.protester.tools.SharedPreferencesManager;

import java.util.Objects;


public class CityFragment extends Fragment {

    private Button btnNext;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private String[] citys = {"Moscow", "SPB"};

    private boolean mFromSplash = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        this.recyclerView = root.findViewById(R.id.rvCity);
        this.recyclerView.setHasFixedSize(true);
        this.btnNext = root.findViewById(R.id.btn_next);

        layoutManager = new LinearLayoutManager(this.getContext());
        this.recyclerView.setLayoutManager(layoutManager);

        try{
            FragmentActivity mainActivity = Objects.requireNonNull(getActivity());

            this.mFromSplash = Objects.requireNonNull(mainActivity
                    .getIntent()
                    .getExtras())
                    .getBoolean(ProtesterApplication.DATA_FLAG_SPLASH);

            mainActivity.getIntent().removeExtra(ProtesterApplication.DATA_FLAG_SPLASH);

        }catch (NullPointerException e){
            Log.d(this.getClass().getName(),"Error extract data from main activity");
        }


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);
        String currentCity = SharedPreferencesManager.getCurrentCity(this.getContext());

        if(SharedPreferencesManager.isFirstLaunch(this.getContext())){

            SharedPreferencesManager.disableFirstLaunch(this.getContext());
            this.btnNext.setVisibility(View.VISIBLE);
            this.btnNext.setOnClickListener(v -> {
                navController.navigate(R.id.nav_events);
                ((AppCompatActivity)getActivity()).getSupportActionBar().show();
            });

            ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
            SharedPreferencesManager.setCurrentCity(this.getContext(),citys[0]);

            currentCity=citys[0];
        }else if(this.mFromSplash)
            navController.navigate(R.id.nav_events);


        this.mAdapter = new AdaptersCitys(citys,currentCity,this.getContext());
        this.recyclerView.setAdapter(this.mAdapter);


    }


}