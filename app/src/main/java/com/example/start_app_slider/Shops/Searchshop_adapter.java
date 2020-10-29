package com.example.start_app_slider.Shops;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.start_app_slider.Admin_Panel.Admin_Adapter;
import com.example.start_app_slider.Admin_Panel.admin_model;
import com.example.start_app_slider.R;
import com.example.start_app_slider.Show_Items.Items_Fragment;

import java.util.ArrayList;

public class Searchshop_adapter extends RecyclerView.Adapter<Searchshop_adapter.viewHolder> {
    Context context;
    ArrayList<Shop_model> arrayList;

    public Searchshop_adapter(Context context, ArrayList<Shop_model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public Searchshop_adapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.shop_list_model, viewGroup, false);
        return new Searchshop_adapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(Searchshop_adapter.viewHolder viewHolder, int position) {
        viewHolder.address.setText(arrayList.get(position).getAddress());
        viewHolder.email.setText(arrayList.get(position).getEmail());
        viewHolder.name.setText(arrayList.get(position).getName());
        viewHolder.shop_id.setText(arrayList.get(position).getId());
        viewHolder.contact.setText(arrayList.get(position).getContact());
        viewHolder.shop_city.setText(arrayList.get(position).getCity());
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
        TextView name;
        TextView email;
        TextView address,status,shop_id,contact,shop_city;
        CardView itemcard;

        public viewHolder(View itemView) {
            super(itemView);
            shop_city= itemView.findViewById(R.id.shop_city);
            contact = itemView.findViewById(R.id.shop_contact);
            image = itemView.findViewById(R.id.shop_img);
            name = itemView.findViewById(R.id.shop_name);
            address = itemView.findViewById(R.id.shop_address);
            email= itemView.findViewById(R.id.shop_email);
            itemcard= itemView.findViewById(R.id.shop_card);
            shop_id= itemView.findViewById(R.id.shop_id);
            itemcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new Items_Fragment();
                    Bundle args = new Bundle();
                    args.putString("key",name.getText().toString());
                    args.putString("id",shop_id.getText().toString());
                    myFragment.setArguments(args);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();

                }
            });

        }
    }
}

