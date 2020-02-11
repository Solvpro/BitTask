package com.example.bittask.ui.onbording.adap;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.bittask.R;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    // Arrays
    public int[] slideImages = {
      R.drawable.ic_slider_backhround,
      R.drawable.ic_slider_backhround,
      R.drawable.ic_slider_backhround
    };

    public String[] slideHeading = {
            "mauris pharetra nisl eget",
            "aliquam erat volutpat",
            "aresh bimarst delivery"
    };

    public String[] slideDescription = {
            "lorem ipsum dolor sit amet ,consectetur adipiscing elit,cras nec nunc urna.nunc vel consequat augue .donec gravida luctus justo ,eu sagitius ipsum elmturn aliquet",
            "lorem ipsum dolor sit amet ,consectetur adipiscing elit,cras nec nunc urna.nunc vel consequat augue .donec gravida luctus justo ,eu sagitius ipsum elmturn aliquet",
            "lorem ipsum dolor sit amet ,consectetur adipiscing elit,cras nec nunc urna.nunc vel consequat augue .donec gravida luctus justo ,eu sagitius ipsum elmturn aliquet"
    };

    // List Of Colors
    // list of background colors
    public int[]  lst_backgroundcolor = {
            Color.rgb(55,55,55),
            Color.rgb(239,85,85),
            Color.rgb(110,49,89),
            Color.rgb(1,188,212)
    };

    public SliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return slideHeading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView ivSlideImage = (ImageView) view.findViewById(R.id.ivSlideImage);
        TextView tvHeader = (TextView) view.findViewById(R.id.tvHeader);
        TextView tvDescription = (TextView) view.findViewById(R.id.tvDescription);

        ivSlideImage.setImageResource(slideImages[position]);
        tvHeader.setText(slideHeading[position]);
        tvDescription.setText(slideDescription[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
