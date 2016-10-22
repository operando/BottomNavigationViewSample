package com.os.operando.bottomnavigationview.sample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class BottomNavigationPagerAdapter extends FragmentPagerAdapter {

    public BottomNavigationPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return EmailFragment.newInstance();
            case 1:
                return CameraFragment.newInstance();
            case 2:
                return MapFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
