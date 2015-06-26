package com.irina.xcep;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;
import com.irina.xcep.adapters.AdapterListas;
import com.irina.xcep.model.Lista;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class HomeActivity extends Activity {
	
	// Declaración de variables
	ButtonRectangle logout;
	ListView list;
	List<ParseObject> ob;
	AdapterListas adapter;
	ArrayList<Lista> misListas = new ArrayList<Lista>();
	ImageButton addlist;
	// Solicitar usuario actual do Parse.com
	ParseUser currentUser = ParseUser.getCurrentUser();
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		// Convertir currentUser en String
		String struser = currentUser.getUsername().toString();
		TextView txtuser = (TextView) findViewById(R.id.txtuser);
		txtuser.setText(this.getString(R.string.text_login_home_user )+ " "  + struser);
		
		//Botón desconectarse da app
		logout = (ButtonRectangle) findViewById(R.id.logout);
		logout.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// Desconectar o current user
				ParseUser.logOut();
				finish();
			}
		});
		
		//Botón engadir nova lista
		addlist = (ImageButton) findViewById(R.id.add_list);
		addlist.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Ir a páxina engadir unha lista
				Intent intent = new Intent(HomeActivity.this, AddShoppingListActivity.class);
				startActivity(intent);
				
			}
		});
		
		//Listas da compra
		listBuy();
		
	}
	public void listBuy() {
		//Recreamos a lista
		list = (ListView) findViewById(R.id.lista_list);
		adapter = new AdapterListas(HomeActivity.this, misListas);
		list.setAdapter(adapter);
				
		ParseQuery<Lista> query = ParseQuery.getQuery(Lista.class);
		query.include("Market");
		//Filtramos as lista para cada usuario logueado na app
		query.include("User");
		query.whereEqualTo("idUser", currentUser);
		//query.include("Products");
		query.findInBackground(new FindCallback<Lista>() {
			@Override
			public void done(List<Lista> objects, ParseException e) {
				misListas = (ArrayList<Lista>) objects;
				adapter.clear();
				if(misListas != null){
					adapter.addAll(misListas);
				}else{
					Toast.makeText(HomeActivity.this, R.string.empty_list, Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		listBuy();
	}
	
	/* private AbsListView.OnScrollListener mScrollListener = new AbsListView.OnScrollListener() {

	        private int mLastFirstVisibleItem;
	        private boolean mAnimationCalled = false;
			private AdapterView<ListAdapter> mListView;
			private boolean mListStateFlying;
			private  Object mAddQuoteBtn;

	        @Override
	        public void onScrollStateChanged(AbsListView view, int scrollState) {
	            //If we are flying
	            boolean mListStateFlying = AbsListView.OnScrollListener.SCROLL_STATE_FLING == scrollState;
	            mAnimationCalled = mListStateFlying ? mAnimationCalled : false;
	            Log.i("ABDLISTVIEW", "State changed, new state: " + scrollState);

	        }

	        @Override
	        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

	           
				if (mAddQuoteBtn == null) return;


	            if (!mAnimationCalled && mLastFirstVisibleItem < firstVisibleItem) {
	                //Scrolling down
	                ((ButtonFloat) mAddQuoteBtn).hide();
	                mAnimationCalled = true;
	            } else if (!mAnimationCalled && mLastFirstVisibleItem > firstVisibleItem) {
	                //Scrolling up
	                ((Toast) mAddQuoteBtn).show();
	                mAnimationCalled = true;
	            }
	            mLastFirstVisibleItem = firstVisibleItem;



	            if(mListStateFlying || mListView.getCount() == 0) return;

	            boolean greater = false;

	            if(firstVisibleItem == 0 && mListPage != 0){
	                //TODO :  We are up scrolling and we aren't in the first page
	                mListPage = (mListPage<= totalItemCount) ? 0 : mListPage - totalItemCount;
	            }else if(visibleItemCount + firstVisibleItem == totalItemCount){
	                //TODO : We need to check if we have more items in our database, in that case we need to retrieve then
	                // and change the cursor
	                greater = true;
	                mListPage += totalItemCount;
	                queryLocalData(greater);
	            }



	        }
	    };*/
}