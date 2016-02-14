package com.wingsofts.hongbao;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/2/14.
 */
public class HBListView extends ListView {
    //header显示的图片
    private MyImageView mImageView;
    private Context mContext;
    //抢到红包时候的监听器
    private OnSuccessListener mOnSuccessListener;

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
        Log.e("wing", "deltaY:" + deltaY + " scrollY:" + scrollY);


        if (mImageView.getHeight() < 300) {
            if (isTouchEvent && deltaY < 0) {
                mImageView.getLayoutParams().height += Math.abs(deltaY);
                mImageView.requestLayout();
            }
        }


        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:

                int ran = (int) (Math.random() * 10);
                Log.e("wing", ran + "");
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

                break;
        }

        return super.onTouchEvent(ev);
    }

    private void closeHeader() {
        ObjectAnimator oa = ObjectAnimator.ofInt(mImageView, "height", mImageView.getHeight(), 0);
        oa.start();
    }


    interface OnSuccessListener {
        void onSuccess();
    }


    public void setOnSuccessListener(OnSuccessListener onSuccessListener) {
        mOnSuccessListener = onSuccessListener;
    }
}







