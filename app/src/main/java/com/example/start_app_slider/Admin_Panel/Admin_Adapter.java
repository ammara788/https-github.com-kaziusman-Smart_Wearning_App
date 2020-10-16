package com.example.start_app_slider.Admin_Panel;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.start_app_slider.LoginActivity;
import com.example.start_app_slider.R;
import com.example.start_app_slider.Shop_Account.ShowProducts.Shop_Product_adapter;
import com.example.start_app_slider.Shop_Account.ShowProducts.Shop_product_model_class;
import com.example.start_app_slider.Shops.Shop_model;
import com.example.start_app_slider.Show_Items.Items_Fragment;

import java.util.ArrayList;

public class Admin_Adapter extends RecyclerView.Adapter<Admin_Adapter.viewHolder> {

    Context context;
    ArrayList<admin_model> arrayList;

    public Admin_Adapter(Context context, ArrayList<admin_model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public Admin_Adapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.admin_shops_model, viewGroup, false);
        return new Admin_Adapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(Admin_Adapter.viewHolder viewHolder, int position) {
        viewHolder.address.setText(arrayList.get(position).getAddress());
        viewHolder.category.setText(arrayList.get(position).getCategory());
        viewHolder.image.setImageResource(arrayList.get(position).getImage());
        viewHolder.name.setText(arrayList.get(position).getName());
        viewHolder.status.setText(arrayList.get(position).getSatus());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView category;
        TextView address,status;
        CardView itemcard;

        public viewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.shop_img);
            name = itemView.findViewById(R.id.shop_name);
            status = itemView.findViewById(R.id.status);
            address = itemView.findViewById(R.id.shop_address);
            category= itemView.findViewById(R.id.shop_category);
            itemcard= itemView.findViewById(R.id.item_card);
            itemcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    if (status.getText().toString().equals("Pending")) {
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                status.setText("Approved");

                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        //No button clicked
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Do You want to Approve this shop?").setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();
//                    Toast.makeText(v.getContext(),price.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(v.getContext(), "Already Approved", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

}
