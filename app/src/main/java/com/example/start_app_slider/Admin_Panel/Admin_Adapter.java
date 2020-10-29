package com.example.start_app_slider.Admin_Panel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.bumptech.glide.Glide;
import com.example.start_app_slider.First_Activity;
import com.example.start_app_slider.R;

import org.json.JSONException;
import org.json.JSONObject;

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
        viewHolder.email.setText(arrayList.get(position).getEmail());
        viewHolder.name.setText(arrayList.get(position).getName());
        viewHolder.status.setText(arrayList.get(position).getSatus());
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
            status = itemView.findViewById(R.id.status);
            address = itemView.findViewById(R.id.shop_address);
            email= itemView.findViewById(R.id.shop_email);
            itemcard= itemView.findViewById(R.id.item_card);
            shop_id= itemView.findViewById(R.id.shop_id);
            itemcard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    if (status.getText().toString().equals("Pending")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Actions on shop");
                        builder.setItems(new CharSequence[]
                                        {"Approve Shop", "Delete Shop", "Cancel"},
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // The 'which' argument contains the index position
                                        // of the selected item
                                        switch (which) {
                                            case 0:
                                                approveshop();
                                                break;
                                            case 1:
                                                deleteshop();
                                                break;
                                            case 2:

                                                break;

                                        }
                                    }
                                });
                        builder.create().show();
//                    Toast.makeText(v.getContext(),price.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Actions on shop");
                        builder.setItems(new CharSequence[]
                                        {"Disapprove Shop", "Delete Shop", "Cancel"},
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // The 'which' argument contains the index position
                                        // of the selected item
                                        switch (which) {
                                            case 0:
                                                disapproveshop();
                                                break;
                                            case 1:
                                                deleteshop();
                                                break;
                                            case 2:

                                                break;

                                        }
                                    }
                                });
                        builder.create().show();
                        Toast.makeText(v.getContext(), "Already Approved", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        public void approveshop()
        {
            AndroidNetworking.post("https://jashabhsoft.com/myapi/approveshop.php")
                    .addBodyParameter("id",shop_id.getText().toString())
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Toast.makeText(context, "Shop has been Approved", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, Admin_Main.class);
                            context.startActivity(intent);


                        }
                        @Override
                        public void onError(ANError error) {
                            // handle error
                        }
                    });

        }
        public void disapproveshop()
        {
            AndroidNetworking.post("https://jashabhsoft.com/myapi/disapproveshop.php")
                    .addBodyParameter("id",shop_id.getText().toString())
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Toast.makeText(context, "Shop has been Disapproved", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, Admin_Main.class);
                            context.startActivity(intent);


                        }
                        @Override
                        public void onError(ANError error) {
                            // handle error
                        }
                    });

        }
        public void deleteshop()
        {
            AndroidNetworking.post("https://jashabhsoft.com/myapi/deleteshopbyid.php")
                    .addBodyParameter("id",shop_id.getText().toString())
                    .setTag("test")
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            Toast.makeText(context, "Shop has been deleted", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(context, Admin_Main.class);
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
