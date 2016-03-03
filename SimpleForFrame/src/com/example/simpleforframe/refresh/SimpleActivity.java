package com.example.simpleforframe.refresh;

import in.srain.cube.mints.base.MintsBaseActivity;
import android.os.Bundle;
import com.lt.ltframe.R;

public class SimpleActivity extends MintsBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		this.pushFragmentToBackStack(SimpleFragment.class, "");

	}

	protected String getCloseWarning() {
		return "返回主页";
	}

	@Override
	protected int getFragmentContainerId() {
		return R.id.id_fragment;
	}
}
