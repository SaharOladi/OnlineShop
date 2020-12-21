package com.example.onlineshop.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlineshop.R;
import com.example.onlineshop.model.ImagesItem;
import com.example.onlineshop.model.ProductsItem;
import com.example.onlineshop.utils.ShoppingPreferences;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.RecyclerHolder> {

    private Context mContext;
    private List<ProductsItem> mProductsItem;


    public List<ProductsItem> getProductsItem() {
        return mProductsItem;
    }

    public void setProductsItem(List<ProductsItem> productsItem) {
        mProductsItem = productsItem;
        notifyDataSetChanged();
    }

    public CardAdapter(Context context, List<ProductsItem> productsItem) {
        mContext = context;
        mProductsItem = productsItem;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.card_list_item, parent, false);

        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        ProductsItem productItem = mProductsItem.get(position);
        holder.bindProduct(productItem);
    }

    @Override
    public int getItemCount() {
        return mProductsItem.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {

        private TextView mName, mSalePrice;
        private com.google.android.material.button.MaterialButton mMaterialButtonPlus,
                mMaterialButtonMinus, mMaterialButtonDelete;
        private androidx.appcompat.widget.AppCompatTextView mCount, mFinalPrice;
        private ImageView mImageView;

        private int basePrice = 0;

        private View mItemView;

        private int mProductCount = 1;


        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            findHolderViews(itemView);
            setListener();

        }


        private void findHolderViews(@NonNull View itemView) {
            mName = itemView.findViewById(R.id.card_name);
            mMaterialButtonPlus = itemView.findViewById(R.id.card_plus_button);
            mMaterialButtonMinus = itemView.findViewById(R.id.card_minus_button);
            mMaterialButtonDelete = itemView.findViewById(R.id.card_delete);
            mCount = itemView.findViewById(R.id.card_count);
            mFinalPrice = itemView.findViewById(R.id.card_final_price);
            mImageView = itemView.findViewById(R.id.card_image_view);
            mSalePrice = itemView.findViewById(R.id.card_sale_price);

            mItemView = itemView;

        }

        private void bindProduct(ProductsItem productItem) {
            if (productItem != null) {
                mName.setText(productItem.getName() + "");
                mFinalPrice.setText(productItem.getRegularPrice() + "");
                mCount.setText(mProductCount + "");
                basePrice = Integer.parseInt(productItem.getRegularPrice());
                mSalePrice.setText(mProductCount * Integer.parseInt(productItem.getRegularPrice()) + "");

                List<ImagesItem> imagesItems = productItem.getImages();

                Glide.with(mItemView)
                        .load(imagesItems.get(0).getSrc())
                        .fitCenter()
                        .into(mImageView);
            }else {
                mItemView.setVisibility(View.INVISIBLE);
            }
        }

        private void setListener() {
            mMaterialButtonPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mProductCount <= 0)
                        mProductCount = 0;

                    mProductCount++;

                    if (mProductCount >= 0) {
                        mCount.setText(mProductCount + "");
                        mSalePrice.setText(mProductCount * basePrice + "");
                    }

                }
            });

            mMaterialButtonMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mProductCount--;
                    if (mProductCount >= 0) {
                        mCount.setText(mProductCount + "");
                        mSalePrice.setText(mProductCount * basePrice + "");
                    }
                }
            });

            mMaterialButtonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShoppingPreferences.removeShopPrefData(mContext);
                }
            });
        }

    }
}
