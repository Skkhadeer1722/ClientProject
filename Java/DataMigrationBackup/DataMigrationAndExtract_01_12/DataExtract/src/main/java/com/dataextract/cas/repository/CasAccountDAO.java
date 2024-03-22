package com.dataextract.cas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataextract.cas.model.CasAccount;
import com.dataextract.cas.model.CasAccountId;

@Repository
public interface CasAccountDAO extends JpaRepository<CasAccount, CasAccountId> {

}
