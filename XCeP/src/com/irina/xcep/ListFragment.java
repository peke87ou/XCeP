package com.irina.xcep;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFloat;

public class ListFragment extends Fragment {
	
	// Declaración de variables
//	ButtonRectangle logout;
//	ListView list;
//	List<ParseObject> ob;
//	AdapterListas adapter;
//	ArrayList<Lista> misListas = new ArrayList<Lista>();
//	ImageButton addlist;
//	// Solicitar usuario actual do Parse.com
//	ParseUser currentUser = ParseUser.getCurrentUser();
	
	private TabHost tabHost;
	
	public static ListFragment newInstance (int Index){
		ListFragment fragment = new ListFragment();
		Bundle args = new Bundle();
		
		args.putInt("Index", Index);
		
		fragment.setArguments(args);
		
		return fragment;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		RelativeLayout home = (RelativeLayout) inflater.inflate(R.layout.fragment_list, container, false);
		
		Resources res = getResources();
		tabHost = (TabHost) home.findViewById(android.R.id.tabhost);
		tabHost.setup();
		
		
		TabHost.TabSpec spec=tabHost.newTabSpec("Lista da compra");
		spec.setContent(R.id.tab_list_buy);
		spec.setIndicator("",res.getDrawable(R.drawable.notebook));
		tabHost.addTab(spec);
		 
		spec=tabHost.newTabSpec("Catálogo");
		spec.setContent(R.id.tab_catalog);
		spec.setIndicator("",res.getDrawable(R.drawable.server));
		tabHost.addTab(spec);

		spec=tabHost.newTabSpec("Escaner");
		spec.setContent(R.id.tab_scan);
		spec.setIndicator("",res.getDrawable(R.drawable.camera));
		tabHost.addTab(spec);
		
		tabHost.setCurrentTab(0);
		// Convertir currentUser en String
//		String struser = currentUser.getUsername().toString();
//		TextView txtuser = (TextView) home.findViewById(R.id.txtuser);
//		txtuser.setText(this.getString(R.string.text_login_home_user )+ " "  + struser);
//		
//		//Botón desconectarse da app
//		logout = (ButtonRectangle) home.findViewById(R.id.logout);
//		logout.setOnClickListener(new OnClickListener() {
//			public void onClick(View arg0) {
//				// Desconectar o current user
//				ParseUser.logOut();
//				getActivity().finish();
//			}
//		});
//		
//		//Botón engadir nova lista
//		addlist = (ImageButton) home.findViewById(R.id.add_list);
//		addlist.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				//Ir a páxina engadir unha lista
//				Intent intent = new Intent(getActivity(), AddShoppingListActivity.class);
//				startActivity(intent);
//				
//			}
//		});
		
//		//Listas da compra
//		list = (ListView) home.findViewById(R.id.lista_list);
//		
//		
		return home;
	}
	
//	public void reloadUserShoppingLists() {
//		//Recreamos o conxunto de listas de compra do usuario
//		
//		adapter = new AdapterListas(getActivity(), misListas);
//		list.setAdapter(adapter);
//				
//		ParseQuery<Lista> query = ParseQuery.getQuery(Lista.class);
//		query.include("Market");
//		//Filtramos as lista para cada usuario logueado na app
//		query.include("User");
//		query.whereEqualTo("idUser", currentUser);
//		//query.include("Products");
//		query.findInBackground(new FindCallback<Lista>() {
//			@Override
//			public void done(List<Lista> objects, ParseException e) {
//				misListas = (ArrayList<Lista>) objects;
//				adapter.clear();
//				if(misListas != null){
//					adapter.addAll(misListas);
//				}else{
//					Toast.makeText(getActivity(), R.string.empty_list, Toast.LENGTH_LONG).show();
//				}
//			}
//		});
//	}
	
	@Override
	public void onResume() {
		super.onResume();
		//reloadUserShoppingLists();
	}
	
	
	
	private AbsListView.OnScrollListener mScrollListener = new AbsListView.OnScrollListener() {

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

	           

	        }
	    };
}