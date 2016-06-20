package com.giozom.travels.domain;

/**
 * Created by garsenius on 20/06/2016.
 * Domain object for TravelsAccount
 */
public class TravelsAccount {


    private static final String TRAVELS_USERNAME_KEY = "TRAVELS_USERNAME";
    private static final String TRAVELS_PASSWORD_KEY = "TRAVELS_PASSWORD_KEY";
    private static final String TRAVELS_URL_KEY = "TRAVELS_URL";


    public final String userName;
    public final String userPassword;
    public final String travels_url;



    //constructor
    public TravelsAccount(){
        userName = System.getProperty(TRAVELS_USERNAME_KEY, "user@phptravels.com");
        userPassword = System.getProperty(TRAVELS_PASSWORD_KEY, "demouser");
        travels_url = System.getProperty(TRAVELS_URL_KEY, "http://www.phptravels.net");
        System.out.println(travels_url);


    }


}
