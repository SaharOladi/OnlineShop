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


public class CategoryListFragment extends Fragment implements OnBackPressed{


    public static final String ARGS_ID = "ARGS_ID";
    public static final int SPAN_COUNT = 2;

    private RecyclerView mRecyclerView;
    private ProductAdapter mAdapter;
    private Repository mRepository;

    private int mCategoryId = 0;

    public CategoryListFragment() {
        // Required empty public constructor
    }

    public static CategoryListFragment newInstance(int id) {
        CategoryListFragment fragment = new CategoryListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCategoryId = (int) getArguments().get(ARGS_ID);
        mRepository = new Repository();
        mRepository.fetchCategoryProduct(1, mCategoryId, new Repository.Callbacks() {
            @Override
            public void onItemResponse(List<ProductsItem> items) {
                initRecyclerAdapter(items);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
        findViews(view);

        return view;
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.category_product_recyclerview);
    }

    private void initRecyclerAdapter(List<ProductsItem> productsItems) {

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), SPAN_COUNT));

        updateRecyclerAdapter(productsItems);
    }

    public void updateRecyclerAdapter(List<ProductsItem> productsItems) {

        if (mAdapter == null) {
            mAdapter = new ProductAdapter(getContext(), productsItems);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setProductsItem(productsItems);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}