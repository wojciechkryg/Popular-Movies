package com.wojdor.popularmovies.application.details;

import android.os.Bundle;

import com.wojdor.popularmovies.R;
import com.wojdor.popularmovies.application.base.BaseActivity;

public class DetailsActivity extends BaseActivity implements DetailsContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }
}
