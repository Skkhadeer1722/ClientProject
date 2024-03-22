package com.dataextract.sgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataextract.sgs.model.SssAllotmentMapping;
import com.dataextract.sgs.model.SssAllotmentMappingId;

@Repository
public interface SssAllotmentMappingDAO extends JpaRepository<SssAllotmentMapping, SssAllotmentMappingId> {

}
