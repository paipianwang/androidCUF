package in.srain.cube.mints.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.lt.ltframe.R;

public abstract class TitleBase extends MintsBaseActivity {

    protected LinearLayout mTitleHeaderBar;
    protected LinearLayout mContentContainer;
    protected abstract View createHeader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    protected int getFrameLayoutId() {
        return R.layout.cube_mints_base_content_frame_with_title_header;
    }

    protected LinearLayout getTitleHeaderBar() {
        return (LinearLayout) findViewById(R.id.cube_mints_content_frame_title_header);
    }

    protected LinearLayout getContentContainer() {
        return (LinearLayout) findViewById(R.id.cube_mints_content_frame_content);
    }

    protected void initViews() {
        super.setContentView(getFrameLayoutId());
        mTitleHeaderBar = getTitleHeaderBar();
        mTitleHeaderBar.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); 
        mTitleHeaderBar.addView(createHeader());
        mContentContainer = getContentContainer();
    }

    protected boolean enableDefaultBack() {
        return true;
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mContentContainer.addView(view);
    }

    public void setContentViewSupper(int layoutResID) {
        super.setContentView(layoutResID);
    }
}
