package com.example.start_app_slider.Admin_Panel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.start_app_slider.LoginActivity;
import com.example.start_app_slider.R;
import com.example.start_app_slider.Shop_Account.Add_Product;
import com.example.start_app_slider.Shop_Account.Shop_Account;
import com.example.start_app_slider.Shop_Account.Shop_Main;
import com.example.start_app_slider.Shop_Account.ShowProducts.Show_Products;
import com.google.android.material.tabs.TabLayout;

public class Admin_Main extends AppCompatActivity {
    TextView logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Admin_Main.SectionsPagerAdapter sectionsPagerAdapter = new Admin_Main.SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Admin_Main.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shop_panel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            Intent i=new Intent(AdminPanel.this,MainActivity.class);
//            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static Shop_Main.PlaceholderFragment newInstance(int sectionNumber) {
            Shop_Main.PlaceholderFragment fragment = new Shop_Main.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//
//            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1){
//                View rootView = inflater.inflate(R.layout.admin_med_fragment, container, false);
//                return rootView;
//            }
//            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 2){
//                View rootView = inflater.inflate(R.layout.admin_pat_fragment, container, false);
//                return rootView;
//            }
//            else if(getArguments().getInt(ARG_SECTION_NUMBER) == 3){
//                View rootView = inflater.inflate(R.layout.admin_doc_fragment, container, false);
//                return rootView;
//            }
//            else {
//
//                View rootView = inflater.inflate(R.layout.activity_shop__main, container, false);
////                TextView textView = (TextView) rootView.findViewById(R.id.section_label);
////                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//                return rootView;
//            }
//        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    All_Shops_Fragment tab1 = new All_Shops_Fragment();
                    return tab1;
                case 1:
                    Pending_Shops_fragment tab2 = new Pending_Shops_fragment();
                    return tab2;
                case 2:
                    Approved_shops_fragment tab3 = new Approved_shops_fragment();
                    return tab3;


            }
            return Shop_Main.PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "All Shops";
                case 1:
                    return "Pending";
                case 2:
                    return "Approved";
            }
            return null;
        }
    }

}

