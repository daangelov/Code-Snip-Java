package models;

import database.DbManager;
import entities.Snippet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SnippetModel {

    public boolean create(Snippet snippet) {

        int success = 0;
        DbManager dbManager = null;
        PreparedStatement stmt = null;

        try {
            dbManager = new DbManager();
            Connection conn = dbManager.initConnection();
            String query = "INSERT INTO snippet (title, lang, text, creator_id) VALUES(?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, snippet.getTitle());
            stmt.setString(2, snippet.getLang());
            stmt.setString(3, snippet.getText());
            stmt.setLong(4, snippet.getCreatorId());

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

    public ArrayList<Snippet> getSnippets(long creatorId) throws Exception {
        ArrayList<Snippet> result = new ArrayList<>();

        DbManager dbManager = new DbManager();
        Connection conn = dbManager.initConnection();

        String query = "SELECT * FROM snippet WHERE creator_id=?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setLong(1, creatorId);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Snippet newSnippet = new Snippet(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("lang"),
                    rs.getString("text"),
                    rs.getDate("created_on"),
                    rs.getDate("updated_on"),
                    rs.getLong("creator_id"),
                    rs.getString("public_id")
            );
            result.add(newSnippet);
        }

        dbManager.closeConnection();

        return result;
    }

    public boolean delete(long snippetId) {
        int success = 0;
        DbManager dbManager = null;
        PreparedStatement stmt = null;

        try {
            dbManager = new DbManager();
            Connection conn = dbManager.initConnection();
            String query = "DELETE FROM snippet WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setLong(1, snippetId);

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
}
