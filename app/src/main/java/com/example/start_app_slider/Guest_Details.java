package com.example.start_app_slider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Guest_Details extends AppCompatActivity {
    Button next;
    Spinner season, type;
    TextView age;
    public static String selected_season="";
    public static String selected_type="";
    boolean valid=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest__details);
        next=findViewById(R.id.btn_guest_next);
        season=findViewById(R.id.spinner_season);
        type=findViewById(R.id.spinner_type);
        age=findViewById(R.id.ed_age);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u_age=age.getText().toString();
                 if (u_age.isEmpty()) {
                    age.setError("Input age");
                    age.findFocus();
                    valid = false;
                }
                 else {
                     Intent i = new Intent(Guest_Details.this, MainActivity.class);
                     LoginActivity.selectedval="";
                     i.putExtra("id", "guest");

                     startActivity(i);
                 }
            }
        });

        season.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selected_season=parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selected_type=parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


    }
}