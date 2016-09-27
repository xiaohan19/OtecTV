package com.chinaotec.tv.otectv.custom;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import com.chinaotec.tv.otectv.R;

/**
 * Created by linhao on 2016/9/22.
 * 配合 android:clipChildren="false" android:clipToPadding="false" 让其不限制子view的显示范围(否则阴影不可见)
 */
public class ShadowView extends FrameLayout {
    private static final String TAG = "ShadowView";
    private static final int DEFAULT_TRAN_DUR_ANIM = 300;
    private static final float DEFAULT_SCALE = 1.0f;

    private Drawable mShadow;
    private Drawable mDrawable;
    private RectF mShadowPaddingRect = new RectF(0, 0, 0, 0);
    private RectF mUpPaddingRect = new RectF(0, 0, 0, 0);

    private boolean isDrawUpRect = true;
    private View mFocusView;
    private boolean isInDraw = false;
    private AnimatorSet mCurrentAnimatorSet;
    private boolean mIsHide = false;
    private NewAnimatorListener mNewAnimatorListener;
    private float mScaleX = 0;
    private float mScaleY = 0;

    public ShadowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ShadowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setWillNotDraw(false);
        this.setVisibility(View.INVISIBLE);
        invalidate();
        // 初始化.
        if (attrs != null) {
            TypedArray tArray = context.obtainStyledAttributes(attrs, R.styleable.ShadowView);// 获取配置属性
            Drawable drawableUpRect = tArray.getDrawable(R.styleable.ShadowView_upImageRes); // 顶层图片.
            Drawable drawableShadow = tArray.getDrawable(R.styleable.ShadowView_shadowImageRes); // 阴影图片.
            setDrawable(drawableUpRect);
            setShadow(drawableShadow);
            tArray.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        if (!isDrawUpRect) {
            onDrawShadow(canvas);
            onDrawUpRect(canvas);
        }
        // 绘制焦点子控件.
        if (mFocusView != null && (!isDrawUpRect && isInDraw)) {
            onDrawFocusView(canvas);
        }
        if (isDrawUpRect) {
            onDrawShadow(canvas);
            onDrawUpRect(canvas);
        }
        canvas.restore();
    }

    public void onDrawFocusView(Canvas canvas) {
        View view = mFocusView;
        canvas.save();
        float scaleX = (float) (getView().getWidth()) / (float) view.getWidth();
        float scaleY = (float) (getView().getHeight()) / (float) view.getHeight();
        canvas.scale(scaleX, scaleY);
        view.draw(canvas);
        canvas.restore();
    }

    public void onDrawShadow(Canvas canvas) {
        Drawable drawableShadow = getShadow();
        if (drawableShadow != null) {
            RectF shadowPaddingRect = getDrawShadowRect();
            int width = getView().getWidth();
            int height = getView().getHeight();
            Rect padding = new Rect();
            drawableShadow.getPadding(padding);
            int left = (int) Math.rint(shadowPaddingRect.left);
            int right = (int) Math.rint(shadowPaddingRect.right);
            int bottom = (int) Math.rint(shadowPaddingRect.bottom);
            int top = (int) Math.rint(shadowPaddingRect.top);
            drawableShadow.setBounds(-padding.left - (left), -padding.top - (top),
                    width + padding.right + (right),
                    height + padding.bottom + (bottom));
            drawableShadow.draw(canvas);
        }
    }

    public void onDrawUpRect(Canvas canvas) {
        Drawable drawableUp = getDrawable();
        if (drawableUp != null) {
            RectF paddingRect = getDrawUpRect();
            int width = getView().getWidth();
            int height = getView().getHeight();
            Rect padding = new Rect();
            drawableUp.getPadding(padding);
            int left = (int) Math.rint(paddingRect.left);
            int right = (int) Math.rint(paddingRect.right);
            int bottom = (int) Math.rint(paddingRect.bottom);
            int top = (int) Math.rint(paddingRect.top);
            drawableUp.setBounds(-padding.left - (left), -padding.top - (top),
                    width + padding.right + (right),
                    height + padding.bottom + (bottom));
            drawableUp.draw(canvas);
        }
    }

    public void setDrawShadowPadding(int size) {
        mShadowPaddingRect.set(new Rect(size, size, size, size));
    }

    public RectF getDrawShadowRect() {
        return this.mShadowPaddingRect;
    }

    public Drawable getShadow() {
        return this.mShadow;
    }

