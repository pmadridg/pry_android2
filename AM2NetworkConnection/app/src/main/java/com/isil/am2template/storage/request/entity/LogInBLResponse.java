package com.isil.am2template.storage.request.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pablo on 11/20/2017.
 */

public class LogInBLResponse {
    private String objectId;

    private String email;

    private String name;



    @SerializedName("user-token")
    private String token;


    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
