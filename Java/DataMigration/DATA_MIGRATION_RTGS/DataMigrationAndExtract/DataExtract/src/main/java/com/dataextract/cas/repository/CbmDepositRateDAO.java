package com.dataextract.cas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataextract.cas.model.CbmDepositRate;
@Repository
public interface CbmDepositRateDAO extends JpaRepository<CbmDepositRate, String> {

}
