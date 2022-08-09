package com.example.uas_10119294_lingga;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.uas_10119294_lingga.contract.NoteContract;
import com.example.uas_10119294_lingga.helpers.DialogCloseListener;
import com.example.uas_10119294_lingga.views.fragments.DashboardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.uas_10119294_lingga.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements DialogCloseListener {
    /*
     * NIM : 10119294
     * NAMA : Lingga Juliansyah
     * Kelas : IF-7
     * */
    private ActivityMainBinding binding;
    private DashboardFragment df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        df = (DashboardFragment) getSupportFragmentManager().findFragmentById(R.id.todoFrag);
//        FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction().replace(R.id.todoFrag,DashboardFragment.class,null)
//                .setReorderingAllowed(true).addToBackStack(null).commit();
//        df = (DashboardFragment) fm.findFragmentById(R.id.todoFrag);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }


    @Override
    public void handleDialogClose(DialogInterface dialogInterface) {
//        df = new DashboardFragment();
//        FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction().replace(R.id.todoFrag,DashboardFragment.class,null)
//                .setReorderingAllowed(true).addToBackStack(null).commit();
//        df.handleDialogClose(dialogInterface);
//         df = (DashboardFragment) getSupportFragmentManager().findFragmentById(R.id.todoFrag);
        Log.i("handle","hndl");
//        System.out.println(df.toString());
//        df.handleDialogClose(dialogInterface);
    }
}