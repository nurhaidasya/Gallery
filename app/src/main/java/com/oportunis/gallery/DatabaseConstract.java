package com.oportunis.gallery;

import android.provider.BaseColumns;

/**
 * Created by User on 11/26/2017.
 */

public class DatabaseConstract {
    public static final class MyTable implements BaseColumns{
        public static final String TABLE_NAME = "photo_book";
        public static final String COLUMN_NAMA = "Nama";
        public static final String COLUMN_DESKRIPSI = "Deskripsi";
        public static final String COLUMN_PHOTO = "Photo";
    }
}
