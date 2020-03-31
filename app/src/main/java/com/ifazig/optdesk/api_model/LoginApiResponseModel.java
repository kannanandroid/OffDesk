package com.ifazig.optdesk.api_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LoginApiResponseModel {
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

        @SerializedName("UserDetails")
        @Expose
        private List<UserDetail> userDetails = null;
        @SerializedName("CompanyDetails")
        @Expose
        private List<CompanyDetail> companyDetails = null;
        @SerializedName("LocationDetails")
        @Expose
        private List<LocationDetail> locationDetails = null;
        @SerializedName("BuildingDetails")
        @Expose
        private List<BuildingDetail> buildingDetails = null;
        @SerializedName("FloorDetails")
        @Expose
        private List<FloorDetail> floorDetails = null;
        @SerializedName("WingsDetails")
        @Expose
        private List<WingsDetail> wingsDetails = null;
        @SerializedName("WorkStationDetails")
        @Expose
        private List<WorkStationDetail> workStationDetails = null;

        public List<UserDetail> getUserDetails() {
            return userDetails;
        }

        public void setUserDetails(List<UserDetail> userDetails) {
            this.userDetails = userDetails;
        }

        public List<CompanyDetail> getCompanyDetails() {
            return companyDetails;
        }

        public void setCompanyDetails(List<CompanyDetail> companyDetails) {
            this.companyDetails = companyDetails;
        }

        public List<LocationDetail> getLocationDetails() {
            return locationDetails;
        }

        public void setLocationDetails(List<LocationDetail> locationDetails) {
            this.locationDetails = locationDetails;
        }

        public List<BuildingDetail> getBuildingDetails() {
            return buildingDetails;
        }

        public void setBuildingDetails(List<BuildingDetail> buildingDetails) {
            this.buildingDetails = buildingDetails;
        }

        public List<FloorDetail> getFloorDetails() {
            return floorDetails;
        }

        public void setFloorDetails(List<FloorDetail> floorDetails) {
            this.floorDetails = floorDetails;
        }

        public List<WingsDetail> getWingsDetails() {
            return wingsDetails;
        }

        public void setWingsDetails(List<WingsDetail> wingsDetails) {
            this.wingsDetails = wingsDetails;
        }

        public List<WorkStationDetail> getWorkStationDetails() {
            return workStationDetails;
        }

        public void setWorkStationDetails(List<WorkStationDetail> workStationDetails) {
            this.workStationDetails = workStationDetails;
        }

    }

    public class BuildingDetail {

        @SerializedName("BuildingID")
        @Expose
        private Integer buildingID;
        @SerializedName("BuildingName")
        @Expose
        private String buildingName;

        public Integer getBuildingID() {
            return buildingID;
        }

        public void setBuildingID(Integer buildingID) {
            this.buildingID = buildingID;
        }

        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

    }

    public class CompanyDetail {

        @SerializedName("CUID")
        @Expose
        private Integer cUID;
        @SerializedName("CompanyID")
        @Expose
        private Integer companyID;
        @SerializedName("CompanyName")
        @Expose
        private String companyName;
        @SerializedName("CountryId")
        @Expose
        private String countryId;

        public Integer getCUID() {
            return cUID;
        }

        public void setCUID(Integer cUID) {
            this.cUID = cUID;
        }

        public Integer getCompanyID() {
            return companyID;
        }

        public void setCompanyID(Integer companyID) {
            this.companyID = companyID;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }

    }

    public class FloorDetail {

        @SerializedName("FloorID")
        @Expose
        private Integer floorID;
        @SerializedName("FloorName")
        @Expose
        private String floorName;

        public Integer getFloorID() {
            return floorID;
        }

        public void setFloorID(Integer floorID) {
            this.floorID = floorID;
        }

        public String getFloorName() {
            return floorName;
        }

        public void setFloorName(String floorName) {
            this.floorName = floorName;
        }

    }

    public class LocationDetail {

        @SerializedName("LocationID")
        @Expose
        private Integer locationID;
        @SerializedName("LocationName")
        @Expose
        private String locationName;

        public Integer getLocationID() {
            return locationID;
        }

        public void setLocationID(Integer locationID) {
            this.locationID = locationID;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

    }

    public class UserDetail {

        @SerializedName("FromFramework")
        @Expose
        private Boolean fromFramework;
        @SerializedName("CountryID")
        @Expose
        private Integer countryID;
        @SerializedName("CountryIDCurrent")
        @Expose
        private Integer countryIDCurrent;
        @SerializedName("RoleID")
        @Expose
        private Integer roleID;
        @SerializedName("UserID")
        @Expose
        private Integer userID;
        @SerializedName("CompanyIDUser")
        @Expose
        private Integer companyIDUser;
        @SerializedName("LocationIDUser")
        @Expose
        private Integer locationIDUser;
        @SerializedName("CompanyName")
        @Expose
        private String companyName;
        @SerializedName("CompanyIDCurrent")
        @Expose
        private Integer companyIDCurrent;
        @SerializedName("LocationName")
        @Expose
        private String locationName;
        @SerializedName("LocationIDCurrent")
        @Expose
        private Integer locationIDCurrent;
        @SerializedName("GroupID")
        @Expose
        private Integer groupID;
        @SerializedName("GroupIDCurrent")
        @Expose
        private Integer groupIDCurrent;
        @SerializedName("LanguageID")
        @Expose
        private Integer languageID;
        @SerializedName("UserFirstName")
        @Expose
        private String userFirstName;
        @SerializedName("UserLastName")
        @Expose
        private String userLastName;
        @SerializedName("ThemeFolderPath")
        @Expose
        private String themeFolderPath;
        @SerializedName("CompLogo")
        @Expose
        private String compLogo;
        @SerializedName("Announcement")
        @Expose
        private String announcement;

        public Integer getIsWorkstationLogin() {
            return IsWorkstationLogin;
        }

        public void setIsWorkstationLogin(Integer isWorkstationLogin) {
            IsWorkstationLogin = isWorkstationLogin;
        }

        @SerializedName("IsWorkstationLogin")
        @Expose
        private Integer IsWorkstationLogin;

        public Boolean getFromFramework() {
            return fromFramework;
        }

        public void setFromFramework(Boolean fromFramework) {
            this.fromFramework = fromFramework;
        }

        public Integer getCountryID() {
            return countryID;
        }

        public void setCountryID(Integer countryID) {
            this.countryID = countryID;
        }

        public Integer getCountryIDCurrent() {
            return countryIDCurrent;
        }

        public void setCountryIDCurrent(Integer countryIDCurrent) {
            this.countryIDCurrent = countryIDCurrent;
        }

        public Integer getRoleID() {
            return roleID;
        }

        public void setRoleID(Integer roleID) {
            this.roleID = roleID;
        }

        public Integer getUserID() {
            return userID;
        }

        public void setUserID(Integer userID) {
            this.userID = userID;
        }

        public Integer getCompanyIDUser() {
            return companyIDUser;
        }

        public void setCompanyIDUser(Integer companyIDUser) {
            this.companyIDUser = companyIDUser;
        }

        public Integer getLocationIDUser() {
            return locationIDUser;
        }

        public void setLocationIDUser(Integer locationIDUser) {
            this.locationIDUser = locationIDUser;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public Integer getCompanyIDCurrent() {
            return companyIDCurrent;
        }

        public void setCompanyIDCurrent(Integer companyIDCurrent) {
            this.companyIDCurrent = companyIDCurrent;
        }

        public String getLocationName() {
            return locationName;
        }

        public void setLocationName(String locationName) {
            this.locationName = locationName;
        }

        public Integer getLocationIDCurrent() {
            return locationIDCurrent;
        }

        public void setLocationIDCurrent(Integer locationIDCurrent) {
            this.locationIDCurrent = locationIDCurrent;
        }

        public Integer getGroupID() {
            return groupID;
        }

        public void setGroupID(Integer groupID) {
            this.groupID = groupID;
        }

        public Integer getGroupIDCurrent() {
            return groupIDCurrent;
        }

        public void setGroupIDCurrent(Integer groupIDCurrent) {
            this.groupIDCurrent = groupIDCurrent;
        }

        public Integer getLanguageID() {
            return languageID;
        }

        public void setLanguageID(Integer languageID) {
            this.languageID = languageID;
        }

        public String getUserFirstName() {
            return userFirstName;
        }

        public void setUserFirstName(String userFirstName) {
            this.userFirstName = userFirstName;
        }

        public String getUserLastName() {
            return userLastName;
        }

        public void setUserLastName(String userLastName) {
            this.userLastName = userLastName;
        }

        public String getThemeFolderPath() {
            return themeFolderPath;
        }

        public void setThemeFolderPath(String themeFolderPath) {
            this.themeFolderPath = themeFolderPath;
        }

        public String getCompLogo() {
            return compLogo;
        }

        public void setCompLogo(String compLogo) {
            this.compLogo = compLogo;
        }

        public String getAnnouncement() {
            return announcement;
        }

        public void setAnnouncement(String announcement) {
            this.announcement = announcement;
        }

    }

    public class WingsDetail {

        @SerializedName("WingID")
        @Expose
        private Integer wingID;
        @SerializedName("WingName")
        @Expose
        private String wingName;

        public Integer getWingID() {
            return wingID;
        }

        public void setWingID(Integer wingID) {
            this.wingID = wingID;
        }

        public String getWingName() {
            return wingName;
        }

        public void setWingName(String wingName) {
            this.wingName = wingName;
        }

    }

    public class WorkStationDetail {

        @SerializedName("WorkStationId")
        @Expose
        private Integer workStationId;
        @SerializedName("AmenityId")
        @Expose
        private String amenityId;
        @SerializedName("CompanyId")
        @Expose
        private Integer companyId;
        @SerializedName("LocationId")
        @Expose
        private Integer locationId;
        @SerializedName("BuildingId")
        @Expose
        private Integer buildingId;
        @SerializedName("FloorID")
        @Expose
        private Integer floorID;
        @SerializedName("WingId")
        @Expose
        private Integer wingId;
        @SerializedName("WorkStationName")
        @Expose
        private String workStationName;
        @SerializedName("IsActive")
        @Expose
        private Boolean isActive;
        @SerializedName("IsDelete")
        @Expose
        private Boolean isDelete;
        @SerializedName("CuID")
        @Expose
        private Integer cuID;
        @SerializedName("CDate")
        @Expose
        private String cDate;
        @SerializedName("MuID")
        @Expose
        private Object muID;
        @SerializedName("MDate")
        @Expose
        private Object mDate;
        @SerializedName("QrCode")
        @Expose
        private String qrCode;
        @SerializedName("QrId")
        @Expose
        private String qrId;
        @SerializedName("X1")
        @Expose
        private Integer x1;
        @SerializedName("Y1")
        @Expose
        private Integer y1;
        @SerializedName("X2")
        @Expose
        private Integer x2;
        @SerializedName("Y2")
        @Expose
        private Integer y2;

        public Integer getWorkStationId() {
            return workStationId;
        }

        public void setWorkStationId(Integer workStationId) {
            this.workStationId = workStationId;
        }

        public String getAmenityId() {
            return amenityId;
        }

        public void setAmenityId(String amenityId) {
            this.amenityId = amenityId;
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

        public Integer getFloorID() {
            return floorID;
        }

        public void setFloorID(Integer floorID) {
            this.floorID = floorID;
        }

        public Integer getWingId() {
            return wingId;
        }

        public void setWingId(Integer wingId) {
            this.wingId = wingId;
        }

        public String getWorkStationName() {
            return workStationName;
        }

        public void setWorkStationName(String workStationName) {
            this.workStationName = workStationName;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

        public Boolean getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(Boolean isDelete) {
            this.isDelete = isDelete;
        }

        public Integer getCuID() {
            return cuID;
        }

        public void setCuID(Integer cuID) {
            this.cuID = cuID;
        }

        public String getCDate() {
            return cDate;
        }

        public void setCDate(String cDate) {
            this.cDate = cDate;
        }

        public Object getMuID() {
            return muID;
        }

        public void setMuID(Object muID) {
            this.muID = muID;
        }

        public Object getMDate() {
            return mDate;
        }

        public void setMDate(Object mDate) {
            this.mDate = mDate;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }

        public String getQrId() {
            return qrId;
        }

        public void setQrId(String qrId) {
            this.qrId = qrId;
        }

        public Integer getX1() {
            return x1;
        }

        public void setX1(Integer x1) {
            this.x1 = x1;
        }

        public Integer getY1() {
            return y1;
        }

        public void setY1(Integer y1) {
            this.y1 = y1;
        }

        public Integer getX2() {
            return x2;
        }

        public void setX2(Integer x2) {
            this.x2 = x2;
        }

        public Integer getY2() {
            return y2;
        }

        public void setY2(Integer y2) {
            this.y2 = y2;
        }

    }
}


