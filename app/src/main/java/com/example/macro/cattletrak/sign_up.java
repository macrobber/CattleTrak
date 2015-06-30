package com.example.macro.cattletrak;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class sign_up extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button btnReg = (Button) findViewById(R.id.btnRegister);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainSelector.class);
                EditText editMail = (EditText) findViewById(R.id.Email);
                EditText editPW = (EditText) findViewById(R.id.editPassword);
                EditText editReEnterPW = (EditText) findViewById(R.id.ReenterPassword);

                String sEmail = editMail.getText().toString();
                String sPW = editPW.getText().toString();
                String sPW2 = editReEnterPW.getText().toString();
                sEmail = sEmail.trim();

                intent.putExtra("Email", sEmail);
                intent.putExtra("PW", sPW);
                intent.putExtra("PW2", sPW2);
                intent.putExtra("FB", "-2"); // here -2 will be a new user manual creation

                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
