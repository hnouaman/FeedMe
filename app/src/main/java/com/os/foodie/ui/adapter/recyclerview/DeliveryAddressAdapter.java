package com.os.foodie.ui.adapter.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.os.foodie.R;
import com.os.foodie.data.network.model.deliveryaddress.getall.Address;
import com.os.foodie.ui.deliveryaddress.addedit.AddEditDeliveryAddressActivity;
import com.os.foodie.ui.deliveryaddress.show.DeliveryAddressMvpPresenter;
import com.os.foodie.ui.deliveryaddress.show.DeliveryAddressMvpView;
import com.os.foodie.utils.AppConstants;
import com.os.foodie.utils.DialogUtils;

import java.util.ArrayList;

public class DeliveryAddressAdapter extends RecyclerView.Adapter<DeliveryAddressAdapter.DeliveryAddressViewHolder> {

    private Activity activity;
    private ArrayList<Address> addresses;
    private DeliveryAddressMvpPresenter<DeliveryAddressMvpView> deliveryAddressMvpPresenter;

    public DeliveryAddressAdapter(Activity activity, ArrayList<Address> addresses, DeliveryAddressMvpPresenter<DeliveryAddressMvpView> deliveryAddressMvpPresenter) {
        this.activity = activity;
        this.addresses = addresses;
        this.deliveryAddressMvpPresenter = deliveryAddressMvpPresenter;
    }

    class DeliveryAddressViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName, tvStreetNumber, tvColony, tvCityStatePin, tvCountry, tvMobileNumber;
        public Button btEdit, btDelete;

        public DeliveryAddressViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.recyclerview_delivery_address_tv_name);
            tvStreetNumber = (TextView) itemView.findViewById(R.id.recyclerview_delivery_address_tv_street_number);
            tvColony = (TextView) itemView.findViewById(R.id.recyclerview_delivery_address_tv_colony);
            tvCityStatePin = (TextView) itemView.findViewById(R.id.recyclerview_delivery_address_tv_city_state_pin);
            tvCountry = (TextView) itemView.findViewById(R.id.recyclerview_delivery_address_tv_country);
            tvMobileNumber = (TextView) itemView.findViewById(R.id.recyclerview_delivery_address_tv_mobile_number);

            btEdit = (Button) itemView.findViewById(R.id.recyclerview_delivery_address_bt_edit);
            btDelete = (Button) itemView.findViewById(R.id.recyclerview_delivery_address_bt_delete);
        }
    }

    @Override
    public DeliveryAddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_delivery_address, parent, false);
        return new DeliveryAddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeliveryAddressViewHolder holder, final int position) {

        Address address = addresses.get(position);

        holder.tvName.setText(address.getFullName());
        holder.tvStreetNumber.setText(address.getFlatNumber());
        holder.tvColony.setText(address.getColony());

        holder.tvCityStatePin.setText(address.getCity() + " " + address.getState() + " " + address.getPincode());

        holder.tvCountry.setText(address.getCountry());
        holder.tvMobileNumber.setText("Mobile Number: " + address.getMobileNumber());

        if (address.getLandmark() != null && !address.getLandmark().isEmpty()) {
            holder.tvMobileNumber.setText("Landmark: " + address.getLandmark() + "\n" + holder.tvMobileNumber.getText());
        }

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogInterface.OnClickListener positiveButton = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                        deliveryAddressMvpPresenter.deleteAddress(addresses.get(position).getId(), position);
                    }
                };

                DialogInterface.OnClickListener negativeButton = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };

                DialogUtils.showAlert(activity,
                        R.string.alert_dialog_title_delete_address, R.string.alert_dialog_text_delete_address,
                        activity.getResources().getString(R.string.alert_dialog_bt_yes), positiveButton,
                        activity.getResources().getString(R.string.alert_dialog_bt_no), negativeButton);
            }
        });

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, AddEditDeliveryAddressActivity.class);
                intent.putExtra(AppConstants.POSITION, position);
                intent.putExtra(AppConstants.DELIVERY_ADDRESS, addresses.get(position));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }
}