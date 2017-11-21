package com.isil.am2template.utils;

import com.google.gson.GsonBuilder;
import com.isil.am2template.storage.request.entity.LogInResponse;

/**
 * Created by emedinaa on 20/10/17.
 */

public class GsonHelper {

    public static LogInResponse responseToObject(String json){
        if(json==null){
            return new LogInResponse();
        }
        GsonBuilder gson = new GsonBuilder();
        //Type collectionType = new TypeToken<T>(){}.getType();
        LogInResponse logInResponse = gson.create().fromJson(json, LogInResponse.class);

        return logInResponse;
    }
}
