package scrips.datamigration.sss.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssSecuritiesPositionStatsTemp;
@Repository
public interface SsscSecuritesPositionStatsTempDAO extends JpaRepository<JpaSssSecuritiesPositionStatsTemp,String>{

}

