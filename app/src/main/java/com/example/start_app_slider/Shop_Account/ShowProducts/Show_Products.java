package com.example.start_app_slider.Shop_Account.ShowProducts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.start_app_slider.R;
import com.example.start_app_slider.Shop_Account.Shop_Main;
import com.example.start_app_slider.Shop_Account.ShowProducts.Shop_Product_adapter;
import com.example.start_app_slider.Shop_Account.ShowProducts.Shop_product_model_class;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Show_Products extends Fragment {
    ArrayList<Shop_product_model_class> itemarrayList;
    RecyclerView shop_item_recyclerView;
    List<String> shop_item_image=new ArrayList<String>();
//    int shop_item_image[]={R.drawable.random2,R.drawable.random1,R.drawable.random4};
    List<String> shop_item_price=new ArrayList<String>();
    List<String> shop_item_category=new ArrayList<String>();
    List<String> shop_item_name=new ArrayList<String>();
    List<String> shop_item_id=new ArrayList<String>();
    List<String> shop_item_type=new ArrayList<String>();
    List<String> shop_item_season=new ArrayList<String>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_products_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shop_item_recyclerView = (RecyclerView) view.findViewById(R.id.shope_lv_shops);

        showproducts();

    }
    public void showshoplist()
    {

        itemarrayList = new ArrayList<>();

        shop_item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        shop_item_recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < shop_item_image.size(); i++) {
            Shop_product_model_class itemModel = new Shop_product_model_class();
            itemModel.setImageUrl(shop_item_image.get(i));
            itemModel.setPrice(shop_item_price.get(i));
            itemModel.setName( shop_item_name.get(i));
            itemModel.setId( shop_item_id.get(i));
            itemModel.setCategory( shop_item_category.get(i));
            itemModel.setType( shop_item_type.get(i));
            itemModel.setSeason( shop_item_season.get(i));
            //add in array list
            itemarrayList.add(itemModel);
        }

        Shop_Product_adapter winteritemuperadapter = new Shop_Product_adapter(getContext(), itemarrayList);
        shop_item_recyclerView.setAdapter(winteritemuperadapter);
    }
    public void showproducts()
    {
        AndroidNetworking.post("https://jashabhsoft.com/myapi/allitemsbyshopid.php")
                .addBodyParameter("id", Shop_Main.shopid)

                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            for (int i = 0; i < response.getJSONArray("allitems").length(); i++)
                            {
                                shop_item_image.add("https://jashabhsoft.com/myapi/uploads/items/"+response.getJSONArray("allitems").getJSONObject(i).getString("image_location"));
                                shop_item_price.add(response.getJSONArray("allitems").getJSONObject(i).getString("price"));

                                shop_item_name.add(response.getJSONArray("allitems").getJSONObject(i).getString("name"));
                                shop_item_id.add(response.getJSONArray("allitems").getJSONObject(i).getString("id"));
                                shop_item_category.add(response.getJSONArray("allitems").getJSONObject(i).getString("category"));
                                shop_item_type.add(response.getJSONArray("allitems").getJSONObject(i).getString("type"));
                                shop_item_season.add(response.getJSONArray("allitems").getJSONObject(i).getString("season"));

                            }

                            showshoplist();
                            shop_item_image.clear();
                            shop_item_category.clear();
                            shop_item_price.clear();
                            shop_item_id.clear();
                            shop_item_category.clear();
                            shop_item_type.clear();
                            shop_item_season.clear();
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