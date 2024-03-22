package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSSSAccountTemp;

@Repository
public interface SSSAccountTempDAO extends JpaRepository<JpaSSSAccountTemp,	String> {
	JpaSSSAccountTemp findByAccountId(String aacountId);
}
