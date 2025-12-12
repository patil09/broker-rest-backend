package com.radianbroker.repository;

import com.radianbroker.entity.Diagram;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiagramRepository extends CrudRepository<Diagram, Long>{

	@Query(value = "SELECT max(diagram_no) FROM diagrams d WHERE d.report_id = ?1", nativeQuery = true)
	Long findMaxDiagramNoReportId(Long reportId);

	@Query(value = "SELECT * FROM diagrams WHERE report_id = ?1", nativeQuery = true)
	List<Diagram> getAllByReportId(Long reportId);
	
	@Query(value = "SELECT * FROM diagrams WHERE report_id = ?1 AND file_type=?2 AND date_verified IS NULL", nativeQuery = true)
	List<Diagram> getAllByReportIdAndFileTypeAndDateVerifiedIsNull(Long reportId, String fileType);

	@Query(value = "SELECT * FROM diagrams WHERE report_id = ?1 AND diagram_id=?2", nativeQuery = true)
	Optional<Diagram> findByReportIdAndDiagramId(Long reportId, Long diagramId);

}
