 package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.rtgs.model.JpaCbmDepositRate;
@Repository
public interface JpaCbmDepositRateDAO extends JpaRepository<JpaCbmDepositRate, String> {

}
