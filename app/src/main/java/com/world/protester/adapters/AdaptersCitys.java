package com.world.protester.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import androidx.recyclerview.widget.RecyclerView;
import com.world.protester.R;
import com.world.protester.tools.SharedPreferencesManager;
import com.world.protester.tools.ToastManager;

public class AdaptersCitys extends RecyclerView.Adapter<AdaptersCitys.CitysViewHolder> {

    private String[] mDataset;
    private String   mCurrentCity;
    private Context mContext;

    private int mLastCheckedButton = -1;

    public class CitysViewHolder extends RecyclerView.ViewHolder {

        public RadioButton radioButton;
        public CitysViewHolder(View v) {
            super(v);

            this.radioButton = v.findViewById(R.id.rbCity);
            this.radioButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    String currentCity = SharedPreferencesManager.getCurrentCity(AdaptersCitys.this.mContext);
                    String newCity = ((RadioButton)v).getText().toString();

                    if(!newCity.equals(currentCity)) {
                        SharedPreferencesManager.setCurrentCity(AdaptersCitys.this.mContext, newCity);

                        mLastCheckedButton = getAdapterPosition();

                        notifyDataSetChanged();

                        StringBuilder messege = new StringBuilder();
                        messege.append(AdaptersCitys.this.mContext.getResources().getString(R.string.fragment_city_toas));
                        messege.append(" ");
                        messege.append(newCity);

                        ToastManager.getInstance().showToast(messege.toString(),AdaptersCitys.this.mContext);
                    }
                }
            });
        }
    }

    public AdaptersCitys(String[] myDataset, String currentCity, Context context) {
        mDataset = myDataset;
        this.mCurrentCity = currentCity;
        this.mContext = context;
    }


    @Override
    public AdaptersCitys.CitysViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_city, parent, false);

        return new AdaptersCitys.CitysViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final AdaptersCitys.CitysViewHolder holder, int position) {

        final String title = mDataset[position];
        holder.radioButton.setText(title);





        String currentCity = SharedPreferencesManager.getCurrentCity(AdaptersCitys.this.mContext);

        if(currentCity!=null &&  currentCity.equals(title)){
            AdaptersCitys.this.mLastCheckedButton = position;
            holder.radioButton.setChecked(true);
        }else
            holder.radioButton.setChecked(false);



    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

}
