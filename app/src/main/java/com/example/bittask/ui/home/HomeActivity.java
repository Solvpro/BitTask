package com.example.bittask.ui.home;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.bittask.R;
import com.example.bittask.ui.home.frag.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;

    private FragmentManager supportFragmentManger;

    private HomeFragment fragment;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        setUp();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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

    protected void setUp() {

        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_humburger_black);
        mDrawerToggle.syncState();

        fragment = new HomeFragment();

        loadFragment(fragment);

    }

    public boolean loadFragment(Fragment fragment) {

        //switching fragment
        if (fragment != null) {
            // clearBackStack();
            supportFragmentManger = getSupportFragmentManager();

            supportFragmentManger
                    .beginTransaction() /* .setCustomAnimations(R.anim.fade_in,R.anim.fade_out) */
                    .replace(R.id.nav_host_fragment, fragment) //nav_host_fragment
                    .commit();

            return true;

        }

        return false;
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
