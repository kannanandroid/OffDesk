package com.ifazig.optdesk.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SettingsDetailsApiResponse {


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

        @SerializedName("SettingDetails")
        @Expose
        private List<SettingDetail> settingDetails = null;

        public List<SettingDetail> getSettingDetails() {
            return settingDetails;
        }

        public void setSettingDetails(List<SettingDetail> settingDetails) {
            this.settingDetails = settingDetails;
        }

    }
    public class SettingDetail {

        @SerializedName("WorkStationSettingId")
        @Expose
        private Integer workStationSettingId;
        @SerializedName("Location")
        @Expose
        private String location;
        @SerializedName("MinSlot")
        @Expose
        private String minSlot;
        @SerializedName("MaxHours")
        @Expose
        private String maxHours;
        @SerializedName("StartTime")
        @Expose
        private String startTime;
        @SerializedName("EndTime")
        @Expose
        private String endTime;
        @SerializedName("PriorDays")
        @Expose
        private Integer priorDays;
        @SerializedName("NumberofBooking")
        @Expose
        private Integer numberofBooking;
        @SerializedName("CuId")
        @Expose
        private Integer cuId;
        @SerializedName("CDate")
        @Expose
        private String cDate;
        @SerializedName("MuId")
        @Expose
        private Integer muId;
        @SerializedName("MDate")
        @Expose
        private String mDate;
        @SerializedName("IsDelete")
        @Expose
        private Integer isDelete;
        @SerializedName("CompanyId")
        @Expose
        private Integer companyId;
        @SerializedName("InBetweenBook")
        @Expose
        private String inBetweenBook;
        @SerializedName("Workstation")
        @Expose
        private Integer Workstation;

        public Integer getWorkStationSettingId() {
            return workStationSettingId;
        }

        public void setWorkStationSettingId(Integer workStationSettingId) {
            this.workStationSettingId = workStationSettingId;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getMinSlot() {
            return minSlot;
        }

        public void setMinSlot(String minSlot) {
            this.minSlot = minSlot;
        }

        public String getMaxHours() {
            return maxHours;
        }

        public void setMaxHours(String maxHours) {
            this.maxHours = maxHours;
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

        public Integer getPriorDays() {
            return priorDays;
        }

        public void setPriorDays(Integer priorDays) {
            this.priorDays = priorDays;
        }

        public Integer getNumberofBooking() {
            return numberofBooking;
        }

        public void setNumberofBooking(Integer numberofBooking) {
            this.numberofBooking = numberofBooking;
        }

        public Integer getCuId() {
            return cuId;
        }

        public void setCuId(Integer cuId) {
            this.cuId = cuId;
        }

        public String getCDate() {
            return cDate;
        }

        public void setCDate(String cDate) {
            this.cDate = cDate;
        }

        public Integer getMuId() {
            return muId;
        }

        public void setMuId(Integer muId) {
            this.muId = muId;
        }

        public String getMDate() {
            return mDate;
        }

        public void setMDate(String mDate) {
            this.mDate = mDate;
        }

        public Integer getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(Integer isDelete) {
            this.isDelete = isDelete;
        }

        public Integer getCompanyId() {
            return companyId;
        }

        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }

        public String getInBetweenBook() {
            return inBetweenBook;
        }

        public void setInBetweenBook(String inBetweenBook) {
            this.inBetweenBook = inBetweenBook;
        }

        public Integer getWorkstation() {
            return Workstation;
        }

        public void setWorkstation(Integer workstation) {
            Workstation = workstation;
        }
    }
}
