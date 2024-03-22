package scrips.datamigration.sss.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssTransaction;
@Repository
public interface SssTransactionDAO extends JpaRepository<JpaSssTransaction, String>{

	JpaSssTransaction findBySssReference(String sssReference);

}
