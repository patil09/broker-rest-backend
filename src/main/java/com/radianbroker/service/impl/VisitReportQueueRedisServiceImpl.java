package com.radianbroker.service.impl;


import com.radianbroker.dto.VisitHoldQueue;
import com.radianbroker.service.VisitReportQueueRedisService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VisitReportQueueRedisServiceImpl implements VisitReportQueueRedisService {

	private final String VISIT_REPORTS_QUEUE_CACHE = "VISIT_REPORTS_QUEUE";

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	private HashOperations<String, String, VisitHoldQueue> hashOperations;

	// This annotation makes sure that the method needs to be executed after
	// dependency injection is done to perform any initialization.
	@PostConstruct
	private void intializeHashOperations() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(VisitHoldQueue visitReport) {
		// TODO Auto-generated method stub
		String hashKey = getHashKey(visitReport.getRisId(), visitReport.getOrderNo());
		hashOperations.put(VISIT_REPORTS_QUEUE_CACHE, hashKey, visitReport);
	}

	@Override
	public VisitHoldQueue findById(Long risId, Long orderNo) {
		// TODO Auto-generated method stub
		String hashKey = getHashKey(risId, orderNo);
		return hashOperations.get(VISIT_REPORTS_QUEUE_CACHE, hashKey);
	}

	@Override
	public void delete(Long risId, Long orderNo) {
		// TODO Auto-generated method stub
		String hashKey = getHashKey(risId, orderNo);
		hashOperations.delete(VISIT_REPORTS_QUEUE_CACHE, hashKey);
	}

	@Override
	public Map<String, VisitHoldQueue> findAll() {
		// TODO Auto-generated method stub
		return hashOperations.entries(VISIT_REPORTS_QUEUE_CACHE);
	}
	
	@Override
	public String getHashKey(Long risId, Long orderNo) {
		// TODO Auto-generated method stub
		return risId + "_"+ orderNo;
	}
}
