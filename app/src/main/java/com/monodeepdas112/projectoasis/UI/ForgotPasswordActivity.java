package com.monodeepdas112.projectoasis.UI;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.monodeepdas112.projectoasis.Interfaces.MyCommonUISetUp;
import com.monodeepdas112.projectoasis.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by monodeep on 6/3/17.
 */

public class ForgotPasswordActivity extends AppCompatActivity implements MyCommonUISetUp,View.OnClickListener {

    private TextInputLayout emailWrapper;
    private Button sendOTPButton;
    private Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpUI();
    }

    @Override
    public void onClick(View v) {
        hideKeyboard();
        switch (v.getId()){
            case R.id.sendOTPButton:
                emailWrapper.setErrorEnabled(false);
                switch (validateUIForms()){
                    case FORM_DETAILS_VALID:
                        Toast.makeText(getApplicationContext(),"Form Details valid... Sending OTP",
                                Toast.LENGTH_LONG).show();
                        break;
                    case EMAIL_EMPTY:
                        emailWrapper.setError(getString(R.string.activity_login_email_error));
                        emailWrapper.requestFocus();
                        break;
                    case EMAIL_INVALID:
                        emailWrapper.setError(getString(R.string.activity_login_email_error));
                        emailWrapper.requestFocus();
                        break;
                }
                break;
        }
    }

    private boolean validateEmail(String email) {
        final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher;
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void setUpUI() {
        setContentView(R.layout.activity_forgot_password);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.activity_forgot_password));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        emailWrapper=(TextInputLayout)findViewById(R.id.emailWrapper);
        sendOTPButton=(Button)findViewById(R.id.sendOTPButton);

        sendOTPButton.setOnClickListener(this);
    }

    @Override
    public int validateUIForms() {
        String emailOTP = emailWrapper.getEditText().getText().toString().trim();
        if (TextUtils.isEmpty(emailOTP))
            return EMAIL_EMPTY;

        if (!validateEmail(emailOTP))
            return EMAIL_INVALID;

        return FORM_DETAILS_VALID;
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}