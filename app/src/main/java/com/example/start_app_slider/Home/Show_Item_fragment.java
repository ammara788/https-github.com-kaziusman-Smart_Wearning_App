package com.example.start_app_slider.Home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.start_app_slider.R;

import org.w3c.dom.Text;

public class Show_Item_fragment extends Fragment {
    TextView name, desc,price;
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
        name=view.findViewById(R.id.textView3);
//        desc=view.findViewById(R.id.description);
        price=view.findViewById(R.id.show_price);


        Bundle b = getArguments();
        name.setText(b.getString("name"));
        price.setText(b.getString("price"));


    }
}
