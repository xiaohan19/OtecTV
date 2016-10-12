package com.chinaotec.tv.otectv.fragment;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;

/**
 * Created by linhao on 2016/10/12.
 */
public class BaseFragment extends Fragment {
    private OnKeyInterceptListener onKeyInterceptListener;

    public void setOnKeyInterceptListener(OnKeyInterceptListener onKeyInterceptListener) {
        this.onKeyInterceptListener = onKeyInterceptListener;
    }

    /**
     * 此方法执行拦截；配合setOnKeyInterceptListener一起使用；
     *
     * @param event
     * @return
     */
    public boolean executeKeyEvent(KeyEvent event) {
        boolean handled = false;
        if (onKeyInterceptListener != null) {
            handled = onKeyInterceptListener.OnKeyIntercept(this, event.getKeyCode(), event);
        }
        return handled;
    }

    public interface OnKeyInterceptListener {
        boolean OnKeyIntercept(Fragment fragment, int keyCode, KeyEvent event);
    }


}
