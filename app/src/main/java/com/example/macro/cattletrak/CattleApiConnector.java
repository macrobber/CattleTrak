package com.example.macro.cattletrak;

import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by mack on 4/25/15.
 */
public class CattleApiConnector {

    public JSONArray GetAllCows(String uid) {

        String result = "";
        InputStream isr = null;

        try{
            HttpURLConnection urlConnection = null;
            URL url = new URL("http://www.primenumberfarms.com/cattle/get_all.php?uid="+uid);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            isr = urlConnection.getInputStream();
        }
        catch(Exception e) {
            Log.e("LOG_TAG", "PN Error in http connection " + e.toString());
        }
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader(isr, "iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result = sb.toString();
        }
        catch (Exception e) {
            Log.e("LOG_TAG", "PN Error converting result " + e.toString());
        }

        JSONArray jArray = null;

        // Parse code goes here....
        try {
            jArray = new JSONArray(result);
            //Theoreticaly, the above line of code actually pushes the result into a JSON array called jArray

        }
        catch (Exception e) {
            Log.e("LOG_TAG", "Error parsing data " + e.toString());
        }
        return jArray;
    }

    // what follows is exactly the same code as above - the only change is in the URL which
    // was modified to pass a value in the URL code.
    // look at UpdateCow.php for example on how to pull this from the URL string
    public JSONArray GetCowDetails(String Tag, String uid)
    {
        String result = "";
        InputStream isr = null;

        String temp = "http://www.primenumberfarms.com/cattle/details.php?Tag="+Tag+"&uid="+uid;


        Log.v(Tag, "PN ******* The URL I am trying is: "+temp);


        try{
            HttpURLConnection urlConnection = null;
            URL url = new URL("http://www.primenumberfarms.com/cattle/details.php?Tag="+Tag+"&uid="+uid);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            isr = urlConnection.getInputStream();
        }
        catch(Exception e) {
            Log.e("LOG_TAG", "PN Error in http connection " + e.toString());
        }
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader(isr, "iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result = sb.toString();
        }
        catch (Exception e) {
            Log.e("LOG_TAG", "PN Error converting result " + e.toString());
        }

        JSONArray jArray = null;

        // Parse code goes here....
        try {
            jArray = new JSONArray(result);
            //Theoreticaly, the above line of code actually pushes the result into a JSON array called jArray

        }
        catch (Exception e) {
            Log.e("LOG_TAG", "Error parsing data " + e.toString());
        }
        return jArray;
    }

    public JSONArray UpdateCow(String Tag, String Name, String Brand, String RegNumber) {

        String result = "";
        InputStream isr = null;

        String temp = "http://www.primenumberfarms.com/update_cow.php?Tag="+Tag+"&Name="+Name+"&Brand="+Brand+"&RegNumber="+RegNumber;

        Log.v(Tag, "PN ******* The URL I am trying is: "+temp);

        try{
            HttpURLConnection urlConnection = null;
            URL url = new URL("http://www.primenumberfarms.com/cattle/update.php?Tag="+Tag+"&Name="+Name+"&Brand="+Brand+"&RegNumber="+RegNumber);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            isr = urlConnection.getInputStream();
        }
        catch(Exception e) {
            Log.e("LOG_TAG", "PN Error in http connection " + e.toString());
        }
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader(isr, "iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result = sb.toString();
        }
        catch (Exception e) {
            Log.e("LOG_TAG", "PN Error converting result " + e.toString());
        }

        JSONArray jArray = null;

        // Parse code goes here....
        try {
            jArray = new JSONArray(result);
            //Theoreticaly, the above line of code actually pushes the result into a JSON array called jArray

        }
        catch (Exception e) {
            Log.e("LOG_TAG", "Error parsing data " + e.toString());
        }
        return jArray;
    }


