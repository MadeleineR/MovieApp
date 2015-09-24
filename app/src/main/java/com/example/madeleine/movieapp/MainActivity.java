package com.example.madeleine.movieapp;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements MovieListFragment.OnMovieSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_content, new MovieListFragment()).commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMovieSelected(String movieId) {
        FragmentManager fm = getSupportFragmentManager();

        MovieDetailFragment detailFragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putString("movieId", movieId);
        detailFragment.setArguments(args);

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_content, detailFragment);

        ft.addToBackStack(null);
        ft.commit();
    }

}
