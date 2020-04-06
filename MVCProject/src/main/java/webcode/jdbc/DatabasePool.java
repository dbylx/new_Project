package webcode.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabasePool {
    private static HikariDataSource hikariDataSource;

    public static HikariDataSource getHikariDataSource(){
        if(hikariDataSource !=null){
            return hikariDataSource;
        }
        synchronized (DatabasePool.class){
            if (hikariDataSource == null){

                String url = "jdbc:mysql://127.0.0.1:3306/school?serverTimezone=UTC";
                HikariConfig hikariConfig = new HikariConfig();

                hikariConfig.setUsername("root");
                hikariConfig.setPassword("123456");
                hikariConfig.setJdbcUrl(url);
                hikariDataSource = new HikariDataSource(hikariConfig);

                return hikariDataSource;
            }
        }
        return null;
    }

}
