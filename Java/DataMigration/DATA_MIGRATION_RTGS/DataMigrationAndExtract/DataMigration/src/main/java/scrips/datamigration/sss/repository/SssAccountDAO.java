package scrips.datamigration.sss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssAccount;
@Repository
public interface SssAccountDAO extends JpaRepository<JpaSssAccount, String>{

	List<JpaSssAccount> findByAccountId(String receiverAccountNo);
}
