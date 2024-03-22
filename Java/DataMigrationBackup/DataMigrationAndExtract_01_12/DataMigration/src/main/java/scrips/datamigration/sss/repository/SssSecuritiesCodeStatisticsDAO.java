package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssSecuritiesCodeStatistics;
@Repository
public interface SssSecuritiesCodeStatisticsDAO extends JpaRepository<JpaSssSecuritiesCodeStatistics, String>{

	JpaSssSecuritiesCodeStatistics findBySecuritiesCode(String securitiesCode);

}
