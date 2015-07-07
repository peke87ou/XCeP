package com.irina.xcep;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.irina.xcep.adapters.MenuAdapter;
import com.irina.xcep.adapters.MergeAdapter;
import com.irina.xcep.menu.navitems.NavDrawerItem;
import com.irina.xcep.menu.navitems.NavMenuItem;
import com.irina.xcep.menu.navitems.NavTitleItem;
import com.irina.xcep.utils.FragmentIndexes;


public class MenuActivity extends Activity implements MenuAdapter.SelectedListButton, AdapterView.OnItemClickListener{


    private int mCurrentFragmentIndex;
    private static final String CURRENT_FRAGMENT_INDEX = "current_fragment";


    private DrawerLayout mDrawerLayout;
    private ListView mListView;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mTitle = this.mDrawerTitle = getTitle();

        /*Setting up all views*/
        initViews();

        mDrawerLayout.setDrawerListener(this.mDrawerToggle);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        if (savedInstanceState == null) {
            loadFragment(FragmentIndexes.FRAGMENT_HOME);
            mCurrentFragmentIndex = FragmentIndexes.FRAGMENT_HOME;
            mDrawerLayout.setSelected(true);

        } else {

            mCurrentFragmentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT_INDEX);
            loadFragment(mCurrentFragmentIndex);
            mDrawerLayout.setSelected(true);
        }

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
    }


    /**
     * method use to initialize all views and listeners
     */
    public void initViews() {

        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.mListView = (ListView) findViewById(R.id.list_drawer);

        this.mListView.setOnItemClickListener(/*OnItemClickListener*/this);


        MenuAdapter menuAdapter = new MenuAdapter(this, R.layout.list_view_item_clip_menu, menu,/*SelectedListButton*/this);

        MergeAdapter mergeAdapter = new MergeAdapter();
        mergeAdapter.addAdapter(menuAdapter);


        this.mListView.setAdapter(mergeAdapter);

        
        this.mDrawerToggle = new ActionBarDrawerToggle(MenuActivity.this,
                this.mDrawerLayout, R.drawable.ic_menu_drawer,
                R.string.drawer_open,
                R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                getActionBar().setTitle(mTitle);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    invalidateOptionsMenu();
                }

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    invalidateOptionsMenu();
                }
            }

        };
    }


    /*
    * @param title: action's bar title depends on current fragment
    * */
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(title);

    }

    /*onItemClick del navigation drawer*/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (((NavDrawerItem) parent.getItemAtPosition(position)).isClickable() &&
                NavDrawerItem.SECTION_MENU_ITEM == ((NavDrawerItem) parent.getItemAtPosition(position)).getType()) {
            loadFragment(menu[position].getId());
        }
    }


    /*Static Navigation Drawer items*/
    private NavDrawerItem[] menu = new NavDrawerItem[]{
            NavTitleItem.create(100, R.string.app_name),
            NavMenuItem.create(101, R.string.my_list, R.drawable.list, true, this),
            NavMenuItem.create(102, R.string.catalog, R.drawable.server, true, this),
            NavMenuItem.create(103, R.string.scan, R.drawable.camera, true, this),
            NavTitleItem.create(200, R.string.setting),
            NavMenuItem.create(201, R.string.facebook, R.drawable.facebook, true, this),
		    NavMenuItem.create(202, R.string.twitter, R.drawable.twitter, true, this),
		    NavMenuItem.create(203, R.string.language, R.drawable.comments, true, this),
		    NavMenuItem.create(204, R.string.reset_bd, R.drawable.recycle, true, this),
		    NavMenuItem.create(205, R.string.help, R.drawable.help, true, this)};



    private void loadFragment(int index) {

        Fragment fragment = null;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        switch (index) {
            case FragmentIndexes.FRAGMENT_HOME:
                fragment = HomeFragment.newInstance(FragmentIndexes.FRAGMENT_HOME);
                break;
//            case FragmentIndexes.FRAGMENT_CATALOG:
//                //fragment = QuotesFragment.newInstance(FragmentIndexes.MY_QUOTES_INDEX);
//                break;
//            case FragmentIndexes.FRAGMENT_SCAN:
//                //fragment = QuotesFragment.newInstance(FragmentIndexes.FAVORITES_QUOTES_INDEX);
//                break;
         default:
        	 Toast.makeText(this, "Funcionalidade en cosntrucción", Toast.LENGTH_LONG).show();
        	 return;
           
        }

        //Store current index
        mCurrentFragmentIndex = index;
        //Add fragment to layout
        transaction.replace(R.id.container, fragment).commit();
        mListView.setItemChecked(index, true);
        mDrawerLayout.closeDrawer(mListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_FRAGMENT_INDEX,mCurrentFragmentIndex);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    
    @Override
    public void onSelectedButton(int type) {

    }
}
