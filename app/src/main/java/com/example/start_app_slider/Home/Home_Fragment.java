package com.example.start_app_slider.Home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

import com.example.start_app_slider.Home.Bestsell_items.bestsell_item_adapter;
import com.example.start_app_slider.Home.Category_items.Model_Class;
import com.example.start_app_slider.Home.Category_items.UsersRecyclerAdapter;
import com.example.start_app_slider.Home.Featured_items.featured_Item_Adapter;
import com.example.start_app_slider.Home.Featured_items.featured_Item_Model_Class;
import com.example.start_app_slider.Home.Sumer_items.summer_item_adapter;
import com.example.start_app_slider.Home.Winter_items.winter_item_adapter;
import com.example.start_app_slider.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends Fragment {
    ArrayList<featured_Item_Model_Class> itemarrayList;
    ArrayList<Model_Class> arrayList;
    //winteritem
    RecyclerView winter_item_recyclerView;
    int winter_item_image[]={R.drawable.random2,R.drawable.random1,R.drawable.random4, R.drawable.random3};
    List<String> winter_item_price=new ArrayList<String>();
    String winter_item_desc[]={"Male", "Women", "Kids","Kids"};


    //
    //summeritem
    RecyclerView summer_item_recyclerView;
    int summer_item_image[]={R.drawable.random1,R.drawable.random2, R.drawable.random3,R.drawable.random4};
    List<String> summer_item_price=new ArrayList<String>();
    String summer_item_desc[]={"Male", "Women", "Kids","Kids"};

    //
    //bestitem
    RecyclerView best_item_recyclerView;
    int best_item_image[]={R.drawable.random1,R.drawable.random2, R.drawable.random3,R.drawable.random4};
    List<String> best_item_price=new ArrayList<String>();
    String best_item_desc[]={"Male", "Women", "Kids","Kids"};
    //
    //top_treds_items
    RecyclerView item_recyclerView;
    int item_image[]={R.drawable.random1,R.drawable.random2, R.drawable.random3,R.drawable.random4};
    List<String> item_price=new ArrayList<String>();
    String item_desc[]={"Male", "Women", "Kids","Kids"};
    //
    //category_items
    RecyclerView recyclerView;
    int icons[] = {R.drawable.men,R.drawable.women, R.drawable.kids,R.drawable.accessories};
    String iconsName[] = {"Male", "Women", "Kids","Accessories"};
    SliderView sliderView;
    private SliderAdapterExample adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        item_price.add("123");
        item_price.add("123");
        item_price.add("123");
        item_price.add("123");
        best_item_price.add("123");
        best_item_price.add("123");
        best_item_price.add("123");
        best_item_price.add("123");
        summer_item_price.add("123");
        summer_item_price.add("123");
        summer_item_price.add("123");
        summer_item_price.add("123");
        winter_item_price.add("123");
        winter_item_price.add("123");
        winter_item_price.add("123");
        winter_item_price.add("123");

        // male, women, kids recyclerview
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewUsers);
        arrayList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < icons.length; i++) {
            Model_Class itemModel = new Model_Class();
            itemModel.setImage(icons[i]);
            itemModel.setName(iconsName[i]);

            //add in array list
            arrayList.add(itemModel);
        }

        UsersRecyclerAdapter uperadapter = new UsersRecyclerAdapter(getContext(), arrayList);
        recyclerView.setAdapter(uperadapter);
/////////////////////////////////////////////////
        //winter items
        winteritem(view);
///////////////////////////////////////////////////////
        //summer Items
       summeritems(view);
////////////////////////////////////////////
        // featured items recyclerview
        featureditems(view);
