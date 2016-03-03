package com.example.simpleforframe;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {
	Button bt;
	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

}
