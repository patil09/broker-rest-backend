package com.radianbroker.repository;

import com.radianbroker.entity.Report;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long>, JpaSpecificationExecutor<Report> {

	@Query(value = "SELECT * FROM reports r WHERE r.report_id = ?1", nativeQuery = true)
	Report getReport(long reportId);

	@Query(value = "SELECT * from reports r WHERE r.order_no = ?1 AND r.ris_id = ?2 AND r.prime_study = ?3", nativeQuery = true)
	List<Report> findByOrderNoAndRisIdAndPrimeStudy(long orderNo, Long risId, boolean isPrimeStudy);


	@Query(value = "SELECT group_concat(exam_code) FROM reports where ris_id = ?1 AND order_no=?2", nativeQuery = true)
	String getExamCodesByRisIdAndOrderNo(Long risId, Long orderNo);

	@Query(value = "SELECT * FROM reports r WHERE r.ris_id = ?1 AND r.visit_no = ?2", nativeQuery = true)
	Report findByRisIdAndVisitNo(Long risId, String visitNo);

	@Query(value = "SELECT * from reports r WHERE "
			+ " r.order_no = ?1 AND "
			+ " r.ris_id = ?2 AND "
			+ " r.prime_study = ?3", nativeQuery = true)
	List<Report> getfindByOrderNoAndRisIdAndPrimeStudy(Long orderNo, Long risId, boolean b);
}
