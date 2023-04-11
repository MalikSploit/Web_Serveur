package com.uca.gui;

import com.uca.core.ProfCore;
import com.uca.core.StickerCore;
import com.uca.entity.ProfEntity;
import com.uca.exceptions.ServiceException;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StickerGUI {

    public static String getAllStickers(ProfEntity connectedUser) throws IOException, TemplateException {
        Map<String, Object> input = new HashMap<>();
        input.put("connectedUser", connectedUser);

        try {
            input.put("stickers", StickerCore.getAll());
        } catch (ServiceException e) {
            input.put("error", e.getMessage());
        }


        return _Factory.genericGUI(input, "stickers/stickers.ftl");
    }

}
