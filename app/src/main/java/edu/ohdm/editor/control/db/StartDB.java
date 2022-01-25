package edu.ohdm.editor.control.db;

public class StartDB {
    private JDBCAccess db = new JDBCAccess();

    public void start(){
        db.executeQuery("CREATE TABLE entries (\n" +
                "    name VARCHAR(30),\n" +
                "    checked INTEGER\n" +
                "); ");


        String sql = "";

        sql += "CREATE TABLE entries (\n" +
                "    name VARCHAR(30),\n" +
                "    checked INTEGER\n" +
                "); ";

        db.executeQuery(sql);

        for(String layer : Database.LAYERS)
        {
            sql = "INSERT INTO entries (name, checked)\n" +
                    "VALUES ('"+layer+"', 0); ";
            db.executeQuery(sql);
        }


        System.out.println("Database created");
    }
}
