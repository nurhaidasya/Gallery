package com.oportunis.gallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by User on 12/7/2017.
 */

public class FragmentCamera extends Fragment implements View.OnClickListener {
    private View view;
    private Button bt_captureCamera;
    private FrameLayout imgv_camera;
    private ImageView bt_next;
    private android.hardware.Camera camera;
    private ImageSurfaceView mImageSurfaceView;
    private byte[] dataImage;
    private Bitmap bitmap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = (View) inflater.inflate(R.layout.fragment_camera, container, false);

        this.bt_captureCamera = (Button) view.findViewById(R.id.bt_captureCamera);
        this.imgv_camera = (FrameLayout) view.findViewById(R.id.imgv_camera);
        this.bt_next = (ImageView) view.findViewById(R.id.bt_next);

        camera = checkDeviceCamera();
        mImageSurfaceView = new ImageSurfaceView(getActivity(), camera);
        this.imgv_camera.addView(mImageSurfaceView);

        this.bt_captureCamera.setOnClickListener(this);
        this.bt_next.setOnClickListener(this);
        return view;
    }

    private android.hardware.Camera checkDeviceCamera(){
        android.hardware.Camera mCamera = null;
        try {
            mCamera = android.hardware.Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mCamera;
    }


    android.hardware.Camera.PictureCallback pictureCallback = new android.hardware.Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, android.hardware.Camera camera) {
            dataImage = data;
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            if(bitmap==null){
                Toast.makeText(getActivity(), "Captured image is empty", Toast.LENGTH_LONG).show();
                return;
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_captureCamera:
                camera.takePicture(null, null, pictureCallback);
                bt_next.setVisibility(View.VISIBLE);
                break;
            case R.id.bt_next:
                Intent in1 = new Intent(getContext(), ActivityUpload.class);
                in1.putExtra("image",dataImage);
                startActivity(in1);
                break;
        }
    }
}

