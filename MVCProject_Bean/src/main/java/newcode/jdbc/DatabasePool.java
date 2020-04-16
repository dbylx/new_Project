package newcode.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class DatabasePool {
    private static HikariDataSource hikariDataSource;

    public DatabasePool(){
        String url = "jdbc:mysql://127.0.0.1:3306/school?serverTimezone=UTC";
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setUsername("root");
        hikariConfig.setPassword("123456");
        hikariConfig.setJdbcUrl(url);
        hikariDataSource = new HikariDataSource(hikariConfig);

    }

    public  HikariDataSource getHikariDataSource(){
            return hikariDataSource;
    }

}
