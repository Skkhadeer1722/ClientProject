package scrips.datamigration.rtgs.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.rtgs.model.JpaAccount;
@Repository
@Transactional
public interface AccountDAO extends JpaRepository<JpaAccount, String>{

	JpaAccount findByAccountNumber(String accountNumber);

}
