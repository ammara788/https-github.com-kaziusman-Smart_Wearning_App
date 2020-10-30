package com.example.start_app_slider;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Shop_SignUpActivity extends AppCompatActivity {
 EditText shop_edt_email,edt_username,edt_shop_password,edt_location,edt_contactNo,edt_desc;
 Spinner edt_city;
 String slectedvalue;
 ImageView shop_img;
    String filePath="";
    File filetoupload;
    Button register;
    TextView login;
    boolean valid = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop__sign_up);
        shop_edt_email=findViewById(R.id.shop_edt_email);
        edt_username=findViewById(R.id.edt_username);
        edt_shop_password=findViewById(R.id.edt_shop_password);
        edt_location=findViewById(R.id.edt_location);
        edt_contactNo=findViewById(R.id.edt_contactNo);
        edt_desc=findViewById(R.id.edt_desc);
        edt_city=findViewById(R.id.edt_city);
        shop_img=findViewById(R.id.shop_img);
        register=findViewById(R.id.btn_register);
        login=findViewById(R.id.signup_login_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Shop_SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();

            }
        });
        edt_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                slectedvalue=parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        shop_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });



}
    public void validate()
    {
        String u_email = shop_edt_email.getText().toString();
        String u_nam = edt_username.getText().toString();
        String u_pass = edt_shop_password.getText().toString();
        String u_contact = edt_contactNo.getText().toString();
        String u_address = edt_location.getText().toString();
        String u_spinner = slectedvalue;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (u_email.isEmpty() || u_email.length() < 3 || !u_email.matches(emailPattern)) {
            shop_edt_email.setError("Email Required");
            shop_edt_email.findFocus();
            valid = false;
        }
        else if (u_pass.isEmpty() || u_pass.length() < 3) {
            edt_shop_password.setError("at least 3 characters");
            edt_shop_password.findFocus();
            valid = false;
        }
        else if (u_address.isEmpty() || u_address.length() < 3) {
            edt_location.setError("at least 3 characters");
            edt_location.findFocus();
            valid = false;
        }
        else if (u_nam.isEmpty() || u_nam.length() < 3) {
            edt_username.setError("at least 3 characters");
            edt_username.findFocus();
            valid = false;
        }
        else if (u_contact.isEmpty() || u_contact.length() < 3) {
            edt_contactNo.setError("at least 3 characters");
            edt_contactNo.findFocus();
            valid = false;
        } else if (u_spinner.equals("City")){
            ((TextView)edt_city.getSelectedView()).setError("Select a City");

        }
        else{
            imageUpload(filetoupload);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Bundle extras = imageReturnedIntent.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    shop_img.setImageBitmap(imageBitmap);
                    Uri tempUri = getImageUri(getApplicationContext(), imageBitmap);
                    String path=getRealPathFromURI(tempUri);
                    filetoupload=new File(path);

//                    uploadBitmap(imageBitmap);
//                    uploadFile("" + getRealPathFromURI(tempUri));
                    Toast.makeText(this, "" + getRealPathFromURI(tempUri), Toast.LENGTH_SHORT).show();
                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    filePath = getRealPathFromURI(selectedImage);
                    File f=new File(filePath);
                    Bitmap image=decodeFile(f);
                    Uri tempUri = getImageUri(getApplicationContext(), image);
                    String path=getRealPathFromURI(tempUri);
                    filetoupload=new File(path);


                    Toast.makeText(this, "" + getRealPathFromURI(selectedImage), Toast.LENGTH_SHORT).show();
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
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Shop_SignUpActivity.this);
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
        AndroidNetworking.upload("https://jashabhsoft.com/myapi/createshop.php")
                .addMultipartFile("file", imageFile)
            .addMultipartParameter("email",shop_edt_email.getText().toString())
                .addMultipartParameter("shop_name",edt_username.getText().toString())
                .addMultipartParameter("password",edt_shop_password.getText().toString())
                .addMultipartParameter("address",edt_location.getText().toString())
                .addMultipartParameter("city",slectedvalue)
                .addMultipartParameter("contact",edt_contactNo.getText().toString())
                .addMultipartParameter("description",edt_desc.getText().toString())
                //.setTag("uploadTest")
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        // do anything with progress
//                        tvProgress.setText((bytesUploaded / totalBytes)*100 + " %");
                    }
                })
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(Shop_SignUpActivity.this, "Account created", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Shop_SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(Shop_SignUpActivity.this, anError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });




    }

}