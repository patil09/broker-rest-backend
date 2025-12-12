package com.radianbroker.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.radianbroker.config.DynamicRabbitMqManager;
import com.radianbroker.payload.request.PaymentRequest;
import com.radianbroker.projections.MipProjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class RabbitMQQueueUtil {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQQueueUtil.class);
    private final DynamicRabbitMqManager dynamicRabbitMqManager;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RabbitMQQueueUtil(DynamicRabbitMqManager dynamicRabbitMqManager) {
        this.dynamicRabbitMqManager = dynamicRabbitMqManager;
    }


    /**
     * Send verified report messages to specific RIS broker
     */
    public void verifiedReportsQueueSend(MipProjection mip, String exchangeName, PaymentRequest messageObj) {
        String risCode = mip.getRisCode();
        if (risCode == null || risCode.isBlank()) {
            logger.error("RIS code missing for MIP ID {}", mip.getMipId());
            return;
        }

        RabbitAdmin rabbitAdmin = dynamicRabbitMqManager.getRabbitAdmin(risCode);
        RabbitTemplate rabbitTemplate = dynamicRabbitMqManager.getRabbitTemplate(risCode);

        if (rabbitAdmin == null || rabbitTemplate == null) {
            logger.error("No RabbitMQ configuration found for RIS: {}", risCode);
            return;
        }

        String queueName = "VerifiedReportQueue-" + mip.getMipId() + "-" + mip.getSiteCode();
        String routingKey = "VerifiedReportQueue-" + mip.getSiteCode();

        try {
            DirectExchange exchange = new DirectExchange(exchangeName, true, false);
            rabbitAdmin.declareExchange(exchange);

            Queue queue = new Queue(queueName, true, false, false);
            rabbitAdmin.declareQueue(queue);

            Binding binding = BindingBuilder.bind(queue).to(exchange).with(routingKey);
            rabbitAdmin.declareBinding(binding);

            String jsonMessage = objectMapper.writeValueAsString(messageObj);
            MessageProperties props = new MessageProperties();
            props.setContentType("application/json");

            Message message = new Message(jsonMessage.getBytes(StandardCharsets.UTF_8), props);
            rabbitTemplate.send(exchangeName, routingKey, message);

            logger.info("✅ Verified report sent to [{}] exchange [{}] routingKey [{}] RIS [{}]",
                    queueName, exchangeName, routingKey, risCode);
        } catch (Exception e) {
            logger.error("❌ Failed to send verified report for RIS {}: {}", risCode, e.getMessage(), e);
        }
    }
}