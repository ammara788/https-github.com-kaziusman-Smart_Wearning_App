package com.example.start_app_slider.Home.Featured_items;

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
import com.example.start_app_slider.Home.Show_Item_fragment;
import com.example.start_app_slider.R;
import com.example.start_app_slider.Show_Items.Items_Fragment;

import java.util.ArrayList;

public class featured_Item_Adapter extends RecyclerView.Adapter<featured_Item_Adapter.viewHolder> {

    Context context;
    ArrayList<featured_Item_Model_Class> arrayList;

    public featured_Item_Adapter(Context context, ArrayList<featured_Item_Model_Class> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public featured_Item_Adapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.featured_model, viewGroup, false);
        return new featured_Item_Adapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(featured_Item_Adapter.viewHolder viewHolder, int position) {
        viewHolder.price.setText(arrayList.get(position).getPrice());
        viewHolder.name.setText(arrayList.get(position).getName());
        viewHolder.item_id.setText(arrayList.get(position).getId());
        Glide.with(viewHolder.image)
                .load(arrayList.get(position).getImageUrl())
                .fitCenter()
                .into(viewHolder.image);
//        viewHolder.image.setImageResource(arrayList.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView price,item_id;
        TextView name;
        CardView itemcard;

        public viewHolder(View itemView) {
            super(itemView);
            item_id= itemView.findViewById(R.id.item_id);
            image = itemView.findViewById(R.id.item_pic);
            price = itemView.findViewById(R.id.item_price);
            name = itemView.findViewById(R.id.item_title);
            itemcard= itemView.findViewById(R.id.itemcard);
            itemcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new Show_Item_fragment();
                    Bundle args = new Bundle();
                    args.putString("id",item_id.getText().toString());
//                    args.putString("name",name.getText().toString());
                    myFragment.setArguments(args);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();

                }
            });

        }
    }
}

