package org.feedreader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InformationActivity extends Activity {

	private Button backButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		 
		backButton = (Button) findViewById(R.id.back_button);
		
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	
	//startService(svc);
}

@Override
protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
	

}
}
