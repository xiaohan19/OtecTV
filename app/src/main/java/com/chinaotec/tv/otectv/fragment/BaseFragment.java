package com.chinaotec.tv.otectv.fragment;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;

/**
 * Created by linhao on 2016/10/12.
 */
public abstract class BaseFragment extends Fragment {
    private OnKeyInterceptListener onKeyInterceptListener;
    private boolean mFocus;

    public void setOnKeyInterceptListener(OnKeyInterceptListener onKeyInterceptListener) {
        this.onKeyInterceptListener = onKeyInterceptListener;
    }

    /**
     * 此方法执行拦截；
     * 配合{@link #setOnKeyInterceptListener(OnKeyInterceptListener)}一起使用；
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

    public boolean isFocus() {
        return mFocus;
    }

    public void setFocus(boolean focus) {
        mFocus = focus;
        if (onFocusChange(mFocus) || mFocus) {
            mFocus = !mFocus;
            onFocusChange(mFocus);
        }
    }

    /**
     * 此方法只为折中方法，因为fragment非view子类无法控制焦点；
     * 所以当焦点传递最终 还是未被真实view消费；则真实焦点还是停留在原始的真实view上；
     * PS:此方法请配合{@link #setFocus(boolean)}一起使用；
     *
     * @param hasFocus
     * @return 焦点是否被消费，已经传递或者消费请返回true；
     */
    public boolean onFocusChange(boolean hasFocus) {
        return false;
    }

    public interface OnKeyInterceptListener {
        boolean OnKeyIntercept(Fragment fragment, int keyCode, KeyEvent event);
    }

}
