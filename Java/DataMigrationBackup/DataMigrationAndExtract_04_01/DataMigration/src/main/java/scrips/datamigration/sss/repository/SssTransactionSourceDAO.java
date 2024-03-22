package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssTransactionSource;
@Repository
public interface SssTransactionSourceDAO extends JpaRepository<JpaSssTransactionSource, String>{

}
