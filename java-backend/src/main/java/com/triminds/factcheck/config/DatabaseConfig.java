package com.triminds.factcheck.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        // 🔑 Ajuste para PostgreSQL ou Oracle
        // dataSource.setDriverClassName("org.postgresql.Driver");
        // dataSource.setUrl("jdbc:postgresql://localhost:5432/factcheck");
        // dataSource.setUsername("your-username");
        // dataSource.setPassword("your-password");
        return dataSource;
    }
}
