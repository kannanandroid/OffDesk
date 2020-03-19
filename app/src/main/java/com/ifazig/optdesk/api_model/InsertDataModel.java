package com.ifazig.optdesk.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InsertDataModel {

    @SerializedName("WingId")
    @Expose
    private Integer wingId;
    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("RoleId")
    @Expose
    private Integer roleId;
    @SerializedName("CompanyId")
    @Expose
    private Integer companyId;
    @SerializedName("LocationId")
    @Expose
    private Integer locationId;
    @SerializedName("BuildingId")
    @Expose
    private Integer buildingId;
    @SerializedName("FloorId")
    @Expose
    private Integer floorId;

    @SerializedName("Book")
    @Expose
    private List<Book> book = null;

    public Integer getWingId() {
        return wingId;
    }

    public void setWingId(Integer wingId) {
        this.wingId = wingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public static class Book {

        @SerializedName("WorkstationId")
        @Expose
        private Integer workstationId;
        @SerializedName("StartDate")
        @Expose
        private String startDate;
        @SerializedName("StartTime")
        @Expose
        private String startTime;
        @SerializedName("EndTime")
        @Expose
        private String endTime;

        public Integer getWorkstationId() {
            return workstationId;
        }

        public void setWorkstationId(Integer workstationId) {
            this.workstationId = workstationId;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
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
}



