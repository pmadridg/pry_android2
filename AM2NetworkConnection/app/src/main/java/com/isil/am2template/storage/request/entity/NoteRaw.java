package com.isil.am2template.storage.request.entity;

/**
 * Created by emedinaa on 14/10/17.
 */

/*
    {
        "name":"Aviso",
        "description":"Tomar UA3",
        "path": "",
        "userId": "59e0540d429d3f501d6493de"
    }
 */
public class NoteRaw {
    private String name;
    private String description;
    private String path;
    private String userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
