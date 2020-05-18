package org.com.code.tool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class DatabasePool {
    private static HikariDataSource hikariDataSource;

    public DatabasePool(){

        String driverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.123:3306/school?serverTimezone=UTC";
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setUsername("root");
        hikariConfig.setPassword("123456");
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setDriverClassName(driverName);
        hikariDataSource = new HikariDataSource(hikariConfig);

    }

    public  HikariDataSource getHikariDataSource(){
        return hikariDataSource;
    }

}
