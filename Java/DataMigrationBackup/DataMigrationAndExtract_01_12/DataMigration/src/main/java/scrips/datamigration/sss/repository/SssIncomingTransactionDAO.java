package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssIncomingTransaction;
@Repository
public interface SssIncomingTransactionDAO  extends JpaRepository<JpaSssIncomingTransaction, String> {

}
