package com.radianbroker.projections;

public interface MipProjection {
	Long getMipId();
	String getSiteCode();
	Long getRisId();
	String getRisCode();

	default String getRoutingKey() {
		return getSiteCode();
	}
	default String getQueueName() {
		return getMipId() + "-" + getSiteCode();
	}

	default String getListenerId() {
		return getQueueName() +"-listener";
	}
}
