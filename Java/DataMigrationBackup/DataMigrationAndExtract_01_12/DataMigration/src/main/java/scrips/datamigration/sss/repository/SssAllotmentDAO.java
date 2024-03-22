package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssAllotment;
@Repository
public interface SssAllotmentDAO extends JpaRepository<JpaSssAllotment,String>{

	JpaSssAllotment findBySecuritiesCode(String securitiesCode);
	
}