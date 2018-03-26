package com.wojdor.popularmovies.application.base;

import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    public void setTitle(int titleResId) {
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setTitle(titleResId);
    }
}
