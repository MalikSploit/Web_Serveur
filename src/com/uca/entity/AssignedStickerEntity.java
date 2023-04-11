package com.uca.entity;

import java.util.Date;

public class AssignedStickerEntity {
    private int id;
    private StudentEntity student;
    private String reason;
    private StickerEntity sticker;
    private Date date;
    private ProfEntity prof;

    public AssignedStickerEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public StickerEntity getSticker() {
        return sticker;
    }

    public void setSticker(StickerEntity sticker) {
        this.sticker = sticker;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProfEntity getProf() {
        return prof;
    }

    public void setProf(ProfEntity prof) {
        this.prof = prof;
    }
}
