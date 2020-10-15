package com.example.start_app_slider.Shop_Account.ShowProducts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.start_app_slider.R;
import com.example.start_app_slider.Shop_Account.ShowProducts.Shop_Product_adapter;
import com.example.start_app_slider.Shop_Account.ShowProducts.Shop_product_model_class;

import java.util.ArrayList;
import java.util.List;

public class Show_Products extends Fragment {
    ArrayList<Shop_product_model_class> itemarrayList;
    RecyclerView shop_item_recyclerView;
    int shop_item_image[]={R.drawable.random2,R.drawable.random1,R.drawable.random4};
    List<String> shop_item_price=new ArrayList<String>();
    List<String> shop_item_category=new ArrayList<String>();
    String shop_item_name[]={"Male", "Women", "Kids","Kids"};
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
        shop_item_category.add("asdf");
        shop_item_category.add("asdf");
        shop_item_category.add("asdf");
        shop_item_price.add("weiroew");
        shop_item_price.add("weiroew");
        shop_item_price.add("weiroew");
        showshoplist(view);
    }
    public void showshoplist(View view)
    {
        shop_item_recyclerView = (RecyclerView) view.findViewById(R.id.shope_lv_shops);
        itemarrayList = new ArrayList<>();

        shop_item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        shop_item_recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < shop_item_image.length; i++) {
            Shop_product_model_class itemModel = new Shop_product_model_class();
            itemModel.setImage( shop_item_image[i]);
            itemModel.setPrice(shop_item_price.get(i));

            itemModel.setName( shop_item_name[i]);

            //add in array list
            itemarrayList.add(itemModel);
        }

        Shop_Product_adapter winteritemuperadapter = new Shop_Product_adapter(getContext(), itemarrayList);
        shop_item_recyclerView.setAdapter(winteritemuperadapter);
    }
}