package com.radianbroker.dto;

import java.io.Serializable;
import java.util.Date;

public class VisitHoldQueue implements Serializable {

	private static final long serialVersionUID = 1L;

	private String generationId;
	private Long risId;
	private Long orderNo;
	private Date scheduledDate;

	public VisitHoldQueue() {
	}

	public VisitHoldQueue(String generationId, Long risId, Long orderNo, Date scheduledDate) {
		super();
		this.generationId = generationId;
		this.risId = risId;
		this.orderNo = orderNo;
		this.scheduledDate = scheduledDate;
	}
	public String getGenerationId() {
		return generationId;
	}
	public void setGenerationId(String generationId) {
		this.generationId = generationId;
	}

	public Long getRisId() {
		return risId;
	}
	public void setRisId(Long risId) {
		this.risId = risId;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Date getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	@Override
	public String toString() {
		return "VisitHoldQueue{" +
				"generationId='" + generationId + '\'' +
				", risId=" + risId +
				", orderNo=" + orderNo +
				", scheduledDate=" + scheduledDate +
				'}';
	}
}
