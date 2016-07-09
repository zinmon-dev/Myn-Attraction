package com.example.asus.myn_attraction.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.myn_attraction.MynAttrApp;
import com.example.asus.myn_attraction.R;
import com.example.asus.myn_attraction.data.vos.AttractionVO;
import com.example.asus.myn_attraction.fragments.AttractionFragment;
import com.example.asus.myn_attraction.views.holders.AttractionViewHolder;

import java.util.List;

/**
 * Created by Asus on 7/6/2016.
 */
public class AttractionAdapter extends RecyclerView.Adapter<AttractionViewHolder> {

    private LayoutInflater inflater;
    private List<AttractionVO> attractionlist;
    private AttractionFragment.ControllerAttraction mAttractionController;

    public AttractionAdapter(List<AttractionVO> attractionlist, AttractionFragment.ControllerAttraction attractionController) {
        inflater = LayoutInflater.from(MynAttrApp.getContext());
        this.attractionlist = attractionlist;
        mAttractionController = attractionController;
    }

    @Override
    public AttractionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_attraction_list, parent, false);
        final AttractionViewHolder attractionVH = new AttractionViewHolder(view, mAttractionController);
        return attractionVH;
    }

    @Override
    public void onBindViewHolder(AttractionViewHolder holder, int position) {
        holder.setData(attractionlist.get(position));
    }

    @Override
    public int getItemCount() {
        return attractionlist.size();
    }
}