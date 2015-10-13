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
public class CachedListAdapter extends ArrayAdapter {

    private final Context context;
    private final Query[] queries;

    public CachedListAdapter(Context context, Query[] queries) {
        super(context, R.layout.list_item, queries);
        this.context = context;
        this.queries = queries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.title);
        textView.setText(queries[position].getSearchstring());
        return rowView;
    }

}