    public JSONArray MyLogin(String email, String pw, String uid) {

        String result = "";
        InputStream isr = null;

        String temp = "http://www.primenumberfarms.com/PTLogin.php?email="+email+"&pw="+pw;

        Log.v("LOG_TAG", "PN ******* The URL I am trying is: "+temp);

        try{
            HttpURLConnection urlConnection = null;
            URL url = new URL("http://www.primenumberfarms.com/PTLogin.php?email="+email+"&pw="+pw);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            isr = urlConnection.getInputStream();
        }
        catch(Exception e) {
            Log.e("LOG_TAG", "PN Error in http connection " + e.toString());
        }
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader(isr, "iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result = sb.toString();
        }
        catch (Exception e) {
            Log.e("LOG_TAG", "PN Error converting result " + e.toString());
        }

        JSONArray jArray = null;

        // Parse code goes here....
        try {
            jArray = new JSONArray(result);
            //Theoreticaly, the above line of code actually pushes the result into a JSON array called jArray

        }
        catch (Exception e) {
            Log.e("LOG_TAG", "Error parsing data " + e.toString());
        }
        return jArray;
    }

    // what follows is exactly the same code as above - the only change is in the URL which
    // was modified to pass a value in the URL code.
    // look at UpdateCow.php for example on how to pull this from the URL string
    public JSONArray AddCattle(String uid, String Tag, String Name, String RegNumber, String ElectronicID, String Owner, String PercentagePure, String DryMatterIntake, String BirthDate, String BirthWeight, String WeaningDate, String WeaningWeight, String YearlingWeight, String Color, String Breeder, String Conception, String SirTag, String DamTag, String HornStatus, String Breed, String BodyScore, String CalvingDate, String AmountPaid, String AmountSold, String Gender)
    {
        String result = "";
        InputStream isr = null;

        try{
            HttpURLConnection urlConnection = null;
            URL url = new URL("http://www.primenumberfarms.com/cattle/add.php?uid="+uid+"&Tag="+Tag+"&Name="+Name+"&RegNumber="+RegNumber+"&ElectronicID="+ElectronicID+"&Owner="+Owner+"&PercentagePure="+PercentagePure+"&DryMatterIntake="+DryMatterIntake+"&BirthDate="+BirthDate+"&BirthWeight="+BirthWeight+"&WeaningDate="+WeaningDate+"&WeaningWeight="+WeaningWeight+"&YearlingWeight="+YearlingWeight+"&Color="+Color+"&Breeder="+Breeder+"&Conception="+Conception+"&SirTag="+SirTag+"&DamTag="+DamTag+"&HornStatus="+HornStatus+"&Breed="+Breed+"&BodyScore="+BodyScore+"&CalvingDate="+CalvingDate+"&AmountPaid="+AmountPaid+"&AmountSold="+AmountSold+"&Gender="+Gender);
            String temp = "http://www.primenumberfarms.com/cattle/update.php?uid="+uid+"&Tag="+Tag+"&Name="+Name+"&RegNumber="+RegNumber+"&ElectronicID="+ElectronicID+"&Owner="+Owner+"&PercentagePure="+PercentagePure+"&DryMatterIntake="+DryMatterIntake+"&BirthDate="+BirthDate+"&BirthWeight="+BirthWeight+"&WeaningDate="+WeaningDate+"&WeaningWeight="+WeaningWeight+"&YearlingWeight="+YearlingWeight+"&Color="+Color+"&Breeder="+Breeder+"&Conception="+Conception+"&SirTag="+SirTag+"&DamTag="+DamTag+"&HornStatus="+HornStatus+"&Breed="+Breed+"&BodyScore="+BodyScore+"&CalvingDate="+CalvingDate+"&AmountPaid="+AmountPaid+"&AmountSold="+AmountSold+"&Gender="+Gender;

            Log.v("LOG_TAG", "PN **** Update Cow ***  The URL I am trying is: "+temp);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            isr = urlConnection.getInputStream();
        }
        catch(Exception e) {
            Log.e("LOG_TAG", "PN Error in http connection " + e.toString());
        }
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader(isr, "iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result = sb.toString();
        }
        catch (Exception e) {
            Log.e("LOG_TAG", "PN Error converting result " + e.toString());
        }

        JSONArray jArray = null;

        // Parse code goes here....
        try {
            jArray = new JSONArray(result);
            //Theoreticaly, the above line of code actually pushes the result into a JSON array called jArray

        }
        catch (Exception e) {
            Log.e("LOG_TAG", "Error parsing data " + e.toString());
        }
        return jArray;
    }

