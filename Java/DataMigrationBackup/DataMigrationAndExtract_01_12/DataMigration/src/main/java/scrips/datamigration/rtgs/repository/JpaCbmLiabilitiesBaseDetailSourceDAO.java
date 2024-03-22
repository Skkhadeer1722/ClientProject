package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.rtgs.model.JpaCbmLiabilitiesBaseDetailSource;
@Repository
public interface JpaCbmLiabilitiesBaseDetailSourceDAO extends JpaRepository<JpaCbmLiabilitiesBaseDetailSource, String> {

}
