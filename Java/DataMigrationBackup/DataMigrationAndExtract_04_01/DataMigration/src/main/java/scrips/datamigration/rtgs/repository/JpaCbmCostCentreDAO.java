package scrips.datamigration.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scrips.datamigration.rtgs.model.JpaCbmCostCentre;


public interface JpaCbmCostCentreDAO extends JpaRepository<JpaCbmCostCentre,String>{

	JpaCbmCostCentre findByCostCentre(String costCentre);

}
