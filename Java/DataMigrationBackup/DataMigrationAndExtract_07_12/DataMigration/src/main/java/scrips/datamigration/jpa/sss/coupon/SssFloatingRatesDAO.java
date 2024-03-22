package scrips.datamigration.jpa.sss.coupon;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SssFloatingRatesDAO extends JpaRepository <JpaSssFloatingRates, JpaSssFloatingRatesId> {
	JpaSssFloatingRates findByReferenceRate(String referenceRate);

	JpaSssFloatingRates findByValueDate(Integer valueDate);

	JpaSssFloatingRates findByReferenceRateAndValueDate(String referenceRate, Integer valueDate);
}
