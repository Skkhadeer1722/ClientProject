package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssSecuritiesCodeStatisticsTemp;
@Repository
public interface SssSecuritiesCodeStatisticsTempDAO extends JpaRepository<JpaSssSecuritiesCodeStatisticsTemp,String>{

}
