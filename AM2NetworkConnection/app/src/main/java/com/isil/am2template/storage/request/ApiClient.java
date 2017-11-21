package com.isil.am2template.storage.request;

import com.isil.am2template.model.entity.Buffet;
import com.isil.am2template.model.entity.UserEntity;
import com.isil.am2template.storage.request.entity.BuffetResponse;
import com.isil.am2template.storage.request.entity.LogInBLRaw;
import com.isil.am2template.storage.request.entity.LogInBLResponse;
import com.isil.am2template.storage.request.entity.LogInRaw;
import com.isil.am2template.storage.request.entity.LogInResponse;
import com.isil.am2template.storage.request.entity.NoteRaw;
import com.isil.am2template.storage.request.entity.NoteResponse;
import com.isil.am2template.storage.request.entity.NotesResponse;
import com.isil.am2template.storage.request.entity.UserBLRaw;
import com.isil.am2template.storage.request.entity.UserBLResponse;
import com.isil.am2template.storage.request.entity.UserRaw;
import com.isil.am2template.storage.request.entity.UsersResponse;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by em on 8/06/16.
 */
public class ApiClient {

    private static final String TAG = "ApiClient";
    private static final String API_BASE_URL="https://api.backendless.com/";
    //private static final String API_BASE_URL="https://obscure-earth-55790.herokuapp.com";
    //private static final String API_BASE_URL="https://gist.githubusercontent.com";

    private static ServicesApiInterface servicesApiInterface;
    private static OkHttpClient.Builder httpClient;


    public static ServicesApiInterface getMyApiClient() {

        if (servicesApiInterface == null) {

            Retrofit.Builder builder =new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
            httpClient =new OkHttpClient.Builder();
            httpClient.addInterceptor(interceptor());

            Retrofit retrofit = builder.client(httpClient.build()).build();
            servicesApiInterface = retrofit.create(ServicesApiInterface.class);
        }
        return servicesApiInterface;
    }

    public interface ServicesApiInterface {

        //Backendless

        //7FBB8DC0-4C21-0178-FF76-367F7D30DC00/E5214A86-653A-529C-FF73-95B4DD4F8C00/users/login

        @POST("/{applicationid}/{restapikey}/users/login")
        Call<LogInBLResponse> logInBL(@Path("applicationid") String appID,
                                      @Path("restapikey") String restApiKEY, @Body LogInBLRaw raw);

        @POST("/{applicationid}/{restapikey}/data/Users")
        Call<UserBLResponse> registerBL(@Path("applicationid") String appID,
                                        @Path("restapikey") String restApiKEY, @Body UserBLRaw raw);

        //https://obscure-earth-55790.herokuapp.com/api/login
        @POST("/api/login")
        Call<LogInResponse> login(@Body LogInRaw raw);

        //https://obscure-earth-55790.herokuapp.com/api/users/register
        @POST("/api/users/register")
        Call<UserEntity> register(@Body UserRaw raw);

        //https://obscure-earth-55790.herokuapp.com/api/users
        @GET("/api/users/")
        Call<UsersResponse> users();

        //Notes
        // https://obscure-earth-55790.herokuapp.com/api/notes/register
        @POST("/api/notes/register")
        Call<NoteResponse> addNote(@Body NoteRaw raw);

        // https://obscure-earth-55790.herokuapp.com/api/notes/
        @GET("data/buffet")
        Call<List<Buffet>> buffet();


        // https://obscure-earth-55790.herokuapp.com/api/notes/
        @GET("/api/notes/")
        Call<NotesResponse> notes();

        /*
        //v1/data/Notes
        @GET("/v1/data/Notes")
        Call<NotesResponse> notes();


        @Headers({
                "Content-Type: application/json",
                "application-id: B9D12B47-6B88-8471-FFAD-2B4FFD1EA100",
                "secret-key: 46C1AEC7-6BA7-D1C7-FF6A-FD9EA95C0C00",
                "application-type: REST"
        })
        @POST("/v1/data/Notes")
        Call<NotesResponse> addNote(@Body NoteRaw raw);*/

    }

    /*private static OkHttpClient.Builder client(){
        if(httpClient==null)httpClient=new OkHttpClient.Builder();
        return httpClient;
    }*/
    private  static  HttpLoggingInterceptor interceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
