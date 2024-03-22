package com.dataextract.cas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataextract.cas.model.CbmLiabilitiesBaseDetail;
@Repository
public interface CbmLiabilitiesBaseDetailDAO extends JpaRepository<CbmLiabilitiesBaseDetail, String> {

}
