package scrips.datamigration.rtgs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import scrips.datamigration.rtgs.model.JpaCbmLiabilitiesBaseDetail;

public interface JpaCbmLiabilitiesBaseDetailDAO extends JpaRepository<JpaCbmLiabilitiesBaseDetail, String>{

	List<JpaCbmLiabilitiesBaseDetail> findByMemberId(String memberId);

}
