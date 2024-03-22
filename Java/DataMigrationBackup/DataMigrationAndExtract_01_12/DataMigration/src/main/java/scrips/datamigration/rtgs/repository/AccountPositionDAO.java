package scrips.datamigration.rtgs.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.rtgs.model.JpaAccountPosition;
@Transactional
@Repository
public interface AccountPositionDAO extends JpaRepository<JpaAccountPosition, Long>{

	JpaAccountPosition findByAccountId(String accountId);
}
