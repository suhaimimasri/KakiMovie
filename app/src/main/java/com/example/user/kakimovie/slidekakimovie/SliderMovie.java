package com.example.user.kakimovie.slidekakimovie;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

public class SliderMovie extends ViewPager {

    public static final int DefaultScrollDurate = 200;
    public static final int SlideModeScrollDurate = 1000;

    public SliderMovie(Context context){
        super(context);
        awal();
    }

    public SliderMovie(Context context, AttributeSet attrs){
        super(context, attrs);
        awal();
    }

    private void awal(){
        SetDurateScroll(DefaultScrollDurate);
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public void SetDurateScroll(int mili){
        try {
            Class<?> viewpage = FragmentSlider.class;
            Field scroller = viewpage.getDeclaredField("iScroller");
            scroller.setAccessible(true);
            scroller.set(this, new OwnScroller(getContext(), mili));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public class OwnScroller extends Scroller{
        private int DurationScrollMili = 1;

        public OwnScroller(Context context, int durationScroll){
            super(context, new DecelerateInterpolator());
            this.DurationScrollMili = durationScroll;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration){
            super.startScroll(startX,startY,dx,dy,DurationScrollMili);
        }
    }
}
