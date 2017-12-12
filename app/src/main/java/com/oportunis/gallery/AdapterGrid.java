package com.oportunis.gallery;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by User on 12/12/2017.
 */

public class AdapterGrid extends RecyclerView.Adapter<AdapterGrid.ViewHolderGrid> {

    private Context context;
    private Cursor cursor;

    public AdapterGrid(Context context) {
        this.context = context;
    }

    public void swapCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public ViewHolderGrid onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_grid, parent, false);
        return new ViewHolderGrid(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderGrid holder, int position) {
        cursor.moveToPosition(position);

        int indexColomId = cursor.getColumnIndex(DatabaseConstract.MyTable._ID);
        int indexColomTitle = cursor.getColumnIndex(DatabaseConstract.MyTable.COLUMN_NAMA);
        int indexColomDeskrip = cursor.getColumnIndex(DatabaseConstract.MyTable.COLUMN_DESKRIPSI);
        int indexColomFoto = cursor.getColumnIndex(DatabaseConstract.MyTable.COLUMN_PHOTO);

        final String id = cursor.getString(indexColomId);
        final String title = cursor.getString(indexColomTitle);
        final String deskripsi = cursor.getString(indexColomDeskrip);
        final byte[] foto = cursor.getBlob(indexColomFoto);

        Bitmap image = BitmapFactory.decodeByteArray(foto, 0, foto.length);
        holder.imgGRID.setImageBitmap(image);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }

    public class ViewHolderGrid extends RecyclerView.ViewHolder {
        public ImageView imgGRID;

        public ViewHolderGrid(View itemView) {
            super(itemView);
            this.imgGRID = itemView.findViewById(R.id.img_grid);
        }
    }
}
