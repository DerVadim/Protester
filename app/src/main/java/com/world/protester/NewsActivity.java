package com.world.protester;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class NewsActivity extends AppCompatActivity {

    TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        this.mTvTitle = this.findViewById(R.id.tvTitle);

        Bundle arguments = getIntent().getExtras();
        this.mTvTitle.setText(arguments.getString("title"));
    }


}
