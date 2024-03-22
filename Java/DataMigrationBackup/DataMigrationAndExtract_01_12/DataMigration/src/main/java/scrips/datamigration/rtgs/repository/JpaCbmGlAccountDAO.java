package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scrips.datamigration.rtgs.model.JpaCbmGlAccount;

public interface JpaCbmGlAccountDAO extends JpaRepository<JpaCbmGlAccount,String>{

	JpaCbmGlAccount findByGlAccount(String glAccount);


}
