package in.srain.cube.mints.base;

import in.srain.cube.app.CubeFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lt.ltframe.R;

/**
 * <a href="http://www.liaohuqiu.net/unified-title-header/">http://www.liaohuqiu.net/unified-title-header/</a>
 *
 * @author http://www.liaohuqiu.net
 */
public abstract class TitleBar extends CubeFragment {

  //  protected TitleHeaderBar mTitleHeaderBar;
    protected LinearLayout mContentContainer;
    protected ImageView imageLeft;
    protected ImageView imageRight;
    protected TextView text;
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.newtop_title1, container,false);
        View headerView=createHeader();
        View contentView = createView(inflater, view, savedInstanceState);
        headerView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); 
        view.addView(headerView);
        view.addView(contentView);
        return view;
    }

    protected int getFrameLayoutId(int check) {
    	
        return R.layout.cube_mints_base_content_frame_with_title_header;
    }

    /**
     * make it looks like Activity
     */
    private void onBackPressed() {
        getContext().onBackPressed();
    }

    protected boolean enableDefaultBack() {
        return true;
    }


    protected void setTitleText(String str) {
    	text.setText(str);
    }
    


    public TitleHeaderBar getTitleHeaderBar() {
        return null;
    }
}
