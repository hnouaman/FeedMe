package com.os.foodie.data.network;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.os.foodie.data.network.model.account.edit.customer.EditCustomerAccountDetailResponse;
import com.os.foodie.data.network.model.account.edit.customer.EditCustomerAccountRequest;
import com.os.foodie.data.network.model.account.edit.restaurant.EditRestaurantAccountRequest;
import com.os.foodie.data.network.model.account.edit.restaurant.EditRestaurantAccountResponse;
import com.os.foodie.data.network.model.account.GetAccountDetailRequest;
import com.os.foodie.data.network.model.account.GetAccountDetailResponse;
import com.os.foodie.data.network.model.cart.add.AddToCartRequest;
import com.os.foodie.data.network.model.cart.add.AddToCartResponse;
import com.os.foodie.data.network.model.cart.clear.ClearCartRequest;
import com.os.foodie.data.network.model.cart.clear.ClearCartResponse;
import com.os.foodie.data.network.model.cart.list.GetCartListRequest;
import com.os.foodie.data.network.model.cart.list.GetCartListResponse;
import com.os.foodie.data.network.model.cart.remove.RemoveFromCartRequest;
import com.os.foodie.data.network.model.cart.remove.RemoveFromCartResponse;
import com.os.foodie.data.network.model.cart.update.UpdateCartRequest;
import com.os.foodie.data.network.model.cart.update.UpdateCartResponse;
import com.os.foodie.data.network.model.cart.view.ViewCartRequest;
import com.os.foodie.data.network.model.cart.view.ViewCartResponse;
import com.os.foodie.data.network.model.changelanguage.ChangeLanguageRequest;
import com.os.foodie.data.network.model.changelanguage.ChangeLanguageResponse;
import com.os.foodie.data.network.model.changeorderstatus.ChangeOrderStatusResponse;
import com.os.foodie.data.network.model.changepassword.ChangePasswordRequest;
import com.os.foodie.data.network.model.changepassword.ChangePasswordResponse;
import com.os.foodie.data.network.model.checkout.CheckoutRequest;
import com.os.foodie.data.network.model.checkout.CheckoutResponse;
import com.os.foodie.data.network.model.citycountrylist.CityCountryListRequest;
import com.os.foodie.data.network.model.citycountrylist.CityCountryListResponse;
import com.os.foodie.data.network.model.coursetype.add.AddCourseTypeRequest;
import com.os.foodie.data.network.model.coursetype.add.AddCourseTypeResponse;
import com.os.foodie.data.network.model.coursetype.list.GetCourseTypeResponse;
import com.os.foodie.data.network.model.cuisinetype.add.AddCuisineTypeRequest;
import com.os.foodie.data.network.model.cuisinetype.add.AddCuisineTypeResponse;
import com.os.foodie.data.network.model.cuisinetype.list.CuisineTypeResponse;
import com.os.foodie.data.network.model.deliveryaddress.add.AddDeliveryAddressRequest;
import com.os.foodie.data.network.model.deliveryaddress.add.AddDeliveryAddressResponse;
import com.os.foodie.data.network.model.deliveryaddress.delete.DeleteAddressRequest;
import com.os.foodie.data.network.model.deliveryaddress.delete.DeleteAddressResponse;
import com.os.foodie.data.network.model.deliveryaddress.getall.GetAllAddressRequest;
import com.os.foodie.data.network.model.deliveryaddress.getall.GetAllAddressResponse;
import com.os.foodie.data.network.model.deliveryaddress.update.UpdateAddressRequest;
import com.os.foodie.data.network.model.deliveryaddress.update.UpdateAddressResponse;
import com.os.foodie.data.network.model.details.CustomerRestaurantDetailsRequest;
import com.os.foodie.data.network.model.details.CustomerRestaurantDetailsResponse;
import com.os.foodie.data.network.model.discount.add.AddDiscountRequest;
import com.os.foodie.data.network.model.discount.add.AddDiscountResponse;
import com.os.foodie.data.network.model.discount.dishlist.DishListRequest;
import com.os.foodie.data.network.model.discount.dishlist.DishListResponse;
import com.os.foodie.data.network.model.discount.list.DeleteDiscountRequest;
import com.os.foodie.data.network.model.discount.list.DiscountListResponse;
import com.os.foodie.data.network.model.earning.GetEarningRequest;
import com.os.foodie.data.network.model.earning.GetEarningResponse;
import com.os.foodie.data.network.model.forgotpassword.ForgotPasswordRequest;
import com.os.foodie.data.network.model.forgotpassword.ForgotPasswordResponse;
import com.os.foodie.data.network.model.home.customer.GetRestaurantListRequest;
import com.os.foodie.data.network.model.home.customer.GetRestaurantListResponse;
import com.os.foodie.data.network.model.like.LikeDislikeRequest;
import com.os.foodie.data.network.model.like.LikeDislikeResponse;
import com.os.foodie.data.network.model.locationinfo.city.CityListRequest;
import com.os.foodie.data.network.model.locationinfo.city.CityListResponse;
import com.os.foodie.data.network.model.locationinfo.country.CountryListResponse;
import com.os.foodie.data.network.model.locationinfo.get.GetUserLocationDetailRequest;
import com.os.foodie.data.network.model.locationinfo.get.GetUserLocationDetailResponse;
import com.os.foodie.data.network.model.locationinfo.set.SetUserLocationRequest;
import com.os.foodie.data.network.model.locationinfo.set.SetUserLocationResponse;
import com.os.foodie.data.network.model.login.LoginRequest;
import com.os.foodie.data.network.model.login.LoginResponse;
import com.os.foodie.data.network.model.logout.LogoutRequest;
import com.os.foodie.data.network.model.logout.LogoutResponse;
import com.os.foodie.data.network.model.menu.add.AddMenuItemRequest;
import com.os.foodie.data.network.model.menu.add.AddMenuItemResponse;
import com.os.foodie.data.network.model.menu.delete.DeleteMenuItemRequest;
import com.os.foodie.data.network.model.menu.delete.DeleteMenuItemResponse;
import com.os.foodie.data.network.model.menu.show.restaurant.GetRestaurantMenuRequest;
import com.os.foodie.data.network.model.menu.show.restaurant.GetRestaurantMenuResponse;
import com.os.foodie.data.network.model.menu.status.StatusMenuItemRequest;
import com.os.foodie.data.network.model.menu.status.StatusMenuItemResponse;
import com.os.foodie.data.network.model.merchantdetails.get.GetMerchantDetailRequest;
import com.os.foodie.data.network.model.merchantdetails.get.GetMerchantDetailResponse;
import com.os.foodie.data.network.model.merchantdetails.set.SetMerchantDetailRequest;
import com.os.foodie.data.network.model.merchantdetails.set.SetMerchantDetailResponse;
import com.os.foodie.data.network.model.notification.NotificationListResponse;
import com.os.foodie.data.network.model.notification.SetNotificationResponse;
import com.os.foodie.data.network.model.order.customer.history.CustomerOrderHistoryRequest;
import com.os.foodie.data.network.model.order.restaurant.detail.OrderHistoryDetail;
import com.os.foodie.data.network.model.orderlist.acceptreject.AcceptRejectOrderRequest;
import com.os.foodie.data.network.model.orderlist.acceptreject.AcceptRejectOrderResponse;
import com.os.foodie.data.network.model.orderlist.show.GetOrderListRequest;
import com.os.foodie.data.network.model.orderlist.show.GetOrderListResponse;
import com.os.foodie.data.network.model.otp.resend.ResendOtpRequest;
import com.os.foodie.data.network.model.otp.resend.ResendOtpResponse;
import com.os.foodie.data.network.model.otp.verify.OtpVerificationRequest;
import com.os.foodie.data.network.model.otp.verify.OtpVerificationResponse;
import com.os.foodie.data.network.model.payment.addcard.AddPaymentCardRequest;
import com.os.foodie.data.network.model.payment.addcard.AddPaymentCardResponse;
import com.os.foodie.data.network.model.payment.delete.DeletePaymentCardRequest;
import com.os.foodie.data.network.model.payment.delete.DeletePaymentCardResponse;
import com.os.foodie.data.network.model.payment.getall.GetAllPaymentCardRequest;
import com.os.foodie.data.network.model.payment.getall.GetAllPaymentCardResponse;
import com.os.foodie.data.network.model.reorder.ReorderRequest;
import com.os.foodie.data.network.model.reorder.ReorderResponse;
import com.os.foodie.data.network.model.restaurantreview.RestaurantReviewRequest;
import com.os.foodie.data.network.model.restaurantreview.RestaurantReviewResponse;
import com.os.foodie.data.network.model.reviews.GetReviewsRequest;
import com.os.foodie.data.network.model.reviews.GetReviewsResponse;
import com.os.foodie.data.network.model.setupprofile.restaurant.SetupRestaurantProfileRequest;
import com.os.foodie.data.network.model.setupprofile.restaurant.SetupRestaurantProfileResponse;
import com.os.foodie.data.network.model.showrestaurantprofile.RestaurantProfileResponse;
import com.os.foodie.data.network.model.signup.customer.CustomerSignUpRequest;
import com.os.foodie.data.network.model.signup.customer.CustomerSignUpResponse;
import com.os.foodie.data.network.model.fblogin.FacebookLoginRequest;
import com.os.foodie.data.network.model.fblogin.FacebookLoginResponse;
import com.os.foodie.data.network.model.signup.restaurant.RestaurantSignUpRequest;
import com.os.foodie.data.network.model.signup.restaurant.RestaurantSignUpResponse;
import com.os.foodie.data.network.model.staticpage.StaticPageRequest;
import com.os.foodie.data.network.model.staticpage.StaticPageResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class AppApiHelpter implements ApiHelper {

    @Override
    public Observable<CustomerSignUpResponse> doCustomerSignUp(CustomerSignUpRequest customerSignUpRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(customerSignUpRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.CUSTOMER_SIGNUP)
//                .addBodyParameter(customerSignUpRequest)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(CustomerSignUpResponse.class);
    }

    @Override
    public Observable<RestaurantSignUpResponse> doRestaurantSignUp(RestaurantSignUpRequest restaurantSignUpRequest, HashMap<String, File> fileMap) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(restaurantSignUpRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.upload(ApiConstants.BASE_URL + ApiConstants.RESTAURANT_SIGNUP)
                .addMultipartFile(fileMap)
                .addMultipartParameter("data", jsonObject.toString())
                .build()
                .getObjectObservable(RestaurantSignUpResponse.class);
    }

    @Override
    public Observable<FacebookLoginResponse> doFacebookLogin(FacebookLoginRequest facebookLoginRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(facebookLoginRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.FB_LOGIN)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(FacebookLoginResponse.class);
    }

    @Override
    public Observable<LoginResponse> doLogin(LoginRequest loginRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(loginRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.LOGIN)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public Observable<OtpVerificationResponse> verifyOTP(OtpVerificationRequest otpVerificationRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(otpVerificationRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.OTP_VERIFICATION)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(OtpVerificationResponse.class);
    }

    @Override
    public Observable<ResendOtpResponse> resendOTP(ResendOtpRequest resendOtpRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(resendOtpRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.OTP_RESEND)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(ResendOtpResponse.class);
    }

    @Override
    public Observable<ForgotPasswordResponse> resetPassword(ForgotPasswordRequest forgotPasswordRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(forgotPasswordRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.FORGOT_PASSWORD)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(ForgotPasswordResponse.class);
    }

    @Override
    public Observable<CountryListResponse> getCountryList() {

        return Rx2AndroidNetworking.get(ApiConstants.BASE_URL + ApiConstants.COUNTRY_LIST)
                .build()
                .getObjectObservable(CountryListResponse.class);
    }

    @Override
    public Observable<CityListResponse> getCityList(CityListRequest cityListRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(cityListRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.CITY_LIST)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(CityListResponse.class);
    }

    @Override
    public Observable<CityCountryListResponse> getCityCountryList(CityCountryListRequest cityCountryListRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(cityCountryListRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.CITY_COUNTRY_LIST)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(CityCountryListResponse.class);
    }

    @Override
    public Observable<GetUserLocationDetailResponse> getUserLocationDetail(GetUserLocationDetailRequest userLocationDetailRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(userLocationDetailRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.GET_USER_LOCATION)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(GetUserLocationDetailResponse.class);
    }

    @Override
    public Observable<SetUserLocationResponse> setUserLocationInfo(SetUserLocationRequest setUserLocationRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(setUserLocationRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.SET_USER_LOCATION)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(SetUserLocationResponse.class);
    }

    @Override
    public Observable<CuisineTypeResponse> getCuisineTypeList() {

        return Rx2AndroidNetworking.get(ApiConstants.BASE_URL + ApiConstants.CUISINE_TYPE_LIST)
                .build()
                .getObjectObservable(CuisineTypeResponse.class);
    }

    @Override
    public Observable<AddCuisineTypeResponse> addCuisineType(AddCuisineTypeRequest addCuisineTypeRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(addCuisineTypeRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.ADD_CUISINE_TYPE)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(AddCuisineTypeResponse.class);
    }

    @Override
    public Observable<SetupRestaurantProfileResponse> setRestaurantProfile(SetupRestaurantProfileRequest restaurantProfileRequest, HashMap<String, File> fileMap) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(restaurantProfileRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.upload(ApiConstants.BASE_URL + ApiConstants.SET_RESTAURANT_PROFILE)
                .addMultipartFile(fileMap)
                .addMultipartParameter("data", jsonObject.toString())
                .build()
                .getObjectObservable(SetupRestaurantProfileResponse.class);
    }

    @Override
    public Observable<GetRestaurantMenuResponse> getRestaurantMenuList(GetRestaurantMenuRequest restaurantMenuRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(restaurantMenuRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.GET_RESTAURANT_MENU)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(GetRestaurantMenuResponse.class);
    }

    @Override
    public Observable<GetCourseTypeResponse> getCourseTypeList() {

        return Rx2AndroidNetworking.get(ApiConstants.BASE_URL + ApiConstants.COURSE_TYPE_LIST)
                .build()
                .getObjectObservable(GetCourseTypeResponse.class);
    }

    @Override
    public Observable<AddCourseTypeResponse> addCourseType(AddCourseTypeRequest addCourseTypeRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(addCourseTypeRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.ADD_COURSE_TYPE)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(AddCourseTypeResponse.class);
    }

    @Override
    public Observable<List<Address>> getGeocoderLocationAddress(final Context context, final LatLng latLng) {

        Observable<List<Address>> observable = Observable.fromCallable(new Callable<List<Address>>() {
            @Override
            public List<Address> call() throws Exception {

//                ArrayList<String> addressArrayList = new ArrayList<>();
//                String fullAddress = "";
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                return geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
//                    List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//
//                    if (addressList != null && addressList.size() > 0) {
//
//                        Address address = addressList.get(0);
//                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
//                            if (i <= 1)
//                                fullAddress = fullAddress + address.getAddressLine(i) + " ";
//                            else
//                                break;
//                        }
//
////                        String postalCode = address.getPostalCode();
////                        String city = address.getLocality();
////                        String state = address.getAdminArea();
////                        String country = address.getCountryName();
////
////                        Log.d("postalCode", ">>" + postalCode);
////                        Log.d("city", ">>" + city);
////                        Log.d("state", ">>" + state);
////                        Log.d("country", ">>" + country);
//                        Log.d("address_str", ">>" + fullAddress);
//                    }

//                } catch (IOException e) {
//                    Log.d("IOException", ">>" + e.getMessage());
//                }
            }
        });

        return observable;
    }

    @Override
    public Observable<GetRestaurantListResponse> getRestaurantList(GetRestaurantListRequest getRestaurantListRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(getRestaurantListRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.GET_RESTAURANT_LIST)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(GetRestaurantListResponse.class);
    }

    @Override
    public Observable<CustomerRestaurantDetailsResponse> getRestaurantDetails(CustomerRestaurantDetailsRequest restaurantDetailsRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(restaurantDetailsRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.GET_RESTAURANT_DETAILS)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(CustomerRestaurantDetailsResponse.class);
    }

    @Override
    public Observable<LikeDislikeResponse> doLikeDislike(LikeDislikeRequest likeDislikeRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(likeDislikeRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.RESTAURANT_LIKE)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(LikeDislikeResponse.class);
    }

    @Override
    public Observable<ChangePasswordResponse> changePassword(ChangePasswordRequest changePasswordRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(changePasswordRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.CHANGE_PASSWORD)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(ChangePasswordResponse.class);
    }

    @Override
//    public Observable<AddMenuItemResponse> addRestaurantMenuItem(AddMenuItemRequest addMenuItemRequest) {
    public Observable<AddMenuItemResponse> addRestaurantMenuItem(AddMenuItemRequest addMenuItemRequest, HashMap<String, File> fileMap) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(addMenuItemRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

//        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.ADD_RESTAURANT_MENU_ITEM)
//                .addJSONObjectBody(jsonObject)
//                .build()
//                .getObjectObservable(AddMenuItemResponse.class);

        return Rx2AndroidNetworking.upload(ApiConstants.BASE_URL + ApiConstants.ADD_RESTAURANT_MENU_ITEM)
                .addMultipartFile(fileMap)
                .addMultipartParameter("data", jsonObject.toString())
                .build()
                .getObjectObservable(AddMenuItemResponse.class);
    }

    @Override
    public Observable<StatusMenuItemResponse> changeStatusRestaurantMenuItem(StatusMenuItemRequest statusMenuItemRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(statusMenuItemRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.ACTIVE_INACTIVE_MENU_ITEM)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(StatusMenuItemResponse.class);
    }

    @Override
    public Observable<DeleteMenuItemResponse> deleteRestaurantMenuItem(DeleteMenuItemRequest deleteMenuItemRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(deleteMenuItemRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.DELETE_MENU_ITEM)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(DeleteMenuItemResponse.class);
    }

    @Override
    public Observable<GetAccountDetailResponse> getCustomerAccountDetail(GetAccountDetailRequest getAccountDetailRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(getAccountDetailRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.GET_CUSTOMER_PROFILE)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(GetAccountDetailResponse.class);
    }

    @Override
    public Observable<EditCustomerAccountDetailResponse> editCustomerAccount(EditCustomerAccountRequest editCustomerAccountRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(editCustomerAccountRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.EDIT_CUSTOMER_PROFILE)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(EditCustomerAccountDetailResponse.class);
    }

    @Override
    public Observable<AddToCartResponse> addToCart(AddToCartRequest addToCartRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(addToCartRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.ADD_TO_CART)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(AddToCartResponse.class);
    }

    @Override
    public Observable<ViewCartResponse> getCartDetails(ViewCartRequest viewCartRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(viewCartRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.CART_DETAIL)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(ViewCartResponse.class);
    }

    @Override
    public Observable<RemoveFromCartResponse> removeFromCart(RemoveFromCartRequest removeFromCartRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(removeFromCartRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.REMOVE_FROM_CART)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(RemoveFromCartResponse.class);
    }

    @Override
    public Observable<UpdateCartResponse> updateCart(UpdateCartRequest updateCartRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(updateCartRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.UPDATE_CART)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(UpdateCartResponse.class);
    }

    @Override
    public Observable<ClearCartResponse> clearCart(ClearCartRequest clearCartRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(clearCartRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.CLEAR_CART)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(ClearCartResponse.class);
    }

    @Override
    public Observable<RestaurantProfileResponse> getRestaurantProfile(CustomerRestaurantDetailsRequest customerRestaurantDetailsRequest) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(customerRestaurantDetailsRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.GET_RESTAURANT_PROFILE)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(RestaurantProfileResponse.class);
    }

    @Override
    public Observable<ChangePasswordResponse> deleteRestaurantImage(String restaurantId) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.put("image_id", restaurantId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.DELETE_RESTAURNT_IMAGES)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(ChangePasswordResponse.class);
    }

    @Override
    public Observable<EditRestaurantAccountResponse> editRestaurantAccount(EditRestaurantAccountRequest editRestaurantAccountRequest, HashMap<String, File> fileMap) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(editRestaurantAccountRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.upload(ApiConstants.BASE_URL + ApiConstants.EDIT_RESTAURANT_ACCOUNT)
                .addMultipartFile(fileMap)
                .addMultipartParameter("data", jsonObject.toString())
                .build()
                .getObjectObservable(EditRestaurantAccountResponse.class);
    }

    @Override
    public Observable<GetAllAddressResponse> getAllAddress(GetAllAddressRequest getAllAddressRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(getAllAddressRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.GET_ADDRESS)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(GetAllAddressResponse.class);
    }

    @Override
    public Observable<DeleteAddressResponse> deleteAddress(DeleteAddressRequest deleteAddressRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(deleteAddressRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.DELETE_ADDRESS)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(DeleteAddressResponse.class);
    }

    @Override
    public Observable<AddDeliveryAddressResponse> addDeliveryAddress(AddDeliveryAddressRequest addDeliveryAddressRequest) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(addDeliveryAddressRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.ADD_ADDRESS)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(AddDeliveryAddressResponse.class);
    }

    @Override
    public Observable<UpdateAddressResponse> updateDeliveryAddress(UpdateAddressRequest updateAddressRequest) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(updateAddressRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.UPDATE_ADDRESS)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(UpdateAddressResponse.class);
    }

    @Override
    public Observable<AddPaymentCardResponse> addPaymentCard(AddPaymentCardRequest addPaymentCardRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(addPaymentCardRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.ADD_PAYMENT_CARD)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(AddPaymentCardResponse.class);
    }

    @Override
    public Observable<GetAllPaymentCardResponse> getAllPaymentCard(GetAllPaymentCardRequest getAllPaymentCardRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(getAllPaymentCardRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.GET_ALL_PAYMENT_CARD)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(GetAllPaymentCardResponse.class);
    }

    @Override
    public Observable<DeletePaymentCardResponse> deletePaymentCard(DeletePaymentCardRequest deletePaymentCardRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(deletePaymentCardRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.DELETE_PAYMENT_CARD)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(DeletePaymentCardResponse.class);
    }

    @Override
    public Observable<CheckoutResponse> checkoout(CheckoutRequest checkoutRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(checkoutRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.CHECKOUT)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(CheckoutResponse.class);
    }

    @Override
    public Observable<GetOrderListResponse> getOrderList(GetOrderListRequest getOrderListRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(getOrderListRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.ORDER_LIST)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(GetOrderListResponse.class);
    }

    @Override
    public Observable<AcceptRejectOrderResponse> acceptRejectOrder(AcceptRejectOrderRequest acceptRejectOrderRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(acceptRejectOrderRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.ACCEPT_REJECT_ORDER)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(AcceptRejectOrderResponse.class);
    }

    @Override
    public Observable<LogoutResponse> logout(LogoutRequest logoutRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(logoutRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.LOGOUT)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(LogoutResponse.class);
    }

    @Override
    public Observable<SetMerchantDetailResponse> setMerchantDetail(SetMerchantDetailRequest merchantDetailRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(merchantDetailRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.SET_MERCHANT_DETAIL)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(SetMerchantDetailResponse.class);
    }

    @Override
    public Observable<GetMerchantDetailResponse> getMerchantDetail(GetMerchantDetailRequest merchantDetailRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(merchantDetailRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.GET_MERCHANT_DETAIL)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(GetMerchantDetailResponse.class);
    }

    @Override
    public Observable<ReorderResponse> reorder(ReorderRequest reorderRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(reorderRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.REORDER)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(ReorderResponse.class);
    }

    @Override
    public Observable<GetReviewsResponse> getReviews(GetReviewsRequest reviewsRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(reviewsRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.REVIEW)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(GetReviewsResponse.class);
    }

    //Abhinav
    @Override
    public Observable<DishListResponse> showDishlist(DishListRequest dishListRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(dishListRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.GET_DISH_LIST)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(DishListResponse.class);
    }

    @Override
    public Observable<AddDiscountResponse> addDiscount(AddDiscountRequest addDiscountRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(addDiscountRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.ADD_DISCOUNT)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(AddDiscountResponse.class);
    }

    @Override
    public Observable<DiscountListResponse> DiscountList(DishListRequest dishListRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(dishListRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.DISCOUNT_LIST)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(DiscountListResponse.class);
    }

    @Override
    public Observable<AddDiscountResponse> deleteDiscount(DeleteDiscountRequest deleteDiscountRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(deleteDiscountRequest));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.DELETE_DISCOUNT)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(AddDiscountResponse.class);
    }

    @Override
    public Observable<StaticPageResponse> staticPage(StaticPageRequest staticPageRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(staticPageRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.GET_PAGE)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(StaticPageResponse.class);
    }

    @Override
    public Observable<SetNotificationResponse> setNotification(String user_id) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.put("user_id", user_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.SET_NOTIFICATION)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(SetNotificationResponse.class);
    }

    @Override
    public Observable<GetEarningResponse> getEarnings(GetEarningRequest earningRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(earningRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.EARNING)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(GetEarningResponse.class);
    }

    //    Monika
    @Override
    public Observable<GetOrderListResponse> getOrderHistoryList(GetOrderListRequest getOrderListRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(getOrderListRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.ORDER_HISTORY)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(GetOrderListResponse.class);
    }

    @Override
    public Observable<GetOrderListResponse> getCustomerOrderHistoryList(CustomerOrderHistoryRequest customerOrderHistoryRequest) {
//    public Observable<CustomerOrderHistoryResponse> getCustomerOrderHistoryList(CustomerOrderHistoryRequest customerOrderHistoryRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(customerOrderHistoryRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.CUSTOMER_ORDER_HISTORY)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(GetOrderListResponse.class);
    }

    @Override
    public Observable<OrderHistoryDetail> getOrderHistoryDetail(String orderId,String language) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.put("order_id", orderId);
            jsonObject.put("language", language);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.ORDER_DETAIL)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(OrderHistoryDetail.class);
    }

    @Override
    public Observable<ChangeOrderStatusResponse> changeOrderStatus(String orderId, String orderStatus, String language) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.put("order_id", orderId);
            jsonObject.put("order_status", orderStatus);
            jsonObject.put("language", language);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.CHANGE_ORDER_STATUS)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(ChangeOrderStatusResponse.class);
    }


    @Override
    public Observable<RestaurantReviewResponse> sendRestaurantReview(RestaurantReviewRequest restaurantReviewRequest) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(restaurantReviewRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.GIVE_REVIEW)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(RestaurantReviewResponse.class);
    }


    @Override
    public Observable<NotificationListResponse> getNotification(String user_id, String restaurant_id) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.put("user_id", user_id);
            jsonObject.put("restaurant_id", restaurant_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.GET_NOTIFICATION_LIST)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(NotificationListResponse.class);
    }

    @Override
    public Observable<NotificationListResponse> readNotification(String notification_id) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject();
            jsonObject.put("notification_id", notification_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.READ_NOTIFICATION)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(NotificationListResponse.class);
    }

    @Override
    public Observable<ChangeLanguageResponse> changeLanguage(ChangeLanguageRequest changeLanguageRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(changeLanguageRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.CHANGE_LANGUAGE)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(ChangeLanguageResponse.class);
    }

    @Override
    public Observable<GetCartListResponse> getCartList(GetCartListRequest getCartListRequest) {

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(new Gson().toJson(getCartListRequest));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("jsonObject", ">>" + jsonObject.toString());

        return Rx2AndroidNetworking.post(ApiConstants.BASE_URL + ApiConstants.CART_LIST)
                .addJSONObjectBody(jsonObject)
                .build()
                .getObjectObservable(GetCartListResponse.class);
    }
}