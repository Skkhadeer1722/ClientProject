package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scrips.datamigration.rtgs.model.JpaCbmLiabilitiesBase;

public interface JpaCbmLiabilitiesBaseDAO extends JpaRepository<JpaCbmLiabilitiesBase, String> {

	JpaCbmLiabilitiesBase findByMemberId(String memberId);

}
