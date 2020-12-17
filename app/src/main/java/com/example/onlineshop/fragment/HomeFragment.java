package com.example.onlineshop.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
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

import java.nio.file.Watchable;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    public static final String TAG = "HomeFragment";
    public static final int SPAN_COUNT = 2;

    private SliderView mSliderView;
    private SliderAdapter mSliderAdapter;


    private RecyclerView mRecyclerViewRecentProduct;
    private RecyclerView mRecyclerViewMostVisitedProduct;
    private RecyclerView mRecyclerViewRatedProduct;
    private RecyclerView mRecyclerView;


    private ProductAdapter mRecentProductAdapter;
    private ProductAdapter mMostVisitedProductAdapter;
    private ProductAdapter mRatedProductAdapter;
    private ProductAdapter mProductAdapter;


    private EditText mSearch;
    private TextView mNew, mRate, mMost;
    private List<ProductsItem> mAllProductsItems = new ArrayList<>();
    private List<ProductsItem> mRecentProductItems = new ArrayList<>();
    private List<ProductsItem> mRatedProductItems = new ArrayList<>();
    private List<ProductsItem> mMostVisitedProductsItems = new ArrayList<>();



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
                initRecyclerAdapter(items);
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

        setSearchListener();



        return view;
    }

    private void setSearchListener() {
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
                returnToRoot();
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
                returnToRoot();
            }
        });

    }

    private void findViews(View view) {
        mSliderView = view.findViewById(R.id.imageSlider);
        mRecyclerViewRecentProduct = view.findViewById(R.id.recent_recycler_product);
        mRecyclerViewMostVisitedProduct = view.findViewById(R.id.most_visited_recycler_product);
        mRecyclerViewRatedProduct = view.findViewById(R.id.top_rated_recycler_product);
        mRecyclerView = view.findViewById(R.id.search_recyclerView);
        mSearch = view.findViewById(R.id.search_query);
        mNew = view.findViewById(R.id.text_new);
        mMost = view.findViewById(R.id.text_most);
        mRate = view.findViewById(R.id.text_rate);

    }

    private void initRecentRecyclerAdapter(List<ProductsItem> productsItems) {

        mRecyclerViewRecentProduct.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));


        updateRecentRecyclerAdapter(productsItems);
        mRecentProductItems = productsItems;
    }

    private void initMostVisitedRecyclerAdapter(List<ProductsItem> productsItems) {

        mRecyclerViewMostVisitedProduct.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        updateMostVisitedRecyclerAdapter(productsItems);
        mMostVisitedProductsItems = productsItems;
    }

    private void initRatedRecyclerAdapter(List<ProductsItem> productsItems) {

        mRecyclerViewRatedProduct.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        updateRatedRecyclerAdapter(productsItems);
        mRatedProductItems = productsItems;
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

    private void initRecyclerAdapter(List<ProductsItem> productsItems) {

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));

        updateRecyclerAdapter(productsItems);
        mAllProductsItems = productsItems;
    }

    public void updateRecyclerAdapter(List<ProductsItem> productsItems) {

        if (mProductAdapter == null) {
            mProductAdapter = new ProductAdapter(getContext(), productsItems);
            mRecyclerView.setAdapter(mProductAdapter);
        } else {
            mProductAdapter.setProductsItem(productsItems);
            mProductAdapter.notifyDataSetChanged();
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

    @RequiresApi(api = Build.VERSION_CODES.M)

    private void filter(String query) {
        List<ProductsItem> searchList = new ArrayList<>();
        if (mProductAdapter != null && !query.equals(null)) {
            for (ProductsItem productsItem :mAllProductsItems) {
                if (productsItem.getName().toLowerCase().contains(query.toLowerCase()))
                    searchList.add(productsItem);
            }
            mProductAdapter.filterList(searchList);
            mRecyclerViewRecentProduct.setVisibility(View.GONE);
            mRecyclerViewRatedProduct.setVisibility(View.GONE);
            mRecyclerViewMostVisitedProduct.setVisibility(View.GONE);
            mSliderView.setVisibility(View.GONE);
            mNew.setVisibility(View.GONE);
            mMost.setVisibility(View.GONE);
            mRate.setVisibility(View.GONE);

            mRecyclerView.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mRecyclerView.setForegroundGravity(Gravity.CENTER);
            }

        }
        Log.d(TAG, "filter: "+mAllProductsItems.size());

    }

    private void returnToRoot(){
        if(mSearch.length()==0){
            mRecyclerViewRecentProduct.setVisibility(View.VISIBLE);
            initRecentRecyclerAdapter(mRecentProductItems);
            mRecyclerViewRatedProduct.setVisibility(View.VISIBLE);
            initRatedRecyclerAdapter(mRatedProductItems);
            mRecyclerViewMostVisitedProduct.setVisibility(View.VISIBLE);
            initMostVisitedRecyclerAdapter(mMostVisitedProductsItems);
            mSliderView.setVisibility(View.VISIBLE);
            mNew.setVisibility(View.VISIBLE);
            mMost.setVisibility(View.VISIBLE);
            mRate.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }



}