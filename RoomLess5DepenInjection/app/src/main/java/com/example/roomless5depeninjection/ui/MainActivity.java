package com.example.roomless5depeninjection.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.View;
import com.example.roomless5depeninjection.R;
import com.example.roomless5depeninjection.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class MainActivity extends AppCompatActivity {
    private static final String LOG = "ui.activity";

    private ActivityMainBinding binding;

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initNavView();
    }

    private void initNavView() {
        navView = binding.bottomNavView;
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_favs
        ).build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(navView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.navigation_home
                        || destination.getId() == R.id.navigation_favs) {
                    navView.setVisibility(View.VISIBLE);
                    getSupportActionBar().hide();
                } else {
                    navView.setVisibility(View.GONE);
                    getSupportActionBar().show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}