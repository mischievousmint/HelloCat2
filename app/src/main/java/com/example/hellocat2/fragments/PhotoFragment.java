package com.example.hellocat2.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.hellocat2.CatModel;
import com.example.hellocat2.MySharedPreferences;
import com.example.hellocat2.R;
import com.example.hellocat2.Utils;


public class PhotoFragment extends Fragment {

    public PhotoFragment() {
        // Required empty public constructor
    }

    private ImageView ivPhoto;
    private Button btnPhoto;
    private Button btnSavePhoto;
    private EditText etPhoto;
    private CatModel mCat;

    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST = 1888;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewPhoto = inflater.inflate(R.layout.fragment_photo, container, false);
        ivPhoto = viewPhoto.findViewById(R.id.ivPhoto);
        etPhoto = viewPhoto.findViewById(R.id.etPhoto);
        btnPhoto = viewPhoto.findViewById(R.id.btnPhoto);
        btnSavePhoto = viewPhoto.findViewById(R.id.btnSavePhoto);
        mCat = new CatModel();


        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // Es una versión mayor que la 6.0
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                    } else {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                }
            }
        });

        btnSavePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCat.setTitle(etPhoto.getText().toString());
                MySharedPreferences.saveCat(mCat);
            }
        });

        CatModel recoveredModel = MySharedPreferences.loadCat();
        String recoveredTitle = recoveredModel.getTitle();
        String recoveredImage = recoveredModel.getImage();
        etPhoto.setText(recoveredTitle);

        Bitmap recoveredImage2 = Utils.StringToBitMap(recoveredImage);
        ivPhoto.setImageBitmap(recoveredImage2);

        return viewPhoto;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Permisos de la cámara concedidos", Toast.LENGTH_LONG).show();

                // LANZO LA CÁMARA DE FORMA NATIVA
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(getContext(), "Permisos de la cámara denegados", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");  // CREO UN BITMAP A PARTIR DE LOS DATOS RECOGIDOS POR LA CÁMARA

            String stringBitmap = Utils.BitMapToString(photo);
            mCat.setImage(stringBitmap);
            ivPhoto.setImageBitmap(photo);
        }

    }

/*    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    *//**
     * @param encodedString
     * @return bitmap (from given string)
     *//*
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }*/

}
