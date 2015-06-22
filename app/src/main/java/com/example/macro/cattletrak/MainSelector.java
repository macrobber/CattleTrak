package com.example.macro.cattletrak;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;

import org.json.JSONArray;
import org.json.JSONObject;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainSelector extends ActionBarActivity {

    private String email;
    private String pw;
    private String uid;
    private String TAG;

    private String fID;
    private String fFullName;
    private String FirstName;
    private String FB;

    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;
    FrameLayout progressBarHolder;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_selector);

//        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);


        Bundle extras = getIntent().getExtras();
        if (extras == null) {
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

/*
        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(2000);
        progressBarHolder.setAnimation(inAnimation);
        progressBarHolder.setVisibility(View.VISIBLE);
*/
        Log.d(TAG, "()()() EMAIL IS: " + this.email);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
//        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
//        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //}

/* Loggin in
        if (this.email != null) {
            Log.d(TAG, "()()() MADE IT HERE");
            // we know we passed a tag....like...maybe???
            new MyLogin().execute(new CattleApiConnector());


        }
*/
    //}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
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

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new CheeseListFragment(), "Cattle");
        adapter.addFragment(new CheeseListFragment(), "Farm");
        adapter.addFragment(new CheeseListFragment(), "Account");
        adapter.addFragment(new CheeseListFragment(), "Info");

        viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
static class Adapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public Adapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }
}

    private class MyLogin extends AsyncTask<CattleApiConnector, Long, JSONArray> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
/*
            inAnimation = new AlphaAnimation(0f, 1f);
            inAnimation.setDuration(200);
            progressBarHolder.setAnimation(inAnimation);
            progressBarHolder.setVisibility(View.VISIBLE);
*/
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

/*
                outAnimation = new AlphaAnimation(1f, 0f);
                outAnimation.setDuration(200);
                progressBarHolder.setAnimation(outAnimation);
                progressBarHolder.setVisibility(View.GONE);
*/

//                ImageView imgCattle = (ImageView) findViewById(R.id.imageView4);
// uncomment the aboce - it works as well...
/*   This works
                imgCattle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

     // THIS WORKS>>>>>>UNCOMMENT IT HERE
                        Intent intent = new Intent(getApplicationContext(), CattleManagement.class);

                        intent.putExtra("uid", uid);
                        startActivity(intent); // spawn the activity




                    } // closes OnClick public void



                }  // closes setonclick listener
                );  // closes setonclick listener
*/
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }  // closes async

} // closes overall

