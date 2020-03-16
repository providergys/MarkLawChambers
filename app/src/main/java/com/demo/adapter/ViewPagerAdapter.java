package com.demo.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.demo.fragment.FragmentArticles;
import com.demo.fragment.FragmentPodCast;
import com.demo.fragment.FragmentVideo;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new FragmentVideo();
        }
        else if (position == 1)
        {
            fragment = new FragmentPodCast();
        }
        else if (position == 2)
        {
            fragment = new FragmentArticles();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Video";
        }
        else if (position == 1)
        {
            title = "PodCast";
        }
        else if (position == 2)
        {
            title = "Article";
        }
        return title;
    }
}