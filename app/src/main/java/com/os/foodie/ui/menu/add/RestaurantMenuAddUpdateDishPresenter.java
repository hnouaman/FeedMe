package com.os.foodie.ui.menu.add;

import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.widget.Toast;

import com.os.foodie.R;
import com.os.foodie.data.DataManager;
import com.os.foodie.data.network.model.coursetype.list.Course;
import com.os.foodie.data.network.model.coursetype.list.GetCourseTypeResponse;
import com.os.foodie.data.network.model.menu.add.AddMenuItemRequest;
import com.os.foodie.data.network.model.menu.add.AddMenuItemResponse;
import com.os.foodie.ui.base.BasePresenter;
import com.os.foodie.ui.welcome.WelcomeActivity;
import com.os.foodie.utils.AppConstants;
import com.os.foodie.utils.NetworkUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RestaurantMenuAddUpdateDishPresenter<V extends RestaurantMenuAddUpdateDishMvpView> extends BasePresenter<V> implements RestaurantMenuAddUpdateDishMvpPresenter<V> {

    public RestaurantMenuAddUpdateDishPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void getCourseTypeList() {
        Log.d("Error", ">>Err");

        if (NetworkUtils.isNetworkConnected(getMvpView().getContext())) {

            getMvpView().showLoading();

            getCompositeDisposable().add(getDataManager()
                    .getCourseTypeList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<GetCourseTypeResponse>() {
                        @Override
                        public void accept(GetCourseTypeResponse courseTypeResponse) throws Exception {

                            getMvpView().hideLoading();

                            if (courseTypeResponse.getResponse().getStatus() == 1) {

                                if (courseTypeResponse.getResponse().getData().get(0).getCourses() != null) {
                                    Log.d("getCourses", ">>" + ((ArrayList<Course>) courseTypeResponse.getResponse().getData().get(0).getCourses()).size());
                                    getMvpView().onCourseTypeReceived((ArrayList<Course>) courseTypeResponse.getResponse().getData().get(0).getCourses());
                                } else {
                                    Log.d("Error", ">>Err");
                                    getMvpView().onError("Course not found");
                                    getMvpView().onCourseTypeReceived(new ArrayList<Course>());
                                }

                            } else {
                                getMvpView().onError(courseTypeResponse.getResponse().getMessage());
                                getMvpView().onCourseTypeReceived(new ArrayList<Course>());
                            }

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
    public void addRestaurantMenuItem(String dishId, String courseId, String itemName, String price, String description, String vegNonVeg, HashMap<String, File> menuImageFile, String itemNameArabic) {

        if (NetworkUtils.isNetworkConnected(getMvpView().getContext())) {

            if (courseId == null || courseId.isEmpty()) {
                getMvpView().onError(R.string.empty_menu_item_course_type);
                return;
            }
            if (itemName == null || itemName.isEmpty()) {
                getMvpView().onError(R.string.empty_menu_item_name_english);
                return;
            }
            if (itemNameArabic == null || itemNameArabic.isEmpty()) {
                getMvpView().onError(R.string.empty_menu_item_name_arabic);
                return;
            }
            if (price == null || price.isEmpty()) {
                getMvpView().onError(R.string.empty_menu_item_price);
                return;
            }
            if (description == null || description.isEmpty()) {
                getMvpView().onError(R.string.empty_menu_item_description);
                return;
            }

            if ((dishId == null || dishId.isEmpty()) && (menuImageFile == null || menuImageFile.isEmpty())) {
                getMvpView().onError(R.string.mandatory_dish_image);
                return;
            }

            getMvpView().showLoading();

            getCompositeDisposable().add(getDataManager()
                    .addRestaurantMenuItem(new AddMenuItemRequest(getDataManager().getCurrentUserId(), dishId, courseId, vegNonVeg, itemName, description, price, "1", itemNameArabic), menuImageFile)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<AddMenuItemResponse>() {
                        @Override
                        public void accept(AddMenuItemResponse addMenuItemResponse) throws Exception {

                            getMvpView().hideLoading();

                            if (addMenuItemResponse.getResponse().getIsDeleted() != null && addMenuItemResponse.getResponse().getIsDeleted().equalsIgnoreCase("1")) {

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

                                Toast.makeText(getMvpView().getContext(), addMenuItemResponse.getResponse().getMessage(), Toast.LENGTH_LONG).show();
                            }

                            Log.d("addMenuItemResponse", "onSuccess>>" + addMenuItemResponse.getResponse().getDishId().toString());

                            if (addMenuItemResponse.getResponse().getStatus() == 1) {

                                Log.d("Message", "onSuccess>>" + addMenuItemResponse.getResponse().getMessage());
                                getMvpView().onMenuItemAdded();

                            } else {
                                getMvpView().onError(addMenuItemResponse.getResponse().getMessage());
                            }

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                            getMvpView().hideLoading();
                            getMvpView().onError(R.string.api_default_error);
//                            Log.d("Error", ">>Err" + throwable.getMessage());
                        }
                    }));
        } else {
            getMvpView().onError(R.string.connection_error);
        }
    }

    @Override
    public void dispose() {

        getMvpView().hideLoading();

        getCompositeDisposable().dispose();
    }
}