package com.example.macro.cattletrak;


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


public class AddHealth extends ActionBarActivity {
    private String uid;
    private String AnimalType;
    private String sTag;
    private String sMed1;
    private String sMed2;
    private String sMed3;
    private String sNotes;
    private EditText Tag;
    private EditText med1;
    private EditText med2;
    private EditText med3;
    private EditText Notes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_health);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        this.uid = extras.getString("uid");
        this.AnimalType = extras.getString("AnimalType");
        this.Tag = (EditText) this.findViewById(R.id.Tag);
        this.med1 = (EditText) this.findViewById(R.id.med1);
        this.med2 = (EditText) this.findViewById(R.id.med2);
        this.med3 = (EditText) this.findViewById(R.id.med3);
        this.Notes = (EditText) this.findViewById(R.id.Notes);

        Button bAddHealth = (Button) findViewById(R.id.btnAddHealth);

        bAddHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sTag = Tag.getText().toString();
                sMed1 = med1.getText().toString();
                sMed2 = med2.getText().toString();
                sMed3 = med3.getText().toString();
                sNotes = Notes.getText().toString();

                if(sTag != null) {
                    new AddCow().execute(new CattleApiConnector());
                }
            }
        });
// end of code for Add / Edit button
    } // closes onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_health, menu);
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

    private class AddCow extends AsyncTask<CattleApiConnector, Long, JSONArray> {
        @Override
        protected JSONArray doInBackground(CattleApiConnector... params) {
            // This is executed in the background thread

//            public JSONArray AddHealth(String uid, String Tag, String AnimalType, String med1, String med2, String med3, String Notes)

            return params[0].AddHealth(uid, sTag, AnimalType, sMed1, sMed2, sMed3, sNotes);
        } // closes do in background - onPost still needs to happen
        @Override
        protected void onPostExecute(JSONArray jArray) {
            // once do in background is done - it sends the result to the main thread...here
//            JSONObject cow = jArray.getJSONObject(0);
            try {
                //   JSONObject cow = jArray.getJSONObject(0);
                Tag.setText("");
                med1.setText("");
                med2.setText("");
                med3.setText("");
                Notes.setText("");

                Toast.makeText(getApplicationContext(), "Health Record Added", Toast.LENGTH_LONG).show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }




    }
