package at.vcity.androidim;




import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;


public class ProcessOneActivity extends Activity {

	
	
	
	// Session Manager Class
	//SessionManager session;
	

	
	private  CheckBox btn_accepter;

	 

	
	@Override
	protected void onCreate(Bundle savedTavis) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedTavis);
		
	

		// Session class instance
	   // session = new SessionManager(ProcessOneActivity.this);
	    
		/*requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				
				WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
		
		 
		//session.checkLogin();
	        
		
		setContentView(R.layout.process_one);
		
		//Sound pour un button
		final MediaPlayer buttonSound = MediaPlayer.create(ProcessOneActivity.this, R.raw.sound_selected);
		

		addListenerOnBtnAccepter();
		
		
		
		

		 
		 
		 // Creation du button continue
	        Button btn_continue_pr = (Button) findViewById(R.id.btn_continue);
	        
	        btn_continue_pr.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					buttonSound.start();
					
					if (((CheckBox) btn_accepter).isChecked()){
					
						// lancement du deuxieme ecran
						Intent i = new Intent(ProcessOneActivity.this, ProcessTwoActivity.class);																
						startActivity(i);
						ProcessOneActivity.this.finish();
					}
					
					else {	Toast.makeText(ProcessOneActivity.this, R.string.button_accepter, Toast.LENGTH_LONG).show();}
					
				}
				
				
			});
	        
	        
	        
	        
		 
	  
	}
	
	public void addListenerOnBtnAccepter() {
		
		btn_accepter = (CheckBox) findViewById(R.id.btn_accepter);
		
		btn_accepter.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
					
				
				
			}
		/**	
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Toast.makeText(ProcessOneActivity.this, "Condition Accepter", Toast.LENGTH_LONG).show();
				// TODO Auto-generated method stub
				
			}**/

			
		});
	}
	

	
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
	}
	

}