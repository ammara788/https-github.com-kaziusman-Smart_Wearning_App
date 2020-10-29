package com.example.start_app_slider.Show_Items;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.start_app_slider.Home.Show_Item_fragment;
import com.example.start_app_slider.R;

import java.util.List;

public class Adapter_Grid extends RecyclerView.Adapter<Adapter_Grid.ViewHolder> {

    List<String> titles;
    List<String> images;
    List<String> id;
    List<String> item_price;
    LayoutInflater inflater;

    public Adapter_Grid(Context ctx, List<String> titles, List<String> images,List<String> id,List<String> item_price){
        this.titles = titles;
        this.images = images;
        this.item_price = item_price;
        this.id = id;
        this.inflater = LayoutInflater.from(ctx);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        Glide.with(holder.gridIcon)
                .load(images.get(position))
                .fitCenter()
                .into(holder.gridIcon);
        holder.id.setText(id.get(position));
        holder.item_price.setText(item_price.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,item_price;
        ImageView gridIcon;
        TextView id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            gridIcon = itemView.findViewById(R.id.item_pic);
            id= itemView.findViewById(R.id.item_id);
            item_price= itemView.findViewById(R.id.item_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new Show_Item_fragment();
                    Bundle args = new Bundle();
                    args.putString("id",id.getText().toString());
//                    args.putString("name",name.getText().toString());
                    myFragment.setArguments(args);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();
                }
            });
        }
    }
}
