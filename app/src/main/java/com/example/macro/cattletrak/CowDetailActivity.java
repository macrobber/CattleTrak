package com.example.macro.cattletrak;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by mack on 4/26/15.
 */
public class CowDetailActivity extends AppCompatActivity {

    //private EditText Tag;
//    private EditText Name;
    private TextView Name;
    private TextView RegNumber;
    private TextView Gender;
    private TextView ElectronicID;
    private TextView Owner;
    private TextView PercentagePure;
    private TextView DryMatterIntake;
    private TextView BirthDate;
    private TextView BirthWeight;
    private TextView WeaningDate;
    private TextView WeaningWeight;
    private TextView YearlingWeight;
    private TextView Breeder;
    private TextView Conception;
    private TextView SirTag;
    private TextView DamTag;
    private TextView HornStatus;
    private TextView Breed;
    private TextView BodyScore;
    private TextView CalvingDate;
    private TextView AmountPaid;
    private TextView AmountSold;


    private TextView ColorMarkings;
//    private TextView Brand;


    private String Tag;
    private String sName;
    private String sBrand;
    private String sRegNumber;
    private String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cow_details);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            return;
        }

        this.uid = extras.getString("uid");


//        this.Name = (EditText) this.findViewById(R.id.Name);
        this.Name = (TextView) this.findViewById(R.id.Name);
        this.RegNumber = (TextView) this.findViewById(R.id.RegNumber);
        this.ElectronicID = (TextView) this.findViewById(R.id.ElectronicID);
        this.Owner = (TextView) this.findViewById(R.id.Owner);
        this.PercentagePure = (TextView) this.findViewById(R.id.PercentagePure);
        this.DryMatterIntake = (TextView) this.findViewById(R.id.DryMatterIntake);
        this.BirthDate = (TextView) this.findViewById(R.id.BirthDate);
        this.BirthWeight = (TextView) this.findViewById(R.id.BirthWeight);
        this.WeaningDate = (TextView) this.findViewById(R.id.WeaningDate);
        this.WeaningWeight = (TextView) this.findViewById(R.id.WeaningWeight);
        this.YearlingWeight = (TextView) this.findViewById(R.id.YearlingWeight);
        this.Breeder = (TextView) this.findViewById(R.id.Breeder);
        this.Conception = (TextView) this.findViewById(R.id.Conception);
        this.SirTag = (TextView) this.findViewById(R.id.SirTag);
        this.DamTag = (TextView) this.findViewById(R.id.DamTag);
        this.HornStatus = (TextView) this.findViewById(R.id.HornStatus);
        this.Breed = (TextView) this.findViewById(R.id.Breed);
        this.BodyScore = (TextView) this.findViewById(R.id.BodyScore);
        this.CalvingDate = (TextView) this.findViewById(R.id.CalvingDate);
        this.AmountPaid = (TextView) this.findViewById(R.id.AmountPaid);
        this.AmountSold = (TextView) this.findViewById(R.id.AmountSold);
        this.Gender = (TextView) this.findViewById(R.id.Gender);
        this.ColorMarkings = (TextView) this.findViewById(R.id.ColorMarkings);



        this.Tag = getIntent().getStringExtra("Tag"); // this was pulled form MainActivity putStringExtra - sent over for query
//        this.uid = getIntent().getStringExtra("uid");

        TextView myTag = (TextView) findViewById(R.id.TagHeader);
        myTag.setText(this.Tag);

        if(this.Tag != null)
        {
            // we know we passed a tag....like...maybe???
            new GetCowDetails().execute(new CattleApiConnector());
        }
    }
    private void UpdateCowClicked(View v, String Tag, String Name, String Brand, String RegNumber) {


        Toast.makeText(getApplicationContext(), Name, Toast.LENGTH_LONG).show();


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //  the following 2 blocks are required to have the menu show up
    //both the onCreateOptionsMenu AND onOptionsItemSelected
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home) {
            onBackPressed();
            return true;
        }

        if(id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected((item));

    }

    private class GetCowDetails extends AsyncTask<CattleApiConnector, Long, JSONArray> {
        @Override
        protected JSONArray doInBackground(CattleApiConnector... params) {
            // This is executed in the background thread
            return params[0].GetCowDetails(Tag, uid);
        }

        @Override
        protected void onPostExecute(JSONArray jArray) {
            // once do in background is done - it sends the result to the main thread...here
//            JSONObject cow = jArray.getJSONObject(0);
            try {
                JSONObject cow = jArray.getJSONObject(0);

//                if(cow.getString("success") == "0")
//                    Toast.makeText(getApplicationContext(), cow.getString("success"), Toast.LENGTH_LONG).show();

                Name.setText(cow.getString("Name"));
                RegNumber.setText(cow.getString("RegNumber"));
                ElectronicID.setText(cow.getString("ElectronicID"));
                Owner.setText(cow.getString("Owner"));
                PercentagePure.setText(cow.getString("PercentagePure"));
                ColorMarkings.setText(cow.getString("Color"));
                DryMatterIntake.setText(cow.getString("DryMatterIntake"));
                BirthDate.setText(cow.getString("BirthDate"));
                BirthWeight.setText(cow.getString("BirthWeight"));
                WeaningDate.setText(cow.getString("WeaningDate"));
                WeaningWeight.setText(cow.getString("WeaningWeight"));
                YearlingWeight.setText(cow.getString("YearlingWeight"));
                Breeder.setText(cow.getString("Breeder"));
                Conception.setText(cow.getString("Conception"));
                SirTag.setText(cow.getString("SirTag"));
                DamTag.setText(cow.getString("DamTag"));
                HornStatus.setText(cow.getString("HornStatus"));
                Breed.setText(cow.getString("Breed"));
                BodyScore.setText(cow.getString("BodyScore"));
                CalvingDate.setText(cow.getString("CalvingDate"));
                AmountPaid.setText(cow.getString("AmountPaid"));
                AmountSold.setText(cow.getString("AmountSold"));
                Gender.setText(cow.getString("Gender"));


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

        private class UpdateCow extends AsyncTask<CattleApiConnector, Long, JSONArray>
        {
            @Override
            protected JSONArray doInBackground(CattleApiConnector...params) {
                // This is executed in the background thread
//                Log.v(Tag, "PN: **** Tag is now: "+Tag );
//                Log.v(Tag, "PN: **** sName is now: "+sName );
//                Log.v(Tag, "PN: **** sBrand is now: "+sBrand );

                return params[0].UpdateCow(Tag, sName, sBrand, sRegNumber);

            }

            @Override
            protected void onPostExecute(JSONArray jArray) {
                // once do in background is done - it sends the result to the main thread...here
//            JSONObject cow = jArray.getJSONObject(0);
                try
                {
                    JSONObject cow = jArray.getJSONObject(0);
//                    Name.setText(cow.getString("Name"));
//                    Brand.setText(cow.getString("Brand"));
//                    RegNumber.setText(cow.getString("RegNumber"));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

    }

}
