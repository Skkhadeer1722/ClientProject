package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.rtgs.model.JpaCbmLiabilitiesBaseSource;
@Repository
public interface JpaCbmLiabilitiesBaseSourceDAO extends JpaRepository<JpaCbmLiabilitiesBaseSource, String>{

}
