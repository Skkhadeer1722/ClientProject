package com.dataextract.sgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataextract.sgs.model.SssAllotment;
import com.dataextract.sgs.model.SssAllotmentId;

@Repository
public interface SssAllotmentDAO extends JpaRepository<SssAllotment, SssAllotmentId> {

//@Query("SELECT new com.dataextract.sgs.model.SssAllotmentModel(b.almAcutionDate,a.aldAlmtPrice,a.aldNomAmt,b.almFirstAlmt,a.aldSettAmt,b.almStat) FROM SssAllotmentMapping a INNER JOIN SssAllotment b ON a.aldAlmtDate=b.almAlmtDate and a.aldIsin = b.almIsin and a.aldIsinType=b.almIsinType")
//	List<SssAllotmentModel> getAllList();

}
