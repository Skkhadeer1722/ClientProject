package com.dataextract.cas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataextract.cas.model.CbmGlAccount;
@Repository
public interface CbmGlAccountDAO extends JpaRepository<CbmGlAccount, String> {

}
