package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssSecuritiesCode;
@Repository
public interface SssSecuritiesCodeDAO extends JpaRepository<JpaSssSecuritiesCode,String>{

	JpaSssSecuritiesCode getBySecuritiesCode(String securitiesCode);
	JpaSssSecuritiesCode findBySecuritiesCode(String securitiesCode);

}
