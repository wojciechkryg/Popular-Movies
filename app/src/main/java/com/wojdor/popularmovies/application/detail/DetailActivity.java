package com.wojdor.popularmovies.application.detail;

import android.os.Bundle;

import com.wojdor.popularmovies.R;
import com.wojdor.popularmovies.application.base.BaseActivity;

public class DetailActivity extends BaseActivity implements DetailContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }
}
