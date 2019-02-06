package at.vcity.androidim;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import at.vcity.androidim.types.FriendInfo;

public class SessionManager {
	// Shared Preferences
	SharedPreferences pref;
	
	// Editor for Shared preferences
	Editor editor;
	
	// Context
	Context _context;
	
	// Shared pref mode
	int PRIVATE_MODE = 0;
	
	// Sharedpref file name
	private static final String PREF_NAME = "AndroidHivePref";
	
	// All Shared Preferences Keys
	private static final String IS_LOGIN = "IsLoggedIn";
	
	// User name (make variable public to access from outside)
	public static final String KEY_NAME = "usernameText";
	
	// Email address (make variable public to access from outside)
	public static final String KEY_PASS = "passwordText";
	
	// Constructor
	public SessionManager(Context context){
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}
	
	
	
	
	/**
	 * Create login session
	 * */
	public void createLoginSession(String usernameText, String passwordText){
		// Storing login value as TRUE
		editor.putBoolean(IS_LOGIN, true);
		
		// Storing name in pref
		editor.putString(KEY_NAME, usernameText);
		
		// Storing email in pref
		editor.putString(KEY_PASS, passwordText);
		
		// commit changes
		editor.commit();
	}	
	
	protected void updateData(FriendInfo[] friends, Object object) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Check login method wil check user login status
	 * If false it will redirect user to login page
	 * Else won't do anything
	 * */
	public void checkLogin(){
		// Check login status
		if(!this.isLoggedIn()){
			// user is not logged in redirect him to Login Activity
			Intent i = new Intent(_context, ProcessOneActivity.class);
			// Closing all the Activities
			i.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
			
			// Add new Flag to start new Activity
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			
			// Staring Login Activity
			_context.startActivity(i);
		}
		else {
			
			// user is logged in redirect him to Login Activity
		
						Intent i = new Intent(_context, FriendList.class);
						
						// Closing all the Activities
						i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
						
						// Add new Flag to start new Activity
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						
						// Staring Login Activity
						_context.startActivity(i);
		}
		
	}
	
	
	
	
	/**
	 * Get stored session data
	 * */
	public HashMap<String, String> getUserDetails(){
		HashMap<String, String> user = new HashMap<String, String>();
		// user name
		user.put(KEY_NAME, pref.getString(KEY_NAME, null));
		
		// user email id
		user.put(KEY_PASS, pref.getString(KEY_PASS, null));
		
		// return user
		return user;
	}
	
	/**
	 * Clear session details
	 * */
	
	
	/**
	 * Quick check for login
	 * **/
	// Get Login State
	public boolean isLoggedIn(){
		return pref.getBoolean(IS_LOGIN, false);
	}
}
