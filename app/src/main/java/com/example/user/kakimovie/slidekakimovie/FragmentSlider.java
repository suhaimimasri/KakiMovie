package com.example.user.kakimovie.slidekakimovie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.user.kakimovie.R;

import java.util.ArrayList;

public class FragmentSlider extends Fragment {

    private int image;

    public FragmentSlider(){
    }

    public static FragmentSlider newInstance(int params){
        FragmentSlider fragment = new FragmentSlider();
        Bundle a = new Bundle();
        a.putInt("image", params);
        fragment.setArguments(a);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        image=getArguments().getInt("image");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_slider_item, container, false);
        ImageView img = (ImageView) view.findViewById(R.id.imageslide);
        img.setImageResource(image);
        return view;
    }
}
