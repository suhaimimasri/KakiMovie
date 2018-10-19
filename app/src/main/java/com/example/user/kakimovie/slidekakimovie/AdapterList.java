package com.example.user.kakimovie.slidekakimovie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterList extends FragmentStatePagerAdapter {

    private static final String TAG = "AdapterList";

    private List<Fragment> iFrags;

    public AdapterList(FragmentManager fm, List<Fragment> frags){
        super(fm);
        this.iFrags = frags;
    }

    @Override
    public Fragment getItem(int position){
        return this.iFrags.get(position%iFrags.size());
    }

    @Override
    public int getCount(){
        return Integer.MAX_VALUE;
    }
}
