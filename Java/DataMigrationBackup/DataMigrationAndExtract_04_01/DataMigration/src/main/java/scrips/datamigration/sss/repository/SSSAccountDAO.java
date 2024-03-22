package scrips.datamigration.sss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSSSAccount;
@Repository
public interface SSSAccountDAO extends JpaRepository<JpaSSSAccount, String>{

	List<JpaSSSAccount> findByAccountId(String receiverAccountNo);
}
