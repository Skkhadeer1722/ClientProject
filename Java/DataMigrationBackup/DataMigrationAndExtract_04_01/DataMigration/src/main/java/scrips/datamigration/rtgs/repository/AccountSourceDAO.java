package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scrips.datamigration.rtgs.model.JpaAccountSource;

public interface AccountSourceDAO extends JpaRepository<JpaAccountSource,String>{

}
