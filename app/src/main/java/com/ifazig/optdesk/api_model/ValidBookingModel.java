package com.ifazig.optdesk.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValidBookingModel {

    @SerializedName("wingid")
    @Expose
    private String wingid;
    @SerializedName("UserId")
    @Expose
    private String userId;
    @SerializedName("RoleId")
    @Expose
    private String roleId;
    @SerializedName("Book")
    @Expose
    private List<Book> book = null;

    public String getWingid() {
        return wingid;
    }

    public void setWingid(String wingid) {
        this.wingid = wingid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    public static class Book {

        @SerializedName("StartDate")
        @Expose
        private String startDate;
        @SerializedName("StartTime")
        @Expose
        private String startTime;
        @SerializedName("EndTime")
        @Expose
        private String endTime;


        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }


        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }

}
