package com.example.macro.cattletrak;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().hide();  // works
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(R.layout.activity_main);

        Button btnLog = (Button) findViewById(R.id.btnLogin);

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getApplicationContext(), "Yup...you clicked it.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainSelector.class);
                EditText editMail = (EditText) findViewById(R.id.editEmail);
                EditText editPW = (EditText) findViewById(R.id.editPassword);
                String sEmail = editMail.getText().toString();
                String sPW = editPW.getText().toString();

                sEmail = sEmail.trim();

                intent.putExtra("Email", sEmail);
                intent.putExtra("PW", sPW);
                intent.putExtra("FB", "1");

                startActivity(intent);

            }
        });

        Button btnSign = (Button) findViewById(R.id.btnSignUp);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), sign_up.class);
                startActivity(intent2);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
