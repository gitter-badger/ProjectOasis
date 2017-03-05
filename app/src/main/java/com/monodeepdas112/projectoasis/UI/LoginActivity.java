package com.monodeepdas112.projectoasis.UI;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.monodeepdas112.projectoasis.Interfaces.MyCommonUISetUp;
import com.monodeepdas112.projectoasis.R;

/**
 * Created by monodeep on 5/3/17.
 */

public class LoginActivity extends AppCompatActivity implements MyCommonUISetUp,View.OnClickListener {

    private static final int WRONG_CREDENTIALS = 1;
    private static final int NO_INTERNET = 2;
    private static final String MSG_NO_INTERNET = "NO INTERNET";
    private static final String MSG_WRONG_CREDENTIALS = "WRONG CREDENTIALS";


    TextInputLayout emailWrapper,passwordWrapper;
    Button loginButton,forgotButton;
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpUI();
    }

    @Override
    public void setUpUI() {
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.activity_login_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        emailWrapper =(TextInputLayout)findViewById(R.id.usernameWrapper);
        passwordWrapper=(TextInputLayout)findViewById(R.id.passwordWrapper);
        loginButton=(Button)findViewById(R.id.loginButton);
        forgotButton=(Button)findViewById(R.id.forgotButton);

        loginButton.setOnClickListener(this);
        forgotButton.setOnClickListener(this);
    }

    @Override
    public int validateUIForms() {
        String email= emailWrapper.getEditText().getText().toString().trim();
        String password=passwordWrapper.getEditText().getText().toString().trim();

        if(TextUtils.isEmpty(email))
            return EMAIL_EMPTY;
        else if (!validateEmail(email))
            return EMAIL_INVALID;

        if ((TextUtils.isEmpty(password))||(!validatePassword(password)))
            return PASSWORD_INVALID;

        return FORM_DETAILS_VALID;
    }

    private boolean validateEmail(String email) {
        //TODO add the code to validate email
        return true;
    }

    private boolean validatePassword(String password) {
        //TODO add the code to validate password
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginButton:
                emailWrapper.setErrorEnabled(false);
                passwordWrapper.setErrorEnabled(false);
                switch (validateUIForms()){
                    case FORM_DETAILS_VALID:
                        if (authenticateDetails()){
                            // TODO task if email/password is valid
                        }else {
                            // mapping the wrong credentials but need to know about no internet
                            // 3 outputs to map 1- successful login 2-wrong credentials 3-no-internet ask
                            // state in the function configureSnackbar below can be passed as per cases
                            // here wrong credentials
                            configureSnackbar(v,WRONG_CREDENTIALS,MSG_WRONG_CREDENTIALS);
                        }
                        break;
                    case EMAIL_EMPTY:
                        emailWrapper.setError(getString(R.string.activity_login_email_error));
                        break;
                    case EMAIL_INVALID:
                        emailWrapper.setError(getString(R.string.activity_login_email_error));
                        break;
                    case PASSWORD_INVALID:
                        passwordWrapper.setError(getString(R.string.activity_login_password_error));
                }
                break;
            case R.id.forgotButton:
                Toast.makeText(getApplicationContext(),"Forgot button working properly",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                hideKeyboard();
        }
    }

    private void hideKeyboard() {

    }

    private void configureSnackbar(View v,int state,String message){
        final Snackbar snackbar = Snackbar.make(v,message,Snackbar.LENGTH_LONG);
        View view=snackbar.getView();
        TextView tv= (TextView)view.findViewById(android.support.design.R.id.snackbar_text);
        switch (state){
            case WRONG_CREDENTIALS:
                tv.setTextColor(getResources().getColor(R.color.contrastColor));
                break;
            case NO_INTERNET:
                tv.setTextColor(getResources().getColor(R.color.cardBackground));
        }
        snackbar.show();
    }

    private boolean authenticateDetails() {
        // TODO task for authenticating the email/password pair from the server
        return false;
    }
}
