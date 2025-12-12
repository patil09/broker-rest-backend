package com.radianbroker.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "diagrams")
public class Diagram {

	@Id
	@Column(name = "diagram_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long diagramId;

	@Column(name = "report_id")
	private Long reportId;

	@Column(name = "diagram_no")
	private int diagramNo;

	@Column(name = "creator", nullable = false)
	private Long creator;

	@Column(name = "date_verified", columnDefinition = "DATE")
	private LocalDate dateVerified;

	@Column(name = "file_type", nullable = false, columnDefinition = "ENUM('JSON','JPEG')")
	@Enumerated(EnumType.STRING)
	private FileType fileType;
	
	@Column(name = "file_path", nullable = false)
	private String filePath;
	
	public enum FileType{
		JSON, JPEG
	}

	public Long getDiagramId() {
		return diagramId;
	}

	public void setDiagramId(Long diagramId) {
		this.diagramId = diagramId;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public int getDiagramNo() {
		return diagramNo;
	}

	public void setDiagramNo(int diagramNo) {
		this.diagramNo = diagramNo;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public LocalDate getDateVerified() {
		return dateVerified;
	}

	public void setDateVerified(LocalDate dateVerified) {
		this.dateVerified = dateVerified;
	}
	
	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
