package com.example.start_app_slider;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.start_app_slider.Home.Show_Item_fragment;

public class Account_Fragment extends Fragment {
    Button logout;
    TextView profile,term;

    String id;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        id=getArguments().getString("id");

        return inflater.inflate(R.layout.account_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profile=view.findViewById(R.id.profile_info);
        term=view.findViewById(R.id.term_condition);
        logout=view.findViewById(R.id.accout_logout);
        if(id.equals("guest"))
        {
            profile.setText("Login/Signup");
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), First_Activity.class);
                startActivity(intent);
            }
        });
//        help=view.findViewById(R.id.help);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(profile.getText().toString().equals("Login/Signup"))
                {
                    Intent intent = new Intent(getActivity(), User_SignUpActivity.class);
                    startActivity(intent);
                }
                else {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment myFragment = new User_Profile_Fragment();
                    Bundle args = new Bundle();
                    args.putString("id", id);

                    myFragment.setArguments(args);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();
                }

            }
        });
        term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new Terms_conditions_fragment();
                Bundle args = new Bundle();
                args.putString("id", id);

                myFragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();

            }
        });
//        help.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}

