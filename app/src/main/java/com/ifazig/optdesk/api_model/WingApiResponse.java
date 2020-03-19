package com.ifazig.optdesk.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WingApiResponse {
    @SerializedName("Status")
    @Expose
    private Boolean status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("ReturnData")
    @Expose
    private List<ReturnDatum> returnData = new ArrayList<ReturnDatum>();
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

    public List<ReturnDatum> getReturnData() {
        return returnData;
    }

    public void setReturnData(List<ReturnDatum> returnData) {
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
    public class ReturnDatum {

        @SerializedName("WingID")
        @Expose
        private Integer wingID;
        @SerializedName("FloorID")
        @Expose
        private Integer floorID;
        @SerializedName("WingName")
        @Expose
        private String wingName;
        @SerializedName("WingShortName")
        @Expose
        private String wingShortName;

        public Integer getWingID() {
            return wingID;
        }

        public void setWingID(Integer wingID) {
            this.wingID = wingID;
        }

        public Integer getFloorID() {
            return floorID;
        }

        public void setFloorID(Integer floorID) {
            this.floorID = floorID;
        }

        public String getWingName() {
            return wingName;
        }

        public void setWingName(String wingName) {
            this.wingName = wingName;
        }

        public String getWingShortName() {
            return wingShortName;
        }

        public void setWingShortName(String wingShortName) {
            this.wingShortName = wingShortName;
        }

    }
}
