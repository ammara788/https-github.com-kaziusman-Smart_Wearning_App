package com.example.start_app_slider.Admin_Panel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.start_app_slider.LoginActivity;
import com.example.start_app_slider.R;
import com.example.start_app_slider.Shop_Account.Shop_Main;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class All_Shops_Fragment extends Fragment {

    SearchView p_search;
    ArrayList<admin_model> itemarrayList;
    RecyclerView shop_item_recyclerView;
    List<String> shop_item_image=new ArrayList<String>();
//    int shop_item_image[]={R.drawable.random2,R.drawable.random1,R.drawable.random4};
    List<String> shop_item_price=new ArrayList<String>();
    List<String> shop_item_desc=new ArrayList<String>();
    List<String> shop_address=new ArrayList<String>();
    List<String> shop_satus=new ArrayList<String>();
    List<String> shop_name=new ArrayList<String>();
    List<String> shop_id=new ArrayList<String>();
    List<String> shop_contact=new ArrayList<String>();
    List<String> shop_city=new ArrayList<String>();
    List<String> shop_email=new ArrayList<String>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.all_shops_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shop_item_image.clear();
        shop_item_recyclerView = (RecyclerView) view.findViewById(R.id.all_lv_shops);
        p_search=view.findViewById(R.id.p_search);

        p_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                serachbyname (query);
//                Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        showallshops();


//        shop_item_price.add("weiroew");
//        shop_item_price.add("weiroew");
//        shop_item_price.add("weiroew");
//        shop_satus.add("Pending");
//        shop_satus.add("Pending");
//        shop_satus.add("Approved");

    }
    public void serachbyname(String name)
            
    {
        Toast.makeText(getContext(), "here", Toast.LENGTH_SHORT).show();shop_item_image.clear();
        shop_id.clear();
        shop_satus.clear();
        shop_name.clear();
        shop_address.clear();
        shop_email.clear();
        shop_contact.clear();
        shop_city.clear();
        AndroidNetworking.post("https://jashabhsoft.com/myapi/shopbyname.php")
                .addBodyParameter("name",name)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            for (int i = 0; i < response.getJSONArray("list").length(); i++)
                            {
                                shop_item_image.add("https://jashabhsoft.com/myapi/uploads/shops/"+response.getJSONArray("list").getJSONObject(i).getString("image_location"));
                                if( response.getJSONArray("list").getJSONObject(i).getString("status").equals("0"))
                                {
                                    shop_satus.add("Pending");
                                }
                                else if( response.getJSONArray("list").getJSONObject(i).getString("status").equals("1"))
                                {
                                    shop_satus.add("Approved");
                                }
                                shop_id.add(response.getJSONArray("list").getJSONObject(i).getString("id"));

                                shop_name.add(response.getJSONArray("list").getJSONObject(i).getString("shop_name"));
                                shop_address.add( response.getJSONArray("list").getJSONObject(i).getString("address"));
                                shop_email.add( response.getJSONArray("list").getJSONObject(i).getString("user_name"));
                                shop_contact.add( response.getJSONArray("list").getJSONObject(i).getString("contact"));
                                shop_city.add( response.getJSONArray("list").getJSONObject(i).getString("city"));

                            }

                            showshoplist();
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
    public void showshoplist()
    {

        itemarrayList = new ArrayList<>();

        shop_item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        shop_item_recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < shop_item_image.size(); i++) {
            admin_model itemModel = new admin_model();
            itemModel.setImageUrl(shop_item_image.get(i));
            itemModel.setEmail( shop_email.get(i));
            itemModel.setAddress( shop_address.get(i));
            itemModel.setName( shop_name.get(i));
            itemModel.setSatus( shop_satus.get(i));
            itemModel.setId( shop_id.get(i));
            itemModel.setContact( shop_contact.get(i));
            itemModel.setContact( shop_city.get(i));
            //add in array list
            itemarrayList.add(itemModel);
        }

        Admin_Adapter winteritemuperadapter = new Admin_Adapter(getContext(), itemarrayList);
        shop_item_recyclerView.setAdapter(winteritemuperadapter);
    }

    public void showallshops()
    {
        AndroidNetworking.post("https://jashabhsoft.com/myapi/allshops.php")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            for (int i = 0; i < response.getJSONArray("shops").length(); i++)
                            {
                                shop_item_image.add("https://jashabhsoft.com/myapi/uploads/shops/"+response.getJSONArray("shops").getJSONObject(i).getString("image_location"));
                                if( response.getJSONArray("shops").getJSONObject(i).getString("status").equals("0"))
                                {
                                    shop_satus.add("Pending");
                                }
                                else if( response.getJSONArray("shops").getJSONObject(i).getString("status").equals("1"))
                                {
                                    shop_satus.add("Approved");
                                }
                                shop_id.add(response.getJSONArray("shops").getJSONObject(i).getString("id"));

                                shop_name.add(response.getJSONArray("shops").getJSONObject(i).getString("shop_name"));
                                shop_address.add( response.getJSONArray("shops").getJSONObject(i).getString("address"));
                                shop_email.add( response.getJSONArray("shops").getJSONObject(i).getString("user_name"));
                                shop_contact.add( response.getJSONArray("shops").getJSONObject(i).getString("contact"));
                                shop_city.add( response.getJSONArray("shops").getJSONObject(i).getString("city"));

                            }

                            showshoplist();
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


