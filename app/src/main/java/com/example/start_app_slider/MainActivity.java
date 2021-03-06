package com.example.start_app_slider;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.start_app_slider.Home.Home_Fragment;
import com.example.start_app_slider.Shops.Shop_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Bundle b;

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
        b=getIntent().getExtras();
//        Toast.makeText(this, b.getString("key"), Toast.LENGTH_SHORT).show();

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
                        transaction1.addToBackStack(null);

// Commit the transaction
                        transaction1.commit();
                        break;
                    case R.id.profile:
                        Fragment docmain2 = new Account_Fragment();
                        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                        Bundle bundle2=new Bundle();
                        bundle2.putString("id",  b.getString("id"));

                        docmain2.setArguments(bundle2);
// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
                        transaction2.replace(R.id.container, docmain2, "account");
                        transaction2.addToBackStack(null);

// Commit the transaction
                        transaction2.commit();

                        break;

                }
                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {

        Toast.makeText(this, "User Account to Logout", Toast.LENGTH_SHORT).show();
    }
}