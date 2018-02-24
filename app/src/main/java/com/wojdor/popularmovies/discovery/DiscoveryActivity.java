package com.wojdor.popularmovies.discovery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wojdor.popularmovies.R;

public class DiscoveryActivity extends AppCompatActivity implements DiscoveryContract.View {

    private DiscoveryContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery);
        presenter = new DiscoveryPresenter(this);
    }
}
