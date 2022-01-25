package edu.ohdm.editor.control.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    private Connection connection;

    // For Amazon Postgresql
    // private final String host = "ssprojectinstance.csv2nbvvgbcb.us-east-2.rds.amazonaws.com"

    // For Google Cloud Postgresql
    // private final String host = "35.44.16.169";

    // For Local PostgreSQL
    private final String host = DBCred.host;

    private final String database = DBCred.database;
    private final int port = DBCred.port;
    private final String user = DBCred.user;
    private final String pass = DBCred.password;
    private String url = "jdbc:postgresql://%s:%d/%s";
    private boolean status;

    public Database()
    {
        this.url = String.format(this.url, this.host, this.port, this.database);
        connect();
        //this.disconnect();
        System.out.println("connection status:" + status);
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
                    connection = DriverManager.getConnection(url, user, pass);
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


    public static final String[] LAYERS= {
                    "ohdm_t%3Aboundaries_admin_2",
                    "ohdm_t%3Aboundaries_admin_3",
                    "ohdm_t%3Aboundaries_admin_4",
                    "ohdm_t%3Aboundaries_admin_5",
                    "ohdm_t%3Aboundaries_admin_6",
                    "ohdm_t%3Aboundaries_admin_7",
                    "ohdm_t%3Aboundaries_admin_8",
                    "ohdm_t%3Aboundaries_admin_9",
                    "ohdm_t%3Aboundaries_admin_10",
                    "ohdm_t%3Ahighway_huge_lines",
                    "ohdm_t%3Ahighway_primary_lines",
                    "ohdm_t%3Ahighway_secondary_lines",
                    "ohdm_t%3Ahighway_small_lines",
                    "ohdm_t%3Ahighway_tertiary_lines",
                    "ohdm_t%3Ahighway_path_lines",
                    "ohdm_t%3Arailway_lines",
                    "ohdm_t%3Ashop_points",
                    "ohdm_t%3Apublic_transport_points",
                    "ohdm_t%3Anatural_points",
                    "ohdm_t%3Aaeroway_points",
                    "ohdm_t%3Acraft_points",
                    "ohdm_t%3Abuilding_polygons",
                    "ohdm_t%3Anatural_polygons",
                    "ohdm_t%3Amilitary_polygons",
                    "ohdm_t%3Awaterway_polygons",
                    "ohdm_t%3Ageological_polygons",
                    "ohdm_t%3Aaeroway_polygons",
                    "ohdm_t%3Aemergency_polygons",
                    "ohdm_t%3Alanduse_brown",
                    "ohdm_t%3Alanduse_commercialetc",
                    "ohdm_t%3Alanduse_freegreenandwood",
                    "ohdm_t%3Alanduse_gardeningandfarm",
                    "ohdm_t%3Alanduse_grey",
                    "ohdm_t%3Alanduse_industrial",
                    "ohdm_t%3Alanduse_military",
                    "ohdm_t%3Alanduse_residentaletc",
                    "ohdm_t%3Alanduse_transport",
                    "ohdm_t%3Alanduse_water",
                    "ohdm_t%3Abuilding_polygons_label",
                    "ohdm_t%3Anatural_polygons_label",
                    "ohdm_t%3Amilitary_polygons_label",
                    "ohdm_t%3Awaterway_polygons_label",
                    "ohdm_t%3Ageological_polygons_label",
                    "ohdm_t%3Aaeroway_polygons_label",
                    "ohdm_t%3Aemergency_polygons_label",
                    "ohdm_t%3Alanduse_brown_label",
                    "ohdm_t%3Alanduse_commercialetc_label",
                    "ohdm_t%3Alanduse_freegreenandwood_label_label",
                    "ohdm_t%3Alanduse_gardeningandfarm_label",
                    "ohdm_t%3Alanduse_grey_label",
                    "ohdm_t%3Alanduse_industrial_label",
                    "ohdm_t%3Alanduse_military_label",
                    "ohdm_t%3Alanduse_residentaletc_label",
                    "ohdm_t%3Alanduse_transport_label",
                    "ohdm_t%3Alanduse_water_label"
            };
}