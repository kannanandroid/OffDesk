package com.ifazig.optdesk.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingApiResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("system")
    @Expose
    private System system;
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public class System {

        @SerializedName("cc")
        @Expose
        private Integer cc;

        public Integer getCc() {
            return cc;
        }

        public void setCc(Integer cc) {
            this.cc = cc;
        }

    }

    public static class Datum {

        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("time")
        @Expose
        private String time;
        @SerializedName("ingredients")
        @Expose
        private String ingredients;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("min_cart_amount")
        @Expose
        private String min_cart_amount;
        @SerializedName("delivery_time")
        @Expose
        private String delivery_time;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("rating")
        @Expose
        private String rating;

        public String getIngredients() {
            return ingredients;
        }

        public void setIngredients(String ingredients) {
            this.ingredients = ingredients;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getMin_cart_amount() {
            return min_cart_amount;
        }

        public void setMin_cart_amount(String min_cart_amount) {
            this.min_cart_amount = min_cart_amount;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }


}
