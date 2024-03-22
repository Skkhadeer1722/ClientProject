package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.rtgs.model.JpaCbmSoraRate;
import scrips.datamigration.rtgs.model.JpaCbmSoraRateId;
@Repository
public interface JpaCbmSoraRateDAO extends JpaRepository<JpaCbmSoraRate, JpaCbmSoraRateId>{

	JpaCbmSoraRate findByReferenceRateAndValueDate(String referenceRate,Integer valueDate);

}