    // what follows is exactly the same code as above - the only change is in the URL which
    // was modified to pass a value in the URL code.
    // look at UpdateCow.php for example on how to pull this from the URL string
    public JSONArray DelCattle(String Tag, String uid)
    {
        String result = "";
        InputStream isr = null;

        try{
            HttpURLConnection urlConnection = null;
            URL url = new URL("http://www.primenumberfarms.com/cattle/deactivate.php?uid="+uid+"&Tag="+Tag);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            isr = urlConnection.getInputStream();
        }
        catch(Exception e) {
            Log.e("LOG_TAG", "PN Error in http connection " + e.toString());
        }
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader(isr, "iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result = sb.toString();
        }
        catch (Exception e) {
            Log.e("LOG_TAG", "PN Error converting result " + e.toString());
        }

        JSONArray jArray = null;

        // Parse code goes here....
        try {
            jArray = new JSONArray(result);
            //Theoreticaly, the above line of code actually pushes the result into a JSON array called jArray

        }
        catch (Exception e) {
            Log.e("LOG_TAG", "Error parsing data " + e.toString());
        }
        return jArray;
    }

    // what follows is exactly the same code as above - the only change is in the URL which
    // was modified to pass a value in the URL code.
    // look at UpdateCow.php for example on how to pull this from the URL string
    public JSONArray UpdateCattle(String uid, String Tag, String Name, String RegNumber, String ElectronicID, String Owner, String PercentagePure, String DryMatterIntake, String BirthDate, String BirthWeight, String WeaningDate, String WeaningWeight, String YearlingWeight, String Color, String Breeder, String Conception, String SirTag, String DamTag, String HornStatus, String Breed, String BodyScore, String CalvingDate, String AmountPaid, String AmountSold, String Gender)
    {
        String result = "";
        InputStream isr = null;

        try{
            HttpURLConnection urlConnection = null;
            URL url = new URL("http://www.primenumberfarms.com/cattle/update.php?uid="+uid+"&Tag="+Tag+"&Name="+Name+"&RegNumber="+RegNumber+"&ElectronicID="+ElectronicID+"&Owner="+Owner+"&PercentagePure="+PercentagePure+"&DryMatterIntake="+DryMatterIntake+"&BirthDate="+BirthDate+"&BirthWeight="+BirthWeight+"&WeaningDate="+WeaningDate+"&WeaningWeight="+WeaningWeight+"&YearlingWeight="+YearlingWeight+"&Color="+Color+"&Breeder="+Breeder+"&Conception="+Conception+"&SirTag="+SirTag+"&DamTag="+DamTag+"&HornStatus="+HornStatus+"&Breed="+Breed+"&BodyScore="+BodyScore+"&CalvingDate="+CalvingDate+"&AmountPaid="+AmountPaid+"&AmountSold="+AmountSold+"&Gender="+Gender);

            String temp = "http://www.primenumberfarms.com/cattle/update.php?uid="+uid+"&Tag="+Tag+"&Name="+Name+"&RegNumber="+RegNumber+"&ElectronicID="+ElectronicID+"&Owner="+Owner+"&PercentagePure="+PercentagePure+"&DryMatterIntake="+DryMatterIntake+"&BirthDate="+BirthDate+"&BirthWeight="+BirthWeight+"&WeaningDate="+WeaningDate+"&WeaningWeight="+WeaningWeight+"&YearlingWeight="+YearlingWeight+"&Color="+Color+"&Breeder="+Breeder+"&Conception="+Conception+"&SirTag="+SirTag+"&DamTag="+DamTag+"&HornStatus="+HornStatus+"&Breed="+Breed+"&BodyScore="+BodyScore+"&CalvingDate="+CalvingDate+"&AmountPaid="+AmountPaid+"&AmountSold="+AmountSold+"&Gender="+Gender;

            Log.v("LOG_TAG", "PN **** Add Cow ***  The URL I am trying is: "+temp);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            isr = urlConnection.getInputStream();
        }
        catch(Exception e) {
            Log.e("LOG_TAG", "PN Error in http connection " + e.toString());
        }
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader(isr, "iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result = sb.toString();
        }
        catch (Exception e) {
            Log.e("LOG_TAG", "PN Error converting result " + e.toString());
        }

        JSONArray jArray = null;
        // Parse code goes here....
        try {
            jArray = new JSONArray(result);
            //Theoreticaly, the above line of code actually pushes the result into a JSON array called jArray
        }
        catch (Exception e) {
            Log.e("LOG_TAG", "Error parsing data " + e.toString());
        }
        return jArray;
    }

