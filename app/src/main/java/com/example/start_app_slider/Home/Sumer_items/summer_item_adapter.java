package com.example.start_app_slider.Home.Sumer_items;

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
import com.example.start_app_slider.Home.Bestsell_items.bestsell_item_adapter;
import com.example.start_app_slider.Home.Featured_items.featured_Item_Model_Class;
import com.example.start_app_slider.Home.Show_Item_fragment;
import com.example.start_app_slider.R;

import java.util.ArrayList;

public class summer_item_adapter extends RecyclerView.Adapter<summer_item_adapter.viewHolder> {

    Context context;
    ArrayList<featured_Item_Model_Class> arrayList;

    public summer_item_adapter(Context context, ArrayList<featured_Item_Model_Class> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public summer_item_adapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.sumer_item_model, viewGroup, false);
        return new summer_item_adapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(summer_item_adapter.viewHolder viewHolder, int position) {
        viewHolder.price.setText(arrayList.get(position).getPrice());
        viewHolder.name.setText(arrayList.get(position).getName());
        viewHolder.s_id.setText(arrayList.get(position).getId());
//        viewHolder.image.setImageResource(arrayList.get(position).getImage());
        Glide.with(viewHolder.image)
                .load(arrayList.get(position).getImageUrl())
                .fitCenter()
                .into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView price,s_id;
        TextView name;
        CardView itemcard;

        public viewHolder(View itemView) {
            super(itemView);
            s_id = itemView.findViewById(R.id.s_id);
            image = itemView.findViewById(R.id.summer_item_pic);
            price = itemView.findViewById(R.id.summer_item_price);
            name = itemView.findViewById(R.id.summer_item_title);
            itemcard= itemView.findViewById(R.id.summer_itemcard);
            itemcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new Show_Item_fragment();
                    Bundle args = new Bundle();
                    args.putString("id",s_id.getText().toString());
//                    args.putString("name",name.getText().toString());
                    myFragment.setArguments(args);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();

                }
            });

        }
    }
}
