package com.example.asus.myn_attraction.fragments;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.asus.myn_attraction.R;
import com.example.asus.myn_attraction.adapters.AttractionAdapter;
import com.example.asus.myn_attraction.data.models.AttractionModel;
import com.example.asus.myn_attraction.data.vos.AttractionVO;

/**
 * A placeholder fragment containing a simple view.
 */
public class AttractionFragment extends Fragment {

    private AttractionAdapter mAttractionAdapter;
    private ControllerAttraction mAttractionController;

    public static AttractionFragment newInstance() {
        return new AttractionFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
         mAttractionController = (ControllerAttraction) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAttractionAdapter = new AttractionAdapter(AttractionModel.getInstance().getAttractionList(), mAttractionController);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attraction, container, false);

        RecyclerView rvEvent = (RecyclerView) view.findViewById(R.id.rv_events);
        rvEvent.setAdapter(mAttractionAdapter);
        rvEvent.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);//ms (equal to 3sec)
            }
        });

        return view;
    }

    public interface ControllerAttraction {
        void onTapEvent(AttractionVO attraction, ImageView ivAttractionPhoto);
    }
}
