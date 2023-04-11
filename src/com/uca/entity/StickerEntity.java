package com.uca.entity;

public class StickerEntity {

    private int id;
    private String color;
    private String description;

    public StickerEntity() {
        //Ignoré !
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
