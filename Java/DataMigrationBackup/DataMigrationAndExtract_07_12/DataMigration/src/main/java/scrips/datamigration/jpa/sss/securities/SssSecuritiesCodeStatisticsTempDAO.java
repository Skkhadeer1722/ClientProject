package scrips.datamigration.jpa.sss.securities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SssSecuritiesCodeStatisticsTempDAO extends JpaRepository<JpaSssSecuritiesCodeStatisticsTemp,String>{

}
