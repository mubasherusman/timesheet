package com.mubasher.timesheet.configuration;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConfigurationProperties(prefix = "msaccess.datasource")
public class DbConfig extends HikariConfig{
	
	@Bean
	@Primary
	public DataSource dataSource() {
	    return new HikariDataSource(this);
	}

}