    public void setShadow(Drawable shadow) {
        mShadow = shadow;
    }

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }

    public void setDrawUpRectPadding(int size) {
        mUpPaddingRect.set(new Rect(size, size, size, size));
    }

    public RectF getDrawUpRect() {
        return this.mUpPaddingRect;
    }

    public void setFocusView(View newFocusView, float scale) {
        setFocusView(newFocusView, null, scale);
    }

    public void setFocusView(View newFocusView, View oldFocusView, float scale) {
        setFocusView(newFocusView, oldFocusView, scale, scale);
    }

    public void setFocusView(View newFocusView, View oldFocusView, float scaleX, float scaleY) {
        if (newFocusView != null) {
            mFocusView = newFocusView;
            newFocusView.animate().scaleX(scaleX).scaleY(scaleY).setDuration(DEFAULT_TRAN_DUR_ANIM).start();
            runTranslateAnimation(newFocusView, scaleX, scaleY);
        }
        setUnFocusView(oldFocusView);
    }

    public void setUnFocusView(View oldFocusView) {
        setUnFocusView(oldFocusView, DEFAULT_SCALE, DEFAULT_SCALE);
    }

    public void setUnFocusView(View oldFocusView, float scaleX, float scaleY) {
        if (oldFocusView != null) {
            oldFocusView.animate().scaleX(scaleX).scaleY(scaleY).setDuration(DEFAULT_TRAN_DUR_ANIM).start();
        }
    }

    private void runTranslateAnimation(View toView, float scaleX, float scaleY) {
        try {
            flyWhiteBorder(toView, getView(), scaleX, scaleY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ShadowView getView() {
        return ShadowView.this;
    }

    public void flyWhiteBorder(final View focusView, View moveView, float scaleX, float scaleY) {
// 用于修复5.0边框错位问题.
        this.mScaleX = mScaleX;
        this.mScaleY = mScaleY;

        int newWidth = 0;
        int newHeight = 0;
        int oldWidth = 0;
        int oldHeight = 0;

        float newX = 0;
        float newY = 0;

        if (focusView != null) {
            newWidth = (int) (Math.rint(focusView.getMeasuredWidth() * scaleX));
            newHeight = (int) (Math.rint(focusView.getMeasuredHeight() * scaleY));
            oldWidth = moveView.getMeasuredWidth();
            oldHeight = moveView.getMeasuredHeight();
            Rect fromRect = findLocationWithView(moveView); // 获取moveView在屏幕上的位置.
            Rect toRect = findLocationWithView(focusView);  // 获取focusView在屏幕上的位置.
            int x = toRect.left - fromRect.left;
            int y = toRect.top - fromRect.top;
            newX = x - Math.abs(focusView.getMeasuredWidth() - newWidth) / 2;
            newY = y - Math.abs(focusView.getMeasuredHeight() - newHeight) / 2;
        }

        // 取消之前的动画.
        if (mCurrentAnimatorSet != null)
            mCurrentAnimatorSet.cancel();

        ObjectAnimator transAnimatorX = ObjectAnimator.ofFloat(moveView, "translationX", newX);
        ObjectAnimator transAnimatorY = ObjectAnimator.ofFloat(moveView, "translationY", newY);
        // BUG，因为缩放会造成图片失真(拉伸).
        // hailong.qiu 2016.02.26 修复 :)
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofInt(new ScaleView(moveView), "width", oldWidth,
                (int) newWidth);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofInt(new ScaleView(moveView), "height", oldHeight,
                (int) newHeight);
        //
        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(transAnimatorX, transAnimatorY, scaleXAnimator, scaleYAnimator);
        mAnimatorSet.setInterpolator(new DecelerateInterpolator(1));
        mAnimatorSet.setDuration(DEFAULT_TRAN_DUR_ANIM);
        mAnimatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (!isDrawUpRect)
                    isInDraw = false;
                if (mIsHide) {
                    getView().setVisibility(View.INVISIBLE);
                }
                if (mNewAnimatorListener != null)
                    mNewAnimatorListener.onAnimationStart(ShadowView.this, focusView, animation);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                if (!isDrawUpRect)
                    isInDraw = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!isDrawUpRect)
                    isInDraw = true;
                getView().setVisibility(mIsHide ? View.INVISIBLE : View.VISIBLE);
                if (mNewAnimatorListener != null)
                    mNewAnimatorListener.onAnimationEnd(ShadowView.this, focusView, animation);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                if (!isDrawUpRect)
                    isInDraw = false;
            }
        });
        mAnimatorSet.start();
        mCurrentAnimatorSet = mAnimatorSet;
    }

    public Rect findLocationWithView(View view) {
        ViewGroup root = (ViewGroup) getView().getParent();
        Rect rect = new Rect();
        root.offsetDescendantRectToMyCoords(view, rect);
        return rect;
    }

    public interface NewAnimatorListener {
        public void onAnimationStart(ShadowView shadowView, View view, Animator animation);

        public void onAnimationEnd(ShadowView shadowView, View view, Animator animation);
    }

    public class ScaleView {
        private View view;
        private int width;
        private int height;

        public ScaleView(View view) {
            this.view = view;
        }

        public int getWidth() {
            return view.getLayoutParams().width;
        }

        public void setWidth(int width) {
            this.width = width;
            view.getLayoutParams().width = width;
            view.requestLayout();
        }

        public int getHeight() {
            return view.getLayoutParams().height;
        }

        public void setHeight(int height) {
            this.height = height;
            view.getLayoutParams().height = height;
            view.requestLayout();
        }
    }


}
