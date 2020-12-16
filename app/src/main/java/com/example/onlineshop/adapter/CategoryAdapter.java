package com.example.onlineshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlineshop.R;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.ImagesItem;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder>{

    private Context mContext;
    private List<CategoriesItem> mCategoriesItems;


    public List<CategoriesItem> getCategoriesItem() {
        return mCategoriesItems;
    }

    public void setCategoriesItem(List<CategoriesItem> categoriesItems) {
        mCategoriesItems = categoriesItems;
        notifyDataSetChanged();
    }

    public CategoryAdapter(Context context, List<CategoriesItem> categoriesItems) {
        mContext = context;
        mCategoriesItems = categoriesItems;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.category_holder, parent, false);

        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        CategoriesItem categoriesItem = mCategoriesItems.get(position);
        holder.bindCategory(categoriesItem);
    }

    @Override
    public int getItemCount() {
        return mCategoriesItems.size();
    }


    public class CategoryHolder extends RecyclerView.ViewHolder {

        private TextView mCategoryName;
        private ImageView mCategoryImage;
        private View mItemView;


        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            findHolderViews(itemView);
            setListeners();

        }

        private void setListeners() {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }

        private void findHolderViews(@NonNull View itemView) {
            mCategoryName = itemView.findViewById(R.id.category_name);
            mCategoryImage = itemView.findViewById(R.id.category_image);

            mItemView = itemView;

        }

        private void bindCategory(CategoriesItem categoriesItem) {
            mCategoryName.setText(categoriesItem.getName()+"");
            ImagesItem imageItem = categoriesItem.getImages();
            Glide.with(mItemView)
                    .load(imageItem.getSrc())
                    .fitCenter()
                    .into(mCategoryImage);
        }

    }
}
