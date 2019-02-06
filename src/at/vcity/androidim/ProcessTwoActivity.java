package at.vcity.androidim;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProcessTwoActivity extends Activity {

	protected static final int TELEPHONE_LENGTH_SHORT = 8;
	
	EditText phonenumberText;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		
				// TODO Auto-generated method stub
				setContentView(R.layout.process_two);	
				
				//Sound pour un button
				final MediaPlayer buttonSound = MediaPlayer.create(ProcessTwoActivity.this, R.raw.sound_selected);
			
				phonenumberText = (EditText) findViewById(R.id.phonenumber);
				phonenumberText.requestFocus();	
				
				
		
		 // Creation du button suivant du numero de verification 
        Button btn_number_pr = (Button) findViewById(R.id.btn_number);
        
        btn_number_pr.setOnClickListener(new View.OnClickListener() {
        	

			
			@Override
			public void onClick(View vi) {
				
				buttonSound.start();
			
				
				
			if (phonenumberText.length() > 0 &&  phonenumberText.length() == 8)
			{
				// lancement du deuxieme ecran
					

				Intent intent = new Intent(getApplicationContext(), ProcessTroisActivity.class);
				 intent.putExtra("PHONE", phonenumberText.getText().toString());
				
				startActivity(intent);
				ProcessTwoActivity.this.finish();
				
				
			
				
			
				
			
 
			}
				
			else{ Toast.makeText(ProcessTwoActivity.this, R.string.verify_phone, Toast.LENGTH_SHORT).show();}
			}
		});
        
	}

	

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	
	
	
	
	
}
