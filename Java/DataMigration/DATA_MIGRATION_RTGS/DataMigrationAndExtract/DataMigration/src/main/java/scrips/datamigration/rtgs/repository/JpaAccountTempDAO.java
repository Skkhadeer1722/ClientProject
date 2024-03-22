package scrips.datamigration.rtgs.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.rtgs.model.JpaAccountTemp;
@Repository
@Transactional
public interface JpaAccountTempDAO extends JpaRepository<JpaAccountTemp, String>{

	JpaAccountTemp findByAccountNumber(String accountNumber);

}
