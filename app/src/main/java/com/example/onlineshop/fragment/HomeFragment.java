package com.example.onlineshop.fragment;

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
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";

    private List<ProductsItem> mProductsItems = new ArrayList<>();
    private List<ImagesItem> mImagesItems = new ArrayList<>();
    private List<String> mImagesUrl = new ArrayList<>();


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
                mProductsItems = items;
                mImagesItems = items.get(0).getImages();
                for (int i = 0; i < mImagesItems.size(); i++) {
                    mImagesUrl.add(mImagesItems.get(i).getSrc());
                    System.out.println(mImagesUrl.get(i));
                }

            }
        });
        Log.d(TAG, "onCreate: " + mProductsItems.size());
        Log.d(TAG, "onCreate: " + mImagesItems.size());
        Log.d(TAG, "onCreate: " + mImagesUrl.size());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        findViews(view);
        setupAdapter(mImagesItems);

        return view;
    }

    private void findViews(View view) {
        mSliderView = view.findViewById(R.id.imageSlider);
    }

    private void setupAdapter(List<ImagesItem> imagesItems) {
        mSliderAdapter = new SliderAdapter(getContext(), imagesItems);
        Log.d(TAG, "initViews: imagesItems " + imagesItems.size());
        mSliderView.setSliderAdapter(mSliderAdapter);
    }

}