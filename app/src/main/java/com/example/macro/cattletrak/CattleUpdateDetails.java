package com.example.macro.cattletrak;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;


public class CattleUpdateDetails extends ActionBarActivity {
    private String uid;
    private String Tag;
    private EditText Name;
    private EditText RegNumber;
    private EditText Gender;
    private EditText ElectronicID;
    private EditText Owner;
    private EditText PercentagePure;
    private EditText DryMatterIntake;
    private EditText BirthWeight;
    private EditText WeaningWeight;
    private EditText YearlingWeight;
    private EditText Breeder;
    private EditText Conception;
    private EditText SirTag;
    private EditText DamTag;
    private EditText HornStatus;
    private EditText Breed;
    private EditText BodyScore;
    private EditText AmountPaid;
    private EditText AmountSold;
    private EditText ColorMarkings;
    private EditText sTag;

    private TextView BirthDate;
    private TextView WeaningDate;
    private TextView CalvingDate;
    // variables for date picker here

    int year_x, month_x, day_x;
    static final int DILOG_ID = 0;
    static final int DATE_PICKER_BIRTHDATE = 0;
    static final int DATE_PICKER_WEANINGDATE = 1;
    static final int DATE_PICKER_CALVINGDATE = 2;

    // variables for date picker here

//    private String sTag;
    private String sName;
    private String sRegNumber;
    private String sElectronicID;
    private String sOwner;
    private String sPercentagePure;
    private String sDryMatterIntake;
    private String sBirthDate;
    private String sBirthWeight;
    private String sWeaningDate;
    private String sWeaningWeight;
    private String sYearlingWeight;
    private String sColor;
    private String sBreeder;
    private String sConception;
    private String sSirTag;
    private String sDamTag;
    private String sHornStatus;
    private String sBreed;
    private String sBodyScore;
    private String sCalvingDate;
    private String sAmountPaid;
    private String sAmountSold;
    private String sGender;

    private Button bUpdateCattle;
   // Button bUpdateCattle



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cattle_update_details);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            return;
        }

        this.uid = extras.getString("uid");
        this.Tag = extras.getString("Tag");

        this.Name = (EditText) this.findViewById(R.id.Name);
        this.RegNumber = (EditText) this.findViewById(R.id.RegNumber);
        this.sTag = (EditText) this.findViewById(R.id.Tag);
        this.ElectronicID = (EditText) this.findViewById(R.id.ElectronicID);
        this.Owner = (EditText) this.findViewById(R.id.Owner);
        this.PercentagePure = (EditText) this.findViewById(R.id.PercentagePure);
        this.DryMatterIntake = (EditText) this.findViewById(R.id.DryMatterIntake);
        this.BirthDate = (TextView) this.findViewById(R.id.tBirthDate);
        this.BirthWeight = (EditText) this.findViewById(R.id.BirthWeight);
        this.WeaningDate = (TextView) this.findViewById(R.id.tWeaningDate);
        this.WeaningWeight = (EditText) this.findViewById(R.id.WeaningWeight);
        this.YearlingWeight = (EditText) this.findViewById(R.id.YearlingWeight);
        this.ColorMarkings = (EditText) this.findViewById(R.id.ColorMarkings);
        this.Breeder = (EditText) this.findViewById(R.id.Breeder);
        this.Conception = (EditText) this.findViewById(R.id.Conception);
        this.SirTag = (EditText) this.findViewById(R.id.SirTag);
        this.DamTag = (EditText) this.findViewById(R.id.DamTag);
        this.HornStatus = (EditText) this.findViewById(R.id.HornStatus);
        this.Breed = (EditText) this.findViewById(R.id.Breed);
        this.BodyScore = (EditText) this.findViewById(R.id.BodyScore);
        this.CalvingDate = (TextView) this.findViewById(R.id.tCalvingDate);
        this.AmountPaid = (EditText) this.findViewById(R.id.AmountPaid);
        this.AmountSold = (EditText) this.findViewById(R.id.AmountSold);
        this.Gender = (EditText) this.findViewById(R.id.Gender);

// **************************** Code for Date Picker Inside onCreate ***************************

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);


        showDialogOnButtonClick();

// **************************** Code for Date Picker Inside onCreate ***************************
        if(this.Tag != null)
        {
            // we know we passed a tag....like...maybe???
            new GetCowDetails().execute(new CattleApiConnector());
        }

        // Code for the Update/Add button


        bUpdateCattle = (Button) findViewById(R.id.btnUpdateCattle);
        bUpdateCattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tag = sTag.getText().toString();
                sName = Name.getText().toString();
                sName = sName.trim();
                sRegNumber = RegNumber.getText().toString();
                sElectronicID = ElectronicID.getText().toString();
                sOwner = Owner.getText().toString();
                sOwner = sOwner.trim();
                sPercentagePure = PercentagePure.getText().toString();
                sDryMatterIntake = DryMatterIntake.getText().toString();
                sBirthDate = BirthDate.getText().toString();
                sBirthWeight = BirthWeight.getText().toString();
                sWeaningDate = WeaningDate.getText().toString();
                sWeaningWeight = WeaningWeight.getText().toString();
                sYearlingWeight = YearlingWeight.getText().toString();
                sColor = ColorMarkings.getText().toString();
                sColor = sColor.trim();
                sBreeder = Breeder.getText().toString();
                sBreeder = sBreeder.trim();
                sConception = Conception.getText().toString();
                sConception = sConception.trim();
                sSirTag = SirTag.getText().toString();
                sDamTag = DamTag.getText().toString();
                sHornStatus = HornStatus.getText().toString();
                sHornStatus = sHornStatus.trim();
                sBreed = Breed.getText().toString();
                sBreed = sBreed.trim();
                sBodyScore = BodyScore.getText().toString();
                sCalvingDate = CalvingDate.getText().toString();
                sAmountPaid = AmountPaid.getText().toString();
                sAmountSold = AmountSold.getText().toString();
                sGender = Gender.getText().toString();
                sGender = sGender.trim();

                if (sTag != null) {
                    new UpdateCow().execute(new CattleApiConnector());
                }
            }
        });
