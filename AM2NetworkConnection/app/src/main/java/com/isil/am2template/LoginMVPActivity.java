package com.isil.am2template;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.isil.am2template.model.entity.UserEntity;
import com.isil.am2template.presenter.LogInPresenter;
import com.isil.am2template.presenter.LoginContract;
import com.isil.am2template.storage.PreferencesHelper;
import com.isil.am2template.storage.request.entity.LogInResponse;


public class LoginMVPActivity extends AppCompatActivity implements LoginContract.View {

    private Button btnLogin,btnRegister;
    private EditText eteUsername;
    private EditText etePassword;
    private String username;
    private String password;

    private View flayLoading;

    private LogInPresenter logInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpPresenter();
        init();
    }

    private void setUpPresenter(){
        logInPresenter= new LogInPresenter();
        logInPresenter.attachView(this);
    }

    private void init() {
        //ui
        eteUsername=(EditText)findViewById(R.id.eteUsername);
        etePassword=(EditText)findViewById(R.id.etePassword);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        btnRegister=(Button)findViewById(R.id.btnRegister);
        flayLoading=findViewById(R.id.flayLoading);

        //events
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInPresenter.logIn();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gotoUserRegister();
                logInPresenter.goToUserRegister();
            }
        });
    }


    private void saveSession(LogInResponse logInResponse) {
        UserEntity userEntity=logInResponse.getData();
        PreferencesHelper.saveSession(this, userEntity.getFirstname(),userEntity.getId());
    }

    private void savePreferences() {

        PreferencesHelper.saveSession(this,username,password);
    }


    @Override
    public void showLoading() {
        flayLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        flayLoading.setVisibility(View.GONE);
    }

    @Override
    public void gotoMain() {
        //savePreferences();
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoUserRegister() {
        Intent intent= new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(LoginMVPActivity.this,
                "LogIn "+message,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean validateForm() {
        username= eteUsername.getText().toString();
        password= etePassword.getText().toString();

        if(username.isEmpty())
        {
            eteUsername.setError("Error campo username");
            return false;
        }
        if(password.isEmpty())
        {
            etePassword.setError("Error campo password");
            return false;
        }
        return true;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
