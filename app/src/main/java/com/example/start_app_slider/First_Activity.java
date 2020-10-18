package com.example.start_app_slider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class First_Activity extends AppCompatActivity {
    Button guest,user_shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_);
        guest=findViewById(R.id.btn_guest);
        user_shop=findViewById(R.id.btn_Shop_signup);
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(First_Activity.this, Guest_Details.class);

                startActivity(i);
            }
        });
        user_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(First_Activity.this, LoginActivity.class);

                startActivity(i);
            }
        });
    }
}