package com.ifazig.optdesk.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingHistoryApiResponse {

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

        @SerializedName("UpcomingBookingHistory")
        @Expose
        private List<UpcomingBookingHistory> upcomingBookingHistory = null;
        @SerializedName("PastBookingHistory")
        @Expose
        private List<PastBookingHistory> pastBookingHistory = null;

        public List<UpcomingBookingHistory> getUpcomingBookingHistory() {
            return upcomingBookingHistory;
        }

        public void setUpcomingBookingHistory(List<UpcomingBookingHistory> upcomingBookingHistory) {
            this.upcomingBookingHistory = upcomingBookingHistory;
        }

        public List<PastBookingHistory> getPastBookingHistory() {
            return pastBookingHistory;
        }

        public void setPastBookingHistory(List<PastBookingHistory> pastBookingHistory) {
            this.pastBookingHistory = pastBookingHistory;
        }

    }
    public class UpcomingBookingHistory {

        @SerializedName("StartTime")
        @Expose
        private String startTime;
        @SerializedName("EndTime")
        @Expose
        private String endTime;
        @SerializedName("StartDate")
        @Expose
        private String startDate;
        @SerializedName("EndDate")
        @Expose
        private String endDate;
        @SerializedName("FloorMapBookingId")
        @Expose
        private Integer floorMapBookingId;
        @SerializedName("Book")
        @Expose
        private List<Book> book = null;
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

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public Integer getFloorMapBookingId() {
            return floorMapBookingId;
        }

        public void setFloorMapBookingId(Integer floorMapBookingId) {
            this.floorMapBookingId = floorMapBookingId;
        }

        public List<Book> getBook() {
            return book;
        }

        public void setBook(List<Book> book) {
            this.book = book;
        }

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

    }
    public class PastBookingHistory {

        @SerializedName("StartTime")
        @Expose
        private String startTime;
        @SerializedName("EndTime")
        @Expose
        private String endTime;
        @SerializedName("StartDate")
        @Expose
        private String startDate;
        @SerializedName("EndDate")
        @Expose
        private String endDate;
        @SerializedName("FloorMapBookingId")
        @Expose
        private Integer floorMapBookingId;
        @SerializedName("Book")
        @Expose
        private List<Book_> book = null;
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

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public Integer getFloorMapBookingId() {
            return floorMapBookingId;
        }

        public void setFloorMapBookingId(Integer floorMapBookingId) {
            this.floorMapBookingId = floorMapBookingId;
        }

        public List<Book_> getBook() {
            return book;
        }

        public void setBook(List<Book_> book) {
            this.book = book;
        }

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
    public class Book_ {

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
