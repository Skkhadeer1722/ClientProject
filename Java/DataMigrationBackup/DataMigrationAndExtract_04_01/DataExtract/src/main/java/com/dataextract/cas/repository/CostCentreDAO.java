package com.dataextract.cas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataextract.cas.model.CbmCostCentre;
import com.dataextract.cas.model.CbmCostCentreId;
@Repository
public interface CostCentreDAO extends JpaRepository<CbmCostCentre, CbmCostCentreId>{

}
