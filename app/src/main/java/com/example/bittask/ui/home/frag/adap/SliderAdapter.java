package com.example.bittask.ui.home.frag.adap;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.bittask.R;
import com.example.bittask.ui.home.module.Slide;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater layoutInflater;
    private ArrayList<String> arrayListUrls;

    private SliderAdapter.Callback mCallback;
    private List<Slide> mSliderItemResponse;

    public SliderAdapter(List<Slide> sliderItemResponse, Context context) {
        mSliderItemResponse = sliderItemResponse;
        mContext = context;
    }

    public void setCallback(SliderAdapter.Callback callback) {
        mCallback = callback;
    }

    // List Of Colors
    // list of background colors
    public int[]  lst_backgroundcolor = {
            Color.rgb(55,55,55),
            Color.rgb(239,85,85),
            Color.rgb(110,49,89),
            Color.rgb(1,188,212)
    };

    public SliderAdapter(Context context, ArrayList<String> arrayListUrls) {
        this.mContext = context;
        this.arrayListUrls = arrayListUrls;
    }

    @Override
    public int getCount() {
        return mSliderItemResponse.size ();
    }

    public void addItems(List<Slide> sliderItems, Context context) {
        mSliderItemResponse.addAll(sliderItems);
        mContext = context;
        notifyDataSetChanged();
    }

    public interface Callback {
        void onBlogEmptyViewRetryClick();
        void onSearchResultViewClick(int unitId);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) mContext.getSystemService (mContext.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate (R.layout.slide, container, false);
        LinearLayout layoutSlide = view.findViewById (R.id.slidelinearlayout);
        ImageView imageView = (ImageView) view.findViewById (R.id.slideImg);
        final int sliderItemId = mSliderItemResponse.get(position).getImageId();
        final String sliderItemUrl = mSliderItemResponse.get(position).getImageTitle();

        if (sliderItemId != 0) {
                Glide.with(view.getContext())
                        .load(sliderItemUrl)
                        .centerCrop()
                        .into(imageView);

            // imageView.setImageResource(sliderItem.getSliderItemUrl());
        }
        // Glide.with(context).load(arrayListUrls.get (position)).into(imageView);
        container.addView (view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView ((LinearLayout) object);
    }
}
