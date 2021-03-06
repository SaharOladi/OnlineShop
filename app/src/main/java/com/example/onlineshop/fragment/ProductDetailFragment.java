package com.example.onlineshop.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.onlineshop.R;
import com.example.onlineshop.adapter.SliderAdapter;
import com.example.onlineshop.model.ImagesItem;
import com.example.onlineshop.model.ProductsItem;
import com.example.onlineshop.repository.Repository;
import com.example.onlineshop.utils.ShoppingPreferences;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import java.util.List;


public class ProductDetailFragment extends Fragment implements OnBackPressed {


    public static final String ARGS_PRODUCT = "ARGS_PRODUCT_DETAIL";

    private Repository mRepository;
    private ProductsItem mProduct;

    private SliderAdapter mSliderAdapter;
    private SliderView mSliderView;

    private TextView mPrice, mRegularPrice, mFinalePrice, mDescription;
    private Button mAddToBag;
    private ImageView mSingleImageProduct;
    private BottomNavigationView mBottomNavigationView;


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

        findViews(view);
        initViews();
        setListener();

        return view;
    }

    private void findViews(View view) {
        mSliderView = view.findViewById(R.id.imageSlider);
        mPrice = view.findViewById(R.id.product_detail_price);
        mRegularPrice = view.findViewById(R.id.product_regular_price);
        mDescription = view.findViewById(R.id.product_description);
        mSingleImageProduct = view.findViewById(R.id.image_view_main_picture);
        mFinalePrice = view.findViewById(R.id.final_price);
        mAddToBag = view.findViewById(R.id.add_to_shop);

        mBottomNavigationView = getActivity().findViewById(R.id.navigation_button);
//        mBottomNavigationView.setVisibility(View.GONE);
    }

    private void initViews() {
        mPrice.setText(mProduct.getPrice());
        mRegularPrice.setText(mProduct.getRegularPrice() + "");

        if (!mProduct.getSalePrice().equals(null))
            mFinalePrice.setText(mProduct.getSalePrice() + "");
        else
            mFinalePrice.setText(mProduct.getRegularPrice() + "");

        mDescription.setText(getDescription());

        setupSliderAdapter(mProduct.getImages());

        Glide.with(mSingleImageProduct)
                .load(mProduct.getImages().get(0).getSrc())
                .fitCenter()
                .into(mSingleImageProduct);
    }

    private String getDescription() {
        String description = mProduct.getDescription();
        if (description.equals(null))
            return description;
        Document document = Jsoup.parse(description);
        document.outputSettings(new Document.OutputSettings().prettyPrint(false));
        document.select("br").append("\\n");
        document.select("p").prepend("\\n\\n");
        String s = document.html().replaceAll("\\\\n", "\n");
        return Jsoup.clean(s, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
    }

    private void setListener() {
        mAddToBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingPreferences.setShopPrefKey(getActivity(), mProduct.getId());
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, ShoppingFragment.newInstance(mProduct.getId()))
                        .commit();
            }
        });
    }

    private void setupSliderAdapter(List<ImagesItem> imagesItems) {
        mSliderAdapter = new SliderAdapter(getActivity(), imagesItems);
        mSliderView.setSliderAdapter(mSliderAdapter);

        //set indicator animation by using IndicatorAnimationType:
        //WORM or THIN_WORM or COLOR or DROP or FILL or NONE or
        // SCALE or SCALE_DOWN or SLIDE and SWAP!!
        mSliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        mSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        mSliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        mSliderView.setIndicatorSelectedColor(Color.WHITE);
        mSliderView.setIndicatorUnselectedColor(Color.GRAY);
        //set scroll delay in seconds :
        mSliderView.setScrollTimeInSec(4);
        mSliderView.startAutoCycle();
    }


    @Override
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}