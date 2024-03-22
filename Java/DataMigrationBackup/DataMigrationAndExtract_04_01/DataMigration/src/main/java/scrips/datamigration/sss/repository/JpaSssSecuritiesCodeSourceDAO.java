package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssSecuritiesCodeSource;

@Repository
public interface JpaSssSecuritiesCodeSourceDAO extends JpaRepository<JpaSssSecuritiesCodeSource, String>{

}
