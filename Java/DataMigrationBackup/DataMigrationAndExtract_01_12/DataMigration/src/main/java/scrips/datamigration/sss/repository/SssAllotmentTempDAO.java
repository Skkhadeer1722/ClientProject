package scrips.datamigration.sss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.sss.model.JpaSssAllotmentTemp;
@Repository
public interface SssAllotmentTempDAO extends JpaRepository<JpaSssAllotmentTemp, String>{

}

