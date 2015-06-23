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
import android.widget.Toast;

import com.facebook.login.LoginManager;

import org.json.JSONArray;
import org.json.JSONObject;


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

        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);

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





/*
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

  */

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}