package com.isil.am2template;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.isil.am2template.model.entity.UserEntity;
import com.isil.am2template.storage.PreferencesHelper;
import com.isil.am2template.storage.request.ApiClient;
import com.isil.am2template.storage.request.StorageConstant;
import com.isil.am2template.storage.request.entity.LogInBLRaw;
import com.isil.am2template.storage.request.entity.LogInBLResponse;
import com.isil.am2template.storage.request.entity.LogInRaw;
import com.isil.am2template.storage.request.entity.LogInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private Button btnLogin,btnRegister;
    private EditText eteUsername;
    private EditText etePassword;
    private String username;
    private String password;

    private View flayLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
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
                if (validateForm()) {
                    //gotoMain();
                    //logIn();
                    authenticationBL();
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUserRegister();
            }
        });
    }

    private void gotoUserRegister() {
        Intent intent= new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    private void gotoMain() {
        //savePreferences();
        Intent intent= new Intent(this,MenuActivity.class);
        startActivity(intent);
    }

    /**
     * Podemos probar con:
     * username : admin@admin.com
     * password: 123456
     */
    private void logIn() {
        showLoading();
        final LogInRaw logInRaw= new LogInRaw();
        logInRaw.setUsername(username);
        logInRaw.setPassword(password);

        Call<LogInResponse> call= ApiClient.getMyApiClient().login(logInRaw);
        call.enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                hideLoading();
                if(response!=null){
                    LogInResponse logInResponse=null;

                    if(response.isSuccessful()){
                        logInResponse=response.body();
                        if(logInResponse!=null){
                            if(logInResponse.getStatus()==200){
                                Log.v("CONSOLE", "success "+logInResponse);
                                //saveSession(logInResponse);
                                gotoMain();
                            }else{
                                Log.v("CONSOLE", "error "+logInResponse);
                            }
                        }
                    }else{
                        Log.v("CONSOLE", "error "+logInResponse);

                        /*JSONObject jsonObject = null;
                        try {
                            jsonObject=new JSONObject (response.errorBody().string());
                        }catch (Exception e){
                            jsonObject= new JSONObject();
                        }

                        logInResponse= GsonHelper.responseToObject(jsonObject.toString());
                        showMessage(logInResponse.getMsg());*/
                    }
                }else{
                    showMessage("Ocurrió un error");
                }
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,
                        "error "+t.getMessage(),Toast.LENGTH_LONG).show();
                hideLoading();
            }
        });

    }


    private void showLoading(){
        flayLoading.setVisibility(View.VISIBLE);
    }

    private void hideLoading(){
        flayLoading.setVisibility(View.GONE);
    }

    private void showMessage(String message){
        Toast.makeText(LoginActivity.this,
                "LogIn "+message,Toast.LENGTH_LONG).show();
    }

   /* private void saveSession(LogInResponse logInResponse) {
        UserEntity userEntity=logInResponse.getData();
        PreferencesHelper.saveSession(this, userEntity.getFirstname(),userEntity.getId());
    }*/

    private void savePreferences() {

        PreferencesHelper.saveSession(this,username,password);
    }

    private boolean validateForm() {
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

    private void authenticationBL(){

        username= eteUsername.getText().toString();
        password= etePassword.getText().toString();


        final LogInBLRaw logInRaw= new LogInBLRaw();
        logInRaw.setLogin(username);
        logInRaw.setPassword(password);



        Call<LogInBLResponse> call= ApiClient.getMyApiClient().logInBL(
                StorageConstant.APPLICATIONID,
                StorageConstant.RESTAPIKEY,logInRaw);


        call.enqueue(new Callback<LogInBLResponse>() {

            @Override
            public void onResponse(Call<LogInBLResponse> call, Response<LogInBLResponse> response) {
                hideLoading();
                if(response!=null){
                    LogInBLResponse logInResponse=null;

                    if(response.isSuccessful()){
                        logInResponse=response.body();
                        if(logInResponse!=null){
                            saveSession(logInResponse);
                            gotoMain();

                        }

                    }else{

                        Log.v("CONSOLE", "error "+logInResponse);

                    }

                }else{

                    showMessage("Ocurrió un error");

                }

            }

            @Override

            public void onFailure(Call<LogInBLResponse> call, Throwable t) {

                hideLoading();

                showMessage(t.getMessage());

            }

        });

    }

    private void saveSession(LogInBLResponse logInResponse) {

        saveBLSession(logInResponse.getObjectId(),logInResponse.getEmail(),logInResponse.getToken());

    }


    public void saveBLSession(String objectId, String username, String token) {

        PreferencesHelper.saveBLSession(this,objectId,username,token);

    }



}
