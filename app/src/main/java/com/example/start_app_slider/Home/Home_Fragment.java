package com.example.start_app_slider.Home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.start_app_slider.Home.Bestsell_items.bestsell_item_adapter;
import com.example.start_app_slider.Home.Category_items.Model_Class;
import com.example.start_app_slider.Home.Category_items.UsersRecyclerAdapter;
import com.example.start_app_slider.Home.Featured_items.featured_Item_Adapter;
import com.example.start_app_slider.Home.Featured_items.featured_Item_Model_Class;
import com.example.start_app_slider.Home.Sumer_items.summer_item_adapter;
import com.example.start_app_slider.Home.Winter_items.winter_item_adapter;
import com.example.start_app_slider.R;
import com.example.start_app_slider.Show_Items.Items_Fragment;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends Fragment {
   SearchView searchview;
    ArrayList<featured_Item_Model_Class> itemarrayList;
    ArrayList<Model_Class> arrayList;
    //winteritem
    RecyclerView winter_item_recyclerView;
    List<String> winter_item_image=new ArrayList<>();
//    int winter_item_image[]={R.drawable.random2,R.drawable.random1,R.drawable.random4, R.drawable.random3};
    List<String> winter_item_price=new ArrayList<String>();
    List<String> winter_item_name=new ArrayList<String>();
    List<String> winter_item_id=new ArrayList<String>();


    //
    //summeritem
    RecyclerView summer_item_recyclerView;
    List<String> summer_item_image=new ArrayList<>();
    List<String> summer_item_price=new ArrayList<String>();
    List<String> summer_item_name=new ArrayList<String>();
    List<String> summer_item_id=new ArrayList<String>();

    //
    //bestitem
    RecyclerView best_item_recyclerView;
    List<String> best_item_image=new ArrayList<>();
//    int best_item_image[]={R.drawable.random1,R.drawable.random2, R.drawable.random3,R.drawable.random4};
    List<String> best_item_price=new ArrayList<String>();
    List<String> best_item_name=new ArrayList<String>();
    List<String> best_item_id=new ArrayList<String>();

    //
    //top_treds_items
    RecyclerView item_recyclerView;
    List<String> item_image=new ArrayList<>();
//    int item_image[]={R.drawable.random1,R.drawable.random2, R.drawable.random3,R.drawable.random4};
    List<String> item_price=new ArrayList<String>();
    List<String> item_name=new ArrayList<String>();
    List<String> item_id=new ArrayList<String>();

    //
    //category_items
    RecyclerView recyclerView;

    int icons[] = {R.drawable.men,R.drawable.women, R.drawable.kids};
    String iconsName[] = {"Male", "Women", "Kids"};
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
        item_recyclerView = (RecyclerView) view.findViewById(R.id.top_trends);
        winter_item_recyclerView = (RecyclerView) view.findViewById(R.id.winter_sell);
        summer_item_recyclerView = (RecyclerView) view.findViewById(R.id.summer_sell);
        best_item_recyclerView = (RecyclerView) view.findViewById(R.id.best_sell);
        searchview=view.findViewById(R.id.searchview);

        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                AppCompatActivity activity = (AppCompatActivity) getContext();
                Fragment myFragment = new Items_Fragment();
                Bundle args = new Bundle();
                args.putString("key","Found Items");
                args.putString("id",query);
                myFragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();

//                Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });






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
        showbycategory("Top Trends");
        showbycategory("Best Sell");
        showbytype("Winter");
        showbytype("Summer");


/////////////////////////////////////////////////
        //winter items
//        winteritem(view);
///////////////////////////////////////////////////////
        //summer Items
//       summeritems(view);
////////////////////////////////////////////
        // featured items recyclerview
//        featureditems(view);
///////////////////////////////////////////////////////
        //bestsetll item adapter
//        bestsellitems(view);



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
//////////////////////////////////////////////////
    public void winteritem()
    {

        itemarrayList = new ArrayList<>();

        winter_item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        winter_item_recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i <  winter_item_image.size(); i++) {
            featured_Item_Model_Class itemModel = new featured_Item_Model_Class();
            itemModel.setImageUrl( winter_item_image.get(i));
            itemModel.setPrice( winter_item_price.get(i));
            itemModel.setName( winter_item_name.get(i));
            itemModel.setId(winter_item_id.get(i));
            //add in array list
            itemarrayList.add(itemModel);
        }

        winter_item_adapter winteritemuperadapter = new winter_item_adapter(getContext(), itemarrayList);
        winter_item_recyclerView.setAdapter(winteritemuperadapter);
    }
    public void summeritems()
    {

        itemarrayList = new ArrayList<>();

        summer_item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        summer_item_recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < summer_item_image.size(); i++) {
            featured_Item_Model_Class itemModel = new featured_Item_Model_Class();
            itemModel.setImageUrl(summer_item_image.get(i));
            itemModel.setPrice(summer_item_price.get(i));
            itemModel.setName(summer_item_name.get(i));
            itemModel.setId(summer_item_id.get(i));
            //add in array list
            itemarrayList.add(itemModel);
        }

        summer_item_adapter summeritemuperadapter = new summer_item_adapter(getContext(), itemarrayList);
        summer_item_recyclerView.setAdapter(summeritemuperadapter);
    }
    public void bestsellitems()
    {

        itemarrayList = new ArrayList<>();

        best_item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        best_item_recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < best_item_image.size(); i++) {
            featured_Item_Model_Class itemModel = new featured_Item_Model_Class();
            itemModel.setImageUrl(best_item_image.get(i));
            itemModel.setPrice(best_item_price.get(i));
            itemModel.setName(best_item_name.get(i));
            itemModel.setId(best_item_id.get(i));

            //add in array list
            itemarrayList.add(itemModel);
        }

        bestsell_item_adapter bestitemuperadapter = new bestsell_item_adapter(getContext(), itemarrayList);
        best_item_recyclerView.setAdapter(bestitemuperadapter);

    }
    public void featureditems()
    {

        itemarrayList = new ArrayList<>();

        item_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        item_recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < item_image.size(); i++) {
            featured_Item_Model_Class itemModel = new featured_Item_Model_Class();
            itemModel.setImageUrl(item_image.get(i));
            itemModel.setPrice(item_price.get(i));
            itemModel.setName(item_name.get(i));
            itemModel.setId(item_id.get(i));

            //add in array list
            itemarrayList.add(itemModel);
        }

        featured_Item_Adapter itemuperadapter = new featured_Item_Adapter(getContext(), itemarrayList);
        item_recyclerView.setAdapter(itemuperadapter);
    }

    public void showbycategory(final String category)
    {
        AndroidNetworking.post("https://jashabhsoft.com/myapi/productsbycategory.php")
                .addBodyParameter("category", category)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            if(category.equals("Top Trends"))
                            {
                                item_image.clear();
                                for (int i = 0; i < response.getJSONArray("list").length(); i++)
                                {
                                    item_image.add("https://jashabhsoft.com/myapi/uploads/items/"+response.getJSONArray("list").getJSONObject(i).getString("image_location"));
                                    item_price.add(response.getJSONArray("list").getJSONObject(i).getString("price"));
                                    item_name.add(response.getJSONArray("list").getJSONObject(i).getString("name"));
                                    item_id.add( response.getJSONArray("list").getJSONObject(i).getString("id"));

                                }
                                featureditems();
                            }
                            else if(category.equals("Best Sell"))
                            {
                                best_item_image.clear();
                                for (int i = 0; i < response.getJSONArray("list").length(); i++)
                                {
                                    best_item_image.add("https://jashabhsoft.com/myapi/uploads/items/"+response.getJSONArray("list").getJSONObject(i).getString("image_location"));
                                    best_item_price.add(response.getJSONArray("list").getJSONObject(i).getString("price"));
                                    best_item_name.add(response.getJSONArray("list").getJSONObject(i).getString("name"));
                                    best_item_id.add( response.getJSONArray("list").getJSONObject(i).getString("id"));

                                }
                                bestsellitems();
                            }

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
    public void showbytype(final String season)
    {
        AndroidNetworking.post("https://jashabhsoft.com/myapi/seasonproducts.php")
                .addBodyParameter("season", season)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            if(season.equals("Winter"))
                            {
                                winter_item_image.clear();
                                for (int i = 0; i < response.getJSONArray("list").length(); i++)
                                {
                                    winter_item_image.add("https://jashabhsoft.com/myapi/uploads/items/"+response.getJSONArray("list").getJSONObject(i).getString("image_location"));
                                    winter_item_price.add(response.getJSONArray("list").getJSONObject(i).getString("price"));
                                    winter_item_name.add(response.getJSONArray("list").getJSONObject(i).getString("name"));
                                    winter_item_id.add( response.getJSONArray("list").getJSONObject(i).getString("id"));

                                }
                                winteritem();
                            }
                            else if(season.equals("Summer"))
                            {
                                summer_item_image.clear();
                                for (int i = 0; i < response.getJSONArray("list").length(); i++)
                                {
                                    summer_item_image.add("https://jashabhsoft.com/myapi/uploads/items/"+response.getJSONArray("list").getJSONObject(i).getString("image_location"));
                                    summer_item_price.add(response.getJSONArray("list").getJSONObject(i).getString("price"));
                                    summer_item_name.add(response.getJSONArray("list").getJSONObject(i).getString("name"));
                                    summer_item_id.add( response.getJSONArray("list").getJSONObject(i).getString("id"));

                                }
                                summeritems();
                            }

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
