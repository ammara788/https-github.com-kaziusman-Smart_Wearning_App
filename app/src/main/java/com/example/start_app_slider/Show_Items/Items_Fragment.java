package com.example.start_app_slider.Show_Items;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.start_app_slider.R;
import com.example.start_app_slider.Shop_Account.Shop_Main;
import com.example.start_app_slider.Show_Items.Adapter_Grid;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Items_Fragment extends Fragment {
    RecyclerView dataList;
    List<String> titles = new ArrayList<>();

    List<String> id= new ArrayList<>();
    List<String> images= new ArrayList<>();
    List<String> item_price= new ArrayList<>();;
    Adapter_Grid grid_adapter;
    TextView my_tv;
    String shop_id;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        shop_id=getArguments().getString("id");
        return inflater.inflate(R.layout.items_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataList = view.findViewById(R.id.dataList);
        my_tv = view.findViewById(R.id.my_tv);
        Bundle b = getArguments();
        my_tv.setText(b.getString("key")+" Products");
        if(b.getString("key").equals("Men"))
        {
            my_tv.setText("Explore Men Products");
            showproduct("Men");
        }
        else if(b.getString("key").equals("Women"))
        {
            my_tv.setText("Explore Women Products");
            showproduct("Women");
        }
        else if(b.getString("key").equals("Kids"))
        {
            showproduct("Kids");
            my_tv.setText("Explore Kids Products");
        }
        else if(b.getString("key").equals("Found Items"))
        {
            my_tv.setText("Found Items");
            showitem(shop_id);
        }
        else
        {
            showdata();
        }


//        Toast.makeText(getContext(), b.getString("key"), Toast.LENGTH_SHORT).show();

          }


    public void gridadapter()
    {



        grid_adapter = new Adapter_Grid(getActivity(),titles,images,id,item_price);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(grid_adapter);

    }
    public void showdata()
    {
        AndroidNetworking.post("https://jashabhsoft.com/myapi/allitemsbyshopid.php")
                .addBodyParameter("id", shop_id)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        titles.clear();
                        images.clear();
                        id.clear();

                        try {
                            for (int i = 0; i < response.getJSONArray("allitems").length(); i++)
                            {
                                images.add("https://jashabhsoft.com/myapi/uploads/items/"+response.getJSONArray("allitems").getJSONObject(i).getString("image_location"));

                                item_price.add(response.getJSONArray("allitems").getJSONObject(i).getString("price"));

                                titles.add(response.getJSONArray("allitems").getJSONObject(i).getString("name"));
                                id.add(response.getJSONArray("allitems").getJSONObject(i).getString("id"));

                            }

                            gridadapter();
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
    public void showproduct(String type)
    {
        AndroidNetworking.post("https://jashabhsoft.com/myapi/itemsbytype.php")
                .addBodyParameter("type", type)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        titles.clear();
                        images.clear();
                        id.clear();

                        try {
                            for (int i = 0; i < response.getJSONArray("shops").length(); i++)
                            {
                                images.add("https://jashabhsoft.com/myapi/uploads/items/"+response.getJSONArray("shops").getJSONObject(i).getString("image_location"));

                                item_price.add(response.getJSONArray("shops").getJSONObject(i).getString("price"));

                                titles.add(response.getJSONArray("shops").getJSONObject(i).getString("name"));
                                id.add(response.getJSONArray("shops").getJSONObject(i).getString("id"));

                            }

                            gridadapter();
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
    public void showitem(String title)
    {
        AndroidNetworking.post("https://jashabhsoft.com/myapi/searchitem.php")
                .addBodyParameter("title", title)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        titles.clear();
                        images.clear();
                        id.clear();

                        try {
                            for (int i = 0; i < response.getJSONArray("searchresults").length(); i++)
                            {
                                images.add("https://jashabhsoft.com/myapi/uploads/items/"+response.getJSONArray("searchresults").getJSONObject(i).getString("image_location"));

                                item_price.add(response.getJSONArray("searchresults").getJSONObject(i).getString("price"));

                                titles.add(response.getJSONArray("searchresults").getJSONObject(i).getString("name"));
                                id.add(response.getJSONArray("searchresults").getJSONObject(i).getString("id"));

                            }

                            gridadapter();
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
