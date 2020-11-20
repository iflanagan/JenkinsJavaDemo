package com.IanFlanagan;

import com.rollbar.api.payload.data.Level;

import java.net.UnknownHostException;

public class MyConfiguration {

    // Person Provider info
    public static final String id = "84935784903";
    public static final String email = "ian.flanagan@rollbar.com";
    public static final String username = "iflanagan";


    public static final String idiv = "24935784903";
    public static final String emailiv = "barrytest@rollbar.com";
    public static final String usernameiv = "Barry";

    public static final String idfb = "14935784903";
    public static final String emailfb  = "johntest@rollbar.com";
    public static final String usernamefb = "John";


    // Rollbar Specific info
    public static final String env = "PROD";
   // public static final String version = "1.0.1";
    public static final String rollbarAccessToken = "<yourtoken>";

    // Server Provider info
    public static final String myRoot = "com.IanFlanagan";
    public static final String myHost = "Ians-MacBook-Pro.local"; // old localhost new Utils.GetHostName(); Ians-MacBook-Pro.local
    public static final String myBranch = "master";
    public static final String myCodeVersion = "1.0.1";

}
