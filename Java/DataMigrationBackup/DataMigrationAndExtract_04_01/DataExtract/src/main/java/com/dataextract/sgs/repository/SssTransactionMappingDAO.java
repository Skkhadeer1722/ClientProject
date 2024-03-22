package com.dataextract.sgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataextract.sgs.model.SssTransactionMapping;
@Repository
public interface SssTransactionMappingDAO extends JpaRepository<SssTransactionMapping, String>{

}
