package com.example.onlineshop.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.onlineshop.R;
import com.example.onlineshop.SliderAdapter;
import com.example.onlineshop.model.ImagesItem;
import com.example.onlineshop.model.ProductsItem;
import com.example.onlineshop.repository.Repository;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";

    private SliderView mSliderView;
    private SliderAdapter mSliderAdapter;
    private Repository mRepository;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRepository = new Repository();
        mRepository.fetchAllProductItemsAsync(new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                setupAdapter(items.get(0).getImages());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        findViews(view);

        return view;
    }

    private void findViews(View view) {
        mSliderView = view.findViewById(R.id.imageSlider);
    }

    private void setupAdapter(List<ImagesItem> imagesItems) {
        mSliderAdapter = new SliderAdapter(getContext(), imagesItems);
        mSliderView.setSliderAdapter(mSliderAdapter);

        //set indicator animation by using IndicatorAnimationType:
        //WORM or THIN_WORM or COLOR or DROP or FILL or NONE or
        // SCALE or SCALE_DOWN or SLIDE and SWAP!!
        mSliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        mSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        mSliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        mSliderView.setIndicatorSelectedColor(Color.WHITE);
        mSliderView.setIndicatorUnselectedColor(Color.GRAY);
        //set scroll delay in seconds :
        mSliderView.setScrollTimeInSec(4);
        mSliderView.startAutoCycle();
    }

}