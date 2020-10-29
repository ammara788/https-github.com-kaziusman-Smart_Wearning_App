package com.example.start_app_slider.Home.Bestsell_items;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.start_app_slider.Home.Featured_items.featured_Item_Adapter;
import com.example.start_app_slider.Home.Featured_items.featured_Item_Model_Class;
import com.example.start_app_slider.Home.Show_Item_fragment;
import com.example.start_app_slider.R;

import java.util.ArrayList;

public class bestsell_item_adapter extends RecyclerView.Adapter<bestsell_item_adapter.viewHolder> {

    Context context;
    ArrayList<featured_Item_Model_Class> arrayList;

    public bestsell_item_adapter(Context context, ArrayList<featured_Item_Model_Class> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public bestsell_item_adapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.bestsell_item_model, viewGroup, false);
        return new bestsell_item_adapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(viewHolder viewHolder, int position) {
        viewHolder.price.setText(arrayList.get(position).getPrice());
        viewHolder.desc.setText(arrayList.get(position).getName());
        viewHolder.b_item_id.setText(arrayList.get(position).getId());
        Glide.with(viewHolder.image)
                .load(arrayList.get(position).getImageUrl())
                .fitCenter()
                .into(viewHolder.image);



//        viewHolder.image.setImageURI(arrayList.get(position).getImageUrl());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView price,b_item_id;
        TextView desc;
        CardView itemcard;

        public viewHolder(View itemView) {
            super(itemView);
            b_item_id= itemView.findViewById(R.id.b_item_id);
            image = itemView.findViewById(R.id.b_item_pic);
            price = itemView.findViewById(R.id.b_item_price);
            desc = itemView.findViewById(R.id.b_item_title);
            itemcard= itemView.findViewById(R.id.itemcard);
            itemcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new Show_Item_fragment();
                    Bundle args = new Bundle();
                    args.putString("id",b_item_id.getText().toString());
//                    args.putString("name",name.getText().toString());
                    myFragment.setArguments(args);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();

                }
            });

        }
    }
}
