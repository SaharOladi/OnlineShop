package com.example.onlineshop.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;


import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ProductAdapter;
import com.example.onlineshop.adapter.SliderAdapter;
import com.example.onlineshop.model.ImagesItem;
import com.example.onlineshop.model.ProductsItem;
import com.example.onlineshop.repository.Repository;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.w3c.dom.Text;

import java.util.List;


public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";

    private SliderView mSliderView;
    private SliderAdapter mSliderAdapter;


    private RecyclerView mRecyclerViewRecentProduct;
    private RecyclerView mRecyclerViewMostVisitedProduct;
    private RecyclerView mRecyclerViewRatedProduct;


    private ProductAdapter mRecentProductAdapter;
    private ProductAdapter mMostVisitedProductAdapter;
    private ProductAdapter mRatedProductAdapter;




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
        setHasOptionsMenu(true);

        mRepository = new Repository();

        mRepository.fetchAllProductItemsAsync(new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                setupSliderAdapter(items.get(0).getImages());
            }
        });

        mRepository.fetchRecentProducts(1, new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                initRecentRecyclerAdapter(items);
            }
        });

        mRepository.fetchMostVisitedProducts(1, new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                initMostVisitedRecyclerAdapter(items);
            }
        });

        mRepository.fetchRatedProducts(1, new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                initRatedRecyclerAdapter(items);
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
        mRecyclerViewRecentProduct = view.findViewById(R.id.recent_recycler_product);
        mRecyclerViewMostVisitedProduct = view.findViewById(R.id.most_visited_recycler_product);
        mRecyclerViewRatedProduct = view.findViewById(R.id.top_rated_recycler_product);

    }

    private void initRecentRecyclerAdapter(List<ProductsItem> productsItems) {

        mRecyclerViewRecentProduct.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));


        updateRecentRecyclerAdapter(productsItems);
    }

    private void initMostVisitedRecyclerAdapter(List<ProductsItem> productsItems) {

        mRecyclerViewMostVisitedProduct.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        updateMostVisitedRecyclerAdapter(productsItems);
    }

    private void initRatedRecyclerAdapter(List<ProductsItem> productsItems) {

        mRecyclerViewRatedProduct.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        updateRatedRecyclerAdapter(productsItems);
    }


    public void updateRecentRecyclerAdapter(List<ProductsItem> productsItems) {

        if (mRecentProductAdapter == null) {
            mRecentProductAdapter = new ProductAdapter(getContext(), productsItems);
            mRecyclerViewRecentProduct.setAdapter(mRecentProductAdapter);
        } else {
            mRecentProductAdapter.setProductsItem(productsItems);
            mRecentProductAdapter.notifyDataSetChanged();
        }
    }

    public void updateMostVisitedRecyclerAdapter(List<ProductsItem> productsItems) {

        if (mMostVisitedProductAdapter == null) {
            mMostVisitedProductAdapter = new ProductAdapter(getContext(), productsItems);
            mRecyclerViewMostVisitedProduct.setAdapter(mMostVisitedProductAdapter);
        } else {
            mMostVisitedProductAdapter.setProductsItem(productsItems);
            mMostVisitedProductAdapter.notifyDataSetChanged();
        }
    }

    public void updateRatedRecyclerAdapter(List<ProductsItem> productsItems) {

        if (mRatedProductAdapter == null) {
            mRatedProductAdapter = new ProductAdapter(getContext(), productsItems);
            mRecyclerViewRatedProduct.setAdapter(mRatedProductAdapter);
        } else {
            mRatedProductAdapter.setProductsItem(productsItems);
            mRatedProductAdapter.notifyDataSetChanged();
        }
    }

    private void setupSliderAdapter(List<ImagesItem> imagesItems) {
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