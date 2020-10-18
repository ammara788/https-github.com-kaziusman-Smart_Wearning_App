package com.example.start_app_slider.Admin_Panel;

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

import java.util.ArrayList;
import java.util.List;

public class Pending_Shops_fragment extends Fragment {
    ArrayList<admin_model> itemarrayList;
    RecyclerView shop_item_recyclerView;
    int shop_item_image[]={R.drawable.random2,R.drawable.random1,R.drawable.random4};
    List<String> shop_item_price=new ArrayList<String>();
    List<String> shop_item_category=new ArrayList<String>();
    String shop_item_desc[]={"Male", "Women", "Kids","Kids"};
    List<String> shop_satus=new ArrayList<String>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pending_shops_fragment, container, false);

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
        shop_satus.add("Pending");
        shop_satus.add("Pending");
        shop_satus.add("Pending");

        showshoplist(view);
    }
    public void showshoplist(View view)
    {
        shop_item_recyclerView = (RecyclerView) view.findViewById(R.id.pending_lv_shops);
        itemarrayList = new ArrayList<>();

        shop_item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        shop_item_recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < shop_item_image.length; i++) {
            admin_model itemModel = new admin_model();
            itemModel.setImage( shop_item_image[i]);
            itemModel.setCategory( shop_item_price.get(i));
            itemModel.setAddress( shop_item_desc[i]);
            itemModel.setName( shop_item_desc[i]);
            itemModel.setSatus( shop_satus.get(i));
            //add in array list
            itemarrayList.add(itemModel);
        }

        Admin_Adapter winteritemuperadapter = new Admin_Adapter(getContext(), itemarrayList);
        shop_item_recyclerView.setAdapter(winteritemuperadapter);
    }
}

