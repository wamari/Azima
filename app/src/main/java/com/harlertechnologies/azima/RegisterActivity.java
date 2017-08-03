package com.harlertechnologies.azima;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
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

    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;


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
        loadIMEI();
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
        //// TODO: 8/2/17 Capture phone IMEI
        ////start next activity
        Toast.makeText(this,"Account created successfully toast", Toast.LENGTH_SHORT).show();
    }
    public void signUpFailed(){
        //present the user with an error
    }

    /**
     * Called when the 'loadIMEI' function is triggered.
     */
    public void loadIMEI(){
        //check if the READ_PHONE_STATE permission is already available
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED){
            //READ_PHONE_STATE permission has not been granted
            requestReadPhoneStatePermission();
        }else{
            //READ_PHONE_STATE PERMISSION HAS BEEN GRANTED
            doPermissionGrantedStuff();
        }
    }
    /**
     * Requests the READ_PHONE_STATE permission.
     * If the permission has been denied previously, a dialog will prompt the user to grant the
     * permission, otherwise it is requested directly.
     */
    private void requestReadPhoneStatePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_PHONE_STATE)){
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("Permission Request")
                    //.setMessage(getString(R.string.permission_read_phone_state_rationale))
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //re-request
                            ActivityCompat.requestPermissions(RegisterActivity.this,
                                    new String[]{Manifest.permission.READ_PHONE_STATE},
                                    MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
                        }
                    })
                    ///.setIcon(R.drawable.onlinlinew_warning_sign)
                    .show();
        }else{
            // READ_PHONE_STATE permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                   MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if(requestCode == MY_PERMISSIONS_REQUEST_READ_PHONE_STATE){
            // Received permission result for READ_PHONE_STATE permission.est.");
            // Check if the only required permission has been granted
            if (grantResults.length ==1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // READ_PHONE_STATE permission has been granted, proceed with displaying IMEI Number
                alertAlert(getString(R.string.permision_available_read_phone_state));
                doPermissionGrantedStuff();
            }else{
                alertAlert(getString(R.string.permissions_not_granted_read_phone_state));
            }
        }
    }

    private void alertAlert(String msg) {
        new AlertDialog.Builder(RegisterActivity.this)
                .setTitle("Permission Request")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do somthing here
                    }
                })
                //.setIcon(R.drawable.onlinlinew_warning_sign)
                .show();
    }

    public void doPermissionGrantedStuff(){
        //Have an  object of TelephonyManager
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //GET IMEI NUMBER OF PHONE
        String IMEINumber = tm.getDeviceId();
        //pass contents to toast
        Toast.makeText(this,"IMEI is "+IMEINumber, Toast.LENGTH_SHORT).show();
    }
}
