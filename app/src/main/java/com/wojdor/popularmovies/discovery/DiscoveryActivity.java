package com.wojdor.popularmovies.discovery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wojdor.popularmovies.R;
import com.wojdor.popularmovies.domain.Movie;

import java.util.ArrayList;
import java.util.List;

public class DiscoveryActivity extends AppCompatActivity implements DiscoveryContract.View {

    private final static int NUMBER_OF_COLUMNS = 2;

    private DiscoveryContract.Presenter presenter;
    private RecyclerView moviesRv;
    private DiscoveryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);
        presenter = new DiscoveryPresenter(this);
        setupMoviesRv();
    }

    private void setupMoviesRv() {
        moviesRv = findViewById(R.id.activity_discovery_movies_rv);
        adapter = new DiscoveryAdapter(getMockMovies());
        moviesRv.setLayoutManager(new GridLayoutManager(this, NUMBER_OF_COLUMNS));
        moviesRv.setAdapter(adapter);
    }

    private List<Movie> getMockMovies() {
        List<Movie> mockMovies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Movie mockMovie = new Movie();
            mockMovies.add(mockMovie);
        }
        return mockMovies;
    }
}
