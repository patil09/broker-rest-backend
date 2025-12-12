package com.radianbroker.dto;



import com.radianbroker.entity.Visit.Priority;
import com.radianbroker.enums.Mode;
import com.radianbroker.enums.OrderStatus;
import com.radianbroker.enums.State;

import java.sql.Timestamp;

public class VisitDTO {

	private Long reportId;
	private Long patientId;
	private Long risId;
	private Long mipId;

	private Long orderNo;
	private String visitNo;
	private String description;
	private String referrer;
	private String mip;
	private String machine;
	private Priority priority;
	private String examCode;
	private String examDescription;
	private Timestamp visitStart;
	private Timestamp visitEnd;
	private String reportText;
	private String notes;
	private String technician;
	private String radiologist;
	private OrderStatus orderStatus;
	private boolean primeStudy;
	private State state;

	private Long mrt;
	private Long smrt;
	private Long mis1;
	private Long mis2;
	private Long typ;
	private Boolean verify1;
	private Boolean verify2;
	private Boolean forTyping;
	private Long lockedBy;
	private Timestamp lockedDate;
	private Long updatedByMsgId;

	private Double baseRate;
	private Double reportFee;
	private Mode mode;

	private String mrtFullName;
	private String mis1FullName;
	private String mis2FullName;
	private Boolean followUp;
	private Boolean voiceRecognitionUsed;
	
	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
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

	public String getVisitNo() {
		return visitNo;
	}

	public void setVisitNo(String visitNo) {
		this.visitNo = visitNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getMip() {
		return mip;
	}

	public void setMip(String mip) {
		this.mip = mip;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public String getExamCode() {
		return examCode;
	}

	public void setExamCode(String examCode) {
		this.examCode = examCode;
	}

	public String getExamDescription() {
		return examDescription;
	}

	public void setExamDescription(String examDescription) {
		this.examDescription = examDescription;
	}

	public Timestamp getVisitStart() {
		return visitStart;
	}

	public void setVisitStart(Timestamp visitStart) {
		this.visitStart = visitStart;
	}

	public Timestamp getVisitEnd() {
		return visitEnd;
	}

	public void setVisitEnd(Timestamp visitEnd) {
		this.visitEnd = visitEnd;
	}

	public String getReportText() {
		return reportText;
	}

	public void setReportText(String reportText) {
		this.reportText = reportText;
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getTechnician() {
		return technician;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}

	public String getRadiologist() {
		return radiologist;
	}

	public void setRadiologist(String radiologist) {
		this.radiologist = radiologist;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public boolean isPrimeStudy() {
		return primeStudy;
	}

	public void setPrimeStudy(boolean primeStudy) {
		this.primeStudy = primeStudy;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Long getMrt() {
		return mrt;
	}

	public void setMrt(Long mrt) {
		this.mrt = mrt;
	}

	public Long getSmrt() {
		return smrt;
	}

	public void setSmrt(Long smrt) {
		this.smrt = smrt;
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

	public Long getTyp() {
		return typ;
	}

	public void setTyp(Long typ) {
		this.typ = typ;
	}

	public Boolean getVerify1() {
		return verify1;
	}

	public void setVerify1(Boolean verify1) {
		this.verify1 = verify1;
	}

	public Boolean getVerify2() {
		return verify2;
	}

	public void setVerify2(Boolean verify2) {
		this.verify2 = verify2;
	}

	public Boolean getForTyping() {
		return forTyping;
	}

	public void setForTyping(Boolean forTyping) {
		this.forTyping = forTyping;
	}

	public Long getLockedBy() {
		return lockedBy;
	}

	public void setLockedBy(Long lockedBy) {
		this.lockedBy = lockedBy;
	}

	public Timestamp getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Timestamp lockedDate) {
		this.lockedDate = lockedDate;
	}

	public Long getUpdatedByMsgId() {
		return updatedByMsgId;
	}

	public void setUpdatedByMsgId(Long updatedByMsgId) {
		this.updatedByMsgId = updatedByMsgId;
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

	public String getMrtFullName() {
		return mrtFullName;
	}

	public void setMrtFullName(String mrtFullName) {
		this.mrtFullName = mrtFullName;
	}

	public String getMis1FullName() {
		return mis1FullName;
	}

	public void setMis1FullName(String mis1FullName) {
		this.mis1FullName = mis1FullName;
	}

	public String getMis2FullName() {
		return mis2FullName;
	}

	public void setMis2FullName(String mis2FullName) {
		this.mis2FullName = mis2FullName;
	}

	public Boolean getFollowUp() {
		return followUp;
	}

	public void setFollowUp(Boolean followUp) {
		this.followUp = followUp;
	}

	public Boolean getVoiceRecognitionUsed() {
		return voiceRecognitionUsed;
	}

	public void setVoiceRecognitionUsed(Boolean voiceRecognitionUsed) {
		this.voiceRecognitionUsed = voiceRecognitionUsed;
	}
	
}
