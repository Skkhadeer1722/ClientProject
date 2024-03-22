package scrips.datamigration.sss.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssFloatingRates;
import scrips.datamigration.sss.model.JpaSssFloatingRatesId;

@Repository
@Transactional
public interface SssFloatingRatesDAO extends JpaRepository <JpaSssFloatingRates, JpaSssFloatingRatesId> {
	JpaSssFloatingRates findByReferenceRate(String referenceRate);

	JpaSssFloatingRates findByValueDate(Integer valueDate);

	JpaSssFloatingRates findByReferenceRateAndValueDate(String referenceRate, Integer valueDate);
}
