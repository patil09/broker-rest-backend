package com.radianbroker.config;

import com.radianbroker.entity.RisRabbitMQConfig;
import com.radianbroker.repository.RisRabbitMQConfigRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class DynamicRabbitMqManager {

    private static final Logger log = LoggerFactory.getLogger(DynamicRabbitMqManager.class);

    @Autowired
    private RisRabbitMQConfigRepository configRepository;

    @Autowired
    @Qualifier("dynamicBrokerRestRabbitTaskExecutor")
    private ThreadPoolTaskExecutor rabbitTaskExecutor;

    private final Map<String, CachingConnectionFactory> connectionFactories = new HashMap<>();
    private final Map<String, RabbitTemplate> rabbitTemplates = new HashMap<>();
    private final Map<String, RabbitAdmin> rabbitAdmins = new HashMap<>();
    private final Map<String, SimpleRabbitListenerContainerFactory> listenerFactories = new HashMap<>();


    /**
     * 🔁 Load and register RabbitMQ connections dynamically
     * for each RIS (from ris_rabbitmq_config table)
     */
    @PostConstruct
    public void initialize() {
        log.info("🔁 Loading RIS RabbitMQ configurations from database...");

        // Fetch all records, regardless of active flag (0 or 1)
        List<RisRabbitMQConfig> risList = configRepository.findByActiveTrue(true);

        if (risList == null || risList.isEmpty()) {
            log.error("❌ No RIS RabbitMQ configurations found in the database!");
            return;
        }

        for (RisRabbitMQConfig ris : risList) {
            try {
                log.info("⚙️ Setting up RabbitMQ for RIS: {} (Host: {})",
                        ris.getRisCode(), ris.getBrokerHost());

                // Create connection factory per RIS
                CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
                connectionFactory.setHost(ris.getBrokerHost());
                connectionFactory.setPort(ris.getBrokerPort());
                connectionFactory.setUsername(ris.getBrokerUsername());
                connectionFactory.setPassword(ris.getBrokerPassword());
                connectionFactory.setVirtualHost("/");

                // RabbitAdmin
                RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
                rabbitAdmin.setAutoStartup(true);

                // RabbitTemplate
                RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
                rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

                // Listener Factory
                SimpleRabbitListenerContainerFactory listenerFactory = new SimpleRabbitListenerContainerFactory();
                listenerFactory.setConnectionFactory(connectionFactory);
                listenerFactory.setTaskExecutor(rabbitTaskExecutor);
                listenerFactory.setConcurrentConsumers(5);
                listenerFactory.setMaxConcurrentConsumers(10);
                listenerFactory.setPrefetchCount(5);
                listenerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
                listenerFactory.setMessageConverter(new Jackson2JsonMessageConverter());

                // Store RIS-based connections
                connectionFactories.put(ris.getRisCode(), connectionFactory);
                rabbitAdmins.put(ris.getRisCode(), rabbitAdmin);
                rabbitTemplates.put(ris.getRisCode(), rabbitTemplate);
                listenerFactories.put(ris.getRisCode(), listenerFactory);

                log.info("✅ RabbitMQ connection initialized for RIS: {}", ris.getRisCode());

            } catch (Exception e) {
                log.error("❌ Failed to initialize RabbitMQ for RIS {} - {}", ris.getRisCode(), e.getMessage(), e);
            }
        }

        log.info("🎯 Total RIS RabbitMQ connections initialized: {}", connectionFactories.size());
    }

    // --- Access methods for other components ---

    public RabbitTemplate getRabbitTemplate(String risCode) {
        RabbitTemplate template = rabbitTemplates.get(risCode);
        if (template == null) {
            log.error("⚠️ RabbitTemplate not found for RIS: {}", risCode);
        }
        return template;
    }

    public RabbitAdmin getRabbitAdmin(String risCode) {
        RabbitAdmin admin = rabbitAdmins.get(risCode);
        if (admin == null) {
            log.error("⚠️ RabbitAdmin not found for RIS: {}", risCode);
        }
        return admin;
    }

    public CachingConnectionFactory getConnectionFactory(String risCode) {
        return connectionFactories.get(risCode);
    }

    public SimpleRabbitListenerContainerFactory getListenerFactory(String risCode) {
        return listenerFactories.get(risCode);
    }
}
