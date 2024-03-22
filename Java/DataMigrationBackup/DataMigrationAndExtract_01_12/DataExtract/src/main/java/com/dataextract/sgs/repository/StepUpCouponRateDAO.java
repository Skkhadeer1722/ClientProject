package com.dataextract.sgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataextract.sgs.model.StepUpCouponRate;
@Repository
public interface StepUpCouponRateDAO extends JpaRepository<StepUpCouponRate, String>{

}
