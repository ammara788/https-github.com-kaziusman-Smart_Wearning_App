package com.example.start_app_slider.Shop_Account.ShowProducts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.example.start_app_slider.Admin_Panel.Admin_Main;
import com.example.start_app_slider.R;
import com.example.start_app_slider.Shop_Account.Edit_item;
import com.example.start_app_slider.Shop_Account.Shop_Main;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

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
        viewHolder.id.setText(arrayList.get(position).getId());
        viewHolder.category.setText(arrayList.get(position).getCategory());
        viewHolder.type.setText(arrayList.get(position).getType());
        viewHolder.season.setText(arrayList.get(position).getSeason());
        Glide.with(viewHolder.image)
                .load(arrayList.get(position).getImageUrl())
                .fitCenter()
                .into(viewHolder.image);
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
        TextView name, id, category, type, season;
        CardView itemcard;

        public viewHolder(View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.item_category);
            type = itemView.findViewById(R.id.item_type);
            season = itemView.findViewById(R.id.item_season);
            id = itemView.findViewById(R.id.item_id);
            name = itemView.findViewById(R.id.item_name);
            image = itemView.findViewById(R.id.shop_img);
            price = itemView.findViewById(R.id.item_price);
            desc = itemView.findViewById(R.id.item_category);
            itemcard = itemView.findViewById(R.id.item_card);
            itemcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Actions on item");
                    builder.setItems(new CharSequence[]
                                    {"Edit Item", "Delete Item", "Cancel"},
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // The 'which' argument contains the index position
                                    // of the selected item
                                    switch (which) {
                                        case 0:
                                            Intent intent = new Intent(v.getContext(), Edit_item.class);
                                            intent.putExtra("id",id.getText().toString());
                                            v.getContext().startActivity(intent);
                                            break;
                                        case 1:
                                            deleteitem();
                                            break;
                                        case 2:

                                            break;

                                    }
                                }
                            });
                    builder.create().show();
//                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            switch (which) {
//                                case DialogInterface.BUTTON_POSITIVE:
//                                    //Yes button clicked
//                                    break;
//
//                                case DialogInterface.BUTTON_NEGATIVE:
//                                    //No button clicked
//                                    break;
//                            }
//                        }
//                    };
//                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                    builder.setMessage("Do You want to edit this product?").setPositiveButton("Yes", dialogClickListener)
//                            .setNegativeButton("No", dialogClickListener).show();
//                    Toast.makeText(v.getContext(),price.getText().toString(), Toast.LENGTH_SHORT).show();

                }
            });

        }
        public void deleteitem() {

            AndroidNetworking.post(" https://jashabhsoft.com/myapi/deleteitembyid.php")
                    .addBodyParameter("id", id.getText().toString())
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Toast.makeText(context, "Item has been deleted", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(context, Shop_Main.class);
                            intent.putExtra("id",Shop_Main.shopid);
                            context.startActivity(intent);

                        }

                        @Override
                        public void onError(ANError error) {
                            // handle error
                        }
                    });
        }
    }

}
