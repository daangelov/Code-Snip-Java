package models;

import database.DbManager;
import entities.User;

import java.sql.*;
import java.util.Objects;

public class UserModel {

    public void create(User user) {
        // TODO implement this
    }

    public User find(String username) {
        // TODO implement this

        return new User();
    }

    public static boolean login(String username, String password) {

        DbManager dbManager = null;

        try {
            dbManager = new DbManager();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            Connection connection = dbManager.initConnection();
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT username, password from user where username= ? and password= ?");
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // found
                return true;
            } else {
                System.out.println("No such user");
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Error in login() -->" + ex.getMessage());
            return false;
        } finally {
            Objects.requireNonNull(dbManager).closeConnection();
        }
    }
}