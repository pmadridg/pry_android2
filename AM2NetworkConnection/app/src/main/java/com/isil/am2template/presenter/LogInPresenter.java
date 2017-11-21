package com.isil.am2template.presenter;

import android.util.Log;

import com.isil.am2template.storage.request.ApiClient;
import com.isil.am2template.storage.request.entity.LogInRaw;
import com.isil.am2template.storage.request.entity.LogInResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by emedinaa on 27/10/17.
 */

public class LogInPresenter {

    private LoginContract.View view;

    public void logIn(){
        if(view.validateForm()){
            authentication();
        }
    }
    public void goToUserRegister(){
        this.view.gotoUserRegister();
    }

    private void authentication() {
        String username= view.getUsername();
        String password= view.getPassword();

        final LogInRaw logInRaw= new LogInRaw();
        logInRaw.setUsername(username);
        logInRaw.setPassword(password);

        Call<LogInResponse> call= ApiClient.getMyApiClient().login(logInRaw);
        call.enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                view.hideLoading();
                if(response!=null){
                    LogInResponse logInResponse=null;

                    if(response.isSuccessful()){
                        logInResponse=response.body();
                        if(logInResponse!=null){
                            if(logInResponse.getStatus()==200){
                                Log.v("CONSOLE", "success "+logInResponse);
                                //saveSession(logInResponse);
                                view.gotoMain();
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
                    view.showMessage("Ocurrió un error");
                }
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                view.hideLoading();
                view.showMessage(t.getMessage());
                /*Toast.makeText(LoginActivity.this,
                        "error "+t.getMessage(),Toast.LENGTH_LONG).show();*/
                //hideLoading();
            }
        });
    }

    public void attachView(LoginContract.View view){
        this.view=view;
    }
    public void detachView(){
        this.view=null;
    }
}
