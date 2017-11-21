package com.isil.am2template.storage.request.entity;

import com.isil.am2template.model.entity.UserEntity;

import java.util.List;

/**
 * Created by emedinaa on 14/10/17.
 */

public class UsersResponse {

    private String msg;

    private int status;

    private List<UserEntity> data;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<UserEntity> getData() {
        return data;
    }

    public void setData(List<UserEntity> data) {
        this.data = data;
    }
}
