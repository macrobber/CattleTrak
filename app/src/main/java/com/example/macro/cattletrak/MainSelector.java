package com.example.macro.cattletrak;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.facebook.login.LoginManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Tabs.SlidingTabLayout;
import Tabs.ViewPagerAdapter;

public class MainSelector extends ActionBarActivity { /* When using Appcombat support library
                                                         you need to extend Main Activity to
                                                         ActionBarActivity.
                                                      */

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"Home", "Cattle", "Farm"};
    int Numboftabs = 3;
    private ListView mDrawerList;
//    private ArrayAdapter<String> mAdapter;

    //First We Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles Are holded in an Array as you can see

    String TITLES[] = {"Home", "Cattle", "Farm", "Upgrade", "Options"};
//    int ICONS[] = {R.drawable.ic_home,R.drawable.ic_events,R.drawable.ic_mail,R.drawable.ic_shop,R.drawable.ic_travel};

    int ICONS[] = {R.drawable.ic_home_black_24dp, R.drawable.ic_today_black_24dp, R.drawable.ic_place_black_24dp, R.drawable.ic_shopping_cart_black_24dp, R.drawable.ic_settings_black_24dp};

    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    String NAME = "Cattle Trak";
    String EMAIL = "admin@cattletrak.com";
    int PROFILE = R.drawable.ic_launcher;

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle

    private String email;
    private String pw;
    private String uid;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;
    FrameLayout progressBarHolder;
    private String fID;
    private String fFullName;
    private String FirstName;
    private String FB;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_selector);

        // Creating The Toolbar and setting it as the Toolbar for the activity

/*
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(TITLES, ICONS, NAME, EMAIL, PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {


            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();

        */

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        this.fID = extras.getString("fID");
        this.FB = extras.getString("FB");


        if (this.FB.equals("-1")) {
            Log.d(TAG, "()()() ()()()()()()()() this.FB is: " + this.FB);
            this.fFullName = extras.getString("fFullName");
            this.FirstName = extras.getString("FirstName");
            this.FB = extras.getString("FB");
            this.email = this.fID;
            this.pw = "-1";
        }
        else  // it is not a facebook login - so grab email and pw
        {
            this.email = extras.getString("Email");
            this.pw = extras.getString("PW");
        }

//        Toast.makeText(getApplicationContext(), "IN MAINSELECTOR - FBID = " + this.fID, Toast.LENGTH_LONG).show();
        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);
        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(2000);
        progressBarHolder.setAnimation(inAnimation);
        progressBarHolder.setVisibility(View.VISIBLE);

        if (this.email != null) {
            // we know we passed a tag....like...maybe???
            new MyLogin().execute(new CattleApiConnector());

        }


    }  // closes onCreate


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.log_out) {

            LoginManager.getInstance().logOut();
            Toast.makeText(getApplicationContext(), "Logging Out", Toast.LENGTH_LONG).show();

            this.finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private class MyLogin extends AsyncTask<CattleApiConnector, Long, JSONArray> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            inAnimation = new AlphaAnimation(0f, 1f);
            inAnimation.setDuration(200);
            progressBarHolder.setAnimation(inAnimation);
            progressBarHolder.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONArray doInBackground(CattleApiConnector... params) {
            // This is executed in the background thread
            return params[0].MyLogin(email, pw, uid);
        }


        @Override
        protected void onPostExecute(JSONArray jArray) {
            // once do in background is done - it sends the result to the main thread...here
            try {
                JSONObject cow = jArray.getJSONObject(0);
                uid = (cow.getString("uid"));
                if (uid == null)
                    Toast.makeText(getApplicationContext(), "Error Logging In", Toast.LENGTH_LONG).show();


                outAnimation = new AlphaAnimation(1f, 0f);
                outAnimation.setDuration(200);
                progressBarHolder.setAnimation(outAnimation);
                progressBarHolder.setVisibility(View.GONE);


                ImageView imgCattle = (ImageView) findViewById(R.id.imageView4);
                imgCattle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), CattleManagement.class);
                        intent.putExtra("uid", uid);
                        startActivity(intent); // spawn the activity
                    } // closes OnClick public void
                }  // closes setonclick listener
                );  // closes setonclick listener

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}



/*
public class MainSelector extends ActionBarActivity {

    private String email;
    private String pw;
    private String uid;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;
    FrameLayout progressBarHolder;
    private String fID;
    private String fFullName;
    private String FirstName;
    private String FB;
    private String TAG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_selector);

//        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            return;
        }
        this.fID = extras.getString("fID");
        this.fFullName = extras.getString("fFullName");
        this.FirstName = extras.getString("FirstName");
        this.FB = extras.getString("FB");


        if (this.FB.equals("-1")) {
            Log.d(TAG, "()()() ()()()()()()()() this.FB is: " + this.FB);
            this.email = this.fID;
            this.pw = "-1";
        }

//        this.email = extras.getString("Email");
//        this.pw = extras.getString("PW");

        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(2000);
        progressBarHolder.setAnimation(inAnimation);
        progressBarHolder.setVisibility(View.VISIBLE);

//        Toast.makeText(getApplicationContext(), "FBID = "+this.fID, Toast.LENGTH_LONG).show();

        if(this.email != null)
        {
            // we know we passed a tag....like...maybe???
            new MyLogin().execute(new CattleApiConnector());

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_selector, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.log_out) {

            LoginManager.getInstance().logOut();
            Toast.makeText(getApplicationContext(), "Logging Out", Toast.LENGTH_LONG).show();

            this.finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private class MyLogin extends AsyncTask<CattleApiConnector, Long, JSONArray> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            inAnimation = new AlphaAnimation(0f, 1f);
            inAnimation.setDuration(200);
            progressBarHolder.setAnimation(inAnimation);
            progressBarHolder.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONArray doInBackground(CattleApiConnector... params) {
            // This is executed in the background thread
            return params[0].MyLogin(email, pw, uid);
        }


        @Override
        protected void onPostExecute(JSONArray jArray) {
            // once do in background is done - it sends the result to the main thread...here
            try {
                JSONObject cow = jArray.getJSONObject(0);
                uid = (cow.getString("uid"));
                if(uid==null)
                    Toast.makeText(getApplicationContext(), "Error Logging In", Toast.LENGTH_LONG).show();


                outAnimation = new AlphaAnimation(1f, 0f);
                outAnimation.setDuration(200);
                progressBarHolder.setAnimation(outAnimation);
                progressBarHolder.setVisibility(View.GONE);

                Toast.makeText(getApplicationContext(), "UID Returned" + uid, Toast.LENGTH_LONG).show();






                ImageView imgCattle = (ImageView) findViewById(R.id.imageView4);
                imgCattle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), CattleManagement.class);

                        intent.putExtra("uid", uid);
                        startActivity(intent); // spawn the activity
                    } // closes OnClick public void
                }  // closes setonclick listener
                );  // closes setonclick listener



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
    */