package com.maosong.component.adapter;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class SimpleViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fList;
    private List<String> titles;

    public SimpleViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> fList, List<String> titles) {
        super(fm);
        this.fList = fList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fList.get(position);
    }

    @Override
    public int getCount() {
        return fList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