// end of code for Add / Edit button

    } // Closes onCreate

    // **************************** Code for Date Picker Outside onCreate***************************
    // These establish onClickListeners for each of the textBoxes for date
    public void showDialogOnButtonClick() {

        BirthDate = (TextView) findViewById(R.id.tBirthDate);
        BirthDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DILOG_ID);
                    }
                }
        );
        WeaningDate = (TextView) findViewById(R.id.tWeaningDate);
        WeaningDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(1);
                    }
                }
        );

        CalvingDate = (TextView) findViewById(R.id.tCalvingDate);
        CalvingDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(2);
                    }
                }
        );
    }

    // Now call the correct Dialog Listener based  on which text listener was triggered
    @Override
    protected Dialog onCreateDialog(int id) {

        switch (id) {
            case DATE_PICKER_BIRTHDATE:
                return new DatePickerDialog(this, dpickerListner, year_x, month_x, day_x);
            case DATE_PICKER_WEANINGDATE:
                return new DatePickerDialog(this, dpickerListnerWeaning, year_x, month_x, day_x);
            case DATE_PICKER_CALVINGDATE:
                return new DatePickerDialog(this, dpickerListnerCalving, year_x, month_x, day_x);
        }
        return null;
    }

    // Process the listener - set the text field to what the picker returned.
    private DatePickerDialog.OnDateSetListener dpickerListner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            Toast.makeText(getApplicationContext(), year_x + "-" + month_x + "-" + day_x, Toast.LENGTH_SHORT).show();
            BirthDate.setText(new StringBuilder()
                            .append(year_x).append("-").append(month_x).append("-").append(day_x)
            );
        }
    };

    private DatePickerDialog.OnDateSetListener dpickerListnerWeaning
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear;
            day_x = dayOfMonth;
            WeaningDate.setText(new StringBuilder()
                            .append(year_x).append("-").append(month_x).append("-").append(day_x)

            );
        }
    };

    private DatePickerDialog.OnDateSetListener dpickerListnerCalving
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear;
            day_x = dayOfMonth;
            CalvingDate.setText(new StringBuilder()
                            .append(year_x).append("-").append(month_x).append("-").append(day_x)

            );
        }
    };

    // **************************** Date Picker runs to here outside onCreate***********************


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cattle_update_details, menu);
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

    private class GetCowDetails extends AsyncTask<CattleApiConnector, Long, JSONArray> {
        @Override
        protected JSONArray doInBackground(CattleApiConnector... params) {
            bUpdateCattle.setVisibility(View.INVISIBLE);

            // This is executed in the background thread
            return params[0].GetCowDetails(Tag, uid);
        }

        @Override
        protected void onPostExecute(JSONArray jArray) {
            // once do in background is done - it sends the result to the main thread...here
//            JSONObject cow = jArray.getJSONObject(0);
            try {
                bUpdateCattle.setVisibility(View.VISIBLE);

                JSONObject cow = jArray.getJSONObject(0);
                sTag.setText(cow.getString("Tag"));
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


    private class UpdateCow extends AsyncTask<CattleApiConnector, Long, JSONArray> {
        @Override
        protected JSONArray doInBackground(CattleApiConnector... params) {
            // This is executed in the background thread
            return params[0].UpdateCattle(uid, Tag, sName, sRegNumber, sElectronicID, sOwner, sPercentagePure, sDryMatterIntake, sBirthDate, sBirthWeight, sWeaningDate, sWeaningWeight, sYearlingWeight, sColor, sBreeder, sConception, sSirTag, sDamTag, sHornStatus, sBreed, sBodyScore, sCalvingDate, sAmountPaid, sAmountSold, sGender);
        }

        @Override
        protected void onPostExecute(JSONArray jArray) {
            // once do in background is done - it sends the result to the main thread...here
//            JSONObject cow = jArray.getJSONObject(0);

            try {
//                   JSONObject cow = jArray.getJSONObject(0);
                Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_LONG).show();
                finish();


/*
                Tag.setText("");
                Name.setText("");
                RegNumber.setText("");
                ElectronicID.setText("");
                Owner.setText("");
                PercentagePure.setText("");
                DryMatterIntake.setText("");
                txtBirthDate.setText("");
                BirthWeight.setText("");
                txtWeaningDate.setText("");
                WeaningWeight.setText("");
                YearlingWeight.setText("");
                ColorMarkings.setText("");
                Breeder.setText("");
                Conception.setText("");
                SirTag.setText("");
                DamTag.setText("");
                HornStatus.setText("");
                Breed.setText("");
                BodyScore.setText("");
                txtCalvingDate.setText("");
                AmountPaid.setText("");
                AmountSold.setText("");
                Gender.setText("");
*/
                Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    // End
}
