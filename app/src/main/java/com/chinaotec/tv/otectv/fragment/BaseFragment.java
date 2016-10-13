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

    public boolean isFocus() {
        return mFocus;
    }

    /**
     * 此方法并未获得真实焦点，只作为焦点记录和传递；
     * 详细说明请参照{@link #onFocusChange(boolean)};
     */

    public void requestFocus() {
        mFocus = true;
        if (onFocusChange(mFocus)) {
            mFocus = !mFocus;
            onFocusChange(mFocus);
        } else {
            mFocus = false;
        }
    }

    /**
     * 注：此方法仅为TV端，会应用{@link android.view.View#bringToFront()}才使用此方法，否侧无需使用（焦点由Android控制）；
     * <p/>
     * 此方法只为折中方法，因为fragment非view子类无法控制焦点；
     * 所以当焦点传递最终 还是未被真实view消费；则真实焦点还是停留在原始的真实view上；
     * PS:此方法请配合{@link #requestFocus()}一起使用；
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
