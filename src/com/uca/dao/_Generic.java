package com.uca.dao;

import com.uca.exceptions.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class _Generic<T> {

    public Connection connect = _Connector.getInstance();


    /**
     * Permet de récupérer toutes les entités de la table.
     */
    public abstract ArrayList<T> getAll() throws ServiceException;


    /**
     * Permet de récupérer une entité par son identifiant
     * @param id Identifiant de la ressource à récupérer
     */
    public abstract T getById(int id) throws ServiceException;

    /**
     * Permet de mettre à jour
     * @param obj Objet à mettre à jour
     * @param id Identifiant de la ressouece
     */
    public abstract void update(int id, T obj) throws ServiceException;


    /**
     * Permet de créer une entrée dans la base de données
     * par rapport à un objet
     * @param obj Objet à créer
     */
    public abstract T create(T obj) throws ServiceException;

    /**
     * Permet la suppression d'une entrée de la base
     * @param id Identifiant de la ressource à supprimer
     */
    public abstract void deleteById(int id) throws ServiceException;

}
