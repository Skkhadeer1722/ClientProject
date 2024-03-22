package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.rtgs.model.JpaCbmSoraRateSource;
@Repository
public interface JpaCbmSoraRateSourceDAO extends JpaRepository<JpaCbmSoraRateSource, String> {

}
