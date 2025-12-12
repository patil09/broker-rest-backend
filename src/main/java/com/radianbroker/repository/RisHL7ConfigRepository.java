package com.radianbroker.repository;


import com.radianbroker.entity.RisHL7Config;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface RisHL7ConfigRepository extends CrudRepository<RisHL7Config, Long>{

    @Query(value ="SELECT * FROM ris_hl7_configs WHERE ris_id=?1", nativeQuery = true)
    RisHL7Config findByRisId(Long risId);
}