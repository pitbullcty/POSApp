package Reconsitution2.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

public class DataBaseManager implements DataBase{

    private static Connection con;
    public static final String URL = "jdbc:mysql://localhost:3306/pos";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";

    public DataBaseManager(){
        try {
            if (con == null) {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Connection connection() {
        return con;
    }

    @Override
    public void insert(String sql) {
        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String sql) {
        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String sql) {
        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet select(String sql) {
        ResultSet set=null;
        try {
            Statement stmt = con.createStatement();
            set = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            return set;
        }
    }
}
