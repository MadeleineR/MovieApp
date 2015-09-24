package com.example.madeleine.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Madeleine on 23.09.2015.
 */
public class MovieListAdapter extends ArrayAdapter {

    private final Context context;
    private final Movie[] movies;

    public MovieListAdapter(Context context, Movie[] movies) {
        super(context, R.layout.list_item, movies);
        this.context = context;
        this.movies = movies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.title);
        textView.setText(movies[position].getTitle());
        return rowView;
    }

}
