package com.example.asus.myn_attraction.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.myn_attraction.R;
import com.example.asus.myn_attraction.data.vos.AttractionVO;
import com.example.asus.myn_attraction.fragments.AttractionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Asus on 7/6/2016.
 */
public class AttractionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.tv_attraction_title)
    TextView tvAttractionTitle;
    @BindView(R.id.iv_attraction_photo)
    ImageView ivAttractionPhoto;
    @BindView(R.id.tv_attraction_desc)
    TextView tvAttractionDesc;

    private AttractionVO mAttraction;
    private AttractionFragment.ControllerAttraction mAttractionController;

    public AttractionViewHolder(View view, AttractionFragment.ControllerAttraction attractionController) {
        super(view);
        ButterKnife.bind(this,view);
        view.setOnClickListener(this);
        mAttractionController = attractionController;
    }

    public void setData(AttractionVO attraction) {
        this.mAttraction = attraction;

        tvAttractionTitle.setText(attraction.getTitle());
        tvAttractionDesc.setText(attraction.getDesc());

        Glide.with(ivAttractionPhoto.getContext())
                .load(attraction.getImages())
                .centerCrop()
                .placeholder(R.drawable.images)
                .into(ivAttractionPhoto);
    }

    @Override
    public void onClick(View view) {
        mAttractionController.onTapEvent(mAttraction, ivAttractionPhoto);
    }
}
