package com.example.onlineshop.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;


import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ProductAdapter;
import com.example.onlineshop.adapter.SliderAdapter;
import com.example.onlineshop.model.ImagesItem;
import com.example.onlineshop.model.ProductsItem;
import com.example.onlineshop.repository.Repository;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        findViews(view);

        updateRecycler();

        return view;
    }

    private void updateRecycler() {
        mRepository.fetchSingleProduct(608, new Repository.SingleCallbacks() {
            @Override
            public void onItemResponse(ProductsItem item) {
                setupSliderAdapter(item.getImages());
            }
        });

        mRepository.fetchRecentProducts(1, new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                initRecyclerAdapter(mRecyclerViewRecentProduct, mRecentProductAdapter, items);
            }
        });

        mRepository.fetchMostVisitedProducts(1, new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                initRecyclerAdapter(mRecyclerViewMostVisitedProduct, mMostVisitedProductAdapter, items);
            }
        });

        mRepository.fetchRatedProducts(1, new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                initRecyclerAdapter(mRecyclerViewRatedProduct, mRatedProductAdapter, items);
            }
        });
    }

    private void findViews(View view) {
        mSliderView = view.findViewById(R.id.imageSlider);
        mRecyclerViewRecentProduct = view.findViewById(R.id.recent_recycler_product);
        mRecyclerViewMostVisitedProduct = view.findViewById(R.id.most_visited_recycler_product);
        mRecyclerViewRatedProduct = view.findViewById(R.id.top_rated_recycler_product);
    }

    private void initRecyclerAdapter(RecyclerView recyclerView,
                                     ProductAdapter productAdapter,
                                     List<ProductsItem> productsItems) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));

        updateRecyclerAdapter(recyclerView, productAdapter, productsItems);
    }

    private void updateRecyclerAdapter(RecyclerView recyclerView,
                                       ProductAdapter productAdapter,
                                       List<ProductsItem> productsItems) {

        if (productAdapter == null) {
            productAdapter = new ProductAdapter(getContext(), productsItems);
            recyclerView.setAdapter(productAdapter);
        } else {
            productAdapter.setProductsItem(productsItems);
            productAdapter.notifyDataSetChanged();
        }
    }


    private void setupSliderAdapter(List<ImagesItem> imagesItems) {

        mSliderAdapter = new SliderAdapter(getContext(), imagesItems);
        mSliderView.setSliderAdapter(mSliderAdapter);
        mSliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        mSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        mSliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        mSliderView.setIndicatorSelectedColor(Color.WHITE);
        mSliderView.setIndicatorUnselectedColor(Color.GRAY);
        mSliderView.setScrollTimeInSec(3);
        mSliderView.startAutoCycle();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);


        MenuItem searchMenuItem = menu.findItem(R.id.search_product);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, SearchFragment.newInstance(query))
                        .commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

}