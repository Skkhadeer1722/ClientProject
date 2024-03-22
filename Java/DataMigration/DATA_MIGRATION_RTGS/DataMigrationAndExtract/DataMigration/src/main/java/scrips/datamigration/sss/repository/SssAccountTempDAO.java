package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssAccountTemp;

@Repository
public interface SssAccountTempDAO extends JpaRepository<JpaSssAccountTemp,	String> {
	JpaSssAccountTemp findByAccountId(String aacountId);
}
