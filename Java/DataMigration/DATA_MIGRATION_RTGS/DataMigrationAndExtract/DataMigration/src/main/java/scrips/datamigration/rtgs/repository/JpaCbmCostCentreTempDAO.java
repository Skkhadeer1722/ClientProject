package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import scrips.datamigration.rtgs.model.JpaCbmCostCentreTemp;
@Repository
public interface JpaCbmCostCentreTempDAO extends JpaRepository<JpaCbmCostCentreTemp,String>{

	JpaCbmCostCentreTemp findByCostCentre(String costCentre);

}
