package com.pangfeng.frame.simple;

import in.srain.cube.views.list.ViewHolderBase;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lt.ltframe.R;

public class SimpleHolder extends ViewHolderBase<SimpeEntity> {

	private TextView themeCoin;

	@SuppressLint("InflateParams")
	@Override
	public View createView(LayoutInflater layoutInflater) {
		View view = layoutInflater.inflate(R.layout.success, null);
		themeCoin = (TextView) view.findViewById(R.id.getid);
		return view;
	}

	
	
	@Override
	public void showData(int position, SimpeEntity itemData) {
		
		themeCoin.setText(itemData.getAudio_name());		


	}
}
