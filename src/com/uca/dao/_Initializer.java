package com.uca.dao;

import com.uca.core.ProfCore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.UUID;

public class _Initializer {

    public static void Init() {
        Connection connection = _Connector.getInstance();

        try {
            PreparedStatement statement;

            //Initialisation des tables
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS students(id int primary key auto_increment, firstname varchar(100), lastname varchar(100), class varchar(100));");
            statement.executeUpdate();

            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS stickers(id int primary key auto_increment, color varchar(100), description varchar(200));");
            statement.executeUpdate();

            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS profs(id int primary key auto_increment, firstname varchar(100), lastname varchar(100), identifier varchar(50), password varchar(300));");
            statement.executeUpdate();

            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS assigned_stickers(id int primary key auto_increment, student_id int, foreign key (student_id) references students(id), reason varchar(200), sticker_id int, foreign key (sticker_id) references stickers(id), assignation_date timestamp default CURRENT_TIMESTAMP, prof_id int, foreign key (prof_id) references profs(id))");
            statement.executeUpdate();

            if(new ProfDAO().getProfCount() == 0){
                String password = UUID.randomUUID().toString();
                ProfCore.create("Admin", "Admin", "admin", password);
                System.out.println("New user generated as database were empty. Username: admin | Password: " + password);
            }
            //Password : 3b8596bf-9071-4a42-b847-38e08b0c3bc9
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("could not create database !");
        }
    }
}
