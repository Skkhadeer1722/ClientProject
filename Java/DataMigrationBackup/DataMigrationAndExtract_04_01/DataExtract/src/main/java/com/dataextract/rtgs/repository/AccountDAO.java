package com.dataextract.rtgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataextract.rtgs.model.RtgsAccount;
import com.dataextract.rtgs.model.RtgsAccountId;

@Repository
public interface AccountDAO extends JpaRepository<RtgsAccount, RtgsAccountId> {

}
