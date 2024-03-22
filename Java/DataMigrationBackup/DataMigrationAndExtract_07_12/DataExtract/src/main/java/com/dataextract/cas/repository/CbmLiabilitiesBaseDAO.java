package com.dataextract.cas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataextract.cas.model.CbmLiabilitiesBase;
import com.dataextract.cas.model.CbmLiabilitiesBaseId;
@Repository
public interface CbmLiabilitiesBaseDAO extends JpaRepository<CbmLiabilitiesBase, CbmLiabilitiesBaseId> {

}
