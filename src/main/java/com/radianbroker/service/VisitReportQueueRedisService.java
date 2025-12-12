package com.radianbroker.service;

import com.radianbroker.dto.VisitHoldQueue;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface VisitReportQueueRedisService {

    void save(VisitHoldQueue visitReport);

    VisitHoldQueue findById(Long risId, Long orderNo);

    void delete(Long risId, Long orderNo);

    Map<String, VisitHoldQueue> findAll();

    String getHashKey(Long risId, Long orderNo);
}
