package database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.faces.context.FacesContext;


public class DbManager implements Serializable {

    private String db_server = "";
    private String db_user = "";
    private String db_password = "";
    private String db_driver = "";

    public Connection connection = null;

    public DbManager() throws Exception {
        init();
    }

    private void init() throws Exception {
        FacesContext fc = FacesContext.getCurrentInstance();
        db_server = fc.getExternalContext().getInitParameter("DB-SERVER");
        db_user = fc.getExternalContext().getInitParameter("DB-USER");
        db_password = fc.getExternalContext().getInitParameter("DB-PASSWORD");
        db_driver = fc.getExternalContext().getInitParameter("JDBC-DRIVER");
        Class.forName(db_driver);
    }

    public Connection initConnection() throws Exception {
        if (this.connection == null) {
            this.connection = DriverManager.getConnection(db_server, db_user, db_password);
            this.connection.setAutoCommit(false);
        } else if (this.connection.isClosed()) {
            this.connection = null;
            this.connection = DriverManager.getConnection(db_server, db_user, db_password);
            this.connection.setAutoCommit(false);
        }
        return this.connection;
    }

    public void closeConnection() {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commitConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollbackConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}