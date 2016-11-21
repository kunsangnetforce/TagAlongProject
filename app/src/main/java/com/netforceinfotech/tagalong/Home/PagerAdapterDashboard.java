package com.netforceinfotech.tagalong.Home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapterDashboard extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterDashboard(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FindRideFragment home = new FindRideFragment();
                return home;
            case 1:
                OfferFragment currentBet = new OfferFragment();
                return currentBet;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}