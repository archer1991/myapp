package com.example.my;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.viewpagerindicator.TabPageIndicator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;



public class Mainpage extends  SlidingFragmentActivity {

	
	private TabPageIndicator mIndicator ;  
    private ViewPager mViewPager ;  
    private FragmentPagerAdapter mAdapter ; 
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainpage);
		// 初始化silding menu
		initLeftmenu();
		
		mIndicator = (TabPageIndicator) findViewById(R.id.id_indicator);  
        mViewPager = (ViewPager) findViewById(R.id.id_pager);  
        mAdapter = new TabAdapter(getSupportFragmentManager());  
        mViewPager.setAdapter(mAdapter);  
        mIndicator.setViewPager(mViewPager, 0); 
		

	}

	private void initLeftmenu() {
		// TODO 自动生成的方法存根
//		Fragment leftMenuFragment = new Left_menu();
		setBehindContentView(R.layout.left_menu_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.id_left_menu_frame, new Left_menu()).commit();
		SlidingMenu menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT);
		// 设置触摸屏幕的模式
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);
		// 设置滑动菜单视图的宽度
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// menu.setBehindWidth()
		// 设置渐入渐出效果的值
		menu.setFadeDegree(0.35f);
		 menu.setBehindScrollScale(1.0f);
		menu.setSecondaryShadowDrawable(R.drawable.shadow);
//		menu.setMenu(R.layout.left_menu);
		// 设置右边（二级）侧滑菜单
//		menu.setSecondaryMenu(R.layout.right_menu_frame);
//		Fragment rightMenuFragment = new MenuRightFragment();
//		getSupportFragmentManager().beginTransaction()
//				.replace(R.id.id_right_menu_frame, rightMenuFragment).commit();

	}

	public void showLeftMenu(View view) {
		getSlidingMenu().showMenu();
	}

	
	
	
	
}
