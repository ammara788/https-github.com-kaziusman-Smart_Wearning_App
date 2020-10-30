package com.example.start_app_slider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.start_app_slider.Admin_Panel.Admin_Main;
import com.example.start_app_slider.Shop_Account.Shop_Main;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    TextView customer_login,shop_login;
    EditText email,pass;
    String select;
    int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);
        email = findViewById(R.id.edt_email);
        pass = findViewById(R.id.edt_password);
        customer_login = findViewById(R.id.customer_login);
        shop_login = findViewById(R.id.shop_login);
        if (ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(LoginActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

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
                    if(email.getText().toString().equals("admin") & pass.getText().toString().equals("admin"))
                    {
                        Intent i = new Intent(LoginActivity.this, Admin_Main.class);
                        startActivity(i);
                    }
                    else{
                       calluserapi();
                        Toast.makeText(LoginActivity.this, "callapi", Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(i);
                    }


                }
                else if(select.equals("shop"))
                {
                    if(email.getText().toString().equals("admin") & pass.getText().toString().equals("admin"))
                    {
                        Intent i = new Intent(LoginActivity.this, Admin_Main.class);
                        startActivity(i);
                    }
                    else{
                        callshopapi();
//                        Intent i = new Intent(LoginActivity.this, Shop_Main.class);
//                        startActivity(i);
                    }

                }

            }
        });
    }
    public void calluserapi()
    {
        AndroidNetworking.post("https://jashabhsoft.com/myapi/login.php")
                .addBodyParameter("email", email.getText().toString())
                .addBodyParameter("password", pass.getText().toString())
              
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(LoginActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                        try {

                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            i.putExtra("id",response.getJSONObject("user").getString("id"));

                            startActivity(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                        i.putExtra("key","this");
//                        startActivity(i);
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
    public void callshopapi()
    {
        AndroidNetworking.post("https://jashabhsoft.com/myapi/shoplogin.php")
                .addBodyParameter("email", email.getText().toString())
                .addBodyParameter("password", pass.getText().toString())

                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Toast.makeText(LoginActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                        try {
                            if(response.getJSONObject("user").getString("status").equals("0"))
                            {
                                Toast.makeText(LoginActivity.this, "Shop not approved", Toast.LENGTH_SHORT).show();
                            }
                            else if (response.getJSONObject("user").getString("status").equals("1")){
                                Intent i = new Intent(LoginActivity.this, Shop_Main.class);
                                i.putExtra("id",response.getJSONObject("user").getString("id"));
//                            i.putExtra("email",response.getJSONObject("user").getString("email"));
//                            i.putExtra("name",response.getJSONObject("user").getString("name"));
//                            i.putExtra("city",response.getJSONObject("user").getString("city"));
//                            i.putExtra("gender",response.getJSONObject("user").getString("gender"));
//                            i.putExtra("contact",response.getJSONObject("user").getString("contact"));
                                startActivity(i);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                        i.putExtra("key","this");
//                        startActivity(i);
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}