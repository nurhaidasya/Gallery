package com.oportunis.gallery;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

/**
 * Created by User on 11/27/2017.
 */

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolderList> {

    private Context context;
    private Cursor cursor;
    private ViewHolderList holder;
    private DatabaseHelper dbhelper = new DatabaseHelper(context);

    public AdapterList(Context context) {
        this.context = context;
    }

    public void swapCursor(Cursor cursor){
        this.cursor = cursor;
    }

    @Override
    public AdapterList.ViewHolderList onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolderList(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolderList holder, int position) {
        this.holder = holder;
        cursor.moveToPosition(position);

        int indexColomId = cursor.getColumnIndex(DatabaseConstract.MyTable._ID);
        int indexColomTitle = cursor.getColumnIndex(DatabaseConstract.MyTable.COLUMN_NAMA);
        int indexColomDeskrip = cursor.getColumnIndex(DatabaseConstract.MyTable.COLUMN_DESKRIPSI);
        int indexColomFoto = cursor.getColumnIndex(DatabaseConstract.MyTable.COLUMN_PHOTO);

        final String id = cursor.getString(indexColomId);
        String title = cursor.getString(indexColomTitle);
        String deskripsi = cursor.getString(indexColomDeskrip);
        final byte [] foto = cursor.getBlob(indexColomFoto);

        final Bitmap image = BitmapFactory.decodeByteArray(foto, 0, foto.length);
        holder.imgLIST.setImageBitmap(image);

        holder.tv_name.setText(title);
        holder.tv_deskrip.setText(deskripsi);
    }

    @Override
    public int getItemCount() {
        if (cursor == null){
            return 0;
        }
        return cursor.getCount();
    }

    public class ViewHolderList extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public TextView tv_deskrip;
        public ImageView imgLIST;

        public ViewHolderList(View itemView) {
            super(itemView);
            this.imgLIST = (ImageView) itemView.findViewById(R.id.img_list);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_nama);
            this.tv_deskrip = (TextView) itemView.findViewById(R.id.tv_deskrip);
        }
    }
}
