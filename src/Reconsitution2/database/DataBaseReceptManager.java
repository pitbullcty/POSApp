package Reconsitution2.database;

import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseReceptManager implements DataBase{

    DataBaseManager dataBaseManager;

    public DataBaseReceptManager(){
        dataBaseManager = new DataBaseManager();
       // dataBaseManager.getInstance();
    }

    @Override
    public void insert(String sql) {
        long id = 0;
        try {
            id = getid();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sql = sql.replace("?",String.valueOf(id));
        dataBaseManager.insert(sql);
    }

    @Override
    public void update(String sql) {
        dataBaseManager.update(sql);
    }

    @Override
    public void delete(String sql) {
        dataBaseManager.delete(sql);
    }

    @Override
    public ResultSet select(String sql) {
        return dataBaseManager.select(sql);
    }


    public long getid() throws Exception{
        long id;
        String sql = "select count(*) from sale_info";
        ResultSet rs = this.select(sql);
        if(rs.next()){
            id = rs.getLong(1);
        }
        else id=0;
        return id;
    }

}
