package com.radianbroker.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {



    /**
     * ✅ Define a separate ThreadPoolTaskExecutor bean
     * to avoid bean name conflict with AsyncConfig
     */
    @Bean(name = "dynamicBrokerRestRabbitTaskExecutor")
    public ThreadPoolTaskExecutor dynamicRabbitTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("dynamic-rabbit-exec-report-rest-");
        executor.initialize();
        return executor;
    }
}
