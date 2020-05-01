package com.ifazig.optdesk.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScanBookingApiResponse {

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
    public class LoginHistory {

        @SerializedName("UserId")
        @Expose
        private Integer userId;
        @SerializedName("WorkstationName")
        @Expose
        private String workstationName;
        @SerializedName("MobileLoginList")
        @Expose
        private List<MobileLoginList> mobileLoginList = null;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getWorkstationName() {
            return workstationName;
        }

        public void setWorkstationName(String workstationName) {
            this.workstationName = workstationName;
        }

        public List<MobileLoginList> getMobileLoginList() {
            return mobileLoginList;
        }

        public void setMobileLoginList(List<MobileLoginList> mobileLoginList) {
            this.mobileLoginList = mobileLoginList;
        }

    }
    public class MobileLoginList {

        @SerializedName("Time")
        @Expose
        private String time;
        @SerializedName("LoginType")
        @Expose
        private String loginType;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getLoginType() {
            return loginType;
        }

        public void setLoginType(String loginType) {
            this.loginType = loginType;
        }

    }
    public class ReturnData {

        @SerializedName("LoginHistory")
        @Expose
        private List<LoginHistory> loginHistory = null;

        public List<LoginHistory> getLoginHistory() {
            return loginHistory;
        }

        public void setLoginHistory(List<LoginHistory> loginHistory) {
            this.loginHistory = loginHistory;
        }

    }
}
