package com.example.start_app_slider.Shop_Account.ShowProducts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.start_app_slider.R;

import java.util.ArrayList;

public class Shop_Product_adapter extends RecyclerView.Adapter<Shop_Product_adapter.viewHolder> {

    Context context;
    ArrayList<Shop_product_model_class> arrayList;

    public Shop_Product_adapter(Context context, ArrayList<Shop_product_model_class> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public Shop_Product_adapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_model, viewGroup, false);
        return new Shop_Product_adapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(Shop_Product_adapter.viewHolder viewHolder, int position) {
        viewHolder.price.setText(arrayList.get(position).getPrice());
        viewHolder.desc.setText(arrayList.get(position).getDescription());
        viewHolder.image.setImageResource(arrayList.get(position).getImage());
        viewHolder.name.setText(arrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView price;
        TextView desc;
        TextView name;
        CardView itemcard;

        public viewHolder(View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.item_name);
            image = itemView.findViewById(R.id.shop_img);
            price = itemView.findViewById(R.id.item_price);
            desc = itemView.findViewById(R.id.item_category);
            itemcard= itemView.findViewById(R.id.item_card);
            itemcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    //Yes button clicked
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Do You want to edit this product?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
//                    Toast.makeText(v.getContext(),price.getText().toString(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
