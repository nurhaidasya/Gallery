package com.oportunis.gallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ActivityMain extends AppCompatActivity implements View.OnClickListener {

    private MainAdapter mainAdapter;
    private ViewPager mViewPager;
    private ImageView bt_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.bt_add = (ImageView) findViewById(R.id.bt_add);
        this.bt_add.setOnClickListener(this);

        mainAdapter = new ActivityMain.MainAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mainAdapter);
        setupViewPager(mViewPager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewPager.getAdapter().notifyDataSetChanged();
    }

    public void setupViewPager(ViewPager viewPager) {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentList());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_add:
                Intent intent = new Intent(ActivityMain.this, ActivityCapture.class);
                startActivity(intent);
                break;
        }
    }

    public class MainAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        public MainAdapter(FragmentManager fm) {
            super(fm);
        }
        public void addFragment(Fragment fragment) {
            fragmentList.add(fragment);
        }
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 1;
        }
    }
}
