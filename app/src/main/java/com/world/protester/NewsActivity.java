package com.world.protester;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class NewsActivity extends AppCompatActivity {

    TextView mTvTitle;
    TextView mTvContent;

    ImageView mEventImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        this.mTvTitle = this.findViewById(R.id.tvTitle);
        this.mTvContent = this.findViewById(R.id.tvText);
        this.mEventImage = this.findViewById(R.id.ivNewsImage);

        Bundle arguments = getIntent().getExtras();

        assert arguments != null;

        this.mTvTitle.setText(arguments.getString("title"));
        this.mTvContent.setText(arguments.getString("text"));

        Picasso.get().load(arguments.getString("url")).into(this.mEventImage);
    }


}
