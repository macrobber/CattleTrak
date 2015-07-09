package com.example.macro.cattletrak;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
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
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.Callable;


public class CattleAdd extends ActionBarActivity {
    private String uid;
    private EditText Tag;
    private EditText Name;
    private EditText RegNumber;
    private EditText ElectronicID;
    private EditText Owner;
    private EditText PercentagePure;
    private EditText DryMatterIntake;
    private EditText BirthWeight;
    private EditText WeaningWeight;
    private EditText YearlingWeight;
    private EditText ColorMarkings;
    private EditText Breeder;
    private EditText Conception;
    private EditText SirTag;
    private EditText DamTag;
    private EditText HornStatus;
    private EditText Breed;
    private EditText BodyScore;
    private EditText AmountPaid;
    private EditText AmountSold;
    private EditText Gender;

    private TextView txtBirthDate;
    private TextView txtWeaningDate;
    private TextView txtCalvingDate;

    private String sTag;
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

    // variables for date picker here

    int year_x, month_x, day_x;
    static final int DILOG_ID = 0;
    static final int DATE_PICKER_BIRTHDATE = 0;
    static final int DATE_PICKER_WEANINGDATE = 1;
    static final int DATE_PICKER_CALVINGDATE = 2;

    // variables for date picker here


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cattle_add);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            return;
        }


        this.uid = extras.getString("uid");
        this.Tag = (EditText) this.findViewById(R.id.Tag);
        this.Name = (EditText) this.findViewById(R.id.Name);
        this.RegNumber = (EditText) this.findViewById(R.id.RegNumber);
        this.ElectronicID = (EditText) this.findViewById(R.id.ElectronicID);
        this.Owner = (EditText) this.findViewById(R.id.Owner);
        this.PercentagePure = (EditText) this.findViewById(R.id.PercentagePure);
        this.DryMatterIntake = (EditText) this.findViewById(R.id.DryMatterIntake);
        this.BirthWeight = (EditText) this.findViewById(R.id.BirthWeight);
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
        this.AmountPaid = (EditText) this.findViewById(R.id.AmountPaid);
        this.AmountSold = (EditText) this.findViewById(R.id.AmountSold);
        this.Gender = (EditText) this.findViewById(R.id.Gender);

// **************************** Code for Date Picker Inside onCreate ***************************
        txtBirthDate = (TextView) findViewById(R.id.tBirthDate);
        txtWeaningDate = (TextView) findViewById(R.id.tWeaningDate);
        txtCalvingDate = (TextView) findViewById(R.id.tCalvingDate);
//        bDate = (Button) findViewById(R.id.btnChangeDate);

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);


        showDialogOnButtonClick();
// **************************** Code for Date Picker Inside onCreate ***************************

// Code for the Update/Add button

        Button bAddCattle = (Button) findViewById(R.id.btnAddCattle);
        bAddCattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sTag = Tag.getText().toString();
                sName = Name.getText().toString();
                sName = sName.trim();
                sRegNumber = RegNumber.getText().toString();
                sElectronicID = ElectronicID.getText().toString();
                sOwner = Owner.getText().toString();
                sOwner = sOwner.trim();
                sPercentagePure = PercentagePure.getText().toString();
                sDryMatterIntake = DryMatterIntake.getText().toString();
                sBirthDate = txtBirthDate.getText().toString();
                sBirthWeight = BirthWeight.getText().toString();
                sWeaningDate = txtWeaningDate.getText().toString();
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
                sCalvingDate = txtCalvingDate.getText().toString();
                sAmountPaid = AmountPaid.getText().toString();
                sAmountSold = AmountSold.getText().toString();
                sGender = Gender.getText().toString();
                sGender = sGender.trim();

                if(sTag != null) {
                    new AddCow().execute(new CattleApiConnector());
                }
            }
        });
// end of code for Add / Edit button

    } // Closes onCreate

    // **************************** Code for Date Picker Outside onCreate***************************
    // These establish onClickListeners for each of the textBoxes for date
    public void showDialogOnButtonClick() {

        txtBirthDate = (TextView) findViewById(R.id.tBirthDate);
        txtBirthDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DILOG_ID);
                    }
                }
        );
        txtWeaningDate = (TextView) findViewById(R.id.tWeaningDate);
        txtWeaningDate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(1);
                    }
                }
        );

        txtCalvingDate = (TextView) findViewById(R.id.tCalvingDate);
        txtCalvingDate.setOnClickListener(
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

/*
        if(id == DILOG_ID)
            return new DatePickerDialog(this, dpickerListner, year_x, month_x, day_x);
        return null;
*/
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
            txtBirthDate.setText(new StringBuilder()
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
            txtWeaningDate.setText(new StringBuilder()
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
            txtCalvingDate.setText(new StringBuilder()
                            .append(year_x).append("-").append(month_x).append("-").append(day_x)

            );
        }
    };

    // **************************** Date Picker runs to here outside onCreate***********************


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cattle_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==android.R.id.home) {
            onBackPressed();
            finish();
            return true;
        }


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
            return params[0].AddCattle(uid, sTag, sName, sRegNumber, sElectronicID, sOwner, sPercentagePure, sDryMatterIntake, sBirthDate, sBirthWeight, sWeaningDate, sWeaningWeight, sYearlingWeight, sColor, sBreeder, sConception, sSirTag, sDamTag, sHornStatus, sBreed, sBodyScore, sCalvingDate, sAmountPaid, sAmountSold, sGender);
        }

        @Override
        protected void onPostExecute(JSONArray jArray) {
            // once do in background is done - it sends the result to the main thread...here
//            JSONObject cow = jArray.getJSONObject(0);
            try {
             //   JSONObject cow = jArray.getJSONObject(0);
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

                Toast.makeText(getApplicationContext(), "Record Added", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
