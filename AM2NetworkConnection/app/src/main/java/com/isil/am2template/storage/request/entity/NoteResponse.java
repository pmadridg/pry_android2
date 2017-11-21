package com.isil.am2template.storage.request.entity;

import com.isil.am2template.model.entity.NoteEntity;

/**
 * Created by emedinaa on 14/10/17.
 */

public class NoteResponse {
    private String msg;

    private int status;

    private NoteEntity data;

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

    public NoteEntity getData() {
        return data;
    }

    public void setData(NoteEntity data) {
        this.data = data;
    }
}
