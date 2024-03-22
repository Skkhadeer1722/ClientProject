package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssSecuritiesPositionStatsSource;
@Repository
public interface JpaSssSecuritiesPositionStatsSourceDAO extends JpaRepository<JpaSssSecuritiesPositionStatsSource, String> {

}