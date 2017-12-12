package com.oportunis.gallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by User on 12/12/2017.
 */

public class FragmentGallery extends Fragment implements View.OnClickListener {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static Uri uri;
    private View view;
    private TextView bt_nextGallery;
    private Button bt_captureGaleri;
    private ImageView imgv_galeri;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_gallery, container, false);

        this.bt_captureGaleri = view.findViewById(R.id.bt_captureGaleri);
        this.imgv_galeri = view.findViewById(R.id.imgv_galeri);
        this.bt_nextGallery = view.findViewById(R.id.bt_nextGallery);

        this.bt_captureGaleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
        this.bt_nextGallery.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_nextGallery:
                byte[] dataImage = imageViewToByte(imgv_galeri);
                Intent intent = new Intent(getContext(), ActivityUpload.class);
                intent.putExtra("image", dataImage);
                startActivity(intent);
                break;
        }
    }

    private byte[] imageViewToByte(ImageView imgv_galeri) {
        Bitmap bitmap = ((BitmapDrawable) imgv_galeri.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null) {
            uri = data.getData();

            try {
                InputStream inputStream = getContext().getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgv_galeri.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
        this.bt_nextGallery.setVisibility(View.VISIBLE);
    }
}
