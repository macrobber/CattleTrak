package com.example.macro.cattletrak;


import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class CattleManagement extends AppCompatActivity {

    private String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cattle_management);

// ************ Section for Back Button Here  *******************
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
// ************ Section for Back Button Here  *******************


//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        this.uid = extras.getString("uid");
        Toast.makeText(getApplicationContext(), this.uid, Toast.LENGTH_SHORT).show();


        TextView txtShowAllCows = (TextView) findViewById(R.id.testView2);
        TextView txtShowCattle = (TextView) findViewById(R.id.searchCattle);
        TextView txtAddCattle = (TextView) findViewById(R.id.AddCattle);
        TextView txtDeactivateCattle = (TextView) findViewById(R.id.tDeleteCow);
        TextView txtEditCattle = (TextView) findViewById(R.id.tEditCow);
        TextView txtHerdHealth = (TextView) findViewById(R.id.herdHealth);

// ************* Block for onClick Listener ***********************
        txtShowAllCows.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowAllCattle.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }
        }));
// ************* Close this block

// ************* Block for onClick Listener ***********************
        txtShowCattle.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchCattle.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }

        }));
// ************* Close this block

// ************* Block for onClick Listener ***********************
        txtAddCattle.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CattleAdd.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }

        }));
// ************* Close this block

        // ************* Block for onClick Listener ***********************
        txtDeactivateCattle.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DeactivateCattle.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }

        }));
// ************* Close this block

        // ************* Block for onClick Listener ***********************
        txtEditCattle.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CattleUpdate.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }
        }));
// ************* Close this block
        // ************* Block for onClick Listener ***********************
        txtHerdHealth.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), herdHealthSelector.class);
                intent.putExtra("uid", uid);
                intent.putExtra("AnimalType", "1");
                startActivity(intent);
            }
        }));
// ************* Close this block


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cattle_management, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

// ***** Section for back button ******
        if(id==android.R.id.home) {
            onBackPressed();
            finish();
            return true;
        }
// ***** Section for back button ******

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
//        if(id==android.R.id.home) {
//            finish();
//        }


        return super.onOptionsItemSelected(item);
    }
}
