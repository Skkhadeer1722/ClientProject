package scrips.datamigration.jpa.cbm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface JpaCbmSoraRateDAO extends JpaRepository<JpaCbmSoraRate, JpaCbmSoraRateId>{

	JpaCbmSoraRate findByReferenceRateAndValueDate(String referenceRate,Integer valueDate);

}
