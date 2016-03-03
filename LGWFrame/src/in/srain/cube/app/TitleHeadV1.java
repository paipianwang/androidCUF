package in.srain.cube.app;

import com.lt.ltframe.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TitleHeadV1 extends RelativeLayout {
	
	private ImageView btnImgl;
	private TextView tv;

	public TitleHeadV1(Context context) {
		this(context,null);
	}

	public TitleHeadV1(Context context,AttributeSet attrs) {
		this(context,attrs,0);
	}
	
	public TitleHeadV1(Context context,AttributeSet attrs, int defStyle){
		
	super(context,attrs,defStyle);
	LayoutInflater.from(context).inflate(R.layout.test, this);
	
	}
	


	
	

}
