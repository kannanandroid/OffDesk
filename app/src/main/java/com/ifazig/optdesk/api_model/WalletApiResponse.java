package com.ifazig.optdesk.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WalletApiResponse {
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

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("date_and_time")
        @Expose
        private String date_and_time;
        @SerializedName("transaction_by")
        @Expose
        private String transaction_by;
        @SerializedName("transaction_id")
        @Expose
        private String transaction_id;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("balance")
        @Expose
        private String balance;
        @SerializedName("status")
        @Expose
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDate_and_time() {
            return date_and_time;
        }

        public void setDate_and_time(String date_and_time) {
            this.date_and_time = date_and_time;
        }

        public String getTransaction_by() {
            return transaction_by;
        }

        public void setTransaction_by(String transaction_by) {
            this.transaction_by = transaction_by;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTransaction_id() {
            return transaction_id;
        }

        public void setTransaction_id(String transaction_id) {
            this.transaction_id = transaction_id;
        }
    }
}
