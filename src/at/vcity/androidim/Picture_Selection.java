package at.vcity.androidim;
import java.io.File;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import at.vcity.androidim.FriendList;
import at.vcity.androidim.ProcessTwoActivity;
import at.vcity.androidim.interfaces.IAppManager;
import at.vcity.androidim.services.IMService;

import com.chats.ci_waooooh.util.Utility;

public class Picture_Selection extends Activity implements OnClickListener
	{
	
	
		

		ImageView user_photo;
		LinearLayout get_more;
		
	    protected static final int NOT_CONNECTED_TO_SERVICE = 0;
		protected static final int FILL_BOTH_USERNAME_AND_PASSWORD = 1;
		public static final String AUTHENTICATION_FAILED = "0";
		public static final String FRIEND_LIST = "FRIEND_LIST";
		protected static final int MAKE_SURE_USERNAME_AND_PASSWORD_CORRECT = 2 ;
		protected static final int NOT_CONNECTED_TO_NETWORK = 3;
		private String usernameText;
	    private String passwordText;
	    private Button cancelButton;
	    private IAppManager imService;
	    public static final int SIGN_UP_ID = Menu.FIRST;
	    public static final int EXIT_APP_ID = Menu.FIRST + 1;
	    TextView textcode;
	    TextView textuser;
	    EditText phonenumberText;
	    EditText pseudo;
	    private ServiceConnection mConnection = new ServiceConnection() {
	        public void onServiceConnected(ComponentName className, IBinder service) {
	            // This is called when the connection with the service has been
	            // established, giving us the service object we can use to
	            // interact with the service.  Because we have bound to a explicit
	            // service that we know is running in our own process, we can
	            // cast its IBinder to a concrete class and directly access it.
	            imService = ((IMService.IMBinder)service).getService();  
	            
	            if (imService.isUserAuthenticated() == true)
	            {
	            	Intent i = new Intent(Picture_Selection.this, FriendList.class);																
					startActivity(i);
					Picture_Selection.this.finish();
	            }
	        }

	        public void onServiceDisconnected(ComponentName className) {
	            // This is called when the connection with the service has been
	            // unexpectedly disconnected -- that is, its process crashed.
	            // Because it is running in our same process, we should never
	            // see this happen.
	        	imService = null;
	            Toast.makeText(Picture_Selection.this, R.string.local_service_stopped,
	                    Toast.LENGTH_SHORT).show();
	        }
	    };
	    
	    
	 // Alert Dialog Manager
		AlertDialogManager alert = new AlertDialogManager();
		
		// Session Manager Class
		SessionManager session;

		


		@Override
		protected void onCreate(Bundle savedInstanceState)
			{
				// TODO Auto-generated method stub
				super.onCreate(savedInstanceState);
				
				
				
				
				
				
		    	startService(new Intent(Picture_Selection.this,  IMService.class));			
				
				
				setContentView(R.layout.modif_profile_layout);
				
				final MediaPlayer buttonSound = MediaPlayer.create(Picture_Selection.this, R.raw.sound_selected);
				
				Button loginButton = (Button) findViewById(R.id.btn_finPro);
		        cancelButton = (Button) findViewById(R.id.cancel_login);
		       // usernameText = (EditText) findViewById(R.id.userName);
		       // passwordText = (EditText) findViewById(R.id.password); 
				user_photo = (ImageView) findViewById(R.id.user_photo);
				get_more = (LinearLayout) findViewById(R.id.account_image_wrapper);
				user_photo.setOnClickListener(this);
				get_more.setOnClickListener(this);
				
				
				
				textcode = (TextView) findViewById(R.id.textcode);
				textuser = (TextView) findViewById(R.id.textuser);
				//phonenumberText = (EditText) findViewById(R.id.textuser);
				pseudo = (EditText) findViewById(R.id.pseudo);
								
								
								Intent inte = getIntent();
								Bundle b = inte.getBundleExtra("value");
								             String code=b.getString("CODE");
								            String phone=b.getString("PHON");
								            
									          textuser.setText("" + phone);
								            
								          textcode.setText("" + code);
								          
								          // testView.append(" "+phone);
				
				
								          
			
								          
								          
								       
								          // Session Manager
								          session = new SessionManager(getApplicationContext()); 
								          
								          //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
								          
								          
			usernameText = textuser.getText().toString();
			passwordText = textcode.getText().toString();
			
			
			
		
		 // Creation du button modifier
     /**   Button btn_continue_mod = (Button) findViewById(R.id.btn_finPro);
        
        btn_continue_mod.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				
				// lancement de la liste d'amis activity
				Intent i = new Intent(Picture_Selection.this, FriendList.class);																
				startActivity(i);
				
			}
		});**/
			
			
        
        
        
        // Creation du button Fin
        Button btn_continue_fin = (Button) findViewById(R.id.anu_button);
        
        btn_continue_fin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View viie) {
				buttonSound.start();
				
				// lancement du deuxieme ecran
				Intent i = new Intent(getApplicationContext(), ProcessTwoActivity.class);
				startActivity(i);
			}
		});
        
			
		
		loginButton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) 
			{	
				buttonSound.start();
				if (imService == null) {
					Toast.makeText(getApplicationContext(),R.string.not_connected_to_service, Toast.LENGTH_LONG).show();
					//showDialog(NOT_CONNECTED_TO_SERVICE);
					return;
				}
				else if (imService.isNetworkConnected() == false)
				{
					Toast.makeText(getApplicationContext(),R.string.not_connected_to_network, Toast.LENGTH_LONG).show();
					//showDialog(NOT_CONNECTED_TO_NETWORK);
					
				}
				else if (usernameText.length() > 0 && 
					passwordText.length() > 0)
				{
					
					Thread loginThread = new Thread(){
						private Handler handler = new Handler();
						@Override
						public void run() {
							String result = null;
							try {
								result = imService.authenticateUser(usernameText.toString().toString(), passwordText.toString().toString());
							} catch (UnsupportedEncodingException e) {
								
								e.printStackTrace();
							}
							if (result == null || result.equals(AUTHENTICATION_FAILED)) 
							{
								/*
								 * Authenticatin failed, inform the user
								 */
								handler.post(new Runnable(){
									public void run() {	
										Toast.makeText(getApplicationContext(),R.string.make_sure_username_and_password_correct, Toast.LENGTH_LONG).show();

										//showDialog(MAKE_SURE_USERNAME_AND_PASSWORD_CORRECT);
									}									
								});
														
							}
							else {
							
								/*
								 * if result not equal to authentication failed,
								 * result is equal to friend list of the user
								 */		
								handler.post(new Runnable(){
									public void run() {	
										
										
										Intent i = new Intent(Picture_Selection.this, FriendList.class);												
										//i.putExtra(FRIEND_LIST, result);						
										startActivity(i);

										// Creating user login session
										// For testing i am stroing name, email as follow
										// Use user real data
										session.createLoginSession("Android Hive", "anroidhiv@gmail.com");
										
										Picture_Selection.this.finish();
										
									}	
									
								});
								finish();
								
							}
							
						}
					};
					loginThread.start();
					
				}
				else {
					/*
					 * Username or Password is not filled, alert the user
					 */
					Toast.makeText(getApplicationContext(),R.string.fill_both_username_and_password, Toast.LENGTH_LONG).show();
					//showDialog(FILL_BOTH_USERNAME_AND_PASSWORD);
				}				
			}       	
        });
        
       
        
        
    }

	@Override
	protected void onPause() 
	{
		unbindService(mConnection);
		super.onPause();
	}

	@Override
	protected void onResume() 
	{		
		bindService(new Intent(Picture_Selection.this, IMService.class), mConnection , Context.BIND_AUTO_CREATE);
	    		
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {		
		boolean result = super.onCreateOptionsMenu(menu);
		
		 menu.add(0, SIGN_UP_ID, 0, R.string.sign_up);
		 menu.add(0, EXIT_APP_ID, 0, R.string.exit_application);


		return result;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
	    
		switch(item.getItemId()) 
	    {
	    	case SIGN_UP_ID:
	    		Intent i = new Intent(Picture_Selection.this, SignUp.class);
	    		startActivity(i);
	    		return true;
	    	case EXIT_APP_ID:
	    		cancelButton.performClick();
	    		return true;
	    }
	       
	    return super.onMenuItemSelected(featureId, item);
	}


		@Override
		public void onClick(View v)
		
			{
			
			
				// TODO Auto-generated method stub

				if (v.getId() == R.id.user_photo)
					{
						// call dialog to picture mode camera / gallery
						Image_Picker_Dialog();
					}
				else if (v.getId() == R.id.account_image_wrapper)
					{
						Intent intent = new Intent(Picture_Selection.this, FriendList.class );
						startActivity(intent);
					}

			}

		public void Image_Picker_Dialog()
			{

				AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
				myAlertDialog.setTitle("Pictures Option");
				myAlertDialog.setMessage("Select Picture Mode");

				myAlertDialog.setPositiveButton("Gallery", new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface arg0, int arg1)
							{
								Utility.pictureActionIntent = new Intent(Intent.ACTION_GET_CONTENT, null);
								Utility.pictureActionIntent.setType("image/*");
								Utility.pictureActionIntent.putExtra("return-data", true);
								startActivityForResult(Utility.pictureActionIntent, Utility.GALLERY_PICTURE);
							}
					});

				myAlertDialog.setNegativeButton("Camera", new DialogInterface.OnClickListener()
					{
						public void onClick(DialogInterface arg0, int arg1)
							{
								Utility.pictureActionIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
								startActivityForResult(Utility.pictureActionIntent, Utility.CAMERA_PICTURE);
							}
					});
				myAlertDialog.show();

			}

		// After the selection of image you will retun on the main activity with bitmap image 
		protected void onActivityResult(int requestCode, int resultCode, Intent data)
			{
				super.onActivityResult(requestCode, resultCode, data);
				if (requestCode == Utility.GALLERY_PICTURE)
					{
						// data contains result 
						// Do some task 
						Image_Selecting_Task(data);
					} else if (requestCode == Utility.CAMERA_PICTURE)
					{
						// Do some task
						Image_Selecting_Task(data);
					}
			}

		public void Image_Selecting_Task(Intent data)
			{
				try
					{
						Utility.uri = data.getData();		
						if (Utility.uri != null)
							{
								// User had pick an image.
								Cursor cursor = getContentResolver().query(Utility.uri, new String[]
									{ android.provider.MediaStore.Images.ImageColumns.DATA }, null, null, null);
								cursor.moveToFirst();
								// Link to the image
								final String imageFilePath = cursor.getString(0);

								//Assign string path to File
								Utility.Default_DIR = new File(imageFilePath);

								// Create new dir MY_IMAGES_DIR if not created and copy image into that dir and store that image path in valid_photo
								Utility.Create_MY_IMAGES_DIR();

								// Copy your image 
								Utility.copyFile(Utility.Default_DIR, Utility.MY_IMG_DIR);

								// Get new image path and decode it
								Bitmap b = Utility.decodeFile(Utility.Paste_Target_Location);

								b = Bitmap.createScaledBitmap(b, 150, 150, true);

								//set your selected image in image view
								user_photo.setImageBitmap(b);
								cursor.close();
 
							} else
							{
								Toast toast = Toast.makeText(this, "Desolé!!! Vous avez rien selectionné.", Toast.LENGTH_LONG);
								toast.show();
							}
					} catch (Exception e)
					{
						// you get this when you will not select any single image 
						Log.e("onActivityResult", "" + e);

					}
			}

	}
