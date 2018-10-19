package com.example.user.kakimovie.listmovie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.kakimovie.R;
import com.example.user.kakimovie.movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private final ArrayList<movie> movies;
    private Context mContext;
    private LayoutInflater mInflater;
    public MovieAdapter(Context context,ArrayList<movie> movie){
        this.movies = movie;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder,final int position) {

        movie terkini = movies.get(position);
        holder.title.setText(terkini.title);
        holder.rating.setText(terkini.rating);
        holder.status.setText(terkini.status);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView title, rating, status;
        RelativeLayout relativeLayout;
        final MovieAdapter mAdapter;
        Context context;
        public ViewHolder(View itemView, MovieAdapter adapter) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            rating = itemView.findViewById(R.id.rating);
            status = itemView.findViewById(R.id.status);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            movie elements = movies.get(position);
            mAdapter.notifyDataSetChanged();

        }
    }

}
