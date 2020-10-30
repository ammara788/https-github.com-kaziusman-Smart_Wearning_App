package com.example.start_app_slider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;


public class User_SignUpActivity extends AppCompatActivity {
    EditText email,pass,name,contact;
    Spinner city_spinner;
    RadioGroup radioGroup;
    String gender;
    Button register;
    String selectedvalue;
    TextView login;
    boolean valid = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        email=findViewById(R.id.edt_email);
        pass=findViewById(R.id.edt_password);
        name=findViewById(R.id.edt_Name);
        contact=findViewById(R.id.edt_contactNo);
        city_spinner = findViewById(R.id.edt_city);
        radioGroup=findViewById(R.id.rgroup);
        register=findViewById(R.id.btn_signup);
        login=findViewById(R.id.signup_login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(User_SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selectedvalue=parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.r_male) {
                    gender ="male";
                } else if(checkedId == R.id.r_female) {
                    gender="female";
                }
            }

        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate();




            }
        });





    }
    public void validate()
    {

        String u_email = email.getText().toString();
        String u_pass = pass.getText().toString();
        String u_contact = contact.getText().toString();
        String u_spinner = selectedvalue;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (u_email.isEmpty() || u_email.length() < 3 || !u_email.matches(emailPattern)) {
            email.setError("Email Required");
            email.findFocus();
            valid = false;
        }
        
        else if (u_pass.isEmpty() || u_pass.length() < 3) {
            pass.setError("at least 3 characters");
            pass.findFocus();
            valid = false;
        }
        else if (u_contact.isEmpty() || u_contact.length() < 3) {
            contact.setError("at least 3 characters");
            contact.findFocus();
            valid = false;
        } else if (u_spinner.equals("City")){
            ((TextView)city_spinner.getSelectedView()).setError("Select a City");

        }
        else{
            callapi();
        }
    }



    public void callapi()
    {
        AndroidNetworking.post("https://www.jashabhsoft.com/myapi/signup.php")
                .addBodyParameter("email", email.getText().toString())
                .addBodyParameter("city", selectedvalue)
                .addBodyParameter("password", pass.getText().toString())
                .addBodyParameter("gender",gender)
                .addBodyParameter("name", name.getText().toString())
                .addBodyParameter("contact", contact.getText().toString())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("error").equals("false"))
                            {
                                Toast.makeText(User_SignUpActivity.this, "Account Created", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(User_SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            else if(response.getString("error").equals("true"))
                            {
                                Toast.makeText(User_SignUpActivity.this, "Email Already Registered", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

}