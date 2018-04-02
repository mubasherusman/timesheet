package com.mubasher.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mubasher.timesheet.dao.GenericSimpleJpaRepositoryImpl;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages ="com.mubasher.timesheet.dao",repositoryBaseClass = GenericSimpleJpaRepositoryImpl.class)
public class Application 
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class, args);
    }
}
