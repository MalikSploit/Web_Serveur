package com.uca.gui;

import com.uca.entity.ProfEntity;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProfileGUI {

    public static String getProfile(ProfEntity connectedUser) throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("connectedUser", connectedUser);

        return _Factory.genericGUI(input, "profile/profile.ftl");
    }


}
