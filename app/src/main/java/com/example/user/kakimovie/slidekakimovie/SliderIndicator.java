package com.example.user.kakimovie.slidekakimovie;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

public class SliderIndicator implements ViewPager.OnPageChangeListener {
    private Context iContext;
    private LinearLayout iContainer;
    private int iDrawable;
    private int iSpacing;
    private int iSize;
    private ViewPager iViewPage;
    private int iPageCount;
    private int iInitialPage = 0;
    private int defaultSizeInDp = 12;
    private int defaultSpacingInDp = 12;

    public SliderIndicator(@NonNull Context context, @NonNull LinearLayout containerView, @NonNull ViewPager viewpager, @DrawableRes int drawableRes){
        if (context == null){
            throw new IllegalArgumentException("context cannot be null");
        }else if (containerView == null){
            throw new IllegalArgumentException("containerView cannot be null");
        }else if (viewpager == null){
            throw new IllegalArgumentException("ViewPager cannot be null");
        }else if (viewpager.getAdapter() == null){
            throw new IllegalArgumentException("ViewPager does not have and adapter set on it");
        }

        iContext = context;
        iContainer = containerView;
        iDrawable = drawableRes;
        iViewPage = viewpager;
    }

    public void setPageCount(int pageCount){
        iPageCount = pageCount;
    }

    public void setInitialPage(int page){
        iInitialPage = page;
    }

    public void setDrawable(@DrawableRes int drawable){
        iDrawable = drawable;
    }

    public void setSpacingRes(@DimenRes int spacingRes){
        iSpacing = spacingRes;
    }

    public void setSize(@DimenRes int dimenRes){
        iSize = dimenRes;
    }

    public void show(){
        initIndicator();
        setIndicatorasSelected(iInitialPage);
        new android.os.Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                iViewPage.setCurrentItem(1);
            }
        }, 2500);
    }

    private void initIndicator(){
        if (iContainer == null || iPageCount <= 0){
            return;
        }

        iViewPage.addOnPageChangeListener(this);
        Resources res = iContext.getResources();
        iContainer.removeAllViews();
        for (int i = 0; i <iPageCount;i++){
            View view = new View(iContext);
            int dimen = iSize != 0 ? res.getDimensionPixelSize(iSize) :((int) res.getDisplayMetrics().density * defaultSizeInDp);
            int margin = iSpacing != 0 ? res.getDimensionPixelSize(iSpacing) : ((int) res.getDisplayMetrics().density * defaultSpacingInDp);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dimen, dimen);
            lp.setMargins(i == 0 ? 0 : margin, 0, 0, 0);
            view.setLayoutParams(lp);
            view.setBackgroundResource(iDrawable);
            view.setSelected(i == 0);
            iContainer.addView(view);
        }
    }

    private void setIndicatorasSelected(int index){
        if (iContainer == null){
            return;
        }
        for (int i = 0; i <iContainer.getChildCount(); i++){
            View view = iContainer.getChildAt(i);
            view.setSelected(i == index);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){

    }

    @Override
    public void onPageSelected(int position){
        int index = position % iPageCount;
        setIndicatorasSelected(index);
        final int moveTo = position + 1;
        try {
            new android.os.Handler().postDelayed(new Runnable(){
                @Override public void run(){
                    iViewPage.setCurrentItem(moveTo);
                }
            }, 2500);
        }catch (Exception e){
        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void cleanup() {
        iViewPage.clearOnPageChangeListeners();
    }
}
