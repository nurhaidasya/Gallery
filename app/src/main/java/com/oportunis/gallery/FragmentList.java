package com.oportunis.gallery;


import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by User on 11/27/2017.
 */

public class FragmentList extends Fragment {

    private View view;
    private RecyclerView rvLIST;
    private SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private AdapterList adapterList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = (View) inflater.inflate(R.layout.fragment_list, container, false);
        this.rvLIST = (RecyclerView) view.findViewById(R.id.rv_list);

        mDBHelper = new DatabaseHelper(getContext());
        mDB = mDBHelper.getReadableDatabase();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvLIST.setLayoutManager(layoutManager);

        adapterList = new AdapterList(getContext());
        rvLIST.setAdapter(adapterList);

        adapterList.swapCursor(getAllPengeluaran());
        adapterList.notifyDataSetChanged();

        return view;
    }

    private Cursor getAllPengeluaran(){
        return mDB.query(DatabaseConstract.MyTable.TABLE_NAME,null,null,null,null,null,null);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapterList.swapCursor(getAllPengeluaran());
        adapterList.notifyDataSetChanged();
    }

}
