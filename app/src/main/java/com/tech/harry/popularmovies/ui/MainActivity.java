package com.tech.harry.popularmovies.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.tech.harry.popularmovies.Data.Remote.ApiUtils;
import com.tech.harry.popularmovies.Data.Remote.MovieRestService;
import com.tech.harry.popularmovies.Model.PopularMovies;
import com.tech.harry.popularmovies.Model.Result;
import com.tech.harry.popularmovies.R;
import com.tech.harry.popularmovies.Utils.Constant;
import com.tech.harry.popularmovies.ui.Adapters.MovieListAdapter;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private MovieListAdapter movieListAdapter;
    private RecyclerView moviesListRecycler;
    private MovieRestService movieRestService;
    private ProgressDialog mProgressDialog;

    private String mFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        movieRestService = ApiUtils.getMovieRestService();

        movieListAdapter = new MovieListAdapter(new ArrayList<Result>(0), this, this);

        moviesListRecycler = (RecyclerView) findViewById(R.id.movie_list);
        moviesListRecycler.setLayoutManager(new GridLayoutManager(this,2));

        moviesListRecycler.setAdapter(movieListAdapter);
        moviesListRecycler.setHasFixedSize(true);


        mFilter = "popular";
        loadMovies(mFilter);


    }

    private void loadMovies(String filter) {
        showProgressBar(true);

        movieRestService.getMovies(filter, getString(R.string.api_key))
                .enqueue(new Callback<PopularMovies>() {
                    @Override
                    public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {
                        if(response.isSuccessful()){

                            movieListAdapter.updateAdapter(response.body().getResults());
                            showProgressBar(false);
                        }

                    }

                    @Override
                    public void onFailure(Call<PopularMovies> call, Throwable t) {
                        Log.d("MainActivity", "error loading from API");
                    }
                });

    }

    private void showProgressBar(boolean b) {
        if (b){
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.show();
            moviesListRecycler.setVisibility(View.GONE);
        }else{
            if(mProgressDialog != null){
                mProgressDialog.hide();
                mProgressDialog.cancel();
            }
            moviesListRecycler.setVisibility(View.VISIBLE);
        }
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
        if (id == R.id.action_popular){
            if(!mFilter.equals("popular")){
                mFilter = "popular";
                loadMovies("popular");
            }
            return true;
        }
        if (id == R.id.action_top){
            if(!mFilter.equals("top_rated")){
                mFilter = "top_tated";
                loadMovies("top_rated");
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(Object mMovie, View mView) {
        Result Movie = (Result) mMovie;
        Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
        intent.putExtra(Constant.title, Movie.getTitle());
        intent.putExtra(Constant.synopsis, Movie.getOverview());
        intent.putExtra(Constant.rating, Movie.getVoteAverage()+"");
        intent.putExtra(Constant.release_date, Movie.parseDate());
        intent.putExtra(Constant.poster, Movie.getMainPosterPath());
        startActivity(intent);
        Toast.makeText(this, ((Result) mMovie).getTitle(), Toast.LENGTH_SHORT).show();
    }
}
