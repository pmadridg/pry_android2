package com.isil.am2template.model.entity;

/**
 * Created by Pablo Claus on 11/2/2017.
 */

public class Buffet {

    private String title;
    private String description;
    private String category;
    private String bimage;
    private String objectId;
    private String path;
    private Boolean marcado;

    public Buffet() {

    }

    public Boolean getMarcado() {
        return marcado;
    }

    public void setMarcado(Boolean marcado) {
        this.marcado = marcado;
    }

    public Buffet(String title, String description, String category, String bimage) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.bimage = bimage;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBimage() {
        return bimage;
    }

    public void setBimage(String bimage) {
        this.bimage = bimage;
    }
}
