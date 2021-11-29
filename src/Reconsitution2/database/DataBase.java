package Reconsitution2.database;

import java.sql.ResultSet;

public interface DataBase {
    void insert(String sql);
    void update(String sql);
    void delete(String sql);
    ResultSet select(String sql);
}
