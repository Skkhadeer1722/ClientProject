package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssMemberTemp;
@Repository
public interface SssMemberTempDAO  extends JpaRepository<JpaSssMemberTemp, String>{

}
