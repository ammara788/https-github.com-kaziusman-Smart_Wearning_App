package com.example.start_app_slider.Shops;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Spinner;
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
import com.example.start_app_slider.Admin_Panel.Admin_Adapter;
import com.example.start_app_slider.Admin_Panel.admin_model;
import com.example.start_app_slider.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Shop_Fragment extends Fragment {

    SearchView searchview;
    String selectedvalue;
    Spinner shopsearch;
    ArrayList<Shop_model> itemarrayList;
    RecyclerView shop_item_recyclerView;
    List<String> shop_item_image=new ArrayList<String>();
    //    int shop_item_image[]={R.drawable.random2,R.drawable.random1,R.drawable.random4};

    List<String> shop_email=new ArrayList<String>();
    List<String> shop_address=new ArrayList<String>();
    List<String> shop_city=new ArrayList<String>();
    List<String> shop_name=new ArrayList<String>();
    List<String> shop_id=new ArrayList<String>();
    List<String> shop_contact=new ArrayList<String>();
//    int shop_item_image[]={R.drawable.random2,R.drawable.random1,R.drawable.random4};

    String strtext;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.shop_main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchview=view.findViewById(R.id.search_view);
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(selectedvalue.equals("City"))
                {
                    Toast.makeText(getContext(), "Select a City to search", Toast.LENGTH_SHORT).show();
                }
                else{
                    serachbynameaftercity(selectedvalue,query );

                }
//                Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        shopsearch=view.findViewById(R.id.shop_search_spinner);

        shopsearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                selectedvalue=parentView.getItemAtPosition(position).toString();
                if(selectedvalue.equals("City"))
                {
                    showallshops();
                }
                else
                {
                    shoplistbycity(selectedvalue);
                }

                Toast.makeText(getContext(), selectedvalue, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });




        shop_item_recyclerView = (RecyclerView) view.findViewById(R.id.lv_shops);






    }
    public void showshoplist()
    {

        itemarrayList = new ArrayList<>();

        shop_item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        shop_item_recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < shop_item_image.size(); i++) {
            Shop_model itemModel = new Shop_model();
            itemModel.setImageUrl(shop_item_image.get(i));
            itemModel.setEmail( shop_email.get(i));
            itemModel.setAddress( shop_address.get(i));
            itemModel.setName( shop_name.get(i));
            itemModel.setId( shop_id.get(i));
            itemModel.setContact( shop_contact.get(i));
            itemModel.setCity( shop_city.get(i));

            //add in array list
            itemarrayList.add(itemModel);
        }

        Searchshop_adapter winteritemuperadapter = new Searchshop_adapter(getContext(), itemarrayList);
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

                        shop_item_image.clear();
                        shop_id.clear();
                        shop_name.clear();
                        shop_name.clear();
                        shop_address.clear();
                        shop_contact.clear();
                        shop_city.clear();
                        try {
                            for (int i = 0; i < response.getJSONArray("shops").length(); i++)
                            {
                                if(response.getJSONArray("shops").getJSONObject(i).getString("status").equals("1"))
                                {
                                    shop_item_image.add("https://jashabhsoft.com/myapi/uploads/shops/"+response.getJSONArray("shops").getJSONObject(i).getString("image_location"));


                                shop_id.add(response.getJSONArray("shops").getJSONObject(i).getString("id"));

                                shop_name.add(response.getJSONArray("shops").getJSONObject(i).getString("shop_name"));
                                shop_address.add( response.getJSONArray("shops").getJSONObject(i).getString("address"));
                                shop_email.add( response.getJSONArray("shops").getJSONObject(i).getString("user_name"));
                                shop_contact.add( response.getJSONArray("shops").getJSONObject(i).getString("contact"));
                                shop_city.add( response.getJSONArray("shops").getJSONObject(i).getString("city"));
                                }
                                else
                                {

                                }
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
    public void shoplistbycity(String city)
    {
        AndroidNetworking.post("https://jashabhsoft.com/myapi/shopbycity.php")
                .addBodyParameter("city",city)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        shop_item_image.clear();
                        shop_id.clear();
                        shop_name.clear();
                        shop_name.clear();
                        shop_address.clear();
                        shop_contact.clear();
                        shop_city.clear();
                        try {

                            for (int i = 0; i < response.getJSONArray("list").length(); i++)
                            {
                                if(response.getJSONArray("list").getJSONObject(i).getString("status").equals("1"))
                                {
                                    shop_item_image.add("https://jashabhsoft.com/myapi/uploads/shops/"+response.getJSONArray("list").getJSONObject(i).getString("image_location"));


                                    shop_id.add(response.getJSONArray("list").getJSONObject(i).getString("id"));

                                    shop_name.add(response.getJSONArray("list").getJSONObject(i).getString("shop_name"));
                                    shop_address.add( response.getJSONArray("list").getJSONObject(i).getString("address"));
                                    shop_email.add( response.getJSONArray("list").getJSONObject(i).getString("user_name"));
                                    shop_contact.add( response.getJSONArray("list").getJSONObject(i).getString("contact"));
                                    shop_city.add( response.getJSONArray("list").getJSONObject(i).getString("city"));
                                }
                                else
                                {

                                }


                            }


                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                        showshoplist();
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
    public void serachbynameaftercity(String city,String name)
    {
        AndroidNetworking.post("https://jashabhsoft.com/myapi/shopbynameaftercity.php")
                .addBodyParameter("city",city)
                .addBodyParameter("name",name)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        shop_item_image.clear();
                        shop_id.clear();
                        shop_name.clear();
                        shop_name.clear();
                        shop_address.clear();
                        shop_contact.clear();
                        shop_city.clear();
                        try {

                            for (int i = 0; i < response.getJSONArray("list").length(); i++)
                            {
                                if(response.getJSONArray("list").getJSONObject(i).getString("status").equals("1"))
                                {
                                    shop_item_image.add("https://jashabhsoft.com/myapi/uploads/shops/"+response.getJSONArray("list").getJSONObject(i).getString("image_location"));


                                    shop_id.add(response.getJSONArray("list").getJSONObject(i).getString("id"));

                                    shop_name.add(response.getJSONArray("list").getJSONObject(i).getString("shop_name"));
                                    shop_address.add( response.getJSONArray("list").getJSONObject(i).getString("address"));
                                    shop_email.add( response.getJSONArray("list").getJSONObject(i).getString("user_name"));
                                    shop_contact.add( response.getJSONArray("list").getJSONObject(i).getString("contact"));
                                    shop_city.add( response.getJSONArray("list").getJSONObject(i).getString("city"));
                                }
                                else
                                {

                                }


                            }


                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                        showshoplist();
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
}
