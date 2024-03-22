package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.rtgs.model.JpaCbmDepositRateSource;
@Repository
public interface JpaCbmDepositRateSourceDAO extends JpaRepository<JpaCbmDepositRateSource, String> {

}
