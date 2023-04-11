package com.uca.core;

import com.uca.dao.StudentDAO;
import com.uca.dao.AssignedStickersDAO;
import com.uca.entity.StudentEntity;
import com.uca.exceptions.ServiceException;

import java.util.ArrayList;

public class Studentcore {

    private static final StudentDAO studentDAO = new StudentDAO();
    private static final AssignedStickersDAO assignedStickersDAO = new AssignedStickersDAO();

    public static ArrayList<StudentEntity> getAll() throws ServiceException {
        return studentDAO.getAll();
    }

    public static StudentEntity getById(int id) throws ServiceException {
        StudentEntity eleve = studentDAO.getById(id);
        if (eleve.getId() == 0) {
            throw new ServiceException("Student does not exists.");
        }
        eleve.setAssignedStickers(assignedStickersDAO.getAllStickersByStudentId(id));
        return eleve;

    }

    public static StudentEntity create(String lastname, String firstname, String classroom) throws ServiceException {
        StudentEntity student = new StudentEntity();
        student.setLastName(lastname);
        student.setFirstName(firstname);
        student.setClassroom(classroom);

        return studentDAO.create(student);
    }

    public static void update(int id, String lastname, String firstname, String classroom) throws ServiceException {
        StudentEntity student = new StudentEntity();
        student.setLastName(lastname);
        student.setFirstName(firstname);
        student.setClassroom(classroom);

        studentDAO.update(id, student);

    }

    public static void delete(int id) throws ServiceException {
        studentDAO.deleteById(id);
    }

}
