package com.example.start_app_slider.Admin_Panel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.start_app_slider.LoginActivity;
import com.example.start_app_slider.R;

public class Show_AdminShop extends AppCompatActivity {
    ImageView imageView3;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_admin_shop);
        imageView3=findViewById(R.id.imageView3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Show_AdminShop.this, Admin_Main.class);
              startActivity(i);
            }
        });
    }

}
