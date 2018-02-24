package com.wojdor.popularmovies.discovery;

public class DiscoveryPresenter implements DiscoveryContract.Presenter {

    private final DiscoveryContract.View discoveryView;

    public DiscoveryPresenter(DiscoveryContract.View discoveryView) {
        this.discoveryView = discoveryView;
    }

    @Override
    public void start() {
    }
}
