package com.monodeepdas112.projectoasis.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.monodeepdas112.projectoasis.Interfaces.MyCommonUISetUp;
import com.monodeepdas112.projectoasis.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by monodeep on 5/3/17.
 */

public class LoginActivity extends AppCompatActivity implements MyCommonUISetUp,View.OnClickListener {


    TextInputLayout emailWrapper,passwordWrapper;
    Button loginButton,forgotButton;
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpUI();
    }

    @Override
    public void setUpUI() {
        setContentView(R.layout.activity_login);
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
        final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher;
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validatePassword(String password) {

        int passwordMaxLength=Integer.parseInt(getString(R.string.password_max_length));
        int passwordMinLength=Integer.parseInt(getString(R.string.password_min_length));
        if (password.length()<=passwordMaxLength&&password.length()>=passwordMinLength)
            return true;
        else return false;
    }

    @Override
    public void onClick(View v) {
        hideKeyboard();
        switch (v.getId()){
            case R.id.loginButton:
                emailWrapper.setErrorEnabled(false);
                passwordWrapper.setErrorEnabled(false);
                switch (validateUIForms()){
                    case FORM_DETAILS_VALID:

                        break;
                    case EMAIL_EMPTY:
                        emailWrapper.setError(getString(R.string.activity_login_email_error));
                        emailWrapper.requestFocus();
                        break;
                    case EMAIL_INVALID:
                        emailWrapper.setError(getString(R.string.activity_login_email_error));
                        emailWrapper.requestFocus();
                        break;
                    case PASSWORD_INVALID:
                        passwordWrapper.setError(getString(R.string.activity_login_password_error));
                        passwordWrapper.requestFocus();
                }
                break;
            case R.id.forgotButton:
                startActivity(new Intent(getApplicationContext(),ForgotPasswordActivity.class));
                break;
        }
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private boolean authenticateDetails() {
        // TODO task for authenticating the email/password pair from the server
        return false;
    }
}