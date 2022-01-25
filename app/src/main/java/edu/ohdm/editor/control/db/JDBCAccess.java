package edu.ohdm.editor.control.db;

/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.Properties;

import edu.ohdm.editor.exceptions.DriverFailedException;

public class JDBCAccess {
    private Connection dbcon;
    private Statement stmt;
    private String host = DBCred.host;
    private String database = DBCred.database;
    private int port = DBCred.port;
    private String user = DBCred.user;
    private String pass = DBCred.password;
    private String url = "jdbc:postgresql://%s:%d/%s";
    private boolean status;

    public JDBCAccess() {
        try {
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", pass);
            dbcon = DriverManager.getConnection(host, props);
            stmt = dbcon.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (PSQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Can't get a connection: " + e.toString());
            try {
                if (stmt != null) stmt.close();

                if (dbcon != null) dbcon.close();
            } catch (SQLException f) {
            }
            //System.exit(0);
        }
    }

    public JDBCAccess(String db_url, String username, String password) throws DriverFailedException {
        try {
            Properties props = new Properties();
            props.setProperty("user", username);
            props.setProperty("password", password);
            dbcon = DriverManager.getConnection(db_url, props);
            stmt = dbcon.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (PSQLException e) {
            throw new DriverFailedException(e.getMessage());
        } catch (Exception e) {
            System.err.println("Can't get a connection: " + e.toString());
            try {
                if (stmt != null) stmt.close();

                if (dbcon != null) dbcon.close();
            } catch (SQLException f) {
            }
            //System.exit(0);
        }
        this.host=db_url;
        this.user=username;
        this.pass=password;
    }

    public ResultSet executeQuery(String query) {
        try {
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Can't execute Query!" + e.toString());
            return null;
        }
    }

    public void close_database() {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (dbcon != null) dbcon.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("database.Database closed");
    }

    public Connection getConnection() {
        return dbcon;
    }

    private void connect()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Class.forName("org.postgresql.Driver");
                    dbcon = DriverManager.getConnection(url, user, pass);
                    status = true;
                    System.out.println("connected:" + status);
                }
                catch (Exception e)
                {
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.status = false;
        }
    }

    public Connection getExtraConnection()
    {
        Connection c = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, pass);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }
}

