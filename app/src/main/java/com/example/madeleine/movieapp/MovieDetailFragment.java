package com.example.madeleine.movieapp;


import android.support.v4.app.Fragment;
import android.view.Display;
import android.webkit.WebView;
import android.widget.TextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;


/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_detail)
public class MovieDetailFragment extends Fragment {

    @FragmentArg
    String movieId;

    @ViewById
    TextView title;

    @ViewById
    TextView year;

    @ViewById
    TextView runtime;

    @ViewById
    WebView webView;

    @RestService
    MovieDBClient movieDBClient;


    public MovieDetailFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();

        String movieId = getArguments().getString("movieId");

        MovieApplication application = (MovieApplication) getActivity().getApplication();
        MovieInfoDao movieInfoDao = application.getDaoSession().getMovieInfoDao();
        MovieInfo movieInfo = movieInfoDao.queryBuilder().where(MovieInfoDao.Properties.ImdbID.eq(movieId)).unique();
        MovieDetail movieDetail = movieInfo.getMovieDetail();
        if(movieDetail != null) {
            Movie movie = new Movie();
            movie.setTitle(movieInfo.getTitle());
            movie.setYear(movieDetail.getYear());
            movie.setRuntime(movieDetail.getRuntime());
            movie.setPoster(movieDetail.getPoster());
            setResults(movie);
        }
        else {
            searchAsync(movieId);
        }
    }

    @Background
    void searchAsync(String id) {
        Movie movie = movieDBClient.getMovie(id);
        setResults(movie);
        persistMovieDetails(movie);
    }

    void persistMovieDetails(Movie movie){
        MovieApplication application = (MovieApplication) getActivity().getApplication();
        MovieInfoDao movieInfoDao = application.getDaoSession().getMovieInfoDao();
        MovieDetailDao movieDetailDao = application.getDaoSession().getMovieDetailDao();
        MovieInfo movieInfo = movieInfoDao.queryBuilder().where(MovieInfoDao.Properties.ImdbID.eq(movie.getId())).unique();

        MovieDetail detail = new MovieDetail();
        detail.setActors(movie.getActors());
        detail.setAwards(movie.getAwards());
        detail.setCountry(movie.getCountry());
        detail.setDirector(movie.getDirector());
        detail.setGenre(movie.getGenre());
        detail.setImdbRating(movie.getImdbRating());
        detail.setImdbVotes(movie.getImdbVotes());
        detail.setLanguage(movie.getLanguage());
        detail.setMetascore(movie.getMetascore());
        detail.setPlot(movie.getPlot());
        detail.setPoster(movie.getPoster());
        detail.setRated(movie.getRated());
        detail.setReleased(movie.getReleased());
        detail.setResponse(movie.getResponse());
        detail.setRuntime(movie.getRuntime());
        detail.setType(movie.getType());
        detail.setWriter(movie.getWriter());
        detail.setYear(movie.getYear());

        long id = movieDetailDao.insert(detail);
        movieInfo.setDetailId(id);
        movieInfoDao.update(movieInfo);
    }

    @UiThread
    public void setResults(Movie result) {

        title.setText(result.getTitle());
        year.setText(result.getYear());
        runtime.setText(result.getRuntime());

        if(result.getPoster() != null && result.getPoster() != "") {
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            int width = display.getWidth();

            String data = "<html><head><title>Poster</title><meta name=\"viewport\"\"content=\"width=" + width + ", initial-scale=0.65 \" /></head>";
            data = data + "<body><center><img width=\"100%\" src=\"" + result.getPoster() + "\" /></center></body></html>";
            try {
                webView.loadData(URLEncoder.encode(data,"utf-8").replaceAll("\\+"," "), "text/html", "utf-8");

            } catch (UnsupportedEncodingException e) {
                webView.loadData("<html><head><title>Example</title><meta name=\"viewport\"\"content=\"width=" + width + ", initial-scale=0.65 \" /></head>", "text/html", "utf-8");
            }
        }
    }
}
