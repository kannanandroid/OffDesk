package com.ifazig.optdesk.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingConfirmationApiResponse {

    @SerializedName("Status")
    @Expose
    private Boolean status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("ReturnData")
    @Expose
    private ReturnData returnData;
    @SerializedName("Method")
    @Expose
    private String method;
    @SerializedName("SessionID")
    @Expose
    private String sessionID;
    @SerializedName("ID")
    @Expose
    private String iD;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReturnData getReturnData() {
        return returnData;
    }

    public void setReturnData(ReturnData returnData) {
        this.returnData = returnData;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }
    public class ReturnData {

        @SerializedName("ConfirmationDetails")
        @Expose
        private List<ConfirmationDetail> confirmationDetails = null;

        public List<ConfirmationDetail> getConfirmationDetails() {
            return confirmationDetails;
        }

        public void setConfirmationDetails(List<ConfirmationDetail> confirmationDetails) {
            this.confirmationDetails = confirmationDetails;
        }

    }
    public class ConfirmationDetail {

        @SerializedName("CompanyName")
        @Expose
        private String companyName;
        @SerializedName("LocationName")
        @Expose
        private String locationName;
        @SerializedName("BuildingName")
        @Expose
        private String buildingName;
        @SerializedName("FloorName")
        @Expose
        private String floorName;
        @SerializedName("WingName")
        @Expose
        private String wingName;
        @SerializedName("book")
        @Expose
        private List<Book> book = null;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public String getFloorName() {
            return floorName;
        }

        public void setFloorName(String floorName) {
            this.floorName = floorName;
        }

        public String getWingName() {
            return wingName;
        }

        public void setWingName(String wingName) {
            this.wingName = wingName;
        }

        public List<Book> getBook() {
            return book;
        }

        public void setBook(List<Book> book) {
            this.book = book;
        }

    }
    public class Book {

        @SerializedName("Date")
        @Expose
        private String date;
        @SerializedName("StartTime")
        @Expose
        private String startTime;
        @SerializedName("ToTime")
        @Expose
        private String toTime;
        @SerializedName("WorkstationId")
        @Expose
        private Integer workstationId;
        @SerializedName("WorkstationName")
        @Expose
        private String workstationName;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getToTime() {
            return toTime;
        }

        public void setToTime(String toTime) {
            this.toTime = toTime;
        }

        public Integer getWorkstationId() {
            return workstationId;
        }

        public void setWorkstationId(Integer workstationId) {
            this.workstationId = workstationId;
        }

        public String getWorkstationName() {
            return workstationName;
        }

        public void setWorkstationName(String workstationName) {
            this.workstationName = workstationName;
        }

    }

}
