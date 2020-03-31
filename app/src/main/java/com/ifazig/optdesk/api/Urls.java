package com.ifazig.optdesk.api;

class Urls {
    //static final String BASE_URL_STAGING = "https://ifazig.com/OptDeskAPI/api/";// live  url
    static final String BASE_URL_STAGING = "http://uatifazig.com/OptDeskAPI/api/";// UAT  url

    // api urls

    // User details
    static final String LOGIN = "GetLoginDetail";
    static final String FORGOTPASSWORD = "ForgotPassword";



    //Complaint details
    static final String LOCATIONDETAILSBYROLEID = "GetLocationDetailsByRoleId";
    static final String BUILDINGDETAILS = "GetBuildingDetails";
    static final String FLOORDETAILS = "GetFloorDetails";
    static final String WINGSDETAILS = "GetWingsDetails";
    static final String SETTINGDETAILS = "SettingDetails";
    static final String VALIDATEWORKSTATION = "MultipleTimeValidation";
    static final String BOOKINGHISTORY = "BookingHistory";
    static final String CANCELBOOKING = "CancelBooking";
    static final String BOOKINGWORKSTATION = "MultipleSave";
    static final String CONFIRMATIONBOOKING = "ConfirmationBooking";
    static final String SCANBOOKING = "ScanBooking";
    static final String WORKSTATIONLOGOUT = "MobileLoginDetails";


    // Post Data create new complaint data
    static final String INSERTUPDATECOMPLAINT = "InsertUpdateComplaint";
    static final String INSERTFEEDBACK = "InsertFeedback";
    // image upload multipart
    static final String DOCUMENTUPLOAD = "DocumentUpload";


}
