package scrips.datamigration.sss.repository;

import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssFloatingRatesTemp;

@Repository
@Transactional
public interface SssFloatingRatesTempDAO extends JpaRepository <JpaSssFloatingRatesTemp, String> {
	Optional<JpaSssFloatingRatesTemp> findById(String id);
}
