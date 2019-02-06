package at.vcity.androidim;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;


public class Main extends Activity {
	
/**	
	public static File WAOOOOH_DIR, Default_DIR;
	
	
	// Creation du dossier principal de waooooh 
			public static File Create_WAOOOOH_DIR()
				{
					try
						{
							// Get SD Card path & your folder name
							WAOOOOH_DIR = new File(Environment.getExternalStorageDirectory(), "Waooooh");

							// check if exist 
							if (!WAOOOOH_DIR.exists())
								{
									// Create New folder 
									WAOOOOH_DIR.mkdirs();
									Log.i("path", ">>.." + WAOOOOH_DIR);
								}
						} catch (Exception e)
						{
							// TODO: handle exception
							Log.e("Create_WAOOOOH_DIR", "" + e);
						}
					return WAOOOOH_DIR;
				}
			
			***/
	
	
	// Alert Dialog Manager
		//AlertDialogManager alert = new AlertDialogManager();
		
		// Session Manager Class
	SessionManager session;
	
	
	
	
	// creation des variable pour le homeScreen ShortCut
	
	Context mContext=Main.this;
	SharedPreferences appPreferences;
	boolean isAppInstalled = false;
	

	
	@Override
	protected void onCreate(Bundle savedTavis) {
		// TODO Auto-generated method stub
		super.onCreate(savedTavis);
		
		// Verifie si l'application est executee pour la premiere fois puis cree le racourci
		appPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        isAppInstalled = appPreferences.getBoolean("isAppInstalled",false);
        if(isAppInstalled==false){
        	/**
             * creation du racourci
             */
            Intent shortcutIntent = new Intent(getApplicationContext(),Main.class);
            shortcutIntent.setAction(Intent.ACTION_MAIN);
            Intent intent = new Intent();
            intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
            intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Waooooh");
            intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.drawable.ic_launcher));
            intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            getApplicationContext().sendBroadcast(intent);
            /**
             * Rendre preference true
             */
            SharedPreferences.Editor editor = appPreferences.edit();
            editor.putBoolean("isAppInstalled", true);
            editor.commit();
     }



        
		
		 
		
        // Session class instance
        session = new SessionManager(getApplicationContext());
        
        //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        
        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
       
        
        // get user data from session
       // HashMap<String, String> user = session.getUserDetails();
        
        // name
       // String username = user.get(SessionManager.KEY_NAME);
        
        // email
       // String email = user.get(SessionManager.KEY_PASS);
        
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		 
		 
		//setContentView(R.layout.splash);
		
	     Thread logoTimer = new Thread() {
					public void run(){
						try{
							sleep(1000);
							Intent i = new Intent(Main.this, ProcessOneActivity.class);																
							startActivity(i);
							 
							  // session.checkLogin();
							 
							
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				    finally{
				    	finish();
				    	
				      }
					}	
				}; 
				logoTimer.start();
		
		
	}
	
	
}
	