package com.example.start_app_slider.Home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.example.start_app_slider.R;
import com.example.start_app_slider.Shop_Account.Edit_item;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class Show_Item_fragment extends Fragment {
    TextView name, desc,price,size,season,type,brand,small,medium,large;
    ImageView item_image,back_btn;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        item_image=view.findViewById(R.id.item_image);
        name=view.findViewById(R.id.item_title);
        small=view.findViewById(R.id.small);
        medium=view.findViewById(R.id.medium);
        large=view.findViewById(R.id.large);
        season=view.findViewById(R.id.item_Season);
        type=view.findViewById(R.id.item_type);
        brand=view.findViewById(R.id.item_brand);
        desc=view.findViewById(R.id.item_desc);
        price=view.findViewById(R.id.item_price);

        Bundle b = getArguments();

        showitem(b.getString("id"));



    }
    public void showitem(String id)
    {
        AndroidNetworking.post("https://jashabhsoft.com/myapi/itemdetailsbyid.php")
                .addBodyParameter("id", id)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            Glide.with(getContext())
                                    .load("https://jashabhsoft.com/myapi/uploads/items/"+response.getJSONObject("item").getString("image_location"))
                                    .into(item_image);
                            name.setText(response.getJSONObject("item").getString("name"));
                            if(response.getJSONObject("item").getString("size").equals("Small"))
                            {
                                medium.setBackgroundColor(Color.TRANSPARENT);
                                large.setBackgroundColor(Color.TRANSPARENT);
                            }
                            else if (response.getJSONObject("item").getString("size").equals("Medium"))
                            {
                                small.setBackgroundColor(Color.TRANSPARENT);
                                large.setBackgroundColor(Color.TRANSPARENT);
                            }
                            else if (response.getJSONObject("item").getString("size").equals("Large"))
                            {
                                small.setBackgroundColor(Color.TRANSPARENT);
                                medium.setBackgroundColor(Color.TRANSPARENT);
                            }
                            season.setText(response.getJSONObject("item").getString("season"));
                            type.setText(response.getJSONObject("item").getString("type"));
                            brand.setText(response.getJSONObject("item").getString("brand"));
                            desc.setText(response.getJSONObject("item").getString("details"));
                            price.setText("Rs:"+response.getJSONObject("item").getString("price"));
                        }
                        catch (JSONException e) {
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
