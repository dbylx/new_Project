package webcode.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCControll {
    public void setStatment(Statement statment) {
        this.statment = statment;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    private Connection connection;
    private Statement statment;
    private ResultSet resultSet;

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
//        String url = "servers:mysql://127.0.0.1:3306/school?serverTimezone=UTC";
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
            connection = DatabasePool.getHikariDataSource().getConnection();
            statment = connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet excuteSQL(String sqlString){
        ConnectMysql();
        try {
            resultSet = statment.executeQuery(sqlString);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public boolean excuteUpdateSQL(String sqlString){
        ConnectMysql();
        try {
            boolean flag =  statment.execute(sqlString);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
           return false;
        }

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
