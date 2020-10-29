package com.example.start_app_slider.Home.Category_items;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.start_app_slider.R;
import com.example.start_app_slider.Show_Items.Items_Fragment;

import java.util.ArrayList;


public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.viewHolder> {

    Context context;
    ArrayList<Model_Class> arrayList;

    public UsersRecyclerAdapter(Context context, ArrayList<Model_Class> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public  UsersRecyclerAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user_recycler, viewGroup, false);
        return new viewHolder(view);
    }
    @Override
    public  void onBindViewHolder(UsersRecyclerAdapter.viewHolder viewHolder,int position) {
        viewHolder.iconName.setText(arrayList.get(position).getName());
        viewHolder.icon.setImageResource(arrayList.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView iconName;

        public viewHolder(View itemView) {
            super(itemView);
            icon =  itemView.findViewById(R.id.icon);
            iconName =  itemView.findViewById(R.id.icon_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), , Toast.LENGTH_SHORT).show();
                   if(iconName.getText().toString().equals("Male"))
                    {
                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        Fragment myFragment = new Items_Fragment();
                        Bundle args = new Bundle();
                        args.putString("key","Men");
                        myFragment.setArguments(args);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();


                    }
                    if(iconName.getText().toString().equals("Women"))
                    {
                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        Fragment myFragment = new Items_Fragment();
                        Bundle args = new Bundle();
                        args.putString("key","Women");
                        myFragment.setArguments(args);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();


                    }
                    if(iconName.getText().toString().equals("Kids"))
                    {
                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        Fragment myFragment = new Items_Fragment();
                        Bundle args = new Bundle();
                        args.putString("key","Kids");
                        myFragment.setArguments(args);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).addToBackStack(null).commit();


                    }
                }
            });

        }
    }

}