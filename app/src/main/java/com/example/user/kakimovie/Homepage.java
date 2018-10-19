package com.example.user.kakimovie;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.kakimovie.listmovie.MovieAdapter;
import com.example.user.kakimovie.slidekakimovie.AdapterList;
import com.example.user.kakimovie.slidekakimovie.FragmentSlider;
import com.example.user.kakimovie.slidekakimovie.SliderIndicator;
import com.example.user.kakimovie.slidekakimovie.SliderMovie;

import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity {

    private AdapterList iAdapter;
    private MovieAdapter movies1;
    private RecyclerView recycler;
    private SliderIndicator iIndicator;
    private SliderMovie sliderView;
    private LinearLayout iLinearLayout;
    private ArrayList<movie> mov = new ArrayList<>();
    ArrayList<String> editTextValue;

    List<Fragment> fragments;

    ArrayList<Integer> images =new ArrayList<Integer>(){{
        add(R.drawable.movie1);
        add(R.drawable.movie2);
        add(R.drawable.movie3);
        add(R.drawable.movie4);
        add(R.drawable.movie5);
        add(R.drawable.movie6);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        editTextValue = new ArrayList<String>();

        if(getIntent().hasExtra("title") && getIntent().hasExtra("rating") && getIntent().hasExtra("status")){

            ArrayList<String> title = getIntent().getStringArrayListExtra("title");
            ArrayList<String> rating = getIntent().getStringArrayListExtra("rating");
            ArrayList<String> status = getIntent().getStringArrayListExtra("status");
            System.out.println("Error 2");
            for (int i = 0; i < title.size(); i++) {
                mov.add(new movie(title.get(i), rating.get(i), status.get(i)));
            }

        }
        else{
            mov.add(new movie("THE VENOM", "9.0", "PLAYING"));
            mov.add(new movie("HANTU KAK LIMAH", "9.0", "PLAYING"));
            mov.add(new movie("AVENGERS", "9.0", "PLAYING"));
            mov.add(new movie("KASANOVA", "9.0", "PLAYING"));
            mov.add(new movie("HALLOWEEN", "9.0", "PLAYING"));
            mov.add(new movie("BLACK PANTHER", "9.0", "PLAYING"));
            mov.add(new movie("CRAZY RICH ASIANS", "9.0", "PLAYING"));
            mov.add(new movie("THE PREDATOR", "9.0", "PLAYING"));
            mov.add(new movie("AQUAMAN", "9.0", "PLAYING"));
            mov.add(new movie("THE NUN", "9.0", "PLAYING"));
        }

        sliderView = (SliderMovie) findViewById(R.id.sliderView);
        iLinearLayout = (LinearLayout) findViewById(R.id.pagesContainer);



        recycler = findViewById(R.id.listview);
        movies1 = new MovieAdapter(this, mov);
        recycler.setAdapter(movies1);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.buttonadd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), movieAdapter.class);
                ArrayList<String> ititle = new ArrayList<>();
                ArrayList<String> irating = new ArrayList<>();
                ArrayList<String> iStatus = new ArrayList<>();

                for (int i = 0; i < mov.size(); i++){
                    ititle.add(mov.get(i).title);
                    irating.add(mov.get(i).rating);
                    iStatus.add(mov.get(i).status);
                }

                intent.putStringArrayListExtra("title", ititle);
                intent.putStringArrayListExtra("rating", irating);
                intent.putStringArrayListExtra("status", iStatus);
                startActivityForResult(intent, 0);
            }
        });

        sliderView.SetDurateScroll(800);
        setupSlider();

    }

    private void setupSlider() {



        fragments = getFragments();
        iAdapter = new AdapterList(getSupportFragmentManager(), fragments);
        sliderView.setAdapter(iAdapter);
        iIndicator = new SliderIndicator(this, iLinearLayout, sliderView, R.drawable.indicator_circle);
        iIndicator.setPageCount(images.size());
        iIndicator.show();
    }

    private List<Fragment> getFragments(){
        List<Fragment> fList = new ArrayList<Fragment>();
        for (int i = 0; i < images.size(); i++){
            fList.add(FragmentSlider.newInstance(images.get(i)));
        }
        return fList;
    }

}
