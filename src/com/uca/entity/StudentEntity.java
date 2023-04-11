package com.uca.entity;

import java.util.ArrayList;

public class StudentEntity {

    private int id;
    private String firstName;
    private String lastName;
    private String classroom;
    private ArrayList<AssignedStickerEntity> assignedStickers;

    public StudentEntity() {
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

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public ArrayList<AssignedStickerEntity> getAssignedStickers() {
        return assignedStickers;
    }

    public void setAssignedStickers(ArrayList<AssignedStickerEntity> assignedStickers) {
        this.assignedStickers = assignedStickers;
    }
}
