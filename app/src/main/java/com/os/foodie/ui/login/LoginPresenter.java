package com.os.foodie.ui.login;

import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.widget.Toast;

import com.os.foodie.R;
import com.os.foodie.data.DataManager;
import com.os.foodie.data.network.model.fblogin.FacebookLoginRequest;
import com.os.foodie.data.network.model.fblogin.FacebookLoginResponse;
import com.os.foodie.data.network.model.login.LoginRequest;
import com.os.foodie.data.network.model.login.LoginResponse;
import com.os.foodie.data.network.model.signup.customer.CustomerSignUpRequest;
import com.os.foodie.data.network.model.signup.customer.CustomerSignUpResponse;
import com.os.foodie.ui.base.BasePresenter;
import com.os.foodie.ui.welcome.WelcomeActivity;
import com.os.foodie.utils.AppConstants;
import com.os.foodie.utils.CommonUtils;
import com.os.foodie.utils.NetworkUtils;
import com.os.foodie.utils.ValidationUtils;

import java.net.URLDecoder;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V> implements LoginMvpPresenter<V> {

    public LoginPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void onLoginClick(String email, String password, String deviceId, String deviceType) {

        if (NetworkUtils.isNetworkConnected(getMvpView().getContext())) {

//            getMvpView().showError(-1, null);

            if (email == null || email.isEmpty()) {

                getMvpView().showError(1, getMvpView().getContext().getString(R.string.empty_email));
//                getMvpView().onError(R.string.empty_email);
                return;
            }
            if (!ValidationUtils.isEmailValid(email)) {

                getMvpView().showError(1, getMvpView().getContext().getString(R.string.invalid_email));
//                getMvpView().onError(R.string.invalid_email);
                return;
            }
            if (password == null || password.isEmpty()) {

                getMvpView().showError(2, getMvpView().getContext().getString(R.string.empty_password));
//                getMvpView().onError(R.string.empty_password);
                return;
            }
            if (password.length() < 6) {

                getMvpView().showError(2, getMvpView().getContext().getString(R.string.minimum_password));
//                getMvpView().onError(R.string.minimum_password);
                return;
            }

            String language = null;

            if (getDataManager().getLanguage().equalsIgnoreCase(AppConstants.LANG_EN)) {
                language = AppConstants.LANG_ENG;
            } else {
                language = AppConstants.LANG_AR;
            }

            getMvpView().showLoading();

            getCompositeDisposable().add(getDataManager()
                    .doLogin(new LoginRequest(email, password, deviceId, deviceType, language))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<LoginResponse>() {
                        @Override
                        public void accept(LoginResponse loginResponse) throws Exception {

                            getMvpView().hideLoading();

                            if (loginResponse.getResponse().getIsDeleted() != null && loginResponse.getResponse().getIsDeleted().equalsIgnoreCase("1")) {

//                                Locale locale = new Locale(AppConstants.LANG_EN);
//                                Locale.setDefault(locale);
//
//                                Configuration config = new Configuration();
//                                config.locale = locale;
//
//                                getMvpView().getContext().getResources().updateConfiguration(config, getMvpView().getContext().getResources().getDisplayMetrics());

                                Intent intent = new Intent(getMvpView().getContext(), WelcomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                getMvpView().getContext().startActivity(intent);

//                                getDataManager().setLanguage(AppConstants.LANG_EN);

                                setUserAsLoggedOut();

                                Toast.makeText(getMvpView().getContext(), loginResponse.getResponse().getMessage(), Toast.LENGTH_LONG).show();
                            }

                            if (loginResponse.getResponse().getStatus() == 0) {
                                getMvpView().onError(loginResponse.getResponse().getMessage());
                            } else {

                                getDataManager().setCurrentUserLoggedIn(true);
                                getDataManager().setFacebook(false);
                                getDataManager().setCurrentUserId(loginResponse.getResponse().getUserId());
                                getDataManager().setCurrentUserType(loginResponse.getResponse().getUserType());
//                                getDataManager().setCurrency(loginResponse.getResponse().getCurrency());
//
//                                URLDecoder.decode(restaurantProfileRequest.getCurrency(),"UTF-8")
                                getDataManager().setCurrency(loginResponse.getResponse().getCurrency());


//                                if (loginResponse.getResponse().getLanguage().equalsIgnoreCase(AppConstants.LANG_ENG)) {
//                                    getDataManager().setLanguage(AppConstants.LANG_EN);
//                                } else {
//                                    getDataManager().setLanguage(AppConstants.LANG_AR);
//                                }
//
//                                Locale locale = new Locale(getDataManager().getLanguage());
//                                Locale.setDefault(locale);
//
//                                Configuration config = new Configuration();
//                                config.locale = locale;
//
//                                getMvpView().getContext().getResources().updateConfiguration(config, getMvpView().getContext().getResources().getDisplayMetrics());

                                getDataManager().setNotificationStatus(loginResponse.getResponse().getIsNotification());

                                if (loginResponse.getResponse().getUserType().equals(AppConstants.CUSTOMER)) {
                                    getDataManager().setCurrentUserName(loginResponse.getResponse().getFirstName() + " " + loginResponse.getResponse().getLastName());
                                    getDataManager().setCustomerRestaurantId(loginResponse.getResponse().getRestaurantId());
                                } else {
                                    getDataManager().setCurrentUserName(loginResponse.getResponse().getRestaurantName());
                                    getDataManager().setRestaurantLogoURL(loginResponse.getResponse().getProfileImage());
                                    Log.d("getProfileImage", ">>" + loginResponse.getResponse().getProfileImage());
                                }

                                if (loginResponse.getResponse().getIsProfileSet().equals("1")) {
                                    getDataManager().setCurrentUserInfoInitialized(true);
                                } else {
                                    getDataManager().setCurrentUserInfoInitialized(false);
                                }

//                                getMvpView().onLoginSuccess(loginResponse.getResponse().getUserType());
                                decideNextActivity();
                            }

                            Log.d("Message", ">>" + loginResponse.getResponse().getMessage());

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                            getMvpView().hideLoading();
                            getMvpView().onError(R.string.api_default_error);
                            Log.d("Error", ">>Err" + throwable.getMessage());
                        }
                    }));
        } else {
            getMvpView().onError(R.string.connection_error);
        }
    }

    @Override
    public void onFacebookLoginClick(final String fbId, final String first_name, final String last_name, final String email, String deviceId, String deviceType) {

        String language = null;

        if (getDataManager().getLanguage().equalsIgnoreCase(AppConstants.LANG_EN)) {
            language = AppConstants.LANG_ENG;
        } else {
            language = AppConstants.LANG_AR;
        }

        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .doFacebookLogin(new FacebookLoginRequest(email, fbId, deviceId, deviceType, language))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FacebookLoginResponse>() {
                    @Override
                    public void accept(FacebookLoginResponse facebookLoginResponse) throws Exception {

                        getMvpView().hideLoading();

                        if (facebookLoginResponse.getResponse().getIsDeleted() != null && facebookLoginResponse.getResponse().getIsDeleted().equalsIgnoreCase("1")) {

//                            Locale locale = new Locale(AppConstants.LANG_EN);
//                            Locale.setDefault(locale);
//
//                            Configuration config = new Configuration();
//                            config.locale = locale;
//
//                            getMvpView().getContext().getResources().updateConfiguration(config, getMvpView().getContext().getResources().getDisplayMetrics());

                            Intent intent = new Intent(getMvpView().getContext(), WelcomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            getMvpView().getContext().startActivity(intent);

//                            getDataManager().setLanguage(AppConstants.LANG_EN);

                            setUserAsLoggedOut();

                            Toast.makeText(getMvpView().getContext(), facebookLoginResponse.getResponse().getMessage(), Toast.LENGTH_LONG).show();
                        }

                        if (facebookLoginResponse.getResponse().getStatus() == 1) {

                            getDataManager().setCurrentUserLoggedIn(true);
                            getDataManager().setFacebook(true);
                            getDataManager().setCurrentUserId(facebookLoginResponse.getResponse().getUserId());
                            getDataManager().setCurrentUserType(facebookLoginResponse.getResponse().getUserType());

//                            if (facebookLoginResponse.getResponse().getLanguage().equalsIgnoreCase(AppConstants.LANG_ENG)) {
//                                getDataManager().setLanguage(AppConstants.LANG_EN);
//                            } else {
//                                getDataManager().setLanguage(AppConstants.LANG_AR);
//                            }
//
//                            Locale locale = new Locale(getDataManager().getLanguage());
//                            Locale.setDefault(locale);
//
//                            Configuration config = new Configuration();
//                            config.locale = locale;
//
//                            getMvpView().getContext().getResources().updateConfiguration(config, getMvpView().getContext().getResources().getDisplayMetrics());


                            getDataManager().setNotificationStatus(facebookLoginResponse.getResponse().getIsNotification());

                            if (facebookLoginResponse.getResponse().getUserType().equals(AppConstants.CUSTOMER)) {

                                getDataManager().setCurrentUserName(facebookLoginResponse.getResponse().getFirstName() + " " + facebookLoginResponse.getResponse().getLastName());
                                getDataManager().setCustomerRestaurantId(facebookLoginResponse.getResponse().getRestaurantId());
                            } else {

                                if (facebookLoginResponse.getResponse().getCurrency() != null) {
                                    getDataManager().setCurrency(facebookLoginResponse.getResponse().getCurrency());
//                                    getDataManager().setCurrency(URLDecoder.decode(facebookLoginResponse.getResponse().getCurrency(), "UTF-8"));
                                }

                                if (getDataManager().getLanguage().equalsIgnoreCase(AppConstants.LANG_AR)) {
                                    getDataManager().setCurrentUserName(facebookLoginResponse.getResponse().getRestaurantNameArabic());
                                } else {
                                    getDataManager().setCurrentUserName(facebookLoginResponse.getResponse().getRestaurantName());
                                }

//                                getDataManager().setCurrentUserName(facebookLoginResponse.getResponse().getRestaurantName());
                                getDataManager().setRestaurantLogoURL(facebookLoginResponse.getResponse().getProfileImage());
                                Log.d("getProfileImage", ">>" + getDataManager().getRestaurantLogoURL());
                            }

                            if (facebookLoginResponse.getResponse().getIsProfileSet().equals("1")) {
                                getDataManager().setCurrentUserInfoInitialized(true);
                            } else {
                                getDataManager().setCurrentUserInfoInitialized(false);
                            }

                            decideNextActivity();

                        } else {

                            getMvpView().setFacebookDetails(fbId, first_name, last_name, email);
//
//                            getMvpView().onError(facebookLoginResponse.getResponse().getMessage());
                        }

                        Log.d("Message", ">>" + facebookLoginResponse.getResponse().getMessage());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        getMvpView().hideLoading();
                        getMvpView().onError(R.string.api_default_error);
                        Log.d("Error", ">>Err" + throwable.getMessage());
                    }
                }));
    }

    @Override
    public void setError(int resId) {
        getMvpView().onError(resId);
    }

    @Override
    public void setError(String message) {
        getMvpView().onErrorLong(message);
    }

    private void decideNextActivity() {

        Log.d("isCurrentUserLoggedIn", "true");

        if (getDataManager().isCurrentUserInfoInitialized()) {

            Log.d("isCurrentUserInfoInit", "true");

            if (getDataManager().getCurrentUserType().equalsIgnoreCase(AppConstants.CUSTOMER)) {

                Log.d("openCustomerHome", "CUSTOMER");

                getMvpView().openCustomerHomeActivity();

            } else {

                Log.d("RestaurantHome", "Res");

                getMvpView().openRestaurantHomeActivity();
            }

        } else {

            Log.d("isCurrentUserInfoInit", "false");

            if (getDataManager().getCurrentUserType().equalsIgnoreCase(AppConstants.CUSTOMER)) {

                Log.d("openLocationInfo", "CUSTOMER");

                getMvpView().openLocationInfoActivity();

            } else {

                Log.d("SetupRestaurantProfile", "Res");

                getMvpView().openSetupRestaurantProfileActivity();
            }
        }
    }

    @Override
    public String getDeviceId() {
        return getDataManager().getDeviceId();
    }

    @Override
    public void dispose() {

        getMvpView().hideLoading();

        getCompositeDisposable().dispose();
    }
}