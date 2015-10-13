package com.example.madeleine.movieapp;

import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import org.androidannotations.annotations.EFragment;

import java.util.List;

/**
 * Created by Christoph on 13.10.2015.
 */
@EFragment(R.layout.fragment_cache)
public class QueryCacheFragment extends ListFragment {

    List<Query> queries;

    @Override
    public void onResume(){
        super.onResume();
        MovieApplication application = (MovieApplication) getActivity().getApplication();
        QueryDao queryDao = application.getDaoSession().getQueryDao();
        queries = queryDao.queryBuilder().list();

        CachedListAdapter adapter = new CachedListAdapter(this.getContext(), queries.toArray(new Query[queries.size()]));
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        MovieListFragment listFragment = MovieListFragment_.builder().queryId(queries.get(position).getId()).build();
        ((MainActivity) getActivity()).replaceFragment(listFragment);
    }
}
