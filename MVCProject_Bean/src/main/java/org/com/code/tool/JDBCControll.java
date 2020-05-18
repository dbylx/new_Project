package org.com.code.tool;

import org.com.code.tool.DatabasePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class JDBCControll {
    private Connection connection;
    private Statement statment;
    private ResultSet resultSet;
    private DatabasePool databasePool;


    @Autowired
    public JDBCControll(DatabasePool databasePool){
        this.databasePool = databasePool;

    }




    public void setStatment(Statement statment) {
        this.statment = statment;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatment() {
        return statment;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }



    public  String ConnectMysql(){
        //原始连接方法
//        String url = "servers:mysql://127.0.0.123:3306/school?serverTimezone=UTC";
//        String driveName = "com.mysql.cj.servers.Driver";
//
//        try{
//            Class.forName(driveName);
//            connection = DriverManager.getConnection(url,"root","123456");
//            statment = connection.createStatement();

//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
        //改为数据库连接池后的连接方法
        try {
            connection = databasePool.getHikariDataSource().getConnection();
            statment = connection.createStatement();
            System.out.println("yes succcessful");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet excuteSQL(String sqlString) throws SQLException {
//        ConnectMysql();
        resultSet = statment.executeQuery(sqlString);
        return resultSet;


    }

    public boolean excuteUpdateSQL(String sqlString) throws SQLException {
//      ConnectMysql();

        boolean flag =  statment.execute(sqlString);

        return true;



    }


    public void closeMysql(){
        try {
            if(resultSet!=null)
                resultSet.close();
            if(statment!=null)
                statment.close();
            if(connection!=null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

