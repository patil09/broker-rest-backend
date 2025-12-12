package com.radianbroker.payload.request;


import com.radianbroker.enums.Mode;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;
import java.util.Date;

public class PaymentRequest implements Serializable{

    private static final long serialVersionUID = 1L;
	private Long reportId;
	private Long risId;
	private Long mipId;
	private Long orderNo;
	private String description;
	private String examCode;
	private Long mis1;
	private Long mis2;
	private Double baseRate;
	private Double reportFee;
	@Enumerated(EnumType.STRING)
	private Mode mode;
	private Date verificationDate;
	private Boolean voiceRecognitionUsed;

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public Long getRisId() {
		return risId;
	}

	public void setRisId(Long risId) {
		this.risId = risId;
	}

	public Long getMipId() {
		return mipId;
	}

	public void setMipId(Long mipId) {
		this.mipId = mipId;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public Long getMis1() {
		return mis1;
	}

	public void setMis1(Long mis1) {
		this.mis1 = mis1;
	}

	public Long getMis2() {
		return mis2;
	}

	public void setMis2(Long mis2) {
		this.mis2 = mis2;
	}

	public Double getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(Double baseRate) {
		this.baseRate = baseRate;
	}

	public Double getReportFee() {
		return reportFee;
	}

	public void setReportFee(Double reportFee) {
		this.reportFee = reportFee;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public Date getVerificationDate() {
		return verificationDate;
	}

	public void setVerificationDate(Date verificationDate) {
		this.verificationDate = verificationDate;
	}

	public Boolean getVoiceRecognitionUsed() {
		return voiceRecognitionUsed;
	}

	public void setVoiceRecognitionUsed(Boolean voiceRecognitionUsed) {
		this.voiceRecognitionUsed = voiceRecognitionUsed;
	}
	
}
