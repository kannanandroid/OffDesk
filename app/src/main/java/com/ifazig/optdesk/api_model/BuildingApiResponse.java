package com.ifazig.optdesk.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BuildingApiResponse {

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

        @SerializedName("BuildingID")
        @Expose
        private Integer buildingID;
        @SerializedName("LocationID")
        @Expose
        private Integer locationID;
        @SerializedName("BuildingName")
        @Expose
        private String buildingName;
        @SerializedName("BuildingShortName")
        @Expose
        private String buildingShortName;

        public Integer getBuildingID() {
            return buildingID;
        }

        public void setBuildingID(Integer buildingID) {
            this.buildingID = buildingID;
        }

        public Integer getLocationID() {
            return locationID;
        }

        public void setLocationID(Integer locationID) {
            this.locationID = locationID;
        }

        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public String getBuildingShortName() {
            return buildingShortName;
        }

        public void setBuildingShortName(String buildingShortName) {
            this.buildingShortName = buildingShortName;
        }

    }

}
