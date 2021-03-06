package com.os.foodie.ui.welcome;

import com.os.foodie.data.DataManager;
import com.os.foodie.ui.base.BasePresenter;
import com.os.foodie.ui.splash.SplashMvpPresenter;
import com.os.foodie.ui.splash.SplashMvpView;
import com.os.foodie.utils.AppConstants;

import io.reactivex.disposables.CompositeDisposable;

public class WelcomePresenter<V extends WelcomeMvpView> extends BasePresenter<V> implements WelcomeMvpPresenter<V> {

    public WelcomePresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    public void changeLanguage(String languageCode){
        getDataManager().setLanguage(languageCode);
    }

    public String getLanguage(){
        return getDataManager().getLanguage();
    }

    @Override
    public void dispose() {

        getMvpView().hideLoading();

        getCompositeDisposable().dispose();
    }
}