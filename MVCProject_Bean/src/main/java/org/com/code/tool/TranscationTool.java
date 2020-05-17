package org.com.code.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;


@Component
@Scope("prototype")
public class TranscationTool {
    private JDBCControll jdbcControll;
    boolean flag = false;
    @Autowired
    public TranscationTool(JDBCControll jdbcControll){
        this.jdbcControll = jdbcControll;
    }



    public void beginTranscation() {
        jdbcControll.ConnectMysql();
        System.out.println("start");
        flag = false;
        try {
            jdbcControll.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println(jdbcControll);
        }

    }

    public void commitTranscation() {
        System.out.println("commitTranscation");
        try {
            jdbcControll.getConnection().commit();
            jdbcControll.closeMysql();
        } catch (SQLException e) {
            System.out.println(jdbcControll);
        }
    }

    public void rollbackTranscation() {
        System.out.println("rollback");

        try {
            if( jdbcControll.getConnection()!=null)
                jdbcControll.getConnection().rollback();
        } catch (SQLException e) {
            System.out.println(jdbcControll);
        }
    }
}
