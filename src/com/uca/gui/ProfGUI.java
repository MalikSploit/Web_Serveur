package com.uca.gui;

import com.uca.core.ProfCore;
import com.uca.entity.ProfEntity;
import com.uca.exceptions.ServiceException;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProfGUI {

    public static String getAllProfs(ProfEntity connectedUser) throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("connectedUser", connectedUser);

        try {
            input.put("profs", ProfCore.getAll());
        } catch (ServiceException e) {
            input.put("error", e.getMessage());
        }


        return _Factory.genericGUI(input, "profs/profs.ftl");
    }


    public static String getProf(ProfEntity connectedUser, int id) throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("connectedUser", connectedUser);

        try {
            input.put("prof", ProfCore.getById(id));
        } catch (ServiceException e) {
            input.put("error", e.getMessage());
        }
        return _Factory.genericGUI(input, "profs/prof.ftl");
    }
}
