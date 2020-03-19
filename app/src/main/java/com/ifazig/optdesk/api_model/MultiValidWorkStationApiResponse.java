package com.ifazig.optdesk.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MultiValidWorkStationApiResponse {

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

        @SerializedName("ValidationWorkstation")
        @Expose
        private List<ValidationWorkstation> validationWorkstation = null;

        public List<ValidationWorkstation> getValidationWorkstation() {
            return validationWorkstation;
        }

        public void setValidationWorkstation(List<ValidationWorkstation> validationWorkstation) {
            this.validationWorkstation = validationWorkstation;
        }

    }

    public class ValidationWorkstation {

        @SerializedName("Workstationlist")
        @Expose
        private List<Workstationlist> workstationlist = null;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("Date")
        @Expose
        private String date;
        @SerializedName("StartTime")
        @Expose
        private String startTime;
        @SerializedName("EndTime")
        @Expose
        private String endTime;


        public List<Workstationlist> getWorkstationlist() {
            return workstationlist;
        }

        public void setWorkstationlist(List<Workstationlist> workstationlist) {
            this.workstationlist = workstationlist;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

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

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
    }

    public class Workstationlist {

        @SerializedName("WorkstationId")
        @Expose
        private Integer workstationId;
        @SerializedName("WorkstationName")
        @Expose
        private String workstationName;
        @SerializedName("BookingStatus")
        @Expose
        private Integer bookingStatus;

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

        public Integer getBookingStatus() {
            return bookingStatus;
        }

        public void setBookingStatus(Integer bookingStatus) {
            this.bookingStatus = bookingStatus;
        }

    }
}
