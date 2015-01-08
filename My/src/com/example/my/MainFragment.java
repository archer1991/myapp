package com.example.my;
import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.IXListViewLoadMore;
import me.maxwin.view.IXListViewRefreshListener;
import me.maxwin.view.XListView;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.bean.CommonException;
import com.zhy.bean.NewsItem;
import com.zhy.biz.NewsItemBiz;
import com.zhy.csdn.Constaint;
import com.example.my.NewsItemAdapter;

@SuppressLint("ValidFragment")
public class MainFragment extends Fragment implements IXListViewRefreshListener, IXListViewLoadMore
{
	/**
	 * 默认的newType
	 */
	private int newsType = Constaint.NEWS_TYPE_YEJIE;
	/**
	 * 当前页面
	 */
	private int currentPage = 1;
	/**
	 * 处理新闻的业务类
	 */
	private NewsItemBiz mNewsItemBiz;
	/**
	 * 扩展的ListView
	 */
	private XListView mXListView;
	/**
	 * 数据适配器
	 */
	private NewsItemAdapter mAdapter;
	
	/**
	 * 数据
	 */
	private List<NewsItem> mDatas = new ArrayList<NewsItem>();

	/**
	 * 获得newType
	 * @param newsType
	 */
	public MainFragment(int newsType)
	{
		this.newsType = newsType;
		mNewsItemBiz = new NewsItemBiz();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.tab_item_fragment_main, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		mAdapter = new NewsItemAdapter(getActivity(), mDatas);
		/**
		 * 初始化
		 */
		mXListView = (XListView) getView().findViewById(R.id.id_xlistView);
		mXListView.setAdapter(mAdapter);
		mXListView.setPullRefreshEnable(this);
		mXListView.setPullLoadEnable(this);
		//mXListView.NotRefreshAtBegin();
		/**
		 * 进来时直接刷新
		 */
		mXListView.startRefresh();
	}

	@Override
	public void onRefresh()
	{
		new LoadDatasTask().execute();
	}

	@Override
	public void onLoadMore()
	{
		// TODO Auto-generated method stub

	}
	/**
	 * 记载数据的异步任务
	 * @author zhy
	 *
	 */
	class LoadDatasTask extends AsyncTask<Void, Void, Void>
	{

		@Override
		protected Void doInBackground(Void... params)
		{
			try
			{
				List<NewsItem> newsItems = mNewsItemBiz.getNewsItems(newsType, currentPage);
				mDatas = newsItems;
			} catch (CommonException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			mAdapter.addAll(mDatas);
			mAdapter.notifyDataSetChanged();
			mXListView.stopRefresh();
		}

	}

}
