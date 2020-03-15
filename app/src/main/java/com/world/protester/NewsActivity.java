package com.world.protester;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewsActivity extends AppCompatActivity {

    TextView mTvTitle;
    TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        this.mTvTitle = this.findViewById(R.id.tvTitle);
        this.mTvContent = this.findViewById(R.id.tvText);

        Bundle arguments = getIntent().getExtras();

        assert arguments != null;

        this.mTvTitle.setText(arguments.getString("title"));
        this.mTvContent.setText(arguments.getString("text"));
    }


}