/////////////////////////////////////////////////////
        //bestsetll item adapter
        bestsellitems(view);



        //Main slider
        sliderView = view.findViewById(R.id.imageSlider);


        adapter = new SliderAdapterExample(getContext());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        sliderimages();
        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });

    }
    public void sliderimages()
    {
        List<SliderItem> sliderItemList = new ArrayList<>();
       List<String> l=new ArrayList<>();
       l.add("https://jashabhsoft.com/images/img2.jpg");
       l.add("https://jashabhsoft.com/images/men.jpg");

        //dummy data
        for (int i = 0; i < 5; i++) {
            SliderItem sliderItem = new SliderItem();
//            sliderItem.setDescription("Slider Item " + i);

            if (i== 0) {
                sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
            }
            else if (i== 1) {
                sliderItem.setImageUrl("https://jashabhsoft.com/images/img2.jpg");
            }
            else if (i== 2) {
                sliderItem.setImageUrl("https://jashabhsoft.com/images/men.jpg");
            }
            else if (i== 3) {
                sliderItem.setImageUrl("https://jashabhsoft.com/images/slider.jpg");

            }else {
                sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
            }
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);
    }

//slider items/////////////////////////////////
//    public void removeLastItem(View view) {
//        if (adapter.getCount() - 1 >= 0)
//            adapter.deleteItem(adapter.getCount() - 1);
//    }
//
//    public void addNewItem(View view) {
//        SliderItem sliderItem = new SliderItem();
//        sliderItem.setDescription("Slider Item Added Manually");
//        sliderItem.setImageUrl("https://images.pexels.com/photos/929778/pexels-photo-929778.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260");
//        adapter.addItem(sliderItem);
//
//    }
////////////////////////////////////////////////////
    public void winteritem(View view)
    {
        winter_item_recyclerView = (RecyclerView) view.findViewById(R.id.winter_sell);
        itemarrayList = new ArrayList<>();

        winter_item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        winter_item_recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i <  winter_item_image.length; i++) {
            featured_Item_Model_Class itemModel = new featured_Item_Model_Class();
            itemModel.setImage( winter_item_image[i]);
            itemModel.setPrice( winter_item_price.get(i));
            itemModel.setDesc( winter_item_desc[i]);

            //add in array list
            itemarrayList.add(itemModel);
        }

        winter_item_adapter winteritemuperadapter = new winter_item_adapter(getContext(), itemarrayList);
        winter_item_recyclerView.setAdapter(winteritemuperadapter);
    }
    public void summeritems(View view)
    {
        summer_item_recyclerView = (RecyclerView) view.findViewById(R.id.summer_sell);
        itemarrayList = new ArrayList<>();

        summer_item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        summer_item_recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < summer_item_image.length; i++) {
            featured_Item_Model_Class itemModel = new featured_Item_Model_Class();
            itemModel.setImage(summer_item_image[i]);
            itemModel.setPrice(summer_item_price.get(i));
            itemModel.setDesc(summer_item_desc[i]);

            //add in array list
            itemarrayList.add(itemModel);
        }

        summer_item_adapter summeritemuperadapter = new summer_item_adapter(getContext(), itemarrayList);
        summer_item_recyclerView.setAdapter(summeritemuperadapter);
    }
    public void bestsellitems(View view)
    {
        best_item_recyclerView = (RecyclerView) view.findViewById(R.id.best_sell);
        itemarrayList = new ArrayList<>();

        best_item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        best_item_recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < item_image.length; i++) {
            featured_Item_Model_Class itemModel = new featured_Item_Model_Class();
            itemModel.setImage(best_item_image[i]);
            itemModel.setPrice(best_item_price.get(i));
            itemModel.setDesc(best_item_desc[i]);

            //add in array list
            itemarrayList.add(itemModel);
        }

        bestsell_item_adapter bestitemuperadapter = new bestsell_item_adapter(getContext(), itemarrayList);
        best_item_recyclerView.setAdapter(bestitemuperadapter);

    }
    public void featureditems(View view)
    {
        item_recyclerView = (RecyclerView) view.findViewById(R.id.top_trends);
        itemarrayList = new ArrayList<>();

        item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        item_recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < item_image.length; i++) {
            featured_Item_Model_Class itemModel = new featured_Item_Model_Class();
            itemModel.setImage(item_image[i]);
            itemModel.setPrice(item_price.get(i));
            itemModel.setDesc(item_desc[i]);

            //add in array list
            itemarrayList.add(itemModel);
        }

        featured_Item_Adapter itemuperadapter = new featured_Item_Adapter(getContext(), itemarrayList);
        item_recyclerView.setAdapter(itemuperadapter);
    }

}
