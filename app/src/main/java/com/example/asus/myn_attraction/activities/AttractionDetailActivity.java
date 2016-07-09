package com.example.asus.myn_attraction.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.myn_attraction.MynAttrApp;
import com.example.asus.myn_attraction.R;
import com.example.asus.myn_attraction.data.models.AttractionModel;
import com.example.asus.myn_attraction.data.vos.AttractionVO;

public class AttractionDetailActivity extends AppCompatActivity {
    private static final String IE_ATTR_TITLE = "IE_ATTR_TITLE";
    private ImageView ivAttractionPhoto;
    private TextView tvAttractionDesc;

    private CollapsingToolbarLayout collapsingToolbar;

    public static Intent newIntent(String attractiontTitle) {
        Intent intent = new Intent(MynAttrApp.getContext(), AttractionDetailActivity.class);
        intent.putExtra(IE_ATTR_TITLE, attractiontTitle);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ivAttractionPhoto = (ImageView) findViewById(R.id.iv_attraction_photo);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Context context = MynAttrApp.getContext();
            String transitionName = getResources().getString(R.string.event_stock_photo_shared_transition);
            ivAttractionPhoto.setTransitionName(transitionName);
        }
        ivAttractionPhoto = (ImageView) findViewById(R.id.iv_attraction_photo);
        tvAttractionDesc = (TextView) findViewById(R.id.tv_attraction_desc);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_accent_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        String attractionTitle = getIntent().getStringExtra(IE_ATTR_TITLE);
        final AttractionVO attraction = AttractionModel.getInstance().getAttractionByTitle(attractionTitle);
        if(attraction == null) {
            throw new RuntimeException("Can't find Event obj with the title : "+attractionTitle);
        } else {
            collapsingToolbar.setTitle(attraction.getTitle());
            tvAttractionDesc.setText(attraction.getDesc() + "\n\n" +
                    attraction.getDesc() + "\n\n" +
                    attraction.getDesc() + "\n\n" +
                    attraction.getDesc() + "\n\n" +
                    attraction.getDesc());


            Glide.with(ivAttractionPhoto.getContext())
                    .load(attraction.getImages())
                    .centerCrop()
                    .placeholder(R.drawable.images)
                    .into(ivAttractionPhoto);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_share);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(Intent.createChooser(createShareIntent(attraction.getImages()),"Share image using"));
                Snackbar.make(view, attraction.getImages(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private static Intent createShareIntent(String photopath){

        Intent myShareIntent = new Intent(Intent.ACTION_SEND);
        Uri imageUri = Uri.parse(photopath);
        // Uri imageUri = Uri.parse(photopath);
        myShareIntent.setType("image/*");
        // myShareIntent.setType("text/*");
        //myShareIntent.putExtra(Intent.EXTRA_TEXT, photopath);
        myShareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        //  Toast.makeText(PDACWeek5ExApp.getContext(), photopath,Toast.LENGTH_SHORT).show();

        return myShareIntent;
    }

}
