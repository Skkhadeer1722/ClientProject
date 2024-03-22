package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.rtgs.model.JpaMemberSource;
@Repository
public interface JpaMemberSourceDAO extends JpaRepository<JpaMemberSource, Long>{

}
