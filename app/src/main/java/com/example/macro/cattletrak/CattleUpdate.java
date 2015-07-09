package com.example.macro.cattletrak;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CattleUpdate extends ActionBarActivity {

    private String uid;
    private EditText Tag;
    private String sTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cattle_update);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            return;
        }
        this.uid = extras.getString("uid");

        Tag = (EditText) findViewById(R.id.Tag);

        Button bUpdateCattle = (Button) findViewById(R.id.btnUpdateCow);

        bUpdateCattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sTag = Tag.getText().toString();

                Intent showDetails = new Intent(getApplicationContext(), CattleUpdateDetails.class);
                showDetails.putExtra("Tag", sTag);
                showDetails.putExtra("uid", uid);

                startActivity(showDetails);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cattle_update, menu);
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

        if(id==android.R.id.home) {
            onBackPressed();
            finish();

            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
