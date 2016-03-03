package com.example.simpleforframe.refresh;

import in.srain.cube.mints.base.TitleBar;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import in.srain.cube.views.list.ListViewDataAdapter;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

import com.lt.ltframe.R;

public class SimpleFragment extends TitleBar {

	// 下拉刷新上拉刷新simple类

	String[] word = { "slw", "slt", "slt", "slt", "slt", "slt", "slt", "slt",
			"slt", "slt", "slt", "slt", "下拉刷新", "slt", "slt", "slt", "slt",
			"slt", "slt", "slt", "slt", "slt", "slt", "slt", "slt", "slt",
			"slt", "下拉刷新" };
	private ListViewDataAdapter<SimpeEntity> listViewDataAdapter;
	private List<SimpeEntity> themeEntity;
	private GridViewWithHeaderAndFooter gridView;
	private PtrClassicFrameLayout mPtrFrame;
	private View headView;
	private View footView;

	protected View createView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.activity_main, null);
		
		gridView = (GridViewWithHeaderAndFooter) view.findViewById(R.id.grid_view_by_everyday);
		footView = inflater.inflate(R.layout.common_foot, null);
		// 尾部监听，头部显示 可选择性添加
		// footView.setOnClickListener(footoncClickListener);
		// footView.findViewById(R.id.ptr_classic_header_rotate_view_progressbar);
		// footTextView = footView.findViewById(R.id.endmeg);
		headView = inflater.inflate(R.layout.more_new_head_v2, null);
		gridView.addHeaderView(headView);
		gridView.addFooterView(footView);
		gridView.setOnScrollListener(gridViewOnScrollListener);

		listViewDataAdapter = new ListViewDataAdapter<SimpeEntity>();
		listViewDataAdapter.setViewHolderClass(this, SimpleHolder.class);
		themeEntity = listViewDataAdapter.getDataList();
		gridView.setAdapter(listViewDataAdapter);
		getData();

		// 数据刷新完毕时 动画效果复原
		mPtrFrame = (PtrClassicFrameLayout) view
				.findViewById(R.id.more_new_head_frame);
		mPtrFrame.setKeepHeaderWhenRefresh(true);
		mPtrFrame.setPtrHandler(new PtrDefaultHandler() {
			@Override
			public void onRefreshBegin(PtrFrameLayout frame) {
				// 恢复设置
				mPtrFrame.refreshComplete();
			}

			@Override
			public boolean checkCanDoRefresh(PtrFrameLayout frame,
					View content, View header) {
				return super.checkCanDoRefresh(frame, content, header);
			}
		});
		return view;
	}

	private void getData() {

		SimpeEntity defu;

		for (int i = 0; i < word.length; i++) {
			defu = new SimpeEntity();
			defu.setAudio_name(word[i]);
			themeEntity.add(defu);
		}
		listViewDataAdapter.notifyDataSetChanged();

	}

	private OnScrollListener gridViewOnScrollListener = new OnScrollListener() {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			switch (scrollState) {
			// 滚动时
			case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:

				footView.setVisibility(View.VISIBLE);
				break;
			// 当不滚动时
			case OnScrollListener.SCROLL_STATE_IDLE:

				if (gridView.getLastVisiblePosition() > (gridView.getCount() * 0.7)) {
					getData();
				}
				break;
			}
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {

		}
	};

	// 底部加载view点击事件
	private OnClickListener footoncClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {

		}
	};

	// 自定义头部
	@Override
	protected View createHeader() {
		SimpleHead view = new SimpleHead(getActivity());
		view.getImageViewAction().setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				getActivity().finish();
			}
		});

		view.setVisibility(View.GONE);

		return view;
	}

}
