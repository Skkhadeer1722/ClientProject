package scrips.datamigration.rtgs.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.rtgs.model.JpaAccountPositionTemp;
@Transactional
@Repository
public interface JpaAccountPositionTempDAO extends JpaRepository<JpaAccountPositionTemp, Long>{

	JpaAccountPositionTemp findByAccountId(String accountId);


}
