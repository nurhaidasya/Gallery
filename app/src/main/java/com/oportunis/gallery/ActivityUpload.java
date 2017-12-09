package com.oportunis.gallery;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ActivityUpload extends AppCompatActivity {

    private ImageView img_upload;
    private EditText et_title;
    private EditText et_deskrip;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase mDB;
    private byte[] byteArray;
    private Bitmap bitmap;
    private Button bt_upload;
    private LinearLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        this.img_upload = (ImageView) findViewById(R.id.img_upload);
        this.et_title = (EditText) findViewById(R.id.et_title);
        this.et_deskrip = (EditText) findViewById(R.id.et_deskrip);
        this.bt_upload = (Button) findViewById(R.id.bt_upload);
        this.rootView = (LinearLayout) findViewById(R.id.rootView);

        dbHelper = new DatabaseHelper(this);
        mDB = dbHelper.getWritableDatabase();
        
        Bundle extras = getIntent().getExtras();
        byteArray = getIntent().getByteArrayExtra("image");
        if(byteArray != null){
            bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        }
        this.img_upload.setImageBitmap(bitmap);

        this.bt_upload = (Button) findViewById(R.id.bt_upload);
        this.bt_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDatabase();
            }
        });
    }

    private void saveDatabase() {
        String title = et_title.getText().toString().trim();
        String deskrip = et_deskrip.getText().toString().trim();
        byte [] foto = byteArray;

        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(deskrip)){
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseConstract.MyTable.COLUMN_NAMA, title);
            contentValues.put(DatabaseConstract.MyTable.COLUMN_DESKRIPSI, deskrip);
            contentValues.put(DatabaseConstract.MyTable.COLUMN_PHOTO, foto);

            long result = mDB.insert(DatabaseConstract.MyTable.TABLE_NAME, null, contentValues);
            if (result > 0){
                Toast.makeText(ActivityUpload.this, "Data Tersimpan", Toast.LENGTH_LONG).show();
                finish();
            }else {
                Snackbar snackbar = Snackbar.make(rootView, "Data Gagal disimpan.", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }else {
            Snackbar snackbar = Snackbar.make(rootView, "Please fill out these field", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}
