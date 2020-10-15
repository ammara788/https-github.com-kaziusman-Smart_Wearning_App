package com.example.start_app_slider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.start_app_slider.Shop_Account.Shop_Main;

public class LoginActivity extends AppCompatActivity {
    TextView customer_login,shop_login;
    String select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);
        customer_login = findViewById(R.id.customer_login);
        shop_login = findViewById(R.id.shop_login);
        select="customer";
        customer_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customer_login.setBackground(getResources().getDrawable(R.drawable.tab_left_selected));
                shop_login.setBackground(getResources().getDrawable(R.drawable.tab_right));
                select="customer";
            }
        });
        shop_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shop_login.setBackground(getResources().getDrawable(R.drawable.tab_right_selected));
                customer_login.setBackground(getResources().getDrawable(R.drawable.tab_left));
                select="shop";
            }
        });

        findViewById(R.id.signup_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(select.equals("customer"))
        {
            Intent i = new Intent(LoginActivity.this,User_SignUpActivity.class);
            startActivity(i);

        }
                else if(select.equals("shop"))
                {
                    Intent i = new Intent(LoginActivity.this,Shop_SignUpActivity.class);
                    startActivity(i);

                }
            }
        });
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(select.equals("customer"))
                {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);

                }
                else if(select.equals("shop"))
                {
                    Intent i = new Intent(LoginActivity.this, Shop_Main.class);
                    startActivity(i);

                }
            }
        });
    }
}