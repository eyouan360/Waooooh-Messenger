package at.vcity.androidim;



import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProcessTroisActivity extends Activity {
	
	
protected static final int VERICODE_LENGTH_SHORT = 4;
	
	EditText passwordText;
	EditText usernameText;
	EditText phonenumberText;
	
	TextView testView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	
						setContentView(R.layout.process_trois);	
						
						final MediaPlayer buttonSound = MediaPlayer.create(ProcessTroisActivity.this, R.raw.sound_selected);
			
						passwordText = (EditText) findViewById(R.id.password);
						phonenumberText = (EditText) findViewById(R.id.phonenumber);
						passwordText.requestFocus();	
					
						
						testView = (TextView) findViewById(R.id.testView);
						
						
						
						
				     	Bundle bunble=getIntent().getExtras();
				        if(bunble!=null){
				            //Getting the value stored in the name "NAME"
				            String phone=bunble.getString("PHONE");
				            
				          testView.setText("" + phone);
				          
				          // testView.append(" "+phone);
				          
				          

				        }

									    
	
	 // Creation du button suivant du code de verification 
    Button btn_verifcode_pr = (Button) findViewById(R.id.btn_verifCode);
    
    btn_verifcode_pr.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View vie) {
			buttonSound.start();
			
			
			
			if (passwordText.length() > 0 &&  passwordText.length() == 4)
			{
			
			
			//lancement de l'ecran suivant
				
			Intent inte = new Intent(ProcessTroisActivity.this, Picture_Selection.class);
			Bundle b = new Bundle();
			 b.putString("CODE", passwordText.getText().toString());
			b.putString("PHON", testView.getText().toString());
			inte.putExtra("value", b);
			
			 
			startActivity(inte);
			ProcessTroisActivity.this.finish();
			}
			
			else{ Toast.makeText(ProcessTroisActivity.this, R.string.verify_code, Toast.LENGTH_SHORT).show();}
		}
	});
    
}






@Override
protected void onStart() {
	// TODO Auto-generated method stub
	super.onStart();
}


@Override
protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
}





	
}
