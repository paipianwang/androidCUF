package com.pangfeng.frame.simple;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lt.ltframe.R;

public class SimpleHead extends RelativeLayout {
	
	private ImageView btnImgl;
	private TextView tv;

	public SimpleHead(Context context) {
		this(context,null);
	}

	public SimpleHead(Context context,AttributeSet attrs) {
		this(context,attrs,0);
	}
	
	public SimpleHead(Context context,AttributeSet attrs, int defStyle){
		
	super(context,attrs,defStyle);
	LayoutInflater.from(context).inflate(R.layout.new_second_top_title, this);
	btnImgl = (ImageView)findViewById(R.id.bar_img);
	tv = (TextView)findViewById(R.id.bar_text);
	
	
		
		
	}
	
	public void setText(String str){
		tv.setText(str);
	}
	
	public ImageView getImageViewAction(){
		return btnImgl;
	}
	
	public void setImageDrable(int drawable){
		btnImgl.setImageDrawable(getResources().getDrawable(drawable));
	}
	
	
	

}
