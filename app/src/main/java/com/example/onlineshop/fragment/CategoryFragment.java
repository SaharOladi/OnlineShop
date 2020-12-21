package com.example.onlineshop.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.CategoryAdapter;
import com.example.onlineshop.adapter.ProductAdapter;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.ProductsItem;
import com.example.onlineshop.repository.Repository;

import java.util.List;

public class CategoryFragment extends Fragment implements CategoryAdapter.OnCategoryClickListener, OnBackPressed {

    public static final String TAG = "CategoryFragment";
    public static final int SPAN_COUNT = 3;

    private RecyclerView mRecyclerViewCategory;
    private CategoryAdapter mProductAdapterCategory;
    private ProgressBar mProgressBar;
    private TextView mTextProgressBar;

    private Repository mRepository;


    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = new Repository();

        mRepository.fetchCategory(1, new Repository.CategoryCallbacks() {
            @Override
            public void onItemResponse(List<CategoriesItem> items) {
                initCategoryRecyclerAdapter(items);
                mProgressBar.setVisibility(View.GONE);
                mTextProgressBar.setVisibility(View.GONE);

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        findViews(view);

        return view;
    }

    private void findViews(View view) {
        mRecyclerViewCategory = view.findViewById(R.id.category_recycler_view);
        mProgressBar = view.findViewById(R.id.progress_bar);
        mTextProgressBar = view.findViewById(R.id.textView_progressbar);
    }

    private void initCategoryRecyclerAdapter(List<CategoriesItem> categoriesItems) {

        mRecyclerViewCategory.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT));
        updateCategoryRecyclerAdapter(categoriesItems);
    }

    private void updateCategoryRecyclerAdapter(List<CategoriesItem> categoriesItems) {

        if (mProductAdapterCategory == null) {
            mProductAdapterCategory = new CategoryAdapter(getActivity(), categoriesItems);
            mRecyclerViewCategory.setAdapter(mProductAdapterCategory);
        } else {
            mProductAdapterCategory.setCategoriesItem(categoriesItems);
            mProductAdapterCategory.notifyDataSetChanged();
        }
    }

    @Override
    public void onCategoryClick(int id) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, CategoryListFragment.newInstance(id))
                .commit();
        Log.d(TAG, "onCategoryClick: "+id);
    }

    @Override
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}