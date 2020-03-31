package com.ifazig.optdesk.api_model;

public class ScanBookingModel {
    String FloorMapBookingId;
    String latitude;
    String longitude;

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
