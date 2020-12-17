package com.example.onlineshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlineshop.R;
import com.example.onlineshop.fragment.CategoryListFragment;
import com.example.onlineshop.fragment.ProductDetailFragment;
import com.example.onlineshop.model.ImagesItem;
import com.example.onlineshop.model.ProductsItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.RecyclerHolder> {

    private Context mContext;
    private List<ProductsItem> mProductsItem;
    private List<ProductsItem> mSearchProductsItem;


    public List<ProductsItem> getProductsItem() {
        return mProductsItem;
    }

    public void setProductsItem(List<ProductsItem> productsItem) {
        mProductsItem = productsItem;
        if (productsItem != null)
            this.mSearchProductsItem = new ArrayList<>(productsItem);
        notifyDataSetChanged();
    }

    public ProductAdapter(Context context, List<ProductsItem> productsItem) {
        mContext = context;
        mProductsItem = productsItem;
        mSearchProductsItem = new ArrayList<>(productsItem);

    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.product_view_holder, parent, false);

        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        ProductsItem productItem = mProductsItem.get(position);
        holder.bindProduct(productItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, ProductDetailFragment.newInstance(productItem))
                        .commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductsItem.size();
    }

    public void filterList(List<ProductsItem> productsItem){
        mProductsItem = productsItem;
        notifyDataSetChanged();
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {

        private TextView mName, mPrice;
        private ImageView mImage;
        private View mItemView;


        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);

            findHolderViews(itemView);

        }


        private void findHolderViews(@NonNull View itemView) {
            mName = itemView.findViewById(R.id.product_name);
            mPrice = itemView.findViewById(R.id.product_price);
            mImage = itemView.findViewById(R.id.product_image);

            mItemView = itemView;

        }

        private void bindProduct(ProductsItem productItem) {
            mName.setText(productItem.getName() + "");
            mPrice.setText(productItem.getPrice() + "");
            List<ImagesItem> imagesItems = productItem.getImages();
            Glide.with(mItemView)
                    .load(imagesItems.get(0).getSrc())
                    .fitCenter()
                    .into(mImage);
        }

    }
}
