package com.ifazig.optdesk.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LocationApiResponse {

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

        @SerializedName("MapCompanyID")
        @Expose
        private Integer mapCompanyID;
        @SerializedName("MapLocationID")
        @Expose
        private Integer mapLocationID;
        @SerializedName("LocationName")
        @Expose
        private String locationName;
        @SerializedName("LocationShortName")
        @Expose
        private String locationShortName;

        public Integer getMapCompanyID() {
            return mapCompanyID;
        }

        public void setMapCompanyID(Integer mapCompanyID) {
            this.mapCompanyID = mapCompanyID;
        }

        public Integer getMapLocationID() {
            return mapLocationID;
        }

        public void setMapLocationID(Integer mapLocationID) {
            this.mapLocationID = mapLocationID;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public String getLocationShortName() {
            return locationShortName;
        }

        public void setLocationShortName(String locationShortName) {
            this.locationShortName = locationShortName;
        }

    }
}
