package cl.hope.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgresConfiguration {

    @Value("${database.user}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Value("${database.url}")
    private String url;

    @Bean
    public JdbcConnectionDetails postgresJdbcConnectionDB() {
        return new JdbcConnectionDetails() {

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public String getPassword() {
                return password;
            }

            @Override
            public String getJdbcUrl() {
                return url;
            }
        };
    }
}