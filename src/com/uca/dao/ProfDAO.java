package com.uca.dao;

import com.uca.entity.ProfEntity;
import com.uca.exceptions.ServiceException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProfDAO extends _Generic<ProfEntity> {

    public int getProfCount() throws ServiceException {
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT COUNT(*) AS nb FROM profs;");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("nb");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException("Could not count professors.");
        }
    }

    @Override
    public ArrayList<ProfEntity> getAll() throws ServiceException {
        try {
            ArrayList<ProfEntity> entities = new ArrayList<>();
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM profs ORDER BY id ASC;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entities.add(parseFromResultSet(resultSet));
            }

            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException("Could not count professors.");
        }
    }

    public void updatePassword(int id, String password) throws ServiceException {
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("UPDATE profs SET password=? WHERE id=?;");
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException("Could not count professors.");
        }
    }

    @Override
    public void update(int id, ProfEntity prof) throws ServiceException {
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("UPDATE profs SET lastname=?, firstname=?, identifier=? WHERE id=?;");
            preparedStatement.setString(1, prof.getLastName());
            preparedStatement.setString(2, prof.getFirstName());
            preparedStatement.setString(3, prof.getIdentifier());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException("Could not count professors.");
        }
    }

    public ProfEntity getByIdentifier(String identifier) throws ServiceException {
        try {
            ProfEntity eleveEntity = new ProfEntity();
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM profs WHERE identifier = ? LIMIT 1;");
            preparedStatement.setString(1, identifier);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                eleveEntity = parseFromResultSet(resultSet);
            }
            return eleveEntity;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException("Could not count professors.");
        }
    }


    @Override
    public ProfEntity getById(int id) throws ServiceException {
        try {
            ProfEntity eleveEntity = new ProfEntity();
            PreparedStatement preparedStatement = this.connect.prepareStatement("SELECT * FROM profs WHERE id = ? LIMIT 1;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                eleveEntity = parseFromResultSet(resultSet);
            }
            return eleveEntity;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException("Could not count professors.");
        }
    }


    @Override
    public void deleteById(int id) throws ServiceException {
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("DELETE FROM assigned_stickers WHERE prof_id = ?;");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement = this.connect.prepareStatement("DELETE FROM profs WHERE id = ?;");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException("Could not count professors.");
        }
    }

    @Override
    public ProfEntity create(ProfEntity obj) throws ServiceException {
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement("INSERT INTO profs(firstname, lastname, identifier, password) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, obj.getFirstName());
            preparedStatement.setString(2, obj.getLastName());
            preparedStatement.setString(3, obj.getIdentifier());
            preparedStatement.setString(4, obj.getPassword());
            preparedStatement.executeUpdate();

            //Auto-incremented values generated by the current PreparedStatement object
            ResultSet res = preparedStatement.getGeneratedKeys();
            res.next();
            obj.setId(res.getInt(1));

            return obj;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServiceException("Could not count professors.");
        }
    }

    // --------- Private methods

    private ProfEntity parseFromResultSet(ResultSet resultSet) throws SQLException {
        ProfEntity entity = new ProfEntity();
        entity.setId(resultSet.getInt("id"));
        entity.setFirstName(resultSet.getString("firstname"));
        entity.setLastName(resultSet.getString("lastname"));
        entity.setIdentifier(resultSet.getString("identifier"));
        entity.setPassword(resultSet.getString("password"));
        return entity;
    }
}
