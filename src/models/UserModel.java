package models;

import database.DbManager;
import entities.User;

import java.sql.*;

public class UserModel {

    public boolean create(User user) {

        int success = 0;
        DbManager dbManager = null;
        PreparedStatement stmt = null;

        try {
            dbManager = new DbManager();
            Connection conn = dbManager.initConnection();
            String query = "INSERT INTO user (username, password, password_salt, firstname, lastname, email) VALUES(?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getPasswordSalt());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getEmail());

            success = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (dbManager != null) {
                    dbManager.closeConnection();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return success == 1;
    }

    public User find(String username) throws Exception {
        DbManager dbManager = new DbManager();
        Connection conn = dbManager.initConnection();

        String query = "SELECT * FROM user WHERE username=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) { // found
            User foundUser = new User(rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("password_salt"),
                    rs.getString("email"),
                    rs.getString("firstname"),
                    rs.getString("lastname"));
            dbManager.closeConnection();
            return foundUser;
        } else {
            dbManager.closeConnection();
            return null;
        }
    }
}