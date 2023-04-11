package com.uca.core;

import com.uca.dao.AssignedStickersDAO;
import com.uca.entity.StudentEntity;
import com.uca.entity.AssignedStickerEntity;
import com.uca.entity.StickerEntity;
import com.uca.entity.ProfEntity;
import com.uca.exceptions.ServiceException;

public class AssignedStickerCore {

    private static final AssignedStickersDAO assignedStickersDAO = new AssignedStickersDAO();

    public static void delete(int id) throws ServiceException {
        assignedStickersDAO.deleteById(id);
    }

    public static AssignedStickerEntity create(int stickerId, int studentId, int profId, String reason) throws ServiceException{
        AssignedStickerEntity entity = new AssignedStickerEntity();
        entity.setReason(reason);

        ProfEntity prof = new ProfEntity();
        prof.setId(profId);
        entity.setProf(prof);

        StudentEntity student = new StudentEntity();
        student.setId(studentId);
        entity.setStudent(student);

        StickerEntity sticker = new StickerEntity();
        sticker.setId(stickerId);
        entity.setSticker(sticker);

        return assignedStickersDAO.create(entity);

    }

}
