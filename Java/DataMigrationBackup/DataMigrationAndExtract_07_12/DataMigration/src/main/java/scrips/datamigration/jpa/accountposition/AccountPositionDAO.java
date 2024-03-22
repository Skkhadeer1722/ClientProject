package scrips.datamigration.jpa.accountposition;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Transactional
@Repository
public interface AccountPositionDAO extends JpaRepository<JpaAccountPosition, Long>{

	JpaAccountPosition findByAccountId(String accountId);
}
