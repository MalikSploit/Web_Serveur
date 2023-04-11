package com.uca.entity;

import java.util.ArrayList;

public class ProfEntity {

    private int id;
    private String firstName;
    private String lastName;
    private String identifier;
    private String password;
    private ArrayList<AssignedStickerEntity> assignedStickers;

    public ProfEntity() {
        //Ignored !
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<AssignedStickerEntity> getAssignedStickers() {
        return assignedStickers;
    }

    public void setAssignedStickers(ArrayList<AssignedStickerEntity> assignedStickers) {
        this.assignedStickers = assignedStickers;
    }
}
