package com.os.foodie.ui.payment.select;

import com.os.foodie.data.network.model.checkout.CheckoutResponse;
import com.os.foodie.data.network.model.payment.getall.GetAllPaymentCardResponse;
import com.os.foodie.ui.base.MvpView;

public interface SelectPaymentMvpView extends MvpView {

    void notifyDataSetChanged(GetAllPaymentCardResponse getAllPaymentCardResponse);

    void onCheckoutComplete(CheckoutResponse checkoutResponse);
//    void onCheckoutComplete(String message);
}