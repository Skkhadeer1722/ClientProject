package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scrips.datamigration.rtgs.model.JpaMemberTemp;

public interface JpaMemberTempDAO extends JpaRepository<JpaMemberTemp, Long>{

}
