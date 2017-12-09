package com.oportunis.gallery;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ActivityCapture extends AppCompatActivity {

    private CaptureAdapter captureAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        captureAdapter = new ActivityCapture.CaptureAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container_upload);
        mViewPager.setAdapter(captureAdapter);
        setupViewPager(mViewPager);

    }


    @Override
    protected void onResume() {
        super.onResume();
        mViewPager.getAdapter().notifyDataSetChanged();
    }

    public void setupViewPager(ViewPager viewPager) {
        CaptureAdapter adapter = new CaptureAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentCamera());
        viewPager.setAdapter(adapter);
    }

    public class CaptureAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentCapture = new ArrayList<>();
        public CaptureAdapter(FragmentManager fm) {
            super(fm);
        }
        public void addFragment(Fragment fragment) {
            fragmentCapture.add(fragment);
        }
        @Override
        public Fragment getItem(int position) {
            return fragmentCapture.get(position);
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

}
