package com.uca.core;

import com.uca.dao.StickersDAO;
import com.uca.entity.StickerEntity;
import com.uca.exceptions.ServiceException;

import java.util.ArrayList;

public class StickerCore {

    private static final StickersDAO stickersDAO = new StickersDAO();

    public static ArrayList<StickerEntity> getAll() throws ServiceException {
        return stickersDAO.getAll();
    }

    public static StickerEntity create(String color, String description) throws ServiceException{
        StickerEntity entity = new StickerEntity();
        entity.setColor(color);
        entity.setDescription(description);
        return stickersDAO.create(entity);
    }

    public static void delete(int id) throws ServiceException {
        stickersDAO.deleteById(id);
    }
}
