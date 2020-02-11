package com.example.bittask.ui.home.frag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.bittask.R;
import com.example.bittask.service.ApiClient;
import com.example.bittask.service.ServiceApiInterface;
import com.example.bittask.ui.detail.DetailActivity;
import com.example.bittask.ui.home.frag.adap.CategoryAdapter;
import com.example.bittask.ui.home.frag.adap.SliderAdapter;
import com.example.bittask.ui.home.module.CategoryResponse;
import com.example.bittask.ui.home.module.Slide;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment
        implements CategoryAdapter.CategoryAdapterListener, View.OnClickListener {

    private static final String TAG = "HomeFragment";

    List<Slide> slideList;

    SliderAdapter pagerAdapter;

    Timer timer;

    private int currentPosition = 0;

    private int customPosition = 0;

    @BindView(R.id.viewPager) ViewPager viewPager;

    @BindView(R.id.tab_indicator) TabLayout tabIndicator;

    private ServiceApiInterface apiService;

    private Retrofit retrofit;

    private Context mContext;

    @BindView(R.id.rvMainCategory) RecyclerView rvMainCategory;

    private GridLayoutManager mGridLayoutManager;

    private CategoryAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, root);

        mContext = getContext();

        setUp(root);

        return root;
    }


    protected void setUp(View view) {

        retrofit = ApiClient.getClient();

        apiService = retrofit.create(ServiceApiInterface.class);

        doGetCategories();

    }

    // Get Categories
    private void doGetCategories(){

        Call<List<CategoryResponse.Category>> call = apiService.doServerGetCategories();

        call.enqueue(new retrofit2.Callback<List<CategoryResponse.Category>>() {
            @Override
            public void onResponse(Call<List<CategoryResponse.Category>> call, Response<List<CategoryResponse.Category>> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(mContext, getString(R.string.network_error) + "", Toast.LENGTH_SHORT).show();
                }

                if(response.body() != null) {

                    Log.d("repo", response.body() + "");

                    Log.d("categoriesSize", response.body().size() + "");
                    if (response.body().size() == 0){

                        Toast.makeText(mContext, response.body().size() + "", Toast.LENGTH_SHORT).show();

                    } else {

                        Log.d("categoriesSize", response.body().size() + "");

                        // Initialize Slider Urls and Main Category
                        initializeRecyclerView(response.body());
                    }

                } else {
                    Toast.makeText(mContext, getString(R.string.network_error) + "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CategoryResponse.Category>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                Log.e(TAG, "error");
                // Toast.makeText(getApplicationContext(), "error :(", Toast.LENGTH_LONG).show();
            }

        });
    }

    // Initialize Slider
    private void initializeSlider(final List<Slide> slider){

        pagerAdapter = new SliderAdapter(slider, mContext);

        viewPager.setAdapter(pagerAdapter);

        // setup tablayout with viewpager
        tabIndicator.setupWithViewPager(viewPager);

        createSlideShow(slider.size());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                tabIndicator.setScrollPosition(position, positionOffset, false);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    // Start Declaration Of Slider

    private void createSlideShow(final int sliderSize) {

        Log.d("sliderSizeSlide", sliderSize + "");

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if (currentPosition == sliderSize) // slideList.size()
                    currentPosition = 0;

                viewPager.setCurrentItem( currentPosition++ , true );

            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 400 , 4000);

    }

    // End Declaration Of Slider

    // Initialize Main Categories
    private void initializeRecyclerView(List<CategoryResponse.Category> categories) {

        slideList = new ArrayList<>();

        // Prepare Array Of Image
        int[] imageId = { R.drawable.ic_slider_backhround, R.drawable.ic_slider_backhround, R.drawable.ic_slider_backhround, R.drawable.ic_slider_backhround};

        List<String> imageUrls  = new ArrayList<>();

        List<String> imageTitles  = new ArrayList<>();

        for (int i = 0 ; i < categories.size(); ++i){

            imageUrls.add(i, categories.get(i).getCategoryImage());

            imageTitles.add(i, categories.get(i).getCategoryName());

        }

        for(int count = 0; count < imageId.length; ++count){

            slideList.add(new Slide(imageId[count], imageUrls.get(count), imageTitles.get(count)));

        }

        // Initialize Slide
        initializeSlider(slideList);

        // LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mGridLayoutManager = new GridLayoutManager(mContext, 2);
        mGridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rvMainCategory.setItemAnimator(new DefaultItemAnimator());

        rvMainCategory.setLayoutManager(mGridLayoutManager);

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        rvMainCategory.setHasFixedSize(true);

        /*
         * The GreenAdapter is responsible for displaying each item in the list.
         */
        Log.d("sizePinsItems", categories.size() + "");
        mAdapter = new CategoryAdapter(categories, mContext);


        rvMainCategory.setAdapter(mAdapter);

        mAdapter.setListener(this);

        // rvMainCategory.setRecyclerListener(mRecycleListener);

    }

    @Override
    public void onItemClick(CategoryResponse.Category category) {
        Intent intent = new Intent(mContext, DetailActivity.class);
        intent.putExtra("category", (Parcelable) category);
        startActivity(intent);
    }

    @Override
    public void onRetryCategoryClick() {

    }

    @Override
    public void onClick(View view) {

    }
}