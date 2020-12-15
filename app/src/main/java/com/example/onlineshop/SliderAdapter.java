package com.example.onlineshop;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.example.onlineshop.model.ImagesItem;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context mContext;
    private List<ImagesItem> mImagesItems = new ArrayList<>();

    public List<ImagesItem> getImagesItems() {
        return mImagesItems;
    }

    public void setImagesItems(List<ImagesItem> imagesItems) {
        mImagesItems = imagesItems;
        notifyDataSetChanged();
    }

    public SliderAdapter(Context context, List<ImagesItem> imagesItems) {
        mContext = context;
        mImagesItems = imagesItems;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.slider_row_item, null);
        return new SliderAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        ImagesItem imageItem = mImagesItems.get(position);
        viewHolder.bindImageItem(viewHolder, imageItem);
    }

    @Override
    public int getCount() {
        return mImagesItems.size();
    }

    public class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        private ImageView imageViewBackground, imageGifContainer;
        private TextView textViewDescription;
        private ImagesItem mImageItem;
        private View mItemView;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            findHolderViews(itemView);
        }

        private void findHolderViews(View itemView) {
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            mItemView = itemView;
        }

        private void bindImageItem(SliderAdapterVH holder, ImagesItem image) {
            mImageItem = image;
            if (!mImageItem.getName().equals(null)) {
                holder.textViewDescription.setText(mImageItem.getName());
                holder.textViewDescription.setTextSize(16);
                holder.textViewDescription.setTextColor(Color.WHITE);
            }

            Glide.with(holder.itemView)
                    .load("https://woocommerce.maktabsharif.ir/wp-content/uploads/2020/01/301302.jpg")
                    .fitCenter()
                    .into(holder.imageViewBackground);

        }

    }

}
