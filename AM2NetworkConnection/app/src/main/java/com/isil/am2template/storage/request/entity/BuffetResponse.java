package com.isil.am2template.storage.request.entity;

import com.isil.am2template.model.entity.Buffet;

import java.util.List;

/**
 * Created by Pablo Claus on 11/2/2017.
 */

public class BuffetResponse {

    private String message;

    private int offset;
    private List<Buffet> data;

    private Object nextPage;
    private int totalObjects;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<Buffet> getData() {
        return data;
    }

    public void setData(List<Buffet> data) {
        this.data = data;
    }

    public Object getNextPage() {
        return nextPage;
    }

    public void setNextPage(Object nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalObjects() {
        return totalObjects;
    }

    public void setTotalObjects(int totalObjects) {
        this.totalObjects = totalObjects;
    }
}
