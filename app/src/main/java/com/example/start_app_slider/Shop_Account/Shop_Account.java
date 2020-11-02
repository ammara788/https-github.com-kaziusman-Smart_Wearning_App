package com.example.start_app_slider.Shop_Account;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.bumptech.glide.Glide;
import com.example.start_app_slider.Admin_Panel.Admin_Main;
import com.example.start_app_slider.First_Activity;
import com.example.start_app_slider.LoginActivity;
import com.example.start_app_slider.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static android.app.Activity.RESULT_OK;

public class Shop_Account extends Fragment {
    Button logout,edit_profile;
    ImageView shop_img;
    TextView shop_email,shop_et_password,et_shop_name,et_address,et_City,et_Contact,shop_Description;
    File filetoupload;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shop_account_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shop_email=view.findViewById(R.id.shop_email);
        shop_et_password=view.findViewById(R.id.shop_et_password);
        et_shop_name=view.findViewById(R.id.et_shop_name);
        et_address=view.findViewById(R.id.et_address);
        et_City=view.findViewById(R.id.et_City);
        et_Contact=view.findViewById(R.id.et_Contact);
        shop_Description=view.findViewById(R.id.shop_Description);
        shop_img=view.findViewById(R.id.shop_edit_img);
        edit_profile=view.findViewById(R.id.edit_profile);



        showdetails();
        shop_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatedetails(filetoupload);
            }
        });




        logout=view.findViewById(R.id.logout);
        Toast.makeText(getContext(), Shop_Main.shopid, Toast.LENGTH_SHORT).show();
        logout.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          Intent intent = new Intent(getActivity(), First_Activity.class);
                                          startActivity(intent);
                                      }
                                  }
        );
    }
    public void showdetails()
    {
        AndroidNetworking.post("https://jashabhsoft.com/myapi/shopbyid.php")
                .addBodyParameter("id",Shop_Main.shopid)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            shop_email.setText(response.getJSONObject("shopdetails").getString("email"));
                            shop_et_password.setText(response.getJSONObject("shopdetails").getString("password"));
                            et_shop_name.setText(response.getJSONObject("shopdetails").getString("shop_name"));
                            et_address.setText(response.getJSONObject("shopdetails").getString("address"));
                            et_City.setText(response.getJSONObject("shopdetails").getString("city"));
                            et_Contact.setText(response.getJSONObject("shopdetails").getString("contact"));
                            shop_Description.setText(response.getJSONObject("shopdetails").getString("description"));
                            Glide.with(getContext())
                            .load("https://jashabhsoft.com/myapi/uploads/shops/"+response.getJSONObject("shopdetails").getString("image_location"))
                                    .into(shop_img);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Bundle extras = imageReturnedIntent.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    shop_img.setImageBitmap(imageBitmap);
                    Uri tempUri = getImageUri(getContext(), imageBitmap);
                    String path=getRealPathFromURI(tempUri);
                    filetoupload=new File(path);
//                    uploadBitmap(imageBitmap);
//                    uploadFile("" + getRealPathFromURI(tempUri));
                    Toast.makeText(getContext(), "" + getRealPathFromURI(tempUri), Toast.LENGTH_SHORT).show();
                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    String filePath = getRealPathFromURI(selectedImage);
                    File f=new File(filePath);
                    Bitmap image=decodeFile(f);
                    Uri tempUri = getImageUri(getContext(), image);
                    String path=getRealPathFromURI(tempUri);
                    filetoupload=new File(path);
                    Toast.makeText(getContext(), "" + getRealPathFromURI(selectedImage), Toast.LENGTH_SHORT).show();
                    shop_img.setImageURI(selectedImage);
                }
                break;
        }
    }
    private Bitmap decodeFile(File f) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // The new size we want to scale to
            final int REQUIRED_SIZE=100;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "pic", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
//                    File file = new File(path);
//                    Uri outputFileUri = Uri.fromFile(file);
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    public void updatedetails(File imageFile)
    {
        AndroidNetworking.upload("https://jashabhsoft.com/myapi/updateshop.php")
                .addMultipartFile("file", imageFile)
                .addMultipartParameter("id",Shop_Main.shopid)
                .addMultipartParameter("email",shop_email.getText().toString())
                .addMultipartParameter("shop_name",et_shop_name.getText().toString())
                .addMultipartParameter("password",shop_et_password.getText().toString())
                .addMultipartParameter("address",et_address.getText().toString())
                .addMultipartParameter("city",et_City.getText().toString())
                .addMultipartParameter("contact",et_Contact.getText().toString())
                .addMultipartParameter("description",shop_Description.getText().toString())
                //.setTag("uploadTest")
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        // do anything with progress
                        Toast.makeText(getContext(), (bytesUploaded / totalBytes)*100 + " %", Toast.LENGTH_SHORT).show();
//
//                        tvProgress.setText((bytesUploaded / totalBytes)*100 + " %");
                    }
                })
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), "updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), Shop_Main.class);
                        intent.putExtra("id",Shop_Main.shopid);

                        startActivity(intent);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(getContext(), anError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });




    }


}