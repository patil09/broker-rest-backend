package com.radianbroker.repository;


import com.radianbroker.entity.Attachment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {

	@Query(value = "SELECT * FROM attachments WHERE ris_id = ?1 AND order_no = ?2", nativeQuery = true)
	List<Attachment> findAllByRisIdAndOrderNo(Long risId, Long orderNo);

	@Query(value = "SELECT * FROM attachments WHERE ris_id = ?1 AND order_no = ?2 AND name=?3", nativeQuery = true)
	Attachment findByRisIdAndOrderNoAndName(Long risId, Long orderNo, String name);

	@Query(value = "SELECT a.* FROM attachments a,reports r WHERE r.order_no =a.order_no AND r.ris_id=a.ris_id AND r.ris_id =?1 AND r.order_no=?2 AND r.mrt=?3", nativeQuery = true)
	List<Attachment> findAllByRisIdAndOrderNoAndUserId(Long risId, Long orderNo, Long mrtUserId);


	@Query(value = "SELECT * FROM attachments " +
			"WHERE  order_no = ?1",
			nativeQuery = true)
	List<Attachment> findAllByRisIdAndOrderNoAndWeeksAgo( Long orderNo);


}
