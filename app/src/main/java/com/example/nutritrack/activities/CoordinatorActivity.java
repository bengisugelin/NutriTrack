package com.example.nutritrack.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import com.example.nutritrack.R;
import com.example.nutritrack.databinding.ActivityCoordinatorBinding;
import com.example.nutritrack.fragments.DiscoverFragment;
import com.example.nutritrack.fragments.HomeFragment;
import com.example.nutritrack.fragments.ProfileFragment;
import com.example.nutritrack.fragments.logFragment;
import com.google.android.material.navigation.NavigationBarView;

public class CoordinatorActivity extends AppCompatActivity {
    ActivityCoordinatorBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        binding = ActivityCoordinatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setBackground(null);

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.log:
                    replaceFragment(new logFragment());
                    break;
                case R.id.discover:
                    replaceFragment(new DiscoverFragment());
                    break;
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}