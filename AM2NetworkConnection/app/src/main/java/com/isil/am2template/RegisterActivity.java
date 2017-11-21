package com.isil.am2template;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.isil.am2template.storage.request.ApiClient;
import com.isil.am2template.storage.request.StorageConstant;
import com.isil.am2template.storage.request.entity.LogInBLRaw;
import com.isil.am2template.storage.request.entity.LogInBLResponse;
import com.isil.am2template.storage.request.entity.UserBLRaw;
import com.isil.am2template.storage.request.entity.UserBLResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    private Button btnRegister;
    private EditText eteName;
    private EditText eteEmail;
    private EditText etePassword;
    private EditText etePassword2;
    private String name;
    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    private void init() {
        //ui
        eteName=(EditText)findViewById(R.id.editText);
        eteEmail=(EditText)findViewById(R.id.editText2);
        etePassword=(EditText)findViewById(R.id.editText3);
        etePassword2=(EditText)findViewById(R.id.editText4);
        btnRegister=(Button)findViewById(R.id.btnRegister);


        //events
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    //gotoMain();
                    //logIn();
                    registrationBL();
                    gotoLogIn();
                }
            }
        });

    }

    private void showMessage(String message){
        Toast.makeText(this,
                "LogIn "+message,Toast.LENGTH_LONG).show();
    }

    public void registrationBL(){

        name= eteName.getText().toString();
        email= eteEmail.getText().toString();
        password= etePassword.getText().toString();


        final UserBLRaw registerRaw= new UserBLRaw();
        registerRaw.setName(name);
        registerRaw.setEmail(email);
        registerRaw.setPassword(password);



        Call<UserBLResponse> call= ApiClient.getMyApiClient().registerBL(
                StorageConstant.APPLICATIONID,
                StorageConstant.RESTAPIKEY,registerRaw);

        call.enqueue(new Callback<UserBLResponse>() {
            @Override
            public void onResponse(Call<UserBLResponse> call, Response<UserBLResponse> response) {

                if(response!=null){
                    UserBLResponse userResponse=null;

                    if(response.isSuccessful()){
                        userResponse=response.body();
                        showMessage("Registrado Correctamente");

                    }else{
                        showMessage("Ocurrió un error");
                    }
                }
            }

            @Override
            public void onFailure(Call<UserBLResponse> call, Throwable t) {

                showMessage(t.getMessage());
            }
        });
    }

    private boolean validateForm() {
        name= eteName.getText().toString();
        email= eteEmail.getText().toString();
        password= etePassword.getText().toString();

        if(name.isEmpty())
        {
            eteName.setError("Error campo username");
            return false;
        }
        if(email.isEmpty())
        {
            eteEmail.setError("Error campo username");
            return false;
        }
        if(password.isEmpty())
        {
            etePassword.setError("Error campo password");
            return false;
        }
        return true;
    }


    private void gotoLogIn() {
        finish();
    }
}
