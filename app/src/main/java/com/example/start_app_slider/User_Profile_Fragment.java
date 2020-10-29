package com.example.start_app_slider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.start_app_slider.Home.Show_Item_fragment;

import org.json.JSONException;
import org.json.JSONObject;

public class User_Profile_Fragment extends Fragment {
    ImageView back;
    EditText profile_email,profile_password,pr_name,pr_city,pr_contact;
    RadioGroup prgroup;
    String id;
    RadioButton male,female;
    Button edit_profile;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        id=getArguments().getString("id");
        return inflater.inflate(R.layout.user_profile_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edit_profile=view.findViewById(R.id.edit_profile);
        profile_email=view.findViewById(R.id.profile_email);
        profile_password=view.findViewById(R.id.profile_password);
        pr_name=view.findViewById(R.id.pr_name);
        pr_city=view.findViewById(R.id.pr_city);
        pr_contact=view.findViewById(R.id.pr_contact);
        prgroup=view.findViewById(R.id.prgroup);
        male= (RadioButton) view.findViewById(R.id.r_male);
        female = (RadioButton) view.findViewById(R.id.r_female);




        filldata();
//        showdata();
        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateprofile();
            }
        });



        back=view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new Account_Fragment();
                Bundle bundle2=new Bundle();
                bundle2.putString("id", id);

                myFragment.setArguments(bundle2);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();

            }
        });
    }
    public void filldata()
    {
        AndroidNetworking.post("https://jashabhsoft.com/myapi/userdetailsbyid.php")
                .addBodyParameter("user_id", id)

                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();
                        try {
                            profile_email.setText(response.getJSONObject("userdetails").getString("email"));
                            profile_password.setText(response.getJSONObject("userdetails").getString("name"));
                            pr_name.setText(response.getJSONObject("userdetails").getString("name"));
                            pr_city.setText(response.getJSONObject("userdetails").getString("city"));
                            pr_contact.setText(response.getJSONObject("userdetails").getString("contact"));
                            if((response.getJSONObject("userdetails").getString("gender").equals("male")))
                            {
                                Toast.makeText(getContext(), "male", Toast.LENGTH_SHORT).show();
                                male.setChecked(true);
                            }
                            else if((response.getJSONObject("userdetails").getString("gender").equals("female")))
                            {
                                Toast.makeText(getContext(), "femail", Toast.LENGTH_SHORT).show();
                                female.setChecked(true);
                            }
//                            prgroup.setText();
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
    public void updateprofile()
    {

    }


    public void showdata()
    {
        AndroidNetworking.post("https://www.jashabhsoft.com/MyApi/userdetailsbyid.php")
//                .addBodyParameter("email", profile_email.getText().toString())
//                .addBodyParameter("city", pr_city.getText().toString())
//                .addBodyParameter("password", profile_password.getText().toString())
//                .addBodyParameter("gender", gender)
//                .addBodyParameter("name", pr_name.getText().toString())
//                .addBodyParameter("contact", contact.getText().toString())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getContext(), ""+response, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}
