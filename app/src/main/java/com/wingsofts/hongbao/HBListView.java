package com.wingsofts.hongbao;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/2/14.
 */
public class HBListView extends ListView {

    //header显示的图片
    private MyImageView mImageView;
    private Context mContext;
    //抢到红包时候的监听器
    private OnSuccessListener mOnSuccessListener;

    private boolean b = false;

    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;

    public HBListView(Context context) {
        this(context, null);
    }

    public HBListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HBListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void changeSize(MyImageView imageView) {
        mImageView = imageView;
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        if (mImageView.getHeight() < 300) {
            if (isTouchEvent && deltaY < 0) {
                mImageView.getLayoutParams().height += Math.abs(deltaY)/2;
                mImageView.requestLayout();
            }
        }

        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                x1 = ev.getX();
                y1 = ev.getY();

                if (oa != null) {
                    if (oa.isRunning()) {
                        oa.cancel();
                    }
                }

                if (mImageView.getHeight() == 0) {
                    mImageView.mTime = 0;
                }
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:

                x2 = ev.getX();
                y2 = ev.getY();
                if (y1 - y2 > 0) {
                    b = false;
                } else if (y2 - y1 > 0) {
                    b = true;
                }

                int ran = (int) (Math.random() * 100);
                Log.e("wing", ran + "");
                if (b) {  //往下滑

                    if (ran > 3) {
                        mImageView.mTime++;
                        closeHeader();
                    } else {
                        mImageView.mTime = 0;
                        if (mOnSuccessListener != null) {
                            mOnSuccessListener.onSuccess();
                        }
                        closeHeader();
                    }
                }

                break;
        }
        return super.onTouchEvent(ev);
    }

    private ObjectAnimator oa;

    private void closeHeader() {
        oa = ObjectAnimator.ofInt(mImageView, "height", mImageView.getHeight(), 0);
        oa.start();

    }

    interface OnSuccessListener {
        void onSuccess();
    }

    public void setOnSuccessListener(OnSuccessListener onSuccessListener) {
        mOnSuccessListener = onSuccessListener;
    }
}

