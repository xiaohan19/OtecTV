package com.chinaotec.tv.otectv.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by linhao on 2016/10/12.
 */
public class MyViewPager extends ViewPager {
    private OnKeyDownInterceptListener onKeyDownInterceptListener;

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        // Let the focused view and/or our descendants get the key first
        return super.dispatchKeyEvent(event) || interceptKeyDownEvent(event);
    }

    public boolean interceptKeyDownEvent(KeyEvent event) {
        boolean handled = false;
        if (onKeyDownInterceptListener != null) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                switch (event.getKeyCode()) {
                    case KeyEvent.KEYCODE_DPAD_UP:
                        handled = arrow(event, FOCUS_UP);
                        break;
                    case KeyEvent.KEYCODE_DPAD_DOWN:
                        handled = arrow(event, FOCUS_DOWN);
                        break;
                }
            }
        }
        return handled;
    }

    public boolean arrow(KeyEvent event, int direction) {
        View currentFocused = findFocus();
        if (currentFocused == this) {
            currentFocused = null;
        } else if (currentFocused != null) {
            boolean isChild = false;
            for (ViewParent parent = currentFocused.getParent(); parent instanceof ViewGroup;
                 parent = parent.getParent()) {
                if (parent == this) {
                    isChild = true;
                    break;
                }
            }
            if (!isChild) {
                currentFocused = null;
            }
        }

        boolean handled = false;

        View nextFocused = FocusFinder.getInstance().findNextFocus(this, currentFocused,
                direction);
        if (nextFocused != null && nextFocused != currentFocused) {
            ViewGroup parent = (ViewGroup) currentFocused.getParent();
            for (int i = 0; i < parent.getChildCount(); i++) {
                if (nextFocused == parent.getChildAt(i)) {
                    nextFocused.requestFocus();
                    handled = true;
                }
            }
        } else {
            handled = onKeyDownInterceptListener.OnKeyDownIntercept(this, event.getKeyCode(), event);
        }
        return handled;
    }

    public void setOnKeyDownInterceptListener(OnKeyDownInterceptListener onKeyDownInterceptListener) {
        this.onKeyDownInterceptListener = onKeyDownInterceptListener;
    }

    public interface OnKeyDownInterceptListener {
        boolean OnKeyDownIntercept(ViewGroup viewGroup, int keyCode, KeyEvent event);
    }

}
