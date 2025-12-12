package com.radianbroker.repository;

import com.radianbroker.entity.RisRabbitMQConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RisRabbitMQConfigRepository extends CrudRepository<RisRabbitMQConfig,Long> {

    @Query(value = "SELECT * FROM ris_rabbitmq_config where active=?1", nativeQuery = true)
    List<RisRabbitMQConfig> findByActiveTrue(Boolean flag);
}
