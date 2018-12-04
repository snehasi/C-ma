package com.donut.cma;

public class Constants {
    public static final int SPLASH_TIMEOUT = 1000;
    public static final String PREF_FILE = "preferences";

    public static final String SERVER = "http://192.168.32.172:1234";
    public static final String CROPS_URL = SERVER + "/crops";

    public static final Boolean DEBUG = true;
    public static final Boolean CITY_SELECT_PLACES= false;

    public class PREF_KEY {
        public static final String setup_done = "setup_done";
        public static final String user_city = "user_city";
        public static final String user_crops = "user_crops";
    }

    // Ritu
    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;
    public static final String PACKAGE_NAME = "com.google.android.gms.location.sample.locationaddress";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_DATA_EXTRA";
}
