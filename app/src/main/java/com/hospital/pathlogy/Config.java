package com.hospital.pathlogy;

public final class Config {
//Keys for Sharedpreferences

    public static final String SERVER_URL = "http://192.168.43.208:8080/report";
    public static final String LOGIN_URL = SERVER_URL + "/login/validateUser";
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "PathlogyLabPREF";

    //login type
    public static final String USER_TYPE = "usertype";
    public static final String USER_OBJ ="userInfo";
    //This would be used to store the username of current logged in user
    public static final String USERNAME_PREF = "username";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    //keys for report details tags
    public static final String TAG_TESTNAME="testName";
    public static final String TAG_DOCTORNAME="doctorName";
    public static final String TAG_LABCODE="labCode";
    public static final String TAG_AMOUNT="amount";
    public static final String TAG_SUBMITDATE ="submitDate";
    public static final String TAG_REPORTSTATUS="reportStatus";
    public static final String TAG_REPORTID="reportId";

}
