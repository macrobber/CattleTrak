package com.example.macro.cattletrak;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class herdHealthSelector extends ActionBarActivity {

    private String uid;
    private String AnimalType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herd_health_selector);
        // ************ Section for Back Button Here  *******************
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
// ************ Section for Back Button Here  *******************

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        this.uid = extras.getString("uid");
        this.AnimalType = extras.getString("AnimalType");
        Toast.makeText(getApplicationContext(), this.AnimalType, Toast.LENGTH_SHORT).show();

        TextView txtNewHealth = (TextView) findViewById(R.id.NewHealth);
//        TextView txtViewHealth = (TextView) findViewById(R.id.viewHealth);


        // ************* Block for onClick Listener ***********************
        txtNewHealth.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddHealth.class);
                intent.putExtra("uid", uid);
                intent.putExtra("AnimalType", AnimalType);
                startActivity(intent);
            }
        }));
// ************* Close this block





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_herd_health_selector, menu);
        return true;
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

        return super.onOptionsItemSelected(item);
    }
}
