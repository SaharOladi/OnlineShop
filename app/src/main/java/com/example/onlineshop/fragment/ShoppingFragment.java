package com.example.onlineshop.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.adapter.CardAdapter;
import com.example.onlineshop.adapter.ProductAdapter;
import com.example.onlineshop.model.ProductsItem;
import com.example.onlineshop.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends Fragment {


    public static final String ARGS_ID = "PRODUCT_ID";

    private int mId = 0;
    private Repository mRepository;

    private RecyclerView mRecyclerView;
    private CardAdapter mCardAdapter;

    public ShoppingFragment() {
        // Required empty public constructor
    }

    public static ShoppingFragment newInstance(int id) {
        ShoppingFragment fragment = new ShoppingFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mId = (int) getArguments().getSerializable(ARGS_ID);
        mRepository = new Repository();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);

        findViews(view);
        setRecycler();

        return view;
    }

    private void setRecycler() {
        if (mId != 0) {
            List<ProductsItem> itemList = new ArrayList<>();
            mRepository.fetchSingleProduct(mId, new Repository.SingleCallbacks() {
                @Override
                public void onItemResponse(ProductsItem item) {
                    itemList.add(item);
                    initRecyclerAdapter(mRecyclerView, mCardAdapter, itemList);
                }
            });
        }
    }


    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.card_recycler_view);
    }

    private void initRecyclerAdapter(RecyclerView recyclerView,
                                     CardAdapter cardAdapter,
                                     List<ProductsItem> productItems) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));

        updateRecyclerAdapter(recyclerView, cardAdapter, productItems);
    }

    private void updateRecyclerAdapter(RecyclerView recyclerView,
                                       CardAdapter cardAdapter,
                                       List<ProductsItem> productItems) {

        if (cardAdapter == null) {
            cardAdapter = new CardAdapter(getActivity(), productItems);
            recyclerView.setAdapter(cardAdapter);
        } else {
            cardAdapter.setProductsItem(productItems);
            cardAdapter.notifyDataSetChanged();
        }
    }
}