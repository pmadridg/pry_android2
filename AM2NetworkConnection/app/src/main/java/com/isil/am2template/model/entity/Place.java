package com.isil.am2template.model.entity;

import java.io.Serializable;

/**
 * Created by Pablo Claus on 11/5/2017.
 */

public class Place implements Serializable {

    private String id;
    private String name;
    private String description;
    private String size;
    private String photo;
    private boolean checked;

    public Place() {
    }

    public Place(String id, String name, String description, String size, String photo, boolean checked) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.photo = photo;
        this.checked = checked;
    }

    public Place(String id, String name, String description, String size) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;

    }

    public Place(String name, String description, String size) {
        this.name = name;
        this.description = description;
        this.size = size;
    }





    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
