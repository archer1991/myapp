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
	 * Ĭ�ϵ�newType
	 */
	private int newsType = Constaint.NEWS_TYPE_YEJIE;
	/**
	 * ��ǰҳ��
	 */
	private int currentPage = 1;
	/**
	 * �������ŵ�ҵ����
	 */
	private NewsItemBiz mNewsItemBiz;
	/**
	 * ��չ��ListView
	 */
	private XListView mXListView;
	/**
	 * ����������
	 */
	private NewsItemAdapter mAdapter;
	
	/**
	 * ����
	 */
	private List<NewsItem> mDatas = new ArrayList<NewsItem>();

	/**
	 * ���newType
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
		 * ��ʼ��
		 */
		mXListView = (XListView) getView().findViewById(R.id.id_xlistView);
		mXListView.setAdapter(mAdapter);
		mXListView.setPullRefreshEnable(this);
		mXListView.setPullLoadEnable(this);
		//mXListView.NotRefreshAtBegin();
		/**
		 * ����ʱֱ��ˢ��
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
	 * �������ݵ��첽����
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
