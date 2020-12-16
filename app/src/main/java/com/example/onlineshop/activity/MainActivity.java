package com.example.onlineshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.onlineshop.adapter.CategoryAdapter;
import com.example.onlineshop.fragment.CategoryFragment;
import com.example.onlineshop.fragment.CategoryListFragment;
import com.example.onlineshop.fragment.HomeFragment;
import com.example.onlineshop.R;
import com.example.onlineshop.fragment.ShoppingFragment;
import com.example.onlineshop.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private BottomNavigationView mBottomNavigationView;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().
                    add(R.id.fragment_container, HomeFragment.newInstance())
                    .commit();
        }

        moveNavigationBottom();
    }

    private void moveNavigationBottom() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_shoppingBag:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, ShoppingFragment.newInstance())
                                .hide(HomeFragment.newInstance())
                                .commit();
                        return true;
                    case R.id.navigation_profile:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, UserFragment.newInstance())
                                .hide(HomeFragment.newInstance())
                                .commit();
                        return true;
                    case R.id.navigation_category:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, CategoryFragment.newInstance())
                                .hide(HomeFragment.newInstance())

                                .commit();
                        return true;

                    case R.id.navigation_home:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, HomeFragment.newInstance())
                                .hide(HomeFragment.newInstance())

                                .commit();
                        return true;
                    default:
                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }
            }
        });
    }


    private void findViews() {
        mBottomNavigationView = findViewById(R.id.navigation_button);
    }



}