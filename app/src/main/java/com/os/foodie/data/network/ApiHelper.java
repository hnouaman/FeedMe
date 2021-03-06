package com.os.foodie.data.network;

import android.content.Context;
import android.location.Address;

import com.google.android.gms.maps.model.LatLng;
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

import java.io.File;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

public interface ApiHelper {

    Observable<CustomerSignUpResponse> doCustomerSignUp(CustomerSignUpRequest customerSignUpRequest);

    Observable<RestaurantSignUpResponse> doRestaurantSignUp(RestaurantSignUpRequest restaurantSignUpRequest, HashMap<String, File> fileMap);

    Observable<FacebookLoginResponse> doFacebookLogin(FacebookLoginRequest facebookLoginRequest);

    Observable<LoginResponse> doLogin(LoginRequest loginRequest);

    Observable<OtpVerificationResponse> verifyOTP(OtpVerificationRequest otpVerificationRequest);

    Observable<ResendOtpResponse> resendOTP(ResendOtpRequest resendOtpRequest);

    Observable<ForgotPasswordResponse> resetPassword(ForgotPasswordRequest forgotPasswordRequest);

    Observable<CountryListResponse> getCountryList();

    Observable<CityListResponse> getCityList(CityListRequest cityListRequest);

    Observable<CityCountryListResponse> getCityCountryList(CityCountryListRequest cityCountryListRequest);

    Observable<GetUserLocationDetailResponse> getUserLocationDetail(GetUserLocationDetailRequest userLocationDetailRequest);

    Observable<SetUserLocationResponse> setUserLocationInfo(SetUserLocationRequest setUserLocationRequest);

    Observable<CuisineTypeResponse> getCuisineTypeList();

    Observable<AddCuisineTypeResponse> addCuisineType(AddCuisineTypeRequest addCuisineTypeRequest);

    Observable<SetupRestaurantProfileResponse> setRestaurantProfile(SetupRestaurantProfileRequest restaurantProfileRequest, HashMap<String, File> fileMap);

    Observable<GetRestaurantMenuResponse> getRestaurantMenuList(GetRestaurantMenuRequest restaurantMenuRequest);

//    Observable<AddMenuItemResponse> addRestaurantMenuItem(AddMenuItemRequest addMenuItemRequest);

    public Observable<AddMenuItemResponse> addRestaurantMenuItem(AddMenuItemRequest addMenuItemRequest, HashMap<String, File>  fileMap);

    Observable<StatusMenuItemResponse> changeStatusRestaurantMenuItem(StatusMenuItemRequest statusMenuItemRequest);

    Observable<DeleteMenuItemResponse> deleteRestaurantMenuItem(DeleteMenuItemRequest deleteMenuItemRequest);

    Observable<GetCourseTypeResponse> getCourseTypeList();

    Observable<AddCourseTypeResponse> addCourseType(AddCourseTypeRequest addCourseTypeRequest);

    Observable<List<Address>> getGeocoderLocationAddress(Context context, LatLng latLng);

    Observable<GetRestaurantListResponse> getRestaurantList(GetRestaurantListRequest getRestaurantListRequest);

    Observable<CustomerRestaurantDetailsResponse> getRestaurantDetails(CustomerRestaurantDetailsRequest restaurantDetailsRequest);

    Observable<LikeDislikeResponse> doLikeDislike(LikeDislikeRequest likeDislikeRequest);

    Observable<ChangePasswordResponse> changePassword(ChangePasswordRequest changePasswordRequest);

    Observable<GetAccountDetailResponse> getCustomerAccountDetail(GetAccountDetailRequest getAccountDetailRequest);

    Observable<EditCustomerAccountDetailResponse> editCustomerAccount(EditCustomerAccountRequest editCustomerAccountRequest);

    Observable<AddToCartResponse> addToCart(AddToCartRequest addToCartRequest);

    Observable<ViewCartResponse> getCartDetails(ViewCartRequest viewCartRequest);

    Observable<RemoveFromCartResponse> removeFromCart(RemoveFromCartRequest removeFromCartRequest);

