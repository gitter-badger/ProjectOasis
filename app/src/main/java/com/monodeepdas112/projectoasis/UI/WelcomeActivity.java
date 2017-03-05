package com.monodeepdas112.projectoasis.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.monodeepdas112.projectoasis.Interfaces.MyCommonUISetUp;
import com.monodeepdas112.projectoasis.R;

/**
 * Created by monodeep on 5/3/17.
 * This activity is used to let user to login or singup
 */

public class WelcomeActivity extends AppCompatActivity implements MyCommonUISetUp,View.OnClickListener
{
    private Button loginButton,signupButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setUpUI();
    }

    @Override
    public void setUpUI() {
        loginButton=(Button)findViewById(R.id.loginButton);
        signupButton=(Button)findViewById(R.id.signupButton);
        loginButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);
    }

    @Override
    public int validateUIForms() {
        return 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginButton:
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;
            case R.id.signupButton:
                break;
            default:
                Toast.makeText(this,"You clicked somewhere else",Toast.LENGTH_LONG).show();
        }
    }
}