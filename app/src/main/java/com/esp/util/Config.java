package com.esp.util;

import android.os.Environment;

public class Config {

	public static String TAG = "WorkJam";
	public static String DB_NAME = "LanceCamper.db";
	// Create a directory in SD CARD
	public static String APP_HOME = Environment.getExternalStorageDirectory().getPath() + "/" + TAG;

	// A directory to store logs
	public static String DIR_LOG = APP_HOME + "/log";
	// preference file name
	public static final String PREF_FILE = TAG + "_PREF";
	

	/* API */
    public static String API_KEY = "JSON_2014";
    public static String API_VERSION = "1.0";
	
	
//	public static String HOST = "http://esprit-apps.com/dev/cmm";
	
//	public static String API_SYNC = HOST + " /sync.php";
	public static String API_LIST = "http://api.androidhive.info/contacts/";
	
	//API TAGS
	//For each API we must have a TAG
	public static String TAG_SYNC = "API_SYNC";
	public static String TAG_LIST = "API_LIST";
	/*
	 * Error and Warnings
	 */
	public static String ERROR_NETWORK = "ERROR_NETWORK";
	public static String ERROR_API = "ERROR_API";
	public static String ERROR_UNKNOWN = "ERROR_UNKNOWN";

	public static int API_SUCCESS = 0;
	public static int API_FAIL = 1;

	// connection timeout is set to 20 seconds
	public static int TIMEOUT_CONNECTION = 20000;

	// SOCKET TIMEOUT IS SET TO 30 SECONDS
	public static int TIMEOUT_SOCKET = 30000;
	
	/*PREFERENCE VARIABLES*/
	public static String PREF_USERID = "PREF_USERID";
	

	/*
	 * Cookie and SESSION
	 */
	public static String PREF_SESSION_COOKIE = "sessionid";
	public static String SET_COOKIE_KEY = "Set-Cookie";
	public static String COOKIE_KEY = "Cookie";
	public static String SESSION_COOKIE = "sessionid";

	
}	

