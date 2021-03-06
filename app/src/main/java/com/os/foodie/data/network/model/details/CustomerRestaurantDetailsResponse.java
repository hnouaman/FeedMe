
package com.os.foodie.data.network.model.details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerRestaurantDetailsResponse {

    @SerializedName("response")
    @Expose
    private Response response;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CustomerRestaurantDetailsResponse() {
    }

    /**
     * 
     * @param response
     */
    public CustomerRestaurantDetailsResponse(Response response) {
        super();
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
