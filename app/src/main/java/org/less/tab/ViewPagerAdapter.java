package org.less.tab;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import less.org.tab.TabPagerAdapter;

/**
 * ===============================================
 * DEVELOPER : RenYang <br/>
 * DATE : 2016/11/1 <br/>
 * DESCRIPTION :
 */
public class ViewPagerAdapter extends TabPagerAdapter{

    String[] str = {"Less","Is","God"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getPageColor(int position) {
        return Color.RED;
    }

    @Override
    public Fragment getItem(int position) {
        return new ExampleFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
