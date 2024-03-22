package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.rtgs.model.JpaCbmSoraRateTemp;
@Repository
public interface JpaCbmSoraRateTempDAO extends JpaRepository<JpaCbmSoraRateTemp, String> {

}