    Observable<UpdateCartResponse> updateCart(UpdateCartRequest updateCartRequest);

    Observable<ClearCartResponse> clearCart(ClearCartRequest clearCartRequest);

    Observable<RestaurantProfileResponse> getRestaurantProfile(CustomerRestaurantDetailsRequest customerRestaurantDetailsRequest);

    Observable<ChangePasswordResponse> deleteRestaurantImage(String restaurantId);

    Observable<EditRestaurantAccountResponse> editRestaurantAccount(EditRestaurantAccountRequest editRestaurantAccountRequest, HashMap<String, File> fileMap);

    Observable<GetAllAddressResponse> getAllAddress(GetAllAddressRequest getAllAddressRequest);

    Observable<DeleteAddressResponse> deleteAddress(DeleteAddressRequest deleteAddressRequest);

    Observable<AddDeliveryAddressResponse> addDeliveryAddress(AddDeliveryAddressRequest addDeliveryAddressRequest);

    Observable<UpdateAddressResponse> updateDeliveryAddress(UpdateAddressRequest updateAddressRequest);

    Observable<AddPaymentCardResponse> addPaymentCard(AddPaymentCardRequest addPaymentCardRequest);

    Observable<GetAllPaymentCardResponse> getAllPaymentCard(GetAllPaymentCardRequest getAllPaymentCardRequest);

    Observable<DeletePaymentCardResponse> deletePaymentCard(DeletePaymentCardRequest deletePaymentCardRequest);

    Observable<CheckoutResponse> checkoout(CheckoutRequest checkoutRequest);

    Observable<GetOrderListResponse> getOrderList(GetOrderListRequest getOrderListRequest);

    Observable<AcceptRejectOrderResponse> acceptRejectOrder(AcceptRejectOrderRequest acceptRejectOrderRequest);

    Observable<LogoutResponse> logout(LogoutRequest logoutRequest);

    Observable<SetMerchantDetailResponse> setMerchantDetail(SetMerchantDetailRequest merchantDetailRequest);

    Observable<GetMerchantDetailResponse> getMerchantDetail(GetMerchantDetailRequest merchantDetailRequest);

    Observable<ReorderResponse> reorder(ReorderRequest reorderRequest);

    Observable<GetReviewsResponse> getReviews(GetReviewsRequest reviewsRequest);

//    Abhi

    Observable<DishListResponse> showDishlist(DishListRequest dishListRequest);

    Observable<AddDiscountResponse> addDiscount(AddDiscountRequest addDiscountRequest);

    Observable<DiscountListResponse> DiscountList(DishListRequest dishListRequest);

    Observable<AddDiscountResponse> deleteDiscount(DeleteDiscountRequest deleteDiscountRequest);

    Observable<StaticPageResponse> staticPage(StaticPageRequest staticPageRequest);

    Observable<SetNotificationResponse> setNotification(String user_id);

    Observable<GetEarningResponse> getEarnings(GetEarningRequest earningRequest);

    //    Monika
    Observable<GetOrderListResponse> getOrderHistoryList(GetOrderListRequest getOrderListRequest);

    Observable<GetOrderListResponse> getCustomerOrderHistoryList(CustomerOrderHistoryRequest customerOrderHistoryRequest);
//Observable<CustomerOrderHistoryResponse> getCustomerOrderHistoryList(CustomerOrderHistoryRequest customerOrderHistoryRequest);

    Observable<OrderHistoryDetail> getOrderHistoryDetail(String orderId,String language);

    Observable<ChangeOrderStatusResponse> changeOrderStatus(String orderId, String orderStatus, String language);

    Observable<RestaurantReviewResponse> sendRestaurantReview(RestaurantReviewRequest restaurantReviewRequest);

    Observable<NotificationListResponse> getNotification(String user_id, String restaurant_id);

    Observable<NotificationListResponse> readNotification(String notification_id);

    Observable<ChangeLanguageResponse> changeLanguage(ChangeLanguageRequest changeLanguageRequest);

    Observable<GetCartListResponse> getCartList(GetCartListRequest getCartListRequest);
}