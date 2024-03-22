package com.dataextract.sgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataextract.sgs.model.SssTransaction;
@Repository
public interface SssTransactionDAO extends JpaRepository<SssTransaction, String> {

}
