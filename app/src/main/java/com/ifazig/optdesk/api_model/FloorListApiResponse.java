package com.ifazig.optdesk.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FloorListApiResponse {
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

        @SerializedName("FloorID")
        @Expose
        private Integer floorID;
        @SerializedName("BuildingID")
        @Expose
        private Integer buildingID;
        @SerializedName("FloorName")
        @Expose
        private String floorName;
        @SerializedName("FloorShortName")
        @Expose
        private String floorShortName;

        public Integer getFloorID() {
            return floorID;
        }

        public void setFloorID(Integer floorID) {
            this.floorID = floorID;
        }

        public Integer getBuildingID() {
            return buildingID;
        }

        public void setBuildingID(Integer buildingID) {
            this.buildingID = buildingID;
        }

        public String getFloorName() {
            return floorName;
        }

        public void setFloorName(String floorName) {
            this.floorName = floorName;
        }

        public String getFloorShortName() {
            return floorShortName;
        }

        public void setFloorShortName(String floorShortName) {
            this.floorShortName = floorShortName;
        }

    }
}
