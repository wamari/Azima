package com.harlertechnologies.azima;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.aware.PublishConfig;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextFullname;
    private EditText editTextEmail;
    private EditText editTextIDNo;
    private EditText editTextTelNo;
    private Button buttonCreate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initialize views
        editTextFullname = (EditText) findViewById(R.id.editTextFullname);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextIDNo = (EditText) findViewById(R.id.editTextIDNo);
        editTextTelNo= (EditText) findViewById(R.id.editTextTelNo);
        buttonCreate = (Button) findViewById(R.id.buttonCreate);

        //set listener for the button
        buttonCreate.setOnClickListener(this);
    }

    public void signUp(){

        if(!validate()){
            signUpFailed();
            return;
        }
        //disable create button
        //buttonCreate.setEnabled(false);

        final ProgressDialog loading;
        loading=ProgressDialog.show(this,"Creating account...","Please Wait....", false, false);

        //get values from Edit text
        String fullName = editTextFullname.getText().toString();
        String email = editTextEmail.getText().toString();
        String IDNo = editTextIDNo.getText().toString();
        String TelNo = editTextTelNo.getText().toString();

        //// TODO: 7/31/17 Implement own sign up logic here
        //// TODO: 7/31/17 Capture phone IMEI/Unique id

        new android.os.Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        //on complete call signup success or signup failed depending on success
                        signUpSuccess();
                        //signUpFailed();
                        loading.dismiss();
                    }
                }, 3000);
    }
    /*validate data input
     */
    public boolean validate() {
        boolean valid=true;

        String fullName = editTextFullname.getText().toString();
        String email = editTextEmail.getText().toString();
        String IDNo = editTextIDNo.getText().toString();
        String TelNo = editTextTelNo.getText().toString();

        if(fullName.isEmpty() || fullName.length()<5){
            editTextFullname.setError("Name too short");
            valid=false;
        }else{
            editTextFullname.setError(null);
        }

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Enter a valid email address");
            valid = false;
        }else{
            editTextEmail.setError(null);
        }

        if(IDNo.isEmpty() || IDNo.length()<8){
            editTextIDNo.setError("ID Number too short");
            valid = false;
        }else{
            editTextIDNo.setError(null);
        }

        if(TelNo.isEmpty() || TelNo.length()<10){
            editTextTelNo.setError("Enter a valid phone number");
            valid = false;
        }else{
            editTextTelNo.setError(null);
        }
        return valid;
    }

    public void onClick(View v){
        if(v == buttonCreate){
            signUp();
        }
    }

    public void signUpSuccess(){
        //start next activity
        Toast.makeText(this,"Account created successfully", Toast.LENGTH_SHORT).show();
    }
    public void signUpFailed(){
        //present the user with an error
    }

}
