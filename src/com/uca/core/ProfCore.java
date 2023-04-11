package com.uca.core;

import com.sun.source.doctree.SerialTree;
import com.uca.dao.AssignedStickersDAO;
import com.uca.dao.ProfDAO;
import com.uca.entity.ProfEntity;
import com.uca.exceptions.ServiceException;
import org.mindrot.jbcrypt.BCrypt;
import java.util.ArrayList;

public class ProfCore {

    private static final ProfDAO profDAO = new ProfDAO();
    private static final AssignedStickersDAO assignedStickersDAO = new AssignedStickersDAO();

    public static ArrayList<ProfEntity> getAll() throws ServiceException {
        return profDAO.getAll();
    }

    public static void updatePassword(int id, String password) throws ServiceException {
        profDAO.updatePassword(id, BCrypt.hashpw(password, BCrypt.gensalt()));
    }

    public static ProfEntity getById(int id) throws ServiceException {
        ProfEntity prof = profDAO.getById(id);
        if (prof.getId() == 0) {
            throw new ServiceException("This professor does not exists.");
        }
        prof.setAssignedStickers(assignedStickersDAO.getAllStickersByProfId(id));
        prof.setPassword(null);
        return prof;
    }

    public static ProfEntity getByIdentifier(String identifier, boolean keepPassword) throws ServiceException {
        ProfEntity prof = profDAO.getByIdentifier(identifier);
        if (prof.getId() == 0) {
            throw new ServiceException("This professor does not exists.");
        }
        prof.setAssignedStickers(assignedStickersDAO.getAllStickersByProfId(prof.getId()));
        if (!keepPassword) {
            prof.setPassword(null);
        }

        return prof;
    }

    public static void update(int id, String lastName, String firstName, String identifier) throws ServiceException {
            ProfEntity entity = new ProfEntity();
            entity.setLastName(lastName);
            entity.setFirstName(firstName);
            entity.setIdentifier(identifier);
            profDAO.update(id, entity);

    }

    public static ProfEntity create(String lastName, String firstName, String identifier, String password) throws ServiceException {
        //Check if identifier already exists
        try {
            getByIdentifier(identifier, false);

        } catch (ServiceException e) {
            ProfEntity entity = new ProfEntity();
            entity.setLastName(lastName);
            entity.setFirstName(firstName);
            entity.setIdentifier(identifier);
            entity.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));

            return profDAO.create(entity);
        }

        throw new ServiceException("A professor already exists with this identifier.");
    }


    public static void delete(int id) throws ServiceException {
        profDAO.deleteById(id);
    }

}
