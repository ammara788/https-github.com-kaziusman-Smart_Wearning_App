package com.example.start_app_slider.Shop_Account;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.example.start_app_slider.Admin_Panel.Admin_Main;
import com.example.start_app_slider.R;
import com.example.start_app_slider.Shop_SignUpActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class Add_Product extends Fragment {


    EditText product_name ,product_brand, product_price, product_Description;
    Spinner category,type,size,season;
    String s_category,s_type,s_size,s_season;
    Button addproduct;
    String filePath;
    File filetoupload;
    ImageView Img;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_product_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Img = view.findViewById(R.id.Img);
        addproduct = view.findViewById(R.id.add_product);
        category = view.findViewById(R.id.category);
        type = view.findViewById(R.id.type);
        size = view.findViewById(R.id.size);
        season = view.findViewById(R.id.season);

        product_name = view.findViewById(R.id.product_name);
        product_brand = view.findViewById(R.id.product_brand);
        product_price = view.findViewById(R.id.product_price);
        product_Description = view.findViewById(R.id.product_Description);

        season.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                s_season=parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });



        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                s_category=parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                s_type=parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                s_size=parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageUpload(filetoupload);
            }
        });


        Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();

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
                    Img.setImageBitmap(imageBitmap);
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
                    filePath = getRealPathFromURI(selectedImage);
                    File f=new File(filePath);
                    Bitmap image=decodeFile(f);
                    Uri tempUri = getImageUri(getContext(), image);
                    String path=getRealPathFromURI(tempUri);
                    filetoupload=new File(path);
                    Toast.makeText(getContext(), "" + getRealPathFromURI(selectedImage), Toast.LENGTH_SHORT).show();
                    Img.setImageURI(selectedImage);
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

    public void imageUpload(File imageFile)
    {
        AndroidNetworking.upload("https://jashabhsoft.com/myapi/additem.php")
                .addMultipartFile("file", imageFile)
                .addMultipartParameter("name",product_name.getText().toString())
                .addMultipartParameter("category",s_category)
                .addMultipartParameter("type",s_type)
                .addMultipartParameter("season",s_season)
                .addMultipartParameter("size",s_size)
                .addMultipartParameter("brand",product_brand.getText().toString())
                .addMultipartParameter("price",product_price.getText().toString())
                .addMultipartParameter("details",product_Description.getText().toString())
                .addMultipartParameter("shop_id",Shop_Main.shopid)
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
