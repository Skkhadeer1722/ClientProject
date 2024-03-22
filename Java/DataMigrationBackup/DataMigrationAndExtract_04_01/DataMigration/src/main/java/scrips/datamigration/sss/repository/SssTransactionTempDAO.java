package scrips.datamigration.sss.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssTransactionTemp;
@Repository
public interface SssTransactionTempDAO extends JpaRepository<JpaSssTransactionTemp, String>{

}
