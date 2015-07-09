package com.example.macro.cattletrak;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;


public class DeactivateCattle extends ActionBarActivity {

    private String uid;
    private String Tag;
    private EditText eTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deactivate_cattle);
// ************ Section for Back Button Here  *******************
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
// ************ Section for Back Button Here  *******************

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            return;
        }

        this.uid = extras.getString("uid");
        this.eTag = (EditText) findViewById(R.id.deactivateCow);
//        this.Tag = this.eTag.getText().toString();

        Button btnDeactivate = (Button) findViewById(R.id.btnDeactivate);
        btnDeactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tag = eTag.getText().toString();


                new AlertDialog.Builder(DeactivateCattle.this)
                        .setTitle("Deactivate Entry")
                        .setMessage("Are you sure you want to deactivate this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                        // these three lines moved
                                if(Tag != null) {
                                    new DeactivateCow().execute(new CattleApiConnector());
                                }
                        //theese three lines moved

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                eTag.setText("");

                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();




// these three lines moved
/*
                if(Tag != null) {
                    new DeactivateCow().execute(new CattleApiConnector());
                }
*/
//theese three lines moved


// below is fine
            }
        });
    }


    private class DeactivateCow extends AsyncTask<CattleApiConnector, Long, JSONArray> {
        @Override
        protected JSONArray doInBackground(CattleApiConnector... params) {
            // This is executed in the background thread
            return params[0].DelCattle(Tag, uid);
        }
        @Override
        protected void onPostExecute(JSONArray jArray) {
            // once do in background is done - it sends the result to the main thread...here
//            JSONObject cow = jArray.getJSONObject(0);
            try {
                //   JSONObject cow = jArray.getJSONObject(0);
                eTag.setText("");

                Toast.makeText(getApplicationContext(), "Record Deactivated", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deactivate_cattle, menu);
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
