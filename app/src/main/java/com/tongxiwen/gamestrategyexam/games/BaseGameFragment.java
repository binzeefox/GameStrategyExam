package com.tongxiwen.gamestrategyexam.games;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import com.tongxiwen.gamestrategyexam.MainActivity;

/**
 * Created by tong.xiwen on 2018/4/20.
 */
public abstract class BaseGameFragment extends Fragment {

    protected MainActivity mActivity;

    public BaseGameFragment(MainActivity activity){
        this.mActivity = activity;
    }

    protected TextView getConsoleField(){
        return mActivity.getConsoleField();
    }

    protected void setTitle(CharSequence title){
        mActivity.setTitle(title);
    }
}
