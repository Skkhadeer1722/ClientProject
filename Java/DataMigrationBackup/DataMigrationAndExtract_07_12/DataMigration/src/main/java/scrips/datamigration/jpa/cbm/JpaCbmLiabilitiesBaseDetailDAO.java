package scrips.datamigration.jpa.cbm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCbmLiabilitiesBaseDetailDAO extends JpaRepository<JpaCbmLiabilitiesBaseDetail, String>{

	List<JpaCbmLiabilitiesBaseDetail> findByMemberId(String memberId);

}
