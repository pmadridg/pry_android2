package com.isil.am2template.storage.request.entity;

/**
 * Created by Pablo Claus on 11/20/2017.
 */

public class UserBLResponse {
    private String objectId;
    private String name;
    private String email;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
