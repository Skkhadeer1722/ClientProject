package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssFloatingRatesSource;
@Repository
public interface SssFloatingRatesSourceDAO extends JpaRepository <JpaSssFloatingRatesSource, String> {

}
