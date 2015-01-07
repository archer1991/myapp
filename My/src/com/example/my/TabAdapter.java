package com.example.my;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.app.FragmentManager;

public class TabAdapter extends FragmentPagerAdapter {

	public static final String[] TITLES = new String[] { "业界", "移动", 
			 "云计算" };

	public TabAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		MainFragment fragment = new MainFragment(arg0);
		return fragment;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return TITLES[position % TITLES.length];
	}

	@Override
	public int getCount() {
		return TITLES.length;
	}

}
