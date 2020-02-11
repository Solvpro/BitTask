package com.example.bittask.ui.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bittask.R;
import com.example.bittask.ui.home.module.CategoryResponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    private CategoryResponse.Category mCategory;

    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.ivCategory) ImageView ivCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        mCategory = new CategoryResponse.Category();

        mCategory = (CategoryResponse.Category) getIntent().getParcelableExtra("category");

        Log.d("mCategoryName", mCategory.getCategoryName());
        Log.d("mCategoryImage", mCategory.getCategoryImage());

        toolbar.setTitle(mCategory.getCategoryName());

        setSupportActionBar(toolbar);

        Glide.with(ivCategory.getContext())
                .load(mCategory.getCategoryImage())
                .centerCrop().into(ivCategory);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);

        // Define Cart Menu Item
        MenuItem cartItem = menu.findItem(R.id.action_cart);
        cartItem.getActionView();
        cartItem.setVisible(true);

        // Define Search Menu Item
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.getActionView();
        searchItem.setVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_search) {
            Log.d("action_search", "true");
        } else if (id == R.id.action_cart) {
            Log.d("action_cart", "true");
        }

        return super.onOptionsItemSelected(item);
    }

}
