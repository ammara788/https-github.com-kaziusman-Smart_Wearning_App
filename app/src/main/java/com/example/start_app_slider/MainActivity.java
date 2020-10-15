package com.example.start_app_slider;

import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.start_app_slider.Home.Home_Fragment;
import com.example.start_app_slider.Shops.Shop_Fragment;
import com.example.start_app_slider.Show_Items.Items_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment docmain = new Home_Fragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
        transaction.replace(R.id.container, docmain, "homemainfragment");
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();


        BottomNavigationView bottomNavigationView= (BottomNavigationView) findViewById(R.id.bottom_nav_main);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home:
                        Fragment docmain = new Home_Fragment();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
                        transaction.replace(R.id.container, docmain, "homemainfragment");
                        transaction.addToBackStack(null);

// Commit the transaction
                        transaction.commit();
//                        ft.disallowAddToBackStack();

                        break;
                    case R.id.shop:
                        Fragment docmain1 = new Shop_Fragment();
                        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
                        transaction1.replace(R.id.container, docmain1, "shopmainfragment");
                        transaction1.disallowAddToBackStack();

// Commit the transaction
                        transaction1.commit();
                        break;
                    case R.id.profile:
                        Fragment docmain2 = new Account_Fragment();
                        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
                        transaction2.replace(R.id.container, docmain2, "account");
                        transaction2.disallowAddToBackStack();

// Commit the transaction
                        transaction2.commit();

                        break;

                }
                return true;
            }
        });

    }
}