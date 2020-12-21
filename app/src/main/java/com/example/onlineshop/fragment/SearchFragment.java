package com.example.onlineshop.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.ProductAdapter;
import com.example.onlineshop.model.ProductsItem;
import com.example.onlineshop.repository.Repository;

import java.util.List;

public class SearchFragment extends Fragment {

    public static final String ARGS_QUERY = "ARGS_QUERY";
    public static final int SPAN_COUNT = 2;

    private RecyclerView mRecyclerViewSearch;
    private ProductAdapter mProductAdapter;
    private Repository mRepository;
    private String mQuery;


    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(String query) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_QUERY, query);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRepository = new Repository();
        mQuery = (String) getArguments().get(ARGS_QUERY);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        findViews(view);
        mRepository.fetchSearchProducts(mQuery, new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                initRecyclerAdapter(items);
            }
        });

        return view;
    }

    private void findViews(View view) {
        mRecyclerViewSearch = view.findViewById(R.id.search_recycler_product);
    }

    private void initRecyclerAdapter(List<ProductsItem> productsItems) {
        mRecyclerViewSearch.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));
        updateRecyclerAdapter(productsItems);
    }

    private void updateRecyclerAdapter(List<ProductsItem> productsItems) {
        if (mProductAdapter == null) {
            mProductAdapter = new ProductAdapter(getContext(), productsItems);
            mRecyclerViewSearch.setAdapter(mProductAdapter);
        } else {
            mProductAdapter.setProductsItem(productsItems);
            mProductAdapter.notifyDataSetChanged();
        }
    }

}