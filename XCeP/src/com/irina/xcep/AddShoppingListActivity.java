package com.irina.xcep;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.gc.materialdesign.views.ButtonRectangle;

public class AddShoppingListActivity extends Activity{
	
	ButtonRectangle btncancel;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.activity_add_shopping_list);
		getActionBar().setTitle(R.string.title_action_bar_add_shopping_list);
		
		btncancel = (ButtonRectangle) findViewById(R.id.cancelar_new_list);
		btncancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
	}


	

}