    // what follows is exactly the same code as above - the only change is in the URL which
    // was modified to pass a value in the URL code.
    // look at UpdateCow.php for example on how to pull this from the URL string
    public JSONArray AddHealth(String uid, String Tag, String AnimalType, String med1, String med2, String med3, String Notes)
    {
        String result = "";
        InputStream isr = null;

        try{
            HttpURLConnection urlConnection = null;

            URL url = new URL("http://www.primenumberfarms.com/Health/add.php?uid="+uid+"&Tag="+Tag+"&AnimalType="+AnimalType+"&med1="+med1+"&med2="+med2+"&med3="+med3+"&Notes="+Notes);
//            URL url = new URL("http://www.primenumberfarms.com/cattle/add.php?uid="+uid+"&Tag="+Tag+"&Name="+Name+"&RegNumber="+RegNumber+"&ElectronicID="+ElectronicID+"&Owner="+Owner+"&PercentagePure="+PercentagePure+"&DryMatterIntake="+DryMatterIntake+"&BirthDate="+BirthDate+"&BirthWeight="+BirthWeight+"&WeaningDate="+WeaningDate+"&WeaningWeight="+WeaningWeight+"&YearlingWeight="+YearlingWeight+"&Color="+Color+"&Breeder="+Breeder+"&Conception="+Conception+"&SirTag="+SirTag+"&DamTag="+DamTag+"&HornStatus="+HornStatus+"&Breed="+Breed+"&BodyScore="+BodyScore+"&CalvingDate="+CalvingDate+"&AmountPaid="+AmountPaid+"&AmountSold="+AmountSold+"&Gender="+Gender);
            String temp = "http://www.primenumberfarms.com/Health/add.php?uid="+uid+"&Tag="+Tag+"&AnimalType="+AnimalType+"&med1="+med1+"&med2="+med2+"&med3="+med3+"&Notes="+Notes;

            Log.v("LOG_TAG", "PN **** Update Cow ***  The URL I am trying is: "+temp);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            isr = urlConnection.getInputStream();
        }
        catch(Exception e) {
            Log.e("LOG_TAG", "PN Error in http connection " + e.toString());
        }
        try {
            BufferedReader reader = new BufferedReader (new InputStreamReader(isr, "iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            isr.close();

            result = sb.toString();
        }
        catch (Exception e) {
            Log.e("LOG_TAG", "PN Error converting result " + e.toString());
        }

        JSONArray jArray = null;

        // Parse code goes here....
        try {
            jArray = new JSONArray(result);
            //Theoreticaly, the above line of code actually pushes the result into a JSON array called jArray

        }
        catch (Exception e) {
            Log.e("LOG_TAG", "Error parsing data " + e.toString());
        }
        return jArray;
    }


}
