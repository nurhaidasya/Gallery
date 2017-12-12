package com.oportunis.gallery;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by User on 12/12/2017.
 */

public class FragmentGrid extends Fragment {

    private View view;
    private RecyclerView rvGrid;
    private SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private AdapterGrid adapterGrid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_grid, container, false);
        this.rvGrid = view.findViewById(R.id.rv_grid);

        mDBHelper = new DatabaseHelper(getContext());
        mDB = mDBHelper.getReadableDatabase();

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        adapterGrid = new AdapterGrid(getContext());

        rvGrid.setAdapter(adapterGrid);
        rvGrid.setLayoutManager(layoutManager);

        adapterGrid.swapCursor(getAllPengeluaran());
        adapterGrid.notifyDataSetChanged();

        return view;
    }

    private Cursor getAllPengeluaran() {
        return mDB.query(DatabaseConstract.MyTable.TABLE_NAME, null, null, null, null, null, null);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapterGrid.swapCursor(getAllPengeluaran());
        adapterGrid.notifyDataSetChanged();
    }
}
