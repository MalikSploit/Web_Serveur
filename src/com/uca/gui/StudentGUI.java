package com.uca.gui;

import com.uca.core.Studentcore;
import com.uca.core.StickerCore;
import com.uca.entity.ProfEntity;
import com.uca.exceptions.ServiceException;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentGUI {

    public static String getAllStudents(ProfEntity connectedUser) throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("connectedUser", connectedUser);
        try {
            input.put("students", Studentcore.getAll());
        } catch (ServiceException e) {
            input.put("error", e.getMessage());
        }

        return _Factory.genericGUI(input, "students/students.ftl");
    }


    public static String getStudent(ProfEntity connectedUser, int id) throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("connectedUser", connectedUser);
        try {
            input.put("student", Studentcore.getById(id));
            input.put("stickers", StickerCore.getAll());
        } catch (ServiceException e) {
            input.put("error", e.getMessage());
        }



        return _Factory.genericGUI(input, "students/student.ftl");
    }
}
