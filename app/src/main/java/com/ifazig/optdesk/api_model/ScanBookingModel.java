package com.ifazig.optdesk.api_model;

public class ScanBookingModel {
    String FloorMapBookingId;
    String latitude;
    String longitude;
    String UserId;
    String ActualLatitude;
    String ActualLongitude;

    public String getActualLatitude() {
        return ActualLatitude;
    }

    public void setActualLatitude(String actualLatitude) {
        ActualLatitude = actualLatitude;
    }

    public String getActualLongitude() {
        return ActualLongitude;
    }

    public void setActualLongitude(String actualLongitude) {
        ActualLongitude = actualLongitude;
    }
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }



    public String getFloorMapBookingId() {
        return FloorMapBookingId;
    }

    public void setFloorMapBookingId(String floorMapBookingId) {
        FloorMapBookingId = floorMapBookingId;
    }


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


}
