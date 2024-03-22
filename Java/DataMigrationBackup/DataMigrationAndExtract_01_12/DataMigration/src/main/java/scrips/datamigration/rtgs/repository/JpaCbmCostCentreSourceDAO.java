package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.rtgs.model.JpaCbmCostCentreSource;
@Repository
public interface JpaCbmCostCentreSourceDAO extends JpaRepository<JpaCbmCostCentreSource,String>{

}
