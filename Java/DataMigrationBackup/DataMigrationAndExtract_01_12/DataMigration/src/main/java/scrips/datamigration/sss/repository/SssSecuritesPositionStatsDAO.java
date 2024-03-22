package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssSecuritiesPositionStats;
@Repository
public interface SssSecuritesPositionStatsDAO extends JpaRepository<JpaSssSecuritiesPositionStats,String>{

	JpaSssSecuritiesPositionStats findBySecuritiesCode(String securitiesCode);

//	JpaSssSecuritiesPositionStats findByAccountId(String accountId);

	JpaSssSecuritiesPositionStats findBySecuritiesCodeAndAccountId(String securitiesCode, String string);

}
