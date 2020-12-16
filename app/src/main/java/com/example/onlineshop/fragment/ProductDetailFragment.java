package com.example.onlineshop.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.model.ProductsItem;
import com.example.onlineshop.repository.Repository;


public class ProductDetailFragment extends Fragment {


    public static final String ARGS_PRODUCT = "ARGS_PRODUCT_DETAIL";

    private Repository mRepository;
    private ProductsItem mProduct;

    public ProductDetailFragment() {
        // Required empty public constructor
    }


    public static ProductDetailFragment newInstance(ProductsItem productsItem) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_PRODUCT, productsItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRepository = new Repository();
        mProduct = (ProductsItem) getArguments().get(ARGS_PRODUCT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);

        return view;
    }
}