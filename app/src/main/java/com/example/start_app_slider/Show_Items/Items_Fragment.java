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

import com.example.start_app_slider.R;
import com.example.start_app_slider.Show_Items.Adapter_Grid;

import java.util.ArrayList;
import java.util.List;

public class Items_Fragment extends Fragment {
    RecyclerView dataList;
    List<String> titles;
    List<Integer> images;
    Adapter_Grid grid_adapter;
    TextView my_tv;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.items_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataList = view.findViewById(R.id.dataList);
        my_tv = view.findViewById(R.id.my_tv);
        Bundle b = getArguments();
        my_tv.setText(b.getString("key")+" Products");
        if(b.getString("key").equals("male"))
        {
            my_tv.setText("Explore Men Products");
        }
        else if(b.getString("key").equals("female"))
        {
            my_tv.setText("Explore Women Products");
        }
        else if(b.getString("key").equals("kids"))
        {
            my_tv.setText("Explore Kids Products");
        }
//        Toast.makeText(getContext(), b.getString("key"), Toast.LENGTH_SHORT).show();
        gridadapter();    }


    public void gridadapter()
    {


        titles = new ArrayList<>();
        images = new ArrayList<>();

        titles.add("First Item");
        titles.add("Second Item");
        titles.add("Third Item");
        titles.add("Fourth Item");
        titles.add("First Item");
        titles.add("Second Item");
        titles.add("Third Item");
        titles.add("Fourth Item");

        images.add(R.drawable.ic_account);
        images.add(R.drawable.ic_arrow_forward_ios_24);
        images.add(R.drawable.ic_baseline_search_24);
        images.add(R.drawable.ic_shop);
        images.add(R.drawable.ic_home_24);
        images.add(R.drawable.ic_launcher_background);
        images.add(R.drawable.ic_launcher_foreground);
        images.add(R.drawable.kids);

        grid_adapter = new Adapter_Grid(getActivity(),titles,images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(grid_adapter);

    }
}
