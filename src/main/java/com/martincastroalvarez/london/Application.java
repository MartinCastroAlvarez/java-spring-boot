package com.martincastroalvarez.london;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableJpaRepositories
@EnableAsync
public class Application {

	public static void main(String[] args) {
        // --------------------------------------------------------------------
        // Java application main hook.
        //
        // The main() is the starting point for JVM to start execution of a
        // Java program. Without the main() method, JVM will not execute the
        // program.
        //
        // References:
        // - https://www.javatpoint.com/java-main-method
        // --------------------------------------------------------------------
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}

    @Bean
    public Executor asyncExecutor() {
        // --------------------------------------------------------------------
        // Defines an async worker for executing some tasks.
        // --------------------------------------------------------------------
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("JDAsync-");
        executor.initialize();
        return executor;
    }

}
