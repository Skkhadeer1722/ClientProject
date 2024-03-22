package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssSecuritiesCodeTemp;
@Repository
public interface SssSecuritiesCodeTempDAO extends JpaRepository<JpaSssSecuritiesCodeTemp,String>{

}
